package com.employeeApp.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeApp.Entity.DepartmentEntity;
import com.employeeApp.exception.ResourceNotFoundException;
import com.employeeApp.payload.EmployeeDepartmentDto;
import com.employeeApp.repository.EmployeeDepartmentRepo;
import com.employeeApp.service.EmployeeDepartmentService;

@Service
public class EmployeeDepartmentServiceImplementation implements EmployeeDepartmentService {
	@Autowired
	private EmployeeDepartmentRepo employeeDeptRepository;

	@Override
	public EmployeeDepartmentDto createEmployeeDept(EmployeeDepartmentDto department) {
		DepartmentEntity deptEntity = this.departmentDtoToEntity(department);
		DepartmentEntity savedDepartment = this.employeeDeptRepository.save(deptEntity);
		return this.departmentEntityToDto(savedDepartment);
	}

	@Override
	public List<EmployeeDepartmentDto> getallDepartments() {
		List<DepartmentEntity> departmentList = this.employeeDeptRepository.findAll();
		List<EmployeeDepartmentDto> deptDtoList = departmentList.stream()
				.map(departments -> this.departmentEntityToDto(departments)).collect(Collectors.toList());
		return deptDtoList;
	}

	@Override
	public EmployeeDepartmentDto getDepartmentById(int departmentId) {
		DepartmentEntity deptEntity=this.employeeDeptRepository.findById(departmentId).orElseThrow(
				()->new ResourceNotFoundException("Department", "DepartmentId", departmentId));
		return this.departmentEntityToDto(deptEntity);
	}

	@Override
	public EmployeeDepartmentDto updateDepartmentById(EmployeeDepartmentDto employeedeptDto, int departmentId) {
		DepartmentEntity departmentEntity=this.employeeDeptRepository.findById(departmentId).orElseThrow(
				()->new ResourceNotFoundException("Department", "DepartmentId", departmentId));
		//departmentEntity.setEmployeeDeptId(employeedeptDto.getDepartmentId());
		departmentEntity.setDepartmentName(employeedeptDto.getDepartmentName());
		return this.departmentEntityToDto(departmentEntity);
	}

	@Override
	public void deleteDepartmentById(int departmentId) {
		DepartmentEntity departmentEntity=this.employeeDeptRepository.findById(departmentId).orElseThrow(
				()->new ResourceNotFoundException("Department", "DepartmentId", departmentId));
		this.employeeDeptRepository.delete(departmentEntity);
	
	}

	// DtoToEntity
	public DepartmentEntity departmentDtoToEntity(EmployeeDepartmentDto employeedeptDto) {
		DepartmentEntity departmentEntity = new DepartmentEntity();
		departmentEntity.setDepartmentId(employeedeptDto.getDepartmentId());
		departmentEntity.setDepartmentName(employeedeptDto.getDepartmentName());

		return departmentEntity;

	}

	// EntityToDto
	public EmployeeDepartmentDto departmentEntityToDto(DepartmentEntity departmentEntity) {
		EmployeeDepartmentDto employeedeptDto = new EmployeeDepartmentDto();
		employeedeptDto.setDepartmentId(departmentEntity.getDepartmentId());
		employeedeptDto.setDepartmentName(departmentEntity.getDepartmentName());
		return employeedeptDto;

	}

}
