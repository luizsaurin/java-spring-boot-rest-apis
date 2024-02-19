package com.example.mapstruct.dto;

public record UserDetailsDto(
	Long id,
	String firstName,
	String lastName,
	Integer age,
	String email,
	Boolean isActive
) {

}
