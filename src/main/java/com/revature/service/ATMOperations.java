package com.revature.service;

public class ATMOperations {
	
	static double currBalance;
	
	public static double depositMoney(double balance) {
		return currBalance += balance;
	}
	
	public static double withdrawMoney(double balance) {
		return currBalance -= balance;
	}
}
