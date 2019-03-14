package com.djb.highway.road.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.road.dao.IVersionMstDao;
import com.djb.highway.road.entity.VersionMstEntity;

@Service("iVersionMstService")
public class VersionMstServiceImpl extends BaseService implements IVersionMstService {

    @Autowired
    @Qualifier("versionMstDao")
    private IVersionMstDao versionMstDao;

    @Override
    public VersionMstEntity getObject(VersionMstEntity versionMst) {
        return versionMstDao.getObject(versionMst);
    }

    @Override
    public void updateVersionMst(VersionMstEntity versionMst) {

        try {
            versionMstDao.update(versionMst);
        } catch (DataNotFoundException e) {
        }
    }

}
