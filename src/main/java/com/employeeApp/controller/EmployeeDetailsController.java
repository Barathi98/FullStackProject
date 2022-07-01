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
import com.employeeapp.payload.EmployeeDto;
import com.employeeapp.service.EmployeeService;

@RestController
@RequestMapping("/api")

public class EmployeeDetailsController {
	@Autowired
	EmployeeService employeeDetailsService;

	// To create an employee by parent ids which are department and project
	@PostMapping("/department/{departmentId}/project/{projectId}/employeeDetails")
	public ResponseEntity<EmployeeDto> addEmployeeDetails(@Valid @PathVariable int departmentId,
			@PathVariable int projectId, @RequestBody EmployeeDto employee) {
		// System.out.println("this is departmentId " +departmentId+" this is
		// employeeData "+ employee );
		EmployeeDto addedEmployeeDetails = this.employeeDetailsService.addemployeeDetails(employee, departmentId,
				projectId);
		return new ResponseEntity<EmployeeDto>(addedEmployeeDetails, HttpStatus.CREATED);

	}

	// To fetch an employee by Id
	@GetMapping("/employeeDetails/{employeeId}")
	public ResponseEntity<EmployeeDto> getEmployeeDetailsById(@PathVariable int employeeId) {
		EmployeeDto getEmployee = this.employeeDetailsService.getEmployeeDetailsById(employeeId);
		return new ResponseEntity<EmployeeDto>(getEmployee, HttpStatus.OK);

	}

//	To fetch all the employees
	@GetMapping("/employeeDetails")
	public ResponseEntity<List<EmployeeDto>> getAllEmployeeDetails() {
		List<EmployeeDto> getAllEmployeeRecords = this.employeeDetailsService.getAllEmployeeDetails();
		return new ResponseEntity<List<EmployeeDto>>(getAllEmployeeRecords, HttpStatus.OK);

	}

	// To update an employee by Id
	@PutMapping("/employeeDetails/{employeeId}")
	public ResponseEntity<EmployeeDto> updateEmployeeDetails(@RequestBody EmployeeDto employee,
			@PathVariable int employeeId) {
		EmployeeDto updatedEmployeeDetails = this.employeeDetailsService.updateEmployeeDetailsById(employee,
				employeeId);

		return new ResponseEntity<EmployeeDto>(updatedEmployeeDetails, HttpStatus.OK);
	}

	// To delete an employee by Id
	@DeleteMapping("/employeeDetails/{employeeId}")
	public ResponseEntity<ApiResponse> deleteEmployeeDetails(@PathVariable int employeeId) {
		ApiResponse deletedEmployeeDetails = new ApiResponse("Employee details were deleted successfully", true);
		return new ResponseEntity<ApiResponse>(deletedEmployeeDetails, HttpStatus.OK);
	}

	// To fetch all the employee by departmentId
	@GetMapping("/employeeDetail/department/{departmentId}")
	public ResponseEntity<List<EmployeeDto>> getAllEmployeeByDept(@PathVariable int departmentId) {
		List<EmployeeDto> getAllEmployeeByDept = this.employeeDetailsService.getAllEmployeeByDept(departmentId);
		return new ResponseEntity<List<EmployeeDto>>(getAllEmployeeByDept, HttpStatus.OK);

	}

	// To fetch all the employee by projectId
	@GetMapping("/employeeDetail/project/{projectId}")
	public ResponseEntity<List<EmployeeDto>> getAllEmployeeByProject(@PathVariable int projectId) {
		List<EmployeeDto> getAllEmployeeByProject = this.employeeDetailsService
				.getAllEmployeeByProject(projectId);
		return new ResponseEntity<List<EmployeeDto>>(getAllEmployeeByProject, HttpStatus.OK);

	}

}
