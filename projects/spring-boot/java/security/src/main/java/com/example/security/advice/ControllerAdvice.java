package com.example.security.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.security.advice.exceptions.InvalidJWTException;
import com.example.security.constant.ErrorMessages;
import com.example.security.dto.response.GenericErrorResponseDTO;
import com.example.security.dto.response.AuthValidationErrorResponseDTO;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return ResponseEntity
			.badRequest()
			.body(
				e.getFieldErrors().stream().map(AuthValidationErrorResponseDTO::new).toArray()
			)
		;
	}

	//TODO verify if is possible to handle this on the custom auth handlers
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHttpMessageNotReadableException() {
		return ResponseEntity
		.badRequest()
		.body(
			new GenericErrorResponseDTO(ErrorMessages.INVALID_HTTP_REQUEST)
			)
			;
		}
		
	//TODO verify if is possible to handle this on the custom auth handlers
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<?> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
		return ResponseEntity
			.badRequest()
			.body(
				new GenericErrorResponseDTO(e.getMessage())
			)
		;
	}

	@ExceptionHandler(InvalidJWTException.class)
	public ResponseEntity<?> handleInvalidJWTException(InvalidJWTException e) {
		return ResponseEntity
			.badRequest()
			.body(
				new GenericErrorResponseDTO(e.getMessage())
			)
		;
	}
	
}
