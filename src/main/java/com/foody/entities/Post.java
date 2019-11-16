package com.foody.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "post")
public class Post extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Type(type="text")
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clinic")
    private Clinic clinic;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "post")
    private Set<Attachment> attachments = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_post_type")
    private PostType postTypes ;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;
	
	public Post() {
		
	}

	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	public void setPostTypes(PostType postTypes) {
		this.postTypes = postTypes;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(this.getId(), post.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
