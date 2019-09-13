package com.revature.exception;

public class NonexistentUserException extends RuntimeException {

	public NonexistentUserException() {
		super("User does not exist.");
	}
}
