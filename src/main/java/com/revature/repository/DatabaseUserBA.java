package com.revature.repository;

import java.util.ArrayList;

import com.revature.model.UserAcc;

public interface DatabaseUserBA {
	
	static ArrayList<UserAcc> getBankAccountsToDisplay() {return null;}
	
	static UserAcc getUserAcc(String name) {return null;}
	
	static void addUserAcc(UserAcc newUser) {}
	
	static void updateUserAcc(UserAcc user) {}
	
	static void deleteUserAcc(String username) {}
	
}
