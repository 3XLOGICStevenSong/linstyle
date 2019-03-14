package com.djb.ylt.health.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.health.dao.IDepartmentDao;
import com.djb.ylt.health.entity.DepartmentEntity;





@Service("iDepartmentService")
public class DepartmentServiceImpl extends BaseService implements IDepartmentService {

    @Autowired
    @Qualifier("departmentDao")
    private IDepartmentDao departmentDao;
	@Override
	public void addDepartment(DepartmentEntity Department) {
		
		// TODO Auto-generated method stub
		  try {
			  departmentDao.insert(Department);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public void deleteDepartment(DepartmentEntity Department) {
		
		try {
			departmentDao.delete(Department);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deleteDepartmentBatch(List<DepartmentEntity> list) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDepartment(DepartmentEntity Department) {
		
		 try {
			 departmentDao.update(Department);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public DepartmentEntity getObject(DepartmentEntity Department) {
		
		  return departmentDao.getObject(Department);
		
	}

	@Override
	public List<DepartmentEntity> getDepartmentList() {
		
        return (List<DepartmentEntity>) departmentDao.findList();
	}

	@Override
	public List<DepartmentEntity> getDepartmentList(DepartmentEntity Department) {
		 List<DepartmentEntity> list = (List<DepartmentEntity>) departmentDao.findListByCondition(Department);
	        return list;
	}

}
