package com.custom_exceptions;

public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidInputException() {
		super("Invalid Password , please check password policy.");
	}
	
}
