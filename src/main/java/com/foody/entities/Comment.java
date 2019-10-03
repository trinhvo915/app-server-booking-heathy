package com.foody.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="comment")
public class Comment extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String content;

//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_expert")
//    private User expert;
	
	@ManyToMany(fetch = FetchType.LAZY,
        cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
        },
        mappedBy = "commentExperts"
    )
    private Set<User> experts = new HashSet<>();
	
	public Comment() {

	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
