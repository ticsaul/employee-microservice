package com.microservices.employee.dto.base;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservices.employee.utils.Constants;

public class FindingEmployeeRequestDto {
	@JsonProperty("employee_id")
	@NotNull
	private Integer employeeId;

	@NotNull
	@JsonProperty("start_date")
	@JsonFormat(pattern = Constants.DATE_FORMAT_JSON, timezone = Constants.TIME_ZONE)
	private Date startDate;

	@NotNull
	@JsonProperty("end_date")
	@JsonFormat(pattern = Constants.DATE_FORMAT_JSON, timezone = Constants.TIME_ZONE)
	private Date endDate;

	public FindingEmployeeRequestDto() {
		super();
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
