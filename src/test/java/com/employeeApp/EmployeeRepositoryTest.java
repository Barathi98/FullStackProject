package com.employeeApp;

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

import com.employeeApp.Entity.DepartmentEntity;
import com.employeeApp.Entity.EmployeeDetailsEntity;
import com.employeeApp.Entity.ProjectEntity;
import com.employeeApp.repository.EmployeeDepartmentRepo;
import com.employeeApp.repository.EmployeeDetailsRepository;
import com.employeeApp.repository.ProjectRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTest {

	@Autowired
	EmployeeDetailsRepository employeeRepository;

	@Autowired
	EmployeeDepartmentRepo departmentRepo;

	@Autowired
	ProjectRepository projectRepository;

	@Test
	@Order(1)
	public void savedEmployeeTest() {
		EmployeeDetailsEntity employee = EmployeeDetailsEntity.builder().employeeFirstName("Barathi")
				.employeeLastName("karunakaran").employeeMobileNumber("8608967899").employeeEmail("barathi@gmail.com")
				.employeeeAddress("Chennai").employeeBloodGroup("A1 -ve").employeeDesignation("Developer")
				.employeeSalary("28,000").build();
		employeeRepository.save(employee);
		Assertions.assertThat(employee.getEmployeeId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void getEmployeeByIdTest() {
		EmployeeDetailsEntity employee = employeeRepository.findById(1).get();
		Assertions.assertThat(employee.getEmployeeId()).isEqualTo(1);
	}

	@Test
	@Order(3)
	public void getAllEmployeeTest() {
		List<EmployeeDetailsEntity> employeeList = employeeRepository.findAll();
		Assertions.assertThat(employeeList.size()).isGreaterThan(0);
	}

	@Test
	@Order(4)
	public void updateEmployeeTest() {
		EmployeeDetailsEntity employee = employeeRepository.findById(1).get();
		employee.setEmployeeEmail("abc@gmail.com");
		EmployeeDetailsEntity updatedEmployee = employeeRepository.save(employee);
		Assertions.assertThat(updatedEmployee.getEmployeeId()).isEqualTo(1);

	}

	@Test
	@Order(5)
	public void deleteEmployeeTest() {
		EmployeeDetailsEntity employee = employeeRepository.findById(1).get();
		employeeRepository.delete(employee);
		EmployeeDetailsEntity employee1 = null;
		Optional<EmployeeDetailsEntity> optionalEmployee = employeeRepository.findByEmployeeEmail("Barathi@gmail.com");
		if (optionalEmployee.isPresent()) {
			employee1 = optionalEmployee.get();
		}
		Assertions.assertThat(employee1).isNull();

	}

	@Test
	@Order(6)
	public void getAllEmployeeByDept() {
		DepartmentEntity department = departmentRepo.findById(1).get();
		List<EmployeeDetailsEntity> employeeList = employeeRepository.findByDepartment(department);
		Assertions.assertThat(employeeList.size()).isGreaterThan(0);
	}

	@Test
	@Order(8)
	public void getAllEmployeeByProject() {
		ProjectEntity project = projectRepository.findById(1).get();
		List<EmployeeDetailsEntity> employeeList = employeeRepository.findByProject(project);
		Assertions.assertThat(employeeList.size()).isGreaterThan(0);
	}
}
