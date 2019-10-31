package com.foody.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "attachment")
public class Attachment  extends AuditEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

    private String fileName;

    private String fileType;

    @Lob
    private byte [] data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_post")
    private Post post;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;
    
    @ManyToMany(fetch = FetchType.LAZY,
		cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "attachment_attachmenttype",
		joinColumns = { @JoinColumn(name = "id_attachment") },
		inverseJoinColumns = { @JoinColumn(name = "id_attachmenttype")}
	)
	Set<AttachmentType> attachmentTypes = new HashSet<>();
    
    public Attachment() {

    }

    public Attachment(String fileName, String fileType, byte[] data,User user,Set<AttachmentType> attachmentTypes) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.user = user;
        this.attachmentTypes = attachmentTypes;
    }

	public Set<AttachmentType> getAttachmentTypes() {
		return attachmentTypes;
	}

	public void setAttachmentTypes(Set<AttachmentType> attachmentTypes) {
		this.attachmentTypes = attachmentTypes;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
