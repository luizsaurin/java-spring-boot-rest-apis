package com.example.security.dto.response;

import org.springframework.validation.FieldError;

public record LoginValidationResponseDTO(
	String field,
	String message
) {
	
	// Constructors

	public LoginValidationResponseDTO(FieldError e) {
		this(
			e.getField(), 
			e.getDefaultMessage()
		);
	}
}
