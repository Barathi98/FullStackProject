package com.employeeapp.controller;

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

import com.employeeapp.exception.ApiResponse;
import com.employeeapp.payload.ProjectDto;
import com.employeeapp.service.ProjectService;

@RestController
@RequestMapping("/api")
public class ProjectController {
	@Autowired
	ProjectService projectService;

	// To create a project by parent department Id
	@PostMapping("/department/{departmentId}/project")
	public ResponseEntity<ProjectDto> createProject(@Valid @PathVariable int departmentId,
			@RequestBody ProjectDto projectDto) {
		ProjectDto createdProject = this.projectService.createProject(projectDto, departmentId);
		return new ResponseEntity<ProjectDto>(createdProject, HttpStatus.CREATED);

	}

	// To fetch a project by Id
	@GetMapping("/project/{projectId}")
	public ResponseEntity<ProjectDto> getProjectById(@PathVariable int projectId) {
		ProjectDto getProject = this.projectService.getProjectById(projectId);
		return new ResponseEntity<ProjectDto>(getProject, HttpStatus.OK);

	}

	// To fetch all projects
	@GetMapping("/projects")
	public ResponseEntity<List<ProjectDto>> getAllProject() {
		List<ProjectDto> getAllProject = this.projectService.getAllProject();
		return new ResponseEntity<List<ProjectDto>>(getAllProject, HttpStatus.OK);

	}

	// To update a project by Id
	@PutMapping("/department/{departmentId}/project/{projectId}")
	public ResponseEntity<ProjectDto> updateProjectById(@RequestBody ProjectDto projectDto,
			@PathVariable int projectId) {
		ProjectDto updatedProject = this.projectService.updateProjectById(projectDto, projectId);
		return new ResponseEntity<ProjectDto>(updatedProject, HttpStatus.OK);

	}

	// To delete a project by Id
	@DeleteMapping("/project/{projectId}")
	public ResponseEntity<ApiResponse> deleteProjectById(@PathVariable int projectId) {
		this.projectService.deleteProjectById(projectId);
		ApiResponse deletedProject = new ApiResponse("project is deleted successfully", true);
		return new ResponseEntity<ApiResponse>(deletedProject, HttpStatus.OK);

	}

	// To fetch all the projects by department Id
	@GetMapping("/project/department/{departmentId}")
	public ResponseEntity<List<ProjectDto>> getAllEmployeeByDept(@PathVariable int departmentId) {
		List<ProjectDto> getAllEmployeeByDept = this.projectService.getProjectByDepartment(departmentId);
		return new ResponseEntity<List<ProjectDto>>(getAllEmployeeByDept, HttpStatus.OK);

	}

}
