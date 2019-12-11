package com.foody.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "district")
public class District implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String _name;
	
	private String _prefix;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_province_id")
    private Province province;
	
	public String getId() {
		return id;
	}

	public String get_name() {
		return _name;
	}

	public String get_prefix() {
		return _prefix;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public void set_prefix(String _prefix) {
		this._prefix = _prefix;
	}
	
}
