package com.example.security.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(
		HttpServletRequest request, 
		HttpServletResponse response,
		AuthenticationException exception
	) throws 
		IOException, 
		ServletException 
	{
		//TODO response
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(403);
		response.getWriter().write("{\"message\":\"" + this.getClass().getSimpleName() + "\"}");
	}
	
}
