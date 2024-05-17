package com.example.versioning.dto.v2;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreationDtoV2(
	
	@NotBlank
	String name,

	@NotBlank @Email
	String email
) {}
