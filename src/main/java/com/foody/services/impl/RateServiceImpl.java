package com.foody.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foody.dto.RateRequest;
import com.foody.entities.Rate;
import com.foody.entities.User;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.repository.RateRepository;
import com.foody.repository.UserRepository;
import com.foody.security.UserPrincipal;
import com.foody.services.RateService;
import com.foody.utils.Constant;

@Service
public class RateServiceImpl implements RateService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RateRepository rateRepository;
	
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
				
				rateRepository.save(rate);
				return new DataResponse(true, new Data(Constant.ADD_RATE_SUCCESS,HttpStatus.OK.value(),rateRequest));
			}
		}else {
			if(user != null) {
				Rate rate = new Rate();
				rate.setUser(user);
				
				rate.setNumberStar(rateRequest.getNumberStar());
				
				User expert = userRepository.getOne(rateRequest.getExpert().getId());
				rate.setExpert(expert);
				
				rateRepository.save(rate);
				return new DataResponse(true, new Data(Constant.ADD_RATE_SUCCESS,HttpStatus.OK.value(),rateRequest));
			}
		}
		
		return new DataResponse(false, new Data(Constant.ADD_RATE_UNSUCCESS,HttpStatus.BAD_REQUEST.value()));
		
	}

}
