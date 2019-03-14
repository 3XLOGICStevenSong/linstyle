package com.djb.highway.road.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.road.dao.IInOutSchematicDao;
import com.djb.highway.road.entity.InOutSchematicEntity;

@Service("iInOutSchematicService")
public class InOutSchematicServiceImpl extends BaseService implements
		IInOutSchematicService {

	@Autowired
	@Qualifier("inOutSchematicDao")
	private IInOutSchematicDao inOutSchematicDao;

	public void addInOutSchematic(InOutSchematicEntity inOutSchematic) {
		// TODO Auto-generated method stub
		try {
			inOutSchematicDao.insert(inOutSchematic);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public void deleteInOutSchematic(InOutSchematicEntity inOutSchematic) {
		// TODO Auto-generated method stub
		try {
			inOutSchematicDao.delete(inOutSchematic);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public void deleteInOutSchematicBatch(List<InOutSchematicEntity> list) {
		// TODO Auto-generated method stub
		try {
			inOutSchematicDao.deleteBatch(list);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public void updateInOutSchematic(InOutSchematicEntity inOutSchematic) {
		// TODO Auto-generated method stub
		try {
			inOutSchematicDao.update(inOutSchematic);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public InOutSchematicEntity getInOutSchematicById(int s_id) {
		// TODO Auto-generated method stub
		return inOutSchematicDao.getObject(s_id);
	}

	public InOutSchematicEntity getInOutSchematicById(
			InOutSchematicEntity inOutSchematic) {
		// TODO Auto-generated method stub
		return inOutSchematicDao.getObject(inOutSchematic);
	}

	public List<InOutSchematicEntity> getInOutSchematicList() {
		// TODO Auto-generated method stub
		return inOutSchematicDao.findList();
	}

	public PageModel findPagedList(InOutSchematicEntity inOutSchematic) {
		return inOutSchematicDao.findPagedList(inOutSchematic);
	}

	public List<InOutSchematicEntity> getInOutSchematicEntityList(
			InOutSchematicEntity inOutSchematic) {
		List<InOutSchematicEntity> list = inOutSchematicDao
				.findListByCondition(inOutSchematic);
		return list;
	}

	

	@Override
	public List<InOutSchematicEntity> getInOutSchematicList(
			InOutSchematicEntity inOutSchematic) {

		List<InOutSchematicEntity> list = inOutSchematicDao
				.findListByCondition(inOutSchematic);
		return list;
	}

}
