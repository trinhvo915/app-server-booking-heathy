package com.foody.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foody.dto.PostRequest;
import com.foody.entities.Clinic;
import com.foody.entities.Post;
import com.foody.entities.PostType;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.repository.ClinicRepository;
import com.foody.repository.PostRepository;
import com.foody.repository.PostTypeRepository;
import com.foody.security.UserPrincipal;
import com.foody.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	ClinicRepository clinicRepository;
	
	@Autowired
	PostTypeRepository postTypeRepository;
	
	@Override
	public DataResponse addPostForClinic(PostRequest postRequest, UserPrincipal currentUser) {
		Clinic clinic = clinicRepository.getOne(postRequest.getIdClinic());
		
		if(clinic != null) {
			PostType postType = postTypeRepository.getOne(postRequest.getIdTypePost());
			Post post = new Post();
			post.setContent(postRequest.getContent());
			post.setClinic(clinic);
			post.setPostTypes(postType);
			postRepository.save(post);
			
			return new DataResponse(true, new Data("Tạo bài viết thành công !",HttpStatus.OK.value()));
		}
		
		return new DataResponse(false, new Data("Phòng khám không tồn tại !",HttpStatus.BAD_REQUEST.value()));
	}

	@Override
	public DataResponse getPostTypePostForClinic(String idClinic, String typePost) {
		Set<Post> posts = postRepository.getPostFollowTypePost(idClinic, typePost);
		
		if(!posts.isEmpty()) {
			return new DataResponse(true, new Data("Lấy thành công !",HttpStatus.OK.value(),posts));
		}
		return new DataResponse(false, new Data("Lấy không thành công !",HttpStatus.BAD_REQUEST.value()));
	}

}
