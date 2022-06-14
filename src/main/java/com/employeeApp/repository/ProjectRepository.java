package com.employeeApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeApp.Entity.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer>
{

}
