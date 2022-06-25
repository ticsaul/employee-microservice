package com.microservices.employee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TotalWorkedHoursResponseDto {
	@JsonProperty("total_worked_hours")
	private Integer totalWorkedHours;

	private boolean success;

	public TotalWorkedHoursResponseDto() {
		super();
	}

	public Integer getTotalWorkedHours() {
		return totalWorkedHours;
	}

	public void setTotalWorkedHours(Integer totalWorkedHours) {
		this.totalWorkedHours = totalWorkedHours;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
