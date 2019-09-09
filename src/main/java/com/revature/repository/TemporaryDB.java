package com.revature.repository;

import java.util.*;
import com.revature.model.UserBA;

public class TemporaryDB {
	private static List<UserBA> database;
	
	static {
		database = new ArrayList<UserBA>();
		addUserBA("Tammy","EEEFFFF",100000.00);
		addUserBA("Evan","AAAAA",70000.00);
	}
	
	public static void addUserBA(String name, String password, double amount) {
		database.add(new UserBA(name,password,amount));
	}
	
	public static void addUserBA(UserBA newUser) {
		database.add(newUser);
	}
	
	public static UserBA getUserBA(String name) throws NullPointerException {
		for (int a = 0; a < database.size(); ++a) {
			if (database.get(a).getName().equals(name)) {
				return database.get(a);
			}
		}
		return null;
	}
	
	public static int getUserBAIndex(String name) throws NullPointerException {
		for (int a = 0; a < database.size(); ++a) {
			if (database.get(a).getName().equals(name)) {
				return a;
			}
		}
		return 0;
	}
	
	public static void updateUserBA(UserBA user) {
		database.get(getUserBAIndex(user.getName())).setName(user.getName());
		//database.get(getUserBAIndex(user.getName())).setPassword(user.getPassword());
	}
	
	public static String statusOFDB() {
		String stmt = "Database [ ";
		for (UserBA e : database) {
			stmt += "User: Name=" + e.getName() + " Amount=$" + e.getAmount() + " ";
		}
		return stmt + "]";
	}
}
