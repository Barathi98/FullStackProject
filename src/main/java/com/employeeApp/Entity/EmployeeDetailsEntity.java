package com.employeeApp.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "EmployeeDetails")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EmployeeDetailsEntity {
	@Id
	@SequenceGenerator(name = "emp", initialValue = 301)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;

	@Column(nullable = false)
	@NotEmpty(message = "First Name cannot be empty")
	@Size(min = 3, max = 15)
	private String employeeFirstName;

	@Column(nullable = false)
	@NotEmpty(message = "Last Name cannot be empty")
	@Size(min = 3, max = 15)
	private String employeeLastName;

	@Column(nullable = false)
	@NotEmpty(message = "Mobile Number cannot be empty")
	@Size(min = 3, max = 15)
	private String employeeMobileNumber;

	@Column(nullable = false)
	@NotEmpty(message = "Email cannot be empty")
	@Email
	@Pattern(message = "Invalid email id", regexp = "^.+@.+\\..+$")
	private String employeeEmail;

	@Column(nullable = false)
	@NotEmpty(message = "Address cannot be empty")
	private String employeeeAddress;

	@Column(nullable = false)
	private String employeeBloodGroup;

	@Column(nullable = false)
	@NotEmpty(message = "Designation cannot be empty")
	private String employeeDesignation;

	@Column(nullable = false)
	@NotEmpty(message = "Salary cannot be empty")
	private String employeeSalary;

	@ManyToOne

//  (optional=true)
//  @JoinColumn(name="department_id")

	private DepartmentEntity department;

	@ManyToOne
//  @JsonBackReference
	private ProjectEntity project;

}
