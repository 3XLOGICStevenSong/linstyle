package com.djb.highway.carpool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.carpool.dao.ICarpoolRouteDao;

import com.djb.highway.carpool.entity.CarpoolRouteEntity;
import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;

@Service("iCarpoolRouteService")
public class CarpoolRouteServiceImpl extends BaseService implements ICarpoolRouteService {

    @Autowired
    @Qualifier("carpoolRouteDao")
    private ICarpoolRouteDao carpoolRouteDao;

    @Override
    public void addCarpoolRoute(CarpoolRouteEntity carpoolRoute) {
        // TODO Auto-generated method stub
        try {
            carpoolRouteDao.insert(carpoolRoute);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteCarpoolRoute(CarpoolRouteEntity carpoolRoute) {
        // TODO Auto-generated method stub
        try {
            carpoolRouteDao.delete(carpoolRoute);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteCarpoolRouteBatch(List<CarpoolRouteEntity> list) {
        // TODO Auto-generated method stub
        try {
            carpoolRouteDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateCarpoolRoute(CarpoolRouteEntity carpoolRoute) {
        // TODO Auto-generated method stub
        try {
            carpoolRouteDao.update(carpoolRoute);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public CarpoolRouteEntity getObject(CarpoolRouteEntity carpoolRoute) {
        return carpoolRouteDao.getObject(carpoolRoute);
    }

    @Override
    public List<CarpoolRouteEntity> getCarpoolRouteList() {
        // TODO Auto-generated method stub
        return (List<CarpoolRouteEntity>) carpoolRouteDao.findList();
    }

    @Override
    public PageModel findPagedList(CarpoolRouteEntity carpoolRoute) {
        return carpoolRouteDao.findPagedList(carpoolRoute);
    }

    @Override
    public List<CarpoolRouteEntity> getCarpoolRouteList(CarpoolRouteEntity carpoolRoute) {
        List<CarpoolRouteEntity> list = carpoolRouteDao.findListByCondition(carpoolRoute);
        return list;
    }

}
