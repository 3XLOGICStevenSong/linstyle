package com.djb.ylt.gene.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.gene.dao.IAnalysisTemplateDao;
import com.djb.ylt.gene.entity. AnalysisTemplateEntity;

@Service("iAnalysisTemplateService")
public class AnalysisTemplateServiceImpl extends BaseService implements IAnalysisTemplateService {

    @Autowired
    @Qualifier("analysisTemplateDao")
    private IAnalysisTemplateDao analysisTemplateDao;


	@Override
	public void addAnalysisTemplate( AnalysisTemplateEntity  analysisTemplate) {
		
		 try {
			  analysisTemplateDao.insert( analysisTemplate);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
		
	}

	@Override
	public void deleteAnalysisTemplate( AnalysisTemplateEntity  analysisTemplate) {
		
		try {
			analysisTemplateDao.delete( analysisTemplate);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deleteAnalysisTemplateBatch(List< AnalysisTemplateEntity> list) {
		
		try {
			analysisTemplateDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void updateAnalysisTemplate(AnalysisTemplateEntity  analysisTemplate) {
		
		try {
			analysisTemplateDao.update( analysisTemplate);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
		
	}

	@Override
	public  AnalysisTemplateEntity getObject( AnalysisTemplateEntity  analysisTemplate) {
		 return  analysisTemplateDao.getObject( analysisTemplate);
	}

	@Override
	public List< AnalysisTemplateEntity> getAnalysisTemplateList() {
		
		return (List< AnalysisTemplateEntity>)  analysisTemplateDao.findList();
	}

	@Override
	public List< AnalysisTemplateEntity> getAnalysisTemplateList( AnalysisTemplateEntity  analysisTemplate) {
		
		 List< AnalysisTemplateEntity> list = (List< AnalysisTemplateEntity>)  analysisTemplateDao.findListByCondition( analysisTemplate);
	        return list;
	}

	
}
