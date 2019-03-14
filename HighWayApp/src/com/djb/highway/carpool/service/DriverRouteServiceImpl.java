package com.djb.highway.carpool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.carpool.dao.IDriverRouteDao;

import com.djb.highway.carpool.entity.DriverRouteEntity;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;

@Service("iDriverRouteService")
public class DriverRouteServiceImpl extends BaseService implements IDriverRouteService {

    @Autowired
    @Qualifier("driverRouteDao")
    private IDriverRouteDao driverRouteDao;

    @Override
    public void addDriverRoute(DriverRouteEntity driverRoute) {
        // TODO Auto-generated method stub
        try {
            driverRouteDao.insert(driverRoute);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteDriverRoute(DriverRouteEntity driverRoute) {
        // TODO Auto-generated method stub
        try {
            driverRouteDao.delete(driverRoute);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteDriverRouteBatch(List<DriverRouteEntity> list) {
        // TODO Auto-generated method stub
        try {
            driverRouteDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateDriverRoute(DriverRouteEntity driverRoute) {
        // TODO Auto-generated method stub
        try {
            driverRouteDao.update(driverRoute);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public DriverRouteEntity getObject(DriverRouteEntity driverRoute) {
        return driverRouteDao.getObject(driverRoute);
    }

    @Override
    public List<DriverRouteEntity> getDriverRouteList() {
        // TODO Auto-generated method stub
        return (List<DriverRouteEntity>) driverRouteDao.findList();
    }

    @Override
    public PageModel findPagedList(DriverRouteEntity driverRoute) {
        return driverRouteDao.findPagedList(driverRoute);
    }

    @Override
    public List<DriverRouteEntity> getDriverRouteList(DriverRouteEntity driverRoute) {
        List<DriverRouteEntity> list = driverRouteDao.findListByCondition(driverRoute);
        return list;
    }
    @Override
    public List<DriverRouteEntity> getValidList(DriverRouteEntity driverRoute) {
        List<DriverRouteEntity> list = driverRouteDao.findOtherList(IDriverRouteDao.GETVALIDLIST, driverRoute, DriverRouteEntity.class);
        return list;
    }
}
