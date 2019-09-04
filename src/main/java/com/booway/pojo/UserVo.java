package com.booway.pojo;

import java.util.Date;

import javax.persistence.Column;

public class UserVo 
{
	private Integer id;
	private String name;
	private String pwd;
	private Date startTime;
	private Date endTime;
	
	private Integer uid;
	private String uname;
	
	private String mname;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getPwd()
	{
		return pwd;
	}
	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}
	public Date getStartTime()
	{
		return startTime;
	}
	public void setStartTime(Date startTime) 
	{
		this.startTime = startTime;
	}
	public Date getEndTime()
	{
		return endTime;
	}
	public void setEndTime(Date endTime) 
	{
		this.endTime = endTime;
	}
	public String getUname()
	{
		return uname;
	}
	public void setUname(String uname) 
	{
		this.uname = uname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}

	
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", pwd=" + pwd + ", startTime=" + startTime + ", endTime="
				+ endTime + ", uid=" + uid + ", uname=" + uname + ", mname=" + mname + "]";
	}
	public UserVo() {}
	public UserVo(Integer id, String name, String pwd, Date startTime, Date endTime, Integer uid, String uname,
			String mname) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.startTime = startTime;
		this.endTime = endTime;
		this.uid = uid;
		this.uname = uname;
		this.mname = mname;
	}
	
	
	
	
	
}
