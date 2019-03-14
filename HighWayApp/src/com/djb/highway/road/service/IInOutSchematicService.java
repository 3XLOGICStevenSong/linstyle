package com.djb.highway.road.service;

import java.util.List;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.road.entity.InOutSchematicEntity;


public interface IInOutSchematicService {
	public void addInOutSchematic(InOutSchematicEntity inOutSchematic);

	public void deleteInOutSchematic(InOutSchematicEntity inOutSchematic);

	public void deleteInOutSchematicBatch(List<InOutSchematicEntity> list);

	public void updateInOutSchematic(InOutSchematicEntity inOutSchematic);

	public InOutSchematicEntity getInOutSchematicById(int s_id);

	public InOutSchematicEntity getInOutSchematicById(
			InOutSchematicEntity inOutSchematic);

	public List<InOutSchematicEntity> getInOutSchematicList();

	public PageModel findPagedList(InOutSchematicEntity inOutSchematic);

	public List<InOutSchematicEntity> getInOutSchematicList(
			InOutSchematicEntity inOutSchematic);
}
