package com.microservices.employee.service;

import org.springframework.stereotype.Service;

import com.microservices.employee.entity.GenderEntity;
import com.microservices.employee.exception.ItemNotFoundException;

@Service
public interface IGenderService {
	public GenderEntity findById(Integer id) throws ItemNotFoundException;
}
