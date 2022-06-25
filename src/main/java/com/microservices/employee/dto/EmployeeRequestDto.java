package com.microservices.employee.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservices.employee.utils.Constants;

public class EmployeeRequestDto {
	@NotNull
	@JsonProperty("gender_id")
	private Integer genderId;

	@NotNull
	@JsonProperty("job_id")
	private Integer jobId;

	@NotBlank
	private String name;

	@NotBlank
	@JsonProperty("last_name")
	private String lastName;

	@NotNull
	@JsonFormat(pattern = Constants.DATE_FORMAT_JSON, timezone = Constants.TIME_ZONE)
	private Date birthdate;

	public EmployeeRequestDto() {
		super();
	}

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

}
