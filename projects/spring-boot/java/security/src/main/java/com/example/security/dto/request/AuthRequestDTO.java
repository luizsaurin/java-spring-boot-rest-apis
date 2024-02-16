package com.example.security.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
	
	@NotBlank
	String username,

	@NotBlank
	String password
) {
	
}
