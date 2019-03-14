package com.djb.highway.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.user.dao.IOftenUsedRoadDao;
import com.djb.highway.user.entity.OftenUsedRoadEntity;

@Service("iOftenUsedRoadService")
public class OftenUsedRoadServiceImpl extends BaseService implements
		IOftenUsedRoadService {

	@Autowired
	@Qualifier("oftenUsedRoadDao")
	private IOftenUsedRoadDao oftenUsedRoadDao;

	@Override
	public void addOftenUsedRoad(OftenUsedRoadEntity oftenUsedRoad) {
		// TODO Auto-generated method stub
		try {
			oftenUsedRoadDao.insert(oftenUsedRoad);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteOftenUsedRoad(OftenUsedRoadEntity oftenUsedRoad) {
		// TODO Auto-generated method stub
		try {
			oftenUsedRoadDao.delete(oftenUsedRoad);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteOftenUsedRoadBatch(List<OftenUsedRoadEntity> list) {
		// TODO Auto-generated method stub
		try {
			oftenUsedRoadDao.deleteBatch(list);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void updateOftenUsedRoad(OftenUsedRoadEntity oftenUsedRoad) {
		// TODO Auto-generated method stub
		try {
			oftenUsedRoadDao.update(oftenUsedRoad);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public OftenUsedRoadEntity getOftenUsedRoadById(int u_id) {
		// TODO Auto-generated method stub
		return oftenUsedRoadDao.getObject(u_id);
	}

	@Override
	public OftenUsedRoadEntity getOftenUsedRoadById(
			OftenUsedRoadEntity oftenUsedRoad) {
		// TODO Auto-generated method stub
		return oftenUsedRoadDao.getObject(oftenUsedRoad);
	}

	@Override
	public List<OftenUsedRoadEntity> getOftenUsedRoadList() {
		// TODO Auto-generated method stub
		return oftenUsedRoadDao.findList();
	}

	@Override
	public PageModel findPagedList(OftenUsedRoadEntity oftenUsedRoad) {
		return oftenUsedRoadDao.findPagedList(oftenUsedRoad);
	}

	@Override
	public List<OftenUsedRoadEntity> getOftenUsedRoadList(
			OftenUsedRoadEntity oftenUsedRoad) {
		List<OftenUsedRoadEntity> list = oftenUsedRoadDao
				.findListByCondition(oftenUsedRoad);
		return list;
	}

	@Override
	public List<OftenUsedRoadEntity> getOftenUsedRoadListByTimeStamp(
			OftenUsedRoadEntity oftenUsedRoad) {
		List<OftenUsedRoadEntity> list = oftenUsedRoadDao.findOtherList(
				IOftenUsedRoadDao.GETOFTENUSEDROADLISTBYTIMESTAMP,
				oftenUsedRoad, OftenUsedRoadEntity.class);

		return list;
	}

	@Override
	public OftenUsedRoadEntity getMaxTimeStampObject(
			OftenUsedRoadEntity oftenUsedRoad) {

		OftenUsedRoadEntity maxTimeStampObj = oftenUsedRoadDao.getOtherObject(
				IOftenUsedRoadDao.GETMAXTIMESTAMPOBJECT, oftenUsedRoad,
				OftenUsedRoadEntity.class);
		return maxTimeStampObj;
	}

}
