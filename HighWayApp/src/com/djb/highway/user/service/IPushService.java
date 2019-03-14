package com.djb.highway.user.service;

import java.util.List;

import com.djb.highway.framework.entity.PageModel;

import com.djb.highway.user.entity.PushEntity;

public interface IPushService {
	public void addPush(PushEntity push);

	public void deletePush(PushEntity push);

	public void deletePushBatch(List<PushEntity> list);

	public void updatePush(PushEntity push);

	public PushEntity getPushById(int p_id);

	public PushEntity getPushById(PushEntity push);

	public List<PushEntity> getPushList(PushEntity paramEntity);

   public PageModel findPagedList(PushEntity push);
}
	
