package com.revature.controller;

//import java.io.Console;
import java.util.Scanner;


public class ATMController {
	
	static Scanner inputs = new Scanner(System.in);
	// this variable does not need an object to be used.
	
	public static void start() {
		System.out.println("Welcome to the ATM.\n" + 
				"Please sign in to your account.\n" + 
				"----------------------------------------");
		
		// possible area to implement a try-catch exception.
		
		System.out.println("User name: ");
		String username = inputs.nextLine();
		
		System.out.println("Password, unobscured until further notice...");
		String password = inputs.nextLine();
		
		System.out.println("Welcome: " + username);
		
		// insert verification class function, which will verify if the user is in the database.
		/*
		 * if (verifyClass == true)
		 * 		call menu()
		 */
		
	}
	
	public static void menu() {
		int select = 0;
		System.out.println("What would you like to do?\n" +
				"\t1. Make a deposit to account.\n" + 
				"\t2. Withdraw from account.\n" + 
				"\t3. Signout.\n");
		select = inputs.nextInt();
		switch (select) {
			case 1:
				System.out.println("How much?");
				// call function that takes care of money input,
				// that very function will be the one to call the functions from ATMOperations
				break;
			case 2:
				// same as above
				break;
			case 3:
				// call function that will end session with the user.
				// this will end the function here and there.
				break;
			default: 
				System.out.println("Not a valid option, try again.");
		}
		
		
	}
}
