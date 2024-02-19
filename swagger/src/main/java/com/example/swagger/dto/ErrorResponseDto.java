package com.example.swagger.dto;

/*
 * This structure represents the standard Spring Boot error response body. 
 * It was created here for display in the Swagger documentation.
 */
public record ErrorResponseDto(
	String timestamp,
	Integer status,
	String error,
	String path
) {
	
}
