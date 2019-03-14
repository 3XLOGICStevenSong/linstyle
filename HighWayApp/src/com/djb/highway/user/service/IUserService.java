package com.djb.highway.user.service;

import java.util.List;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.user.entity.OftenUsedRoadEntity;
import com.djb.highway.user.entity.UserDeployPicEntity;
import com.djb.highway.user.entity.UserEntity;
import com.djb.highway.user.entity.UserReviewEntity;

public interface IUserService {
	public void addUser(UserEntity user);

	public void deleteUser(UserEntity user);

	public void deleteUserBatch(List<UserEntity> list);

	public void updateUser(UserEntity user);

	public UserEntity getObject(UserEntity user);

	public List<UserEntity> getUserList();

	public PageModel findPagedList(UserEntity user);

	public List<UserEntity> getUserList(UserEntity user);

	public UserEntity getUserDeployPicObject(UserEntity user);
	public List<UserDeployPicEntity> findUserDeployPicPagedList(UserEntity user);
	public UserDeployPicEntity getObject(UserDeployPicEntity userDeployPic);
    public Integer countUserNumber();

	List<UserReviewEntity> findUserReviewPagedList(
			UserDeployPicEntity userDeploy);

	UserReviewEntity getObject(UserReviewEntity userReviewEntity);

	List<UserReviewEntity> findListByCondition(UserReviewEntity UserReview);

	OftenUsedRoadEntity getObject(OftenUsedRoadEntity oftenUsedRoad);

	List<OftenUsedRoadEntity> findListByCondition(
			OftenUsedRoadEntity oftenUsedRoad);}

	
