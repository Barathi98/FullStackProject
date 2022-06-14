package com.employeeApp.service;

import java.util.List;

import com.employeeApp.payload.EmployeeDepartmentDto;

public interface EmployeeDepartmentService 
{
    //creating the dept 
	public EmployeeDepartmentDto createEmployeeDept(EmployeeDepartmentDto department);
	
	//getting all the departments
	public List<EmployeeDepartmentDto> getallDepartments();
	
	//getting department by id
	public EmployeeDepartmentDto getDepartmentById(int departmentId);
	
	//update on department by id
	public EmployeeDepartmentDto updateDepartmentById(EmployeeDepartmentDto department,int departmentId);
	
	//delete department by id
	public void deleteDepartmentById(int departmentId);
} 
