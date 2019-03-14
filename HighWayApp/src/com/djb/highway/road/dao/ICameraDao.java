package com.djb.highway.road.dao;

import com.djb.highway.framework.dao.BaseDAO;
import com.djb.highway.road.entity.CameraEntity;

public interface ICameraDao extends BaseDAO<CameraEntity> {

    public static final String GETCAMERALISTFORPLAZ = "getcameralistforplaz";
    public static final String GETCAMERAPOINTLIST = "getcameraPointList";
}
