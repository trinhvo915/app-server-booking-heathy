package com.foody.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.foody.entities.enums.NumberStar;

@Entity
@Table(name ="rate")
public class Rate extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private NumberStar numberStar;
	
	private Double value;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_expert")
//    private User expert;
	@ManyToMany(fetch = FetchType.LAZY,
        cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
        },
        mappedBy = "rateExperts"
    )
    private Set<User> experts = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;
	
	public Rate() {
		
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
