package com.foody.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foody.entities.Province;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.repository.ProvinceRepository;
import com.foody.services.ProvinceService;

import java.util.HashSet;
import java.util.Set;
@Service
public class ProvinceServiceImpl implements ProvinceService{

	@Autowired
	ProvinceRepository provinceRepository;
	
	@Override
	public DataResponse getProvinces() {
		Set<Province>  provinces = new HashSet<Province>(provinceRepository.findAll());
		return new DataResponse(true, new Data("Lấy thành công !!",HttpStatus.OK.value(),provinces));
	}

}
