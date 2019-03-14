package com.djb.highway.road.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.road.entity.PlazaEntity;

@Repository("plazaDao")
public class PlazaDaoImpl extends BaseDAOImpl<PlazaEntity> implements
		IPlazaDao {
}
