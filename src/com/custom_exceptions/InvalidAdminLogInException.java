package com.custom_exceptions;

public class InvalidAdminLogInException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidAdminLogInException() {
		super("Need Admin Access !");
	}
}
