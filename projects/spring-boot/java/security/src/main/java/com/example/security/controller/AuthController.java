package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.constant.AuthControllerURIs;
import com.example.security.constant.SecurityConstants;
import com.example.security.dto.request.AuthRequestDTO;
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
		@RequestBody @Valid AuthRequestDTO dto
	) {
		return authService.login(dto);
	}

	@PutMapping(AuthControllerURIs.refresh)
	public ResponseEntity<?> refresh(
		@RequestHeader(SecurityConstants.AUTHORIZATION) String refreshToken
	) {
		return authService.refresh(refreshToken);
	}
}
