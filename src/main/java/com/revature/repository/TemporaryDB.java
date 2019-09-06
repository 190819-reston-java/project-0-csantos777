package com.revature.repository;

import java.util.*;
import com.revature.model.*;

public class TemporaryDB {
	private static List<UserBA> database;
	
	TemporaryDB() {
		database = new ArrayList<UserBA>();
		addUserBA("Tammy","EEEFFFF",100000.00);
		addUserBA("Evan","AAAAA",70000.00);
	}
	
	public void addUserBA(String name, String password, double amount) {
		database.add(new UserBA(name,password,amount));
	}
	
	public UserBA getUserBA(String name) throws NullPointerException {
		for (UserBA a : database) {
			if (a.getName().equals(name))
				return a;
		}
		return null;
	}
	
	public void updateUserBA(UserBA user) {
		UserBA temp = getUserBA(user.getName());
		temp.setName(user.getName());
	}
}
