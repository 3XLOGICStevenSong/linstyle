package com.djb.highway.road.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.road.entity.VersionMstEntity;

@Repository("versionMstDao")
public class VersionMstDaoImpl extends BaseDAOImpl<VersionMstEntity> implements IVersionMstDao {
}
