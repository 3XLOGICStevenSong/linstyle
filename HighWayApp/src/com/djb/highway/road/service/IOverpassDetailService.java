package com.djb.highway.road.service;

import java.util.List;

import com.djb.highway.road.entity.OverpassDetailEntity;

public interface IOverpassDetailService {
    public void addOverpassDetail(OverpassDetailEntity overpass);

    public void deleteOverpassDetail(OverpassDetailEntity overpass);

    public void updateOverpassDetail(OverpassDetailEntity overpass);

    public OverpassDetailEntity getObject(OverpassDetailEntity overpass);

    public List<OverpassDetailEntity> getOverpassDetailList();

    public List<OverpassDetailEntity> getOverpassDetailList(OverpassDetailEntity overpass);

}
