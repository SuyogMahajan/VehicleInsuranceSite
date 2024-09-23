package com.custom_exceptions;

public class InvalidUnderWriterLogInException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidUnderWriterLogInException() {
		super("Need Specific UnderWriter Access.");
	}

}
