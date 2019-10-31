package com.foody.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name ="attachmentt_type")
public class AttachmentType  extends AuditEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;

	@ManyToMany(fetch = FetchType.LAZY,
        cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
        },
        mappedBy = "attachmentTypes"
    )
    private Set<Attachment> attachments = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AttachmentType [name=" + name + ", attachments=" + attachments + "]";
	}
	
}
