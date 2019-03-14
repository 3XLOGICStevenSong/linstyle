package com.djb.highway.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.user.dao.IUserDao;
import com.djb.highway.user.entity.OftenUsedRoadEntity;
import com.djb.highway.user.entity.UserDeployPicEntity;
import com.djb.highway.user.entity.UserEntity;
import com.djb.highway.user.entity.UserReviewEntity;

@Service("iUserService")
public class UserServiceImpl extends BaseService implements IUserService {

	@Autowired
	@Qualifier("userDao")
	private IUserDao userDao;

	@Override
	public void addUser(UserEntity user) {
		// TODO Auto-generated method stub
		try {
			userDao.insert(user);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(UserEntity user) {
		// TODO Auto-generated method stub
		try {
			userDao.delete(user);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteUserBatch(List<UserEntity> list) {
		// TODO Auto-generated method stub
		try {
			userDao.deleteBatch(list);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void updateUser(UserEntity user) {
		// TODO Auto-generated method stub
		try {
			userDao.update(user);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public UserEntity getObject(UserEntity user) {
		return userDao.getObject(user);
	}

	@Override
	public UserDeployPicEntity getObject(UserDeployPicEntity userDeployPic) {
		return userDao.getOtherObject(userDeployPic, UserDeployPicEntity.class);
	}

	@Override
	public UserReviewEntity getObject(UserReviewEntity userReviewEntity) {
		return userDao.getOtherObject(userReviewEntity, UserReviewEntity.class);
	}

	@Override
	public OftenUsedRoadEntity getObject(OftenUsedRoadEntity oftenUsedRoad) {
		return userDao.getOtherObject(oftenUsedRoad, OftenUsedRoadEntity.class);
	}

	@Override
	public List<UserEntity> getUserList() {
		// TODO Auto-generated method stub
		return (List<UserEntity>) userDao.findList();
	}

	@Override
	public PageModel findPagedList(UserEntity user) {
		return userDao.findPagedList(user);
	}

	@Override
	public List<UserEntity> getUserList(UserEntity user) {
		List<UserEntity> list = (List<UserEntity>) userDao
				.findListByCondition(user);
		return list;
	}

	@Override
	public UserEntity getUserDeployPicObject(UserEntity user) {
		return userDao.getObject(IUserDao.GETUSERDEPLOYPICOBJECT, user);
	}

	@Override
	public List<UserDeployPicEntity> findUserDeployPicPagedList(UserEntity user) {
		return userDao.findOtherList(IUserDao.FINDUSERDEPLOYPICPAGEDLIST, user,
				UserDeployPicEntity.class);
	}

	@Override
	public List<UserReviewEntity> findUserReviewPagedList(
			UserDeployPicEntity userDeploy) {
		return userDao.findOtherList(IUserDao.FINDUSERREVIEWPAGEDLIST,
				userDeploy, UserReviewEntity.class);
	}

	@Override
	public List<UserReviewEntity> findListByCondition(
			UserReviewEntity userReview) {
		return userDao.findOtherListByCondition(userReview,
				UserReviewEntity.class);
	}

	@Override
	public List<OftenUsedRoadEntity> findListByCondition(
			OftenUsedRoadEntity oftenUsedRoad) {
		return userDao.findOtherListByCondition(oftenUsedRoad,
				OftenUsedRoadEntity.class);
	}

	@Override
	public Integer countUserNumber() {

		return userDao.getOtherObject(IUserDao.COUNTUSRENUMBER, Integer.class);
	}

}
