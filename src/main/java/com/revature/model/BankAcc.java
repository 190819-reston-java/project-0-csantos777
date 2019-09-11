package com.revature.model;

public class BankAcc {
	
	private double balance;
	private int accNumber;
	
	BankAcc() {
		balance = 0.0;
		accNumber = 0;
	}
	
	BankAcc(double balance, int accNumber) {
		this.balance = balance;
		this.accNumber = accNumber;
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

	@Override
	public String toString() {
		return "BankAcc [balance=" + balance + ", accNumber=" + accNumber + "]";
	}
	
	
}
