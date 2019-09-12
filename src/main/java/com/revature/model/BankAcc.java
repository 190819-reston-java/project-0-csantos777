package com.revature.model;

public class BankAcc {
	
	private double balance;
	private int accNumber;
	private String name;
	
	protected BankAcc() {
		balance = 0.0;
		accNumber = 0;
		name = "";
	}
	
	protected BankAcc(double balance) {
		this.balance = balance;
	}

	protected double getBalance() {
		return balance;
	}

	protected void setBalance(double balance) {
		this.balance = balance;
	}

	protected int getAccNumber() {
		return accNumber;
	}

	protected void setAccNumber(int accNumber) {
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
