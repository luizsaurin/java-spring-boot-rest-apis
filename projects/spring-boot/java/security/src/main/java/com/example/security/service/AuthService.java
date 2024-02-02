package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.security.dto.request.LoginRequestDTO;
import com.example.security.dto.response.LoginResponseDTO;
import com.example.security.model.User;

@Service
public class AuthService {

	// Dependencies

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	// Methods

	public ResponseEntity<?> login(LoginRequestDTO dto) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
		Authentication auth = authManager.authenticate(authToken);
		String userToken = tokenService.generateToken((User) auth.getPrincipal());
		return new ResponseEntity<LoginResponseDTO>(new LoginResponseDTO(userToken, "Bearer"), HttpStatus.OK);
	}
	
}
