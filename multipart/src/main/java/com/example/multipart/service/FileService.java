package com.example.multipart.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.multipart.advice.exceptions.FileDownloadException;
import com.example.multipart.constant.ApiConstants;
import com.example.multipart.dto.FileUploadResponseDTO;
import com.example.multipart.utils.MultipartUtils;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class FileService {
	
	// Dependencies

	@Autowired
	private MultipartUtils multipartUtils;

	// Methods

	public FileUploadResponseDTO uploadOneFile(MultipartFile file) {
		return uploadFileAndBuildResponse(file);
	}

	public List<FileUploadResponseDTO> uploadManyFiles(MultipartFile[] files) {
		
		List<FileUploadResponseDTO> list = new ArrayList<>();

		for (int i = 0; i < files.length; i++) {
			list.add(uploadFileAndBuildResponse(files[i]));
		}

		return list;
	}

	private FileUploadResponseDTO uploadFileAndBuildResponse(MultipartFile file) {

		String fileName = multipartUtils.storeFile(file);
		String downloadUrl = multipartUtils.getFileDownloadUrl(fileName);

		return new FileUploadResponseDTO(
			fileName, 
			downloadUrl, 
			file.getContentType(), 
			file.getSize()
		);
	}

	public ResponseEntity<?> downloadFile(HttpServletRequest req, String filename) {
		
		Resource resource = multipartUtils.loadFileAsResource(filename);

		String contentType = null;

		try {
			contentType = req.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			throw new FileDownloadException(e.getMessage());
		}

		// generic media type fallback
		if(Strings.isBlank(contentType)) {
			contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
		}

		return ResponseEntity.ok()
			.contentType(MediaType.parseMediaType(contentType))
			.header(
				HttpHeaders.CONTENT_DISPOSITION, 
				resolveDownloadFileContentDisposition(req, resource))
			.body(resource)
		;
	}

	private String resolveDownloadFileContentDisposition(HttpServletRequest req, Resource resource) {

		// use this to download the file
		// return ApiConstants.ATTACHMENT_FILENAME + "\"" + resource.getFilename() + "\"";

		// use this to only show the file on the web browser
		return ApiConstants.INLINE;
	}

}
