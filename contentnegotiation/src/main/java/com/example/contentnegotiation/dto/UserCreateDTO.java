package com.example.contentnegotiation.dto;

public record UserCreateDTO(
	String firstName,
	String lastName,
	Integer age,
	String email,
	Boolean isActive
) {}
