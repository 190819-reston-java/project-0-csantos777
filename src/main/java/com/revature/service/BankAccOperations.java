package com.revature.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.exception.*;
import com.revature.model.*;
import com.revature.repository.TemporaryDB;
import com.revature.repository.ActualDB;
import com.revature.service.*;

public class BankAccOperations {

	/*public static int verification(String username, String password) {
		
		//UserAcc temp = TemporaryDB.getUserBA(name);
		
		UserAcc tempo = ActualDB.getUserAcc(username);
		
		if (tempo == null)
			throw new NonexistentUserException();
		else if (!tempo.getPassword().equals(password) || password == null)
			throw new WrongLoginException();
		else if (!tempo.getName().equals(username) || username == null)
			throw new WrongLoginException();
		else
			return 0;
	}*/
	
	/*public static double depositMoney(double balance) {
		return currBalance += balance;
	}

	public static double withdrawMoney(double balance) {
		if (balance > currBalance)
			throw new NegativeBalanceException();
		else
			return currBalance -= balance;
		
	}*/
	
	/*public static void depositMoney(double balance, UserAcc user) {
		user.setBalance(balance += user.getBalance());
	}*/
	
	public static double getBalance(String username) {
		final String sql = "SELECT balance FROM accounts WHERE username_id IN " + 
				"(SELECT username FROM users WHERE username = ?);";
		
		double bal = 0.0;
		
		try (Connection conn = ConnectorUtil.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, username);
				if (stmt.execute()) {
					try (ResultSet rs = stmt.executeQuery()) {
						while(rs.next())
							bal = rs.getDouble("balance");
					}
					
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// get a single user account here.
		return bal;
	}
	
	public static BankAcc getBankAcc(UserAcc user) {
		final String sql = "SELECT * FROM accounts WHERE username_id IN " + 
				"(SELECT username FROM users WHERE username = ?);";
		
		BankAcc ba = null;
		
		try (Connection conn = ConnectorUtil.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, user.getUsername());
				if (stmt.execute()) {
					try (ResultSet rs = stmt.executeQuery()) {
						while (rs.next()) {
							//ba.setName(rs.getString("account_name"));
							//ba.setAccNumber(rs.getInt("account_number"));
							//ba.setBalance(rs.getDouble("balance"));
							ba = createBankAccInstance(rs);
						}
					}
					
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// get a single user account here.
		return ba;
	}
	
	/*public static List<BankAcc> getBankAccs(UserAcc user) {
		final String sql = "SELECT * FROM accounts WHERE username_id IN " + 
				"(SELECT username FROM users WHERE username = ?);";
		
		ArrayList<BankAcc> ba = null;
		
		try (Connection conn = ConnectorUtil.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, user.getUsername());
				if (stmt.execute()) {
					try (ResultSet rs = stmt.executeQuery()) {
						while (rs.next()) {
							//ba.setName(rs.getString("account_name"));
							//ba.setAccNumber(rs.getInt("account_number"));
							//ba.setBalance(rs.getDouble("balance"));
							ba = createBankAccInstance(rs);
						}
					}
					
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// get a single user account here.
		return ba;
	}*/
	
	// creates BankAcc object, which will then be used in conjunction with the UserAcc
	private static BankAcc createBankAccInstance(ResultSet rs) throws SQLException {
		return new BankAcc(rs.getString("account_name"),
							rs.getInt("account_number"),
							rs.getDouble("balance"));
	}
	
	/*public static void createBankAcc(String userName, double amount) {
		
		try {
			Connection conn = ConnectorUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO accounts(username_id,");
		}
	}*/
	
	public static void deleteBankAcc(String username) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectorUtil.getConnection() ;
			stmt = conn.prepareStatement(ActualDB.deleteQuery(1));
			stmt.setString(1, username);
			stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
	}
	
	public static void depositMoney(double deposit, UserAcc user) {

		PreparedStatement stmt = null;
		Connection conn = null;
		
		try {
			conn = ConnectorUtil.getConnection();
			stmt = conn.prepareStatement(
					"UPDATE accounts SET balance = balance + ? " + 
					"WHERE username_id IN " + 
					"(SELECT username FROM users WHERE username = ?);");
			//stmt.setDouble(1, (user.getBalance()+balance));
			stmt.setDouble(1, deposit);
			stmt.setString(2, user.getUsername());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
	}
	
	
	public static void withdrawMoney(double withdraw, UserAcc user) {
		PreparedStatement stmt = null;
		Connection conn = null;
		
		if (withdraw > getBalance(user.getUsername()))
			throw new NegativeBalanceException();
		else
			try {
				conn = ConnectorUtil.getConnection();
				stmt = conn.prepareStatement(
						"UPDATE accounts SET balance = balance - ? " + 
						"WHERE username_id IN " + 
						"(SELECT username FROM users WHERE username = ?);");
				//stmt.setDouble(1, (-1*(balance-user.getBalance())));
				stmt.setDouble(1, withdraw);
				stmt.setString(2, user.getUsername());
				stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				StreamCloser.close(conn);
				StreamCloser.close(stmt);
			}
		
	}
}
