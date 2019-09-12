package com.revature.repository;

import com.revature.model.UserAcc;

public interface DatabaseUserBA {
	
	static String getBankAccountsToDisplay() {return "";}
	
	static UserAcc getUserAcc(String name) {return null;}
	
	static void addUserAcc(UserAcc newUser) {}
	
	static void updateUserAcc(UserAcc user) {}
	
	static void deleteUserAcc(UserAcc user) {}
	
}
