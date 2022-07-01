package com.employeeapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employeeapp.entity.DepartmentEntity;
import com.employeeapp.entity.EmployeeEntity;
import com.employeeapp.entity.ProjectEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
	List<EmployeeEntity> findByDepartment(DepartmentEntity department);
	
@Query(value = "SELECT * FROM employee emp WHERE emp.department_department_id = :department_department_id",nativeQuery = true)
	
	public List<EmployeeEntity> getEmployeeByDepartmentId( @Param("department_department_id") int department_department_id);
	

	List<EmployeeEntity> findByProject(ProjectEntity project);

	Optional<EmployeeEntity> findByEmployeeEmail(String employeeEmail);
}
