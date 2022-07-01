package com.employeeapp.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeapp.entity.DepartmentEntity;
import com.employeeapp.entity.EmployeeEntity;
import com.employeeapp.entity.ProjectEntity;
import com.employeeapp.exception.ResourceNotFoundException;
import com.employeeapp.payload.DepartmentDto;
import com.employeeapp.payload.EmployeeDto;
import com.employeeapp.payload.ProjectDto;
import com.employeeapp.repository.DepartmentRepository;
import com.employeeapp.repository.EmployeeRepository;
import com.employeeapp.repository.ProjectRepository;
import com.employeeapp.service.EmployeeService;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
	@Autowired
	EmployeeRepository employeeDetailsRepo;

	@Autowired
	DepartmentRepository employeeDeptRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ProjectRepository projectRepo;

	@Override
	public EmployeeDto addemployeeDetails(EmployeeDto employeeDto, int departmentId, int projectId) {
		DepartmentEntity departmentEntity = this.employeeDeptRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "DepartmentId", departmentId));

		ProjectEntity projectEntity = this.projectRepo.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project", "ProjectId", projectId));

		employeeDto.setDepartment(this.modelMapper.map(departmentEntity, DepartmentDto.class));

		employeeDto.setProject(this.modelMapper.map(projectEntity, ProjectDto.class));
		EmployeeEntity employeeEntity = this.employeeDetailsRepo.save(this.employeeDtoToEntity(employeeDto));

		return this.employeeEntityToDto(employeeEntity);
	}

	@Override
	public List<EmployeeDto> getAllEmployeeDetails() {
		List<EmployeeEntity> employeeRecords = this.employeeDetailsRepo.findAll();
		List<EmployeeDto> employeeRecordsDto = employeeRecords.stream()
				.map(employees -> this.employeeEntityToDto(employees)).collect(Collectors.toList());
		return employeeRecordsDto;
	}

	@Override
	public EmployeeDto getEmployeeDetailsById(int employeeId) {
		EmployeeEntity employeeRecord = this.employeeDetailsRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "EmployeeId", employeeId));
		return this.employeeEntityToDto(employeeRecord);
	}

	@Override
	public EmployeeDto updateEmployeeDetailsById(EmployeeDto employee, int employeeId) {
		EmployeeEntity employeeRecord = this.employeeDetailsRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "EmployeeId", employeeId));
		employeeRecord.setEmployeeId(employee.getEmployeeId());
		employeeRecord.setEmployeeFirstName(employee.getEmployeeFirstName());
		employeeRecord.setEmployeeLastName(employee.getEmployeeLastName());
		employeeRecord.setEmployeeMobileNumber(employee.getEmployeeMobileNumber());
		employeeRecord.setEmployeeEmail(employee.getEmployeeEmail());
		employeeRecord.setEmployeeeAddress(employee.getEmployeeeAddress());
		employeeRecord.setEmployeeBloodGroup(employee.getEmployeeBloodGroup());
		employeeRecord.setEmployeeDesignation(employee.getEmployeeDesignation());
		employeeRecord.setEmployeeSalary(employee.getEmployeeSalary());
		EmployeeEntity savedEmployeeRecord = this.employeeDetailsRepo.save(employeeRecord);
		return this.employeeEntityToDto(savedEmployeeRecord);
	}

	@Override
	public void deleteEmployeeDetailsById(int employeeId) {
		EmployeeEntity employeeRecord = this.employeeDetailsRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "EmployeeId", employeeId));
		this.employeeDetailsRepo.delete(employeeRecord);

	}

	public EmployeeEntity employeeDtoToEntity(EmployeeDto employeeDto) {
		return this.modelMapper.map(employeeDto, EmployeeEntity.class);
	}

	public EmployeeDto employeeEntityToDto(EmployeeEntity employeeEntity) {
		return this.modelMapper.map(employeeEntity, EmployeeDto.class);

	}

	@Override
	public List<EmployeeDto> getAllEmployeeByDept(int departmentId) {
		
		List<EmployeeEntity> employeeDetailsEntity = this.employeeDetailsRepo.getEmployeeByDepartmentId(departmentId);
System.out.println("List of Employee....."+employeeDetailsEntity);
//		DepartmentEntity deptEntity = this.employeeDeptRepository.findById(departmentId);
//				.orElseThrow(() -> new ResourceNotFoundException("Department", "DepartmentId", departmentId));
//		List<EmployeeEntity> employeeDetailsEntity = this.employeeDetailsRepo.findByDepartment(deptEntity);
		List<EmployeeDto> employeeDetailsDto = employeeDetailsEntity.stream()
				.map(employees -> this.employeeEntityToDto(employees)).collect(Collectors.toList());
		return employeeDetailsDto;
		
	}

	@Override
	public List<EmployeeDto> getAllEmployeeByProject(int projectId) {
		ProjectEntity projectEntity = this.projectRepo.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project", "ProjectId", projectId));
		List<EmployeeEntity> employeeEntity = this.employeeDetailsRepo.findByProject(projectEntity);
		List<EmployeeDto> employeeDto = employeeEntity.stream()
				.map(employees -> this.employeeEntityToDto(employees)).collect(Collectors.toList());
		return employeeDto;
	}

}
