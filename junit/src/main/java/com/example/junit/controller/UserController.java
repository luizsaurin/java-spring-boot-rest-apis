package com.example.junit.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.junit.dto.UserCreationDto;
import com.example.junit.dto.UserDetailsDto;
import com.example.junit.dto.UserUpdateDto;
import com.example.junit.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired private UserService service;

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pagination) {
		return ResponseEntity.ok(service.findAll(pagination));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> create(
		@RequestBody @Valid UserCreationDto dto, 
		UriComponentsBuilder uBuilder
	) {
		
		UserDetailsDto userDto = service.create(dto);
		
		URI uri = uBuilder.path("/users/{id}").buildAndExpand(userDto.id()).toUri();
		
		return ResponseEntity.created(uri).body(userDto);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> update(
		@PathVariable Long id, 
		@RequestBody UserUpdateDto dto
	) {
		return ResponseEntity.ok(service.update(id, dto));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
