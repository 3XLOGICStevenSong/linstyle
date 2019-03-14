package com.djb.highway.user.service;

import java.util.List;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.user.entity.OftenUsedRoadEntity;

public interface IOftenUsedRoadService {
	public void addOftenUsedRoad(OftenUsedRoadEntity oftenUsedRoad);

	public void deleteOftenUsedRoad(OftenUsedRoadEntity oftenUsedRoad);

	public void deleteOftenUsedRoadBatch(List<OftenUsedRoadEntity> list);

	public void updateOftenUsedRoad(OftenUsedRoadEntity oftenUsedRoad);

	public OftenUsedRoadEntity getOftenUsedRoadById(int our_id);

	public OftenUsedRoadEntity getOftenUsedRoadById(
			OftenUsedRoadEntity oftenUsedRoad);

	public List<OftenUsedRoadEntity> getOftenUsedRoadList();

	public PageModel findPagedList(OftenUsedRoadEntity oftenUsedRoad);

	public List<OftenUsedRoadEntity> getOftenUsedRoadList(
			OftenUsedRoadEntity oftenUsedRoad);

	public List<OftenUsedRoadEntity> getOftenUsedRoadListByTimeStamp(
			OftenUsedRoadEntity oftenUsedRoad);
	
	public OftenUsedRoadEntity getMaxTimeStampObject(
			OftenUsedRoadEntity oftenUsedRoad);
	

}
