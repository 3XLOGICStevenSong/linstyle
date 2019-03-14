package com.djb.highway.road.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.road.entity.TmServicesEntity;

@Repository("tmServicesDao")
public class TmServicesDaoImpl extends BaseDAOImpl<TmServicesEntity> implements
		ITmServicesDao {
}
