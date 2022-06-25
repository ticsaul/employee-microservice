package com.microservices.employee.service;

import org.springframework.stereotype.Service;

import com.microservices.employee.entity.JobEntity;
import com.microservices.employee.exception.ItemNotFoundException;

@Service
public interface IJobService {
	public JobEntity findById(Integer id) throws ItemNotFoundException;
}
