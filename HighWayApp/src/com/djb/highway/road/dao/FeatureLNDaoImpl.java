package com.djb.highway.road.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.road.entity.FeatureLNEntity;


@Repository("featureLNDao")
public class FeatureLNDaoImpl extends BaseDAOImpl< FeatureLNEntity> implements
		IFeatureLNDao {
}
