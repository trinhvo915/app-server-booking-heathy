package com.foody.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.foody.dto.RateResponse;
import com.foody.entities.Rate;
import com.foody.entities.enums.NumberStar;

public class RateFunction {

	public static Double getRateDoctor(Set<Rate> rateExperts) {
		Double countRate = 0.0; int fiveRate = 0 ; int fourRate = 0;
		int threeRate = 0; int twoRate = 0; int oneRate = 0;
		for (Rate rate : rateExperts) {
			if(rate.getNumberStar() == NumberStar.FIVE) {
				fiveRate++;
			}
			if(rate.getNumberStar() == NumberStar.FOUR) {
				fourRate++;
			}
			if(rate.getNumberStar() == NumberStar.THREE) {
				threeRate++;
			}
			if(rate.getNumberStar() == NumberStar.TWO) {
				twoRate++;
			}
			if(rate.getNumberStar() == NumberStar.ONE) {
				oneRate++;
			}
		}
		
		Double sumRate = (double) (fiveRate + fourRate + threeRate + twoRate + oneRate);
		if(sumRate == 0) {
			countRate = 1.0;
		}else {
			countRate = (5.0*fiveRate + 4.0*fourRate + 3.0*threeRate + 2.0*twoRate + 1.0*oneRate) / sumRate;
		}
		
		if(countRate>= 0.5 && countRate <1) {
			countRate = 0.5;
		}else if(countRate >=1.5 && countRate < 2) {
			countRate = 1.5;
		}else if(countRate >=2.5 && countRate < 3) {
			countRate = 2.5;
		}else if(countRate >=3.5 && countRate < 4) {
			countRate = 3.5;
		}else if(countRate >=4.5 && countRate < 5) {
			countRate = 4.5;
		}
		return countRate;
	}
	
	
	public static List<RateResponse> getRateResponses(Set<Rate> rateExperts) {
		List<RateResponse> rateResponses = new ArrayList<RateResponse>();
		for (Rate rate : rateExperts) {
			RateResponse response = new RateResponse();
			response.setNumberStar(rate.getNumberStar());
			response.setValue(rate.getValue());
			response.setIdUser(rate.getUser().getId());
			response.setIdDoctor(rate.getExpert().getId());
			rateResponses.add(response);
		}
		return rateResponses;
	}
}
