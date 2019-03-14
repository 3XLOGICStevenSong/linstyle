package com.djb.ylt.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.user.dao.IDepartmentDoctorDao;
import com.djb.ylt.user.entity.DepartmentDoctorEntity;


@Service("iDepartmentDoctorService")
public class DepartmentDoctorServiceImpl extends BaseService implements IDepartmentDoctorService {

    @Autowired
    @Qualifier("departmentDoctorDao")
    private IDepartmentDoctorDao departmentDoctorDao;
	@Override
	public void addDepartmentDoctor(DepartmentDoctorEntity DepartmentDoctor) {
		
		// TODO Auto-generated method stub
		  try {
			  departmentDoctorDao.insert(DepartmentDoctor);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public void deleteDepartmentDoctor(DepartmentDoctorEntity DepartmentDoctor) {
		
		try {
			departmentDoctorDao.delete(DepartmentDoctor);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deleteDepartmentDoctorBatch(List<DepartmentDoctorEntity> list) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDepartmentDoctor(DepartmentDoctorEntity DepartmentDoctor) {
		
		 try {
			 departmentDoctorDao.update(DepartmentDoctor);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public DepartmentDoctorEntity getObject(DepartmentDoctorEntity DepartmentDoctor) {
		
		  return departmentDoctorDao.getObject(DepartmentDoctor);
		
	}

	@Override
	public List<DepartmentDoctorEntity> getDepartmentDoctorList() {
		
        return (List<DepartmentDoctorEntity>) departmentDoctorDao.findList();
	}

	@Override
	public List<DepartmentDoctorEntity> getDepartmentDoctorList(DepartmentDoctorEntity DepartmentDoctor) {
		 List<DepartmentDoctorEntity> list = (List<DepartmentDoctorEntity>) departmentDoctorDao.findListByCondition(DepartmentDoctor);
	        return list;
	}

}
