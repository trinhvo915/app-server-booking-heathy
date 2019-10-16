package com.foody.dto;

import com.foody.entities.User;
import com.foody.entities.enums.NumberStar;

public class RateRequest {
	
	private NumberStar numberStar;
	
	private User expert;
	
	public User getExpert() {
		return expert;
	}

	public void setExpert(User expert) {
		this.expert = expert;
	}

	public NumberStar getNumberStar() {
		return numberStar;
	}

	public void setNumberStar(NumberStar numberStar) {
		this.numberStar = numberStar;
	}
}
