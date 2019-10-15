package com.foody.dto;

import org.hibernate.annotations.Type;

import com.foody.entities.User;

public class CommentRequest {
	
	@Type(type="text")
	private String content;
	
//	private Set<User> experts = new HashSet<>();
	private User expert;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getExpert() {
		return expert;
	}

	public void setExpert(User expert) {
		this.expert = expert;
	}
	
}
