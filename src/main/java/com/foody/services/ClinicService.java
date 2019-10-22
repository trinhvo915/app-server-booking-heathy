package com.foody.services;

import com.foody.dto.ClinicRequest;
import com.foody.payload.DataResponse;
import com.foody.security.UserPrincipal;

public interface ClinicService {

	DataResponse addClinicCurrentDoctor(UserPrincipal currentUser,ClinicRequest clinicRequest);

	DataResponse addDoctorIntoClinic(UserPrincipal currentUser, String idClinic, String usernameOrEmail);
}