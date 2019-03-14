package com.djb.highway.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.user.entity.PhoneBookEntity;
import com.djb.highway.user.entity.PushEntity;

@Repository("pushDao")
public class PushDaoImpl extends BaseDAOImpl<PushEntity> implements
		IPushDao {
}
