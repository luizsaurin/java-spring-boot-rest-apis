package com.example.openfeign.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.openfeign.config.FeignConfig;
import com.example.openfeign.dto.GenericResponseDTO;

@FeignClient(name = "example-client", url = "${integration.example-client.url}", configuration = FeignConfig.class)
public interface ExampleClient {
	
	@GetMapping("/client")
	ResponseEntity<GenericResponseDTO> getResponse(
		@RequestParam(required = false) String status
	);
}
