package com.example.versioning.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.versioning.constant.UserControllerURIs;
import com.example.versioning.dto.v1.UserCreationDtoV1;
import com.example.versioning.dto.v1.UserDetailsDtoV1;
import com.example.versioning.dto.v2.UserCreationDtoV2;
import com.example.versioning.dto.v2.UserDetailsDtoV2;
import com.example.versioning.service.UserService;

import jakarta.validation.Valid;


@RestController
public class UserController {
	
	// Dependencies

	@Autowired
	private UserService userService;

	// Methods

	@GetMapping(UserControllerURIs.FIND_BY_ID_V1)
	public ResponseEntity<?> findByIdV1(@PathVariable Long id) {
		return ResponseEntity.ok(userService.findByIdV1(id));
	}
	
	@GetMapping(UserControllerURIs.FIND_BY_ID_V2)
	public ResponseEntity<?> findByIdV2(@PathVariable Long id) {
		return ResponseEntity.ok(userService.findByIdV2(id));
	}

	@GetMapping(UserControllerURIs.FIND_ALL_V1)
	public ResponseEntity<?> findAllV1(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pagination) {
		return ResponseEntity.ok(userService.findAllV1(pagination));
	}
	
	@GetMapping(UserControllerURIs.FIND_ALL_V2)
	public ResponseEntity<?> findAllV2(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pagination) {
		return ResponseEntity.ok(userService.findAllV2(pagination));
	}

	@Transactional
	@PostMapping(UserControllerURIs.CREATE_V1)
	public ResponseEntity<?> createV1(@RequestBody @Valid UserCreationDtoV1 dto, UriComponentsBuilder uBuilder) {
		UserDetailsDtoV1 responseDto = userService.createV1(dto);
		URI uri = uBuilder.path(UserControllerURIs.FIND_BY_ID_V1).buildAndExpand(responseDto.id()).toUri();
		return ResponseEntity.created(uri).body(responseDto);
	}
	
	@Transactional
	@PostMapping(UserControllerURIs.CREATE_V2)
	public ResponseEntity<?> createV2(@RequestBody @Valid UserCreationDtoV2 dto, UriComponentsBuilder uBuilder) {
		UserDetailsDtoV2 responseDto = userService.createV2(dto);
		URI uri = uBuilder.path(UserControllerURIs.FIND_BY_ID_V1).buildAndExpand(responseDto.id()).toUri();
		return ResponseEntity.created(uri).body(responseDto);
	}

}
