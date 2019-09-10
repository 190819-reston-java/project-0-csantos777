package com.revature.service;

import com.revature.exception.NegativeBalanceException;
import com.revature.model.UserBA;
import com.revature.repository.TemporaryDB;

public class ATMOperations {
	
	static double currBalance;
	
	public static boolean verification(String name, String password) {
		
		UserBA temp = TemporaryDB.getUserBA(name);
		
		//System.out.println(temp);
		
		return (temp.getName().equals(name) && temp.getPassword().equals(password)) ? true : false;
		
		//return (name.equals(getUserBA(name).getName())));
		
		/* 
		 * 1. goto repository
		 * 2. use BufferReader to read names from .txt file for time being
		 * 2a. or use PostgreSQL database
		 * 3. Find using binarySearch
		 * 4. return User object if found.
		 * 4a. if not, return to controller.
		 * 
		 */
		//return false;
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
	
	public static void depositMoney(double balance, UserBA user) {
		user.setAmount(balance += user.getAmount());
	}
	
	public static void withdrawMoney(double balance, UserBA user) {
		if (balance > user.getAmount())
			throw new NegativeBalanceException();
		else
			user.setAmount(-1*(balance -= user.getAmount()));
		
	}
}
