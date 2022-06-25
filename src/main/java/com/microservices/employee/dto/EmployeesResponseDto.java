package com.microservices.employee.dto;

import java.util.List;

import com.microservices.employee.entity.EmployeeEntity;

public class EmployeesResponseDto {
	private List<EmployeeEntity> employees;
	private boolean success;

	public EmployeesResponseDto() {
		super();
	}

	public List<EmployeeEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeEntity> employees) {
		this.employees = employees;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
