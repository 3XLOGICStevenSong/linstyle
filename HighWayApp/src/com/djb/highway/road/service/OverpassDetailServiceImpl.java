package com.djb.highway.road.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.road.dao.IOverpassDetailDao;
import com.djb.highway.road.entity.OverpassDetailEntity;

@Service("iOverpassDetailService")
public class OverpassDetailServiceImpl extends BaseService implements IOverpassDetailService {

    @Autowired
    @Qualifier("overpassDetailDao")
    private IOverpassDetailDao overpassDetailDao;

    @Override
    public void addOverpassDetail(OverpassDetailEntity plaza) {

        try {
            overpassDetailDao.insert(plaza);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteOverpassDetail(OverpassDetailEntity plaza) {

        try {
            overpassDetailDao.delete(plaza);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateOverpassDetail(OverpassDetailEntity plaza) {

        try {
            overpassDetailDao.update(plaza);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public OverpassDetailEntity getObject(OverpassDetailEntity plaza) {
        return overpassDetailDao.getObject(plaza);
    }

    @Override
    public List<OverpassDetailEntity> getOverpassDetailList() {

        return (List<OverpassDetailEntity>) overpassDetailDao.findList();
    }

    @Override
    public List<OverpassDetailEntity> getOverpassDetailList(OverpassDetailEntity plaza) {
        List<OverpassDetailEntity> list = (List<OverpassDetailEntity>) overpassDetailDao.findListByCondition(plaza);
        return list;
    }

}
