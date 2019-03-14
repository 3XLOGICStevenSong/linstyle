package com.djb.highway.carpool.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.carpool.dao.IPassengerRouteDao;
import com.djb.highway.carpool.entity.PassengerRouteEntity;
import com.djb.highway.common.util.Constants;
import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;

@Service("iPassengerRouteService")
public class PassengerRouteServiceImpl extends BaseService implements IPassengerRouteService {

    @Autowired
    @Qualifier("passengerRouteDao")
    private IPassengerRouteDao passengerRouteDao;

    @Override
    public void addPassengerRoute(PassengerRouteEntity passengerRoute) {
        // TODO Auto-generated method stub
        try {
            passengerRouteDao.insert(passengerRoute);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deletePassengerRoute(PassengerRouteEntity passengerRoute) {
        // TODO Auto-generated method stub
        try {
            passengerRouteDao.delete(passengerRoute);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deletePassengerRouteBatch(List<PassengerRouteEntity> list) {
        // TODO Auto-generated method stub
        try {
            passengerRouteDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updatePassengerRoute(PassengerRouteEntity passengerRoute) {
        // TODO Auto-generated method stub
        try {
            passengerRouteDao.update(passengerRoute);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public PassengerRouteEntity getObject(PassengerRouteEntity passengerRoute) {
        return passengerRouteDao.getObject(passengerRoute);
    }

    @Override
    public List<PassengerRouteEntity> getPassengerRouteList() {
        // TODO Auto-generated method stub
        return (List<PassengerRouteEntity>) passengerRouteDao.findList();
    }

    @Override
    public PageModel findPagedList(PassengerRouteEntity passengerRoute) {
        return passengerRouteDao.findPagedList(passengerRoute);
    }

    @Override
    public List<PassengerRouteEntity> getPassengerRouteList(PassengerRouteEntity passengerRoute) {
        List<PassengerRouteEntity> list = passengerRouteDao.findListByCondition(passengerRoute);
        return list;
    }

    @Override
    public List<PassengerRouteEntity> getPassengerRouteListBySearch(PassengerRouteEntity passengerRoute) {
        List<PassengerRouteEntity> list = passengerRouteDao.findOtherList(IPassengerRouteDao.FINDLISTBTSEARCH, passengerRoute, PassengerRouteEntity.class);
        return list;
    }

    @Override
    public PassengerRouteEntity getObjectByState(PassengerRouteEntity passengerRoute) {
        // TODO Auto-generated method stub
        return passengerRouteDao.getOtherObject(IPassengerRouteDao.GETOBJECTBYSTATE, passengerRoute, PassengerRouteEntity.class);
    }

    @Override
    public List<PassengerRouteEntity> getPassengerRouteListByTime(PassengerRouteEntity passengerRoute) {
        // TODO Auto-generated method stub
        List<PassengerRouteEntity> list = passengerRouteDao.findListOrderByTime(passengerRoute);
        return list;
    }

  
   

}
