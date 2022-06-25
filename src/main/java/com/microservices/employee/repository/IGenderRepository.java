package com.microservices.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.employee.entity.GenderEntity;

@Repository
public interface IGenderRepository extends JpaRepository<GenderEntity, Integer> {

}
