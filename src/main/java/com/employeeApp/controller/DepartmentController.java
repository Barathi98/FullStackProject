package com.employeeApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

public class DepartmentController {
	@Autowired
	EmployeeDepartmentService employeeDeptService;

//To create a department by Admin parent id	
	@PostMapping("/department/{adminId}")
	public ResponseEntity<EmployeeDepartmentDto> createDepartment(@Valid @RequestBody EmployeeDepartmentDto department,
			@PathVariable int adminId) {
		EmployeeDepartmentDto createDept = this.employeeDeptService.createEmployeeDept(department, adminId);
		return new ResponseEntity<EmployeeDepartmentDto>(createDept, HttpStatus.CREATED);

	}

	// To fetch all departments
	@GetMapping("/department/")
	public ResponseEntity<List<EmployeeDepartmentDto>> getDepartments() {
		List<EmployeeDepartmentDto> allDepartments = this.employeeDeptService.getallDepartments();
		// System.out.println(allDepartments.g);
		return new ResponseEntity<List<EmployeeDepartmentDto>>(allDepartments, HttpStatus.OK);

	}

	// To fetch a department by Id
	@GetMapping("/department/{departmentId}")
	public ResponseEntity<EmployeeDepartmentDto> getDepartmentById(@PathVariable int departmentId) {
		EmployeeDepartmentDto employeeDeptDto = this.employeeDeptService.getDepartmentById(departmentId);

		return new ResponseEntity<EmployeeDepartmentDto>(employeeDeptDto, HttpStatus.OK);
	}

	// To update department by Id
	@PutMapping("/department/{departmentId}")
	public ResponseEntity<EmployeeDepartmentDto> updateDepartmentbyId(@RequestBody EmployeeDepartmentDto department,
			@PathVariable int departmentId) {
		EmployeeDepartmentDto employeeDeptDto = this.employeeDeptService.updateDepartmentById(department, departmentId);
		return new ResponseEntity<EmployeeDepartmentDto>(employeeDeptDto, HttpStatus.OK);
	}

	// To delete department by Id
	@DeleteMapping("/department/{departmentId}")
	public ResponseEntity<ApiResponse> deleteDepartmentById(@PathVariable int departmentId) {
		this.employeeDeptService.deleteDepartmentById(departmentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("department is successfully deleted", true),
				HttpStatus.OK);

	}

	// To fetch all the departments by adminId
	@GetMapping("departments/{adminId}")

	public ResponseEntity<List<EmployeeDepartmentDto>> getAlldepartmentByAdmin(@PathVariable int adminId) {
		List<EmployeeDepartmentDto> departmentList = this.employeeDeptService.getAllDepartmentByAdmin(adminId);
		return new ResponseEntity<List<EmployeeDepartmentDto>>(departmentList, HttpStatus.OK);
	}

}
