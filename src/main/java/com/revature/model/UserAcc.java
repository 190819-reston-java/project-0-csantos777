package com.revature.model;
import java.security.SecureRandom; // to use on passwords...

public class UserAcc {
	
	private String name;
	private String password;
	private String address;
	private String city;
	private String zipcode;
	private String state;
	private String country;
	private BankAcc bankAcc;
	
	public UserAcc() {
		bankAcc = new BankAcc();
		name = "User";
		password = "1234";
		address = "";
		city = "";
		zipcode = "";
		state = "";
		country = "";
	}
	
	public UserAcc(String name, String password, double amount) {
		bankAcc = new BankAcc(amount);
		this.password = password;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = 
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setBalance(double balance) {
		bankAcc.setBalance(balance);
	}
	
	public double getBalance() {
		return bankAcc.getBalance();
	}

	@Override
	public String toString() {
		return "BankAcc [Name="+name+"]\n";
	}
	
}
