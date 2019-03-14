package com.djb.highway.road.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.road.entity.OverpassEntity;

@Repository("overpassDao")
public class OverpassDaoImpl extends BaseDAOImpl<OverpassEntity> implements IOverpassDao {
}
