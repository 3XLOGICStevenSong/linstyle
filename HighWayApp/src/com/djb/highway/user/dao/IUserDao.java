package com.djb.highway.user.dao;

import com.djb.highway.framework.dao.BaseDAO;
import com.djb.highway.user.entity.UserEntity;

public interface IUserDao extends BaseDAO<UserEntity> {

    public static final String GETOFTENUSEDROADOBJECT = "getOftenUsedRoadObject";
    public static final String GETUSERDEPLOYPICOBJECT = "getUserDeployPicObject";
    public static final String FINDOFTENUSEDROADLIST = "findOftenUsedRoadList";
    public static final String FINDOFTENUSEDROADPAGEDLIST = "findOftenUsedRoadPagedList";
    public static final String FINDUSERDEPLOYPICLIST = "findUserDeployPicList";
    public static final String FINDUSERDEPLOYPICPAGEDLIST = "findUserDeployPicPagedList";

    public static final String FINDUSERREVIEWLIST = "findUserReviewList";
    public static final String FINDUSERREVIEWPAGEDLIST = "findUserReviewPagedList";
   
    public static final String COUNTUSRENUMBER = "countUsreNumber";
    

}
