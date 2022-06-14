package com.employeeApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeApp.exception.ApiResponse;
import com.employeeApp.payload.ProjectDto;
import com.employeeApp.service.ProjectService;

@RestController
@RequestMapping("/api")
public class ProjectController
{
	@Autowired
	ProjectService projectService;
	
	@PostMapping("/department/{departmentId}/project")
	public ResponseEntity<ProjectDto> createProject(@Valid @PathVariable int departmentId,
			@RequestBody ProjectDto projectDto)
	{
		ProjectDto createdProject=this.projectService.
				createProject(projectDto, departmentId);
		return new ResponseEntity<ProjectDto> (createdProject,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/project/{projectId}")
	public ResponseEntity<ProjectDto> getProjectById(@PathVariable int ProjectId)
	{
		ProjectDto getProject=this.projectService.getProjectById(ProjectId);
		return new ResponseEntity<ProjectDto>(getProject,HttpStatus.OK);
		
	}
	
	@GetMapping("/projects")
	public ResponseEntity<List<ProjectDto>> getAllProject()
	{
		List <ProjectDto> getAllProject=this.projectService.getAllProject();
		return new ResponseEntity<List<ProjectDto>> (getAllProject,HttpStatus.OK);
		
	}
	
	@PutMapping("project/{projectId}")
	public ResponseEntity<ProjectDto> updateProjectById(ProjectDto projectDto,int projectId)
	{
		ProjectDto updatedProject=this.projectService.updateProjectById(projectDto, projectId);
		return new ResponseEntity<ProjectDto>(updatedProject,HttpStatus.OK);
		
	}
	
	@DeleteMapping("project/{projectId}")
    public ResponseEntity<ApiResponse> deleteProjectById(int projectId)
    {
		this.projectService.deleteProjectById(projectId);
		ApiResponse deletedProject=new ApiResponse("project is deleted successfully", true);
		return new ResponseEntity<ApiResponse> (deletedProject,HttpStatus.OK);
		
    }
}
