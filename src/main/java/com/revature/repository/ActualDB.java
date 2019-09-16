package com.revature.repository;

import java.sql.*;
import java.util.Properties;
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
	
	private static String encryptPassword(String password) {
		return password;
	}
	
	public static ArrayList<UserAcc> getBankAccountsToDisplay() {
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
		
		final String sql = "INSERT INTO users(username,first_name,last_name," + 
				"address,city,country,state,zipcode,user_password) VALUES(?,?,?,?,?,?,?,?,?);";
		PreparedStatement stmt = null;
		Connection conn = null;
		
		String[] name = u.getName().split(" ");
		
		try {
			conn = ConnectorUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, name[0]);
			stmt.setString(3, name[1]);
			stmt.setString(4, u.getAddress());
			stmt.setString(5, u.getCity());
			stmt.setString(6, u.getCountry());
			stmt.setString(7, u.getState());
			stmt.setString(8, u.getZipcode());
			stmt.setString(9, encryptPassword(u.getPassword()));
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
	}
	
	public static void updateUserAcc(UserAcc u) {
		
		final String sql = "UPDATE users SET address = ?, city = ?, " +
				"state = ?, zipcode = ? WHERE username = ?;";
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
				rs.getString("user_password"), 
				rs.getString("address"),
				rs.getString("city"), 
				rs.getString("state"), 
				rs.getString("zipcode"),
				rs.getString("country"));
	}
	
	
	
}
