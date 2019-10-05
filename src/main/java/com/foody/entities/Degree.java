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
@Table(name ="degree")
public class Degree extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY,
        cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
        },
        mappedBy = "degrees"
    )
    private Set<User> users = new HashSet<>();
	
	public Degree() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equals(Object obj) {
        if (obj instanceof Degree) {
        	Degree another = (Degree) obj;
            if (this.getId().equals(another.getId())) {
                return true;
            }
        }
        return false;
    }
}
