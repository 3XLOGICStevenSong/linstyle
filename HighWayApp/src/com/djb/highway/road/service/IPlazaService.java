package com.djb.highway.road.service;

import java.util.List;

import com.djb.highway.road.entity.PlazaEntity;

public interface IPlazaService {
    public void addPlaza(PlazaEntity plaza);

    public void deletePlaza(PlazaEntity plaza);

    public void updatePlaza(PlazaEntity plaza);

    public PlazaEntity getObject(PlazaEntity plaza);

    public List<PlazaEntity> getPlazaList();

    public List<PlazaEntity> getPlazaList(PlazaEntity plaza);

    public List<PlazaEntity> findTmPlazListByRoadNames(PlazaEntity plaza);

    public List<PlazaEntity> getPlazaPointList(PlazaEntity plaza);
}
