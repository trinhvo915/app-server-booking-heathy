package com.foody.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="expert_code")
public class ExpertCode extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	public ExpertCode() {
		
	}
}
