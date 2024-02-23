package com.example.multipart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultipartConfig {
	
	// Attributes

	@Value("${file.upload.directory}")
    private String fileUploadDirectory;

	// Methods

	public String getFileUploadDirectory() {
		return fileUploadDirectory;
	}

	public void setFileUploadDirectory(String fileUploadDirectory) {
		this.fileUploadDirectory = fileUploadDirectory;
	}

}
