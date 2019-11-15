package com.foody.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foody.dto.ClinicsRequest;
import com.foody.dto.PostRequest;
import com.foody.payload.DataResponse;
import com.foody.security.CurrentUser;
import com.foody.security.UserPrincipal;
import com.foody.services.PostService;

@RestController
@RequestMapping("api/posts")
public class PostController {

	@Autowired
	PostService postService;
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public DataResponse addPostForClinic(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody PostRequest postRequest){
		return postService.addPostForClinic(postRequest, currentUser);
	}
	
	@RequestMapping(value= "{id_clinic}/{id_post_type}",method = RequestMethod.GET, produces = "application/json")
	public DataResponse getCommentDoctor(@CurrentUser UserPrincipal currentUser, @PathVariable("id_clinic") String id_clinic,@PathVariable("id_post_type") String id_post_type){
		  return postService.getPostTypePostForClinic(id_clinic, id_post_type);
	}
}
