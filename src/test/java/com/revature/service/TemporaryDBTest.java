package com.revature.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.model.UserBA;
import com.revature.repository.TemporaryDB;

public class TemporaryDBTest {
	TemporaryDB ins = new TemporaryDB();
	@Test
	public void addNewUserToBank() {
		UserBA example = new UserBA("Ronald","123",45000);
		TemporaryDB.addUserBA(example);
	}
	
	@Test
	public void searchCurrentUser() {
		UserBA example = new UserBA("Tammy","EEEFFFF",100000.00);
		// assert that the example object is the same as the object retrieved
		// using the getUserBA method.
	}

}
