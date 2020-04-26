package com.customermanagementsystem.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
		
	public DatabaseConfig(){}

	public static final String MYSQL_DRIVER_CLASS_NAME= "com.mysql.cj.jdbc.Driver";
	public static final String USERNAME = "cmssys";
	public static final String PASSWORD = "Cms@kennesaw2020";
	public static final String MYSQL_DB = "jdbc:mysql://localhost:3306/cms";
	
	public Connection getConnection(){
		Connection conn = null;
		
		try {
			Class.forName(MYSQL_DRIVER_CLASS_NAME);
			conn = DriverManager.getConnection(MYSQL_DB, USERNAME, PASSWORD);
		} 
		catch (SQLException e) {e.printStackTrace();}
		catch(ClassNotFoundException e){e.printStackTrace();}
		return conn;
	}

}
