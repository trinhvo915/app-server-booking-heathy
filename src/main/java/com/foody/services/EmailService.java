package com.foody.services;

import javax.servlet.http.HttpServletRequest;

import com.foody.entities.User;

public interface EmailService {
	
	void sendEmail(String email, HttpServletRequest httpRequest, User user);
}
