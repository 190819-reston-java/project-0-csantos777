package com.revature.repository;

import com.revature.model.UserAcc;

public interface DatabaseUserBA {
	
	static String getBankAccountsToDisplay() {return "";}
	
	static UserAcc getUserBA(String name) {return null;}
	
	static void addUserBA(UserAcc newUser) {}
	
	static void updateUserBA(UserAcc user) {}
	
}
