package com.example.security.dto.response;

public record AuthResponseDTO(
	String accessTokenExpiration,
	String refreshTokenExpiration,
	String accessToken,
	String refreshToken
) {
	
}
