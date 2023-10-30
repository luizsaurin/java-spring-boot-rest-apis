package com.example.mapper.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreationDto(
	
	@NotBlank
	String firstName,

	@NotBlank
	String lastName,

	@NotNull
	Integer age,

	@NotBlank @Email
	String email,

	@NotNull
	Boolean isActive
) {
	
}
