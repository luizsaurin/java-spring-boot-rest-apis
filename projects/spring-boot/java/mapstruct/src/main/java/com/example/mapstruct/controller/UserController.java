package com.example.mapstruct.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.mapstruct.dto.UserCreationDto;
import com.example.mapstruct.dto.UserUpdateDto;
import com.example.mapstruct.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired private UserService service;

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@GetMapping
	public ResponseEntity<?> findAll(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pagination) {
		return service.findAll(pagination);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> create(@RequestBody @Valid UserCreationDto dto, UriComponentsBuilder uBuilder) {
		return service.create(dto, uBuilder);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserUpdateDto dto) {
		return service.update(id, dto);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return service.delete(id);
	}
}
