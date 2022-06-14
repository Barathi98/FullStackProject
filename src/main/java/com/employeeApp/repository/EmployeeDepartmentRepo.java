package com.employeeApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeApp.Entity.DepartmentEntity;

public interface EmployeeDepartmentRepo extends JpaRepository<DepartmentEntity, Integer> {

}
