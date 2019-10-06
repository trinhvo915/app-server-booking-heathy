package com.foody.services.impl;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foody.dto.ClinicRequest;
import com.foody.entities.Clinic;
import com.foody.entities.User;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.repository.ClinicRepository;
import com.foody.repository.UserRepository;
import com.foody.services.ClinicService;
import com.foody.utils.Constant;


@Service
public class ClinicServiceImpl implements ClinicService{

	@Autowired
	ClinicRepository clinicRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public DataResponse addClinicCurrentDoctor(String idUser, ClinicRequest clinicRequest) {
		User user = userRepository.findByIdAndCheckRole(idUser, true, Constant.EXPERT);
		if(user ==null) {
			return new DataResponse(false, new Data(Constant.NO_EXPERT,HttpStatus.BAD_REQUEST.value()));
		}
		if(!"".equals(idUser)) {
			Clinic clinic = new Clinic(clinicRequest.getName(), clinicRequest.getAddress(), clinicRequest.getLatitude(), clinicRequest.getLongitude());
			clinic.setFaculties(clinicRequest.getFaculties());
			
			Set<User> users = new HashSet<>();
			users.add(user);
			clinic.setUsers(users);
			
			clinicRepository.save(clinic);
			return new DataResponse(true, new Data(Constant.REGISTER_CLINIC_SUCCESS,HttpStatus.OK.value(),clinic));
		}else {
			return new DataResponse(false, new Data(Constant.USER_NO_FIND_ID,HttpStatus.BAD_REQUEST.value()));
		}
	}
	
}
