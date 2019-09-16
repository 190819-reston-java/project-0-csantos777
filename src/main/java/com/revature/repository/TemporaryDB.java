package com.revature.repository;

import java.util.*;
import com.revature.model.UserAcc;

public class TemporaryDB implements DatabaseUserBA {
	private static List<UserAcc> database;
	
	static {
		database = new ArrayList<UserAcc>();
		addUserAcc("Tammy","EEF",100000.00);
		addUserAcc("Evan","AAAAA",70000.00);
	}
	
	public static void addUserAcc(String name, String password, double amount) {
	//	database.add(new UserAcc(name,password,amount));
	}
	
	public static void addUserAcc(UserAcc newUser) {
		database.add(newUser);
	}
	
	public static UserAcc getUserBA(String name) throws NullPointerException {
		for (int a = 0; a < database.size(); ++a) {
			if (database.get(a).getUsername().equals(name)) {
				return database.get(a);
			}
		}
		return null;
	}
	
	public static int getUserBAIndex(String name) throws NullPointerException {
		for (int a = 0; a < database.size(); ++a) {
			if (database.get(a).getUsername().equals(name)) {
				return a;
			}
		}
		return 0;
	}
	
	public static void updateUserBA(UserAcc user) {
		database.get(getUserBAIndex(user.getUsername())).setName(user.getUsername());
		//database.get(getUserBAIndex(user.getUsername())).setPassword(user.getPassword());
	}
	
	public static String getBankAccountsToDisplay() {
		String stmt = "Database [ ";
		for (UserAcc e : database) {
			stmt += "User: Name=" + e.getUsername() + " Amount=$" + e.getBalance() + " ";
		}
		return stmt + "]";
	}
}
