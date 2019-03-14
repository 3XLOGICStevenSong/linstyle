package com.djb.highway.bus.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.bus.dao.IBusGroupDao;

import com.djb.highway.bus.entity.BusGroupEntity;
import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;

@Service("iBusGroupService")
public class BusGroupServiceImpl extends BaseService implements IBusGroupService {

    @Autowired
    @Qualifier("busGroupDao")
    private IBusGroupDao busGroupDao;

    @Override
    public void addGroup(BusGroupEntity group) {
        // TODO Auto-generated method stub
        try {
            busGroupDao.insert(group);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteGroup(BusGroupEntity group) {
        // TODO Auto-generated method stub
        try {
            busGroupDao.delete(group);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteGroupBatch(List<BusGroupEntity> list) {
        // TODO Auto-generated method stub
        try {
            busGroupDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateGroup(BusGroupEntity group) {
        // TODO Auto-generated method stub
        try {
            busGroupDao.update(group);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public BusGroupEntity getObject(BusGroupEntity group) {
        return busGroupDao.getObject(group);
    }

    @Override
    public List<BusGroupEntity> getGroupList() {
        // TODO Auto-generated method stub
        return (List<BusGroupEntity>) busGroupDao.findList();
    }

    @Override
    public PageModel findPagedList(BusGroupEntity group) {
        return busGroupDao.findPagedList(group);
    }

    @Override
    public List<BusGroupEntity> getGroupList(BusGroupEntity group) {
        List<BusGroupEntity> list = busGroupDao.findListByCondition(group);
        return list;
    }

    @Override
    public List<BusGroupEntity> searchGroupList(BusGroupEntity group) {
        List<BusGroupEntity> list = busGroupDao.findOtherList(IBusGroupDao.SEARCHGROUPLISTBYCONDITION, group,BusGroupEntity.class);
        return list;
    }

   


   

}
