package com.employeeApp.service;

import java.util.List;

import com.employeeApp.payload.AdminDto;

public interface AdminService {
	public AdminDto createAdmin(AdminDto admin);

	public AdminDto getAdmin(int adminId);

	public AdminDto updateAdminById(AdminDto adminDto, int adminId);

	public void deleteAdminbyId(int adminId);

	public List<AdminDto> getAllAdmin();

}
