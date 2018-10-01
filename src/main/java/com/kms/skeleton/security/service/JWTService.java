package com.kms.skeleton.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kms.skeleton.security.dto.UserLoginDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

public class JWTService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTService.class);
	private ObjectMapper mapper = new ObjectMapper();
	private String secrectKey = "secrectKey";

	public String encode(String loginName) {
		try {
			UserLoginDto dto = new UserLoginDto(loginName, "NULL");
			String payload = mapper.writeValueAsString(dto);
			return Jwts.builder().setPayload(payload).signWith(SignatureAlgorithm.HS512, secrectKey).compact();

		} catch (Exception e) {
			LOGGER.error("Error when encoding username", loginName, e);
			throw new RuntimeException(e.getMessage());
		}
	}

	public String decode(String token) {
		try {
			DefaultClaims claims = (DefaultClaims) Jwts.parser().setSigningKey(secrectKey).parse(token).getBody();
			
			return claims.get("username", String.class);
		} catch (Exception e) {
			LOGGER.warn("Exception when parsing token {}", token, e);
			throw new RuntimeException("Invalid json web token");
		}
	}
}
