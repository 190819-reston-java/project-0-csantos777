package com.revature.model;

public class UserBA {
	
	private double amount;
	private String name;
	private String password;
	//private String bankID;
	
	public UserBA() {
		amount = 0.0;
		name = "User";
		password = "1234";
	}
	
	public UserBA(String name, String password, double amount) {
		this.amount = amount;
		this.password = password;
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
