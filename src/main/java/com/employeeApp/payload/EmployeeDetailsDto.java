package com.employeeApp.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDetailsDto 
{
	private int employeeId;
	  
	  private String employeeFirstName;
	
	  private String employeeLastName;
	  
	  private String employeeMobileNumber;
	 
	  private String employeeEmail;
	  
	  private String employeeeAddress;
	
	  private String employeeBloodGroup;
	 
	  private String employeeDesignation;
	  
	  private String employeeSalary;
	  
	  private EmployeeDepartmentDto department;
	  
	  private ProjectDto project;
}
