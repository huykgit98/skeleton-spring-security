package com.kms.skeleton.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kms.skeleton.security.entity.DatabaseUserDetails;

@Service
public class DatabaseUserDetailsService implements UserDetailsService{

	private static final String DEFAULT_VALUE = "admin";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseUserDetailsService.class);
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		LOGGER.info("Load user details by username = {}", userName);
		//TODO: Replace by a DB call to get real user details
		return userName.equals(DEFAULT_VALUE) ? new DatabaseUserDetails(DEFAULT_VALUE, DEFAULT_VALUE): null;
	}

}
