package com.djb.highway.bus.service;



import java.util.List;

import com.djb.highway.bus.entity.BusGroupEntity;

import com.djb.highway.framework.entity.PageModel;


public interface IBusGroupService {
	public void addGroup(BusGroupEntity group);

	public void deleteGroup(BusGroupEntity group);

	public void deleteGroupBatch(List<BusGroupEntity> list);

	public void updateGroup(BusGroupEntity group);

	public BusGroupEntity getObject(BusGroupEntity group);

	public List<BusGroupEntity> getGroupList();

	public PageModel findPagedList(BusGroupEntity group);

	public List<BusGroupEntity> getGroupList(
	                BusGroupEntity group);
	public List<BusGroupEntity> searchGroupList(
                    BusGroupEntity group);


	

}
