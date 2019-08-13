package com.foody.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foody.dto.EmailRequest;
import com.foody.entities.User;
import com.foody.payload.Data;
import com.foody.services.EmailService;
import com.foody.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userservice;
	
	@Autowired
	EmailService emailService;
	
	@RequestMapping(value ="forgot", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Data> forgotPassword(@RequestBody EmailRequest emailRequest, HttpServletRequest httpRequest){
		User user = userservice.findByEmail(emailRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not find email : "+emailRequest.getEmail()));
		
		if(user != null) {
			emailService.sendEmail(emailRequest.getEmail(), httpRequest, user);
			Data data = new Data("true",HttpStatus.OK.value());
			return new ResponseEntity<>(data,HttpStatus.CREATED);
		}
		Data data = new Data("false",HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(data,HttpStatus.BAD_REQUEST);
	}
}
