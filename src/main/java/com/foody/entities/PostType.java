package com.foody.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="post_type")
public class PostType extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_post")
    private Post post;
	
	public PostType() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostType postType = (PostType) o;
        return Objects.equals(this.getId(), postType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
