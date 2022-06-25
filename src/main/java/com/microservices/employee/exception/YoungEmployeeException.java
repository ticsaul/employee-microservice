package com.microservices.employee.exception;

public class YoungEmployeeException extends Exception {
	private static final long serialVersionUID = 8382303440256937569L;

	public YoungEmployeeException(String message) {
		super(message);
	}
}
