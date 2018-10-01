package com.kms.skeleton.security.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticatedUserCache implements UserCache {
	
	private Map<String, UserDetails> cache = new ConcurrentHashMap<>();
	
	@Override
	public UserDetails getUserFromCache(String username) {			
		return cache.get(username);
	}

	@Override
	public void putUserInCache(UserDetails user) {
		
		cache.put(user.getUsername(), user);	
		
	}

	@Override
	public void removeUserFromCache(String username) {
		cache.remove(username);	
	}	

}
