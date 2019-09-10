package com.booway.excel;

import java.util.ArrayList;

/**
 * 数据表列集合
 */
public class DataColumnCollection extends ArrayList<DataColumn>
{
	private static final long serialVersionUID = -7479928637350982636L;
	protected DataTable dataTable;

	/**
	 * 根据列名获取列对象
	 * @param columnName 列名
	 * @return
	 */
	public DataColumn getColumn(String columnName)
	{
		for (DataColumn column : this)
		{
			if (column.getColumnName().equalsIgnoreCase(columnName))
			{
				return column;
			}
		}
		// 如果获取列没有获取到，则获取tag值中的值
		for (DataColumn column : this)
		{
			if (!"".equals(parseStr(column.getTag())))
			{
				if (parseStr(column.getTag()).equalsIgnoreCase(columnName))
				{
					return column;
				}
			}

		}
		return null;
	}

	private String parseStr(Object obj)
	{
		if (obj == null)
		{
			return "";
		}
		return obj.toString().trim();
	}

	/**
	 * 根据列名获取列所索引
	 * @param columnName 列名
	 * @return 没有改列，返回-1，否则返回列索引
	 */
	public int indexOf(String columnName)
	{
		for (DataColumn column : this)
		{
			if (column.getColumnName().equalsIgnoreCase(columnName))
			{
				return this.indexOf(column);
			}
		}

		return -1;
	}

	/**
	 * 添加新列
	 * @param column
	 * @return
	 */
	public DataColumn addNewColumn(String columnName)
	{
		if (dataTable == null)
		{
			return null;
		}
		// 是否有列
		DataColumn dataColumn = this.getColumn(columnName);
		if (dataColumn != null)
		{
			return dataColumn;
		}
		dataColumn = new DataColumn(columnName);
		dataColumn.dataTable = dataTable;

		// 添加列
		if (!this.add(dataColumn))
		{
			return null;
		}
		// 遍历行进行新增列的行数据添加
		for (DataRow row : dataTable.getRows())
		{
			row.initItemMap();
		}

		return dataColumn;
	}

	/**
	 * 添加新列
	 * @param column
	 * @param index
	 * @return
	 */
	public DataColumn addNewColumn(String columnName, int index)
	{
		if (dataTable == null)
		{
			return null;
		}
		// 是否有列
		DataColumn dataColumn = this.getColumn(columnName);
		if (dataColumn != null)
		{
			return dataColumn;
		}
		dataColumn = new DataColumn(columnName);
		dataColumn.dataTable = dataTable;

		// 添加列
		this.add(index, dataColumn);
		// 遍历行进行新增列的行数据添加
		for (DataRow row : dataTable.getRows())
		{
			row.initItemMap();
		}

		return dataColumn;
	}

	/**
	 * 移除列
	 * @param columnName 列名称
	 * @return
	 */
	public boolean removeColumn(String columnName)
	{
		if (dataTable == null)
		{
			return false;
		}
		// 是否有列
		DataColumn dataColumn = this.getColumn(columnName);
		if (dataColumn == null)
		{
			return true;
		}
		if (!this.remove(dataColumn))
		{
			return false;
		}
		// 遍历行，进行移除列的数据删除
		for (DataRow row : dataTable.getRows())
		{
			row.onDataTableColumnRemove(columnName);
		}

		return true;
	}
}
