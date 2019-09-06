package com.revature.repository;

import java.util.*;
import com.revature.model.*;

public class TemporaryDB {
	private static List<UserBA> database;
	private static int index;
	
	public TemporaryDB() {
		index = 0;
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
		for (UserBA a : database) {
			if (a.getName().equals(name))
				return a;
		}
		return null;
	}
	
	public static void updateUserBA(UserBA user) {
		UserBA temp = getUserBA(user.getName());
		temp.setName(user.getName());
	}
	
	public static String statusOFDB() {
		String stmt = "Database [ ";
		for (UserBA e : database) {
			stmt += "User: Name=" + e.getName() + " Amount=$" + e.getAmount() + " ";
		}
		return stmt + "]";
	}
}
