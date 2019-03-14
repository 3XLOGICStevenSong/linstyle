package com.djb.highway.road.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.road.dao.IPlazaDao;
import com.djb.highway.road.entity.PlazaEntity;

@Service("iPlazaService")
public class PlazaServiceImpl extends BaseService implements IPlazaService {

	@Autowired
	@Qualifier("plazaDao")
	private IPlazaDao plazaDao;

	@Override
	public void addPlaza(PlazaEntity plaza) {

		try {
			plazaDao.insert(plaza);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deletePlaza(PlazaEntity plaza) {

		try {
			plazaDao.delete(plaza);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void updatePlaza(PlazaEntity plaza) {

		try {
			plazaDao.update(plaza);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public PlazaEntity getObject(PlazaEntity plaza) {
		return plazaDao.getObject(plaza);
	}

	@Override
	public List<PlazaEntity> getPlazaList() {

		return (List<PlazaEntity>) plazaDao.findList();
	}

	@Override
	public List<PlazaEntity> getPlazaList(PlazaEntity plaza) {
		List<PlazaEntity> list = (List<PlazaEntity>) plazaDao
				.findListByCondition(plaza);
		return list;
	}

	@Override
	public List<PlazaEntity> findTmPlazListByRoadNames(PlazaEntity plaza) {

		List<PlazaEntity> list = (List<PlazaEntity>) plazaDao.findOtherList(
				IPlazaDao.FINDLISTBYROADNAMES, plaza, PlazaEntity.class);

		return list;
	}

	  @Override
	    public List<PlazaEntity> getPlazaPointList(PlazaEntity plaza) {
	        List<PlazaEntity> list = (List<PlazaEntity>) plazaDao.findOtherList(IPlazaDao.GETPLAZAPOINTlIST, plaza, PlazaEntity.class);

	        return list;
	    }
}
