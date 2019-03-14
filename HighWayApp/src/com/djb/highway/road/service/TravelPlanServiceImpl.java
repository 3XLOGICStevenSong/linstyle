package com.djb.highway.road.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.road.dao.ITravelPlanDao;
import com.djb.highway.road.entity.TravelPlanEntity;

@Service("iTravelPlanService")
public class TravelPlanServiceImpl extends BaseService implements ITravelPlanService {

	@Autowired
	@Qualifier("travelPlanDao")
	private ITravelPlanDao travelPlanDao;

	@Override
	public void addTravelPlan(TravelPlanEntity travelPlan) {
		
		try {
			travelPlanDao.insert(travelPlan);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteTravelPlan(TravelPlanEntity travelPlan) {
		
		try {
			travelPlanDao.delete(travelPlan);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void updateTravelPlan(TravelPlanEntity travelPlan) {
		
		try {
			travelPlanDao.update(travelPlan);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public TravelPlanEntity getObject(TravelPlanEntity travelPlan) {
		return travelPlanDao.getObject(travelPlan);
	}

	@Override
	public List<TravelPlanEntity> getTravelPlanList() {
		
		return (List<TravelPlanEntity>) travelPlanDao.findList();
	}


	@Override
	public List<TravelPlanEntity> getTravelPlanList(TravelPlanEntity travelPlan) {
		List<TravelPlanEntity> list = (List<TravelPlanEntity>) travelPlanDao
				.findListByCondition(travelPlan);
		return list;
	}


}
