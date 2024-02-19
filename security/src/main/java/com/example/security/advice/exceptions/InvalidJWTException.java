package com.example.security.advice.exceptions;

public class InvalidJWTException extends RuntimeException {
	
	public InvalidJWTException(String message) {
		super(message);
	}
}
