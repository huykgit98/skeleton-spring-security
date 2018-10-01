package com.kms.skeleton.security.entity;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserNameNullPasswordToken extends UsernamePasswordAuthenticationToken{

	private static final long serialVersionUID = -8048511258916082672L;

	public UserNameNullPasswordToken(String username) {
		super(username, null);
	}

}
