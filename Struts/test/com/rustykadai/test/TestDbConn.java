package com.rustykadai.test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import com.rustykadai.struts.DbConnSingleton;

import org.junit.Test;


public class TestDbConn {


	@Test
	public void test() throws SQLException, InstantiationException,
			IllegalAccessException, ClassNotFoundException {

		DbConnSingleton.getInstance().setConnection("xe");
		Connection conn  = DbConnSingleton.getInstance().getConnection();

		assertFalse(conn.isClosed());
		conn.close();
		assertTrue(conn.isClosed());
	}

}
