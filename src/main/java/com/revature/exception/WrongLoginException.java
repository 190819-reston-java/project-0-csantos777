package com.revature.exception;

public class WrongLoginException extends RuntimeException {

	public WrongLoginException(String type) {
		super("Wrong " + type + ".");
	}
}
