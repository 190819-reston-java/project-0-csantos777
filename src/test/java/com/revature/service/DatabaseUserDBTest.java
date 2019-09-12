package com.revature.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.model.UserAcc;
import com.revature.repository.TemporaryDB;

public class DatabaseUserDBTest {
	TemporaryDB ins = new TemporaryDB();
	@Test
	public void addNewUserToBank() {
		UserAcc example = new UserAcc("Ronald","123",45000);
		TemporaryDB.addUserAcc(example);
	}
	
	@Test
	public void searchCurrentUser() {
		UserAcc example = new UserAcc("Tammy","EEF",100000.00);
		
		UserAcc found = ins.getUserBA("Tammy");
		
		assertEquals(example.getName(),found.getName());
		assertEquals(example.getBalance(),found.getBalance(), 0.01);
		// assert that the example object is the same as the object retrieved
		// using the getUserBA method.
	}

}
