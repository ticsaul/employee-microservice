package com.microservices.employee.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservices.employee.entity.EmployeeEntity;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

	@Query("select e from EmployeeEntity e where e.name = :name and e.lastName = :lastName and e.birthdate = :birthdate")
	Optional<EmployeeEntity> findEmployeeByNameAndLastNameAndBirthdate(String name, String lastName, Date birthdate);

	@EntityGraph(value = EmployeeEntity.ALL_INFO_GRAPH, type = EntityGraphType.LOAD)
	@Query("select e from EmployeeEntity  e where e.job.id = :jobId")
	List<EmployeeEntity> findEmployeesByJobId(int jobId);

}
