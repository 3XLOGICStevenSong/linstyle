package com.djb.ylt.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;

import com.djb.ylt.user.dao.IFollowInterestDao;

import com.djb.ylt.user.entity.FollowInterestEntity;

@Service("iFollowInterestService")
public class FollowInterestServiceImpl extends BaseService implements IFollowInterestService {

	@Autowired
	@Qualifier("followInterestDao")
	private IFollowInterestDao followInterestDao;

	@Override
	public void addFollowInterest(FollowInterestEntity followInterest) {

		// TODO Auto-generated method stub
		try {
			followInterestDao.insert(followInterest);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteFollowInterest(FollowInterestEntity followInterest) {

		try {
			followInterestDao.delete(followInterest);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}

	@Override
	public void updateFollowInterest(FollowInterestEntity followInterest) {

		try {
			followInterestDao.update(followInterest);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public FollowInterestEntity getObject(FollowInterestEntity followInterest) {

		return followInterestDao.getObject(followInterest);

	}

	@Override
	public List<FollowInterestEntity> getFollowInterestList(FollowInterestEntity followInterest) {
		List<FollowInterestEntity> list = (List<FollowInterestEntity>) followInterestDao.findListByCondition(followInterest);
		return list;
	}

	@Override
	public Integer getCount(FollowInterestEntity followInterest) {
		return followInterestDao.getOtherObject(IFollowInterestDao.GETCOUNT, followInterest, Integer.class);

	}

}
