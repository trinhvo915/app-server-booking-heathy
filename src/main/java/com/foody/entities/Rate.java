package com.foody.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.foody.entities.enums.NumberStar;

@Entity
@Table(name ="rate")
public class Rate extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private NumberStar numberStar;
	
	private Double value;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_expert")
    private User expert;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clinic")
    private Clinic clinic;
	
	public Rate() {
		
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public User getExpert() {
		return expert;
	}

	public User getUser() {
		return user;
	}

	public NumberStar getNumberStar() {
		return numberStar;
	}

	public void setNumberStar(NumberStar numberStar) {
		this.numberStar = numberStar;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public void setExpert(User expert) {
		this.expert = expert;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate = (Rate) o;
        return Objects.equals(this.getId(), rate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
