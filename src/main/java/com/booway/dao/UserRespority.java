package com.booway.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.booway.pojo.User;

public interface UserRespority extends JpaRepository<User, Long>,JpaSpecificationExecutor<User>
{
	/**
	 * 基本查询用这种
	 * @param condition
	 * @param pageable
	 * @return
	 */
	List<User> findByName(String condition,Pageable pageable);
	List<User> findByNameLike(String condition,Pageable pageable);
	User findByNameAndPwd(String name,String pwd);
	List<User> findByendTimeBetween(Date d1,Date d2,Pageable pageable);
	List<User> findAll(Specification<User> user);
	
	/**
	 * 占位符，参数按顺序赋值
	 * @param name
	 * @return
	 */
	@Query(value="select * from user where name =?",nativeQuery = true)
	//@Query("from user where name = ?") // JDBC style parameters (?) are not supported for JPA queries.
	List<User> queryName(String name);
	
	@Query("from User where name = :name and pwd = :pwd")
	List<User> queryName(String name,String pwd);
	 
	@Query("from User where name = :name and pwd = :pwd and id = :id")
	List<User> queryName(String name,String pwd,Integer id);
	
	/**
	 * 本地sql查询
	 * @return
	 */
	@Query(value="select * from user",nativeQuery = true)
	List<User> queryAll();
	
//	public List<User> findCondition(Specification<User> specification); 
	
}
