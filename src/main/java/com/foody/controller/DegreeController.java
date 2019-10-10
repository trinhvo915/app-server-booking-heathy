package com.foody.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foody.entities.Degree;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.services.DegreeService;

@RestController
@RequestMapping("api/degrees")
public class DegreeController {
	
	@Autowired
	DegreeService degreeService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public DataResponse getAllFaculty(){
		List<Degree>  degrees = degreeService.getAll();
		if(!degrees.isEmpty()) {
			return new DataResponse(true, new Data("Get degrees success",HttpStatus.OK.value(),degrees));
		}
		return new DataResponse(false, new Data("Get degrees unccess",HttpStatus.BAD_REQUEST.value()));
	}
}
