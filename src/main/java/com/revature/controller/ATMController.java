package com.revature.controller;

import java.util.InputMismatchException;
//import java.io.Console;
import java.util.Scanner;

import com.revature.repository.TemporaryDB;
import com.revature.service.ATMOperations;
import com.revature.exception.NegativeBalanceException;
import com.revature.model.UserBA;


public class ATMController {
	
	static Scanner inputs = new Scanner(System.in);
	// this variable does not need an object to be used.
	
	public static void start() {
		
		String username = "";
		String password = "";

		System.out.println("Welcome to the ATM.\n" + 
				"Please sign in to your account.\n" + 
				"----------------------------------------");
		// possible area to implement a try-catch exception.
			System.out.println("User name: ");
			username = inputs.nextLine();
			
			System.out.println("Password, unobscured until further notice...");
			password = inputs.nextLine();
			
			System.out.println("Welcome: " + username);
			
			System.out.println(TemporaryDB.statusOFDB());
		
		// insert verification class function, which will verify if the user is in the database.
		/*
		 * if (verifyClass == true)
		 * 		call menu()
		 */
			
		if (ATMOperations.verification(username, password)) {
			menu(TemporaryDB.getUserBA(username));
		}
	}
	
	public static void menu(UserBA user) {
		int select = 0;
		System.out.println("What would you like to do?\n" +
				"\t1. Make a deposit to account.\n" + 
				"\t2. Withdraw from account.\n" + 
				"\t3. Signout.\n");
		select = inputs.nextInt();
		switch (select) {
			case 1:
				System.out.println("How much? Current balance is: $" + user.getAmount());
				
				try {
					ATMOperations.depositMoney(inputs.nextDouble(), user);
					System.out.println("Your balance is now $" + user.getAmount());
				} catch (InputMismatchException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("How much? Current balance is: $" + user.getAmount());
				
				try {
					ATMOperations.depositMoney(inputs.nextDouble(), user);
					System.out.println("Your balance is now $" + user.getAmount());
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
