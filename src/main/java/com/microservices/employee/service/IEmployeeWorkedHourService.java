package com.microservices.employee.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microservices.employee.dto.EmployeeWorkedHourRequestDto;
import com.microservices.employee.entity.EmployeeWorkedHourEntity;
import com.microservices.employee.exception.EmployeeWorkedHoursExistsException;
import com.microservices.employee.exception.ItemNotFoundException;
import com.microservices.employee.exception.OverflowWorkHoursException;
import com.microservices.employee.exception.StartDateInvalidException;
import com.microservices.employee.exception.WorkDateInvalidException;

@Service
public interface IEmployeeWorkedHourService {
	public Optional<EmployeeWorkedHourEntity> findEmployeeWorkedHoursByEmployeeIdAndWorkDate(int employeeId,
			Date workDate);

	public EmployeeWorkedHourEntity save(EmployeeWorkedHourEntity employeeWorkedHourEntity);

	public EmployeeWorkedHourEntity saveValidatedEmployeeWorkedHours(EmployeeWorkedHourRequestDto employeeWorkedHourDto)
			throws OverflowWorkHoursException, WorkDateInvalidException, EmployeeWorkedHoursExistsException,
			ItemNotFoundException;

	Integer getTotalWorkedHoursByEmployeeIdAndDateRange(int employeeId, Date startDate, Date endDate)
			throws StartDateInvalidException;

	BigDecimal getPaymentByEmployeeIdAndDateRange(int employeeId, Date startDate, Date endDate)
			throws StartDateInvalidException;
}
