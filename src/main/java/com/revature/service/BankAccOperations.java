package com.revature.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.exception.*;
import com.revature.model.UserAcc;
import com.revature.service.ConnectorUtil;
import com.revature.repository.TemporaryDB;
import com.revature.repository.ActualDB;

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
	
	public static void getBankAcc(UserAcc user) {
		final String sql = "SELECT * FROM accounts WHERE current_username IN " + 
				"(SELECT username FROM users WHERE username = ?);";
		try (Connection conn = ConnectorUtil.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, user.getUsername());
				if (stmt.execute()) {
					try (ResultSet rs = stmt.executeQuery()) {
						while (rs.next()) {
							user.setBankAccProperties(
									rs.getString("account_name"),
									rs.getInt("account_number"));
							user.setBalance(rs.getDouble("balance"));
						}
					}
					
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private final static String balanceQuery() {
		return "UPDATE accounts SET balance = ? " + 
				"WHERE current_username IN " + 
				"(SELECT username FROM users WHERE username = ?);";
	}
	
	public static void depositMoney(UserAcc user, double balance) {

		try (Connection conn = ConnectorUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(balanceQuery());
			stmt.setDouble(1, (user.getBalance()+balance));
			stmt.setString(2, user.getUsername());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void withdrawMoney(double balance, UserAcc user) {
		if (balance > user.getBalance())
			throw new NegativeBalanceException();
		else
			try (Connection conn = ConnectorUtil.getConnection()) {
				PreparedStatement stmt = conn.prepareStatement(balanceQuery());
				stmt.setDouble(1, (-1*(balance-user.getBalance())));
				stmt.setString(2, user.getUsername());
				stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
