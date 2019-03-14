package com.djb.ylt.health.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.health.dao.IDepartmentClassDao;
import com.djb.ylt.health.entity.DepartmentClassEntity;






@Service("iDepartmentClassService")
public class DepartmentClassServiceImpl extends BaseService implements IDepartmentClassService {

    @Autowired
    @Qualifier("departmentClassDao")
    private IDepartmentClassDao departmentClassDao;
	

	@Override
	public List<DepartmentClassEntity> getDepartmentClassList() {
		
        return (List<DepartmentClassEntity>) departmentClassDao.findList();
	}

	
}
