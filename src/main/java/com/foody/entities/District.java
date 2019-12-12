package com.foody.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "district")
public class District implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String _name;
	
	private String _prefix;

	private String _province_id;

	public District() {
		super();
	}

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

	public String get_province_id() {
		return _province_id;
	}

	public void set_province_id(String _province_id) {
		this._province_id = _province_id;
	}
	
}
