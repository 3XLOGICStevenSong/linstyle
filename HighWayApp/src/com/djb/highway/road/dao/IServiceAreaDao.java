package com.djb.highway.road.dao;

import com.djb.highway.framework.dao.BaseDAO;
import com.djb.highway.road.entity.ServiceAreaEntity;

public interface IServiceAreaDao extends BaseDAO<ServiceAreaEntity> {
	public static final String GETOBJECTBYLINKCODE = "getObjectByLinkCode";
	 public static final String GETSERVICEAREAPOINTlIST = "getServiceAreaPointList";
}
