package com.foody.entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
