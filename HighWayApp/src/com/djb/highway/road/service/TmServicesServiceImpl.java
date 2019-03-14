package com.djb.highway.road.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.road.dao.ITmServicesDao;
import com.djb.highway.road.entity.TmServicesEntity;

@Service("iTmServicesService")
public class TmServicesServiceImpl extends BaseService implements ITmServicesService {

    @Autowired
    @Qualifier("tmServicesDao")
    private ITmServicesDao tmServicesDao;

    @Override
    public void addTmServices(TmServicesEntity TmServices) {

        try {
            tmServicesDao.insert(TmServices);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteTmServices(TmServicesEntity TmServices) {

        try {
            tmServicesDao.delete(TmServices);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteTmServicesBatch(List<TmServicesEntity> list) {

        try {
            tmServicesDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateTmServices(TmServicesEntity TmServices) {

        try {
            tmServicesDao.update(TmServices);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public TmServicesEntity getObject(TmServicesEntity TmServices) {
        return tmServicesDao.getObject(TmServices);
    }

    @Override
    public List<TmServicesEntity> getTmServicesList() {

        return (List<TmServicesEntity>) tmServicesDao.findList();
    }

    @Override
    public List<TmServicesEntity> getTmServicesList(TmServicesEntity TmServices) {
        List<TmServicesEntity> list = (List<TmServicesEntity>) tmServicesDao.findListByCondition(TmServices);
        return list;
    }

}
