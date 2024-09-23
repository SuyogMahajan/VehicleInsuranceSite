package com.custom_exceptions;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super("Unable to find User !!");
	}

}
