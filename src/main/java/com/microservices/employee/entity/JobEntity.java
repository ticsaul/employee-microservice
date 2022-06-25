package com.microservices.employee.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "JOBS")
public class JobEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SALARY")
	private BigDecimal salary;

	@JsonIgnore
	@OneToMany(mappedBy = "job")
	private List<EmployeeEntity> employees;

	public JobEntity() {
		// default constructor
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public List<EmployeeEntity> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<EmployeeEntity> employees) {
		this.employees = employees;
	}

	public EmployeeEntity addEmployee(EmployeeEntity employee) {
		getEmployees().add(employee);
		employee.setJob(this);

		return employee;
	}

	public EmployeeEntity removeEmployee(EmployeeEntity employee) {
		getEmployees().remove(employee);
		employee.setJob(null);

		return employee;
	}

}