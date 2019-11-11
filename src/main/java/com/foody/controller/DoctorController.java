package com.foody.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foody.dto.ClinicsRequest;
import com.foody.payload.DataResponse;
import com.foody.repository.AttachmentTypeRepository;
import com.foody.security.CurrentUser;
import com.foody.security.UserPrincipal;
import com.foody.services.ClinicService;
import com.foody.services.UserService;

@RestController
@RequestMapping("api/doctor")
public class DoctorController {
	@Autowired
	UserService userservice;
	
	@Autowired
	AttachmentTypeRepository attachmentTypeResponsitory;
	
	@Autowired
	ClinicService ClinicService;
	
	@RequestMapping(value= "all", method = RequestMethod.GET, produces = "application/json")
	public DataResponse getAllDoctor(){
		return userservice.getAllDoctor();
	}
	
	@RequestMapping(value= "all-clinic/{id_doctor}/{id_clinic}",method = RequestMethod.GET, produces = "application/json")
	public DataResponse registerDoctor(@CurrentUser UserPrincipal currentUser, @PathVariable("id_doctor") String id_doctor,@PathVariable("id_clinic") String id_clinic){
		  ClinicsRequest clinicsRequest = new ClinicsRequest(id_clinic, id_doctor);
		  return ClinicService.getDoctorInClinic(clinicsRequest);
	}
	
//	@RequestMapping(value= "img/{id_user}/{name}", method = RequestMethod.GET, produces = "application/json")
//	public AttachmentType getAllDoctora(@PathVariable("id_user") String id_user,@PathVariable("name") String name){
//		return attachmentTypeResponsitory.getAttachmentTypeDoctorPersonal(id_user, true, name);
//	}
}
