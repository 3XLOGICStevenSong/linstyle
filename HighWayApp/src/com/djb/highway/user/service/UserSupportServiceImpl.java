package com.djb.highway.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.user.dao.IUserSupportDao;
import com.djb.highway.user.entity.UserSupportEntity;

@Service("iUserSupportService")
public class UserSupportServiceImpl extends BaseService implements
		IUserSupportService {

	@Autowired
	@Qualifier("userSupportDao")
	private IUserSupportDao userSupportDao;

	@Override
	public void addUserSupport(UserSupportEntity userSupport) {
		// TODO Auto-generated method stub
		try {
			userSupportDao.insert(userSupport);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteUserSupport(UserSupportEntity userSupport) {
		// TODO Auto-generated method stub
		try {
			userSupportDao.delete(userSupport);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteUserSupportBatch(List<UserSupportEntity> list) {
		// TODO Auto-generated method stub
		try {
			userSupportDao.deleteBatch(list);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void updateUserSupport(UserSupportEntity userSupport) {
		// TODO Auto-generated method stub
		try {
			userSupportDao.update(userSupport);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public UserSupportEntity getUserSupportById(int ur_id) {
		// TODO Auto-generated method stub
		return userSupportDao.getObject(ur_id);
	}

	@Override
	public UserSupportEntity getUserSupportById(UserSupportEntity userSupport) {
		// TODO Auto-generated method stub
		return userSupportDao.getObject(userSupport);
	}

	@Override
	public List<UserSupportEntity> getUserSupportList() {
		// TODO Auto-generated method stub
		return userSupportDao.findList();
	}

	@Override
	public PageModel findPagedList(UserSupportEntity userSupport) {
		return userSupportDao.findPagedList(userSupport);
	}

	@Override
	public List<UserSupportEntity> getUserSupportList(
			UserSupportEntity userSupport) {
		List<UserSupportEntity> list = userSupportDao
				.findListByCondition(userSupport);
		return list;
	}

}
