package com.foody.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foody.entities.Faculty;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.services.FacultyService;

@RestController
@RequestMapping("api/faculties")
public class FacultyController {
	
	@Autowired
	FacultyService facultyService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public DataResponse getAllFaculty(){
		List<Faculty> faculties =   facultyService.getAll();
		if(!faculties.isEmpty()) {
			return new DataResponse(true, new Data("Get faculty success",HttpStatus.OK.value(),faculties));
		}
		return new DataResponse(false, new Data("Get faculty unccess",HttpStatus.BAD_REQUEST.value()));
	}
}
