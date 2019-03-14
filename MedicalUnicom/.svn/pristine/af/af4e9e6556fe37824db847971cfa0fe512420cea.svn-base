package com.djb.ylt.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.user.dao.IDoctorConcernDao;

import com.djb.ylt.user.entity.DoctorConcernEntity;

@Service("iDoctorConcernService")
public class DoctorConcernServiceImpl extends BaseService implements IDoctorConcernService {

	@Autowired
	@Qualifier("doctorConcernDao")
	private IDoctorConcernDao doctorConcernDao;

	@Override
	public void addDoctorConcern(DoctorConcernEntity DoctorConcern) {

		// TODO Auto-generated method stub
		try {
			doctorConcernDao.insert(DoctorConcern);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteDoctorConcern(DoctorConcernEntity DoctorConcern) {

		try {
			doctorConcernDao.delete(DoctorConcern);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}

	@Override
	public void deleteDoctorConcernBatch(List<DoctorConcernEntity> list) {

		// TODO Auto-generated method stub

	}

	@Override
	public void updateDoctorConcern(DoctorConcernEntity DoctorConcern) {

		try {
			doctorConcernDao.update(DoctorConcern);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public DoctorConcernEntity getObject(DoctorConcernEntity DoctorConcern) {

		return doctorConcernDao.getObject(DoctorConcern);

	}

	@Override
	public List<DoctorConcernEntity> getDoctorConcernList() {

		return (List<DoctorConcernEntity>) doctorConcernDao.findList();
	}

	@Override
	public List<DoctorConcernEntity> getDoctorConcernList(DoctorConcernEntity DoctorConcern) {
		List<DoctorConcernEntity> list = (List<DoctorConcernEntity>) doctorConcernDao
				.findListByCondition(DoctorConcern);
		return list;
	}
}
