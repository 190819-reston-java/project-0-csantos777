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
public class ActualDB implements DatabaseUserBA {
	
	static Connection getConnection() {
		Connection conn = null;
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
	
	public static UserAcc getUserBA(String firstName, String lastName) {
		UserAcc user = null;
		try (Connection conn = getConnection()) {
			final String sql = "SELECT * FROM users WHERE first_name = ? AND last_name = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, firstName);
				stmt.setString(2, lastName);
				if (stmt.execute()) {
					try (ResultSet rs = stmt.executeQuery()) {
						rs.getString("first_name");
					}
				}
			}
			
		} catch (SQLException e) {
			
		}
		
		return user;
	}
	
	private static UserAcc makeUserAcc(ResultSet rs) throws SQLException {
		return new UserAcc();
	}
	
	/*public static void getBankAccountsToDisplay() {
		HashMap<Integer, UserBA> map = new HashMap<Integer, UserBA>();
		
		try {
			conn = getConnection();
			
			String sql = "SELECT * FROM BankATM.users";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}*/
	
	
	
}
