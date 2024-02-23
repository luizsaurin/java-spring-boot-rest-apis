package com.example.multipart.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import com.example.multipart.advice.exceptions.FileStorageDirectoryCreationException;
import com.example.multipart.advice.exceptions.FileStoreException;
import com.example.multipart.advice.exceptions.NullFileUploadException;
import com.example.multipart.advice.exceptions.ResourceNotFoundException;
import com.example.multipart.dto.GenericErrorResponseDTO;

@RestControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler(FileStorageDirectoryCreationException.class)
	public ResponseEntity<?> handleFileStorageDirectoryCreationException(FileStorageDirectoryCreationException e) {
		return ResponseEntity.internalServerError().body(new GenericErrorResponseDTO(e.getMessage()));
	}

	@ExceptionHandler(FileStoreException.class)
	public ResponseEntity<?> handleFileStoreException(FileStoreException e) {
		return ResponseEntity.internalServerError().body(new GenericErrorResponseDTO(e.getMessage()));
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException() {
		return ResponseEntity.notFound().build();
	
	}
	@ExceptionHandler(NullFileUploadException.class)
	public ResponseEntity<?> handleNullFileUploadException(NullFileUploadException e) {
		return ResponseEntity.badRequest().body(new GenericErrorResponseDTO(e.getMessage()));
	}

	@ExceptionHandler(MultipartException.class)
	public ResponseEntity<?> handleMultipartException(MultipartException e) {
		return ResponseEntity.badRequest().body(new GenericErrorResponseDTO(e.getMessage()));
	}

}
