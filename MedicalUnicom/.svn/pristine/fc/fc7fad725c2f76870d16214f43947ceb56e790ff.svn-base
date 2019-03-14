package com.djb.ylt.common.util;

import javax.servlet.http.HttpServletRequest;

import com.djb.ylt.framework.action.BaseAction;
import com.djb.ylt.user.service.IOrderNumberService;



public class OrderNumberUtil extends BaseAction {

    private IOrderNumberService iOrderNumberService;

    /**
     * <P>
     * コンストラクタ
     * </P>
     */
    public OrderNumberUtil() {
        super();
    }

    public synchronized String getOrderNumber(HttpServletRequest request) {

        if (iOrderNumberService == null) {
            iOrderNumberService = (IOrderNumberService) ResourceLocator.getBean("iOrderNumberService", request.getServletContext());
        }
        
        return iOrderNumberService.doGetOrderNumber();
    }

}