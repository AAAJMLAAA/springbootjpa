package com.booway;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.booway.pojo.User;
import com.booway.pojo.User2;
import com.booway.pojo.UserVo;

public class text 
{
	public static void main(String[] args)
	{
		String sc = User.class.getPackage()+"."+User.class.getSimpleName();
		//String i = sc.replaceAll("package", "");
		System.out.println(sc.substring(8));
		System.out.println();
		User user = new User();
		User2 user2 = new User2();
		List<Class<?>> list = new ArrayList<>();
		list.add(User.class);
		list.add(User2.class);
		List<String> list2 = new ArrayList<String>();
		list2.add("id");
		getFilds(list,UserVo.class,list2);
	}
	
	
	// 获取属性
	public static void getFilds(List<Class<?>> list,Class<?> t,List<String> listString)
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
		sb.deleteCharAt(sb.lastIndexOf(",")).append(") from");
		System.out.println(sb.toString());
	}
}
