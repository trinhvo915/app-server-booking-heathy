package com.foody.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foody.dto.RateRequest;
import com.foody.payload.DataResponse;
import com.foody.security.CurrentUser;
import com.foody.security.UserPrincipal;
import com.foody.services.RateService;

@RestController
@RequestMapping("api/rates")
public class RateController {
	
	@Autowired
	RateService rateService;
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public DataResponse RateToDoctor(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody RateRequest rateRequest){
		return rateService.addRate(currentUser, rateRequest);
	}
}
