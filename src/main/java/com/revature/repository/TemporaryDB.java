package com.revature.repository;

import java.util.*;
import com.revature.model.*;

public class TemporaryDB {
	private static List<UserBA> database;
	
	TemporaryDB() {
		database = new LinkedList<UserBA>();
		addUserBA("Tammy","EEEFFFF",100000.00);
		addUserBA("Evan","AAAAA",70000.00);
	}
	
	public void addUserBA(String name, String password, double amount) {
		database.add(new UserBA(name,password,amount));
	}
}
