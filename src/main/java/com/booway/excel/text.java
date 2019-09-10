package com.booway.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class text
{
	@ds(DAAA.between)
	private String name;
	public static void main(String[] args) throws Exception
	{
		/**
		 * 进行数据保存时，一定要保存数据是否都正确才开始进行保存
		 * 在保存的时候的将查询出来的数据放在内存中，然后通过内存进行获取
		 */
		File file = new File("D:\\user.xls");
		file.createNewFile();
		FileInputStream is = new FileInputStream(file);
		
		Workbook workbook = new HSSFWorkbook(is);
		Sheet sheet = workbook.getSheet("user");
		Row row = sheet.getRow(0);
		int s = row.getPhysicalNumberOfCells();
		Map<String,Object> maps = new HashMap<String,Object>();
		List<String> list = new ArrayList<>();
		List<S1> s1 = new ArrayList<S1>();
		for (short x = 0; x < s; x++)
		{
			list.add(row.getCell(x).getStringCellValue());
		}
		int t = sheet.getLastRowNum()+1;
		
		for (int y = 1 ; y < t ; y++)
		{
			S1 d1= new S1(); 
			Row r = sheet.getRow(y);
			int cells = r.getPhysicalNumberOfCells();
			for (int a = 0; a < cells; a++)
			{
				maps.put(list.get(a), r.getCell(a).getStringCellValue());
			}
			
			d1.setMaps(maps);
			s1.add(d1);
		}
		
		System.out.println("开始内存操作======================");
		
		for (S1 s2 : s1)
		{
			System.out.println(s2.getMaps().get("pwd"));
			System.out.println(s2.getMaps().get("name"));
		}
		workbook.close();
	}
}
