package com.foody.services;

import com.foody.dto.ClinicsRequest;
import com.foody.dto.RateRequest;
import com.foody.payload.DataResponse;
import com.foody.security.UserPrincipal;

public interface RateService {
	
	DataResponse addRate(UserPrincipal currentUser, RateRequest rateRequest);
	
	DataResponse getRatetDoctor(ClinicsRequest clinicsRequest);
}
