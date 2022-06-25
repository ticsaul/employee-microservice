package com.microservices.employee.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.employee.entity.JobEntity;
import com.microservices.employee.exception.ItemNotFoundException;
import com.microservices.employee.repository.IJobRepository;
import com.microservices.employee.service.IJobService;

@Service
public class JobServiceImpl implements IJobService {

	@Autowired
	private IJobRepository jobRepo;

	@Override
	public JobEntity findById(Integer id) throws ItemNotFoundException {
		Optional<JobEntity> jobOpt = jobRepo.findById(id);
		if (!jobOpt.isPresent())
			throw new ItemNotFoundException("Job Id " + id + "   not found");
		return jobOpt.get();
	}

}
