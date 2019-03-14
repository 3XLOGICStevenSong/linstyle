package com.djb.highway.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.user.entity.UserReviewEntity;

@Repository("userReviewDao")
public class UserReviewDaoImpl extends BaseDAOImpl<UserReviewEntity> implements
		IUserReviewDao {
}
