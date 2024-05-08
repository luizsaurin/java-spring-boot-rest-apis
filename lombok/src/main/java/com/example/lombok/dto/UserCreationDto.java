package com.example.lombok.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationDto {

	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@NotNull
	private Integer age;
	
	@NotBlank @Email
	private String email;
	
	@NotNull
	private Boolean isActive;
}