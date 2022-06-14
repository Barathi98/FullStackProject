package com.employeeApp.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectEntity 
{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int projectId;
  
  @Column
  @NotEmpty(message="Project title cannot be empty")
  @Size(min=4,max=50)
  private String projectTitle;
  
  @Column
  @NotEmpty(message="Project lead name cannot be empty")
  @Size(min=4,max=20)
  private String projectLeadName;
  
  @Column
  private int TeamSize;
  
  @ManyToOne
  private DepartmentEntity department;
  
  @OneToMany(cascade = CascadeType.ALL)
  private List<EmployeeDetailsEntity> employee=new ArrayList<>();
  }
