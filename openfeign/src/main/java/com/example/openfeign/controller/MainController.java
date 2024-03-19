package com.example.openfeign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.openfeign.integration.ExampleClient;

@RestController
public class MainController {

	// Dependencies

	@Autowired
	private ExampleClient exampleClient;

	// Methods

	@GetMapping("/main")
	public ResponseEntity<?> getResponse(@RequestParam(required = false) String status) {
		return ResponseEntity.ok(exampleClient.getResponse(status).getBody());
	}
	
}
