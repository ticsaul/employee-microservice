package com.microservices.employee.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.employee.dto.EmployeeRequestDto;
import com.microservices.employee.dto.EmployeeWorkedHourRequestDto;
import com.microservices.employee.dto.EmployeesResponseDto;
import com.microservices.employee.dto.PaymentRequestDto;
import com.microservices.employee.dto.PaymentResponseDto;
import com.microservices.employee.dto.RowInsertedResponseDto;
import com.microservices.employee.dto.TotalWorkedHoursRequestDto;
import com.microservices.employee.dto.TotalWorkedHoursResponseDto;
import com.microservices.employee.entity.EmployeeEntity;
import com.microservices.employee.entity.EmployeeWorkedHourEntity;
import com.microservices.employee.service.IEmployeeService;
import com.microservices.employee.service.IEmployeeWorkedHourService;
import com.microservices.employee.service.IJobService;
import com.microservices.employee.utils.LoggingUtil;

@RestController
@RequestMapping("${employees.endpoint}")
@Validated
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IEmployeeWorkedHourService hourService;
	@Autowired
	private IJobService jobService;

	@PostMapping
	public ResponseEntity<RowInsertedResponseDto> saveEmployee(
			@Valid @RequestBody(required = true) EmployeeRequestDto employeeDto) {
		LOGGER.info("EmployeeDto: {}", LoggingUtil.getJsonFormato(employeeDto, true));
		RowInsertedResponseDto response = new RowInsertedResponseDto();
		HttpStatus status = null;
		try {
			EmployeeEntity employeeSaved = employeeService.saveValidatedEmployee(employeeDto);
			response.setId(employeeSaved.getId());
			response.setSuccess(true);
			status = HttpStatus.OK;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			response.setId(null);
			response.setSuccess(false);
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(response, status);
	}

	@PostMapping("${employees.workedhours.endpoint}")
	public ResponseEntity<RowInsertedResponseDto> saveEmployeeWorkedHours(
			@Valid @RequestBody(required = true) EmployeeWorkedHourRequestDto employeeWorkedHourDto) {
		LOGGER.info("EmployeeWorkedHourDto: {}", LoggingUtil.getJsonFormato(employeeWorkedHourDto, true));
		RowInsertedResponseDto response = new RowInsertedResponseDto();
		HttpStatus status = null;
		try {
			EmployeeWorkedHourEntity employeeWorkedHourEntity = hourService
					.saveValidatedEmployeeWorkedHours(employeeWorkedHourDto);
			response.setId(employeeWorkedHourEntity.getId());
			response.setSuccess(true);
			status = HttpStatus.OK;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			response.setId(null);
			response.setSuccess(false);
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(response, status);
	}

	@GetMapping("${employees.by-job.endpoint}")
	public ResponseEntity<EmployeesResponseDto> getEmployeesByJob(@PathVariable("jobId") int jobId) {
		EmployeesResponseDto response = new EmployeesResponseDto();
		HttpStatus status = null;
		try {
			response.setEmployees(employeeService.findEmployeesByJobId(jobService.findById(jobId).getId()));
			response.setSuccess(true);
			status = HttpStatus.OK;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			response.setEmployees(new ArrayList<>());
			response.setSuccess(false);
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(response, status);
	}

	@PostMapping("${employees.totalworkedhours.endpoint}")
	public ResponseEntity<TotalWorkedHoursResponseDto> getTotalWorkedHours(
			@Valid @RequestBody(required = true) TotalWorkedHoursRequestDto totalHourDto) {
		LOGGER.info("TotalWorkedHoursRequestDto: {}", LoggingUtil.getJsonFormato(totalHourDto, true));
		TotalWorkedHoursResponseDto response = new TotalWorkedHoursResponseDto();
		HttpStatus status = null;
		try {
			EmployeeEntity employee = employeeService.findById(totalHourDto.getEmployeeId());
			response.setTotalWorkedHours(hourService.getTotalWorkedHoursByEmployeeIdAndDateRange(employee.getId(),
					totalHourDto.getStartDate(), totalHourDto.getEndDate()));
			response.setSuccess(true);
			status = HttpStatus.OK;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			response.setTotalWorkedHours(null);
			response.setSuccess(false);
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(response, status);
	}

	@PostMapping("${employees.payment.endpoint}")
	public ResponseEntity<PaymentResponseDto> getPayment(
			@Valid @RequestBody(required = true) PaymentRequestDto paymentRequestDto) {
		LOGGER.info("PaymentRequestDto: {}", LoggingUtil.getJsonFormato(paymentRequestDto, true));
		PaymentResponseDto response = new PaymentResponseDto();
		HttpStatus status = null;
		try {
			EmployeeEntity employee = employeeService.findById(paymentRequestDto.getEmployeeId());
			response.setPayment(hourService.getPaymentByEmployeeIdAndDateRange(employee.getId(),
					paymentRequestDto.getStartDate(), paymentRequestDto.getEndDate()));
			response.setSuccess(true);
			status = HttpStatus.OK;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			response.setPayment(null);
			response.setSuccess(false);
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(response, status);
	}
}
