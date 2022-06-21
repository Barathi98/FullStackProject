package com.employeeApp.payload;

import java.util.List;

import com.employeeApp.Entity.AdminEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDepartmentDto 
{ 
	
	private int DepartmentId;

	private String DepartmentName;
	
	private List <EmployeeDetailsDto> employee;
	
	private List <ProjectDto> project;
	
	private AdminDto admin;
	   
}
