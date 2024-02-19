package com.example.security.dto.response;

import org.springframework.validation.FieldError;

public record AuthValidationErrorResponseDTO(
	String field,
	String message
) {
	
	// Constructors

	public AuthValidationErrorResponseDTO(FieldError e) {
		this(
			e.getField(), 
			e.getDefaultMessage()
		);
	}
}
