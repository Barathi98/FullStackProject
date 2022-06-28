package com.employeeApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeApp.entity.AdminEntity;
import com.employeeApp.entity.DepartmentEntity;

public interface EmployeeDepartmentRepo extends JpaRepository<DepartmentEntity, Integer> {
	List<DepartmentEntity> findByAdmin(AdminEntity admin);
}
