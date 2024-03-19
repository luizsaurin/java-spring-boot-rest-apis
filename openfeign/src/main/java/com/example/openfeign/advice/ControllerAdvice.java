package com.example.openfeign.advice;

import java.net.ConnectException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.openfeign.dto.GenericResponseDTO;
import com.example.openfeign.util.OpenFeignUtils;

import feign.FeignException;

@RestControllerAdvice
public class ControllerAdvice {

	// Dependencies

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OpenFeignUtils feignUtils;
	
	// Methods

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<?> handleFeignException(FeignException e) {

		HttpStatus status = HttpStatus.valueOf(e.status());

		if(status == null) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return ResponseEntity
			.status(status)
			.body(new GenericResponseDTO(feignUtils.extractErrorMessage(e)))
		;
	}

	@ExceptionHandler(ConnectException.class)
	public ResponseEntity<?> handleConnectException(ConnectException e) {
		return ResponseEntity
			.internalServerError()
			.body(new GenericResponseDTO(e.getMessage()))
		;
	}
}
