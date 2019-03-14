package com.djb.highway.carpool.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.carpool.entity.DriverRouteEntity;
import com.djb.highway.framework.dao.BaseDAOImpl;

@Repository("driverRouteDao")
public class DriverRouteDaoImpl extends BaseDAOImpl<DriverRouteEntity> implements IDriverRouteDao {
}
