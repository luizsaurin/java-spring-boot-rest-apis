package com.example.bundles.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.bundles.advice.exceptions.CustomErrorException;
import com.example.bundles.dto.ResponseDto;

@RestController
public class MainController {
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping("/greeting")
	public ResponseEntity<?> greeting(
		@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) Locale locale
	) {
		String message = messageSource.getMessage("greeting.message", null, locale);
		ResponseDto dto = new ResponseDto(message);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/error")
	public ResponseEntity<?> error(
		@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) Locale locale
	) {
		String message = messageSource.getMessage("error.message", null, locale);
		throw new CustomErrorException(message);
	}
	
}
