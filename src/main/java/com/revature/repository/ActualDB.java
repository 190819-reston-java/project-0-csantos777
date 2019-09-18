package com.revature.repository;

import java.sql.*;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.revature.controller.ATMController;
import com.revature.model.*;
import java.io.IOException;
import com.revature.service.StreamCloser;
import  com.revature.service.ConnectorUtil;

import java.util.ArrayList;
import java.security.SecureRandom;
/*
 * This is only a draft.
 */
public class ActualDB implements DatabaseUserBA {
	
	public static Logger logger = Logger.getLogger(ATMController.class);
	
	public static UserAcc logUserAccIn(String username, String password) {
		UserAcc user = null;
		try (Connection conn = ConnectorUtil.getConnection()) {
			final String sql = "SELECT * FROM users WHERE username = ? AND user_password = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, username);
				stmt.setString(2, encryptPassword(password));
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
	
	public static int verifyUser(String username, String password) {
		int res = 0;
		try (Connection conn = ConnectorUtil.getConnection()) {
			final String sql = "SELECT * FROM users WHERE username = ? AND user_password = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, username);
				stmt.setString(2, encryptPassword(password));
				if (stmt.execute()) {
					try (ResultSet rs = stmt.executeQuery()) {
						while (rs.next()) {
							if (username.equals(rs.getString("username"))) {
								if (rs.getString("user_password").equals(password)) {
									res = 1;
								}
								else {
									res = -1; 
								}
							}
							else
								res = 0;
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private static String encryptPassword(String password) {
		return password;
	}
	
	public static ArrayList<UserAcc> getUserAccounts() {
		ArrayList<UserAcc> list = new ArrayList<UserAcc>();
		try (Connection conn = ConnectorUtil.getConnection()) {
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
		
		final String sql = "INSERT INTO users(username,first_name,last_name,user_password) " 
							+ "VALUES(?,?,?,?);";
		PreparedStatement stmt = null;
		Connection conn = null;
		
		String[] name = u.getName().split(" ");
		
		try {
			conn = ConnectorUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, name[0]);
			stmt.setString(3, name[1]);
			stmt.setString(4, encryptPassword(u.getPassword()));
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
	}
	
	public static void addUserAddress(UserAcc u) {
		
		final String sql = "INSERT INTO user_address(username_id,address,city,country,state,zipcode) " 
							+ "VALUES(?,?,?,?,?,?);";
		PreparedStatement stmt = null;
		Connection conn = null;
		
		try {
			conn = ConnectorUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getAddress());
			stmt.setString(3, u.getCity());
			stmt.setString(4, u.getCountry());
			stmt.setString(5, u.getState());
			stmt.setString(6, u.getZipcode());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
	}
	
	public static void updateUserAddress(UserAcc u) {
		
		final String sql = "UPDATE user_address SET address = ?, city = ?, " +
				"state = ?, zipcode = ? WHERE username_id = ?;";
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = ConnectorUtil.getConnection();
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
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
	}
	
	public static void updateUserAcc(UserAcc u) {
		
		final String sql = "UPDATE users SET first_name = ?, last_name = ?, " +
				"user_password = ? WHERE username = ?;";
		PreparedStatement stmt = null;
		Connection conn = null;
		String[] name = u.getName().split(" ");
		
		try {
			conn = ConnectorUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name[0]);
			stmt.setString(2, name[1]);
			stmt.setString(3, encryptPassword(u.getPassword()));
			stmt.setString(4, u.getUsername());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
	}
	
	public final static String deleteQuery(int opt) {
		final String[] sql = {"DELETE FROM users WHERE username = ?",
				"DELETE FROM accounts IN" + 
				"(SELECT username FROM users WHERE username = ?);",
					"DELETE FROM transaction_history IN " + 
					"(SELECT username FROM accounts IN " +
					"(SELECT username FROM users WHERE username = ?));"};
		if (opt == 0)
			return sql[opt];
		else if (opt == 1)
			return sql[opt];
		else
			return sql[opt];
	}
	
	public static void deleteUserAcc(String username) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectorUtil.getConnection() ;
			stmt = conn.prepareStatement(deleteQuery(0));
			stmt.setString(1, username);
			stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
	}
	
	private static UserAcc makeUserAccInstance(ResultSet rs) throws SQLException {
		return new UserAcc(rs.getString("username"), 
				(rs.getString("first_name") + " " + rs.getString("last_name")),
				rs.getString("user_password")/*, 
				rs.getString("address"),
				rs.getString("city"), 
				rs.getString("state"), 
				rs.getString("zipcode"),
				rs.getString("country")*/);
	}
	
	
	
}
