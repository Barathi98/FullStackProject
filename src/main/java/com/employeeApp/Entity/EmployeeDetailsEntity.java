package com.employeeApp.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="EmployeeDetails")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDetailsEntity
{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int employeeId;
  
  
  @Column(nullable=false)
  @NotEmpty(message="First Name cannot be empty")
  @Size(min=3,max=15)
  private String employeeFirstName;
  
  
  @Column(nullable=false)
  @NotEmpty(message="Last Name cannot be empty")
  @Size(min=3,max=15)
  private String employeeLastName;
  
  
  @Column(nullable=false)
  @NotEmpty(message="Mobile Number cannot be empty")
  @Size(min=3,max=15)
  private String employeeMobileNumber;
  
  
  @Column(nullable=false)
  @NotEmpty(message="Email cannot be empty")
  @Email
  @Pattern(message = "Invalid email id", regexp = "^.+@.+\\..+$")
  private String employeeEmail;
  
  
  @Column(nullable=false)
  @NotEmpty(message="Address cannot be empty")
  private String employeeeAddress;
  
  @Column(nullable=false)
  private String employeeBloodGroup;
  
  
  @Column(nullable=false)
  @NotEmpty(message="Designation cannot be empty")
  private String employeeDesignation;
  
  @Column(nullable=false)
  @NotEmpty(message="Salary cannot be empty")
  private String employeeSalary;
  
  @ManyToOne
  private DepartmentEntity department;
  
  @ManyToOne
  private ProjectEntity project;
  
}
