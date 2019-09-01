package com.booway.util;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NaticateSql 
{
	@Autowired
	  EntityManager entityManager;
	 
	 public  List queryObject(Map<String,Object> maps)
	 {
		 StringBuilder sb = new StringBuilder();
		 sb.append("select * from user where 1 = 1");
		 if (!maps.isEmpty())
		 {
			 for (Map.Entry<String, Object> map : maps.entrySet())
			 {
				 sb.append("and "+map.getKey() +"like"+map.getValue());
			 } 
		 }
		 Query query = entityManager.createNativeQuery(sb.toString());
		 List list = query.getResultList();
		 return list;
	 }
}
