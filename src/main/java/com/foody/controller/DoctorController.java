package com.foody.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.foody.services.BookingService;
import com.foody.services.ClinicService;
import com.foody.services.PostService;
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
	
	@Autowired
	BookingService BookingService;
	
	@Autowired
	PostService postService;
	
	@RequestMapping(value= "all", method = RequestMethod.GET, produces = "application/json")
	public DataResponse getAllDoctor(){
		return userservice.getAllDoctor();
	}
	
	@RequestMapping(value= "all-clinic/{id_doctor}/{id_clinic}/{date_qurrey}/{date_current}",method = RequestMethod.GET, produces = "application/json")
	public DataResponse getDoctor(@CurrentUser UserPrincipal currentUser, @PathVariable("id_doctor") String id_doctor,@PathVariable("id_clinic") String id_clinic,@PathVariable("date_qurrey") String date_qurrey, @PathVariable("date_current") String date_current) throws ParseException{
		Date dateQurey =new SimpleDateFormat("yyyy-MM-dd").parse(date_qurrey); 
		
		Date date_currentRequest =new SimpleDateFormat("yyyy-MM-dd").parse(date_current); 
		
		ClinicsRequest clinicsRequest = new ClinicsRequest(id_clinic, id_doctor,dateQurey, date_currentRequest);
		  return ClinicService.getDoctorInClinic(clinicsRequest);
	}
	@RequestMapping(value= "posts/{id_clinic}/{id_post_type}",method = RequestMethod.GET, produces = "application/json")
	public DataResponse getCommentDoctor(@CurrentUser UserPrincipal currentUser, @PathVariable("id_clinic") String id_clinic,@PathVariable("id_post_type") String id_post_type){
		  return postService.getPostTypePostForClinic(id_clinic, id_post_type);
	}
//	@RequestMapping(value= "img/{id_user}/{name}", method = RequestMethod.GET, produces = "application/json")
//	public AttachmentType getAllDoctora(@PathVariable("id_user") String id_user,@PathVariable("name") String name){
//		return attachmentTypeResponsitory.getAttachmentTypeDoctorPersonal(id_user, true, name);
//	}
}
