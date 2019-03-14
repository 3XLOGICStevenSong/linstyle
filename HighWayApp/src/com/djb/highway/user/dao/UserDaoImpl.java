package com.djb.highway.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.user.entity.UserEntity;

@Repository("userDao")
public class UserDaoImpl extends BaseDAOImpl<UserEntity> implements
		IUserDao {
}
