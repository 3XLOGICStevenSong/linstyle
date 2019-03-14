package com.djb.ylt.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.user.dao.IOrderNumberDao;
import com.djb.ylt.user.entity.OrderNumberEntity;



@Service("iOrderNumberService")
public class OrderNumberServiceImpl extends BaseService implements IOrderNumberService {

    @Autowired
    @Qualifier("orderNumberDao")
    private IOrderNumberDao orderNumberDao;

    public String doGetOrderNumber() {
        String orderNumber = null;
        try {
            OrderNumberEntity orderNumberEntity = orderNumberDao.getObject();
            if (orderNumberEntity != null) {
                Long longOrderNumber = orderNumberEntity.getId();
                longOrderNumber = longOrderNumber.longValue() + 1;
                if (longOrderNumber != null) {
                    orderNumber = longOrderNumber.toString();
                }
            }
            orderNumberDao.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderNumber;
    }

}
