package com.foody.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="expert_code")
public class ExpertCode extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private UUID tokenCode;

	public ExpertCode(UUID tokenCode) {
		this.tokenCode = tokenCode;
	}

	public UUID getTokenCode() {
		return tokenCode;
	}

	public void setTokenCode(UUID tokenCode) {
		this.tokenCode = tokenCode;
	}
}
