package com.foody.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


public class EmailRequest {
	
	@Email(message = "The email is not properly formatted")
	@Size(min = 1, max = 40)
	private String email;

	public EmailRequest() {
		
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
