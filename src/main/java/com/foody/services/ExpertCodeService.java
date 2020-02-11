package com.foody.services;

import com.foody.payload.DataResponse;

public interface ExpertCodeService {
	DataResponse saveTokenCode(String lenValue);
	
	DataResponse getTokenCode(String id);
	
	DataResponse deleteCode(String idCode);
}
