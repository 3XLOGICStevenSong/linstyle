package com.djb.highway.road.service;

import java.util.List;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.road.entity.MapLocationEntity;

public interface IMapLocationService {
    public void addMapLocation(MapLocationEntity mapLocation);

    public void deleteMapLocation(MapLocationEntity mapLocation);

    public void deleteMapLocationBatch(List<MapLocationEntity> list);

    public void updateMapLocation(MapLocationEntity mapLocation);

    public MapLocationEntity getObject(MapLocationEntity mapLocation);

    public List<MapLocationEntity> getMapLocationList();

    public PageModel findPagedList(MapLocationEntity mapLocation);

    public List<MapLocationEntity> getMapLocationList(MapLocationEntity mapLocation);

    public List<MapLocationEntity> getMapLocationPageList(MapLocationEntity mapLocation);

}
