package com.microservices.employee.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.employee.entity.GenderEntity;
import com.microservices.employee.exception.ItemNotFoundException;
import com.microservices.employee.repository.IGenderRepository;
import com.microservices.employee.service.IGenderService;

@Service
public class GenderServiceImpl implements IGenderService {

	@Autowired
	private IGenderRepository genderRepo;

	@Transactional
	@Override
	public GenderEntity findById(Integer id) throws ItemNotFoundException {
		Optional<GenderEntity> genderOpt = genderRepo.findById(id);
		if (!genderOpt.isPresent())
			throw new ItemNotFoundException("Gender Id " + id + "   not found");
		return genderOpt.get();
	}

}
