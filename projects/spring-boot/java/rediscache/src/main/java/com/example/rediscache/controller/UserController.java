package com.example.rediscache.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.example.rediscache.dto.UserCreationDto;
import com.example.rediscache.dto.UserDetailsDto;
import com.example.rediscache.dto.UserUpdateDto;
import com.example.rediscache.model.User;
import com.example.rediscache.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private UserService service;

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {

		log.info("GET /users/" + id);

		UserDetailsDto dto = service.findById(id);
		if(dto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	public ResponseEntity<?> findAll(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pagination) {

		log.info("GET /users");
		
		return ResponseEntity.ok(service.findAll(pagination));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> create(@RequestBody @Valid UserCreationDto dto, UriComponentsBuilder uBuilder) {
		
		log.info("POST /users");
		
		User user = service.create(dto);
		URI uri = uBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDetailsDto(user));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserUpdateDto dto) {
		
		log.info("PUT /users");
		
		UserDetailsDto userDto = service.update(id, dto);
		if(userDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userDto);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		log.info("DELETE /user/" + id);
		
		boolean success = service.delete(id);
		if(!success) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.noContent().build();
	}
}
