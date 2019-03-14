package com.djb.ylt.health.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.health.dao.IInqueryResultDao;
import com.djb.ylt.health.entity.InqueryResultEntity;





@Service("iInqueryResultService")
public class InqueryResultServiceImpl extends BaseService implements IInqueryResultService {

    @Autowired
    @Qualifier("inqueryResultDao")
    private IInqueryResultDao inqueryResultDao;
	@Override
	public void addInqueryResult(InqueryResultEntity InqueryResult) {
		
		// TODO Auto-generated method stub
		  try {
			  inqueryResultDao.insert(InqueryResult);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public void deleteInqueryResult(InqueryResultEntity InqueryResult) {
		
		try {
			inqueryResultDao.delete(InqueryResult);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deleteInqueryResultBatch(List<InqueryResultEntity> list) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInqueryResult(InqueryResultEntity InqueryResult) {
		
		 try {
			 inqueryResultDao.update(InqueryResult);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public InqueryResultEntity getObject(InqueryResultEntity InqueryResult) {
		
		  return inqueryResultDao.getObject(InqueryResult);
		
	}

	@Override
	public List<InqueryResultEntity> getInqueryResultList() {
		
        return (List<InqueryResultEntity>) inqueryResultDao.findList();
	}

	@Override
	public List<InqueryResultEntity> getInqueryResultList(InqueryResultEntity InqueryResult) {
		 List<InqueryResultEntity> list = (List<InqueryResultEntity>) inqueryResultDao.findListByCondition(InqueryResult);
	        return list;
	}

}
