package com.djb.highway.road.dao;

import com.djb.highway.framework.dao.BaseDAO;
import com.djb.highway.road.entity.InfoBoardEntity;

public interface IInfoBoardDao extends BaseDAO<InfoBoardEntity> {
    public static final String GETINFOBOARDPOINTLIST = "getInfoBoardPointList";
}
