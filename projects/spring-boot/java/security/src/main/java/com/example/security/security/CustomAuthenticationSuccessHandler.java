package com.example.security.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(
		HttpServletRequest request, 
		HttpServletResponse response,
		Authentication authentication
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
