package com.employeeapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employeeapp.entity.DepartmentEntity;
import com.employeeapp.entity.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
	List<ProjectEntity> findByDepartment(DepartmentEntity department);
	
@Query(value = "SELECT * FROM project project WHERE project. department_department_id = :deptid",nativeQuery = true)
	
	public List<ProjectEntity> getProjectByDepartmentId( @Param("deptid") int deptid);
	Optional<ProjectEntity> findByProjectTitle(String projectTitle);
}
