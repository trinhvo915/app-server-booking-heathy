package com.foody.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="expert_code")
public class ExpertCode extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	public ExpertCode() {
		
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpertCode expertCode = (ExpertCode) o;
        return Objects.equals(this.getId(), expertCode.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
