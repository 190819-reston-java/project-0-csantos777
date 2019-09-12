package com.revature;

import java.util.ArrayList;

//import com.revature.controller.ATMController;
import com.revature.*;
import com.revature.model.UserAcc;
import com.revature.repository.ActualDB;

/** 
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main {

	public static void main(String[] args) {
		//ATMController.start();
		
		UserAcc example = ActualDB.getUserAcc("reg241");
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
		System.out.println(list);
	}
}
