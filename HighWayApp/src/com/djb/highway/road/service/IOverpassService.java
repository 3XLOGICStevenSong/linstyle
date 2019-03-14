package com.djb.highway.road.service;

import java.util.List;

import com.djb.highway.road.entity.OverpassEntity;

public interface IOverpassService {
    public void addOverpass(OverpassEntity overpass);

    public void deleteOverpass(OverpassEntity overpass);

    public void updateOverpass(OverpassEntity overpass);

    public OverpassEntity getObject(OverpassEntity overpass);

    public List<OverpassEntity> getOverpassList();

    public List<OverpassEntity> getOverpassList(OverpassEntity overpass);

}
