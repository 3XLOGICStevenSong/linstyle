package com.djb.highway.bus.dao;


import com.djb.highway.bus.entity.BusGroupEntity;
import com.djb.highway.framework.dao.BaseDAO;


public interface IBusGroupDao extends BaseDAO<BusGroupEntity> {
    public static final String SEARCHGROUPLISTBYCONDITION = "searchGroupByCondition";

}
