package com.djb.highway.user.dao;

import com.djb.highway.framework.dao.BaseDAO;
import com.djb.highway.user.entity.OftenUsedRoadEntity;

public interface IOftenUsedRoadDao extends BaseDAO<OftenUsedRoadEntity> {
	public static final String GETOFTENUSEDROADLISTBYTIMESTAMP = "getOftenUsedRoadListByTimeStamp";
	public static final String GETMAXTIMESTAMPOBJECT = "getMaxTimeStampObject";

}
