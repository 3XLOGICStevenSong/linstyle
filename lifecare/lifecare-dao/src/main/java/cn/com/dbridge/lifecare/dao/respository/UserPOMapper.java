package cn.com.dbridge.lifecare.dao.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dbridge.lifecare.dao.po.MobileMyInfoPO;
import cn.com.dbridge.lifecare.dao.po.UserPO;
import cn.com.dbridge.lifecare.dao.po.WebUserManagePO;
import cn.com.dbridge.lifecare.dao.po.WebUserPO;
import cn.com.dbridge.lifecare.dao.po.WebUserSelectPO;

public interface UserPOMapper {

    int deleteByPrimaryKey(Integer userId);

    int insert(UserPO record);

    List<UserPO> selectAll();
    UserPO selectByUser(UserPO userPO);

    /**
     * 根据用户信息查询用户列表
     * @param userPO
     * @return
     */
    List<UserPO> selectUserListByUser(UserPO userPO);

    UserPO selectByUserNumber(@Param("userNumber") String userNumber);

    /**
     * 
     * @Title: selectMyInfo 
     * @author 郭健
     * @Description: 获取我的信息页面信息
     * @param userPO
     * @return
     */
    MobileMyInfoPO selectMyInfo(UserPO userPO);

    /**
     * 
     * @Title: updateByPrimaryKey 
     * @author 郭健
     * @Description: 修改用户信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserPO record);

    /**
     * 
     * @Title: selectByPrimaryKey 
     * @author 郭健
     * @Description: 获取客户基本信息页面信息
     * @param userId
     * @return
     */
    UserPO selectByPrimaryKey(Integer userId);

    /**
     * 
     * @Title: queryUsersInfo 
     * @author 王林江
     * @Description: 管理页面查询用户列表信息
     * @param webUserManagePO
     * @return
     */
    List<WebUserManagePO> queryUsersInfo(WebUserManagePO webUserManagePO);

    /**
     * 
     * @Title: selectUserInfo 
     * @author 王林江
     * @Description: 获取选择用户管理信息
     * @param webUserSelectPO
     * @return
     */
    WebUserSelectPO selectUserInfo(WebUserSelectPO webUserSelectPO);

    /**
     * 
     * @Title: getUserInfoByUserId 
     * @author 王林江
     * @Description: 根据用户ID获取用户管理信息
     * @param userId
     * @return
     */
    WebUserPO getUserInfoByUserId(@Param("userId") Integer userId);

    /**
     * 
     * @Title: updateUserInfoByUserId 
     * @author 王林江
     * @Description: 根据用户ID编辑用户管理信息
     * @param webUserPO
     * @return
     */
    int updateUserInfoByUserId(WebUserPO webUserPO);

    /**
     * 
     * @Title: getUserNumberCount 
     * @author 王林江
     * @Description: 获取相同用户编号的记录数
     * @param webUserPO
     * @return
     */
    int getUserNumberCount(WebUserPO webUserPO);

    /**
     * 
     * @Title: insertUser 
     * @author 王林江
     * @Description: 添加用户管理信息处理
     * @param webUserPO
     * @return
     */
    int insertUser(WebUserPO webUserPO);

    /**
     * 
     * @Title: getNextUserNumber 
     * @author 王林江
     * @Description: 客户编号采番后五位取得
     * @return
     */
    String getNextUserNumber();

    /**
     * 
     * @Title: updatePasswordByUserNumber 
     * @author 王林江
     * @Description: 更新用户密码
     * @param webUserPO
     * @return
     */
    int updatePasswordByUserNumber(WebUserPO webUserPO);

    /**
     * 查询所有的头像
     * @return
     */
    List<String> getAllImgs();
}