package com.djb.highway.road.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.road.dao.IHighWayDao;
import com.djb.highway.road.entity.HighWayEntity;

@Service("iHighWayService")
public class HighWayServiceImpl extends BaseService implements IHighWayService {

	@Autowired
	@Qualifier("highWayDao")
	private IHighWayDao highWayDao;

	@Override
	public void addHighWay(HighWayEntity HighWay) {

		try {
			highWayDao.insert(HighWay);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteHighWay(HighWayEntity HighWay) {

		try {
			highWayDao.delete(HighWay);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteHighWayBatch(List<HighWayEntity> list) {

		try {
			highWayDao.deleteBatch(list);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void updateHighWay(HighWayEntity HighWay) {

		try {
			highWayDao.update(HighWay);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public HighWayEntity getObject(HighWayEntity HighWay) {
		return highWayDao.getObject(HighWay);
	}

	@Override
	public List<HighWayEntity> getHighWayList() {

		return (List<HighWayEntity>) highWayDao.findList();
	}

	@Override
	public List<HighWayEntity> getHighWayList(HighWayEntity HighWay) {
		List<HighWayEntity> list = (List<HighWayEntity>) highWayDao
				.findListByCondition(HighWay);
		return list;
	}

}
