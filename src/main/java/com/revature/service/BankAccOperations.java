package com.revature.service;

import com.revature.exception.*;
import com.revature.model.UserAcc;
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
	
	public static void depositMoney(double balance, UserAcc user) {
		user.setBalance(balance += user.getBalance());
	}
	
	public static void depositMoney(UserAcc user) {
	//	final String sql 
	}
	
	public static void withdrawMoney(double balance, UserAcc user) {
		if (balance > user.getBalance())
			throw new NegativeBalanceException();
		else
			user.setBalance(-1*(balance -= user.getBalance()));
		
	}
}
