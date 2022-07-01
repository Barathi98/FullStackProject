package com.employeeapp.service;

import java.util.List;

import com.employeeapp.payload.ProjectDto;



public interface ProjectService {
	public ProjectDto createProject(ProjectDto projectDto, int departmentId);

	public ProjectDto getProjectById(int projectId);

	public List<ProjectDto> getAllProject();

	public ProjectDto updateProjectById(ProjectDto projectDto, int projectId);

	public void deleteProjectById(int projectId);

	public List<ProjectDto> getProjectByDepartment(int departmentId);

}
