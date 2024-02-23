package com.example.multipart.dto;

public record FileUploadResponseDTO(
	String fileName,
	String fileDownloadUrl,
	String fileType,
	long size
) {}
