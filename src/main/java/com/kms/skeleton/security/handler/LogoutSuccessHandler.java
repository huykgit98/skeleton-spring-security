package com.kms.skeleton.security.handler;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.util.Assert;

import com.kms.skeleton.security.service.JWTService;

public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements InitializingBean {

	@Resource(name = "authenticatedUserCache")
	private UserCache userCache;
	
	@Autowired
	private JWTService jwtService;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (token != null) {
			String username = jwtService.decode(token);
			userCache.removeUserFromCache(username);

		}

		response.setStatus(HttpStatus.OK.value());
		response.getWriter().flush();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(userCache, "userCache must be set");
		Assert.notNull(jwtService, "jwtService must be set");

	}
}
