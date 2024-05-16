package com.example.mongo.dto;

import com.example.mongo.model.User;

public record UserDetailsDto(
	String id,
	String firstName,
	String lastName,
	Integer age,
	String email,
	Boolean isActive
) {
	
	// Constructors

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
