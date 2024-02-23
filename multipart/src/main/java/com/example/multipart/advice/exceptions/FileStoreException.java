package com.example.multipart.advice.exceptions;

import com.example.multipart.constant.ErrorMessages;

public class FileStoreException extends RuntimeException {
	
	public FileStoreException(String cause) {
		super(ErrorMessages.FILE_STORE + ": " + cause);
	}
}
