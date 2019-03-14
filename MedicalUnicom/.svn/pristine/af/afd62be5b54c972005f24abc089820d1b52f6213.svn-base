package com.djb.ylt.gene.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.gene.dao.IDetectAnalysisDao;
import com.djb.ylt.gene.entity.DetectAnalysisEntity;


@Service("iDetectAnalysisService")
public class DetectAnalysisServiceImpl extends BaseService implements IDetectAnalysisService {

    @Autowired
    @Qualifier("detectAnalysisDao")
    private IDetectAnalysisDao detectAnalysisDao;


	@Override
	public void addDetectAnalysis(DetectAnalysisEntity DetectAnalysis) {
		
		 try {
			 detectAnalysisDao.insert(DetectAnalysis);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
		
	}

	@Override
	public void deleteDetectAnalysis(DetectAnalysisEntity DetectAnalysis) {
		
		try {
			detectAnalysisDao.delete(DetectAnalysis);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deleteDetectAnalysisBatch(List<DetectAnalysisEntity> list) {
		
		try {
			detectAnalysisDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void updateDetectAnalysis(DetectAnalysisEntity DetectAnalysis) {
		
		try {
			detectAnalysisDao.update(DetectAnalysis);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
		
	}

	@Override
	public DetectAnalysisEntity getObject(DetectAnalysisEntity DetectAnalysis) {
		 return detectAnalysisDao.getObject(DetectAnalysis);
	}

	@Override
	public List<DetectAnalysisEntity> getDetectAnalysisList() {
		
		return (List<DetectAnalysisEntity>) detectAnalysisDao.findList();
	}

	@Override
	public List<DetectAnalysisEntity> getDetectAnalysisList(DetectAnalysisEntity DetectAnalysis) {
		
		 List<DetectAnalysisEntity> list = (List<DetectAnalysisEntity>) detectAnalysisDao.findListByCondition(DetectAnalysis);
	        return list;
	}

}
