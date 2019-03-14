package com.djb.ylt.health.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.health.dao.IInqueryAnswerDao;
import com.djb.ylt.health.entity.InqueryAnswerEntity;





@Service("iInqueryAnswerService")
public class InqueryAnswerServiceImpl extends BaseService implements IInqueryAnswerService {

    @Autowired
    @Qualifier("inqueryAnswerDao")
    private IInqueryAnswerDao inqueryAnswerDao;
	@Override
	public void addInqueryAnswer(InqueryAnswerEntity InqueryAnswer) {
		
		// TODO Auto-generated method stub
		  try {
			  inqueryAnswerDao.insert(InqueryAnswer);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public void deleteInqueryAnswer(InqueryAnswerEntity InqueryAnswer) {
		
		try {
			inqueryAnswerDao.delete(InqueryAnswer);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deleteInqueryAnswerBatch(List<InqueryAnswerEntity> list) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInqueryAnswer(InqueryAnswerEntity InqueryAnswer) {
		
		 try {
			 inqueryAnswerDao.update(InqueryAnswer);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public InqueryAnswerEntity getObject(InqueryAnswerEntity InqueryAnswer) {
		
		  return inqueryAnswerDao.getObject(InqueryAnswer);
		
	}

	@Override
	public List<InqueryAnswerEntity> getInqueryAnswerList() {
		
        return (List<InqueryAnswerEntity>) inqueryAnswerDao.findList();
	}

	@Override
	public List<InqueryAnswerEntity> getInqueryAnswerList(InqueryAnswerEntity InqueryAnswer) {
		 List<InqueryAnswerEntity> list = (List<InqueryAnswerEntity>) inqueryAnswerDao.findListByCondition(InqueryAnswer);
	        return list;
	}

	@Override
	public void addInqueryAnswerBatch(List<InqueryAnswerEntity> InqueryAnswerList) {
		
		 try {
			  inqueryAnswerDao.insertBatch(InqueryAnswerList);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
		
	}

}
