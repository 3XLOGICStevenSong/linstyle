package com.djb.ylt.user.service;

import java.util.List;

import com.djb.ylt.user.entity.FollowInterestEntity;

public interface IFollowInterestService {

	public void addFollowInterest(FollowInterestEntity followInterest);

	public void deleteFollowInterest(FollowInterestEntity followInterest);

	public void updateFollowInterest(FollowInterestEntity followInterest);

	public FollowInterestEntity getObject(FollowInterestEntity followInterest);

	public List<FollowInterestEntity> getFollowInterestList(FollowInterestEntity followInterest);

	public Integer getCount(FollowInterestEntity followInterest);
	
}
