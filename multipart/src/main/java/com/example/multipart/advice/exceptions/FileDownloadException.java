package com.example.multipart.advice.exceptions;

import com.example.multipart.constant.ErrorMessages;

public class FileDownloadException extends RuntimeException {

	public FileDownloadException(String cause) {
		super(ErrorMessages.FILE_DOWNLOAD + ": " + cause);
	}
	
}
