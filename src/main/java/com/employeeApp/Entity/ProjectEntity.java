package com.employeeApp.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "project")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProjectEntity {
	@Id
	@SequenceGenerator(name = "project", initialValue = 401)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int projectId;

	@Column
	@NotEmpty(message = "Project title cannot be empty")
	@Size(min = 4, max = 50)
	private String projectTitle;

	@Column
	@NotEmpty(message = "Project lead name cannot be empty")
	@Size(min = 4, max = 20)
	private String projectLeadName;

//  @Column
//  @NotNull
//  private int TeamSize;

	@ManyToOne
//  @JsonBackReference
	private DepartmentEntity department;

	@OneToMany(cascade = CascadeType.ALL)
	private List<EmployeeDetailsEntity> employeeDetails = new ArrayList<EmployeeDetailsEntity>();
}
