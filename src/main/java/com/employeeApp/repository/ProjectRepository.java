package com.employeeApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeApp.Entity.DepartmentEntity;
import com.employeeApp.Entity.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer>
{
	 List<ProjectEntity> findByDepartment(DepartmentEntity department);
}
