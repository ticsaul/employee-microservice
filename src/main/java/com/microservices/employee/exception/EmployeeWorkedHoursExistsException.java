package com.microservices.employee.exception;

public class EmployeeWorkedHoursExistsException extends Exception {
	private static final long serialVersionUID = 8382303440256937569L;

	public EmployeeWorkedHoursExistsException(String message) {
		super(message);
	}
}
