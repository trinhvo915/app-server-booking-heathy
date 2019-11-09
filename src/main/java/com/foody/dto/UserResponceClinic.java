package com.foody.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.foody.entities.Booking;
import com.foody.entities.Degree;
import com.foody.entities.Faculty;
import com.foody.entities.enums.UserGender;

public class UserResponceClinic {
	private String id;
	
	private Date createAt;
	
	private Date updateAt;
	
	private String createdBy;
	
	private String updatedBy  ;
	
	private String deletedBy ;
	
	private String username;
	
	private String fullName;
	
	private Date birthday;
	
	private UserGender gender;
	
	private Integer age;
	
	private Integer badPoint;
	
	private String email;
	
	private String address;
	
	private String mobile;
	
	private String about;
	
	private String facebook;
	
	private String code;
	
	private Set<Faculty> faculties = new HashSet<>();
	
	private Set<Booking> bookingExperts = new HashSet<>();
	
	private Set<Degree> degrees = new HashSet<>();
	
}
