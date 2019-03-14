package com.djb.highway.road.service;

import java.util.List;

import com.djb.highway.road.entity.CameraEntity;

public interface ICameraService {
    public void addCamera(CameraEntity camera);

    public void deleteCamera(CameraEntity camera);

    public void deleteCameraBatch(List<CameraEntity> list);

    public void updateCamera(CameraEntity camera);

    public CameraEntity getObject(CameraEntity camera);

    public List<CameraEntity> getCameraList();

    public List<CameraEntity> getCameraList(CameraEntity camera);

    public List<CameraEntity> getCameraListForPlaz(CameraEntity camera);

    public List<CameraEntity> getCameraPointList(CameraEntity camera);
}
