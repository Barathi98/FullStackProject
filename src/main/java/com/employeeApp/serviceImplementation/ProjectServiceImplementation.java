package com.employeeApp.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeApp.entity.DepartmentEntity;
import com.employeeApp.entity.EmployeeDetailsEntity;
import com.employeeApp.entity.ProjectEntity;
import com.employeeApp.exception.ResourceNotFoundException;
import com.employeeApp.payload.EmployeeDepartmentDto;
import com.employeeApp.payload.EmployeeDetailsDto;
import com.employeeApp.payload.ProjectDto;
import com.employeeApp.repository.EmployeeDepartmentRepo;
import com.employeeApp.repository.EmployeeDetailsRepository;
import com.employeeApp.repository.ProjectRepository;
import com.employeeApp.service.ProjectService;

@Service
public class ProjectServiceImplementation implements ProjectService {
	@Autowired
	ProjectRepository projectRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	EmployeeDepartmentRepo deparmentRepo;

	@Autowired
	EmployeeDetailsRepository employeeRepository;

	@Override
	public ProjectDto createProject(ProjectDto projectDto, int departmentId) {
		DepartmentEntity departmentEntity = this.deparmentRepo.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "DepartmentId", departmentId));

		projectDto.setDepartment(this.modelMapper.map(departmentEntity, EmployeeDepartmentDto.class));

		ProjectEntity savedProject = this.projectRepo.save(this.projectDtoToEntity(projectDto));

		return this.projectEntityToDto(savedProject);
	}

	@Override
	public ProjectDto getProjectById(int projectId) {
		ProjectEntity projectEntity = this.projectRepo.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project", "ProjectId", projectId));

		return this.projectEntityToDto(projectEntity);
	}

	@Override
	public List<ProjectDto> getAllProject() {
		List<ProjectDto> getAllProjects = this.projectRepo.findAll().stream()
				.map(project -> this.projectEntityToDto(project)).collect(Collectors.toList());
		return getAllProjects;
	}

	@Override
	public ProjectDto updateProjectById(ProjectDto projectDto, int projectId) {

		ProjectEntity projectEntity = this.projectRepo.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project", "ProjectId", projectId));
//
//		projectEntity.setProjectTitle(projectDto.getProjectTitle());
//		projectEntity.setProjectLeadName(projectDto.getProjectLeadName());
		ProjectEntity updatedProject = this.projectRepo.save(this.projectDtoToEntity(projectDto));
		return this.projectEntityToDto(updatedProject);
	}

	@Override
	public void deleteProjectById(int projectId) {
		ProjectEntity projectEntity = this.projectRepo.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project", "ProjectId", projectId));
		this.projectRepo.delete(projectEntity);
	}

	public ProjectEntity projectDtoToEntity(ProjectDto projectDto) {
		return this.modelMapper.map(projectDto, ProjectEntity.class);

	}

	public ProjectDto projectEntityToDto(ProjectEntity projectEntity) {
		return this.modelMapper.map(projectEntity, ProjectDto.class);

	}

	@Override
	public List<ProjectDto> getProjectByDepartment(int departmentId) {
		DepartmentEntity deptEntity = this.deparmentRepo.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "DepartmentId", departmentId));
		List<ProjectEntity> projectEntityList = this.projectRepo.findByDepartment(deptEntity);
		List<ProjectDto> projectDtoList = projectEntityList.stream().map(projects -> this.projectEntityToDto(projects))
				.collect(Collectors.toList());
		return projectDtoList;
	}

}
