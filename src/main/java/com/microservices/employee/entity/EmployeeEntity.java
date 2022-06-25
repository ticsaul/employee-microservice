package com.microservices.employee.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservices.employee.utils.Constants;

@Entity
@Table(name = "EMPLOYEES")
@NamedEntityGraph(name = EmployeeEntity.ALL_INFO_GRAPH, attributeNodes = { @NamedAttributeNode("gender"),
		@NamedAttributeNode("job"), @NamedAttributeNode("employeeWorkedHours") })
public class EmployeeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ALL_INFO_GRAPH = "EmployeeEntity.allInfo";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@JsonFormat(pattern = Constants.DATE_FORMAT_BIRTHDATE_JSON)
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDATE")
	private Date birthdate;

	@JsonProperty("last_name")
	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "NAME")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GENDER_ID")
	private GenderEntity gender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JOB_ID")
	private JobEntity job;

	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private List<EmployeeWorkedHourEntity> employeeWorkedHours;

	public EmployeeEntity() {
		// default constructor
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GenderEntity getGender() {
		return this.gender;
	}

	public void setGender(GenderEntity gender) {
		this.gender = gender;
	}

	public JobEntity getJob() {
		return this.job;
	}

	public void setJob(JobEntity job) {
		this.job = job;
	}

	public List<EmployeeWorkedHourEntity> getEmployeeWorkedHours() {
		return this.employeeWorkedHours;
	}

	public void setEmployeeWorkedHours(List<EmployeeWorkedHourEntity> employeeWorkedHours) {
		this.employeeWorkedHours = employeeWorkedHours;
	}

	public EmployeeWorkedHourEntity addEmployeeWorkedHour(EmployeeWorkedHourEntity employeeWorkedHour) {
		getEmployeeWorkedHours().add(employeeWorkedHour);
		employeeWorkedHour.setEmployee(this);

		return employeeWorkedHour;
	}

	public EmployeeWorkedHourEntity removeEmployeeWorkedHour(EmployeeWorkedHourEntity employeeWorkedHour) {
		getEmployeeWorkedHours().remove(employeeWorkedHour);
		employeeWorkedHour.setEmployee(null);

		return employeeWorkedHour;
	}

}
