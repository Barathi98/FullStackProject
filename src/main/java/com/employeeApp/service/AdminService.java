package com.employeeApp.service;

import com.employeeApp.payload.AdminDto;

public interface AdminService 
{
  public AdminDto createAdmin(AdminDto admin);
  
  public AdminDto getAdmin(int adminId);
  
  public AdminDto updateAdminById(AdminDto adminDto,int adminId);
  
  public void deleteAdminbyId(int adminId);
  
  
}
