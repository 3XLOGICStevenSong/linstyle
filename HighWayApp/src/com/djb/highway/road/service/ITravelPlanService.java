package com.djb.highway.road.service;

import java.util.List;

import com.djb.highway.road.entity.TravelPlanEntity;

public interface ITravelPlanService {
	public void addTravelPlan(TravelPlanEntity travelPlan);

	public void deleteTravelPlan(TravelPlanEntity travelPlan);

	public void updateTravelPlan(TravelPlanEntity travelPlan);

	public TravelPlanEntity getObject(TravelPlanEntity travelPlan);

	public List<TravelPlanEntity> getTravelPlanList();

	public List<TravelPlanEntity> getTravelPlanList(TravelPlanEntity travelPlan);

}
