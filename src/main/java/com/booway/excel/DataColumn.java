package com.booway.excel;

import java.io.Serializable;
import java.sql.Types;

/**
 * 数据表列管理
 * @author shijiaokun
 */
public class DataColumn implements Serializable
{
	private static final long serialVersionUID = -5513634240671235859L;
	private String columnName; // fieldName
	private String columnLable; // caption
	private int dataType; // java.sql.Types

	private Object tag;
	protected DataTable dataTable;

	public DataColumn()
	{
		this.columnName = "";
		this.columnLable = "";
		this.dataType = Types.NULL;
	}

	public DataColumn(String columnName)
	{
		this.columnName = columnName;
		this.columnLable = columnName;
		this.dataType = Types.NULL;
	}

	public DataColumn(String columnName, String columnLable)
	{
		this.columnName = columnName;
		this.columnLable = columnLable;
		this.dataType = Types.NULL;
	}

	public String getColumnName()
	{
		return columnName;
	}

	public String getColumnLable()
	{
		return columnLable;
	}

	public int getDataType()
	{
		return dataType;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	public void setColumnLable(String columnLable)
	{
		this.columnLable = columnLable;
	}

	public void setDataType(int dataType)
	{
		this.dataType = dataType;
	}

	public DataTable getDataTable()
	{
		return dataTable;
	}

	public Object getTag()
	{
		return tag;
	}

	public void setTag(Object tag)
	{
		this.tag = tag;
	}

}
