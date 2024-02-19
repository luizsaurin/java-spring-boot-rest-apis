package com.example.security.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

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

		SecurityContextHolder.getContext().setAuthentication(
			tokenService.getSecurityFilterAuthentication(request)
		);

		filterChain.doFilter(request, response);
	}

}
