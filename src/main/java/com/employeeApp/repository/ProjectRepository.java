package com.employeeApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeApp.entity.DepartmentEntity;
import com.employeeApp.entity.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
	List<ProjectEntity> findByDepartment(DepartmentEntity department);
	Optional<ProjectEntity> findByProjectTitle(String projectTitle);
}
