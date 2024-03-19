package com.example.openfeign.util;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;

@Component
public class OpenFeignUtils {
	
	// Dependencies

	@Autowired
	private ObjectMapper objectMapper;

	// Methods

	public String extractErrorMessage(FeignException e) {

		try {
			Optional<ByteBuffer> responseBody = e.responseBody();
			
			if (responseBody.isPresent()) {
				byte[] bytes = responseBody.get().array();
				String response = new String(bytes, StandardCharsets.UTF_8);
				// Assuming the response is in JSON format, you can parse it to extract the message part
				// Here we are using ObjectMapper to parse JSON
				// You may need to adjust this based on the actual structure of your response
				JsonNode rootNode = objectMapper.readTree(response);
				JsonNode messageNode = rootNode.get("message");
				if (messageNode != null) {
					return messageNode.asText();
				}
			}
		} catch (Exception ex) {
			System.out.println("Error getting error message from FeignException");
		}

		return null;
	}

}
