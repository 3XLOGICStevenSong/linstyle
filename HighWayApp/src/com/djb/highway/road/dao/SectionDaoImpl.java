package com.djb.highway.road.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.road.entity.SectionEntity;

@Repository("sectionDao")
public class SectionDaoImpl extends BaseDAOImpl<SectionEntity> implements
		ISectionDao {
}
