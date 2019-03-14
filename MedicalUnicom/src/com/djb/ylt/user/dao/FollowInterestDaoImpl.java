package com.djb.ylt.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.ylt.framework.dao.BaseDAOImpl;
import com.djb.ylt.user.entity.FollowInterestEntity;





@Repository("followInterestDao")
public class FollowInterestDaoImpl extends BaseDAOImpl<FollowInterestEntity> implements
		IFollowInterestDao {
}
