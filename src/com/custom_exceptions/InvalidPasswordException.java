package com.custom_exceptions;

public class InvalidPasswordException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidPasswordException() {
		super("Please check password policy.");
	}

}
