package com.foody.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.foody.entities.User;

public class UserRequestChangePassword {
	
	private String code;
	
	@NotNull(message = "Please provide a pasword")
    @Column(unique = true)
	private String newPassword;

	public UserRequestChangePassword() {

	}
	
	public UserRequestChangePassword(String code, String newPassword) {
		this.code = code;
		this.newPassword = newPassword;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public User setUser(UserRequestChangePassword userRequestChangePassword) {
		if(userRequestChangePassword != null) {
			User user = new User(userRequestChangePassword.getCode(), userRequestChangePassword.getNewPassword());
			return user;
		}
		return null;
	}
}
