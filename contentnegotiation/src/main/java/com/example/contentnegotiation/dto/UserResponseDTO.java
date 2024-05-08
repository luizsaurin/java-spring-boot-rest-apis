package com.example.contentnegotiation.dto;

public record UserResponseDTO(
	Long id,
	String firstName,
	String lastName,
	Integer age,
	String email,
	Boolean isActive
) {
	
}
