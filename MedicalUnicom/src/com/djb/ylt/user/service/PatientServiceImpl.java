package com.djb.ylt.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.user.dao.IPatientDao;
import com.djb.ylt.user.entity.PatientEntity;


@Service("iPatientService")
public class PatientServiceImpl extends BaseService implements IPatientService {

    @Autowired
    @Qualifier("patientDao")
    private IPatientDao patientDao;
	@Override
	public void addPatient(PatientEntity patient) {
		
		// TODO Auto-generated method stub
		  try {
			  patientDao.insert(patient);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public void deletePatient(PatientEntity patient) {
		
		try {
			patientDao.delete(patient);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deletePatientBatch(List<PatientEntity> list) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePatient(PatientEntity patient) {
		
		 try {
			 patientDao.update(patient);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public PatientEntity getObject(PatientEntity patient) {
		
		  return patientDao.getObject(patient);
		
	}

	@Override
	public List<PatientEntity> getPatientList() {
		
        return (List<PatientEntity>) patientDao.findList();
	}

	@Override
	public List<PatientEntity> getPatientList(PatientEntity patient) {
		 List<PatientEntity> list = (List<PatientEntity>) patientDao.findListByCondition(patient);
	        return list;
	}

}
