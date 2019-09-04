package com.revature.exception;

public class NegativeBalanceException extends RuntimeException {

	public NegativeBalanceException() {
		super("No negative balance is permitted.");
	}
}
