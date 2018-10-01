package com.kms.skeleton.security.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
	@RequestMapping(method = RequestMethod.GET, value = "/authenticate")
	public String authenticate() {	
		return "SUCCESS";
	}
}
