package com.djb.highway.road.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.road.entity.MapLocationEntity;

@Repository("mapLocationDao")
public class MapLocationDaoImpl extends BaseDAOImpl<MapLocationEntity> implements
		IMapLocationDao {
}
