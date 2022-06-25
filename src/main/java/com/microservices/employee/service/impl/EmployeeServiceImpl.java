package com.microservices.employee.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.employee.dto.EmployeeRequestDto;
import com.microservices.employee.entity.EmployeeEntity;
import com.microservices.employee.entity.GenderEntity;
import com.microservices.employee.entity.JobEntity;
import com.microservices.employee.exception.EmployeeExistsException;
import com.microservices.employee.exception.ItemNotFoundException;
import com.microservices.employee.exception.YoungEmployeeException;
import com.microservices.employee.repository.IEmployeeRepository;
import com.microservices.employee.service.IEmployeeService;
import com.microservices.employee.service.IGenderService;
import com.microservices.employee.service.IJobService;
import com.microservices.employee.utils.DateUtil;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeRepository employeeRepo;
	@Autowired
	private IGenderService genderService;
	@Autowired
	private IJobService jobService;

	@Transactional
	@Override
	public EmployeeEntity findById(Integer id) throws ItemNotFoundException {
		Optional<EmployeeEntity> employeeOpt = employeeRepo.findById(id);
		if (!employeeOpt.isPresent())
			throw new ItemNotFoundException("Employee Id " + id + "   not found");
		return employeeOpt.get();
	}

	@Transactional
	@Override
	public EmployeeEntity save(EmployeeEntity employeeEntity) {
		return employeeRepo.save(employeeEntity);
	}

	@Transactional
	@Override
	public EmployeeEntity saveValidatedEmployee(EmployeeRequestDto employeeDto)
			throws YoungEmployeeException, ItemNotFoundException, EmployeeExistsException {
		if (!DateUtil.isAdultByBirthdate(employeeDto.getBirthdate()))
			throw new YoungEmployeeException("Employee is not adult");
		if (findEmployeeByNameAndLastNameAndBirthdate(employeeDto).isPresent())
			throw new EmployeeExistsException(
					"Employee " + employeeDto.getName() + " " + employeeDto.getLastName() + " exists in database");

		GenderEntity genderEntity = genderService.findById(employeeDto.getGenderId());
		JobEntity jobEntity = jobService.findById(employeeDto.getJobId());
		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setBirthdate(employeeDto.getBirthdate());
		employeeEntity.setGender(genderEntity);
		employeeEntity.setJob(jobEntity);
		employeeEntity.setLastName(employeeDto.getLastName());
		employeeEntity.setName(employeeDto.getName());
		return save(employeeEntity);
	}

	@Transactional
	@Override
	public Optional<EmployeeEntity> findEmployeeByNameAndLastNameAndBirthdate(EmployeeRequestDto employeeDto) {
		return employeeRepo.findEmployeeByNameAndLastNameAndBirthdate(employeeDto.getName(), employeeDto.getLastName(),
				employeeDto.getBirthdate());
	}

	@Transactional(readOnly = true)
	@Override
	public List<EmployeeEntity> findEmployeesByJobId(int jobId) {
		return employeeRepo.findEmployeesByJobId(jobId);
	}

}
