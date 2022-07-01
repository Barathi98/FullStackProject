package com.employeeapp;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.employeeapp.entity.AdminEntity;
import com.employeeapp.entity.DepartmentEntity;
import com.employeeapp.repository.AdminRepository;
import com.employeeapp.repository.DepartmentRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class DepartmentRepositoryTest {
	@Autowired
	private DepartmentRepository departmentRepo;

	@Autowired
	private AdminRepository adminRepository;

	@Test
	@Order(1)
	public void savedDepartmentTest() {
		DepartmentEntity department = DepartmentEntity.builder().departmentName("developer").build();
		departmentRepo.save(department);
		Assertions.assertThat(department.getDepartmentId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void getByIdDepartmentTest() {
		DepartmentEntity department = departmentRepo.findById(1).get();
		Assertions.assertThat(department);
	}

	@Test
	@Order(3)
	public void getAllDepartmentTest() {
		List<DepartmentEntity> departmentList = departmentRepo.findAll();
		Assertions.assertThat(departmentList.size()).isGreaterThan(0);
	}

	@Test
	@Order(4)
	public void getDepartmentByIdTest() {
		DepartmentEntity department = departmentRepo.findById(1).get();
		Assertions.assertThat(department.getDepartmentId()).isEqualTo(1);
	}

	@Test
	@Order(5)
	public void updateAdminTest() {
		DepartmentEntity department = departmentRepo.findById(1).get();
		department.setDepartmentName("quality");
		DepartmentEntity updatedDepartment = departmentRepo.save(department);
		Assertions.assertThat(updatedDepartment.getDepartmentId()).isEqualTo(1);
	}

	@Test
	@Order(6)
	public void getDepartmentsByAdminId() {
		AdminEntity admin = adminRepository.findById(1).get();
		List<DepartmentEntity> departmentList = departmentRepo.findByAdmin(admin);
		Assertions.assertThat(departmentList.size()).isGreaterThan(0);
	}

}
