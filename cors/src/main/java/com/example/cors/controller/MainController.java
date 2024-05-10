package com.example.cors.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {
	
	@GetMapping
	public ResponseEntity<?> get() {
		return ResponseEntity.ok().build();
	}
	
	@PostMapping
	public ResponseEntity<?> post() {
		return ResponseEntity.ok().build();
	}

}
