package com.example.security.service;

import java.util.Date;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.security.dto.response.LoginResponseDTO;
import com.example.security.model.User;
import com.example.security.utils.DateUtils;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenService {
	
	// Application properties

	@Value("${api.jwt.secret}")
	private String secret;

    @Value("${api.jwt.issuer}")
    private String issuer;

    @Value("${api.jwt.access.expiration}")
    private long accessTokenExpiration;
    
	@Value("${api.jwt.refresh.expiration}")
    private long refreshTokenExpiration;

	// Attributes
	
	private Algorithm algorithm = null;

	// Dependencies
	
	@Autowired
	private UserDetailsService userDetailsService;

	// Constructors

	@PostConstruct
	protected void init() {
		algorithm = Algorithm.HMAC256(secret);
	}

	// Methods

	public LoginResponseDTO generateUserLoginToken(Authentication auth) {

		User user = (User) auth.getPrincipal();

		String username = user.getUsername();
		Long userId = user.getId();

		Date now = new Date();

		Date accessExpiration = getTokenExpirationDateTime(
			now, accessTokenExpiration
		);
		
		Date refreshExpiration = getTokenExpirationDateTime(
			now, refreshTokenExpiration
		);

		return new LoginResponseDTO(
			DateUtils.formatDate(accessExpiration), 
			DateUtils.formatDate(refreshExpiration),
			generateToken(username, userId, now, accessExpiration), 
			generateToken(username, userId, now, refreshExpiration)
		);
	}

	private String generateToken(
		String subject,
		Long userId,
		Date issuedAt,
		Date expiresAt
	) {
		return JWT
			.create()
			.withIssuer(this.issuer)
			.withSubject(subject)
			.withClaim("userId", userId)
			.withIssuedAt(issuedAt)
			.withExpiresAt(expiresAt)
			.sign(this.algorithm)
			.strip()
		;
	}

	private Date getTokenExpirationDateTime(Date now, long expiration) {
		return new Date(now.getTime() + expiration);
	}

	public Authentication getAuthenticationForSecurityFilter(
		HttpServletRequest request
	) {

		String token = getRequestToken(request);

		if(Strings.isBlank(token)) {
			return null;
		}

		DecodedJWT jwt = decodeJWT(token);

		if(jwt == null) {
			return null;
		}

		if(isTokenExpired(jwt)) {
			return null;
		}

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(
			jwt.getSubject()
		);

		return new UsernamePasswordAuthenticationToken(
			userDetails, 
			userDetails.getPassword(), 
			userDetails.getAuthorities()
		);
	}

	private String getRequestToken(HttpServletRequest req) {

		String authHeader = req.getHeader("Authorization");

		if(Strings.isBlank(authHeader)) {
			return null;
		}

		return authHeader.replace("Bearer ", "");
	}

	private DecodedJWT decodeJWT(String token) {
		try {
			return JWT
				.require(algorithm)
				.build()
				.verify(token)
			;
		} catch (JWTVerificationException e) {
			return null;
		}
	}

	private boolean isTokenExpired(DecodedJWT jwt) {
		
		if(jwt.getExpiresAt().after(new Date())) {
			return false;
		}

		return true;
	}

}
