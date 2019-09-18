package com.revature.controller;

import java.util.InputMismatchException;
//import java.io.Console;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.repository.ActualDB;
import com.revature.repository.TemporaryDB;
import com.revature.service.BankAccOperations;
import com.revature.exception.NegativeBalanceException;
import com.revature.exception.NonexistentUserException;
import com.revature.exception.WrongLoginException;
import com.revature.model.UserAcc;


public class ATMController {
	
	private static Scanner inputs = new Scanner(System.in);
	public static Logger logger = Logger.getLogger(ATMController.class);
	// this variable does not need an object to be used.
	
	public static void start() {

		logger.info("ATM starts, and will loop in this fashion.");
		System.out.println("Welcome to the ATM.\n");
		
		userLogin();

	}
	
	public static UserAcc verifyUser(String username, String password) {
		
		int eval = ActualDB.verifyUser(username, password);
		System.out.println(eval);
		
		if (eval == 0)
			throw new NonexistentUserException();
		else 
			return ActualDB.logUserAccIn(username, password);
	}
	
	//public static void loginVerification()
	
	public static void userLogin() {
		
		UserAcc info = null;
		System.out.println("Please sign in to your account.\n" + 
			"----------------------------------------");
		while (info == null) {
			try {
				
				String[] inffo = prompt();

				try {
					info = verifyUser(inffo[0], inffo[1]);
					if (info != null) 
						menu(info);
				} catch (WrongLoginException e) {
					e.printStackTrace();
				} catch (NonexistentUserException e) {
					e.printStackTrace();
				}
			} catch (InputMismatchException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*public static void userRegister() {
		String[] inffo = prompt();
		
		System.out.println("Enter first name.");
		String firstName = inputs.next();
		
		System.out.println("Enter last name.");
		String lastName = inputs.next();
		
		UserAcc first = new UserAcc(inffo[0], (firstName + lastName), inffo[1]);
		
		ActualDB.createUserAcc(first);
		
		System.out.println("How much would you like to enter?");
		double answer = Double.parseDouble(inputs.next());
		
		
		
		
	}*/
	
	
	public static String[] prompt() {
		System.out.println("User name: ");
		String username = inputs.next();
		
		System.out.println("Password, unobscured until further notice...");
		String password = inputs.next();
		
		return new String[] {username, password};
		
	}
	
	public static void menu(UserAcc user) {
		char quit = ' ';
		while (quit != 'q') {
			System.out.println("What would you like to do?\n" +
					"\t0. Display current balance.\n" +
					"\t1. Make a deposit to account.\n" + 
					"\t2. Withdraw from account.\n" + 
					"\t3. Signout.\n" + 
					"Press 4. to quit.");
			try {
				int select = Integer.parseInt(inputs.next());
					switch (select) {
						case 0:
							System.out.println("Your current balance is: $" 
									+ BankAccOperations.getBalance(user.getUsername()));
							logger.trace("The user checked balance, calling the database.");
							break;
						case 1:
							System.out.println("How much to deposit? Current balance is: $" 
									+ BankAccOperations.getBalance(user.getUsername()));
							logger.debug("Here is where the user deposits money.");
							try {
								BankAccOperations.depositMoney(Double.parseDouble(inputs.next()), user);
								System.out.println("Your balance is now $" + /*user.getBalance()*/
										BankAccOperations.getBalance(user.getUsername()));
							} catch (NumberFormatException e) {
								e.printStackTrace();
								quit = 'n';
							}
							break;
						case 2:
							System.out.println("How much to withdraw? Current balance is: $" 
									+ BankAccOperations.getBalance(user.getUsername()));
							try {
								logger.debug("Here is where the database is called and the user withdraws money.");
								BankAccOperations.withdrawMoney(Double.parseDouble(inputs.next()), user);
								System.out.println("Your balance is now $" + 
										BankAccOperations.getBalance(user.getUsername()));
									//user.getBalance());
							} catch (NegativeBalanceException e) {
								logger.error("We have a user who attempted to withdraw more than they actually have.");
								e.printStackTrace();
								quit = 'n';
							} catch (NumberFormatException e) {
								logger.error("A user entered the wrong input.");
								e.printStackTrace();
								quit = 'n';
							}
							break;
						case 3:
							System.out.println("Goodbye.");
							start();
							break;
						case 4:
							System.out.println("Goodbye.");
							quit = 'q';
							System.exit(0);
							break;
						default:
							System.out.println("Not a valid option, try again.");
						select = 0;
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
		}
