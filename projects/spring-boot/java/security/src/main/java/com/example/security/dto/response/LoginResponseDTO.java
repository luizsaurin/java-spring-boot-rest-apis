package com.example.security.dto.response;

public record LoginResponseDTO(
	String accessTokenExpiration,
	String refreshTokenExpiration,
	String accessToken,
	String refreshToken
) {
	
}
