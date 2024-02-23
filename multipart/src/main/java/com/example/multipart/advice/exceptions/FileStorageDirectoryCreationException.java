package com.example.multipart.advice.exceptions;

import com.example.multipart.constant.ErrorMessages;

public class FileStorageDirectoryCreationException extends RuntimeException {
	
	public FileStorageDirectoryCreationException(String cause) {
		super(ErrorMessages.FILE_STORAGE_DIRECTORY_CREATION + ": " + cause);
	}
}
