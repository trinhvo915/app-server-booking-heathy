package com.foody.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name ="price")
public class Price extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private String totalPrice;
	
	@Type(type="text")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clinic")
    private Clinic clinic;
	
	public Price() {
		
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(this.getId(), price.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
