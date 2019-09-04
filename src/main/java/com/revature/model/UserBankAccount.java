package com.revature.model;

public class UserBankAccount {
	
	private double amount;
	private String name;
	//private String bankID;
	
	public UserBankAccount() {
		amount = 0.0;
		name = "User";
	}
	
	public UserBankAccount(String name, double amount) {
		this.amount = amount;
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "BankAcc [Name="+name+" Amount=$"+amount+"]\n";
	}
	
}
