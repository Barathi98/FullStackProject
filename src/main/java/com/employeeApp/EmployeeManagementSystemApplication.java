package com.employeeApp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}
   
	@Bean
	ModelMapper getModelMapper()
	{
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper;
	}
}
