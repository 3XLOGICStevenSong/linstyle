package com.djb.ylt.health.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.health.dao.IInqueryQuestionDao;
import com.djb.ylt.health.entity.InqueryQuestionEntity;





@Service("iInqueryQuestionService")
public class InqueryQuestionServiceImpl extends BaseService implements IInqueryQuestionService {

    @Autowired
    @Qualifier("inqueryQuestionDao")
    private IInqueryQuestionDao inqueryQuestionDao;
	@Override
	public void addInqueryQuestion(InqueryQuestionEntity InqueryQuestion) {
		
		// TODO Auto-generated method stub
		  try {
			  inqueryQuestionDao.insert(InqueryQuestion);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public void deleteInqueryQuestion(InqueryQuestionEntity InqueryQuestion) {
		
		try {
			inqueryQuestionDao.delete(InqueryQuestion);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deleteInqueryQuestionBatch(List<InqueryQuestionEntity> list) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInqueryQuestion(InqueryQuestionEntity InqueryQuestion) {
		
		 try {
			 inqueryQuestionDao.update(InqueryQuestion);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public InqueryQuestionEntity getObject(InqueryQuestionEntity InqueryQuestion) {
		
		  return inqueryQuestionDao.getObject(InqueryQuestion);
		
	}

	@Override
	public List<InqueryQuestionEntity> getInqueryQuestionList() {
		
        return (List<InqueryQuestionEntity>) inqueryQuestionDao.findList();
	}

	@Override
	public List<InqueryQuestionEntity> getInqueryQuestionList(InqueryQuestionEntity InqueryQuestion) {
		 List<InqueryQuestionEntity> list = (List<InqueryQuestionEntity>) inqueryQuestionDao.findListByCondition(InqueryQuestion);
	        return list;
	}

}
