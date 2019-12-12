package com.foody.services;

import com.foody.payload.DataResponse;

public interface DistrictService {
	DataResponse getDistricts(String id_province);
}
