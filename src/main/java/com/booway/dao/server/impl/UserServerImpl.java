package com.booway.dao.server.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.booway.dao.UserRespority;
import com.booway.dao.server.UserServer;
import com.booway.pojo.User;

@Service
public class UserServerImpl implements UserServer 
{
	@Autowired
	UserRespority userRespority;

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

}
