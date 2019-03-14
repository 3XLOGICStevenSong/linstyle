package com.djb.highway.road.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.road.dao.IOverpassDao;
import com.djb.highway.road.entity.OverpassEntity;

@Service("iOverpassService")
public class OverpassServiceImpl extends BaseService implements IOverpassService {

    @Autowired
    @Qualifier("overpassDao")
    private IOverpassDao overpassDao;

    @Override
    public void addOverpass(OverpassEntity plaza) {

        try {
            overpassDao.insert(plaza);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteOverpass(OverpassEntity plaza) {

        try {
            overpassDao.delete(plaza);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateOverpass(OverpassEntity plaza) {

        try {
            overpassDao.update(plaza);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public OverpassEntity getObject(OverpassEntity plaza) {
        return overpassDao.getObject(plaza);
    }

    @Override
    public List<OverpassEntity> getOverpassList() {

        return (List<OverpassEntity>) overpassDao.findList();
    }

    @Override
    public List<OverpassEntity> getOverpassList(OverpassEntity plaza) {
        List<OverpassEntity> list = (List<OverpassEntity>) overpassDao.findListByCondition(plaza);
        return list;
    }

}
