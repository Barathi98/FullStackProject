package com.employeeapp.service;

import java.util.List;

import com.employeeapp.payload.DepartmentDto;



public interface DepartmentService {
	// creating the dept
	public DepartmentDto createEmployeeDept(DepartmentDto department, int adminId);

	// getting all the departments
	public List<DepartmentDto> getallDepartments();

	// getting department by id
	public DepartmentDto getDepartmentById(int departmentId);

	// update on department by id
	public DepartmentDto updateDepartmentById(DepartmentDto department, int departmentId);

	// delete department by id
	public void deleteDepartmentById(int departmentId);

	public List<DepartmentDto> getAllDepartmentByAdmin(int adminId);
}
