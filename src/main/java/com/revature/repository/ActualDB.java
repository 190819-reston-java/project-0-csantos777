package com.revature.repository;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import com.revature.model.*;

import java.util.HashMap;
import java.util.Map;
/*
 * This is only a draft.
 */
public class ActualDB {
private static Connection conn = null;
	
	static Connection getConnection() {
		try {
			//We'll write some boilerplate to work with Properties
			Properties props = new Properties();
			//The following lines just ensure we find connection.properties
			//regardless of how our project is built:
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			props.load(loader.getResourceAsStream("connection.properties"));
			
			//All we've done is set these values to the values found in
			// connection.properties
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			
			//How to actually make connections with jdbc
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("===CONNECTED===");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public HashMap<Integer, UserBA> getBankAccounts() {
		HashMap<Integer, UserBA> map = new HashMap<Integer, UserBA>();
		
		return map;
	}
	
	
	
}
