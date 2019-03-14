package com.djb.highway.bus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.bus.dao.IBusUserDao;

import com.djb.highway.bus.entity.BusUserEntity;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;

@Service("iBusUserService")
public class BusUserServiceImpl extends BaseService implements IBusUserService {

    @Autowired
    @Qualifier("busUserDao")
    private IBusUserDao busUserDao;

    @Override
    public void addBusUser(BusUserEntity busUser) {
        // TODO Auto-generated method stub
        try {
            busUserDao.insert(busUser);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteBusUser(BusUserEntity busUser) {
        // TODO Auto-generated method stub
        try {
            busUserDao.delete(busUser);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteBusUserBatch(List<BusUserEntity> list) {
        // TODO Auto-generated method stub
        try {
            busUserDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateBusUser(BusUserEntity busUser) {
        // TODO Auto-generated method stub
        try {
            
            //busUserDao.update(busUser);
            
            //testcode
            
            int count=busUserDao.update(busUser);
            logger.info("updateBusUser:", ">>updateBusUser>>"+"count:"+String.valueOf(count));
            
            
            
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public BusUserEntity getObject(BusUserEntity busUser) {
        return busUserDao.getObject(busUser);
    }

    @Override
    public List<BusUserEntity> getBusUserList() {
        // TODO Auto-generated method stub
        return (List<BusUserEntity>) busUserDao.findList();
    }

    @Override
    public PageModel findPagedList(BusUserEntity busUser) {
        return busUserDao.findPagedList(busUser);
    }

    @Override
    public List<BusUserEntity> getBusUserList(BusUserEntity busUser) {
        List<BusUserEntity> list = busUserDao.findListByCondition(busUser);
        return list;
    }

    

   

}
