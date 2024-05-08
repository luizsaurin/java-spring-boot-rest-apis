package com.example.lombok.dto;

import com.example.lombok.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {

	// Attributes
	
	private Long id;
	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	private Boolean isActive;
	
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