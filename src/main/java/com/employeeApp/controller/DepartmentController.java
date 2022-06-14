package com.employeeApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeApp.exception.ApiResponse;
import com.employeeApp.payload.EmployeeDepartmentDto;
import com.employeeApp.service.EmployeeDepartmentService;
@RestController
@RequestMapping("/api")
public class DepartmentController 
{
  @Autowired
  EmployeeDepartmentService employeeDeptService;

@PostMapping("/department")
public ResponseEntity<EmployeeDepartmentDto> createDepartment(@Valid @RequestBody EmployeeDepartmentDto department)
{
	EmployeeDepartmentDto createDept=this.employeeDeptService.createEmployeeDept(department);
	return new ResponseEntity<EmployeeDepartmentDto>(createDept,HttpStatus.CREATED);
	
}

@GetMapping("/department/")
public ResponseEntity<List <EmployeeDepartmentDto>> getDepartments() 
{
	List<EmployeeDepartmentDto> allDepartments=this.employeeDeptService.getallDepartments();
	return new ResponseEntity<List<EmployeeDepartmentDto>> (allDepartments,HttpStatus.OK);
	
}

@GetMapping("/department/{departmentId}")
public ResponseEntity<EmployeeDepartmentDto> getDepartmentById(@PathVariable int departmentId)
{
	EmployeeDepartmentDto employeeDeptDto=this.employeeDeptService.getDepartmentById(departmentId);

	return new ResponseEntity<EmployeeDepartmentDto>(employeeDeptDto,HttpStatus.OK);
}	

@PutMapping("/department/{departmentId}")
public ResponseEntity<EmployeeDepartmentDto> updateDepartmentbyId
(@RequestBody EmployeeDepartmentDto department,@PathVariable int departmentId)
{
	EmployeeDepartmentDto employeeDeptDto=this.employeeDeptService.updateDepartmentById(department, departmentId);
	return new ResponseEntity<EmployeeDepartmentDto>(employeeDeptDto,HttpStatus.OK);
}

@DeleteMapping("/department/{departmentId}")
public ResponseEntity<ApiResponse> deleteDepartmentById(@PathVariable int departmentId)
{
	this.employeeDeptService.deleteDepartmentById(departmentId);
	return new ResponseEntity<ApiResponse>(new ApiResponse("department is successfully deleted", true),HttpStatus.OK);
	
}
}
