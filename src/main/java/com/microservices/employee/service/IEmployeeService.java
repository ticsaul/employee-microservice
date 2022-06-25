package com.microservices.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microservices.employee.dto.EmployeeRequestDto;
import com.microservices.employee.entity.EmployeeEntity;
import com.microservices.employee.exception.EmployeeExistsException;
import com.microservices.employee.exception.ItemNotFoundException;
import com.microservices.employee.exception.YoungEmployeeException;

@Service
public interface IEmployeeService {
	public EmployeeEntity findById(Integer id) throws ItemNotFoundException;

	public EmployeeEntity save(EmployeeEntity employeeEntity);

	public EmployeeEntity saveValidatedEmployee(EmployeeRequestDto employeeDto)
			throws YoungEmployeeException, ItemNotFoundException, EmployeeExistsException;

	public Optional<EmployeeEntity> findEmployeeByNameAndLastNameAndBirthdate(EmployeeRequestDto employeeDto);

	public List<EmployeeEntity> findEmployeesByJobId(int jobId);
}
