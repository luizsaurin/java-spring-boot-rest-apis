package com.example.security.security;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(
		HttpServletRequest request, 
		HttpServletResponse response,
		AccessDeniedException accessDeniedException
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
