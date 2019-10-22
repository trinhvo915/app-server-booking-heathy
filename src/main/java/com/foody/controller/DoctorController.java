package com.foody.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foody.payload.DataResponse;
import com.foody.services.UserService;

@RestController
@RequestMapping("api/doctor")
public class DoctorController {
	@Autowired
	UserService userservice;
	
	@RequestMapping(value= "all", method = RequestMethod.GET, produces = "application/json")
	public DataResponse getAllDoctor(){
		return userservice.getAllDoctor();
	}
}
