package com.employeeapp.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeapp.entity.DepartmentEntity;
import com.employeeapp.entity.ProjectEntity;
import com.employeeapp.exception.ResourceNotFoundException;
import com.employeeapp.payload.DepartmentDto;
import com.employeeapp.payload.ProjectDto;
import com.employeeapp.repository.DepartmentRepository;
import com.employeeapp.repository.EmployeeRepository;
import com.employeeapp.repository.ProjectRepository;
import com.employeeapp.service.ProjectService;

@Service
public class ProjectServiceImplementation implements ProjectService {
	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public ProjectDto createProject(ProjectDto projectDto, int departmentId) {
		DepartmentEntity departmentEntity = this.departmentRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "DepartmentId", departmentId));

		projectDto.setDepartment(this.modelMapper.map(departmentEntity, DepartmentDto.class));

		ProjectEntity savedProject = this.projectRepository.save(this.projectDtoToEntity(projectDto));

		return this.projectEntityToDto(savedProject);
	}

	@Override
	public ProjectDto getProjectById(int projectId) {
		ProjectEntity projectEntity = this.projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project", "ProjectId", projectId));

		return this.projectEntityToDto(projectEntity);
	}

	@Override
	public List<ProjectDto> getAllProject() {
		List<ProjectDto> getAllProjects = this.projectRepository.findAll().stream()
				.map(project -> this.projectEntityToDto(project)).collect(Collectors.toList());
		return getAllProjects;
	}

	@Override
	public ProjectDto updateProjectById(ProjectDto projectDto, int projectId) {

		ProjectEntity projectEntity = this.projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project", "ProjectId", projectId));

		ProjectEntity updatedProject = this.projectRepository.save(this.projectDtoToEntity(projectDto));
		return this.projectEntityToDto(updatedProject);
	}

	@Override
	public void deleteProjectById(int projectId) {
		ProjectEntity projectEntity = this.projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project", "ProjectId", projectId));
		this.projectRepository.delete(projectEntity);
	}

	public ProjectEntity projectDtoToEntity(ProjectDto projectDto) {
		return this.modelMapper.map(projectDto, ProjectEntity.class);

	}

	public ProjectDto projectEntityToDto(ProjectEntity projectEntity) {
		return this.modelMapper.map(projectEntity, ProjectDto.class);

	}

	@Override
	public List<ProjectDto> getProjectByDepartment(int departmentId) {
		List<ProjectEntity> projectEntityList=this.projectRepository.getProjectByDepartmentId(departmentId);
        System.out.println("Project list.."+projectEntityList);
		//		DepartmentEntity deptEntity = this.deparmentRepo.findById(departmentId)
//				.orElseThrow(() -> new ResourceNotFoundException("Department", "DepartmentId", departmentId));
//		List<ProjectEntity> projectEntityList = this.projectRepo.findByDepartment(deptEntity);
		List<ProjectDto> projectDtoList = projectEntityList.stream().map(projects -> this.projectEntityToDto(projects))
				.collect(Collectors.toList());
		return projectDtoList;
	}

}
