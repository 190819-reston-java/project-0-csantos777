package com.revature.controller;

import java.util.InputMismatchException;
//import java.io.Console;
import java.util.Scanner;

import com.revature.repository.ActualDB;
import com.revature.repository.TemporaryDB;
import com.revature.service.BankAccOperations;
import com.revature.exception.NegativeBalanceException;
import com.revature.exception.WrongLoginException;
import com.revature.model.UserAcc;


public class ATMController {
	
	static Scanner inputs = new Scanner(System.in);
	// this variable does not need an object to be used.
	
	public static void start() {
		
		String username = "";
		String password = "";

		System.out.println("Welcome to the ATM.\n" + 
				"Please sign in to your account.\n" + 
				"----------------------------------------");

		try {
			System.out.println("User name: ");
			username = inputs.nextLine();
			
			System.out.println("Password, unobscured until further notice...");
			password = inputs.nextLine();
			
			
			//if (BankAccOperations.verification(username, password) == 0) {
				UserAcc curr = ActualDB.logUserAccIn(username, password);
				System.out.println("Welcome: " + curr.getName());
				menu(curr);
			//}
			//else {
				//System.out.println("User doesn't exist or you entered wrong password.");
			//}
		} catch (WrongLoginException e) {
			e.printStackTrace();
		}

	}
	
	public static void menu(UserAcc user) {
		System.out.println("What would you like to do?\n" +
				"\t1. Make a deposit to account.\n" + 
				"\t2. Withdraw from account.\n" + 
				"\t3. Signout.\n");
		int select = inputs.nextInt();
		while (select < 3) {
			switch (select) {
				case 1:
					System.out.println("How much to deposit? Current balance is: $" 
							+ BankAccOperations.getBalance(user.getUsername()));
						
					try {
						BankAccOperations.depositMoney(inputs.nextDouble(), user);
						System.out.println("Your balance is now $" + /*user.getBalance()*/
								BankAccOperations.getBalance(user.getUsername()));
					} catch (InputMismatchException e) {
						e.printStackTrace();
					}
					break;
				case 2:
					System.out.println("How much to withdraw? Current balance is: $" 
							+ BankAccOperations.getBalance(user.getUsername())/*user.getBalance()*/);
					
					try {
						BankAccOperations.withdrawMoney(inputs.nextDouble(), user);
						System.out.println("Your balance is now $" + 
								BankAccOperations.getBalance(user.getUsername()));
							//user.getBalance());
					} catch (NegativeBalanceException e) {
						e.printStackTrace();
					} catch (InputMismatchException e) {
						e.printStackTrace();
					}
					break;
				case 3:
					System.out.println("Goodbye.");
					System.exit(0);
					break;
				default:
					System.out.println("Not a valid option, try again.");
			}
		}
	}
}
