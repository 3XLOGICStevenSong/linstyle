package com.djb.highway.carpool.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.carpool.entity.CarpoolRouteEntity;
import com.djb.highway.framework.dao.BaseDAOImpl;

@Repository("carpoolRouteDao")
public class CarpoolRouteDaoImpl extends BaseDAOImpl<CarpoolRouteEntity> implements ICarpoolRouteDao {
}
