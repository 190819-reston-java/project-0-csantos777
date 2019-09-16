package com.revature.model;

public class BankAcc {
	
	private double balance;
	private int accNumber;
	private String name;
	
	public BankAcc() {
		balance = 0.0;
		accNumber = 0;
		name = "";
	}
	
	public BankAcc(String name, int accNumber, double balance) {
		this.balance = balance;
		this.accNumber = accNumber;
		this.name = name;
	}
	
	public BankAcc(double balance) {
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(int accNumber) {
		this.accNumber = accNumber;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BankAcc [balance=" + balance + ", accNumber=" + accNumber + ", name=" + name + "]";
	}
	
	
}
