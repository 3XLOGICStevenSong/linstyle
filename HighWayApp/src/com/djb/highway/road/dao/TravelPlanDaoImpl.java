package com.djb.highway.road.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.road.entity.TravelPlanEntity;

@Repository("travelPlanDao")
public class TravelPlanDaoImpl extends BaseDAOImpl<TravelPlanEntity> implements
		ITravelPlanDao {
}
