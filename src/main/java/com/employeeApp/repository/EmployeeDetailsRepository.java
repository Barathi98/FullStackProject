package com.employeeApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeApp.Entity.DepartmentEntity;
import com.employeeApp.Entity.EmployeeDetailsEntity;
import com.employeeApp.Entity.ProjectEntity;

public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetailsEntity, Integer> 
{
	 List<EmployeeDetailsEntity> findByDepartment(DepartmentEntity department); 
	 List<EmployeeDetailsEntity> findByProject(ProjectEntity project);
}
