package com.djb.highway.road.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.road.entity.ServiceAreaEntity;

@Repository("serviceAreaDao")
public class ServiceAreaDaoImpl extends BaseDAOImpl<ServiceAreaEntity> implements
		IServiceAreaDao {
}
