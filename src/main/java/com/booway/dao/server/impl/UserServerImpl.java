package com.booway.dao.server.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.booway.dao.UserRespority;
import com.booway.dao.server.UserServer;
import com.booway.pojo.User;
import com.booway.pojo.User2;
import com.booway.pojo.User3;
import com.booway.pojo.UserVo;
import com.booway.util.ExcelUtil;
import com.booway.util.SqlUtils;

@Service
public class UserServerImpl implements UserServer 
{
	@Autowired
	UserRespority userRespority;

	@PersistenceContext
	EntityManager entityManager;
	@Override
	public int saveUser(User user)
	{
		User save = userRespority.save(user);
		return 0;
	}

	@Override
	public List<User> findByName(String condition, Pageable pageable) 
	{
		List<User> list = userRespority.findByName(condition, pageable);
		return list;
	}

	@Override
	public List<User> findByNameLike(String condition, Pageable pageable) 
	{
		List<User> list = userRespority.findByNameLike(condition, pageable);
		return list;
	}

	@Override
	public User findByNameAndPwd(String name, String pwd)
	{
		User user = userRespority.findByNameAndPwd(name, pwd);
		return user;
	}

	@Override
	public List<User> findByendTimeBetween(Date d1, Date d2, Pageable pageable) 
	{
		List<User> list = userRespority.findByendTimeBetween(d1, d2, pageable);
		return list;
	}

	@Override
	public Page<User> findAll(User user,Pageable pageable) 
	{
		
		Specification<User> specification = new Specification<User>()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * root : 匹配实体属性的
			 * query :查询拼接
			 * criteriaBuilder：查询条件
			 */
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (user.getName() != null)
				{
					Predicate predicate = criteriaBuilder.like(root.get("name"),"%"+user.getName()+"%");
					predicates.add(predicate);
				}
				
				if (user.getPwd() != null)
				{
					Predicate predicate = criteriaBuilder.like(root.get("pwd"),"%"+user.getPwd()+"%");
					predicates.add(predicate);
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		};
		
		/**
		 * 利用自带的findAll()方法的
		 */
		Page<User> page = userRespority.findAll(specification, pageable);
		//userRespority.findAll(specification);
		
		return page;
	}

	@Override
	public List<User> queryName(String name)
	{
		return userRespority.queryName(name);
	}

	@Override
	public List<User> queryName(String name, String pwd)
	{
		return userRespority.queryName(name, pwd);
	}

	@Override
	public List<User> queryName(String name, String pwd, Integer id) 
	{
		return userRespority.queryName(name,pwd,id);
	}

	@Override
	public List<User> queryAll()
	{
		return userRespority.queryAll();
	}

	@Override
	public List<User> findCondition(User user) 
	{
		// JpaSpecificationExecutor<Users>  and的优先级高于or
		Specification<User> specification = new Specification<User>()
		{
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return  cb.or(cb.and(cb.equal(root.get("name"),user.getName()),cb.equal(root.get("pwd"),user.getPwd())));
			}
			
		};
		
		List<User> list = null;
		//userRespority.findCondition(specification);
		return list;
	}

	@Override
	public List<User> findCondition(Date dateStart, Date dateEnd) 
	{
		   Specification<User> sepcification = new Specification<User>() 
		   {
			   
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb)
			{
				Predicate predicateStart =  (dateStart != null) ? (dateEnd != null)
			            ? cb.between(root.get("startTime"), dateStart, dateEnd)
			                : cb.greaterThanOrEqualTo(root.get("startTime"), dateStart) 
			                : (dateEnd != null) 
			                    ? cb.lessThanOrEqualTo(root.get("startTime"), dateEnd):null;
			                      

				return cb.and(predicateStart);
			}
			   
		};
		List<User> list = userRespority.findAll(sepcification, new Sort(Direction.ASC,"startTime"));
		for (User user : list)
		{
			System.out.println(user);
		}
		return list;
	}

	@Override
	public File exportFile(String condition) 
	{
		File file = new File("D:\\a.xls");
		List<User> list = userRespority.queryName(condition);
		for (User u : list)
		{
			System.out.println(u);
		}
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("user");
		
		try
		{
			ExcelUtil.createData(list, sheet);
			
		}
		catch (Exception e1) 
		{
			e1.printStackTrace();
		} 
		
		try(FileOutputStream fos = new FileOutputStream(file);)
		{
			workbook.write(fos);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return file;
	}

	@Override
	public List<UserVo> queryVo(UserVo userVo) throws Exception 
	{
		SqlUtils s = new SqlUtils();
		
		List<Class<?>> list = new ArrayList<>();
		list.add(User.class);
		list.add(User2.class);
		list.add(User3.class);
		List<String> strings = new ArrayList<String>();
		strings.add("id");
		strings.add("uid");
		StringBuilder stringBuilder = s.getFilds(list, UserVo.class, strings);
		stringBuilder.append(" User user, User2 user2 , User3 user3 where  user.id = user2.id and user2.uid = user3.uid");
		System.out.println(stringBuilder.toString());
		Query query = entityManager.createQuery(stringBuilder.toString());
		@SuppressWarnings("unused")
		List<UserVo> resultList = query.getResultList();
		for (UserVo u : resultList)
		{
			System.out.println(u);
		}
		return resultList;
	}
}
