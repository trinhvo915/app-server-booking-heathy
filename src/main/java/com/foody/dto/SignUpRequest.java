package com.foody.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpRequest {
	
	@Size(min = 4, max = 30)
	private String fullName;
	
	
	@NotNull(message = "Please provide a username")
    @Column(unique = true)
	@Size(min = 6, max = 15)
	private String username;
	
	@Email(message = "The email is not properly formatted")
	@Size(min = 1, max = 40)
	private String email;
	
	@Size(min = 1, max = 20)
	private String mobile;
	
	@NotNull(message = "Please provide a pasword")
    @Column(unique = true)
	@NotBlank
	@Size(min = 6, max = 20)
	private String password;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
