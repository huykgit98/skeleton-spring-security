package com.kms.skeleton.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kms.skeleton.security.dto.UserLoginDto;
import com.kms.skeleton.security.util.WebUtil;

public class UsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	protected UsernamePasswordAuthenticationFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		ObjectMapper mapper = new ObjectMapper();
		UserLoginDto userLoginDto = mapper.readValue(WebUtil.readRequestBody(request), UserLoginDto.class);
		

		return authenticate(request, userLoginDto);
	}
	
	public Authentication authenticate(HttpServletRequest httpRequest, UserLoginDto userLoginDto) {
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword());

		authRequest.setDetails(authenticationDetailsSource.buildDetails(httpRequest));
		Authentication authentication = this.getAuthenticationManager().authenticate(authRequest);

		return authentication;
	}

}
