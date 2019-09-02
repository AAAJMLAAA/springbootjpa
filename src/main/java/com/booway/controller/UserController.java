package com.booway.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@RequestMapping(value="/9",method=RequestMethod.POST)
	public List<User> queryMohu9() throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = sdf.parse("2019-8-23");
		Date d2 = sdf.parse("2019-8-28");
		List<User> findMoHu = userServer.findCondition(d1, d2);
		
		
		return findMoHu;
	}
	
	@RequestMapping(value="/10",method=RequestMethod.POST)
	public List<User> queryMohu10() throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//Date d1 = sdf.parse("2019-8-23");
		Date d2 = sdf.parse("2019-9-12");
		List<User> findMoHu = userServer.findCondition(null, d2);
		
		
		return findMoHu;
	}
	
	@RequestMapping(value="/11",method=RequestMethod.POST)
	public List<User> queryMohu11() throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = sdf.parse("2019-8-28");
		//Date d2 = sdf.parse("2019-9-12");
		List<User> findMoHu = userServer.findCondition(d1, null);
		
		
		return findMoHu;
	}
	
	@RequestMapping(value="/12")
	@ResponseBody
	public String queryMohu12(HttpServletResponse response) throws ParseException
	{
		File file = userServer.exportFile("jm7");
		  //下载的文件携带这个名称
	    response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
	    //文件下载类型--二进制文件
	    response.setContentType("application/octet-stream");
	   
	    try(InputStream is = new FileInputStream(file);
				OutputStream os=response.getOutputStream();) 
	    {
	    	int len = 0;
			byte[] b = new byte[1024];
			while((len = is.read(b)) != -1)
			{
				os.write(b, 0, len);				
			}
		} 
	    catch (IOException e) 
	    {
			e.printStackTrace();
		}
	  
	    return "下载成功";
}
}
