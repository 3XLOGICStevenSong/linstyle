package com.djb.highway.user.service;

import java.util.List;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.user.entity.OpinionEntity;

public interface IOpinionService {
	public void addOpinion(OpinionEntity opinion);

	public void deleteOpinion(OpinionEntity opinion);

	public void deleteOpinionBatch(List<OpinionEntity> list);

	public void updateOpinion(OpinionEntity opinion);

	public OpinionEntity getOpinionById(int ur_id);

	public OpinionEntity getOpinionById(OpinionEntity opinion);

	public List<OpinionEntity> getOpinionList();

	public PageModel findPagedList(OpinionEntity opinion);

	public List<OpinionEntity> getOpinionList(OpinionEntity opinion);
}
