package com.foody.services.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foody.dto.ClinicRequest;
import com.foody.dto.ClinicResponse;
import com.foody.entities.Clinic;
import com.foody.entities.User;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.repository.ClinicRepository;
import com.foody.repository.FacultyRepository;
import com.foody.repository.UserRepository;
import com.foody.security.UserPrincipal;
import com.foody.services.ClinicService;
import com.foody.utils.Constant;


@Service
public class ClinicServiceImpl implements ClinicService{

	@Autowired
	ClinicRepository clinicRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FacultyRepository facultyRepository;
	
	@Override
	public DataResponse addClinicCurrentDoctor(UserPrincipal currentUser, ClinicRequest clinicRequest) {
		User user = userRepository.findByIdAndCheckRole(currentUser.getId(), true, Constant.EXPERT);
		if(user ==null) {
			return new DataResponse(false, new Data(Constant.NO_EXPERT,HttpStatus.BAD_REQUEST.value()));
		}
		
		if(!"".equals(currentUser.getId())) {
			Clinic clinic = new Clinic(clinicRequest.getName(), clinicRequest.getAddress(), clinicRequest.getLatitude(), clinicRequest.getLongitude());
			
			List<String> idFaculties = new ArrayList<String>();
			clinicRequest.getFaculties().stream().forEach( x -> idFaculties.add(x.getId()));
			
			clinic.setFaculties(facultyRepository.findByIdIn(idFaculties));
			
			Set<User> users = new HashSet<>();
			users.add(user);
			clinic.setUsers(users);
			
			clinicRepository.save(clinic);
			
			ClinicResponse clinicResponse = new ClinicResponse(clinic);
			return new DataResponse(true, new Data(Constant.REGISTER_CLINIC_SUCCESS,HttpStatus.OK.value(),clinicResponse));
		}else {
			return new DataResponse(false, new Data(Constant.USER_NO_FIND_ID,HttpStatus.BAD_REQUEST.value()));
		}
	}

	@Override
	public DataResponse addDoctorIntoClinic(UserPrincipal currentUser, String idClinic, String usernameOrEmail) {
		User user = userRepository.findByIdAndCheckRole(currentUser.getId(), true, "EXPERT");
		Clinic clinic = clinicRepository.findByIdClinicAndIdUser(idClinic,user.getId());
		User userAdd = userRepository.findByEmailAndCheckRole(usernameOrEmail, true, "EXPERT");
		
		if(clinic != null && userAdd != null){
			Set<User> users = userRepository.getUserByIdClinic(idClinic, true);
			users.add(userAdd);
			clinic.setUsers(users);
			
			clinicRepository.save(clinic);
			return new DataResponse(true, new Data(Constant.ADD_DOCTOR_SUCCESS,HttpStatus.OK.value(),clinic));
		}else {
			return new DataResponse(false, new Data(Constant.ADD_DOCTOR_UNSUCCESS,HttpStatus.BAD_REQUEST.value()));
		}
	}
	
}
