package com.microservices.employee.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservices.employee.entity.EmployeeWorkedHourEntity;

@Repository
public interface IEmployeeWorkedHourRepository extends JpaRepository<EmployeeWorkedHourEntity, Integer> {
	@Query("select e from EmployeeWorkedHourEntity e where e.employee.id = :employeeId and e.workedDate = :workedDate")
	Optional<EmployeeWorkedHourEntity> findEmployeeWorkedHoursByEmployeeIdAndWorkDate(int employeeId, Date workedDate);

	@Query(value = "select IFNULL(SUM(ew.WORKED_HOURS), 0)  from EMPLOYEE_WORKED_HOURS ew \n"
			+ "where ew.EMPLOYEES_ID = :employeeId \n" + "AND ew.WORKED_DATE >= :startDate \n"
			+ "AND ew.WORKED_DATE <= :endDate", nativeQuery = true)
	Integer getTotalWorkedHoursByEmployeeIdAndDateRange(@Param("employeeId") int employeeId,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query(value = "select IFNULL(SUM(ew.WORKED_HOURS), 0) * j.SALARY from EMPLOYEE_WORKED_HOURS ew\n"
			+ "inner join EMPLOYEES e on e.ID = ew.EMPLOYEES_ID \n" + "inner join JOBS j on j.ID = e.JOB_ID \n"
			+ "where e.ID = :employeeId AND ew.WORKED_DATE >= :startDate \n"
			+ "AND ew.WORKED_DATE <= :endDate", nativeQuery = true)
	BigDecimal getPaymentByEmployeeIdAndDateRange(@Param("employeeId") int employeeId,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
