package com.employeeApp.service;

import java.util.List;

import com.employeeApp.exception.ApiResponse;
import com.employeeApp.payload.EmployeeDetailsDto;

public interface EmployeeDetailsService 
{
	//add employeeDetails
	public EmployeeDetailsDto addemployeeDetails(EmployeeDetailsDto employee,int departmentId,int projectId);
	
	//get all employee details 
	public List <EmployeeDetailsDto> getAllEmployeeDetails();
	
	//get employee details by Id
	public EmployeeDetailsDto getEmployeeDetailsById(int employeeId);
	
	//update employee details by Id
	public EmployeeDetailsDto updateEmployeeDetailsById(EmployeeDetailsDto employee,int employeeId);
	
	//delete employee details by Id
	public void deleteEmployeeDetailsById(int employeeId);
	
	//get all employee by department Id
	public List<EmployeeDetailsDto> getAllEmployeeByDept(int departmentId);
	
	//get all employee by project Id
	public List<EmployeeDetailsDto> getAllEmployeeByProject(int projectId);
	
		
	

}
