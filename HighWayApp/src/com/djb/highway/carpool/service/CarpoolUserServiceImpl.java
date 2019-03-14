package com.djb.highway.carpool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.carpool.dao.ICarpoolUserDao;

import com.djb.highway.carpool.entity.CarpoolUserEntity;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.user.dao.IUserDao;

@Service("iCarpoolUserService")
public class CarpoolUserServiceImpl extends BaseService implements ICarpoolUserService {

    @Autowired
    @Qualifier("carpoolUserDao")
    private ICarpoolUserDao carpoolUserDao;

    @Override
    public void addCarpoolUser(CarpoolUserEntity carpoolUser) {
        // TODO Auto-generated method stub
        try {
            carpoolUserDao.insert(carpoolUser);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteCarpoolUser(CarpoolUserEntity carpoolUser) {
        // TODO Auto-generated method stub
        try {
            carpoolUserDao.delete(carpoolUser);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteCarpoolUserBatch(List<CarpoolUserEntity> list) {
        // TODO Auto-generated method stub
        try {
            carpoolUserDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateCarpoolUser(CarpoolUserEntity carpoolUser) {
        // TODO Auto-generated method stub
        try {
            carpoolUserDao.update(carpoolUser);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public CarpoolUserEntity getObject(CarpoolUserEntity carpoolUser) {
        return carpoolUserDao.getObject(carpoolUser);
    }

    @Override
    public List<CarpoolUserEntity> getCarpoolUserList() {
        // TODO Auto-generated method stub
        return (List<CarpoolUserEntity>) carpoolUserDao.findList();
    }

    @Override
    public PageModel findPagedList(CarpoolUserEntity carpoolUser) {
        return carpoolUserDao.findPagedList(carpoolUser);
    }

    @Override
    public List<CarpoolUserEntity> getCarpoolUserList(CarpoolUserEntity carpoolUser) {
        List<CarpoolUserEntity> list = carpoolUserDao.findListByCondition(carpoolUser);
        return list;
    }

    
    @Override
    public Integer countCarpoolUserNumber() {

        return carpoolUserDao.getOtherObject(ICarpoolUserDao.COUNTCARPOOLUSRENUMBER, Integer.class);
    }

   

}
