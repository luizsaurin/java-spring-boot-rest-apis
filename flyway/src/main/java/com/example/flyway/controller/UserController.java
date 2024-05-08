package com.example.flyway.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.flyway.constant.UserControllerURIs;
import com.example.flyway.dto.UserCreationDto;
import com.example.flyway.dto.UserDetailsDto;
import com.example.flyway.dto.UserUpdateDto;
import com.example.flyway.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	// Dependencies

	@Autowired
	private UserService service;

	// Methods

	@GetMapping(UserControllerURIs.FIND_BY_ID)
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping(UserControllerURIs.FIND_ALL)
	public ResponseEntity<?> findAll(
		@PageableDefault(
			sort = "id", 
			direction = Direction.DESC, 
			page = 0, 
			size = 10
		) Pageable pagination
	) {
		return ResponseEntity.ok(service.findAll(pagination));
	}

	@Transactional
	@PostMapping(UserControllerURIs.CREATE)
	public ResponseEntity<?> create(
		@RequestBody @Valid UserCreationDto dto, 
		UriComponentsBuilder uBuilder
	) {
		UserDetailsDto responseDto = service.create(dto);
		URI uri = uBuilder.path(UserControllerURIs.FIND_BY_ID).buildAndExpand(responseDto.id()).toUri();
		return ResponseEntity.created(uri).body(responseDto);
	}

	@Transactional
	@PutMapping(UserControllerURIs.UPDATE)
	public ResponseEntity<?> update(
		@PathVariable Long id, 
		@RequestBody UserUpdateDto dto
	) {
		return ResponseEntity.ok(service.update(id, dto));
	}

	@Transactional
	@DeleteMapping(UserControllerURIs.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
