package com.djb.highway.carpool.dao;

import com.djb.highway.carpool.entity.DriverRouteEntity;
import com.djb.highway.framework.dao.BaseDAO;

public interface IDriverRouteDao extends BaseDAO<DriverRouteEntity> {
    public static final String GETVALIDLIST = "getValidList";
}
