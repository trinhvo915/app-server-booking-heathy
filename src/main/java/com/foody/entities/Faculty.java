package com.foody.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "faculty")
public class Faculty extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;

	@ManyToMany(fetch = FetchType.LAZY,
        cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
        },
        mappedBy = "faculties"
    )
	private Set<Clinic> clinics = new HashSet<>();
	
	
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
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(this.getId(), faculty.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

	@Override
	public String toString() {
		return "Faculty [name=" + name + "]";
	}
    
}
