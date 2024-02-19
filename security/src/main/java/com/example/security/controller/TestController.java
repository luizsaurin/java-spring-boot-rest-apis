package com.example.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.constant.TestControllerURIs;

@RestController
public class TestController {

	// Methods
	
	@GetMapping(TestControllerURIs.denyAll)
	public ResponseEntity<?> denyAll() {
		return ResponseEntity.ok().build();
	}

	@GetMapping(TestControllerURIs.permitAll)
	public ResponseEntity<?> permitAll() {
		return ResponseEntity.ok().build();
	}

	@GetMapping(TestControllerURIs.permitAllAuthenticated)
	public ResponseEntity<?> permitAllAuthenticated() {
		return ResponseEntity.ok().build();
	}

	@GetMapping(TestControllerURIs.permitAuthenticatedAdmin)
	public ResponseEntity<?> permitAuthenticatedAdmin() {
		return ResponseEntity.ok().build();
	}

	@GetMapping(TestControllerURIs.permitAuthenticatedManager)
	public ResponseEntity<?> permitAuthenticatedManager() {
		return ResponseEntity.ok().build();
	}

	@GetMapping(TestControllerURIs.permitAuthenticatedCommonUser)
	public ResponseEntity<?> permitAuthenticatedCommonUser() {
		return ResponseEntity.ok().build();
	}

	@GetMapping(TestControllerURIs.permitAuthenticatedAdminOrManager)
	public ResponseEntity<?> permitAuthenticatedAdminOrManager() {
		return ResponseEntity.ok().build();
	}
}
