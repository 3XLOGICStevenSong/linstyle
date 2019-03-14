package com.djb.highway.road.dao;

import com.djb.highway.framework.dao.BaseDAO;
import com.djb.highway.road.entity.OverpassEntity;

public interface IOverpassDao extends BaseDAO<OverpassEntity> {

	public static final String FIND_OVERPASSDETAILLIST_BYCONDITION = "findOverpassDetailListByCondition";

	public static final String GET_OVERPASSDETAIL_OBJECT = "getOverpassDetailObject";

}
