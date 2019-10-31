package com.foody.controller;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.foody.entities.AttachmentType;
import com.foody.payload.DataResponse;
import com.foody.repository.AttachmentTypeRepository;
import com.foody.services.UserService;

@RestController
@RequestMapping("api/doctor")
public class DoctorController {
	@Autowired
	UserService userservice;
	
	@Autowired
	AttachmentTypeRepository attachmentTypeResponsitory;
	
	@RequestMapping(value= "all", method = RequestMethod.GET, produces = "application/json")
	public DataResponse getAllDoctor(){
		return userservice.getAllDoctor();
	}
	
//	@RequestMapping(value= "img/{id_user}/{name}", method = RequestMethod.GET, produces = "application/json")
//	public AttachmentType getAllDoctora(@PathVariable("id_user") String id_user,@PathVariable("name") String name){
//		return attachmentTypeResponsitory.getAttachmentTypeDoctorPersonal(id_user, true, name);
//	}
}
