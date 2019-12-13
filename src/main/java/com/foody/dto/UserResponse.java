package com.foody.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.foody.entities.Attachment;
import com.foody.entities.Clinic;
import com.foody.entities.Role;
import com.foody.entities.User;
import com.foody.entities.enums.UserGender;
import com.foody.utils.AttacchmetFunction;

public class UserResponse {
	private String id;
	
	private String username;

	private String fullName;
	
	private Date birthday;
	
	private UserGender gender;
	
	private Integer age;
	
	private String check;
	
	private Attachment attachmentPerson ;
	
	Set<Role> roles = new HashSet<>();
	
	Set<Clinic> clinic = new HashSet<>();
	
	List<UserClinicView> clinicViews = new ArrayList<UserClinicView>();
	
	public UserResponse() {
		super();
	}

	public UserResponse(User user,List<Clinic> clinincs) {
		List<UserClinicView> clinicViews = new ArrayList<UserClinicView>();
		this.id = user.getId();
		this.username = user.getUsername();
		this.fullName = user.getFullName();
		this.birthday = user.getBirthday();
		this.gender = user.getGender();
		this.age = user.getAge();
		this.roles = user.getRoles();
		Attachment attachment = AttacchmetFunction.getAttachmentPerson(user.getAttachments(), "DAIDIEN");
		for (Clinic clinic : clinincs) {
			UserClinicView clinicView = new UserClinicView(user.getId(),clinic.getId());
			clinicViews.add(clinicView);
		}
		this.setClinicViews(clinicViews);
		this.attachmentPerson = attachment;
	}
	
	public UserResponse(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.fullName = user.getFullName();
		this.birthday = user.getBirthday();
		this.gender = user.getGender();
		this.age = user.getAge();
		this.roles = user.getRoles();
		Attachment attachment = AttacchmetFunction.getAttachmentPerson(user.getAttachments(), "DAIDIEN");
		this.attachmentPerson = attachment;
	}
	
	public List<UserClinicView> getClinicViews() {
		return clinicViews;
	}

	public void setClinicViews(List<UserClinicView> clinicViews) {
		this.clinicViews = clinicViews;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Attachment getAttachmentPerson() {
		return attachmentPerson;
	}

	public void setAttachmentPerson(Attachment attachmentPerson) {
		this.attachmentPerson = attachmentPerson;
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
