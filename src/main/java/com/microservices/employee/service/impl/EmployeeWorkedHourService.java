package com.microservices.employee.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.employee.dto.EmployeeWorkedHourRequestDto;
import com.microservices.employee.entity.EmployeeEntity;
import com.microservices.employee.entity.EmployeeWorkedHourEntity;
import com.microservices.employee.exception.EmployeeWorkedHoursExistsException;
import com.microservices.employee.exception.ItemNotFoundException;
import com.microservices.employee.exception.OverflowWorkHoursException;
import com.microservices.employee.exception.StartDateInvalidException;
import com.microservices.employee.exception.WorkDateInvalidException;
import com.microservices.employee.repository.IEmployeeWorkedHourRepository;
import com.microservices.employee.service.IEmployeeService;
import com.microservices.employee.service.IEmployeeWorkedHourService;
import com.microservices.employee.utils.DateUtil;

@Service
public class EmployeeWorkedHourService implements IEmployeeWorkedHourService {

	@Autowired
	private IEmployeeWorkedHourRepository hourRepo;
	@Autowired
	private IEmployeeService employeeService;

	@Transactional
	@Override
	public Optional<EmployeeWorkedHourEntity> findEmployeeWorkedHoursByEmployeeIdAndWorkDate(int employeeId,
			Date workDate) {
		return hourRepo.findEmployeeWorkedHoursByEmployeeIdAndWorkDate(employeeId, workDate);
	}

	@Transactional
	@Override
	public EmployeeWorkedHourEntity save(EmployeeWorkedHourEntity employeeWorkedHourEntity) {
		return hourRepo.save(employeeWorkedHourEntity);
	}

	@Transactional
	@Override
	public EmployeeWorkedHourEntity saveValidatedEmployeeWorkedHours(EmployeeWorkedHourRequestDto employeeWorkedHourDto)
			throws OverflowWorkHoursException, WorkDateInvalidException, EmployeeWorkedHoursExistsException,
			ItemNotFoundException {
		if (!DateUtil.isWorkedHoursAllow(employeeWorkedHourDto.getWorkedHours()))
			throw new OverflowWorkHoursException(employeeWorkedHourDto.getWorkedHours() + " hours not allowed");
		if (!DateUtil.isDate1EqualsOrLowerThanDate2(employeeWorkedHourDto.getWorkedDate(), new Date()))
			throw new WorkDateInvalidException(employeeWorkedHourDto.getWorkedDate().toString() + " is not valid");
		if (findEmployeeWorkedHoursByEmployeeIdAndWorkDate(employeeWorkedHourDto.getEmployeeId(),
				employeeWorkedHourDto.getWorkedDate()).isPresent())
			throw new EmployeeWorkedHoursExistsException("Employee Worked Hours exist in database");
		EmployeeEntity employeeEntity = employeeService.findById(employeeWorkedHourDto.getEmployeeId());

		EmployeeWorkedHourEntity employeeWorkedHourEntity = new EmployeeWorkedHourEntity();
		employeeWorkedHourEntity.setEmployee(employeeEntity);
		employeeWorkedHourEntity.setWorkedDate(employeeWorkedHourDto.getWorkedDate());
		employeeWorkedHourEntity.setWorkedHours(employeeWorkedHourDto.getWorkedHours());
		return save(employeeWorkedHourEntity);
	}

	@Transactional
	@Override
	public Integer getTotalWorkedHoursByEmployeeIdAndDateRange(int employeeId, Date startDate, Date endDate)
			throws StartDateInvalidException {
		if (!DateUtil.isDate1EqualsOrLowerThanDate2(startDate, endDate))
			throw new StartDateInvalidException("start_date is greater than end_date");
		return hourRepo.getTotalWorkedHoursByEmployeeIdAndDateRange(employeeId, startDate, endDate);
	}

	@Transactional
	@Override
	public BigDecimal getPaymentByEmployeeIdAndDateRange(int employeeId, Date startDate, Date endDate)
			throws StartDateInvalidException {
		if (!DateUtil.isDate1EqualsOrLowerThanDate2(startDate, endDate))
			throw new StartDateInvalidException("start_date is greater than end_date");
		return hourRepo.getPaymentByEmployeeIdAndDateRange(employeeId, startDate, endDate);
	}

}
