package com.djb.highway.user.service;

import java.util.List;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.user.entity.UserSupportEntity;

public interface IUserSupportService {
	public void addUserSupport(UserSupportEntity userSupport);

	public void deleteUserSupport(UserSupportEntity userSupport);

	public void deleteUserSupportBatch(List<UserSupportEntity> list);

	public void updateUserSupport(UserSupportEntity userSupport);

	public UserSupportEntity getUserSupportById(int ur_id);

	public UserSupportEntity getUserSupportById(UserSupportEntity userSupport);

	public List<UserSupportEntity> getUserSupportList();

	public PageModel findPagedList(UserSupportEntity userSupport);

	public List<UserSupportEntity> getUserSupportList(UserSupportEntity userSupport);
}
