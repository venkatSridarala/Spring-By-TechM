package com.tmf.store.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	final static String USER_NAME = "root";
	final static String PASSWORD = "rootpassword";
	final static String DB_URL = "jdbc:mysql://localhost:3306/toysdb";
	final static String DRIVER = "com.mysql.cj.jdbc.Driver";

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		return conn;
	}
}
