package com.djb.highway.road.service;

import com.djb.highway.road.dto.travel.TravelPlanParamDTO;
import com.djb.highway.road.dto.travel.TravelScreenDTO;

public interface ITravelPlanRelultService {
	public TravelScreenDTO getTravelPlanData(TravelPlanParamDTO travelPlanParamDTO);

}
