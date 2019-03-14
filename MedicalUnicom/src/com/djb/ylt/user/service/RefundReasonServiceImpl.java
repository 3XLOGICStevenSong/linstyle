package com.djb.ylt.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.user.dao.IRefundReasonDao;
import com.djb.ylt.user.entity.RefundReasonEntity;


@Service("iRefundReasonService")
public class RefundReasonServiceImpl extends BaseService implements IRefundReasonService {

    @Autowired
    @Qualifier("refundReasonDao")
    private IRefundReasonDao refundReasonDao;
	@Override
	public void addRefundReason(RefundReasonEntity RefundReason) {
		
		// TODO Auto-generated method stub
		  try {
			  refundReasonDao.insert(RefundReason);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public void deleteRefundReason(RefundReasonEntity RefundReason) {
		
		try {
			refundReasonDao.delete(RefundReason);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deleteRefundReasonBatch(List<RefundReasonEntity> list) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRefundReason(RefundReasonEntity RefundReason) {
		
		 try {
			 refundReasonDao.update(RefundReason);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public RefundReasonEntity getObject(RefundReasonEntity RefundReason) {
		
		  return refundReasonDao.getObject(RefundReason);
		
	}

	@Override
	public List<RefundReasonEntity> getRefundReasonList() {
		
        return (List<RefundReasonEntity>) refundReasonDao.findList();
	}

	@Override
	public List<RefundReasonEntity> getRefundReasonList(RefundReasonEntity RefundReason) {
		 List<RefundReasonEntity> list = (List<RefundReasonEntity>) refundReasonDao.findListByCondition(RefundReason);
	        return list;
	}

	@Override
	public RefundReasonEntity getRefundReasonInfo(RefundReasonEntity RefundReason) {
		
		// TODO Auto-generated method stub
		return null;
	}

}
