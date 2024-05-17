package com.example.versioning.dto.v1;

import jakarta.validation.constraints.NotBlank;

public record UserCreationDtoV1(
	
	@NotBlank
	String name
) {}
