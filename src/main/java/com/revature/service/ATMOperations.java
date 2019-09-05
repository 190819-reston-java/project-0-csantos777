package com.revature.service;

public class ATMOperations {
	
	static double currBalance;
	
	public static void verification() {
		/* 
		 * 1. goto repository
		 * 2. use BufferReader to read names from .txt file for time being
		 * 2a. or use PostgreSQL database
		 * 3. Find using binarySearch
		 * 4. return User object if found.
		 * 4a. if not, return to controller.
		 * 
		 */
	}
	
	public static double depositMoney(double balance) {
		return currBalance += balance;
	}
	
	public static double withdrawMoney(double balance) {
		return currBalance -= balance;
	}
}
