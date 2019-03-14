package com.djb.highway.user.service;

import java.util.List;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.user.entity.UserReviewEntity;

public interface IUserReviewService {
	public void addUserReview(UserReviewEntity userReview);

	public void deleteUserReview(UserReviewEntity userReview);

	public void deleteUserReviewBatch(List<UserReviewEntity> list);

	public void updateUserReview(UserReviewEntity userReview);

	public UserReviewEntity getUserReviewById(int ur_id);

	public UserReviewEntity getUserReviewById(UserReviewEntity userReview);

	public List<UserReviewEntity> getUserReviewList();

	public PageModel findPagedList(UserReviewEntity userReview);

	public List<UserReviewEntity> getUserReviewList(UserReviewEntity userReview);
}
