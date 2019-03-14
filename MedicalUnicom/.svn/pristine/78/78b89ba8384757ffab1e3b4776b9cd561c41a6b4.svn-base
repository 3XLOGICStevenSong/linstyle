package com.djb.ylt.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.user.dao.IEmergencyDoctorDao;
import com.djb.ylt.user.entity.EmergencyDoctorEntity;



@Service("iEmergencyDoctorService")
public class EmergencyDoctorServiceImpl extends BaseService implements IEmergencyDoctorService {

	@Autowired
	@Qualifier("emergencyDoctorDao")
	private IEmergencyDoctorDao emergencyDoctorDao;

	@Override
	public void deleteEmergencyDoctor(EmergencyDoctorEntity EmergencyDoctor) {

		try {
			emergencyDoctorDao.delete(EmergencyDoctor);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}

	@Override
	public void deleteEmergencyDoctorBatch(List<EmergencyDoctorEntity> list) {

		// TODO Auto-generated method stub

	}

	@Override
	public void updateEmergencyDoctor(EmergencyDoctorEntity EmergencyDoctor) {

		try {
			emergencyDoctorDao.update(EmergencyDoctor);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public EmergencyDoctorEntity getObject() {

		return emergencyDoctorDao.getObject();

	}

	@Override
	public List<EmergencyDoctorEntity> getEmergencyDoctorList() {

		return (List<EmergencyDoctorEntity>) emergencyDoctorDao.findList();
	}

	@Override
	public List<EmergencyDoctorEntity> getEmergencyDoctorList(EmergencyDoctorEntity EmergencyDoctor) {
		List<EmergencyDoctorEntity> list = (List<EmergencyDoctorEntity>) emergencyDoctorDao.findListByCondition(EmergencyDoctor);
		return list;
	}

	@Override
	public void addEmergencyDoctor(EmergencyDoctorEntity EmergencyDoctor) {
		
		try {
			emergencyDoctorDao.insert(EmergencyDoctor);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
		
	}

	@Override
	public Integer getCount(EmergencyDoctorEntity EmergencyDoctor) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateEmergencyDoctorTime(EmergencyDoctorEntity EmergencyDoctor) {
		
		// TODO Auto-generated method stub
		return 0;
	}

	
}
