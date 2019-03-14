package com.djb.highway.carpool.dao;


import java.util.List;

import com.djb.highway.carpool.entity.PassengerRouteEntity;
import com.djb.highway.framework.dao.BaseDAO;

public interface IPassengerRouteDao extends BaseDAO<PassengerRouteEntity> {
    public static final String FINDLISTBTSEARCH = "findListBySearch";
    public static final String GETOBJECTBYSTATE="getObjectByState";

    public List<PassengerRouteEntity> findListOrderByTime(Object parameter);
}
