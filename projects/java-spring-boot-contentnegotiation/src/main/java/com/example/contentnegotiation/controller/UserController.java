package com.example.contentnegotiation.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.contentnegotiation.model.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping
	public List<User> findAll() {
		return Arrays.asList(
			new User(1L, "John", "Doe", 41, "john.doe@mail.com", true),
			new User(2L, "Mary", "Jane", 35, "mary.jane@mail.com", true)
		);
	}
}
