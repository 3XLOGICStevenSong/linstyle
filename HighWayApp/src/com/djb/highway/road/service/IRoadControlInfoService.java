package com.djb.highway.road.service;

import java.util.List;

import com.djb.highway.road.entity.RoadControlInfoEntity;

public interface IRoadControlInfoService {
    public void addRoadControlInfo(RoadControlInfoEntity roadControlInfo);

    public void deleteRoadControlInfo(RoadControlInfoEntity roadControlInfo);

    public void deleteRoadControlInfoBatch(List<RoadControlInfoEntity> list);

    public void updateRoadControlInfo(RoadControlInfoEntity roadControlInfo);

    public RoadControlInfoEntity getObject(RoadControlInfoEntity roadControlInfo);

    public List<RoadControlInfoEntity> getRoadControlInfoList();

    public List<RoadControlInfoEntity> getRoadControlInfoList(RoadControlInfoEntity roadControlInfo);

    public List<RoadControlInfoEntity> getRoadControlInfoPageList(RoadControlInfoEntity roadControlInfo);

}
