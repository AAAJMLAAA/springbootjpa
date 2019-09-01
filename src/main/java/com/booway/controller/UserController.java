package com.booway.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booway.dao.server.UserServer;
import com.booway.pojo.User;

@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	UserServer userServer;

	@RequestMapping(value="/3",method=RequestMethod.POST)
	 public  List<User> hello3(String condition,@RequestParam(value = "pageSize") int pageSize,@RequestParam(value = "pageNum") int pageNum)
	  {
		QPageRequest qr = new QPageRequest(pageNum,pageSize);
		//Pageable of = PageRequest.of(pageNum, pageSize,sort);
		List<User> list = userServer.findByNameLike(condition, null);
		 return list;
	  }
	
	@RequestMapping(value="/4",method=RequestMethod.POST)
	public Page<User> queryMohu()
	{
		User user = new User();
		user.setName("jm");
		user.setPwd("123");
		PageRequest of = PageRequest.of(0, 10);
		Page<User> findMoHu = userServer.findAll(user,of);
		
		for (User u : findMoHu)
		{
			System.out.println(u);
		}
		return findMoHu;
	}
	
	@RequestMapping(value="/5",method=RequestMethod.POST)
	public List<User> queryMohu5()
	{
		
		List<User> findMoHu = userServer.findByName("jm8", null);
		
		for (User u : findMoHu)
		{
			System.out.println(u);
		}
		return findMoHu;
	}
	
	@RequestMapping(value="/7",method=RequestMethod.POST)
	public List<User> queryMohu7()
	{
		
		List<User> findMoHu = userServer.queryName("jm8", "1238");
		
		for (User u : findMoHu)
		{
			System.out.println(u);
		}
		return findMoHu;
	}
	
	@RequestMapping(value="/6",method=RequestMethod.POST)
	public List<User> queryMohu6()
	{
		
		List<User> findMoHu = userServer.queryName("jm7");
		
		for (User u : findMoHu)
		{
			System.out.println(u);
		}
		return findMoHu;
	}
}
