package com.example.swagger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
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

import com.example.swagger.dto.ErrorResponseDto;
import com.example.swagger.dto.UserCreationDto;
import com.example.swagger.dto.UserDetailsDto;
import com.example.swagger.dto.UserUpdateDto;
import com.example.swagger.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "Endpoints for managing Users")
public class UserController {
	
	@Autowired private UserService service;

	@Operation(summary = "Find user by ID", responses = {
		@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserDetailsDto.class))),
		@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
		@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
		@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
	})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@Operation(summary = "Find all users", responses = {
		@ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDetailsDto.class)))),
		@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
		@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAll(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pagination) {
		return service.findAll(pagination);
	}
	
	@Operation(summary = "Create user", responses = {
		@ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = UserDetailsDto.class))),
		@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
		@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
	})
	@Transactional
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody @Valid UserCreationDto dto, UriComponentsBuilder uBuilder) {
		return service.create(dto, uBuilder);
	}
	
	@Operation(summary = "Update user", responses = {
		@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserDetailsDto.class))),
		@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
		@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
		@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
	})
	@Transactional
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserUpdateDto dto) {
		return service.update(id, dto);
	}

	@Operation(summary = "Delete user", responses = {
		@ApiResponse(responseCode = "204", description = "No Content", content = @Content),
		@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
		@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
		@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
	})
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return service.delete(id);
	}
}
