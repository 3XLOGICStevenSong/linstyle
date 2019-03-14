package com.djb.ylt.health.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.health.dao.ISymptomDao;

import com.djb.ylt.health.entity.SymptomEntity;




@Service("iSymptomService")
public class SymptomServiceImpl extends BaseService implements ISymptomService {

    @Autowired
    @Qualifier("symptomDao")
    private ISymptomDao symptomDao;
	
	@Override
	public SymptomEntity getObject(SymptomEntity symptom) {
		
		  return symptomDao.getObject(symptom);
		
	}

	

}
