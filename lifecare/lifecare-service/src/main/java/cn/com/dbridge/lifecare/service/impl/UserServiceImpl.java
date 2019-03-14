package cn.com.dbridge.lifecare.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.dbridge.jtraining.upload.picture.service.impl.StorageProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.com.dbridge.lifecare.dao.po.MobileMyInfoPO;
import cn.com.dbridge.lifecare.dao.po.NursePlanPO;
import cn.com.dbridge.lifecare.dao.po.ResponsibilityPO;
import cn.com.dbridge.lifecare.dao.po.SmsMsgPO;
import cn.com.dbridge.lifecare.dao.po.TaskPO;
import cn.com.dbridge.lifecare.dao.po.UserPO;
import cn.com.dbridge.lifecare.dao.po.UserTaskRealPO;
import cn.com.dbridge.lifecare.dao.po.WebUserManagePO;
import cn.com.dbridge.lifecare.dao.po.WebUserPO;
import cn.com.dbridge.lifecare.dao.po.WebUserSelectPO;
import cn.com.dbridge.lifecare.dao.respository.NursePlanPOMapper;
import cn.com.dbridge.lifecare.dao.respository.ResponsibilityPOMapper;
import cn.com.dbridge.lifecare.dao.respository.SmsMsgMapper;
import cn.com.dbridge.lifecare.dao.respository.TaskPOMapper;
import cn.com.dbridge.lifecare.dao.respository.UserPOMapper;
import cn.com.dbridge.lifecare.dao.respository.UserTaskRealMapper;
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
import cn.com.dbridge.lifecare.framework.exception.CustomException;
import cn.com.dbridge.lifecare.framework.exception.CustomUnauthorizedException;
import cn.com.dbridge.lifecare.framework.util.AesCipherUtil;
import cn.com.dbridge.lifecare.framework.util.AgeUtils;
import cn.com.dbridge.lifecare.framework.util.PwdUtil;
import cn.com.dbridge.lifecare.framework.vo.web.WebUserAddVO;
import cn.com.dbridge.lifecare.framework.vo.web.WebUserManageVO;
import cn.com.dbridge.lifecare.framework.vo.web.WebUserPwdResetVO;
import cn.com.dbridge.lifecare.framework.vo.web.WebUserVO;
import cn.com.dbridge.lifecare.pingyin.PinyinTool;
import cn.com.dbridge.lifecare.pingyin.PinyinTool.Type;
import cn.com.dbridge.lifecare.service.TaskService;
import cn.com.dbridge.lifecare.service.UserService;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private NursePlanPOMapper nursePlanPOMapper;
	@Autowired
    private ResponsibilityPOMapper responsibilityPOMapper;
	@Autowired
    private SmsMsgMapper smsMsgMapper;
	@Autowired
    private TaskPOMapper taskPOMapper;
    @Autowired
    private UserPOMapper userPOMapper;
    @Autowired
    private UserTaskRealMapper userTaskRealMapper;
    
    @Autowired
    private TaskService taskService;
    @Autowired
    private StorageProperties storageProperties;
    
    /**
    * @Title: deleteUserById
    * @author 郭健
    * @Description: 删除用户信息
    * @param userId
    * @return 
     */
    @Override
    public int deleteUserById(Integer userId) {
    	int count = userPOMapper.deleteByPrimaryKey(userId);
    	NursePlanPO nursePlanPO = new NursePlanPO();
    	nursePlanPO.setCustomId(userId);
    	nursePlanPOMapper.deleteNursePlan(nursePlanPO);
    	ResponsibilityPO responsibilityPO = new ResponsibilityPO();
    	responsibilityPO.setUserId(userId);
    	responsibilityPOMapper.delete(userId);
    	SmsMsgPO smsMsgPO = new SmsMsgPO();
    	smsMsgPO.setUserId(userId);
    	smsMsgMapper.deleteSmsMsg(smsMsgPO);
    	TaskPO customTaskPO = new TaskPO();
    	customTaskPO.setCustomId(userId);
    	taskPOMapper.deleteTask(customTaskPO);
    	TaskPO serviceTaskPO = new TaskPO();
    	serviceTaskPO.setServicePersonId(userId);
    	taskPOMapper.deleteTask(serviceTaskPO);
    	UserTaskRealPO userTaskRealPO = new UserTaskRealPO();
    	userTaskRealPO.setUserId(userId);
    	userTaskRealMapper.deleteUserTaskReal(userTaskRealPO);
    	return count;
    }

    @Override
    public int add(MobileUserDTO mobileUserDTO) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public List<UserPO> queryAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /****
    @Title: updateUser
    * @author 郭健
    * @Description: 修改用户信息
    * @param record
    * @return
    */
    @Override
    public int updateUser(MobileUserDTO mobileUserDTO) {
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(mobileUserDTO, userPO);
        int affectNum = userPOMapper.updateByPrimaryKey(userPO);
        return affectNum;
    }
    
    @Override
    public int updateTyingStatus(MobileUserDTO mobileUserDTO) {
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(mobileUserDTO, userPO);
        userPO.setOpenId("");
        userPO.setWechatNickName("");
        int affectNum = userPOMapper.updateByPrimaryKey(userPO);
        return affectNum;
    }

    /**   
     * Title: getMyInfo  
     * Description:获取我的信息页信息
     * @author 郭健
     * @param mobileMyInfoDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.UserService#selectMyInfo(cn.com.dbridge.lifecare.framework.dto.mobile.MobileMyInfoDTO)   
     */
    @Override
    public MobileMyInfoPO getMyInfo(MobileMyInfoDTO mobileMyInfoDTO) {
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(mobileMyInfoDTO, userPO);
        MobileMyInfoPO mobileMyInfoPO = userPOMapper.selectMyInfo(userPO);
        userPO.setUserType("2");
        if (null == mobileMyInfoPO) {
            return null;
        }
        return mobileMyInfoPO;
    }
    
    /**
     * 
     * Title: getBasicInfo  
     * Description:获取客户信息页面信息
     * @author 郭健
     * @param mobileBasicInfoDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.UserService#getBasicInfo(cn.com.dbridge.lifecare.framework.dto.mobile.MobileBasicInfoDTO)
     */
    @Override
    public UserPO getBasicInfo(MobileBasicInfoDTO mobileBasicInfoDTO) {
        Integer customId = mobileBasicInfoDTO.getCustomId();
        UserPO userPoParam = new UserPO();
        userPoParam.setUserId(customId);
        UserPO userPO = userPOMapper.selectByUser(userPoParam);
        if (null == userPO) {
            return null;
        }
        return userPO;
    }

    @Override
    public UserPO getUserById(Integer userId) {
        UserPO userPO = userPOMapper.selectByPrimaryKey(userId);
        if (null == userPO) {
            return null;
        }
        return userPO;
    }

    @Override
    public UserDTO getUserByUserUnionId(String unionId) {
        UserPO userPoParam = new UserPO();
        userPoParam.setUnionId(unionId);
        UserPO userPO = userPOMapper.selectByUser(userPoParam);
        if (null == userPO) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userPO, userDTO);
        return userDTO;
    }

    @Override
    public UserDTO getUserByUserNumber(String userNumber) {
        UserDTO userDTO = new UserDTO();
        UserPO userPO  = null;
        try {
            userPO = userPOMapper.selectByUserNumber(userNumber);
            if (null == userPO) {
                return null;
            }
        } catch (Exception e) {
          //这里就是为了满足业务需要，的写法
            return null;
        }
        BeanUtils.copyProperties(userPO, userDTO);
        return userDTO;
    }

    

    @Override
    public UserDTO getUserByUser(UserDTO userDTO) {
        UserPO userPO  = new   UserPO();
        BeanUtils.copyProperties(userDTO, userPO);
        userPO  = userPOMapper.selectByUser(userPO);
        if(null == userPO) {
            return null;
        }
        BeanUtils.copyProperties(userPO, userDTO);
        return userDTO;
    }
    /**
     * 
     * @Title: getUserByNumber
     * @Description: 权限分配检索员工
     * @param userNumber
     * @return
     * @author linh
     */
    @Override
    public UserPO getUserByNumber(String userNumber) {
        UserPO userPO = null;
        try {
            userPO = userPOMapper.selectByUserNumber(userNumber);
            if (null == userPO) {
                return null;
            }
        } catch (Exception e) {
            //这里就是为了满足业务需要，的写法
            return null;
        }
        return userPO;
    }
    /****
    @Title: queryUsersInfo
    * @author 王林江
    * @Description: 管理页面查询用户列表信息
    * @param webUserManageDTO 用户管理页面用户信息查询DTO
    * @return
    */
    @Override
    public WebPageResult<List<WebUserManageVO>> queryUsersInfo(WebUserManageDTO webUserManageDTO) {
        //页面处理结果
        WebPageResult<List<WebUserManageVO>> webPageResult = new WebPageResult<List<WebUserManageVO>>();
        //分页数据
        Page<WebUserManagePO> pages = null;

        //check error标志
        boolean isError = false;
        //预约日期，预约时间Begin，预约时间End关联Check
        //预约日期，预约时间Begin，预约时间End都为空的时候
        if (webUserManageDTO.getOrderDate() == null
                && webUserManageDTO.getOrderBeginTime() == null
                && webUserManageDTO.getOrderEndTime() == null) {
            isError = false;
            //预约日期，预约时间Begin，预约时间End都为不空的时候    
        } else if (webUserManageDTO.getOrderDate() != null
                && webUserManageDTO.getOrderBeginTime() != null
                && webUserManageDTO.getOrderEndTime() != null) {
            isError = false;
            //预约日期，预约时间Begin，预约时间End不同时为空的时候
        } else {
            isError = true;
        }

        //有错的情况
        if (isError) {
            throw new CustomException(
                    "预约日期，预约时间Begin，预约时间End设定不正：服务池选择服务人员时，预约日期，预约时间Begin，预约时间End必须同时入力");
        }
        
        //查询条件赋值
        WebUserManagePO webUserManagePO = new WebUserManagePO();
        BeanUtils.copyProperties(webUserManageDTO, webUserManagePO);

        //性别要求(0:不限 1:男 2:女)
        if (webUserManageDTO.getSexRequire() != null) {
            //1:男
            if (webUserManageDTO.getSexRequire() == 1) {
                webUserManagePO.setSex(new Byte("0"));
            }
            // 2:女
            if (webUserManageDTO.getSexRequire() == 2) {
                webUserManagePO.setSex(new Byte("1"));
            }
        }

        //订单主键不为空的场合，预约日期，预约时间Begin，预约时间End关联取得
        if (webUserManageDTO.getId() != null) {
            //check error标志
            isError = false;
            TaskPO taskPO = taskService.getTaskById(webUserManageDTO.getId());
            if (taskPO != null) {
                //预约日期，预约时间Begin，预约时间End关联Check
                //预约日期，预约时间Begin，预约时间End都为空的时候
                if (taskPO.getOrderDate() == null
                        && taskPO.getOrderBeginTime() == null
                        && taskPO.getOrderEndTime() == null) {
                    isError = false;
                    //预约日期，预约时间Begin，预约时间End都为不空的时候    
                } else if (taskPO.getOrderDate() != null
                        && taskPO.getOrderBeginTime() != null
                        && taskPO.getOrderEndTime() != null) {
                    isError = false;
                    //预约日期
                    webUserManagePO.setOrderDate(taskPO.getOrderDate());
                    //预约时间Begin
                    webUserManagePO
                            .setOrderBeginTime(taskPO.getOrderBeginTime());
                    //预约时间End
                    webUserManagePO.setOrderEndTime(taskPO.getOrderEndTime());
                    //预约日期，预约时间Begin，预约时间End不同时为空的时候
                } else {
                    isError = true;
                }
                //有错的情况
                if (isError) {
                    throw new CustomException(
                            "预约日期，预约时间Begin，预约时间End取得失败：服务池选择服务人员时，预约日期，预约时间Begin，预约时间End必须同时有值");
                }
                //性别要求(0:不限 1:男 2:女)
                if (taskPO.getSexRequire() != null) {
                    //1:男
                    if (taskPO.getSexRequire() == 1) {
                        webUserManagePO.setSex(new Byte("0"));
                    }
                    // 2:女
                    if (taskPO.getSexRequire() == 2) {
                        webUserManagePO.setSex(new Byte("1"));
                    }
                }
                //服务类别编码(A:生活助理 B:老龄照护 C:临床护理)
                if (taskPO.getServiceCategoryCode() != null) {
                    webUserManagePO.setServiceCategoryCode(
                            taskPO.getServiceCategoryCode());
                }

            } else {
                throw new CustomException(
                        "订单取得失败：服务池选择服务人员时，订单主键=" + webUserManageDTO.getId()
                                + "取得失败");
            }
        }

        //分页数据设定
        if (webUserManageDTO.getOffset() != null
                && webUserManageDTO.getLimit() != null) {
            pages = PageHelper.offsetPage(webUserManageDTO.getOffset(),
                    webUserManageDTO.getLimit());
        }
        //获得用户列表信息
        List<WebUserManagePO> webUserManagePOList = userPOMapper.queryUsersInfo(webUserManagePO);
        //获得用户列表信息
        List<WebUserManageVO> webUserManageVOList = new ArrayList<WebUserManageVO>();
        if (webUserManagePOList != null && webUserManagePOList.size() > 0) {

            for (WebUserManagePO param : webUserManagePOList) {
                //结果赋值
                WebUserManageVO webUserManageVO = new WebUserManageVO();
                BeanUtils.copyProperties(param, webUserManageVO);
                //年龄设定
                if (webUserManageVO.getBirthday() != null) {
                    //年龄计算
                    Integer age = AgeUtils.getAgeByBirthday(
                            webUserManageVO.getBirthday());
                    //年龄设定
                    webUserManageVO.setAge(age);
                }
                webUserManageVOList.add(webUserManageVO);
            }

        } else {
            webUserManageVOList = null;
        }
        //用户列表信息
        webPageResult.setRows(webUserManageVOList);
        //分页数据设定
        if (webUserManageDTO.getOffset() != null
                && webUserManageDTO.getLimit() != null) {
            //记录总数
            webPageResult.setTotal(pages.getTotal());
        }
        return webPageResult;
    }

    /****
    @Title: selectUserInfo
    * @author 王林江
    * @Description: 服务池选择服务人员
    * @param webUserSelectDTO 服务池选择服务人员DTO
    * @return
    */
    @Override
    public int selectUserInfo(WebUserSelectDTO webUserSelectDTO) {
        //页面处理结果
        int rtnNum = 0;
        //影响数据的条数
        int affectNum = 0;
        //查询条件赋值
        WebUserSelectPO webUserSelectPO = new WebUserSelectPO();
        BeanUtils.copyProperties(webUserSelectDTO, webUserSelectPO);

        //check error标志
        boolean isError = false;
        TaskPO taskPO = taskService.getTaskById(webUserSelectDTO.getId());
        if (taskPO != null) {
            //预约日期，预约时间Begin，预约时间End关联Check
            //预约日期，预约时间Begin，预约时间End都为空的时候
            if (taskPO.getOrderDate() == null
                    && taskPO.getOrderBeginTime() == null
                    && taskPO.getOrderEndTime() == null) {
                isError = false;
                //预约日期，预约时间Begin，预约时间End都为不空的时候    
            } else if (taskPO.getOrderDate() != null
                    && taskPO.getOrderBeginTime() != null
                    && taskPO.getOrderEndTime() != null) {
                isError = false;
                //预约日期
                webUserSelectPO.setOrderDate(taskPO.getOrderDate());
                //预约时间Begin
                webUserSelectPO.setOrderBeginTime(taskPO.getOrderBeginTime());
                //预约时间End
                webUserSelectPO.setOrderEndTime(taskPO.getOrderEndTime());
                //预约日期，预约时间Begin，预约时间End不同时为空的时候
            } else {
                isError = true;
            }
        } else {
            isError = true;
        }
        //有错的情况
        if (isError) {
            throw new CustomException(
                    "预约日期，预约时间Begin，预约时间End取得失败：服务池选择服务人员时，预约日期，预约时间Begin，预约时间End必须同时有值");
        }

        //检查时间服务任务池是否冲突
        int conflictTaskCnt = taskPOMapper
                .getServiceTimeConflictTaskCnt(webUserSelectPO);
        //有冲突任务
        if (conflictTaskCnt > 0) {
            throw new CustomException("服务人员选择不正：所选服务人员在任务的预约时间内有其他任务");
        }
        //订单服务人员更新实行
        affectNum = taskPOMapper.updateServicePersonId(webUserSelectPO);
        //页面处理结果
        rtnNum = rtnNum + affectNum;
        //订单服务人员更新成功的场合
        if (affectNum > 0) {
            //根据订单编号删除任务池的订单查看信息
            affectNum = userTaskRealMapper
                    .deleteByTaskId(webUserSelectDTO.getId());
            //页面处理结果
            rtnNum = rtnNum + affectNum;

            //短信消息表PO
            SmsMsgPO smsMsgPO = new SmsMsgPO();
            //订单编号
            smsMsgPO.setTaskId(webUserSelectDTO.getId());
            //用户Id
            smsMsgPO.setUserId(webUserSelectDTO.getUserId());
            //创建人
            smsMsgPO.setCreateBy(webUserSelectDTO.getLoginUserId());
            smsMsgPO.setUpdateTime(new Date());
            smsMsgPO.setUpdateBy(webUserSelectDTO.getLoginUserId());
            //订单分配服务人员的时候，为服务人员短添加短信处理
            affectNum = smsMsgMapper.insertServiceSmsMsgByOrderId(smsMsgPO);
            //页面处理结果
            rtnNum = rtnNum + affectNum;
        }

        //页面处理结果
        return rtnNum;
    }

    /****
    @Title: getUserInfoByUserId
    * @author 王林江
    * @Description: 根据用户ID获取用户管理信息
    * @param userId 用户主键
    * @return
    */
    @Override
    public WebUserVO getUserInfoByUserId(Integer userId) {
        //页面处理结果
        WebUserVO rtnVO = null;
        //服务类别(A:生活助理 B:老龄照护 C:临床护理)
        List<String> serviceCategoryLevel = null;
        //根据用户ID 获取用户管理信息
        WebUserPO webUserPO = userPOMapper.getUserInfoByUserId(userId);
        if (null != webUserPO) {
            //页面处理结果
            rtnVO = new WebUserVO();
            //结果赋值
            BeanUtils.copyProperties(webUserPO, rtnVO);

            //拼接返回IMG属性为可访问的http地址
            String img = rtnVO.getImg();
            if(StringUtils.isNotEmpty(img)){
                rtnVO.setImg(storageProperties.getShowPath()+ img);
            }

            //服务类别(A:生活助理 B:老龄照护 C:临床护理)
            if (webUserPO.getServiceCategoryLevel() != null
                    && webUserPO.getServiceCategoryLevel().length() == 3) {
                //服务类别列表
                serviceCategoryLevel = new ArrayList<String>();
                //服务类别等级第三位是1场合
                if (webUserPO.getServiceCategoryLevel().charAt(2) == '1') {
                    serviceCategoryLevel.add("A");
                }
                //服务类别等级第二位是1场合
                if (webUserPO.getServiceCategoryLevel().charAt(1) == '1') {
                    serviceCategoryLevel.add("B");
                }
                //服务类别等级第一位是1场合
                if (webUserPO.getServiceCategoryLevel().charAt(0) == '1') {
                    serviceCategoryLevel.add("C");
                }
                //服务类别(A:生活助理 B:老龄照护 C:临床护理)设定
                rtnVO.setServiceCategoryLevel(serviceCategoryLevel);
            }
        }
        return rtnVO;
    }

    /****
    @Title: updateUserInfoByUserId
    * @author 王林江
    * @Description: 根据用户ID编辑用户管理信息
    * @param webUserDTO 根据用户ID编辑用户管理信息DTO
    * @return
    */
    @Override
    public int updateUserInfoByUserId(WebUserDTO webUserDTO) {
        //校验用户编号唯一
        checkUserNumberUnique(webUserDTO);

        //校验身份证号唯一
        checkIdCardUnique(webUserDTO);

        //编辑用户管理信息PO
        WebUserPO webUserPO = new WebUserPO();
        //编辑用户管理信息PO赋值
        BeanUtils.copyProperties(webUserDTO, webUserPO);

        //用户ID必须Check
        if (webUserDTO.getUserId() == null) {
            throw new CustomException("用户ID设定不正：用户ID必须入力");
        }
        //2:服务人员3:后台用户场合
        if (webUserDTO.getUserType() != null && webUserDTO.getUserType() != 1) {
            //用户编号跟客户采番规则一样的时候
            if (webUserDTO.getUserNumber().indexOf("ZQM") == 0
                    || webUserDTO.getUserNumber().indexOf("ZQF") == 0) {
                throw new CustomException("用户编号设定不正：用户编号不能以ZQM或ZQF开头");
            }

            //用户编号必须入力Check
            if (StringUtils.isEmpty(webUserDTO.getUserNumber())) {
                throw new CustomException("用户编号设定不正：用户编号必须入力");
            }
            //用户编号重复Check
            int repeatCnt = userPOMapper.getUserNumberCount(webUserPO);
            if (repeatCnt > 0) {
                throw new CustomException("用户编号设定不正：用户编号已存在");
            }
        }
        //用户状态1:停用的场合
        if (webUserDTO.getUserStatus() != null
                && webUserDTO.getUserStatus() == 1) {
            //1:客户 2:服务人员
            if (webUserDTO.getUserType() != null
                    && webUserDTO.getUserType() != 3) {
                //获取用户待分配或待未完成任务数
                int usedTaskCnt = taskPOMapper
                        .getUsedTaskCnt(webUserDTO.getUserId());
                //用户有待分配或待未完成任务
                if (usedTaskCnt > 0) {
                    throw new CustomException(
//                            "状态设定不正：用户" + webUserDTO.getRealName()
//                            + "有待分配待或者未完成任务，不能停用该用户。"
                    		"该服务人员有未完成的服务，不能停用"
                    		);
                }
            }
        }
        
        //拼音名称(客户名称、服务人员名称、后台用户名称)
        if (!StringUtils.isEmpty(webUserDTO.getRealName())) {
            //拼音名称(客户名称、服务人员名称、后台用户名称)
            String realNamePinyin = "";
            //拼音转换工具
            PinyinTool tool = new PinyinTool();
            //拼音转换
            try {
                realNamePinyin = tool.toPinYin(webUserDTO.getRealName(), "",
                        Type.UPPERCASE);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                throw new CustomException("拼音名称不正：拼音名称转换失败");
            }
            webUserPO.setRealNamePinyin(realNamePinyin);
        }
        
        //拼音街道小区
        if (!StringUtils.isEmpty(webUserDTO.getStreetVillage())) {
            //拼音街道小区
            String streetVillagePinyin = "";
            //拼音转换工具
            PinyinTool tool = new PinyinTool();
            //拼音转换
            try {
                streetVillagePinyin = tool.toPinYin(
                        webUserDTO.getStreetVillage(),
                        "",
                        Type.UPPERCASE);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                throw new CustomException("拼音街道小区不正：拼音街道小区转换失败");
            }
            webUserPO.setStreetVillagePinyin(streetVillagePinyin);
        }
        
        //更新人
        webUserPO.setUpdateBy(webUserDTO.getLoginUserId());

        //2:服务人员3:后台用户场合,密码重新设定
        if (webUserDTO.getUserType() != null && webUserDTO.getUserType() != 1) {
            //密码
            String password = "";
            //DB原纪录信息取得
            WebUserPO pwdPO = userPOMapper
                    .getUserInfoByUserId(webUserDTO.getUserId());
            //DB编号
            String dbUserNumber = pwdPO.getUserNumber();
            //DB密码
            if (webUserDTO.getUserNumber() != null
                    && !(webUserDTO.getUserNumber().equals(dbUserNumber))) {
                //原密码取得
                String key = "";
                String dbPassword = pwdPO.getPassword();
                //密钥取得
                if (!StringUtils.isEmpty(dbPassword)) {
                    key = AesCipherUtil.deCrypto(dbPassword);
                }
                //密码不为空的场合
                if (dbUserNumber != null
                        && (key.length() > dbUserNumber.length())) {
                    password = key.substring(dbUserNumber.length());
                }

                //密码AES復号化
                key = AesCipherUtil.enCrypto(password);
                //密码设定
                webUserPO.setPassword(key);

            }
        }
        //2:服务人员场合,服务类别(A:生活助理 B:老龄照护 C:临床护理)设定
        if (webUserDTO.getUserType() != null && webUserDTO.getUserType() == 2) {
            //服务类别(A:生活助理 B:老龄照护 C:临床护理)非空的场合
            if (webUserDTO.getServiceCategoryLevel() != null
                    && webUserDTO.getServiceCategoryLevel().size() > 0) {
                //服务类别(000:未设定 001:生活助理 010:老龄照护 100:临床护理 011:生活助理 + 老龄照护 101:生活助理 + 临床护理 110:老龄照护 + 临床护理 111:生活助理 + 老龄照护 + 临床护理)
                StringBuffer serviceCategoryLevel = new StringBuffer("000");
                for (String param : webUserDTO
                        .getServiceCategoryLevel()) {
                    //A:生活助理设定的场合
                    if("A".equals(param)) {
                        serviceCategoryLevel.setCharAt(2, '1');
                        //B:老龄照护设定的场合
                    } else if ("B".equals(param)) {
                        serviceCategoryLevel.setCharAt(1, '1');
                        //C:临床护理设定的场合
                    } else if ("C".equals(param)) {
                        serviceCategoryLevel.setCharAt(0, '1');
                    }
                }
                //服务类别(000:未设定 001:生活助理 010:老龄照护 100:临床护理 011:生活助理 + 老龄照护 101:生活助理 + 临床护理 110:老龄照护 + 临床护理 111:生活助理 + 老龄照护 + 临床护理)
                webUserPO.setServiceCategoryLevel(
                        serviceCategoryLevel.toString());
            } else {
            	//如果ServiceCategoryLevel没传递或者为空数组，将其初始化为000
            	webUserPO.setServiceCategoryLevel("000");
            }
        }
        //根据用户ID编辑用户管理信息
        int affectNum = userPOMapper.updateUserInfoByUserId(webUserPO);
        return affectNum;
    }

    /****
    @Title: addUser
    * @author 王林江
    * @Description: 添加用户管理信息处理
    * @param webUserDTO 用户管理信息DTO
    * @return
    */
    @Override
    public WebUserAddVO addUser(WebUserDTO webUserDTO) {
        //校验用户编号唯一
        checkUserNumberUnique(webUserDTO);

        //校验身份证号唯一
        checkIdCardUnique(webUserDTO);

        //页面处理结果
        WebUserAddVO rtnVO = null;
        //用户管理信息PO
        WebUserPO webUserPO = new WebUserPO();
        //插入数据的条数
        int affectNum = 0;
        //用户管理信息PO赋值
        BeanUtils.copyProperties(webUserDTO, webUserPO);
        //编号(客户编号、服务人员编号、后台用户编号)处理
        //1:客户场合
        if (webUserDTO.getUserType() != null && webUserDTO.getUserType() == 1) {
            //用户编号
            String userNumber = "ZQ";
            //用户编号采番
            String userNumberRight = userPOMapper.getNextUserNumber();
            //首位客户编号
            if (StringUtils.isEmpty(userNumberRight)) {
                userNumberRight = "00001";
            }
            //性别男的场合
            if (webUserDTO.getSex() != null && webUserDTO.getSex() == 0) {
                userNumber = userNumber + "M" + userNumberRight;
                //性别女的场合   
            } else {
                userNumber = userNumber + "F" + userNumberRight;
            }
            //用户编号
            webUserPO.setUserNumber(userNumber);

            //2:服务人员3:后台用户场合   
        } else if (webUserDTO.getUserType() != null
                && webUserDTO.getUserType() != 1) {
            //用户编号必须入力Check
            if (StringUtils.isEmpty(webUserDTO.getUserNumber())) {
                throw new CustomException("用户编号设定不正：用户编号必须入力");
            }
            //用户编号跟客户采番规则一样的时候
            if (webUserDTO.getUserNumber().indexOf("ZQM") == 0
                    || webUserDTO.getUserNumber().indexOf("ZQF") == 0) {
                throw new CustomException("用户编号设定不正：用户编号不能以ZQM或ZQF开头");
            }
            //用户编号重复Check
            int repeatCnt = userPOMapper.getUserNumberCount(webUserPO);
            if (repeatCnt > 0) {
                throw new CustomException("用户编号设定不正：用户编号重复");
            }
        }

        //密码设定处理
        //生成6位密码
        String password = PwdUtil.makePWD(6);
        //密码AES復号化
        String key = AesCipherUtil.enCrypto(password);
        //密码设定
        webUserPO.setPassword(key);

        //拼音名称(客户名称、服务人员名称、后台用户名称)
        if (!StringUtils.isEmpty(webUserDTO.getRealName())) {
            //拼音名称(客户名称、服务人员名称、后台用户名称)
            String realNamePinyin = "";
            //拼音转换工具
            PinyinTool tool = new PinyinTool();
            //拼音转换
            try {
                realNamePinyin = tool.toPinYin(webUserDTO.getRealName(), "",
                        Type.UPPERCASE);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                throw new CustomException("拼音名称不正：拼音名称转换失败");
            }
            webUserPO.setRealNamePinyin(realNamePinyin);
        }
        
        //拼音街道小区
        if (!StringUtils.isEmpty(webUserDTO.getStreetVillage())) {
            //拼音街道小区
            String streetVillagePinyin = "";
            //拼音转换工具
            PinyinTool tool = new PinyinTool();
            //拼音转换
            try {
                streetVillagePinyin = tool.toPinYin(
                        webUserDTO.getStreetVillage(),
                        "",
                        Type.UPPERCASE);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                throw new CustomException("拼音街道小区不正：拼音街道小区转换失败");
            }
            webUserPO.setStreetVillagePinyin(streetVillagePinyin);
        }

        //2:服务人员场合,服务类别(A:生活助理 B:老龄照护 C:临床护理)设定
        if (webUserDTO.getUserType() != null && webUserDTO.getUserType() == 2) {
            //服务类别(A:生活助理 B:老龄照护 C:临床护理)非空的场合
            if (webUserDTO.getServiceCategoryLevel() != null
                    && webUserDTO.getServiceCategoryLevel().size() > 0) {
                //服务类别(000:未设定 001:生活助理 010:老龄照护 100:临床护理 011:生活助理 + 老龄照护 101:生活助理 + 临床护理 110:老龄照护 + 临床护理 111:生活助理 + 老龄照护 + 临床护理)
                StringBuffer serviceCategoryLevel = new StringBuffer("000");
                for (String param : webUserDTO.getServiceCategoryLevel()) {
                    //A:生活助理设定的场合
                    if ("A".equals(param)) {
                        serviceCategoryLevel.setCharAt(2, '1');
                        //B:老龄照护设定的场合
                    } else if ("B".equals(param)) {
                        serviceCategoryLevel.setCharAt(1, '1');
                        //C:临床护理设定的场合
                    } else if ("C".equals(param)) {
                        serviceCategoryLevel.setCharAt(0, '1');
                    }
                }
                //服务类别(000:未设定 001:生活助理 010:老龄照护 100:临床护理 011:生活助理 + 老龄照护 101:生活助理 + 临床护理 110:老龄照护 + 临床护理 111:生活助理 + 老龄照护 + 临床护理)
                webUserPO.setServiceCategoryLevel(
                        serviceCategoryLevel.toString());
            }else {
            	   webUserPO.setServiceCategoryLevel("000");
            }
        }

        //创建人
        webUserPO.setCreateBy(webUserDTO.getLoginUserId());

        //根据用户ID编辑用户管理信息
        affectNum = userPOMapper.insertUser(webUserPO);
        //添加用户管理信息处理
        if (affectNum > 0) {
            //页面处理结果
            rtnVO = new WebUserAddVO();
            //用户主键
            rtnVO.setUserId(webUserPO.getUserId());
            //密码设定
            rtnVO.setPassword(password);
            //编号(客户编号、服务人员编号、后台用户编号)
            rtnVO.setUserNumber(webUserPO.getUserNumber());
            //名称(客户名称、服务人员名称、后台用户名称)
            rtnVO.setRealName(webUserPO.getRealName());
        }
        //页面处理结果
        return rtnVO;
    }

    /**
     * 用户工号唯一校验
     * 返回List部分代码就是为了解决db垃圾数据的解决方案
     * @param webUserDTO
     */
    private void checkUserNumberUnique(WebUserDTO webUserDTO) {
        Byte userType = webUserDTO.getUserType();
        //1:客户 用户是通过后端数据库生成，不需要这里进行校验
        if(1 != userType){
            UserPO userPO = new UserPO();
            userPO.setUserNumber(webUserDTO.getUserNumber());
            List<UserPO> containUserNumberList = userPOMapper.selectUserListByUser(userPO);
            if (!CollectionUtils.isEmpty(containUserNumberList)) {
                Integer userId = webUserDTO.getUserId();
                if (userId != null) {
                    //更新
                    Integer dbUserId = containUserNumberList.get(0).getUserId();
                    if (!userId.equals(dbUserId)) {
                        throw new CustomException("工号必须唯一");
                    }
                } else {
                    //添加
                    throw new CustomException("工号必须唯一");
                }
            }
        }
    }
    /**
     * 身份证号唯一校验
     * 返回List部分代码就是为了解决db垃圾数据的解决方案
     * @param webUserDTO
     */
    private void checkIdCardUnique(WebUserDTO webUserDTO) {
        //校验身份证号唯一
        UserPO userPO = new UserPO();
        userPO.setUserType(webUserDTO.getUserType()+"");
        userPO.setIdCard(webUserDTO.getIdCard());
        List<UserPO> containIdCardList = userPOMapper.selectUserListByUser(userPO);
        if(!CollectionUtils.isEmpty(containIdCardList)){
            Integer userId = webUserDTO.getUserId();
            if (userId != null) {
                //更新
                Integer dbUserId = containIdCardList.get(0).getUserId();
                if (!userId.equals(dbUserId)) {
                    throw new CustomException("身份证号码必须唯一");
                }
            } else {
                //添加
                throw new CustomException("身份证号码必须唯一");
            }
        }
    }

    /****
    @Title: resetPwd
    * @author 王林江
    * @Description: 重置密码处理
    * @param webUserPwdResetDTO 密码重置DTO
    * @return
    */
    @Override
    public WebUserPwdResetVO resetPwd(WebUserPwdResetDTO webUserPwdResetDTO) {
        //返回密码
        WebUserPwdResetVO rtnPwd = null;
        //用户管理信息PO
        WebUserPO webUserPO = new WebUserPO();
        //更新数据的条数
        int affectNum = 0;
        //登录名
        webUserPO.setUserNumber(webUserPwdResetDTO.getUserNumber());
        //更新人
        webUserPO.setUpdateBy(webUserPwdResetDTO.getLoginUserId());

        //密码设定处理
        //生成6位密码
        String password = PwdUtil.makePWD(6);
        //密码AES復号化
        String key = AesCipherUtil.enCrypto(password);
        //密码设定
        webUserPO.setPassword(key);
        //重置密码处理
        affectNum = userPOMapper.updatePasswordByUserNumber(webUserPO);
        //有记录更新的场合
        if (affectNum > 0) {
            rtnPwd = new WebUserPwdResetVO();
            //返回密码
            rtnPwd.setPassword(password);
        } else {
            throw new CustomException("登录名输入错误");
        }
        return rtnPwd;
    }

    /****
    @Title: modifyPwd
    * @author 王林江
    * @Description: 密码修改处理
    * @param webUserPwdModifyDTO 密码修改DTO
    * @return
    */
    @Override
    public int modifyPwd(WebUserPwdModifyDTO webUserPwdModifyDTO) {
        //用户管理信息PO
        WebUserPO webUserPO = new WebUserPO();
        //更新数据的条数
        int affectNum = 0;
        //登录名
        String username = webUserPwdModifyDTO.getUserNumber();
        //原密码
        String passwordOld = webUserPwdModifyDTO.getPasswordOld();
        //新密码
        String passwordNew = webUserPwdModifyDTO.getPasswordNew();
        //原密码最小长度Check
        if (passwordOld != null && passwordOld.length() < 6) {
            throw new CustomException("原密码设定不正：原密码长度不能小于6位");
        }
        //新密码最小长度Check
        if (passwordNew != null && passwordNew.length() < 6) {
            throw new CustomException("新密码设定不正：新密码长度不能小于6位");
        }

        //原密码与新密码相同
        if (passwordOld != null
                && passwordOld.equals(passwordNew)) {
            throw new CustomException("原密码与新密码相同");
        }

        //判断用户名是否存在
        UserDTO userDTO = getUserByUserNumber(username);
        if (userDTO == null) {
            throw new CustomException("登录名输入错误");
        }

        //校验密码是否正确
        String key = null;
        String dbPassword = userDTO.getPassword();
        //密钥取得
        if (!StringUtils.isEmpty(dbPassword)) {
            key = AesCipherUtil.deCrypto(dbPassword);
        }
        //登录名与密码check
        if (!StringUtils.equals(passwordOld, key)) {
            throw new CustomUnauthorizedException("原密码输入错误");
        }

        //新密码AES復号化
        String keyNew = AesCipherUtil
                .enCrypto(webUserPwdModifyDTO.getPasswordNew());

        //登录名
        webUserPO.setUserNumber(username);
        //密码设定
        webUserPO.setPassword(keyNew);
        //更新人
        webUserPO.setUpdateBy(webUserPwdModifyDTO.getLoginUserId());

        //密码修改处理
        affectNum = userPOMapper.updatePasswordByUserNumber(webUserPO);

        return affectNum;
    }


    public List<String> getAllImgs(){
        List<String> imgs = userPOMapper.getAllImgs();
        return imgs;
    }
}