package com.foody.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="post_type")
public class PostType extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;

	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "postTypes")
    private Set<Post> posts = new HashSet<Post>();
	
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
