package com.employeeApp.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeApp.Entity.DepartmentEntity;
import com.employeeApp.Entity.EmployeeDetailsEntity;
import com.employeeApp.exception.ResourceNotFoundException;
import com.employeeApp.payload.EmployeeDepartmentDto;
import com.employeeApp.payload.EmployeeDetailsDto;
import com.employeeApp.repository.EmployeeDepartmentRepo;
import com.employeeApp.repository.EmployeeDetailsRepository;
import com.employeeApp.service.EmployeeDetailsService;
@Service
public class EmployeeDetailsServiceImplementation implements EmployeeDetailsService{
    @Autowired
     EmployeeDetailsRepository employeeDetailsRepo;
    
    @Autowired
    EmployeeDepartmentRepo employeeDeptRepository;
    
    @Autowired
     ModelMapper modelMapper;
    
    
	@Override
	public EmployeeDetailsDto addemployeeDetails(EmployeeDetailsDto employeeDto,int departmentId) {
		DepartmentEntity departmentEntity=this.employeeDeptRepository.findById(departmentId).orElseThrow(
				()->new ResourceNotFoundException("Department", "DepartmentId", departmentId));
		employeeDto.setDepartment(this.modelMapper.map(departmentEntity, EmployeeDepartmentDto.class));
		EmployeeDetailsEntity employeeEntity=this.employeeDetailsRepo.save(this.employeeDtoToEntity(employeeDto));
		
		return this.employeeEntityToDto(employeeEntity);
	}

	@Override
	public List<EmployeeDetailsDto> getAllEmployeeDetails() {
		List<EmployeeDetailsEntity> employeeRecords=this.employeeDetailsRepo.findAll();
		List<EmployeeDetailsDto> employeeRecordsDto=employeeRecords.stream().map
				(employees->this.employeeEntityToDto(employees)).collect(Collectors.toList());
		return employeeRecordsDto;
	}

	@Override
	public EmployeeDetailsDto getEmployeeDetailsById(int employeeId) {
		EmployeeDetailsEntity employeeRecord=this.employeeDetailsRepo.findById(employeeId).
				orElseThrow(()->new ResourceNotFoundException("Employee", "EmployeeId", employeeId));
		return this.employeeEntityToDto(employeeRecord);
	}

	@Override
	public EmployeeDetailsDto updateEmployeeDetailsById(EmployeeDetailsDto employee, int employeeId) {
		EmployeeDetailsEntity employeeRecord=this.employeeDetailsRepo.findById(employeeId).
				orElseThrow(()->new ResourceNotFoundException("Employee", "EmployeeId", employeeId));
		employeeRecord.setEmployeeId(employee.getEmployeeId());
		employeeRecord.setEmployeeFirstName(employee.getEmployeeFirstName());
		employeeRecord.setEmployeeLastName(employee.getEmployeeLastName());
		employeeRecord.setEmployeeMobileNumber(employee.getEmployeeMobileNumber());
		employeeRecord.setEmployeeEmail(employee.getEmployeeEmail());
		employeeRecord.setEmployeeeAddress(employee.getEmployeeeAddress());
		employeeRecord.setEmployeeBloodGroup(employee.getEmployeeBloodGroup());
		employeeRecord.setEmployeeDesignation(employee.getEmployeeDesignation());
		employeeRecord.setEmployeeSalary(employee.getEmployeeSalary());
		EmployeeDetailsEntity savedEmployeeRecord= this.employeeDetailsRepo.save(employeeRecord);
		return this.employeeEntityToDto(savedEmployeeRecord);
	}

	@Override
	public void deleteEmployeeDetailsById(int employeeId) {
		EmployeeDetailsEntity employeeRecord=this.employeeDetailsRepo.findById(employeeId).
				orElseThrow(()->new ResourceNotFoundException("Employee", "EmployeeId", employeeId));
		this.employeeDetailsRepo.delete(employeeRecord);
		
	}
	
	public EmployeeDetailsEntity employeeDtoToEntity(EmployeeDetailsDto employeeDto)
	{
	 return	this.modelMapper.map(employeeDto,EmployeeDetailsEntity.class);
	}
	
	public EmployeeDetailsDto employeeEntityToDto(EmployeeDetailsEntity employeeEntity)
	{
		return this.modelMapper.map(employeeEntity, EmployeeDetailsDto.class);
		
	}

	@Override
	public List<EmployeeDetailsDto> getAllEmployeeByDept(int departmentId) {
		DepartmentEntity deptEntity=this.employeeDeptRepository.findById(departmentId).orElseThrow(
				()->new ResourceNotFoundException("Department", "DepartmentId", departmentId));
		List <EmployeeDetailsEntity> employeeDetailsEntity=this.employeeDetailsRepo.findByDepartment(deptEntity);
		List <EmployeeDetailsDto> employeeDetailsDto=employeeDetailsEntity.stream().map
				(employees->this.employeeEntityToDto(employees)).collect(Collectors.toList());
		return employeeDetailsDto;
	}

	
	

}
