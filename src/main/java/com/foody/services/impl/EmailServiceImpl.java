package com.foody.services.impl;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.foody.entities.User;
import com.foody.services.EmailService;
import com.foody.services.UserService;
import com.foody.utils.Constant;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	UserService userservice;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendEmail(String email, HttpServletRequest httpRequest, User user) {
		user.setCode(UUID.randomUUID().toString());
		userservice.save(user);
		
		String appUrl = httpRequest.getScheme() + "://" +httpRequest.getServerName()+":"+httpRequest.getServerPort();
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setFrom(Constant.EMAIL);
		mailMessage.setTo(user.getEmail());
		System.out.println("************** : "+ user.getEmail());

		mailMessage.setText("To reset your password, click the link below:\n" + appUrl + "/reset?token=" + user.getCode());
		
		mailSender.send(mailMessage);
	}


}
