package com.rustykadai.struts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.opensymphony.xwork2.ActionSupport;


public class ListTables extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String db;
	private String owner;
	private String results;
	private List<Table> recordList = new ArrayList<Table>();
	private static final Logger logger = LogManager.getLogger(ListTables.class);


	public List<Table> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Table> recordList) {
		this.recordList = recordList;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String execute() {

		final String SQL_1 = "select a.OWNER, a.TABLE_NAME, a.LAST_ANALYZED, b.COMMENTS from ALL_TABLES a"
				+ " JOIN ALL_TAB_COMMENTS b on a.TABLE_NAME = b.TABLE_NAME"
				+ " where a.OWNER = ?";
		PreparedStatement ps = null;

		try {

			logger.info("Setting connection for: " + db);
			DbConnSingleton.getInstance().setConnection(db);
			Connection conn = DbConnSingleton.getInstance().getConnection();

			ps = conn.prepareStatement(SQL_1);
			ps.setString(1, owner);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Table table = new Table();
				table.setOwner(rs.getString("OWNER"));
				table.setTableName(rs.getString("TABLE_NAME"));
				table.setLastAnalyzed(rs.getString("LAST_ANALYZED"));
				table.setComments(rs.getString("COMMENTS"));
				recordList.add(table);

			}

		} catch (Exception e) {

			logger.error(e.getMessage());
			return ERROR;
		}
		
		logger.info("Returned list of tables owned by: " + owner );
		return SUCCESS;
	}
}
