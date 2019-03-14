package com.djb.highway.road.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.road.dao.IMapLocationDao;
import com.djb.highway.road.entity.MapLocationEntity;

@Service("iMapLocationService")
public class MapLocationServiceImpl extends BaseService implements IMapLocationService {

    @Autowired
    @Qualifier("mapLocationDao")
    private IMapLocationDao mapLocationDao;

    @Override
    public void addMapLocation(MapLocationEntity mapLocation) {
        // TODO Auto-generated method stub
        try {
            mapLocationDao.insert(mapLocation);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteMapLocation(MapLocationEntity mapLocation) {
        // TODO Auto-generated method stub
        try {
            mapLocationDao.delete(mapLocation);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteMapLocationBatch(List<MapLocationEntity> list) {
        // TODO Auto-generated method stub
        try {
            mapLocationDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateMapLocation(MapLocationEntity mapLocation) {
        // TODO Auto-generated method stub
        try {
            mapLocationDao.update(mapLocation);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public MapLocationEntity getObject(MapLocationEntity mapLocation) {
        return mapLocationDao.getObject(mapLocation);
    }

    @Override
    public List<MapLocationEntity> getMapLocationList() {
        // TODO Auto-generated method stub
        return mapLocationDao.findList();
    }

   
    @Override
    public List<MapLocationEntity> getMapLocationList(MapLocationEntity mapLocation) {
        List<MapLocationEntity> list = mapLocationDao.findListByCondition(mapLocation);
        return list;
    }

    @Override
    public PageModel findPagedList(MapLocationEntity mapLocation) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MapLocationEntity> getMapLocationPageList(MapLocationEntity mapLocation) {
        // TODO Auto-generated method stub
        return null;
    }

}
