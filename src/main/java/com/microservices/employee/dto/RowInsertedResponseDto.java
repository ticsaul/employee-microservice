package com.microservices.employee.dto;

public class RowInsertedResponseDto {
	private Integer id;
	private boolean success;

	public RowInsertedResponseDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
