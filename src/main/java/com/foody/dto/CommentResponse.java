package com.foody.dto;

import java.util.Date;

import com.foody.entities.Attachment;

public class CommentResponse {
	
	private String id;
	
	private String content;
	
	private Date createAt;
	
	private Date updateAt;

	private String createdBy;
	
	private String updatedBy  ;
	
	private Attachment attachment;

	public CommentResponse() {
		
	}

	public String getId() {
		return id;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public String getContent() {
		return content;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}
