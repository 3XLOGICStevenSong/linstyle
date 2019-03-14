package com.djb.highway.road.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.road.dao.IRoadControlInfoDao;
import com.djb.highway.road.entity.RoadControlInfoEntity;

@Service("iRoadControlInfoService")
public class RoadControlInfoServiceImpl extends BaseService implements IRoadControlInfoService {

    @Autowired
    @Qualifier("roadControlInfoDao")
    private IRoadControlInfoDao roadControlInfoDao;

    @Override
    public void addRoadControlInfo(RoadControlInfoEntity RoadControlInfo) {

        try {
            roadControlInfoDao.insert(RoadControlInfo);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteRoadControlInfo(RoadControlInfoEntity RoadControlInfo) {

        try {
            roadControlInfoDao.delete(RoadControlInfo);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteRoadControlInfoBatch(List<RoadControlInfoEntity> list) {

        try {
            roadControlInfoDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateRoadControlInfo(RoadControlInfoEntity RoadControlInfo) {

        try {
            roadControlInfoDao.update(RoadControlInfo);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public RoadControlInfoEntity getObject(RoadControlInfoEntity RoadControlInfo) {
        return roadControlInfoDao.getObject(RoadControlInfo);
    }

    @Override
    public List<RoadControlInfoEntity> getRoadControlInfoList() {

        return (List<RoadControlInfoEntity>) roadControlInfoDao.findList();
    }

    @Override
    public List<RoadControlInfoEntity> getRoadControlInfoList(RoadControlInfoEntity RoadControlInfo) {
        List<RoadControlInfoEntity> list = (List<RoadControlInfoEntity>) roadControlInfoDao.findListByCondition(RoadControlInfo);
        return list;
    }

    @Override
    public List<RoadControlInfoEntity> getRoadControlInfoPageList(RoadControlInfoEntity RoadControlInfo) {
        List<RoadControlInfoEntity> list = (List<RoadControlInfoEntity>) roadControlInfoDao.findOtherList(IRoadControlInfoDao.FINDPAGELISTBYCONDITION,
                        RoadControlInfo, RoadControlInfoEntity.class);

        return list;
    }
}
