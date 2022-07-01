package com.employeeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeapp.entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {

}
