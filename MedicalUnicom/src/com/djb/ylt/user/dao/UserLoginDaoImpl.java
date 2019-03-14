package com.djb.ylt.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.ylt.framework.dao.BaseDAOImpl;
import com.djb.ylt.user.entity.UserLoginEntity;



@Repository("userLoginDao")
public class UserLoginDaoImpl extends BaseDAOImpl<UserLoginEntity> implements
		IUserLoginDao {
}
