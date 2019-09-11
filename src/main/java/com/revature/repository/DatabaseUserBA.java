package com.revature.repository;

import com.revature.model.UserBA;

public interface DatabaseUserBA {

	void updateUserBA(UserBA user);
	
	String statusOFDB();
	
	UserBA getUserBA(String name);
	
}
