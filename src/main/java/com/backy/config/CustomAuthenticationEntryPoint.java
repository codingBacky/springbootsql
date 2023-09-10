package com.backy.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) 
			throws IOException, ServletException{
		if("XMLHTTPRequest".equals(request.getHeader("x-reequested-with"))) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		}else {
			response.sendRedirect("/members/login");
		}
		
	}
}
