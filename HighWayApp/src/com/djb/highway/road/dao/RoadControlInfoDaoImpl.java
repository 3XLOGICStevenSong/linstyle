package com.djb.highway.road.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.road.entity.RoadControlInfoEntity;

@Repository("roadControlInfoDao")
public class RoadControlInfoDaoImpl extends BaseDAOImpl<RoadControlInfoEntity>
		implements IRoadControlInfoDao {
}
