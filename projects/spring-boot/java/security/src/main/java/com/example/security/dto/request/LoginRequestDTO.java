package com.example.security.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
	
	@NotBlank
	String username,

	@NotBlank
	String password
) {
	
}
