package com.djb.ylt.gene.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.gene.dao.ITestResultMasterDao;
import com.djb.ylt.gene.entity.TestResultMasterEntity;

@Service("iTestResultMasterService")
public class TestResultMasterServiceImpl extends BaseService implements ITestResultMasterService {

    @Autowired
    @Qualifier("testResultMasterDao")
    private ITestResultMasterDao testResultMasterDao;


	@Override
	public void addTestResultMaster(TestResultMasterEntity testResultMaster) {
		
		 try {
			 testResultMasterDao.insert(testResultMaster);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
		
	}

	@Override
	public void deleteTestResultMaster(TestResultMasterEntity testResultMaster) {
		
		try {
			testResultMasterDao.delete(testResultMaster);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deleteTestResultMasterBatch(List<TestResultMasterEntity> list) {
		
		try {
			testResultMasterDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void updateTestResultMaster(TestResultMasterEntity testResultMaster) {
		
		try {
			testResultMasterDao.update(testResultMaster);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
		
	}

	@Override
	public TestResultMasterEntity getObject(TestResultMasterEntity testResultMaster) {
		 return testResultMasterDao.getObject(testResultMaster);
	}

	@Override
	public List<TestResultMasterEntity> getTestResultMasterList() {
		
		return (List<TestResultMasterEntity>) testResultMasterDao.findList();
	}

	@Override
	public List<TestResultMasterEntity> getTestResultMasterList(TestResultMasterEntity testResultMaster) {
		
		 List<TestResultMasterEntity> list = (List<TestResultMasterEntity>) testResultMasterDao.findListByCondition(testResultMaster);
	        return list;
	}

}
