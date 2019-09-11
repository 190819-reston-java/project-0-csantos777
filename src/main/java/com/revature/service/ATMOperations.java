package com.revature.service;

import com.revature.exception.*;
import com.revature.model.UserAcc;
import com.revature.repository.TemporaryDB;

public class ATMOperations {
	
	static double currBalance;
	
	public static boolean verification(String name, String password) {
		
		UserAcc temp = TemporaryDB.getUserBA(name);
		
		if (temp == null)
			throw new WrongLoginException();
		else
			return (temp.getName().equals(name) && temp.getPassword().equals(password)) ? true : false;
	}
	
	//test method
	public static double getCurrBalance() {
		return currBalance;
	}

	public static void setCurrBalance(double currBalance) {
		ATMOperations.currBalance = currBalance;
	}
	
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
		user.setAmount(balance += user.getAmount());
	}
	
	public static void withdrawMoney(double balance, UserAcc user) {
		if (balance > user.getAmount())
			throw new NegativeBalanceException();
		else
			user.setAmount(-1*(balance -= user.getAmount()));
		
	}
}
