package com.revature.exception;

public class WrongLoginException extends RuntimeException {

	public WrongLoginException() {
		super("User does not exist.");
	}
}
