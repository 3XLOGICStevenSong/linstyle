package com.djb.ylt.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.user.dao.IPatientScheduleDao;
import com.djb.ylt.user.entity.PatientScheduleEntity;


@Service("iPatientScheduleService")
public class PatientScheduleServiceImpl extends BaseService implements IPatientScheduleService {

    @Autowired
    @Qualifier("patientScheduleDao")
    private IPatientScheduleDao patientScheduleDao;
	@Override
	public void addPatientSchedule(PatientScheduleEntity PatientSchedule) {
		
		// TODO Auto-generated method stub
		  try {
			  patientScheduleDao.insert(PatientSchedule);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public void deletePatientSchedule(PatientScheduleEntity PatientSchedule) {
		
		try {
			patientScheduleDao.delete(PatientSchedule);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deletePatientScheduleBatch(List<PatientScheduleEntity> list) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePatientSchedule(PatientScheduleEntity PatientSchedule) {
		
		 try {
			 patientScheduleDao.update(PatientSchedule);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public PatientScheduleEntity getObject(PatientScheduleEntity PatientSchedule) {
		
		  return patientScheduleDao.getObject(PatientSchedule);
		
	}

	@Override
	public List<PatientScheduleEntity> getPatientScheduleList() {
		
        return (List<PatientScheduleEntity>) patientScheduleDao.findList();
	}

	@Override
	public List<PatientScheduleEntity> getPatientScheduleList(PatientScheduleEntity PatientSchedule) {
		 List<PatientScheduleEntity> list = (List<PatientScheduleEntity>) patientScheduleDao.findListByCondition(PatientSchedule);
	        return list;
	}

	@Override
	public void addPatientScheduleBatch(List<PatientScheduleEntity> PatientSchedule) {
		
		 try {
			 patientScheduleDao.insertBatch(PatientSchedule);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public void updateBatch(List<PatientScheduleEntity> patientSchedule) {
		
		 try {
			 patientScheduleDao.updateBatch(patientSchedule);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
		
	}

}
