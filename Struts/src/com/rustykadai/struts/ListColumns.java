package com.rustykadai.struts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.opensymphony.xwork2.ActionSupport;

public class ListColumns extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String owner;
	private String tableName;

	private List<Column> columnList = new ArrayList<Column>();
	private static final Logger logger = LogManager.getLogger(ListColumns.class
			.getName());

	public String execute() {

		Connection conn = null;
		try {
			conn = DbConnSingleton.getInstance().getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage());
		}

		final String SQL_1 = "select a.COLUMN_NAME,a.DATA_TYPE,a.DATA_LENGTH,a.DATA_PRECISION,a.DATA_SCALE,b.COMMENTS "
				+ "from ALL_TAB_COLS a JOIN ALL_COL_COMMENTS b ON A.COLUMN_NAME = B.COLUMN_NAME "
				+ "where a.TABLE_NAME = ? and b.owner = ? and a.TABLE_NAME = b.TABLE_NAME";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			
			ps = conn.prepareStatement(SQL_1);
			ps.setString(1, tableName);
			ps.setString(2, owner);
			rs = ps.executeQuery();
			
			while (rs.next()) {

				Column column = new Column();
				column.setColumnName(rs.getNString("COLUMN_NAME"));
				column.setDataType(rs.getNString("DATA_TYPE"));
				column.setDataLength(rs.getNString("DATA_LENGTH"));
				column.setDataPrecision(rs.getNString("DATA_PRECISION"));
				column.setDataScale(rs.getNString("DATA_SCALE"));
				column.setComments(rs.getNString("COMMENTS"));
				columnList.add(column);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block

			logger.error("Column listing error",e);
			return ERROR;
		}

		logger.info("Retrieved column list for table: " + tableName);
		return SUCCESS;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public List<Column> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}

}
