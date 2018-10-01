package com.kms.skeleton.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kms.skeleton.security.dto.ErrorResponseDto;


public class UsernamePasswordLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		ErrorResponseDto dto = new ErrorResponseDto(HttpStatus.UNAUTHORIZED.value(), "Bad credentials");
		response.getWriter().write(mapper.writeValueAsString(dto));

		response.getWriter().flush();

	}
}
