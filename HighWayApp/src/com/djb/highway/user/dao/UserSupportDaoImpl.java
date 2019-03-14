package com.djb.highway.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.user.entity.UserSupportEntity;

@Repository("userSupportDao")
public class UserSupportDaoImpl extends BaseDAOImpl<UserSupportEntity> implements
		IUserSupportDao {
}
