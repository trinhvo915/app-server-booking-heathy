package com.foody.utils;
import org.springframework.http.HttpStatus;

import com.foody.entities.User;
import com.foody.payload.Data;
import com.foody.services.UserService;

public class ConfirmCode {
	
	private static UserService userService;

	public static Data checkConfirmCode(String token) {
		User checkToken = ConfirmCode.userService.findByCode(token);
		if(checkToken != null) {
			Data data = new Data(checkToken.toString(), HttpStatus.OK.value());
			return data;
		}
		Data data = new Data("false", HttpStatus.BAD_REQUEST.value());
		return data;
	}
}
