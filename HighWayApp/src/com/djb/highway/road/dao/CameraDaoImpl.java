package com.djb.highway.road.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.road.entity.CameraEntity;

@Repository("cameraDao")
public class CameraDaoImpl extends BaseDAOImpl<CameraEntity> implements
		ICameraDao {
}
