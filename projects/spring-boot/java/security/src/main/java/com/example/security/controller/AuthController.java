package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.constant.AuthControllerURIs;
import com.example.security.dto.request.LoginRequestDTO;
import com.example.security.service.AuthService;

import jakarta.validation.Valid;

@RestController
public class AuthController {
	
	// Dependencies

	@Autowired
	private AuthService authService;

	// Methods

	@PostMapping(AuthControllerURIs.login)
	public ResponseEntity<?> login(
		@RequestBody @Valid LoginRequestDTO dto
	) {
		return authService.login(dto);
	}
}
