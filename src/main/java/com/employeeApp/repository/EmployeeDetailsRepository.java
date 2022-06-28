package com.employeeApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeApp.entity.DepartmentEntity;
import com.employeeApp.entity.EmployeeDetailsEntity;
import com.employeeApp.entity.ProjectEntity;

public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetailsEntity, Integer> {
	List<EmployeeDetailsEntity> findByDepartment(DepartmentEntity department);

	List<EmployeeDetailsEntity> findByProject(ProjectEntity project);

	Optional<EmployeeDetailsEntity> findByEmployeeEmail(String employeeEmail);
}
