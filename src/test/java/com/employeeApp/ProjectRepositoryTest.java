package com.employeeApp;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.employeeApp.entity.DepartmentEntity;
import com.employeeApp.entity.ProjectEntity;
import com.employeeApp.repository.EmployeeDepartmentRepo;
import com.employeeApp.repository.ProjectRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectRepositoryTest {
	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	EmployeeDepartmentRepo departmentRepo;

	@Test
	@Order(1)
	public void savedProjectTest() {
		ProjectEntity project = ProjectEntity.builder().projectTitle("EmployeeManagementSystem")
				.projectLeadName("Barathi").build();
		projectRepository.save(project);
	}

	@Test
	@Order(2)
	public void getProjectByIdTest() {
		ProjectEntity project = projectRepository.findById(1).get();
		Assertions.assertThat(project.getProjectId()).isEqualTo(1);
	}

	@Test
	@Order(3)
	public void getAllProjects() {
		List<ProjectEntity> projectList = projectRepository.findAll();
		Assertions.assertThat(projectList.size()).isGreaterThan(0);
	}

	@Test
	@Order(4)
	public void updateProjectByIdTest() {
		ProjectEntity project = projectRepository.findById(1).get();
		project.setProjectTitle("placementManagementSystem");
		ProjectEntity updatedProject = projectRepository.save(project);
		Assertions.assertThat(updatedProject.getProjectId()).isEqualTo(1);
	}
	
	@Test
	@Order(5)
	public void getAllProjectByDeptTest() {
		DepartmentEntity department = departmentRepo.findById(1).get();
		List<ProjectEntity> projectList = projectRepository.findByDepartment(department);
		Assertions.assertThat(projectList.size()).isGreaterThan(0);
	}
}
