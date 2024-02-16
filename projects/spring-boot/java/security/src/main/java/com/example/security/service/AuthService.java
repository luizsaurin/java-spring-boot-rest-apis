package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.security.dto.request.AuthRequestDTO;

@Service
public class AuthService {

	// Dependencies

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	// Methods

	public ResponseEntity<?> login(AuthRequestDTO dto) {

		Authentication auth = authManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				dto.username(), 
				dto.password()
			)
		);

		return ResponseEntity.ok(
			tokenService.resolveAuthLogin(auth)
		);
	}

	public ResponseEntity<?> refresh(String refreshToken) {

		return ResponseEntity.ok(
			tokenService.resolveAuthRefresh(refreshToken)
		);

	}
	
}
