package com.djb.highway.road.dao;

import com.djb.highway.framework.dao.BaseDAO;
import com.djb.highway.road.entity.RoadControlInfoEntity;

public interface IRoadControlInfoDao extends BaseDAO<RoadControlInfoEntity> {
    
    public static final String FINDPAGELISTBYCONDITION = "findPageListByCondition";

}
