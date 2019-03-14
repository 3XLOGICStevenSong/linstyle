package com.djb.highway.bus.dao;

import org.springframework.stereotype.Repository;


import com.djb.highway.bus.entity.BusGroupEntity;
import com.djb.highway.framework.dao.BaseDAOImpl;


@Repository("busGroupDao")
public class BusGroupDaoImpl extends BaseDAOImpl<BusGroupEntity> implements
		IBusGroupDao {
}
