package com.example.dockerfile.dto;

import com.example.dockerfile.model.User;

public record UserDetailsDto(
	Long id,
	String firstName,
	String lastName,
	Integer age,
	String email,
	Boolean isActive
) {
	public UserDetailsDto(User user) {
		this(
			user.getId(), 
			user.getFirstName(), 
			user.getLastName(), 
			user.getAge(), 
			user.getEmail(), 
			user.getIsActive()
		);
	}
}
