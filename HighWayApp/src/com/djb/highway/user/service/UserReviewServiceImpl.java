package com.djb.highway.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.user.dao.IUserReviewDao;
import com.djb.highway.user.entity.UserReviewEntity;

@Service("iUserReviewService")
public class UserReviewServiceImpl extends BaseService implements
		IUserReviewService {

	@Autowired
	@Qualifier("userReviewDao")
	private IUserReviewDao userReviewDao;

	@Override
	public void addUserReview(UserReviewEntity userReview) {
		// TODO Auto-generated method stub
		try {
			userReviewDao.insert(userReview);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteUserReview(UserReviewEntity userReview) {
		// TODO Auto-generated method stub
		try {
			userReviewDao.delete(userReview);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteUserReviewBatch(List<UserReviewEntity> list) {
		// TODO Auto-generated method stub
		try {
			userReviewDao.deleteBatch(list);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void updateUserReview(UserReviewEntity userReview) {
		// TODO Auto-generated method stub
		try {
			userReviewDao.update(userReview);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public UserReviewEntity getUserReviewById(int ur_id) {
		// TODO Auto-generated method stub
		return userReviewDao.getObject(ur_id);
	}

	@Override
	public UserReviewEntity getUserReviewById(UserReviewEntity userReview) {
		// TODO Auto-generated method stub
		return userReviewDao.getObject(userReview);
	}

	@Override
	public List<UserReviewEntity> getUserReviewList() {
		// TODO Auto-generated method stub
		return userReviewDao.findList();
	}

	@Override
	public PageModel findPagedList(UserReviewEntity userReview) {
		return userReviewDao.findPagedList(userReview);
	}

	@Override
	public List<UserReviewEntity> getUserReviewList(UserReviewEntity userReview) {
		List<UserReviewEntity> list = userReviewDao
				.findListByCondition(userReview);
		return list;
	}

}
