package com.example.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	// Methods
	
	@GetMapping("/1")
	public ResponseEntity<?> denyAll() {
		return ResponseEntity.ok().build();
	}

	@GetMapping("/2")
	public ResponseEntity<?> permitAll() {
		return ResponseEntity.ok().build();
	}

	@GetMapping("/3")
	public ResponseEntity<?> permitAllAuthenticated() {
		return ResponseEntity.ok().build();
	}

	@GetMapping("/4")
	public ResponseEntity<?> permitAuthenticatedCommonUser() {
		return ResponseEntity.ok().build();
	}

	@GetMapping("/5")
	public ResponseEntity<?> permitAuthenticatedAdmin() {
		return ResponseEntity.ok().build();
	}

	@GetMapping("/6")
	public ResponseEntity<?> permitAuthenticatedCommunUserOrAdmin() {
		return ResponseEntity.ok().build();
	}
}
