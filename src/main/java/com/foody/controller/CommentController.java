package com.foody.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public DataResponse registerDoctor(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody CommentRequest commentRequest){
		return commentService.addComment(currentUser,commentRequest);
	}
}
