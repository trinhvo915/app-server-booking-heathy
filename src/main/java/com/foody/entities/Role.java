package com.foody.entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "role")
public class Role extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Please provide a Role Name")
    @Valid
    @Column(unique = true)
	private String name;
	
    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
        },
        mappedBy = "roles"
    )
    private Set<User> users = new HashSet<>();
    
	public Role() {

	}
	
	public Role(String id, String name) {
		this.setId(id);
		this.name = name;
	}
	public Role(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(this.getId(), role.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

	@Override
	public String toString() {
		return "Role [name=" + name + ", id=" + getId() + "]";
	}
}
