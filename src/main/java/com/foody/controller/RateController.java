package com.foody.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foody.dto.ClinicsRequest;
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
	
	@RequestMapping(value= "doctor/{id_doctor}/{id_clinic}",method = RequestMethod.GET, produces = "application/json")
	public DataResponse getCommentDoctor(@CurrentUser UserPrincipal currentUser, @PathVariable("id_doctor") String id_doctor,@PathVariable("id_clinic") String id_clinic){
		  ClinicsRequest clinicsRequest = new ClinicsRequest(id_clinic, id_doctor);
		  return rateService.getRatetDoctor(clinicsRequest);
	}
}
