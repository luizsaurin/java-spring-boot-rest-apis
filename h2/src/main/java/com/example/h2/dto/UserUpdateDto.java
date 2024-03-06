package com.example.h2.dto;

public record UserUpdateDto(
	String firstName,
	String lastName,
	Integer age,
	String email,
	Boolean isActive
) {
	
}
