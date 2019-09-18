package com.revature.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.model.UserAcc;
import com.revature.repository.TemporaryDB;

public class DatabaseUserDBTest {
	
	// another mock test
	@Test
	public void addNewUserToBank() {
		UserAcc example = new UserAcc("Ronald","dfef wtwet","123");
		TemporaryDB.addUserAcc(example);
	}
	
	@Test
	public void searchCurrentUser() {
		UserAcc example = new UserAcc("tam55","EEF","asdf wert");
		
		example.setBalance(100000.00);
		UserAcc found = TemporaryDB.getUserBA("tam55");
		
		assertEquals(example.getName(),found.getName());
		assertEquals(example.getBalance(),found.getBalance(), 0.01);
		// assert that the example object is the same as the object retrieved
		// using the getUserBA method.
		
		
	}

	// test methods to implement sometime soon.
}
