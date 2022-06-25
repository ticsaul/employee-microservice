package com.microservices.employee.exception;

public class OverflowWorkHoursException extends Exception {
	private static final long serialVersionUID = 8382303440256937569L;

	public OverflowWorkHoursException(String message) {
		super(message);
	}
}
