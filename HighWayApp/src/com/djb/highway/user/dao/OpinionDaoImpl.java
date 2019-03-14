package com.djb.highway.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.user.entity.OpinionEntity;

@Repository("opinionDao")
public class OpinionDaoImpl extends BaseDAOImpl<OpinionEntity> implements
		IOpinionDao {
}
