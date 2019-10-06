package com.foody.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foody.dto.DoctorRegisterRequest;
import com.foody.dto.EmailRequest;
import com.foody.dto.UserRequestChangePassword;
import com.foody.dto.UserResponse;
import com.foody.entities.User;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.services.EmailService;
import com.foody.services.UserService;
import com.foody.utils.ConfirmCode;
import com.foody.utils.Constant;

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
	
	@RequestMapping(value ="confirm", method = RequestMethod.POST, produces = "application/json")
	public Data confirmCodeChangePassword(@RequestParam("token") String token){
		Data data = ConfirmCode.checkConfirmCode(token);
		return data;
	}
	
	@RequestMapping(value ="change", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Data> changePassword(@RequestBody UserRequestChangePassword userRequestChangePassword){
		Data data = ConfirmCode.checkConfirmCode(userRequestChangePassword.getCode());
		if(data.getStatus() == HttpStatus.OK.value()) {
			User user = new UserRequestChangePassword().setUser(userRequestChangePassword);
			userservice.save(user);
			return new ResponseEntity<>(data, HttpStatus.OK);
		}
		return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value= "{ids}",method = RequestMethod.GET, produces = "application/json")
	public DataResponse getAllRoles(@PathVariable("ids") List<String> ids){
		List<User> users = userservice.findByIdIn(ids);
		if(users.size() != 0) {
			return new DataResponse(true, new Data(Constant.GET_LIST_USER_SUCCESS,HttpStatus.OK.value(),users));
		}
		return new DataResponse(false, new Data(Constant.GET_LIST_USER_UNSUCCESS,HttpStatus.BAD_REQUEST.value(),users));
	}
	
	@RequestMapping(value= "doctor/{id}", method = RequestMethod.PUT, produces = "application/json")
	public DataResponse registerDoctor(@PathVariable("id") String id, @Valid @RequestBody DoctorRegisterRequest doctorRegisterRequest){
		DataResponse data = userservice.updateUser(id, doctorRegisterRequest);
		return data;
	}
	
	@RequestMapping(value= "role/{id_user},{name_ole}", method = RequestMethod.GET, produces = "application/json")
	public UserResponse getUserByIdAndCheckRole(@PathVariable("id_user") String id_user,@PathVariable("nameRole") String nameRole){
		return userservice.getUserByIdAndCheckRole(id_user,nameRole);
	}
}
