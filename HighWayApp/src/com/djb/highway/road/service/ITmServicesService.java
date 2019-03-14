package com.djb.highway.road.service;

import java.util.List;

import com.djb.highway.road.entity.TmServicesEntity;

public interface ITmServicesService {
    public void addTmServices(TmServicesEntity tmServices);

    public void deleteTmServices(TmServicesEntity tmServices);

    public void deleteTmServicesBatch(List<TmServicesEntity> list);

    public void updateTmServices(TmServicesEntity tmServices);

    public TmServicesEntity getObject(TmServicesEntity tmServices);

    public List<TmServicesEntity> getTmServicesList();

    public List<TmServicesEntity> getTmServicesList(TmServicesEntity tmServices);

}
