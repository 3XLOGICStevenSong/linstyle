package com.djb.highway.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.user.entity.UserDeployPicEntity;

@Repository("userDeployPicDao")
public class UserDeployPicDaoImpl extends BaseDAOImpl<UserDeployPicEntity> implements
		IUserDeployPicDao {
}
