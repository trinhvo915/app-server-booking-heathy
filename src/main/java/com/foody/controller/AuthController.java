package com.foody.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<?> authenticateUser(){
		return null;
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(){
		return null;
	}
	
} 
