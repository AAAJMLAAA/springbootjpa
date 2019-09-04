package com.booway.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jpa多表拼接
 * @author jinmingliang
 *
 */
public class SqlUtils 
{
	// 获取属性
	public <T>  StringBuilder getFilds(List<Class<?>> list,Class<?> t,List<String> listString)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select new ");
		sb.append((t.getPackage()+"."+t.getSimpleName()).substring(8)+"(");
		Map<String,Integer> maps = new HashMap<String,Integer>();
		
		for (Class<?> t1 : list)
		{
			Field[] fields = t1.getDeclaredFields();
			for(Field filed : fields)
			{
				
				if (listString.contains(filed.getName()))
				{
					 if (maps.containsKey(filed.getName()))
					 {
						 continue;
					 }
					 else
					 {
						 maps.put(filed.getName(), 1);
						 sb.append(t1.getSimpleName().toLowerCase()+"."+filed.getName()+", ");
					 }
					 continue;
				}
				sb.append(t1.getSimpleName().toLowerCase()+"."+filed.getName()+", ");
			}
		}
		sb.deleteCharAt(sb.lastIndexOf(",")).append(") from ");
		System.out.println(sb.toString());
		return sb;
	}
}
