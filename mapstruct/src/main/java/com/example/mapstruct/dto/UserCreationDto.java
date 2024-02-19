package com.example.mapstruct.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreationDto(
	
	@NotBlank
	String name,

	@NotBlank @Email
	String email,

	@NotNull
	String enabled
) {
	
}
