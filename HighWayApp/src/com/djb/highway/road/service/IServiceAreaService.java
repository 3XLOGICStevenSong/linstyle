package com.djb.highway.road.service;

import java.util.List;

import com.djb.highway.road.entity.ServiceAreaEntity;

public interface IServiceAreaService {
	public void addServiceArea(ServiceAreaEntity serviceArea);

	public void deleteServiceArea(ServiceAreaEntity serviceArea);

	public void deleteServiceAreaBatch(List<ServiceAreaEntity> list);

	public void updateServiceArea(ServiceAreaEntity serviceArea);

	public ServiceAreaEntity getObject(ServiceAreaEntity serviceArea);

	public List<ServiceAreaEntity> getServiceAreaList();

	public List<ServiceAreaEntity> getServiceAreaList(
			ServiceAreaEntity serviceArea);

	public ServiceAreaEntity getObjectByLinkCode(ServiceAreaEntity serviceArea);

	
	public List<ServiceAreaEntity> getServiceAreaPointList(
                    ServiceAreaEntity serviceArea);
}
