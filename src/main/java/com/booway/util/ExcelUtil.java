package com.booway.util;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelUtil
{
	public static void createData(List list,Sheet sheet) throws IllegalArgumentException, IllegalAccessException, InstantiationException
	{
		if (!list.isEmpty())
		{
			Row rowHead = sheet.createRow(0);
			Field[] field = list.get(0).getClass().getDeclaredFields();
			for (int y = 0 ; y < field.length ;y++)
			{
				// 获取属性
				rowHead.createCell(y).setCellValue(field[y].getName());
			}
			
			for (int x = 0; x < list.size();x++ )
			{
				Row row = sheet.createRow(x+1);
				Object obj = list.get(x);
				System.out.println(obj);
				Field[] fields = obj.getClass().getDeclaredFields();
				
				for (int z = 0 ; z < fields.length ;z++)
				{
					fields[z].setAccessible(true);
					// 获取属性值
					Object object =  fields[z].get(obj);
					row.createCell(z).setCellValue(String.valueOf(object));
				}
				

			}
		}
		
	}
}
