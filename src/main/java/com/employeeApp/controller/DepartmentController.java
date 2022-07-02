package com.employeeapp.controller;

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

import com.employeeapp.exception.ApiResponse;
import com.employeeapp.payload.DepartmentDto;
import com.employeeapp.service.DepartmentService;

@RestController
@RequestMapping("/api")

public class DepartmentController {
	@Autowired
	DepartmentService departmentService;

//To create a department by Admin parent id	
	@PostMapping("/department/{adminId}")
	public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody DepartmentDto department,
			@PathVariable int adminId) {
		DepartmentDto createDept = this.departmentService.createEmployeeDept(department, adminId);
		return new ResponseEntity<DepartmentDto>(createDept, HttpStatus.CREATED);

	}

	// To fetch all departments
	@GetMapping("/department/")
	public ResponseEntity<List<DepartmentDto>> getDepartments() {
		List<DepartmentDto> allDepartments = this.departmentService.getallDepartments();
		// System.out.println(allDepartments.g);
		return new ResponseEntity<List<DepartmentDto>>(allDepartments, HttpStatus.OK);

	}

	// To fetch a department by Id
	@GetMapping("/department/{departmentId}")
	public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable int departmentId) {
		DepartmentDto employeeDeptDto = this.departmentService.getDepartmentById(departmentId);

		return new ResponseEntity<DepartmentDto>(employeeDeptDto, HttpStatus.OK);
	}

	// To update department by Id
	@PutMapping("/department/{departmentId}")
	public ResponseEntity<DepartmentDto> updateDepartmentbyId(@RequestBody DepartmentDto department,
			@PathVariable int departmentId) {
		DepartmentDto employeeDeptDto = this.departmentService.updateDepartmentById(department, departmentId);
		return new ResponseEntity<DepartmentDto>(employeeDeptDto, HttpStatus.OK);
	}

	// To delete department by Id
	@DeleteMapping("/department/{departmentId}")
	public ResponseEntity<ApiResponse> deleteDepartmentById(@PathVariable int departmentId) {
		this.departmentService.deleteDepartmentById(departmentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("department is successfully deleted", true),
				HttpStatus.OK);

	}

	// To fetch all the departments by adminId
	@GetMapping("departments/{adminId}")

	public ResponseEntity<List<DepartmentDto>> getAlldepartmentByAdmin(@PathVariable int adminId) {
		List<DepartmentDto> departmentList = this.departmentService.getAllDepartmentByAdmin(adminId);
		return new ResponseEntity<List<DepartmentDto>>(departmentList, HttpStatus.OK);
	}

}
