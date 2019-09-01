package com.booway.dao.server;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.booway.pojo.User;

public interface UserServer 
{
	int saveUser(User user);
	List<User> findByName(String condition,Pageable pageable);
	List<User> findByNameLike(String condition,Pageable pageable);
	User findByNameAndPwd(String name,String pwd);
	List<User> findByendTimeBetween(Date d1,Date d2,Pageable pageable);
	Page<User> findAll(User user,Pageable pageable);
	List<User> queryName(String name);
	List<User> queryName(String name,String pwd);
	List<User> queryName(String name,String pwd,Integer id);
	List<User> queryAll();
	
	/**
	 * 通过spec
	 * @param user
	 * @return
	 */
	List<User> findCondition(User user);

}
