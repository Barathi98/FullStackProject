package com.employeeApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeApp.Entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {

}