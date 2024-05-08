package com.example.lombok.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
	
	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	private Boolean isActive;
}
