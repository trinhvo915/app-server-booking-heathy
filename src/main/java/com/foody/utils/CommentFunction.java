package com.foody.utils;

import java.util.ArrayList;
import java.util.List;
import com.foody.dto.CommentResponse;
import com.foody.entities.Attachment;
import com.foody.entities.Comment;

public class CommentFunction {

	public static List<CommentResponse> getCommentDoctor(List<Comment> commentExperts) {
		List<CommentResponse> commentResponses =new ArrayList<CommentResponse>();
		
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
