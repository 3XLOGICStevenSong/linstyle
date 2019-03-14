package com.djb.highway.bus.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.bus.entity.BusUserEntity;
import com.djb.highway.bus.entity.BusGroupEntity;
import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.user.entity.UserEntity;

@Repository("busUserDao")
public class BusUserDaoImpl extends BaseDAOImpl<BusUserEntity> implements
		IBusUserDao {
}
