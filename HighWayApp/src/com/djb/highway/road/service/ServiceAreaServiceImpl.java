package com.djb.highway.road.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.road.dao.IServiceAreaDao;
import com.djb.highway.road.entity.ServiceAreaEntity;

@Service("iServiceAreaService")
public class ServiceAreaServiceImpl extends BaseService implements
		IServiceAreaService {

	@Autowired
	@Qualifier("serviceAreaDao")
	private IServiceAreaDao serviceAreaDao;

	@Override
	public void addServiceArea(ServiceAreaEntity ServiceArea) {

		try {
			serviceAreaDao.insert(ServiceArea);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteServiceArea(ServiceAreaEntity ServiceArea) {

		try {
			serviceAreaDao.delete(ServiceArea);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteServiceAreaBatch(List<ServiceAreaEntity> list) {

		try {
			serviceAreaDao.deleteBatch(list);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void updateServiceArea(ServiceAreaEntity ServiceArea) {

		try {
			serviceAreaDao.update(ServiceArea);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public ServiceAreaEntity getObject(ServiceAreaEntity ServiceArea) {
		return serviceAreaDao.getObject(ServiceArea);
	}

	@Override
	public List<ServiceAreaEntity> getServiceAreaList() {

		return (List<ServiceAreaEntity>) serviceAreaDao.findList();
	}

	@Override
	public List<ServiceAreaEntity> getServiceAreaList(
			ServiceAreaEntity ServiceArea) {
		List<ServiceAreaEntity> list = (List<ServiceAreaEntity>) serviceAreaDao
				.findListByCondition(ServiceArea);
		return list;
	}

	@Override
	public ServiceAreaEntity getObjectByLinkCode(ServiceAreaEntity serviceArea) {

		return serviceAreaDao.getOtherObject(
				IServiceAreaDao.GETOBJECTBYLINKCODE, serviceArea,
				ServiceAreaEntity.class);
	}

	
	 @Override
	    public List<ServiceAreaEntity> getServiceAreaPointList(ServiceAreaEntity ServiceArea) {
	        List<ServiceAreaEntity> list = (List<ServiceAreaEntity>) serviceAreaDao.findOtherList(IServiceAreaDao.GETSERVICEAREAPOINTlIST, ServiceArea,
	                        ServiceAreaEntity.class);

	        return list;
	    }

	 
}
