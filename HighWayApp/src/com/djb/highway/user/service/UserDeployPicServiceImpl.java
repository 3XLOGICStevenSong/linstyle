package com.djb.highway.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.user.dao.IUserDeployPicDao;
import com.djb.highway.user.entity.UserDeployPicEntity;

@Service("iUserDeployPicService")
public class UserDeployPicServiceImpl extends BaseService implements IUserDeployPicService {

    @Autowired
    @Qualifier("userDeployPicDao")
    private IUserDeployPicDao userDeployPicDao;

    @Override
    public void addUserDeployPic(UserDeployPicEntity userDeployPic) {
        // TODO Auto-generated method stub
        try {
            userDeployPicDao.insert(userDeployPic);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteUserDeployPic(UserDeployPicEntity userDeployPic) {
        // TODO Auto-generated method stub
        try {
            userDeployPicDao.delete(userDeployPic);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteUserDeployPicBatch(List<UserDeployPicEntity> list) {
        // TODO Auto-generated method stub
        try {
            userDeployPicDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateUserDeployPic(UserDeployPicEntity userDeployPic) {
        // TODO Auto-generated method stub
        try {
            userDeployPicDao.update(userDeployPic);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public UserDeployPicEntity getObject(UserDeployPicEntity userDeployPic) {
        return userDeployPicDao.getObject(userDeployPic);
    }

    @Override
    public List<UserDeployPicEntity> getUserDeployPicList() {
        // TODO Auto-generated method stub
        return userDeployPicDao.findList();
    }

    @Override
    public PageModel findPagedList(UserDeployPicEntity userDeployPic) {
        return userDeployPicDao.findPagedList(userDeployPic);
    }

    @Override
    public List<UserDeployPicEntity> getUserDeployPicList(UserDeployPicEntity userDeployPic) {
        List<UserDeployPicEntity> list = userDeployPicDao.findListByCondition(userDeployPic);
        return list;
    }

    @Override
    public List<UserDeployPicEntity> findMoreUserDeployPicList(UserDeployPicEntity userDeployPic) {
        List<UserDeployPicEntity> list = userDeployPicDao.findOtherList(IUserDeployPicDao.FINDMOREUSERDEPLOYPICLIST, userDeployPic, UserDeployPicEntity.class);
        return list;
    }
    @Override
    public List<UserDeployPicEntity> findMoreUserDeployPicHIdList(UserDeployPicEntity userDeployPic) {
        List<UserDeployPicEntity> list = userDeployPicDao.findOtherList(IUserDeployPicDao.FINDMOREUSERDEPLOYPICHIDLIST, userDeployPic, UserDeployPicEntity.class);
        return list;
    }
    @Override
    public List<UserDeployPicEntity> findListbyPage(UserDeployPicEntity userDeployPic) {
        List<UserDeployPicEntity> list = userDeployPicDao.findOtherList(IUserDeployPicDao.FINDLISTBYPAGE, userDeployPic, UserDeployPicEntity.class);
        return list;
    }
    // @Override
    // public List<UserDeployPicEntity>
    // findMoreUserDeployPicListBySectionId(UserDeployPicEntity userDeployPic) {
    // List<UserDeployPicEntity> list =
    // userDeployPicDao.findOtherList(IUserDeployPicDao.FINDMOREUSERDEPLOYPICLISTBYSECTIONID,
    // userDeployPic,
    // UserDeployPicEntity.class);
    // return list;
    // }

    @Override
    public List<Integer> findSupportUserIdList(UserDeployPicEntity userDeployPic) {

        List<Integer> list = userDeployPicDao.findOtherList(IUserDeployPicDao.FINDUSERSUPPORTLIST, userDeployPic, Integer.class);

        return list;
    }

    @Override
    public UserDeployPicEntity getObjectForUpdate(UserDeployPicEntity userDeployPic) {
        UserDeployPicEntity userDeployPicEntity = userDeployPicDao.getOtherObject(IUserDeployPicDao.GETOBJECTFORUPDATE, userDeployPic,
                        UserDeployPicEntity.class);

        return userDeployPicEntity;
    }

    @Override
    public List<UserDeployPicEntity> getUserDeployPicListByTimeStamp(UserDeployPicEntity userDeployPic) {

        List<UserDeployPicEntity> userDeployPicEntityList = userDeployPicDao.findOtherList(IUserDeployPicDao.GETUSERDEPLOYPICLISTBYTIMESTAMP, userDeployPic,
                        UserDeployPicEntity.class);

        return userDeployPicEntityList;
    }

    @Override
    public UserDeployPicEntity getMaxTimeStampObject(UserDeployPicEntity userDeployPic) {

        UserDeployPicEntity maxTimeStampObj = userDeployPicDao
                        .getOtherObject(IUserDeployPicDao.GETMAXTIMESTAMPOBJECT, userDeployPic, UserDeployPicEntity.class);
        return maxTimeStampObj;

    }

    @Override
    public Integer getUserDeployPicListCount(UserDeployPicEntity userDeployPic) {

        Integer count = userDeployPicDao.getOtherObject(IUserDeployPicDao.FINDLIST_COUNT, userDeployPic, Integer.class);

        return count;
    }

    @Override
    public List<UserDeployPicEntity> getUserDeployPicPageList(UserDeployPicEntity userDeployPic) {
        List<UserDeployPicEntity> list = userDeployPicDao.findOtherList(IUserDeployPicDao.FINDPAGELISTBYCONDITION, userDeployPic, UserDeployPicEntity.class);
        return list;

    }
    @Override
    public List<UserDeployPicEntity> getUserDeployPicPointList(UserDeployPicEntity userDeployPic) {
        List<UserDeployPicEntity> list = userDeployPicDao.findOtherList(IUserDeployPicDao.GETUSERDEPLOYPICPOINTLIST, userDeployPic, UserDeployPicEntity.class);
        return list;

    }
}
