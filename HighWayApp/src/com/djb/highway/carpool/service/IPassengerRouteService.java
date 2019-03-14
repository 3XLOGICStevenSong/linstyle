package com.djb.highway.carpool.service;

import java.util.List;

import com.djb.highway.carpool.entity.PassengerRouteEntity;
import com.djb.highway.framework.entity.PageModel;

public interface IPassengerRouteService {

    public void addPassengerRoute(PassengerRouteEntity PassengerRoute);

    public void deletePassengerRoute(PassengerRouteEntity PassengerRoute);

    public void deletePassengerRouteBatch(List<PassengerRouteEntity> list);

    public void updatePassengerRoute(PassengerRouteEntity PassengerRoute);

    public PassengerRouteEntity getObject(PassengerRouteEntity PassengerRoute);

    public List<PassengerRouteEntity> getPassengerRouteList();

    public PageModel findPagedList(PassengerRouteEntity PassengerRoute);

    public List<PassengerRouteEntity> getPassengerRouteList(PassengerRouteEntity passengerRoute);
    
    public List<PassengerRouteEntity> getPassengerRouteListBySearch(PassengerRouteEntity passengerRoute);
 
    public PassengerRouteEntity getObjectByState(PassengerRouteEntity PassengerRoute);
    
    public List<PassengerRouteEntity> getPassengerRouteListByTime(PassengerRouteEntity PassengerRoute);
   
}