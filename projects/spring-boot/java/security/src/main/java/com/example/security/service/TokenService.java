package com.example.security.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.security.model.User;

@Service
public class TokenService {
	
	// Application properties

	@Value("${api.jwt.secret}")
	private String secret;

    @Value("${api.jwt.issuer}")
    private String issuer;

	// Methods

	public String generateToken(User user) {

		return JWT
			.create()
			.withIssuer(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString())
			.withSubject(user.getUsername())
			.withClaim("userId", user.getId())
			.withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
			.sign(Algorithm.HMAC256(secret))
		;
	}
	
	public String getTokenSubject(String token) {
		
		return JWT
			.require(Algorithm.HMAC256(secret))
			.withIssuer(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString())
			.build()
			.verify(token)
			.getSubject()
		;
	}

}
