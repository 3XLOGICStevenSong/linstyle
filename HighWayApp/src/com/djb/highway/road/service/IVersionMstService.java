package com.djb.highway.road.service;

import com.djb.highway.road.entity.VersionMstEntity;

public interface IVersionMstService {
    public void updateVersionMst(VersionMstEntity versionMst);

    public VersionMstEntity getObject(VersionMstEntity versionMst);
}
