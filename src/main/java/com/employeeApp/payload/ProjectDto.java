package com.employeeApp.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectDto {
	private int projectId;

	private String projectTitle;

	private String ProjectLeadName;

	private EmployeeDepartmentDto department;

	private List<EmployeeDetailsDto> employeeDetails;

}
