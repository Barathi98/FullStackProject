package com.employeeApp.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeApp.entity.AdminEntity;
import com.employeeApp.entity.DepartmentEntity;
import com.employeeApp.exception.ResourceNotFoundException;
import com.employeeApp.payload.AdminDto;
import com.employeeApp.payload.EmployeeDepartmentDto;
import com.employeeApp.repository.AdminRepository;
import com.employeeApp.repository.EmployeeDepartmentRepo;
import com.employeeApp.service.EmployeeDepartmentService;

@Service
public class EmployeeDepartmentServiceImplementation implements EmployeeDepartmentService {
	@Autowired
	private EmployeeDepartmentRepo employeeDeptRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EmployeeDepartmentDto createEmployeeDept(EmployeeDepartmentDto departmentDto, int adminId) {
		AdminEntity adminEntity = this.adminRepository.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin", "AdminId", adminId));

		departmentDto.setAdmin(this.modelMapper.map(adminEntity, AdminDto.class));

		DepartmentEntity savedDepartment = this.employeeDeptRepository
				.save(this.modelMapper.map(departmentDto, DepartmentEntity.class));
		return this.departmentEntityToDto(savedDepartment);
	}

	@Override
	public List<EmployeeDepartmentDto> getallDepartments() {
		List<EmployeeDepartmentDto> departmentList = this.employeeDeptRepository.findAll().stream()
				.map(departments -> this.modelMapper.map(departments, EmployeeDepartmentDto.class))
				.collect(Collectors.toList());

		return departmentList;
	}

	@Override
	public EmployeeDepartmentDto getDepartmentById(int departmentId) {
		DepartmentEntity deptEntity = this.employeeDeptRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "DepartmentId", departmentId));
		return this.modelMapper.map(deptEntity, EmployeeDepartmentDto.class);
	}

	@Override
	public EmployeeDepartmentDto updateDepartmentById(EmployeeDepartmentDto employeedeptDto, int departmentId) {
		DepartmentEntity departmentEntity = this.employeeDeptRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "DepartmentId", departmentId));
		
		departmentEntity.setDepartmentName(employeedeptDto.getDepartmentName());

		
		return this.departmentEntityToDto(departmentEntity);
	}

	@Override
	public void deleteDepartmentById(int departmentId) {
		DepartmentEntity departmentEntity = this.employeeDeptRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "DepartmentId", departmentId));
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

	@Override
	public List<EmployeeDepartmentDto> getAllDepartmentByAdmin(int adminId) {
		AdminEntity adminEntity = this.adminRepository.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin", "AdminId", adminId));
		List<DepartmentEntity> departmentList = this.employeeDeptRepository.findByAdmin(adminEntity);
		List<EmployeeDepartmentDto> deptDtoList = departmentList.stream()
				.map(department -> this.modelMapper.map(department, EmployeeDepartmentDto.class))
				.collect(Collectors.toList());
		return deptDtoList;
	}

}
