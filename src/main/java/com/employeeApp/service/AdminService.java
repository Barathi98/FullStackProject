package com.employeeapp.service;

import java.util.List;

import com.employeeapp.payload.AdminDto;

public interface AdminService {
	public AdminDto createAdmin(AdminDto admin);

	public AdminDto getAdmin(int adminId);

	public AdminDto updateAdminById(AdminDto adminDto, int adminId);

	public void deleteAdminbyId(int adminId);

	public List<AdminDto> getAllAdmin();

}
