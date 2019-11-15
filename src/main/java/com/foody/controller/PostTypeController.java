package com.foody.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.foody.payload.DataResponse;
import com.foody.services.PostTypeService;

@RestController
@RequestMapping("api/post-types")
public class PostTypeController {
	
	@Autowired
	PostTypeService postTypeService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public DataResponse getAllPostType(){
		return postTypeService.getAllPostType();
	}
}
