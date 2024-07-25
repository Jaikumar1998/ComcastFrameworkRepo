package com.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	
	Connection conn;
	
	public void getDBConnection(String url, String username, String password) {
		try {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		conn =DriverManager.getConnection(url, username, password);
		}catch (Exception e) {
		}
	}
	
	public void getDBConnection() {
		try {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		conn =DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects","root@%" ,"root" );
		}catch (Exception e) {
		}
	}
	
	public void closeConnection() throws SQLException {
		conn.close();
		
	}
	 public ResultSet executeSelectQuery(String query) throws SQLException {
		 ResultSet result = null;
		 try {
		 Statement stat =conn.createStatement();
		 result = stat.executeQuery(query);
		 }catch (Exception e) {
		}
		 return result;
	 }
	 
	 public int executeNonSelectQuery(String query) {
		 int result = 0;
		 try {
			 Statement stat =conn.createStatement();
			 result = stat.executeUpdate(query);
			 }catch (Exception e) {
			}
		 return result;
		 
	 }

}
