package com.foody.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foody.entities.District;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.repository.DistrictRepository;
import com.foody.services.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService{

	@Autowired
	DistrictRepository districtRepository;
	
	@Override
	public DataResponse getDistricts(String id_province) {
		Set<District> districts = districtRepository.getDistricts(id_province);
		return new DataResponse(true, new Data("Lấy thành công !!",HttpStatus.OK.value(),districts));
	}

}
