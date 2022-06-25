package com.microservices.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.employee.entity.JobEntity;

@Repository
public interface IJobRepository extends JpaRepository<JobEntity, Integer> {

}
