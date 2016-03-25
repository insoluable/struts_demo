package com.rustykadai.struts;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

// Create one and only one connection object to be used for all calling classes
// TODO: Look at connection pooling
public class DbConnSingleton {
	
	private static final Logger logger = LogManager.getLogger(DbConnSingleton.class.getName());
	private static DbConnSingleton instance = null;
	protected DbConnSingleton() {};
	
	public static DbConnSingleton getInstance()	{
		
		if (instance == null)	{
			
			instance = new DbConnSingleton();
		}
		
		return instance;
	}
	
	static String connStringTemplate = 
			"jdbc:oracle:thin:@(description=(address=" 
			+ "(host=%s)(protocol=tcp)(port=1521))"
					+"(connect_data=(sid=%s)))"; 
	private Connection conn = null;
	private String connString = null;
	private String user = null;
	private String pass = null;
	
	
	
	public void setConnection(String db) throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException	{
		
		Properties properties = new Properties();
		
		try{
			
			properties.load(DbConnSingleton.class.getClassLoader().getResourceAsStream("db.properties"));
		}
		
		catch(Exception e)	{
			
			logger.error("Problem loading properties file. " + e.getMessage());
			System.exit(0);
			}
		
		
		switch (db)	{
		
		case "xe":
			
			connString = String.format(connStringTemplate, properties.getProperty("host"), properties.getProperty("sid"));
			user = properties.getProperty("user");
			pass = properties.getProperty("pass");

			break;
			

			
		default: 
			
			logger.error("Invalid db credentials. Check db.properties");
			
		}
				
		// Note this driver call: ODBC class no longer works
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		try {conn = DriverManager.getConnection(connString,user,pass);}
		
		catch(Exception e)	{
			
			logger.error("Could not connect to " + db + " as user " + user + ". " + e.getMessage() );
			System.exit(0);
		}
		
		
		logger.info("Connected to " + db + " as user " + user );
		
	}
	
	public Connection getConnection()	{
		
		return this.conn;
	}
	

}
