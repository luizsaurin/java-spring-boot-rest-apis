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
import com.example.security.advice.exceptions.InvalidJWTException;
import com.example.security.constant.ErrorMessages;
import com.example.security.constant.JWTConstants;
import com.example.security.constant.SecurityConstants;
import com.example.security.dto.response.AuthResponseDTO;
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
		algorithm = Algorithm.HMAC256(this.secret);
	}

	// Methods

	public Authentication getSecurityFilterAuthentication(HttpServletRequest request) {

		String token = getRequestToken(request);

		if(Strings.isBlank(token)) {
			return null;
		}

		DecodedJWT jwt = decodeJWT(token);

		if(jwt == null) {
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

	public AuthResponseDTO resolveAuthLogin(Authentication auth) {
		User user = (User) auth.getPrincipal();
		return buildAuthResponse(user.getUsername());
	}

	public AuthResponseDTO resolveAuthRefresh(String refreshToken) {

		if(Strings.isBlank(refreshToken)) {
			throw new InvalidJWTException(ErrorMessages.BLANK_AUTH_HEADER);
		}
		
		DecodedJWT jwt = decodeJWT(refreshToken);
		
		if(jwt == null) {
			throw new InvalidJWTException(ErrorMessages.INVALID_JWT);
		}
		
		String tokenType = jwt.getClaim(JWTConstants.TOKEN_TYPE).asString();

		if(!tokenType.contentEquals(JWTConstants.REFRESH)) {
			throw new InvalidJWTException(ErrorMessages.INVALID_JWT);
		}

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(
			jwt.getSubject()
		);

		return buildAuthResponse(userDetails.getUsername());
	}

	private AuthResponseDTO buildAuthResponse(String username) {
		
		Date now = new Date();

		Date accessExpiration = getTokenExpirationDateTime(
			now, this.accessTokenExpiration
		);
		
		Date refreshExpiration = getTokenExpirationDateTime(
			now, this.refreshTokenExpiration
		);

		return new AuthResponseDTO(
			DateUtils.formatDate(accessExpiration), 
			DateUtils.formatDate(refreshExpiration),
			generateJWT(username, now, accessExpiration, JWTConstants.ACCESS), 
			generateJWT(username, now, refreshExpiration, JWTConstants.REFRESH)
		);
	}

	private String generateJWT(
		String subject,
		Date issuedAt,
		Date expiresAt,
		String tokenType
	) {
		return JWT
			.create()
			.withIssuer(this.issuer)
			.withSubject(subject)
			.withClaim(JWTConstants.TOKEN_TYPE, tokenType)
			.withIssuedAt(issuedAt)
			.withExpiresAt(expiresAt)
			.sign(this.algorithm)
			.strip()
		;
	}

	private Date getTokenExpirationDateTime(Date now, long expiration) {
		return new Date(now.getTime() + expiration);
	}

	private String getRequestToken(HttpServletRequest req) {

		String authHeader = req.getHeader(SecurityConstants.AUTHORIZATION);

		if(Strings.isBlank(authHeader)) {
			return null;
		}

		return authHeader.replace(SecurityConstants.BEARER, "");
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

}
