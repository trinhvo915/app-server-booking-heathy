package com.foody.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="price")
public class Price extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private String totalPrice;
	
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
	
	public boolean equals(Object obj) {
        if (obj instanceof Price) {
        	Price another = (Price) obj;
            if (this.getId().equals(another.getId())) {
                return true;
            }
        }
        return false;
    }
}
