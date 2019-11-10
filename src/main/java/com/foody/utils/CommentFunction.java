package com.foody.utils;

import java.util.HashSet;
import java.util.Set;

import com.foody.dto.CommentResponse;
import com.foody.entities.Attachment;
import com.foody.entities.Comment;

public class CommentFunction {

	public static Set<CommentResponse> getCommentDoctor(Set<Comment> commentExperts) {
		Set<CommentResponse> commentResponses = new HashSet<CommentResponse>();
		
		for (Comment item : commentExperts) {
			CommentResponse response = new CommentResponse();
			response.setId(item.getId());
			response.setContent(item.getContent());
			response.setCreateAt(item.getCreateAt());
			response.setUpdateAt(item.getUpdateAt());
			response.setCreatedBy(item.getCreatedBy());
			response.setUpdatedBy(item.getUpdatedBy());
			
			Attachment attachment = AttacchmetFunction.getAttachmentPerson(item.getUser().getAttachments(), "DAIDIEN");
			response.setAttachment(attachment);
			
			commentResponses.add(response);
		}
		
		return commentResponses;
	}
	
}
