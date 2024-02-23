package com.example.multipart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.multipart.constant.ApiConstants;
import com.example.multipart.constant.ControllerURIs;
import com.example.multipart.service.FileService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class FileController {
	
	// Dependencies

	@Autowired
	private FileService fileService;

	// Methods

	@PostMapping(ControllerURIs.FILE_UPLOAD_ONE)
	public ResponseEntity<?> uploadOne(
		@RequestParam(ApiConstants.FILE) MultipartFile file
	) {
		return ResponseEntity.ok(fileService.uploadOneFile(file));
	}

	@PostMapping(ControllerURIs.FILE_UPLOAD_MANY)
	public ResponseEntity<?> uploadMany(
		@RequestParam(ApiConstants.FILES) MultipartFile[] files
	) {
		return ResponseEntity.ok(fileService.uploadManyFiles(files));
	}

	@GetMapping(ControllerURIs.FILE_DOWNLOAD_WITH_FORMAT)
	public ResponseEntity<?> download(
		HttpServletRequest req,	
		@PathVariable String filename
	) {
		return fileService.downloadFile(req, filename);
	}

}
