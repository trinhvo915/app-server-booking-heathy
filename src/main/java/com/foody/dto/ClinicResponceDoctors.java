package com.foody.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.foody.entities.Attachment;
import com.foody.entities.Faculty;
import com.foody.entities.Price;

public class ClinicResponceDoctors {
	
	private String id;
	
	private String name;
	
	private String address;
	
	private String latitude;
	
	private String longitude;
	
	private Date createAt;
	
	private String createdBy;
	
	private Set<Faculty> faculties = new HashSet<>();
	
	private Set<Price> prices = new HashSet<>();
	
	private Set<Attachment> photoClinics = new HashSet<>();
	
	private Set<UserResponceClinic> userResponceClinics = new HashSet<>();
}
