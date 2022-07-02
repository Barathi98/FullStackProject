package com.employeeapp.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeapp.entity.AdminEntity;
import com.employeeapp.entity.DepartmentEntity;
import com.employeeapp.exception.ResourceNotFoundException;
import com.employeeapp.payload.AdminDto;
import com.employeeapp.payload.DepartmentDto;
import com.employeeapp.repository.AdminRepository;
import com.employeeapp.repository.DepartmentRepository;
import com.employeeapp.service.DepartmentService;

@Service
public class DepartmentServiceImplementation implements DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DepartmentDto createEmployeeDept(DepartmentDto departmentDto, int adminId) {
		AdminEntity adminEntity = this.adminRepository.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin", "AdminId", adminId));

		departmentDto.setAdmin(this.modelMapper.map(adminEntity, AdminDto.class));

		DepartmentEntity savedDepartment = this.departmentRepository
				.save(this.modelMapper.map(departmentDto, DepartmentEntity.class));
		return this.departmentEntityToDto(savedDepartment);
	}

	@Override
	public List<DepartmentDto> getallDepartments() {
		List<DepartmentDto> departmentList = this.departmentRepository.findAll().stream()
				.map(departments -> this.modelMapper.map(departments, DepartmentDto.class))
				.collect(Collectors.toList());

		return departmentList;
	}

	@Override
	public DepartmentDto getDepartmentById(int departmentId) {
		DepartmentEntity deptEntity = this.departmentRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "DepartmentId", departmentId));
		return this.modelMapper.map(deptEntity, DepartmentDto.class);
	}

	@Override
	public DepartmentDto updateDepartmentById(DepartmentDto employeedeptDto, int departmentId) {
		DepartmentEntity departmentEntity = this.departmentRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "DepartmentId", departmentId));

		departmentEntity.setDepartmentName(employeedeptDto.getDepartmentName());

		return this.departmentEntityToDto(departmentEntity);
	}

	@Override
	public void deleteDepartmentById(int departmentId) {
		DepartmentEntity departmentEntity = this.departmentRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "DepartmentId", departmentId));
		this.departmentRepository.delete(departmentEntity);

	}

	// DtoToEntity
	public DepartmentEntity departmentDtoToEntity(DepartmentDto employeedeptDto) {
		DepartmentEntity departmentEntity = new DepartmentEntity();
		departmentEntity.setDepartmentId(employeedeptDto.getDepartmentId());
		departmentEntity.setDepartmentName(employeedeptDto.getDepartmentName());

		return departmentEntity;

	}

	// EntityToDto
	public DepartmentDto departmentEntityToDto(DepartmentEntity departmentEntity) {
		DepartmentDto employeedeptDto = new DepartmentDto();
		employeedeptDto.setDepartmentId(departmentEntity.getDepartmentId());
		employeedeptDto.setDepartmentName(departmentEntity.getDepartmentName());
		return employeedeptDto;

	}

	@Override
	public List<DepartmentDto> getAllDepartmentByAdmin(int adminId) {
		List<DepartmentEntity> departmentList = this.departmentRepository.getEmployeeByDepartmentId(adminId);
		System.out.println(departmentList);
		// AdminEntity adminEntity = this.adminRepository.findById(adminId)
//				.orElseThrow(() -> new ResourceNotFoundException("Admin", "AdminId", adminId));
//		List<DepartmentEntity> departmentList = this.employeeDeptRepository.findByAdmin(adminEntity);
		List<DepartmentDto> deptDtoList = departmentList.stream()
				.map(department -> this.modelMapper.map(department, DepartmentDto.class)).collect(Collectors.toList());
		return deptDtoList;
	}

}
