package com.example.bundles.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.bundles.advice.exceptions.CustomErrorException;
import com.example.bundles.dto.ResponseDto;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<?> handleCustomErrorException(CustomErrorException e) {
        return ResponseEntity.badRequest().body(new ResponseDto(e.getMessage()));
    }
	
}
