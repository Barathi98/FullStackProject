package com.employeeapp.service;

import java.util.List;

import com.employeeapp.payload.EmployeeDto;



public interface EmployeeService {
	// add employeeDetails
	public EmployeeDto addemployeeDetails(EmployeeDto employee, int departmentId, int projectId);

	// get all employee details
	public List<EmployeeDto> getAllEmployeeDetails();

	// get employee details by Id
	public EmployeeDto getEmployeeDetailsById(int employeeId);

	// update employee details by Id
	public EmployeeDto updateEmployeeDetailsById(EmployeeDto employee, int employeeId);

	// delete employee details by Id
	public void deleteEmployeeDetailsById(int employeeId);

	// get all employee by department Id
	public List<EmployeeDto> getAllEmployeeByDept(int departmentId);

	// get all employee by project Id
	public List<EmployeeDto> getAllEmployeeByProject(int projectId);

}
