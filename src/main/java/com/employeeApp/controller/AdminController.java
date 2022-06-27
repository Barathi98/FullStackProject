package com.employeeApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeApp.exception.ApiResponse;
import com.employeeApp.payload.AdminDto;
import com.employeeApp.service.AdminService;

@RestController
@RequestMapping("/api")
public class AdminController {
	@Autowired
	AdminService adminService;

	// To create a admin resources
	@PostMapping("/admin")
	public ResponseEntity<AdminDto> createAdmin(@Valid @RequestBody AdminDto adminDto) {
		AdminDto createAdmin = this.adminService.createAdmin(adminDto);
		return new ResponseEntity<AdminDto>(createAdmin, HttpStatus.CREATED);

	}

	// To fetch a particular admin record by admin Id
	@GetMapping("/admin/{adminId}")
	public ResponseEntity<AdminDto> getAdmin(@PathVariable int adminId) {
		AdminDto getAdmin = this.adminService.getAdmin(adminId);
		return new ResponseEntity<AdminDto>(getAdmin, HttpStatus.OK);
	}

	// To update a admin record by admin id
	@PutMapping("/admin/{adminId}")
	public ResponseEntity<AdminDto> updateAdmin(@RequestBody AdminDto adminDto, @PathVariable int adminId) {
		AdminDto updatedAdmin = this.adminService.updateAdminById(adminDto, adminId);
		return new ResponseEntity<AdminDto>(updatedAdmin, HttpStatus.OK);
	}

	// To delete admin by Id
	@DeleteMapping("/admin/{adminId}")
	public ResponseEntity<ApiResponse> deleteAdmin(@PathVariable int adminId) {
		this.adminService.deleteAdminbyId(adminId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Admin details are successfully deleted", true),
				HttpStatus.OK);

	}

	// To fetch all the admin records as a List
	@GetMapping("/admins")
	public ResponseEntity<List<AdminDto>> getAllAdmin() {
		List<AdminDto> adminList = this.adminService.getAllAdmin();
		return new ResponseEntity<List<AdminDto>>(adminList, HttpStatus.OK);
	}
}
