package com.example.security.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.security.dto.response.LoginValidationResponseDTO;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return ResponseEntity
			.badRequest()
			.body(
				e.getFieldErrors().stream().map(LoginValidationResponseDTO::new).toArray()
			)
		;
	}
	
}
