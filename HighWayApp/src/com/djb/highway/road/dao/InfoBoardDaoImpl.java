package com.djb.highway.road.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.road.entity.InfoBoardEntity;

@Repository("infoBoardDao")
public class InfoBoardDaoImpl extends BaseDAOImpl<InfoBoardEntity> implements
		IInfoBoardDao {
}
