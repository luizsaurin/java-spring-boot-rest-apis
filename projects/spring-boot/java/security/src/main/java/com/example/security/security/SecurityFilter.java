package com.example.security.security;

import java.io.IOException;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.security.repository.UserRepository;
import com.example.security.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	// Dependencies

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserRepository userRepository;

	// Methods
	
	@Override
	protected void doFilterInternal(
		@NonNull HttpServletRequest request, 
		@NonNull HttpServletResponse response, 
		@NonNull FilterChain filterChain
	) throws 
		ServletException, 
		IOException 
	{
		String token = getRequestToken(request);

		if(Strings.isNotBlank(token)) {
			
			String subject = tokenService.getTokenSubject(token);
			UserDetails user = userRepository.findByUsername(subject);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		}

		filterChain.doFilter(request, response);
	}

	private String getRequestToken(HttpServletRequest req) {

		String authHeader = req.getHeader("Authorization");

		if(Strings.isBlank(authHeader)) {
			return null;
		}

		return authHeader.replace("Bearer ", "");
	}

}
