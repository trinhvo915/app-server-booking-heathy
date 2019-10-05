package com.foody.entities;

import java.io.Serializable;

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

	// Gioi Thieu Chung
	// Gioi Thieu Co So vat chat
	// Thong tin gia kham
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
	
	public boolean equals(Object obj) {
        if (obj instanceof PostType) {
        	PostType another = (PostType) obj;
            if (this.getId().equals(another.getId())) {
                return true;
            }
        }
        return false;
    }
}
