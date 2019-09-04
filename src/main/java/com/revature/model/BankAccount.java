package com.revature.model;

public class BankAccount {
	
	private double amount;
	private String name;
	
	public BankAccount() {
		amount = 0.0;
		name = "User";
	}
	
	public BankAccount(String name, double amount) {
		this.amount = amount;
		this.name = name;
	}
	
	
	
}
