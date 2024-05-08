package com.example.contentnegotiation.controller;

import java.net.URI;
import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.contentnegotiation.constant.UserControllerURIs;
import com.example.contentnegotiation.dto.UserCreateDTO;
import com.example.contentnegotiation.dto.UserResponseDTO;
import com.example.contentnegotiation.model.User;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
public class UserController {
	
	@GetMapping(UserControllerURIs.FIND_ALL)
	public ResponseEntity<?> findAll(HttpServletRequest req) {

		return ResponseEntity.ok(Arrays.asList(
			new UserResponseDTO(1L, "John", "Doe", 41, "john.doe@mail.com", true),
			new UserResponseDTO(2L, "Mary", "Jane", 35, "mary.jane@mail.com", true)
		));
	}

	@PostMapping(UserControllerURIs.CREATE)
	public ResponseEntity<?> create(
		HttpServletRequest req, 
		@RequestBody UserCreateDTO dto, 
		UriComponentsBuilder uBuilder
	) {

		// Convert from UserCreateDTO to User, with a new ID
		User user = new User(
			1L, 
			dto.firstName(), 
			dto.lastName(), 
			dto.age(), 
			dto.email(), 
			dto.isActive()
		);

		// Convert from User to UserResponseDTO
		UserResponseDTO response = new UserResponseDTO(
			user.getId(), 
			user.getFirstName(), 
			user.getLastName(), 
			user.getAge(), 
			user.getEmail(), 
			user.getIsActive()
		);

		URI uri = uBuilder.path(UserControllerURIs.FIND_ALL).build().toUri();

		return ResponseEntity.created(uri).body(response);
	}
	
}
