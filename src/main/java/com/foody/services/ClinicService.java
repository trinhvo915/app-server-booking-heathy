package com.foody.services;

import com.foody.dto.ClinicRequest;
import com.foody.payload.DataResponse;

public interface ClinicService {

	DataResponse addClinicCurrentDoctor(String idUser,ClinicRequest clinicRequest);
}
