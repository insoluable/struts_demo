package com.rustykadai.struts;



public class Table {
	
	private String owner;
	private String tableName;
	private String lastAnalyzed;
	private String comments;


	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getLastAnalyzed() {
		return lastAnalyzed;
	}
	public void setLastAnalyzed(String lastAnalyzed) {
		this.lastAnalyzed = lastAnalyzed;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	
}
