package com.booway.excel;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据表行管理
 * @author shijiaokun
 */
public class DataRow implements Serializable
{
	private static final long serialVersionUID = -4964515718789457729L;
	protected DataTable dataTable;
	private Map<String, Object> itemMap;

	protected DataRow()
	{

	}

	public DataTable getDataTable()
	{
		return dataTable;
	}

	/**
	 * 获取列值
	 * @param columnName 列名
	 * @return
	 */
	public Object getValue(String columnName)
	{
		if (dataTable == null)
		{
			return null;
		}

		DataColumn column = dataTable.getColumns().getColumn(columnName);
		if (column == null)
		{
			return null;
		}

		// 查找列
		return itemMap.get(column.getColumnName());
	}

	/**
	 * 设置列值
	 * @param columnName 列名
	 * @param value 列值
	 */
	public void setValue(String columnName, Object value)
	{
		if (dataTable == null)
		{
			return;
		}

		DataColumn column = dataTable.getColumns().getColumn(columnName);
		if (column == null)
		{
			return;
		}

		if (!itemMap.containsKey(column.getColumnName()))
		{
			return;
		}

		// 设置值
		itemMap.put(columnName, value);
	}

	/**
	 * 获取列值
	 * @param columnName 列名
	 * @return
	 */
	public Object getValue(DataColumn column)
	{
		if (dataTable == null)
		{
			return null;
		}
		if (column == null)
		{
			return null;
		}

		DataColumn columnIn = dataTable.getColumns().getColumn(column.getColumnName());
		if (columnIn == null)
		{
			return null;
		}

		// 查找列
		return itemMap.get(columnIn.getColumnName());
	}

	/**
	 * 设置列值
	 * @param columnName 列名
	 * @param value 列值
	 */
	public void setValue(DataColumn column, Object value)
	{
		if (dataTable == null)
		{
			return;
		}
		if (column == null)
		{
			return;
		}

		if (!itemMap.containsKey(column.getColumnName()))
		{
			return;
		}

		// 设置值
		itemMap.put(column.getColumnName(), value);
	}

	/**
	 * 初始化数据行列数据
	 */
	protected void initItemMap()
	{
		if (itemMap == null)
		{
			itemMap = new HashMap<String, Object>();
		}
		if (dataTable == null)
		{
			return;
		}

		// 遍历列进行初始化行数据
		for (DataColumn column : dataTable.getColumns())
		{
			// 如果存在，则继续
			if (itemMap.containsKey(column.getColumnName()))
			{
				continue;
			}

			// 初始化为 null 值
			itemMap.put(column.getColumnName(), null);
		}
	}

	/**
	 * 所属数据表列移除事件
	 * @param removeColumnName 移除的列名
	 */
	protected void onDataTableColumnRemove(String removeColumnName)
	{
		if (itemMap == null)
		{
			itemMap = new HashMap<String, Object>(0);
		}

		itemMap.remove(removeColumnName);

	}

	/**
	 * 获取行数据集合
	 * @return 行数据的副本
	 */
	public Map<String, Object> getItemMap()
	{
		if (itemMap == null)
		{
			return new HashMap<String, Object>(0);
		}
		return Collections.synchronizedMap(itemMap);
	}

}
