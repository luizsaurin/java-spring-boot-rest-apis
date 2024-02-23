package com.example.multipart.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.logging.log4j.util.Strings;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.multipart.advice.exceptions.FileStorageDirectoryCreationException;
import com.example.multipart.advice.exceptions.FileStoreException;
import com.example.multipart.advice.exceptions.NullFileUploadException;
import com.example.multipart.advice.exceptions.ResourceNotFoundException;
import com.example.multipart.config.MultipartConfig;
import com.example.multipart.constant.ErrorMessages;
import com.example.multipart.constant.ControllerURIs;

@Component
public class MultipartUtils {
	
	// Attributes

	private final Path fileStorageDirectory;

	// Constructors

	public MultipartUtils(MultipartConfig config) {
		Path path = Paths.get(config.getFileUploadDirectory()).toAbsolutePath().normalize();
		this.fileStorageDirectory = path;
		
		try {
			Files.createDirectories(this.fileStorageDirectory);
		} catch (IOException e) {
			throw new FileStorageDirectoryCreationException(e.getMessage());
		}
	}

	// Methods

	public String storeFile(MultipartFile file) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		if(Strings.isBlank(fileName)) {
			throw new NullFileUploadException(ErrorMessages.NULL_FILE_UPLOAD);
		}

		// storage location. could be local or remote
		Path targetDirectory = fileStorageDirectory.resolve(fileName);
		
		try {
			Files.copy(file.getInputStream(), targetDirectory, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new FileStoreException(e.getMessage());
		}

		return fileName;

	}

	public String getFileDownloadUrl(String fileName) {
		return ServletUriComponentsBuilder
			.fromCurrentContextPath()
			.path(ControllerURIs.FILE_DOWNLOAD + "/")
			.path(fileName)
			.toUriString()
		;
	}

	public Resource loadFileAsResource(String fileName) {

		UrlResource resource = null;

		try {
			resource = new UrlResource(
				fileStorageDirectory.resolve(fileName).normalize().toUri()
			);
		} catch (MalformedURLException e) {
			throw new ResourceNotFoundException();
		}

		if(resource == null || !resource.exists()) {
			throw new ResourceNotFoundException();
		}

		return resource;
	}

}
