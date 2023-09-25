package com.example.customjsonserialization.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customjsonserialization.example1.User1;
import com.example.customjsonserialization.example2.User2;
import com.example.customjsonserialization.example2.UserDetailsDto;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/example1")
	public ResponseEntity<?> responseUsingCustomJsonSerialization() {

		User1 user = new User1(1L, "john.doe", "john.doe@mail.com", "123");
		return ResponseEntity.ok(user);
	}

	@GetMapping("/example2")
	public ResponseEntity<?> responseUsingDto() {

		User2 user = new User2(2L, "mary.jane", "mary.jane@mail.com", "456");
		return ResponseEntity.ok(new UserDetailsDto(user));
	}
}
