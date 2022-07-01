package com.employeeapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employeeapp.entity.AdminEntity;
import com.employeeapp.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {
	List<DepartmentEntity> findByAdmin(AdminEntity admin);
	
@Query(value = "SELECT * FROM department dept WHERE dept.admin_admin_id = :adminId",nativeQuery = true)
	
	public List<DepartmentEntity> getEmployeeByDepartmentId( @Param("adminId") int adminId);
}
