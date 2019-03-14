package com.djb.ylt.gene.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.gene.dao.ITestResultDao;
import com.djb.ylt.gene.entity.TestResultEntity;

@Service("iTestResultService")
public class TestResultServiceImpl extends BaseService implements ITestResultService {

    @Autowired
    @Qualifier("testResultDao")
    private ITestResultDao testResultDao;


	@Override
	public void addTestResult(TestResultEntity testResult) {
		
		 try {
			 testResultDao.insert(testResult);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
		
	}

	@Override
	public void deleteTestResult(TestResultEntity testResult) {
		
		try {
			testResultDao.delete(testResult);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deleteTestResultBatch(List<TestResultEntity> list) {
		
		try {
			testResultDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void updateTestResult(TestResultEntity testResult) {
		
		try {
			testResultDao.update(testResult);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
		
	}

	@Override
	public TestResultEntity getObject(TestResultEntity testResult) {
		 return testResultDao.getObject(testResult);
	}

	@Override
	public List<TestResultEntity> getTestResultList() {
		
		return (List<TestResultEntity>) testResultDao.findList();
	}

	@Override
	public List<TestResultEntity> getTestResultList(TestResultEntity testResult) {
		
		 List<TestResultEntity> list = (List<TestResultEntity>) testResultDao.findListByCondition(testResult);
	        return list;
	}

}
