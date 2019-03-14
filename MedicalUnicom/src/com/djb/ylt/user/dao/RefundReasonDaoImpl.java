package com.djb.ylt.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.ylt.framework.dao.BaseDAOImpl;

import com.djb.ylt.user.entity.RefundReasonEntity;



@Repository("refundReasonDao")
public class RefundReasonDaoImpl extends BaseDAOImpl<RefundReasonEntity> implements
		IRefundReasonDao {
}
