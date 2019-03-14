package com.djb.highway.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.user.entity.PhoneBookEntity;

@Repository("phoneBookDao")
public class PhoneBookDaoImpl extends BaseDAOImpl<PhoneBookEntity> implements
		IPhoneBookDao {
}
