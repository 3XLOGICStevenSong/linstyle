package com.djb.highway.carpool.dao;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.djb.highway.carpool.entity.PassengerRouteEntity;
import com.djb.highway.framework.dao.BaseDAOImpl;


@Repository("passengerRouteDao")
public class PassengerRouteDaoImpl extends BaseDAOImpl<PassengerRouteEntity> implements
		IPassengerRouteDao {
    
    public static final String FINDLISTORDERBYTIME="findListOrderByTime";

    @Override
    public List<PassengerRouteEntity> findListOrderByTime(Object parameter) {
        return findList(FINDLISTORDERBYTIME, parameter);
    }
    
}
