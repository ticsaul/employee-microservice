package com.microservices.employee.exception;

public class StartDateInvalidException extends Exception {
	private static final long serialVersionUID = 8382303440256937569L;

	public StartDateInvalidException(String message) {
		super(message);
	}
}
