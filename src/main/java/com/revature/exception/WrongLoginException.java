package com.revature.exception;

public class WrongLoginException extends RuntimeException {

	public WrongLoginException() {
		super("Wrong password or username.");
	}
}
