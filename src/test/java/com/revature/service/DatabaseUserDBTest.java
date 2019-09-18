package com.revature.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.model.UserAcc;
import com.revature.repository.TemporaryDB;

public class DatabaseUserDBTest {
	
	TemporaryDB ins = new TemporaryDB();
	@Test
	public void addNewUserToBank() {
		UserAcc example = new UserAcc("Ronald","dfef wtwet","123");
		TemporaryDB.addUserAcc(example);
	}
	
	@Test
	public void searchCurrentUser() {
		UserAcc example = new UserAcc("Tammy","werwe erterte","EEE");
		
		example.setBalance(100000.00);
		UserAcc found = ins.getUserBA("Tammy");
		
		assertEquals(example.getName(),found.getName());
		assertEquals(example.getBalance(),found.getBalance(), 0.01);
		// assert that the example object is the same as the object retrieved
		// using the getUserBA method.
		
		
	}

	// test methods to implement sometime soon.
	/*UserAcc example = ActualDB.getUserAcc("reg241");
	ArrayList <UserAcc> list = ActualDB.getBankAccountsToDisplay();
	
	System.out.println(list);
	
	example.setAddress("235 AAAA RD.");
	example.setCity("EEE");
	example.setState("Delaware");
	
	ActualDB.updateUserAcc(example);
	list = ActualDB.getBankAccountsToDisplay();
	
	UserAcc nee = new UserAcc("eab64", "Jack Delvin", "23ff", "234 VVVV VV.",
		"AAAA", "Virgin-ia", "22222", "USA");
	
	ActualDB.createUserAcc(nee);
	list = ActualDB.getBankAccountsToDisplay();
	System.out.println(list);*/

}
