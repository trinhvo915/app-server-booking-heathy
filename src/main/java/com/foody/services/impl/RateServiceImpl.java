package com.foody.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foody.dto.ClinicsRequest;
import com.foody.dto.RateRequest;
import com.foody.entities.Clinic;
import com.foody.entities.Rate;
import com.foody.entities.User;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.repository.RateRepository;
import com.foody.repository.UserRepository;
import com.foody.repository.ClinicRepository;
import com.foody.security.UserPrincipal;
import com.foody.services.RateService;
import com.foody.utils.Constant;

@Service
public class RateServiceImpl implements RateService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RateRepository rateRepository;
	
	@Autowired
	ClinicRepository clinicRepository;
	
	@Autowired
	ClinicServiceImpl clinicServiceImpl;
	
	@Override
	public DataResponse addRate(UserPrincipal currentUser, RateRequest rateRequest) {
		
		Rate checkRate = rateRepository.checkUserAndDoctorForRate(currentUser.getId(), rateRequest.getExpert().getId());
		User user = userRepository.getOne(currentUser.getId());
		
		if(checkRate !=null) {
			rateRepository.delete(checkRate);
			if(user != null) {
				Rate rate = new Rate();
				rate.setUser(user);
				
				rate.setNumberStar(rateRequest.getNumberStar());
				
				User expert = userRepository.getOne(rateRequest.getExpert().getId());
				rate.setExpert(expert);
				
				Clinic clinic = clinicRepository.getOne(rateRequest.getClinic().getId());
				rate.setClinic(clinic);
				
				Rate ratedata =  rateRepository.save(rate);
				
				DataResponse responsedata = new DataResponse();
				if(ratedata != null) {
					ClinicsRequest clinicsRequest = new ClinicsRequest(rateRequest.getClinic().getId(), rateRequest.getExpert().getId());
					responsedata = clinicServiceImpl.getDoctorInClinic(clinicsRequest);
				}
				return new DataResponse(true, new Data(Constant.ADD_RATE_SUCCESS,HttpStatus.OK.value(),responsedata));
			}
		}else {
			if(user != null) {
				Rate rate = new Rate();
				rate.setUser(user);
				
				rate.setNumberStar(rateRequest.getNumberStar());
				
				User expert = userRepository.getOne(rateRequest.getExpert().getId());
				rate.setExpert(expert);
				
				Clinic clinic = clinicRepository.getOne(rateRequest.getClinic().getId());
				rate.setClinic(clinic);
				
				Rate ratedata =rateRepository.save(rate);
				DataResponse responsedata = new DataResponse();
				
				if(ratedata != null) {
					ClinicsRequest clinicsRequest = new ClinicsRequest(rateRequest.getClinic().getId(), rateRequest.getExpert().getId());
					responsedata = clinicServiceImpl.getDoctorInClinic(clinicsRequest);
				}
				
				return new DataResponse(true, new Data(Constant.ADD_RATE_SUCCESS,HttpStatus.OK.value(),responsedata.getData().getObject()));
			}
		}
		
		return new DataResponse(false, new Data(Constant.ADD_RATE_UNSUCCESS,HttpStatus.BAD_REQUEST.value()));
		
	}

}
