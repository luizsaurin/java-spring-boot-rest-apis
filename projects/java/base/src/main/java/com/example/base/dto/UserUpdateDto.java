package com.example.base.dto;

public record UserUpdateDto(
	String firstName,
	String lastName,
	Integer age,
	String email,
	Boolean isActive
) {
	
}
