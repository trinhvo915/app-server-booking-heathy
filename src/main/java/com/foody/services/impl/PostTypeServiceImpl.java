package com.foody.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foody.entities.PostType;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.repository.PostTypeRepository;
import com.foody.services.PostTypeService;

@Service
public class PostTypeServiceImpl implements PostTypeService{

	@Autowired
	PostTypeRepository postTypeRepository;
	
	@Override
	public DataResponse getAllPostType() {
		List<PostType> postTypes = postTypeRepository.findAll();
		return new DataResponse(true, new Data("Tạo bài viết thành công !",HttpStatus.OK.value(),postTypes));
	}

}
