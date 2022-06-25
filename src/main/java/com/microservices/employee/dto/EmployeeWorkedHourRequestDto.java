package com.microservices.employee.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservices.employee.utils.Constants;

public class EmployeeWorkedHourRequestDto {
	@NotNull
	@JsonProperty("employee_id")
	private Integer employeeId;

	@NotNull
	@JsonProperty("worked_hours")
	private Integer workedHours;

	@NotNull
	@JsonProperty("worked_date")
	@JsonFormat(pattern = Constants.DATE_FORMAT_JSON, timezone = Constants.TIME_ZONE)
	private Date workedDate;

	public EmployeeWorkedHourRequestDto() {
		super();
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getWorkedHours() {
		return workedHours;
	}

	public void setWorkedHours(Integer workedHours) {
		this.workedHours = workedHours;
	}

	public Date getWorkedDate() {
		return workedDate;
	}

	public void setWorkedDate(Date workedDate) {
		this.workedDate = workedDate;
	}

}
