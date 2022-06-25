package com.microservices.employee.exception;

public class ItemNotFoundException extends Exception {
	private static final long serialVersionUID = 8382303440256937569L;

	public ItemNotFoundException(String message) {
		super(message);
	}
}
