package com.foody.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foody.dto.ClinicsRequest;
import com.foody.dto.CommentRequest;
import com.foody.payload.DataResponse;
import com.foody.security.CurrentUser;
import com.foody.security.UserPrincipal;
import com.foody.services.CommentService;

@RestController
@RequestMapping("api/comments")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public DataResponse CommentToDoctor(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody CommentRequest commentRequest){
		return commentService.addComment(currentUser,commentRequest);
	}
	
	@RequestMapping(value= "doctor/{id_doctor}/{id_clinic}",method = RequestMethod.GET, produces = "application/json")
	public DataResponse getCommentDoctor(@CurrentUser UserPrincipal currentUser, @PathVariable("id_doctor") String id_doctor,@PathVariable("id_clinic") String id_clinic){
		  ClinicsRequest clinicsRequest = new ClinicsRequest(id_clinic, id_doctor);
		  return commentService.getListCommentDoctor(clinicsRequest);
	}
	
}
