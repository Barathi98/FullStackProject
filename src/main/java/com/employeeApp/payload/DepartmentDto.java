package com.employeeapp.payload;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentDto {

	private int DepartmentId;

	private String DepartmentName;

	private List<EmployeeDto> employee;

	private List<ProjectDto> project;

	private AdminDto admin;

}
