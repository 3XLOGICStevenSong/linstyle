package com.djb.highway.carpool.dao;

import com.djb.highway.carpool.entity.CarpoolUserEntity;
import com.djb.highway.framework.dao.BaseDAO;

public interface ICarpoolUserDao extends BaseDAO<CarpoolUserEntity> {
    public static final String COUNTCARPOOLUSRENUMBER = "countCarpoolUserNumber";

}
