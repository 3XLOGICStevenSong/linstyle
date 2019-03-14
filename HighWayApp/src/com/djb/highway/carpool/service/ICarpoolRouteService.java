package com.djb.highway.carpool.service;

import java.util.List;

import com.djb.highway.carpool.entity.CarpoolRouteEntity;

import com.djb.highway.framework.entity.PageModel;

public interface ICarpoolRouteService {

    public void addCarpoolRoute(CarpoolRouteEntity carpoolRoute);

    public void deleteCarpoolRoute(CarpoolRouteEntity carpoolRoute);

    public void deleteCarpoolRouteBatch(List<CarpoolRouteEntity> list);

    public void updateCarpoolRoute(CarpoolRouteEntity carpoolRoute);

    public CarpoolRouteEntity getObject(CarpoolRouteEntity carpoolRoute);

    public List<CarpoolRouteEntity> getCarpoolRouteList();

    public PageModel findPagedList(CarpoolRouteEntity carpoolRoute);

    public List<CarpoolRouteEntity> getCarpoolRouteList(CarpoolRouteEntity carpoolRoute);

}