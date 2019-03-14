package com.djb.highway.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.user.entity.OftenUsedRoadEntity;

@Repository("oftenUsedRoadDao")
public class OftenUsedRoadDaoImpl extends BaseDAOImpl<OftenUsedRoadEntity> implements
		IOftenUsedRoadDao {
}
