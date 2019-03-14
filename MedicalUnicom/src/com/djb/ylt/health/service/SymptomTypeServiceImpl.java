package com.djb.ylt.health.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.health.dao.ISymptomTypeDao;
import com.djb.ylt.health.entity.SymptomTypeEntity;




@Service("iSymptomTypeService")
public class SymptomTypeServiceImpl extends BaseService implements ISymptomTypeService {

    @Autowired
    @Qualifier("symptomTypeDao")
    private ISymptomTypeDao symptomTypeDao;
	


	@Override
	public List<SymptomTypeEntity> getSymptomTypeList() {
		
		  return (List<SymptomTypeEntity>) symptomTypeDao.findList();
	}

	

}
