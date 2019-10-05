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
@Table(name = "faculty")
public class Faculty extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clinic")
    private Clinic clinic;
	
	@ManyToMany(fetch = FetchType.LAZY,
        cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
        },
        mappedBy = "faculties"
    )
	private Set<User> users = new HashSet<>();
	
	public Faculty() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equals(Object obj) {
        if (obj instanceof Faculty) {
        	Faculty another = (Faculty) obj;
            if (this.getId().equals(another.getId())) {
                return true;
            }
        }
        return false;
    }
}
