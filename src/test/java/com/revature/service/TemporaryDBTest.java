package com.revature.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.model.UserAcc;
import com.revature.repository.TemporaryDB;

public class TemporaryDBTest {
	TemporaryDB ins = new TemporaryDB();
	@Test
	public void addNewUserToBank() {
		UserAcc example = new UserAcc("Ronald","123",45000);
		TemporaryDB.addUserBA(example);
	}
	
	@Test
	public void searchCurrentUser() {
		UserAcc example = new UserAcc("Tammy","EEEFFFF",100000.00);
		
		UserAcc found = ins.getUserBA("Tammy");
		
		assertEquals(example.getName(),found.getName());
		assertEquals(example.getAmount(),found.getAmount(), 0.01);
		// assert that the example object is the same as the object retrieved
		// using the getUserBA method.
	}

}
