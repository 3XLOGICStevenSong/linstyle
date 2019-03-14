package com.djb.highway.carpool.service;

import java.util.List;

import com.djb.highway.carpool.entity.DriverRouteEntity;

import com.djb.highway.framework.entity.PageModel;

public interface IDriverRouteService {

    public void addDriverRoute(DriverRouteEntity driverRoute);

    public void deleteDriverRoute(DriverRouteEntity driverRoute);

    public void deleteDriverRouteBatch(List<DriverRouteEntity> list);

    public void updateDriverRoute(DriverRouteEntity driverRoute);

    public DriverRouteEntity getObject(DriverRouteEntity driverRoute);

    public List<DriverRouteEntity> getDriverRouteList();

    public PageModel findPagedList(DriverRouteEntity driverRoute);

    public List<DriverRouteEntity> getDriverRouteList(DriverRouteEntity driverRoute);
    
    public List<DriverRouteEntity> getValidList(DriverRouteEntity driverRoute);
}