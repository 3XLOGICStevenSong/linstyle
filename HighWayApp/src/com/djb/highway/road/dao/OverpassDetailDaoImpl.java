package com.djb.highway.road.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.road.entity.OverpassDetailEntity;

@Repository("overpassDetailDao")
public class OverpassDetailDaoImpl extends BaseDAOImpl<OverpassDetailEntity>
		implements IOverpassDetailDao {
}
