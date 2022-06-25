package com.microservices.employee.exception;

public class WorkDateInvalidException extends Exception {
	private static final long serialVersionUID = 8382303440256937569L;

	public WorkDateInvalidException(String message) {
		super(message);
	}
}
