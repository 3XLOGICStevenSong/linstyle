package com.djb.highway.road.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.road.entity.InOutSchematicEntity;



@Repository("inOutSchematicDao")
public class InOutSchematicDaoImpl extends BaseDAOImpl<InOutSchematicEntity> implements
		IInOutSchematicDao {
}
