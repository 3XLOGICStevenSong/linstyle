package com.djb.highway.road.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.road.entity.HighWayEntity;

@Repository("highWayDao")
public class HighWayDaoImpl extends BaseDAOImpl<HighWayEntity> implements
		IHighWayDao {
}
