package com.djb.highway.user.dao;

import com.djb.highway.framework.dao.BaseDAO;
import com.djb.highway.user.entity.UserDeployPicEntity;

public interface IUserDeployPicDao extends BaseDAO<UserDeployPicEntity> {
    public static final String FINDMOREUSERDEPLOYPICLIST = "findMoreUserDeployPicList";
    public static final String FINDMOREUSERDEPLOYPICLISTBYSECTIONID = "findMoreUserDeployPicListBySectionId";
    public static final String FINDUSERSUPPORTLIST = "findUserSupportList";
    public static final String GETOBJECTFORUPDATE = "getObjectForUpdate";
    public static final String GETUSERDEPLOYPICLISTBYTIMESTAMP = "getUserDeployPicListByTimeStamp";
    public static final String GETMAXTIMESTAMPOBJECT = "getMaxTimeStampObject";
    public static final String FINDLIST_COUNT = "findList_count";
    public static final String FINDPAGELISTBYCONDITION = "findPageListByCondition";
    public static final String FINDMOREUSERDEPLOYPICHIDLIST = "findMoreUserDeployPicHIdList";
    public static final String GETUSERDEPLOYPICPOINTLIST = "getUserDeployPicPointList";
    public static final String FINDLISTBYPAGE = "findListbyPage";
}
