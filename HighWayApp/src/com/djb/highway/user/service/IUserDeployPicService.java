package com.djb.highway.user.service;

import java.util.List;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.user.entity.UserDeployPicEntity;

public interface IUserDeployPicService {
    public void addUserDeployPic(UserDeployPicEntity userDeployPic);

    public void deleteUserDeployPic(UserDeployPicEntity userDeployPic);

    public void deleteUserDeployPicBatch(List<UserDeployPicEntity> list);

    public void updateUserDeployPic(UserDeployPicEntity userDeployPic);

    public UserDeployPicEntity getObject(UserDeployPicEntity userDeployPic);

    public List<UserDeployPicEntity> getUserDeployPicList();

    public PageModel findPagedList(UserDeployPicEntity userDeployPic);

    public List<UserDeployPicEntity> getUserDeployPicList(UserDeployPicEntity userDeployPic);

    public List<UserDeployPicEntity> getUserDeployPicListByTimeStamp(UserDeployPicEntity userDeployPic);

    public List<UserDeployPicEntity> findMoreUserDeployPicList(UserDeployPicEntity userDeployPic);
    public List<UserDeployPicEntity> findMoreUserDeployPicHIdList(UserDeployPicEntity userDeployPic);

    // public List<UserDeployPicEntity>
    // findMoreUserDeployPicListBySectionId(UserDeployPicEntity userDeployPic);

    public List<Integer> findSupportUserIdList(UserDeployPicEntity userDeployPic);

    public UserDeployPicEntity getObjectForUpdate(UserDeployPicEntity userDeployPic);

    public UserDeployPicEntity getMaxTimeStampObject(UserDeployPicEntity userDeployPic);

    public Integer getUserDeployPicListCount(UserDeployPicEntity userDeployPic);

    public List<UserDeployPicEntity> getUserDeployPicPageList(UserDeployPicEntity userDeployPic);

    public List<UserDeployPicEntity> getUserDeployPicPointList(UserDeployPicEntity userDeployPic);
    public List<UserDeployPicEntity> findListbyPage(UserDeployPicEntity userDeployPic);
    
}
