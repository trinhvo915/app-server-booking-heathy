package com.foody.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "province")
public class Province  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String _name;
	
	private String _code;
	
	public Province() {
		super();
	}

	public Province(String id, String _name, String _code) {
		super();
		this.id = id;
		this._name = _name;
		this._code = _code;
	}

	public String getId() {
		return id;
	}

	public String get_name() {
		return _name;
	}

	public String get_code() {
		return _code;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public void set_code(String _code) {
		this._code = _code;
	}
	
}
