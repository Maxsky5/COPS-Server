package com.cesi.cops.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	
	@RequestMapping(value = "/api/user", method = RequestMethod.GET)
	public Principal getUser(Principal user) {
		LOGGER.info("Auth requested with username={}", user != null ? user.getName() : "<no user>");
		return user;
	}
}
