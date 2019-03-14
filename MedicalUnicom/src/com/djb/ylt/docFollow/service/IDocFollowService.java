package com.djb.ylt.docFollow.service;

import java.util.List;
import com.djb.ylt.docFollow.entity.DocFollowEntity;

public interface IDocFollowService {
//	医生端 - 获取关注登录医生的患者列表
	public List<DocFollowEntity> getList(DocFollowEntity docFollowEntity);
//	医生端 - 获取登录医生关注的患者列表
	public List<DocFollowEntity> getFollowList(DocFollowEntity docFollowEntity);
	// 添加关注 先获取是否已关注
	public DocFollowEntity getObject(DocFollowEntity docFollowEntity);
	// 添加关注
	public void addFollowInterest(DocFollowEntity followInterest);
	// 取消关注
	public void deleteFollowInterest(DocFollowEntity followInterest);
}
