package com.djb.ylt.gene.dao;

import org.springframework.stereotype.Repository;
import com.djb.ylt.framework.dao.BaseDAOImpl;

import com.djb.ylt.gene.entity.TestResultMasterEntity;



@Repository("testResultMasterDao")
public class TestResultMasterDaoImpl extends BaseDAOImpl<TestResultMasterEntity> implements
		ITestResultMasterDao {
}
