package com.booway.excel;

import java.io.Serializable;

/**
 * 内存数据表对象
 */
public class DataTable implements Serializable, Cloneable
{
	private static final long serialVersionUID = -6090445434572249084L;
	private String tableName = "";
	private DataRowCollection rows;
	private DataColumnCollection columns;
	private Object tag = null;

	/** 左中右页眉 */
	private String leftHeader;
	private String middleHeader;
	private String rightHeader;

	public DataTable()
	{
		// 初始化行列
		rows = new DataRowCollection();
		columns = new DataColumnCollection();

		// 设置列所属数据表
		columns.dataTable = this;
	}

	public DataTable(String tableName)
	{
		this.tableName = tableName;

		// 初始化行列
		rows = new DataRowCollection();
		columns = new DataColumnCollection();

		// 设置列所属数据表
		columns.dataTable = this;
	}

	/**
	 * 数据表新数据行（未添加到行集合中）
	 * @return
	 */
	public DataRow newRow()
	{
		DataRow row = new DataRow();
		row.dataTable = this;
		row.initItemMap();

		return row;
	}

	/**
	 * 获取数据表名
	 * @return
	 */
	public String getTableName()
	{
		return tableName;
	}

	/**
	 * 设置内存表名
	 * @param tableName
	 */
	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	/**
	 * 获取数据行集合
	 * @return
	 */
	public DataRowCollection getRows()
	{
		return rows;
	}

	/**
	 * 获取数据列集合（列名大小写不敏感）
	 * @return
	 */
	public DataColumnCollection getColumns()
	{
		return columns;
	}

	/**
	 * 修改列名称
	 * @param oldColumnName 原始列名称
	 * @param newColumnName 新列名称
	 */
	public void changeColumnName(String oldColumnName, String newColumnName)
	{
		DataColumn column = columns.getColumn(oldColumnName);

		// 如果列不存在，则不作后续操作
		if (column == null)
		{
			return;
		}

		column.setColumnName(newColumnName);

		// 将每行数据的ItemMap内名称修改为与现有列名对应
		for (DataRow row : rows)
		{
			Object value = row.getItemMap().remove(oldColumnName);
			row.setValue(column, value);
		}
	}

	/**
	 * 复制表结构
	 * @param copyDataRow 是否拷贝数据行
	 * @return
	 */
	public DataTable copy(boolean copyDataRow)
	{
		DataTable tableRe = new DataTable(this.tableName);
		// 初始化列
		for (DataColumn col : this.getColumns())
		{
			tableRe.getColumns().addNewColumn(col.getColumnName());
		}
		DataRow newRow = null;
		// 初始化数据行
		for (DataRow row : this.getRows())
		{
			newRow = tableRe.newRow();
			for (DataColumn col : this.getColumns())
			{
				newRow.setValue(col.getColumnName(), row.getValue(col));
			}
			tableRe.getRows().add(newRow);
		}
		newRow = null;
		return tableRe;
	}

	public Object getTag()
	{
		return tag;
	}

	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}

	public String getLeftHeader()
	{
		return leftHeader;
	}

	public void setLeftHeader(String leftHeader)
	{
		this.leftHeader = leftHeader;
	}

	public String getMiddleHeader()
	{
		return middleHeader;
	}

	public void setMiddleHeader(String middleHeader)
	{
		this.middleHeader = middleHeader;
	}

	public String getRightHeader()
	{
		return rightHeader;
	}

	public void setRightHeader(String rightHeader)
	{
		this.rightHeader = rightHeader;
	}
}
