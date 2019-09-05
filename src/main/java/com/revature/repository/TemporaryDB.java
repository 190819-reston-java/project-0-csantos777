package com.revature.repository;

import java.util.*;
import com.revature.model.*;

public class TemporaryDB {
	private static List<UserBA> database;
	
	TemporaryDB() {
		database = new LinkedList<UserBA>();
		database.add(new UserBA("Tammy","EEEFFFF",100000.00));
		database.add(new UserBA("Evan","AAAAA",70000.00));
	}
}
