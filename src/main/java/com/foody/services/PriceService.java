package com.foody.services;

import java.util.List;

import com.foody.dto.PriceRequest;
import com.foody.payload.DataResponse;

public interface PriceService {
	DataResponse addPricesClinic(String idClinic, List<PriceRequest> priceRequests);
	
	DataResponse getPricesClinic(String idClinic);
}
