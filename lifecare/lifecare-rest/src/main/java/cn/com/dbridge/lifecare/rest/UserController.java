package cn.com.dbridge.lifecare.rest;
import java.util.List;

import javax.validation.Valid;

import cn.com.dbridge.jtraining.upload.picture.service.impl.StorageProperties;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.dbridge.lifecare.dao.po.MobileMyInfoPO;
import cn.com.dbridge.lifecare.dao.po.UserPO;
import cn.com.dbridge.lifecare.framework.base.Result;
import cn.com.dbridge.lifecare.framework.base.WebPageResult;
import cn.com.dbridge.lifecare.framework.dblog.annotation.OperatorLog;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileBasicInfoDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileMyInfoDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileUpdateAddrDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileUpdateEmailDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileUpdateMobileDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileUserDTO;
import cn.com.dbridge.lifecare.framework.dto.web.WebUserDTO;
import cn.com.dbridge.lifecare.framework.dto.web.WebUserManageDTO;
import cn.com.dbridge.lifecare.framework.dto.web.WebUserPwdModifyDTO;
import cn.com.dbridge.lifecare.framework.dto.web.WebUserPwdResetDTO;
import cn.com.dbridge.lifecare.framework.dto.web.WebUserSelectDTO;
import cn.com.dbridge.lifecare.framework.enums.SexEnum;
import cn.com.dbridge.lifecare.framework.vo.mobile.MobileBasicInfoVO;
import cn.com.dbridge.lifecare.framework.vo.mobile.MobileMyInfoVO;
import cn.com.dbridge.lifecare.framework.vo.web.UserVO;
import cn.com.dbridge.lifecare.framework.vo.web.WebUserAddVO;
import cn.com.dbridge.lifecare.framework.vo.web.WebUserManageVO;
import cn.com.dbridge.lifecare.framework.vo.web.WebUserPwdResetVO;
import cn.com.dbridge.lifecare.framework.vo.web.WebUserVO;
import cn.com.dbridge.lifecare.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * 
 * @ClassName:UserController
 * @Description:用户信息Controller
 * @author:陈健飞
 * @date:2018年12月27日 下午2:22:06
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@RestController
@Api(tags = "用户信息")
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StorageProperties storageProperties;
    /**
     * 
     * @Title: updateUserAddr
     * @Description: 编辑地址信息
     * @param mobileUpdateAddrDTO
     * @return
     */
    @ApiOperation(value = "编辑地址信息", notes = "编辑地址信息")
    @PutMapping(value = "wechat/user/editAddr")
    @OperatorLog(module="用户管理",methods="更新地址",description="更新地址")
    public Result<Object> updateUserAddr(@RequestBody MobileUpdateAddrDTO mobileUpdateAddrDTO) {
        MobileUserDTO mobileUserDTO = new MobileUserDTO();
        BeanUtils.copyProperties(mobileUpdateAddrDTO, mobileUserDTO);
        userService.updateUser(mobileUserDTO);
        return new Result<Object>(HttpStatus.OK.value(), "操作成功");
    }

    /**
     * 
     * @Title: updateUserMobile
     * @Description: 编辑手机
     * @param mobileUpdateMobileDTO
     * @return
     */
    @ApiOperation(value = "编辑手机", notes = "编辑手机")
    @PutMapping(value = "wechat/user/editMobile")
    @OperatorLog(module="用户管理",methods="更新手机号码",description="更新手机号码")
    public Result<Object> updateUserMobile(
            @RequestBody MobileUpdateMobileDTO mobileUpdateMobileDTO) {
        MobileUserDTO mobileUserDTO = new MobileUserDTO();
        BeanUtils.copyProperties(mobileUpdateMobileDTO, mobileUserDTO);
        userService.updateUser(mobileUserDTO);
        return new Result<Object>(HttpStatus.OK.value(), "操作成功");
    }

    /**
     * 
     * @Title: updateUserMobile
     * @Description: 编辑电子邮箱
     * @param mobileUpdateEmailDTO
     * @return
     */
    @ApiOperation(value = "编辑电子邮箱", notes = "编辑电子邮箱")
    @PutMapping(value = "wechat/user/editEmail")
    @OperatorLog(module="用户管理",methods="更新电子邮箱",description="更新电子邮箱")
    public Result<Object> updateUserEmail(
            @RequestBody MobileUpdateEmailDTO mobileUpdateEmailDTO) {
        MobileUserDTO mobileUserDTO = new MobileUserDTO();
        BeanUtils.copyProperties(mobileUpdateEmailDTO, mobileUserDTO);
        userService.updateUser(mobileUserDTO);
        return new Result<Object>(HttpStatus.OK.value(), "操作成功");
    }

    /**
     * 
     * @Title: updateTyingStatus
     * @Description: 编辑绑定状态
     * @param mobileUpdateEmailDTO
     * @return
     */
    @ApiOperation(value = "编辑绑定状态", notes = "编辑绑定状态")
    @PutMapping(value = "wechat/user/untying")
    @OperatorLog(module="用户管理",methods="更新绑定状态",description="更新绑定状态")
    public Result<Object> updateTyingStatus(
            @RequestBody MobileUpdateEmailDTO mobileUpdateEmailDTO) {
        MobileUserDTO mobileUserDTO = new MobileUserDTO();
        BeanUtils.copyProperties(mobileUpdateEmailDTO, mobileUserDTO);
        userService.updateTyingStatus(mobileUserDTO);
        return new Result<Object>(HttpStatus.OK.value(), "操作成功");
    }

    /**
     * 
     * @Title: getMyInfo
     * @Description: 获取我的信息页信息
     * @param userId 用户主键
     * @return
     */
    @ApiOperation(value = "获取我的信息页信息", notes = "获取我的信息页信息")
    @GetMapping(value = "/wechat/user/myInfo/{userId}")
    public Result<MobileMyInfoVO> getMyInfo(@PathVariable(value="userId") Integer userId) {
        MobileMyInfoDTO mobileMyInfoDTO = new MobileMyInfoDTO();
        mobileMyInfoDTO.setUserId(userId);
        MobileMyInfoPO mobileMyInfoPO = userService.getMyInfo(mobileMyInfoDTO);
        MobileMyInfoVO mobileMyInfoVO = new MobileMyInfoVO();
        if (null != mobileMyInfoPO) {
            BeanUtils.copyProperties(mobileMyInfoPO, mobileMyInfoVO);
            mobileMyInfoVO.setNickName(mobileMyInfoPO.getWechatNickName());
            mobileMyInfoVO.setImg(storageProperties.getShowPath() + mobileMyInfoPO.getImg());
        }
        return new Result<MobileMyInfoVO>(HttpStatus.OK.value(),
                "操作成功", mobileMyInfoVO);
    }

    /**
     * 
     * @Title: getBasicInfo 
     * @Description: 获取客户信息页面信息
     * @param customId
     * @return
     */
    @ApiOperation(value = "根据客户ID获取客户基本信息", notes = "根据客户ID获取客户基本信息")
    @GetMapping(value = "wechat/user/basicInfo/{customId}")
    public Result<MobileBasicInfoVO> getBasicInfo(@PathVariable Integer customId) {
        MobileBasicInfoDTO mobileBasicInfoDTO = new MobileBasicInfoDTO();
        mobileBasicInfoDTO.setCustomId(customId);
        UserPO userPO = userService.getBasicInfo(mobileBasicInfoDTO );
        MobileBasicInfoVO mobileBasicInfoVO = null;
        if(null != userPO) {
            mobileBasicInfoVO = new MobileBasicInfoVO();
            BeanUtils.copyProperties(userPO, mobileBasicInfoVO);
            mobileBasicInfoVO.setCustomId(customId);
            mobileBasicInfoVO.setSex(SexEnum.getDesc(userPO.getSex()+""));
            mobileBasicInfoVO.setImg(storageProperties.getShowPath() + userPO.getImg());
        }
        return new Result<MobileBasicInfoVO>(HttpStatus.OK.value(),"操作成功",mobileBasicInfoVO);
    }

    /**
     * 
     * @Title: getUserByNumber
     * @Description: 通过员工号获取员工信息
     * @param userNumber
     * @return】
     * @author linh
     */
    @ApiOperation(value = "根据员工号获取员工信息", notes = "根据员工号获取员工信息")
    @GetMapping(value = "userInfo/{userNumber}")
    @RequiresAuthentication
    public Result<UserVO> getUserByNumber(@ApiParam(name = "userNumber", value = "员工号")
                                              @PathVariable String userNumber) {
        UserPO userPO = userService.getUserByNumber(userNumber);
        UserVO result = new UserVO();
        if (userPO != null) {
            result.setUserId(userPO.getUserId());
            result.setMobile(userPO.getMobile());
            result.setRealName(userPO.getRealName());
            return new Result<UserVO>(HttpStatus.OK.value(), "根据用户编号查询用户信息成功",
                    result);
        } else {
            return new Result<UserVO>(HttpStatus.OK.value(), "用户不存在", result);
        }

    }

    /**
     * 
     * @Title: queryUsersInfo
     * @Description: 根据用户管理页面查询条件取得用户管理信息
     * @param webUserManageDTO 用户管理页面用户信息查询DTO
     * @author:王林江
     * @return
     */
    @ApiOperation(value = "管理页面查询用户列表信息", notes = "管理页面查询用户列表信息")
    @PostMapping("/user/list")
    @RequiresAuthentication
    public WebPageResult<List<WebUserManageVO>> queryUsersInfo(
            @ApiParam(name = "webUserManageDTO", value = "用户管理页面用户信息查询DTO") @RequestBody @Valid WebUserManageDTO webUserManageDTO) {
        //页面处理结果
        WebPageResult<List<WebUserManageVO>> webPageResult = userService
                .queryUsersInfo(webUserManageDTO);
        //返回处理结果
        WebPageResult<List<WebUserManageVO>> rtnPageResult = new WebPageResult<List<WebUserManageVO>>(
                HttpStatus.OK.value(), "操作成功",
                webPageResult.getRows(), webPageResult.getTotal());
        return rtnPageResult;
    }

    /**
     * 
     * @Title: selectUserInfo 
     * @Description: 服务池选择服务人员订单更新处理
     * @param webUserSelectDTO 服务池选择服务人员DTO
     * author:王林江
     * @return
     */
    @ApiOperation(value = "服务池选择服务人员信息", notes = "服务池选择服务人员信息")
    @PostMapping(value = "/user/select")
    @RequiresAuthentication
    public Result<Object> selectUserInfo(
            @ApiParam(name = "webUserSelectDTO", value = "服务池选择服务人员DTO") @RequestBody @Valid WebUserSelectDTO webUserSelectDTO) {
        //服务池选择服务人员
        userService.selectUserInfo(webUserSelectDTO);
        return new Result<Object>(HttpStatus.OK.value(),
                "操作成功");
    }

    /**
     * 
     * @Title: getUserInfoByUserId 
     * @Description: 获取用户管理信息处理
     * @param userId 用户主键
     * author:王林江
     * @return
     */
    @GetMapping("/user/{userId}")
    @ApiOperation(value = "根据用户ID获取用户管理信息", notes = "根据用户ID获取用户管理信息")
    @RequiresAuthentication
    public Result<WebUserVO> getUserInfoByUserId(
            @ApiParam(name = "userId", value = "用户主键") @PathVariable(value = "userId") Integer userId) {
        return new Result<WebUserVO>(HttpStatus.OK.value(), "操作成功",
                userService.getUserInfoByUserId(userId));
    }

    /**
     * 
     * @Title: updateUserInfoByUserId 
     * @Description: 根据用户ID编辑用户管理信息处理
     * @param webUserDTO 用户管理信息DTO
     * author:王林江
     * @return
     */
    @ApiOperation(value = "根据用户ID编辑用户管理信息", notes = "根据用户ID编辑用户管理信息")
    @PutMapping(value = "/user")
    @OperatorLog(module = "用户管理", methods = "更新用户", description = "更新用户")
    @RequiresAuthentication
    public Result<Object> updateUserInfoByUserId(
            @ApiParam(name = "webUserDTO", value = "用户管理信息DTO") @RequestBody @Valid WebUserDTO webUserDTO) {
        //编辑用户管理信息处理
        webUserDTO.setImg(webUserDTO.getImgName());
        userService.updateUserInfoByUserId(webUserDTO);
        return new Result<Object>(HttpStatus.OK.value(), "操作成功");
    }

    /**
     * 
     * @Title: addUser 
     * @Description: 添加用户管理信息处理
     * @param webUserDTO 用户管理信息DTO
     * author:王林江
     * @return
     */
    @ApiOperation(value = "添加用户管理信息", notes = "添加用户管理信息")
    @PostMapping(value = "/user")
    @OperatorLog(module = "用户管理", methods = "添加用户", description = "添加用户")
    @RequiresAuthentication
    public Result<WebUserAddVO> addUser(
            @ApiParam(name = "webUserDTO", value = "用户管理信息DTO") @RequestBody @Valid WebUserDTO webUserDTO) {
        webUserDTO.setImg(webUserDTO.getImgName());
        return new Result<WebUserAddVO>(HttpStatus.OK.value(), "操作成功",
                userService.addUser(webUserDTO));
    }

    /**
     * 
     * @Title: resetPwd
     * @Description: 重置密码处理
     * @param webUserPwdResetDTO 密码重置DTO
     * author:王林江
     * @return
     */
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @PostMapping("/user/pwdReset")
    @OperatorLog(module = "用户管理", methods = "重置密码", description = "重置密码")
    @RequiresAuthentication
    public Result<WebUserPwdResetVO> resetPwd(
            @ApiParam(name = "webUserPwdResetDTO", value = "密码重置DTO") @RequestBody @Valid WebUserPwdResetDTO webUserPwdResetDTO) {
        return new Result<WebUserPwdResetVO>(HttpStatus.OK.value(), "操作成功",
                userService.resetPwd(webUserPwdResetDTO));
    }

    /**
     * 
     * @Title: modifyPwd
     * @Description: 密码修改处理
     * @param webUserPwdModifyDTO 密码修改DTO
     * author:王林江
     * @return
     */
    @ApiOperation(value = "密码修改", notes = "密码修改")
    @PostMapping("/user/pwdModify")
    @OperatorLog(module = "用户管理", methods = "密码修改", description = "密码修改")
    @RequiresAuthentication
    public Result<Object> modifyPwd(
            @ApiParam(name = "webUserPwdModifyDTO", value = "密码修改DTO") @RequestBody @Valid WebUserPwdModifyDTO webUserPwdModifyDTO) {
        //密码修改
        userService.modifyPwd(webUserPwdModifyDTO);
        return new Result<Object>(HttpStatus.OK.value(), "操作成功");
    }
    
    
    /**
     * 
     * @Title: deleteUserById 
     * @Description: 获取用户管理信息处理
     * @param userId 用户主键
     * author:郭健
     * @return
     */
    @DeleteMapping("/user/{userId}")
    @ApiOperation(value = "根据用户ID删除用户管理信息", notes = "根据用户ID删除用户管理信息")
    @OperatorLog(module = "用户管理", methods = "删除用户", description = "删除用户")
    @RequiresAuthentication
    public Result<Object> deleteUserById(
            @ApiParam(name = "userId", value = "用户主键") @PathVariable(value = "userId") Integer userId) {
        return new Result<Object>(HttpStatus.OK.value(), "操作成功",
                userService.deleteUserById(userId));
    }
}