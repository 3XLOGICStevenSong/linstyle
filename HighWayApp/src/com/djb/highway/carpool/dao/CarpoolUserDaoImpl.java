package com.djb.highway.carpool.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.carpool.entity.CarpoolUserEntity;
import com.djb.highway.framework.dao.BaseDAOImpl;

@Repository("carpoolUserDao")
public class CarpoolUserDaoImpl extends BaseDAOImpl<CarpoolUserEntity> implements
		ICarpoolUserDao {
}
