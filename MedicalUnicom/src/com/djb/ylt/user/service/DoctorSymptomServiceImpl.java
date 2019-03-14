package com.djb.ylt.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.user.dao.IDoctorSymptomDao;
import com.djb.ylt.user.entity.DoctorSymptomEntity;


@Service("iDoctorSymptomService")
public class DoctorSymptomServiceImpl extends BaseService implements IDoctorSymptomService {

    @Autowired
    @Qualifier("doctorSymptomDao")
    private IDoctorSymptomDao doctorSymptomDao;
	@Override
	public void addDoctorSymptom(DoctorSymptomEntity doctorSymptom) {
		
		// TODO Auto-generated method stub
		  try {
			  doctorSymptomDao.insert(doctorSymptom);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public void deleteDoctorSymptom(DoctorSymptomEntity doctorSymptom) {
		
		try {
			doctorSymptomDao.delete(doctorSymptom);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deleteDoctorSymptomBatch(List<DoctorSymptomEntity> list) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDoctorSymptom(DoctorSymptomEntity doctorSymptom) {
		
		 try {
			 doctorSymptomDao.update(doctorSymptom);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public DoctorSymptomEntity getObject(DoctorSymptomEntity doctorSymptom) {
		
		  return doctorSymptomDao.getObject(doctorSymptom);
		
	}

	@Override
	public List<DoctorSymptomEntity> getDoctorSymptomList() {
		
        return (List<DoctorSymptomEntity>) doctorSymptomDao.findList();
	}

	@Override
	public List<DoctorSymptomEntity> getDoctorSymptomList(DoctorSymptomEntity doctorSymptom) {
		 List<DoctorSymptomEntity> list = (List<DoctorSymptomEntity>) doctorSymptomDao.findListByCondition(doctorSymptom);
	        return list;
	}

}
