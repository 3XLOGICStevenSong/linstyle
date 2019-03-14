package cn.com.dbridge.lifecare.service;

import java.util.List;

import cn.com.dbridge.lifecare.dao.po.MobileMyInfoPO;
import cn.com.dbridge.lifecare.dao.po.UserPO;
import cn.com.dbridge.lifecare.framework.base.WebPageResult;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileBasicInfoDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileMyInfoDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileUserDTO;
import cn.com.dbridge.lifecare.framework.dto.web.UserDTO;
import cn.com.dbridge.lifecare.framework.dto.web.WebUserDTO;
import cn.com.dbridge.lifecare.framework.dto.web.WebUserManageDTO;
import cn.com.dbridge.lifecare.framework.dto.web.WebUserPwdModifyDTO;
import cn.com.dbridge.lifecare.framework.dto.web.WebUserPwdResetDTO;
import cn.com.dbridge.lifecare.framework.dto.web.WebUserSelectDTO;
import cn.com.dbridge.lifecare.framework.vo.web.WebUserAddVO;
import cn.com.dbridge.lifecare.framework.vo.web.WebUserManageVO;
import cn.com.dbridge.lifecare.framework.vo.web.WebUserPwdResetVO;
import cn.com.dbridge.lifecare.framework.vo.web.WebUserVO;

public interface UserService {
    /**
     * 
     * @Title: deleteUserById
     * @Description: 根据用户ID删除用户信息
     * @param userId
     * @return
     */
    int deleteUserById(Integer userId);
    /**
     * 
     * @Title: add
     * @Description: 添加用户信息
     * @param record
     * @return
     */
    int add(MobileUserDTO mobileUserDTO);

    /**
     * 
     * @Title: getUserByUserNumber
     * @Description:根据身份编号获取用户信息
     * @param userNumber
     * @return
     */
    UserDTO getUserByUser(UserDTO userDTO);
    /**
     * 
     * @Title: queryAll
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @return
     */
    List<UserPO> queryAll();

    UserPO getUserById(Integer userId);

    /**
     * 
     * @Title: updateUser
     * @author 郭健
     * @Description: 修改用户信息
     * @param record
     * @return
     */
    int updateUser(MobileUserDTO mobileUserDTO);

    /**
     * 
     * @Title: getUserByUserUnionId
     * @Description: 根据unionId获取用户信息
     * @param unionId
     * @return
     */
    UserDTO getUserByUserUnionId(String unionId);
    /**
     * 
     * @Title: getUserByUserNumber
     * @Description: 根据用户名工号查询
     * @param username
     * @return
     */
    UserDTO getUserByUserNumber(String username);
    /**
     * 
     * @Title: getMyInfo
     * @author 郭健
     * @Description: 获取我的信息页面
     * @param mobileMyInfoDTO
     * @return
     */
    MobileMyInfoPO getMyInfo(MobileMyInfoDTO mobileMyInfoDTO);
    /**
     * 
     * @Title: getBasicInfo
     * @author 郭健
     * @Description: 获取客户信息页面信息
     * @param userId
     * @return
     */
    UserPO getBasicInfo(MobileBasicInfoDTO mobileBasicInfoDTO);

    /**
     * 
     * @Title: getUserByNumber
     * @Description: 权限分配获取用户信息
     * @param userNumber
     * @return
     */
    UserPO getUserByNumber(String userNumber);

    /****
    @Title: queryUsersInfo
    * @author 王林江
    * @Description: 管理页面查询用户列表信息
    * @param webUserManageDTO 用户管理页面用户信息查询DTO
    * @return
    */
    WebPageResult<List<WebUserManageVO>> queryUsersInfo(
            WebUserManageDTO webUserManageDTO);

    /**
     * 
     * @Title: selectUserInfo
     * @author 王林江
     * @Description: 服务池选择服务人员
     * @param webUserSelectDTO 服务池选择服务人员DTO
     * @return
     */
    int selectUserInfo(WebUserSelectDTO webUserSelectDTO);

    /**
     * 
     * @Title: getUserInfoByUserId
     * @author 王林江
     * @Description: 根据用户ID获取用户管理信息
     * @param userId 用户主键
     * @return
     */
    WebUserVO getUserInfoByUserId(Integer userId);

    /**
     * 
     * @Title: updateUserInfoByUserId
     * @author 王林江
     * @Description: 根据用户ID编辑用户管理信息
     * @param webUserDTO 用户管理信息DTO
     * @return
     */
    int updateUserInfoByUserId(WebUserDTO webUserDTO);
    /**
     * 
     * @Title: updateTyingStatus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param mobileUserDTO
     * @return
     */
    public int updateTyingStatus(MobileUserDTO mobileUserDTO);

    /**
     * 
     * @Title: addUser
     * @author 王林江
     * @Description: 添加用户管理信息处理
     * @param webUserDTO 用户管理信息DTO
     * @return
     */
    WebUserAddVO addUser(WebUserDTO webUserDTO);

    /**
     * 
     * @Title: resetPwd
     * @author 王林江
     * @Description: 重置密码处理
     * @param webUserPwdResetDTO 密码重置DTO
     * @return
     */
    WebUserPwdResetVO resetPwd(WebUserPwdResetDTO webUserPwdResetDTO);

    /**
     * 
     * @Title: modifyPwd
     * @author 王林江
     * @Description: 密码修改处理
     * @param webUserPwdModifyDTO 密码修改DTO
     * @return
     */
    int modifyPwd(WebUserPwdModifyDTO webUserPwdModifyDTO);

    /**
     * 查询全部头像
     * @return
     */
    public List<String> getAllImgs();
}