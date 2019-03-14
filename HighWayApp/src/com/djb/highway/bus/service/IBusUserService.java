package com.djb.highway.bus.service;



import java.util.List;

import com.djb.highway.bus.entity.BusUserEntity;

import com.djb.highway.framework.entity.PageModel;


public interface IBusUserService {
	public void addBusUser( BusUserEntity busUser);

	public void deleteBusUser( BusUserEntity busUser);

	public void deleteBusUserBatch(List<BusUserEntity> list);

	public void updateBusUser( BusUserEntity busUser);

	public BusUserEntity getObject( BusUserEntity busUser);

	public List<BusUserEntity> getBusUserList();

	public PageModel findPagedList( BusUserEntity busUser);

	public List< BusUserEntity> getBusUserList(
	                BusUserEntity busUser);


	

}
