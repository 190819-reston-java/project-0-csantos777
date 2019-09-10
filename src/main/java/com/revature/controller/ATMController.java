package com.revature.controller;

import java.util.InputMismatchException;
//import java.io.Console;
import java.util.Scanner;

import com.revature.repository.TemporaryDB;
import com.revature.service.ATMOperations;
import com.revature.exception.NegativeBalanceException;
import com.revature.exception.WrongLoginException;
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

		try {
			System.out.println("User name: ");
			username = inputs.nextLine();
			
			System.out.println("Password, unobscured until further notice...");
			password = inputs.nextLine();
			
			
			if (ATMOperations.verification(username, password)) {
				System.out.println("Welcome: " + username);
				menu(TemporaryDB.getUserBA(username));
			}
			else {
				System.out.println("User doesn't exist or you entered wrong password.");
			}
		} catch (WrongLoginException e) {
			e.printStackTrace();
		}

	}
	
	public static void menu(UserBA user) {
		int select = inputs.nextInt();
			System.out.println("What would you like to do?\n" +
					"\t1. Make a deposit to account.\n" + 
					"\t2. Withdraw from account.\n" + 
					"\t3. Signout.\n");
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
