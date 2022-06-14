package com.employeeApp.service;

import java.util.List;

import com.employeeApp.payload.ProjectDto;

public interface ProjectService 
{
  public ProjectDto createProject(ProjectDto projectDto,int departmentId);
  
  public ProjectDto getProjectById(int projectId);
  
  public List<ProjectDto> getAllProject();
  
  public ProjectDto updateProjectById(ProjectDto projectDto,int projectId);
  
  public void deleteProjectById(int projectId);
}
