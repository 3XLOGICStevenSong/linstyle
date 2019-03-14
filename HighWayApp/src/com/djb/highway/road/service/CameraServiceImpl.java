package com.djb.highway.road.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.road.dao.ICameraDao;
import com.djb.highway.road.entity.CameraEntity;

@Service("iCameraService")
public class CameraServiceImpl extends BaseService implements ICameraService {

    @Autowired
    @Qualifier("cameraDao")
    private ICameraDao cameraDao;

    @Override
    public void addCamera(CameraEntity Camera) {

        try {
            cameraDao.insert(Camera);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteCamera(CameraEntity Camera) {

        try {
            cameraDao.delete(Camera);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteCameraBatch(List<CameraEntity> list) {

        try {
            cameraDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateCamera(CameraEntity Camera) {

        try {
            cameraDao.update(Camera);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public CameraEntity getObject(CameraEntity Camera) {
        return cameraDao.getObject(Camera);
    }

    @Override
    public List<CameraEntity> getCameraList() {

        return (List<CameraEntity>) cameraDao.findList();
    }

    @Override
    public List<CameraEntity> getCameraList(CameraEntity Camera) {
        List<CameraEntity> list = (List<CameraEntity>) cameraDao.findListByCondition(Camera);
        return list;
    }

    @Override
    public List<CameraEntity> getCameraListForPlaz(CameraEntity Camera) {
        List<CameraEntity> list = (List<CameraEntity>) cameraDao.findOtherList(ICameraDao.GETCAMERALISTFORPLAZ, Camera, CameraEntity.class);

        return list;
    }
    @Override
    public List<CameraEntity> getCameraPointList(CameraEntity camera) {
        List<CameraEntity> list = (List<CameraEntity>) cameraDao.findOtherList(ICameraDao.GETCAMERAPOINTLIST, camera, CameraEntity.class);

        return list;
    }

}
