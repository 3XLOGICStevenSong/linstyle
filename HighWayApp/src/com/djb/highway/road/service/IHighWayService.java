package com.djb.highway.road.service;

import java.util.List;

import com.djb.highway.road.entity.HighWayEntity;

public interface IHighWayService {
	public void addHighWay(HighWayEntity highWay);

	public void deleteHighWay(HighWayEntity highWay);

	public void deleteHighWayBatch(List<HighWayEntity> list);

	public void updateHighWay(HighWayEntity highWay);

	public HighWayEntity getObject(HighWayEntity highWay);

	public List<HighWayEntity> getHighWayList();

	public List<HighWayEntity> getHighWayList(HighWayEntity highWay);

}
