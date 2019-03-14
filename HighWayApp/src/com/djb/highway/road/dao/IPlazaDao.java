package com.djb.highway.road.dao;

import com.djb.highway.framework.dao.BaseDAO;
import com.djb.highway.road.entity.PlazaEntity;

public interface IPlazaDao extends BaseDAO<PlazaEntity> {
    public static final String FINDLISTBYROADNAMES = "findListByRoadNames";
    public static final String GETPLAZLISTFORCAMERASTAKE = "getPlazListForCameraStake";
    public static final String GETPLAZAPOINTlIST = "getPlazaPointList";
}
