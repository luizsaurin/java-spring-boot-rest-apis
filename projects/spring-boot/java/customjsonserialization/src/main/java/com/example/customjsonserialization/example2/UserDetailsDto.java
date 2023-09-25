package com.example.customjsonserialization.example2;

public record UserDetailsDto(
	Long user_id,
	String user_email,
	String user_login
) {
	public UserDetailsDto(User2 user) {
		this(
			user.getId(), 
			user.getEmail(), 
			user.getLogin()
		);
	}
}
