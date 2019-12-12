package com.foody.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clinic")
public class Clinic extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String address;
	
	private String addressQuery ;
	
	private String latitude;
	
	private String longitude;
	  
    @ManyToMany(fetch = FetchType.LAZY,
			cascade = {
				CascadeType.PERSIST,
				CascadeType.MERGE
		})
	@JoinTable(name = "user_clinic",
		joinColumns = { @JoinColumn(name = "id_clinic") },
		inverseJoinColumns = { @JoinColumn(name = "id_user")}
	)
    private Set<User> users = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "clinic")
    private Set<Post> posts = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
		cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "clinic_faculty",
		joinColumns = { @JoinColumn(name = "id_clinic") },
		inverseJoinColumns = { @JoinColumn(name = "id_faculty")}
	)
    private Set<Faculty> faculties = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "clinic")
    private Set<Price> prices = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "clinic")
    private Set<Rate> rateUsers = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "clinic")
    private Set<Comment> commentUsers = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "clinic")
    private Set<Booking> bookingUsers = new HashSet<>();
    
	public Clinic() {
		
	}

	public Clinic(String name, String address, String latitude, String longitude, String addressQuery) {
		super();
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.addressQuery = addressQuery;
	}

	public String getAddressQuery() {
		return addressQuery;
	}

	public void setAddressQuery(String addressQuery) {
		this.addressQuery = addressQuery;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(Set<Faculty> faculties) {
		this.faculties = faculties;
	}
    
	public Set<Price> getPrices() {
		return prices;
	}

	public void setPrices(Set<Price> prices) {
		this.prices = prices;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clinic clinic = (Clinic) o;
        return Objects.equals(this.getId(), clinic.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

}
