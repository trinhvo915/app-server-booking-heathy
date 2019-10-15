package com.foody.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name ="comment")
public class Comment extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Type(type="text")
	private String content;
	
//	@ManyToMany(fetch = FetchType.LAZY,
//        cascade = {
//                CascadeType.PERSIST,
//                CascadeType.MERGE
//        },
//        mappedBy = "commentExperts"
//    )
//    private Set<User> experts = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_expert")
    private User expert;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;
	
	public Comment() {

	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getExpert() {
		return expert;
	}

	public void setExpert(User expert) {
		this.expert = expert;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(this.getId(), comment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
