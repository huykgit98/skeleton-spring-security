package com.kms.skeleton.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.kms.skeleton.security.entity.DatabaseUserDetails;
import com.kms.skeleton.security.service.JWTService;

public class UsernamePasswordLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private JWTService jwtService;		

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		response.setStatus(HttpStatus.OK.value());		
		DatabaseUserDetails userDetails = (DatabaseUserDetails) auth.getPrincipal();				
		response.getWriter().write(jwtService.encode(userDetails.getUsername()));		
		response.getWriter().flush();
	}
}
