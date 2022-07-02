package com.employeeapp;

import java.util.List;
import java.util.Optional;

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

import com.employeeapp.entity.DepartmentEntity;
import com.employeeapp.entity.EmployeeEntity;
import com.employeeapp.entity.ProjectEntity;
import com.employeeapp.repository.DepartmentRepository;
import com.employeeapp.repository.EmployeeRepository;
import com.employeeapp.repository.ProjectRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTest {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Test
	@Order(1)
	public void savedEmployeeTest() {
		EmployeeEntity employee = EmployeeEntity.builder().employeeFirstName("Barathi")
				.employeeLastName("karunakaran").employeeMobileNumber("8608967899").employeeEmail("barathi@gmail.com")
				.employeeeAddress("Chennai").employeeBloodGroup("A1 -ve").employeeDesignation("Developer")
				.employeeSalary("28,000").build();
		employeeRepository.save(employee);
		Assertions.assertThat(employee.getEmployeeId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void getEmployeeByIdTest() {
		EmployeeEntity employee = employeeRepository.findById(1).get();
		Assertions.assertThat(employee.getEmployeeId()).isEqualTo(1);
	}

	@Test
	@Order(3)
	public void getAllEmployeeTest() {
		List<EmployeeEntity> employeeList = employeeRepository.findAll();
		Assertions.assertThat(employeeList.size()).isGreaterThan(0);
	}

	@Test
	@Order(4)
	public void updateEmployeeTest() {
		EmployeeEntity employee = employeeRepository.findById(1).get();
		employee.setEmployeeEmail("abc@gmail.com");
		EmployeeEntity updatedEmployee = employeeRepository.save(employee);
		Assertions.assertThat(updatedEmployee.getEmployeeId()).isEqualTo(1);

	}

	@Test
	@Order(5)
	public void deleteEmployeeTest() {
		EmployeeEntity employee = employeeRepository.findById(1).get();
		employeeRepository.delete(employee);
		EmployeeEntity employee1 = null;
		Optional<EmployeeEntity> optionalEmployee = employeeRepository.findByEmployeeEmail("Barathi@gmail.com");
		if (optionalEmployee.isPresent()) {
			employee1 = optionalEmployee.get();
		}
		Assertions.assertThat(employee1).isNull();

	}

	@Test
	@Order(6)
	public void getAllEmployeeByDept() {
		DepartmentEntity department = departmentRepository.findById(1).get();
		List<EmployeeEntity> employeeList = employeeRepository.findByDepartment(department);
		Assertions.assertThat(employeeList.size()).isGreaterThan(0);
	}

	@Test
	@Order(8)
	public void getAllEmployeeByProject() {
		ProjectEntity project = projectRepository.findById(1).get();
		List<EmployeeEntity> employeeList = employeeRepository.findByProject(project);
		Assertions.assertThat(employeeList.size()).isGreaterThan(0);
	}
}
