package com.foody.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foody.entities.ExpertCode;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.repository.ExpertCodeRepository;
import com.foody.services.ExpertCodeService;
import com.foody.utils.CheckNumber;
import com.foody.utils.Constant;

@Service
public class ExpertCodeServiceImpl implements ExpertCodeService{

	@Autowired
	ExpertCodeRepository expertCodeRepository;
	
	@Override
	public DataResponse saveTokenCode(String lenValue) {
		boolean checkNumber = CheckNumber.checkNumber(lenValue);
		if(checkNumber == false) {
			return new DataResponse(false, new Data(Constant.CREATE_TOKEN_CODE_UNSUCCESS,HttpStatus.BAD_REQUEST.value(),null));
		}
		Integer len = Integer.parseInt(lenValue);
		for(int i = 0; i < len; i++) {
			ExpertCode expertCode = new ExpertCode();
			expertCodeRepository.save(expertCode);
		}
		return new DataResponse(true, new Data(Constant.CREATE_TOKEN_CODE_SUCCESS,HttpStatus.CREATED.value(),null));
	}

	@Override
	public DataResponse getTokenCode(String id) {
		ExpertCode expertCode = expertCodeRepository.getExpertCode(id,true);
		if(expertCode != null) {
			return new DataResponse(true, new Data(Constant.GET_TOKEN_CODE_SUCCESS,HttpStatus.OK.value(),expertCode));
		}
		return new DataResponse(false, new Data(Constant.GET_TOKEN_CODE_UNSUCCESS,HttpStatus.BAD_REQUEST.value(),expertCode));
	}

	@Override
	public DataResponse deleteCode(String idCode) {
		ExpertCode code = expertCodeRepository.getOne(idCode);
		if(code != null) {
			expertCodeRepository.delete(code);
			return new DataResponse(true, new Data("Xóa thành công !",HttpStatus.OK.value()));
		}
		return new DataResponse(false, new Data("Xóa không thành công !",HttpStatus.BAD_REQUEST.value()));
	}
}
