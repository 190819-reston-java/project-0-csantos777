package com.revature.controller;

import java.util.InputMismatchException;
//import java.io.Console;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.repository.ActualDB;
import com.revature.repository.TemporaryDB;
import com.revature.service.BankAccOperations;
import com.revature.exception.NegativeBalanceException;
import com.revature.exception.WrongLoginException;
import com.revature.model.UserAcc;


public class ATMController {
	
	private static Scanner inputs = new Scanner(System.in);
	public static Logger logger = Logger.getLogger(ATMController.class);
	// this variable does not need an object to be used.
	
	public static void start() {

		logger.info("ATM starts, and will loop in this fashion.");
		System.out.println("Welcome to the ATM.\n");
		
		try {
			UserAcc curr = ActualDB.logUserAccIn(userInfo());
			System.out.println("Welcome: " + curr.getName());
			menu(curr);
		} catch (WrongLoginException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
			//else {
				//System.out.println("User doesn't exist or you entered wrong password.");
			//}

	}
	
	public static UserAcc userInfo() {
		
		UserAcc info = new UserAcc();
		
		System.out.println("Please sign in to your account.\n" + 
			"----------------------------------------");
		try {
			System.out.println("User name: ");
			info.setUsername(inputs.next());
			
			System.out.println("Password, unobscured until further notice...");
			info.setPassword(inputs.next());
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		return info;
	}
	
	public static void menu(UserAcc user) {
		char quit = ' ';
		while (quit != 'q') {
			System.out.println("What would you like to do?\n" +
					"\t0. Display current balance." +
					"\t1. Make a deposit to account.\n" + 
					"\t2. Withdraw from account.\n" + 
					"\t3. Signout.\n" + 
					"Press 4. to quit.");
			int select = inputs.nextInt();
				switch (select) {
					case 0:
						System.out.println("Your current balance is: $" 
								+ BankAccOperations.getBalance(user.getUsername()));
						break;
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
						quit = 'q';
						break;
					default:
						System.out.println("Not a valid option, try again.");
					select = 0;
				}
			}
		start();
		}
}
