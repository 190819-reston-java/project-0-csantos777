package com.revature.repository;

import java.sql.*;
import java.util.Properties;
import com.revature.model.*;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	
	public static UserAcc getUserAcc(String username) {
		UserAcc user = null;
		try (Connection conn = getConnection()) {
			final String sql = "SELECT * FROM users WHERE username = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, username);
				if (stmt.execute()) {
					try (ResultSet rs = stmt.executeQuery()) {
						while (rs.next()) {
							user = makeUserAccInstance(rs);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static ArrayList<UserAcc> getBankAccountsToDisplay() {
		ArrayList<UserAcc> list = new ArrayList<UserAcc>();
		try (Connection conn = getConnection()) {
			final String sql = "SELECT * FROM users;";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				if (stmt.execute()) {
					try (ResultSet rs = stmt.executeQuery()) {
						while (rs.next()) {
							list.add(makeUserAccInstance(rs));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void createUserAcc(UserAcc u) {
		
		final String sql = "INSERT INTO users(username,first_name,last_name," + 
				"address,city,country,state,zipcode,user_password) VALUES(?,?,?,?,?,?,?,?,?);";
		PreparedStatement stmt = null;
		Connection conn = null;
		
		String[] name = u.getName().split(" ");
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, name[0]);
			stmt.setString(3, name[1]);
			stmt.setString(4, u.getAddress());
			stmt.setString(5, u.getCity());
			stmt.setString(6, u.getCountry());
			stmt.setString(7, u.getState());
			stmt.setString(8, u.getZipcode());
			stmt.setString(9, u.getPassword());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void updateUserAcc(UserAcc u) {
		
		final String sql = "UPDATE users SET address = ?, city = ?, " +
				"state = ?, zipcode = ? WHERE username = ?;";
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,u.getAddress());
			stmt.setString(2, u.getCity());
			stmt.setString(3, u.getState());
			stmt.setString(4, u.getZipcode());
			stmt.setString(5, u.getUsername());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void deleteUserAcc(String username) {
		final String[] sql = {"DELETE FROM users WHERE username = ?",
				"DELETE FROM accounts IN" + 
				"(SELECT username FROM users WHERE username = ?);",
					"DELETE FROM transaction_history IN " + 
					"(SELECT username FROM accounts IN " +
					"(SELECT username FROM users WHERE username = ?));"};
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql[0]);
			stmt.setString(1, username);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static UserAcc makeUserAccInstance(ResultSet rs) throws SQLException {
		return new UserAcc(rs.getString("username"), 
				(rs.getString("first_name") + " " + rs.getString("last_name")),
				rs.getString("user_password"), 
				rs.getString("address"),
				rs.getString("city"), 
				rs.getString("state"), 
				rs.getString("zipcode"),
				rs.getString("country"));
	}
	
	
	
}
