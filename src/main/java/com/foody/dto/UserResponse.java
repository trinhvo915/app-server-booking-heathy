package com.foody.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.foody.entities.Clinic;
import com.foody.entities.Role;
import com.foody.entities.User;
import com.foody.entities.enums.UserGender;

public class UserResponse {
	
	private String username;

	private String fullName;
	
	private Date birthday;
	
	private UserGender gender;
	
	private Integer age;
	
	private String check;
	
	Set<Role> roles = new HashSet<>();
	
	Set<Clinic> clinic = new HashSet<>();
	
	public UserResponse() {
		super();
	}

	public UserResponse(User user) {
		this.username = user.getUsername();
		this.fullName = user.getFullName();
		this.birthday = user.getBirthday();
		this.gender = user.getGender();
		this.age = user.getAge();
		this.roles = user.getRoles();
//		this.clinic = user.getClinics();
	}
	
	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public Set<Clinic> getClinic() {
		return clinic;
	}

	public void setClinic(Set<Clinic> clinic) {
		this.clinic = clinic;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
