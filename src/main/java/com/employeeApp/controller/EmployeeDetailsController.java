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
import com.employeeApp.payload.EmployeeDetailsDto;
import com.employeeApp.service.EmployeeDetailsService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeDetailsController 
{
	@Autowired
	EmployeeDetailsService employeeDetailsService;
	
	@PostMapping("/department/{departmentId}/project/{projectId}/employeeDetails")
	public ResponseEntity<EmployeeDetailsDto> addEmployeeDetails(@Valid @PathVariable int departmentId,
	 @PathVariable int projectId,@RequestBody EmployeeDetailsDto employee)
	{
		//System.out.println("this is departmentId " +departmentId+" this is employeeData "+ employee );
		EmployeeDetailsDto addedEmployeeDetails=
				this.employeeDetailsService.addemployeeDetails(employee, departmentId,projectId);
		return new ResponseEntity<EmployeeDetailsDto>(addedEmployeeDetails,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/employeeDetails/{employeeId}")
	public ResponseEntity<EmployeeDetailsDto> getEmployeeDetailsById(@PathVariable int employeeId)
	{
		EmployeeDetailsDto getEmployee=this.employeeDetailsService.getEmployeeDetailsById(employeeId);
		return new ResponseEntity<EmployeeDetailsDto>(getEmployee,HttpStatus.OK);
		
	}
	
	@GetMapping("/employeeDetails")
	public ResponseEntity<List<EmployeeDetailsDto>> getAllEmployeeDetails()
	{
		List <EmployeeDetailsDto> getAllEmployeeRecords=
				this.employeeDetailsService.getAllEmployeeDetails();
		return new ResponseEntity<List<EmployeeDetailsDto>>(getAllEmployeeRecords,HttpStatus.OK);
		
	}
	
	@PutMapping("/employeeDetails/{employeeId}")
	public ResponseEntity<EmployeeDetailsDto> updateEmployeeDetails
	(@RequestBody EmployeeDetailsDto employee,@PathVariable int employeeId)
	{
		EmployeeDetailsDto updatedEmployeeDetails=this.employeeDetailsService.
				updateEmployeeDetailsById(employee, employeeId);
		
		return new ResponseEntity<EmployeeDetailsDto>(updatedEmployeeDetails,HttpStatus.OK);
	}
	
	@DeleteMapping("/employeeDetails/{employeeId}")
	public ResponseEntity<ApiResponse> deleteEmployeeDetails(@PathVariable int employeeId)
	{
		ApiResponse deletedEmployeeDetails=
				new ApiResponse("Employee details were deleted successfully", true);
		return new ResponseEntity<ApiResponse>(deletedEmployeeDetails,HttpStatus.OK);
	}
	
@GetMapping("/employeeDetail/department/{departmentId}")
public ResponseEntity<List<EmployeeDetailsDto>> getAllEmployeeByDept(@PathVariable int departmentId)
{
	List<EmployeeDetailsDto> getAllEmployeeByDept=this.employeeDetailsService.getAllEmployeeByDept(departmentId);
	return new ResponseEntity<List<EmployeeDetailsDto>>(getAllEmployeeByDept,HttpStatus.OK);
	
}

@GetMapping("/employeeDetail/project/{projectId}")
public ResponseEntity<List<EmployeeDetailsDto>> getAllEmployeeByProject(@PathVariable int projectId)
{
	List<EmployeeDetailsDto> getAllEmployeeByProject=this.employeeDetailsService.getAllEmployeeByProject(projectId);
	return new ResponseEntity<List<EmployeeDetailsDto>>(getAllEmployeeByProject,HttpStatus.OK);
	
}
	
	
	


}
