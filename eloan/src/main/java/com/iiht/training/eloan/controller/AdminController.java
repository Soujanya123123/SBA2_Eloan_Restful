package com.iiht.training.eloan.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.training.eloan.dto.UserDto;
import com.iiht.training.eloan.dto.exception.LoanException;
import com.iiht.training.eloan.exception.InvalidDataException;
import com.iiht.training.eloan.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/register-clerk")
	public ResponseEntity<UserDto> registerClerk(@Valid @RequestBody UserDto userDto, BindingResult bindingResult)
			throws InvalidDataException, LoanException {

		if (bindingResult.hasErrors()) {
			throw new InvalidDataException("Clerk details provided are invalid");
		}
		return new ResponseEntity<UserDto>(adminService.registerClerk(userDto), HttpStatus.OK);
	}

	@PostMapping("/register-manager")
	public ResponseEntity<UserDto> registerManager(@Valid @RequestBody UserDto userDto, BindingResult bindingResult)
			throws InvalidDataException, LoanException {
		if (bindingResult.hasErrors()) {
			throw new InvalidDataException("Manager details provided are invalid");
		}
		return new ResponseEntity<UserDto>(adminService.registerManager(userDto), HttpStatus.OK);
	}

	@GetMapping("/all-clerks")
	public ResponseEntity<List<UserDto>> getAllClerks() throws LoanException {
		return new ResponseEntity<List<UserDto>>(adminService.getAllClerks(), HttpStatus.OK);
	}

	@GetMapping("/all-managers")
	public ResponseEntity<List<UserDto>> getAllManagers() throws LoanException {
		return new ResponseEntity<List<UserDto>>(adminService.getAllManagers(), HttpStatus.OK);
	}

}