package com.djb.highway.user.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.djb.highway.common.util.CommonUtil;
import com.djb.highway.common.util.Constants;
import com.djb.highway.common.util.ResourceLocator;
import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.framework.dto.BaseDTO;
import com.djb.highway.road.dtoclient.BaseClientDTO;
import com.djb.highway.user.dto.UserDTO;
import com.djb.highway.user.dtoclient.CreateUserNameDTO;
import com.djb.highway.user.dtoclient.UserClientDTO;
import com.djb.highway.user.dtoclient.VerificationCodeDTO;
import com.djb.highway.user.entity.UserEntity;
import com.djb.highway.user.service.IUserService;

@Controller("/User")
public class UserAction extends BaseAction {

    @Autowired
    @Qualifier("iUserService")
    private IUserService iUserService;

    private String mverificationCode = null;
    private Map mapVerificationCode = null;

    // 自动生成账号时，用户CODE增加的起始值
    static long tempNum = 1;
    // 自动生成账号时，用户CODE的起始值
    static long userCodeTemp = 100000;

    public UserAction() {
        super();
    }

    // // 用于实现我的分享
    // /**
    // *
    // * @param mapping
    // * The ActionMapping used to select this instance
    // * @param form
    // * The optional ActionForm bean for this request
    // * @param request
    // * The servlet request we are processing
    // * @param response
    // * The servlet response we are creating
    // *
    // * @exception Exception
    // * if business logic throws an exception
    // */
    // public ActionForward doGetUserDeployPicList(ActionMapping mapping,
    // ActionForm form, HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // // 参数DTO
    // UserDTO paramDTO = (UserDTO) form;
    // // test
    // // paramDTO.setU_id(1);
    // // 结果DTO
    // UserDTO resultDTO = new UserDTO();
    // // 初始化页面DTO
    //
    // MyShareDTO myShareDTO = new MyShareDTO();
    // // 参数Entity
    // UserEntity paramEntity = new UserEntity();
    // // 参数DTO -> 参数Entity
    // CommonUtil.reflectClass(paramDTO, paramEntity);
    // // 结果Entity
    // UserEntity resultEntity = null;
    // // DB操作
    // try {
    // resultEntity = iUserService.getUserDeployPicObject(paramEntity);
    // if (resultEntity != null) {
    // // resultEntity.setUserDeployPicEntitys(iUserService
    // // .findUserDeployPicPagedList(paramEntity));
    // // 结果Entity -> 结果DTO
    // CommonUtil.reflectClass(resultEntity, resultDTO);
    // if (resultEntity.getUserDeployPicEntitys() != null) {
    // List<UserDeployPicDTO> _list = new ArrayList<UserDeployPicDTO>();
    // for (UserDeployPicEntity _resultEntity : resultEntity
    // .getUserDeployPicEntitys()) {
    // UserDeployPicDTO _resultDTO = new UserDeployPicDTO();
    // CommonUtil.reflectClass(_resultEntity, _resultDTO);
    // _list.add(_resultDTO);
    // }
    // resultDTO.setUserDeployPicDTOs(_list);
    // }
    // } else {
    // resultDTO = new UserDTO();
    // }
    //
    // } catch (Exception e) {
    // myShareDTO.setReturnCode("-660");
    // // resultDTO = new UserDTO();
    // // resultDTO.setErrFlg(true);
    // myShareDTO.setErrFlg(true);
    // // formMessages(myShareDTO,
    // // Constants.MSG_KEY_GET_USERDEPLOYPIC_ERROR,
    // // null);
    // }
    //
    // // 初始化页面DTO,减少数据冗余
    // if (resultDTO.getUserDeployPicDTOs() != null) {
    // List<UserDeployPicClearDTO> userDeployPicClearList = new
    // ArrayList<UserDeployPicClearDTO>();
    // for (UserDeployPicDTO udp : resultDTO.getUserDeployPicDTOs()) {
    // UserDeployPicClearDTO userDeployPicClearDTO = new
    // UserDeployPicClearDTO();
    // CommonUtil.reflectClass(udp, userDeployPicClearDTO);
    // // 处理赞的数量
    // if (udp.getCommend_count() == null) {
    // userDeployPicClearDTO.setZanCount("0");
    // } else {
    // userDeployPicClearDTO.setZanCount(""
    // + udp.getCommend_count());
    // }
    //
    // // 日期格式化处理
    // SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    // SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日");
    //
    // if (udp.getUdp_deploy_time() != null
    // && DateUtil.isToday(udp.getUdp_deploy_time())) {
    // userDeployPicClearDTO.setUdp_deploy_time(sdf.format(udp
    // .getUdp_deploy_time()));
    //
    // } else {
    // userDeployPicClearDTO.setUdp_deploy_time(sdf2.format(udp
    // .getUdp_deploy_time()));
    // }
    //
    // userDeployPicClearList.add(userDeployPicClearDTO);
    // }
    // myShareDTO.setUserDeployPicDTOs(userDeployPicClearList);
    //
    // }
    //
    // // android
    // toJson(response, myShareDTO);
    // // ;
    //
    // return null;
    // }
    //
    // // 用于实现分页功能的我的分享（分页）
    //
    // /**
    // *
    // * @param mapping
    // * The ActionMapping used to select this instance
    // * @param form
    // * The optional ActionForm bean for this request
    // * @param request
    // * The servlet request we are processing
    // * @param response
    // * The servlet response we are creating
    // *
    // * @exception Exception
    // * if business logic throws an exception
    // */
    // public ActionForward doGetUserDeployPicPageList(ActionMapping mapping,
    // ActionForm form, HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // // 参数DTO
    // UserDTO paramDTO = (UserDTO) form;
    // // test
    // paramDTO.setU_id(4);
    // // 结果DTO
    // UserDTO resultDTO = new UserDTO();
    // // 初始化页面DTO
    // MyShareDTO myShareDTO = new MyShareDTO();
    // // 参数Entity
    // UserEntity paramEntity = new UserEntity();
    // // 参数DTO -> 参数Entity
    // CommonUtil.reflectClass(paramDTO, paramEntity);
    // // 结果Entity
    // UserEntity resultEntity = new UserEntity();
    // // DB操作
    // try {
    // // 用于测试分页
    // int startRow = 1;
    // int endRow = 5;
    // paramEntity.setStartRow(startRow);
    // paramEntity.setEndRow(endRow);
    // paramEntity.setStartRow(paramDTO.getStartRow());
    // paramEntity.setEndRow(paramDTO.getEndRow());
    // // 使用具有分页功能的方法：
    // System.out.println("-------startrow:" + paramEntity.getStartRow());
    // System.out.println("-------endrow:" + paramEntity.getEndRow());
    // List<UserDeployPicEntity> userDeployPicEntityList = iUserService
    // .findUserDeployPicPagedList(paramEntity);
    // resultEntity.setUserDeployPicEntitys(userDeployPicEntityList);
    // if (resultEntity != null) {
    // CommonUtil.reflectClass(resultEntity, resultDTO);
    // if (resultEntity.getUserDeployPicEntitys() != null) {
    // List<UserDeployPicDTO> _list = new ArrayList<UserDeployPicDTO>();
    // for (UserDeployPicEntity _resultEntity : resultEntity
    // .getUserDeployPicEntitys()) {
    // UserDeployPicDTO _resultDTO = new UserDeployPicDTO();
    // CommonUtil.reflectClass(_resultEntity, _resultDTO);
    // _list.add(_resultDTO);
    // }
    // resultDTO.setUserDeployPicDTOs(_list);
    // }
    // }
    //
    // } catch (Exception e) {
    // myShareDTO.setReturnCode("-660");
    // myShareDTO.setErrFlg(true);
    // // formMessages(myShareDTO,
    // // Constants.MSG_KEY_GET_USERDEPLOYPIC_ERROR,
    // // null);
    // }
    //
    // // 初始化页面DTO,减少数据冗余
    // if (resultDTO.getUserDeployPicDTOs() != null) {
    // List<UserDeployPicClearDTO> userDeployPicClearList = new
    // ArrayList<UserDeployPicClearDTO>();
    // for (UserDeployPicDTO udp : resultDTO.getUserDeployPicDTOs()) {
    // UserDeployPicClearDTO userDeployPicClearDTO = new
    // UserDeployPicClearDTO();
    // CommonUtil.reflectClass(udp, userDeployPicClearDTO);
    // // 处理赞的数量
    // if (udp.getCommend_count() == null) {
    // userDeployPicClearDTO.setZanCount("0");
    // } else {
    // userDeployPicClearDTO.setZanCount(""
    // + udp.getCommend_count());
    // }
    //
    // // 日期格式化处理
    // SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    // SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日");
    //
    // if (udp.getUdp_deploy_time() != null
    // && DateUtil.isToday(udp.getUdp_deploy_time())) {
    // userDeployPicClearDTO.setUdp_deploy_time(sdf.format(udp
    // .getUdp_deploy_time()));
    //
    // } else {
    // userDeployPicClearDTO.setUdp_deploy_time(sdf2.format(udp
    // .getUdp_deploy_time()));
    // }
    //
    // userDeployPicClearList.add(userDeployPicClearDTO);
    // }
    // myShareDTO.setUserDeployPicDTOs(userDeployPicClearList);
    //
    // }
    // if (!myShareDTO.isErrFlg()) {
    // myShareDTO.setReturnCode("0");
    // }
    //
    // // android
    // toJson(response, myShareDTO);
    // // ;
    //
    // return null;
    // }

    /**
     * 
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request
     * @param request
     *            The servlet request we are processing
     * @param response
     *            The servlet response we are creating
     * 
     * @exception Exception
     *                if business logic throws an exception
     */
    public ActionForward doLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        UserDTO paramDTO = (UserDTO) form;
        UserClientDTO userClientDTO = new UserClientDTO();
        // test
        // UserDTO paramDTO = (UserDTO) new UserDTO();
        // paramDTO.setU_code("999999");
        // paramDTO.setU_password("111111");
        // 结果DTO
        // BaseClientDTO baseDTO = new BaseClientDTO();
        UserDTO resultDTO = new UserDTO();

        // 参数Entity
        UserEntity paramEntity = new UserEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);

        // 结果Entity
        UserEntity resultEntity = null;
        // DB操作
        paramEntity.setU_password(null);
        resultEntity = iUserService.getObject(paramEntity);

        if (resultEntity == null) {// 用户名不正确
            // 参数DTO -> 结果DTO
            CommonUtil.reflectClass(paramDTO, resultDTO);
            userClientDTO.setReturnCode("-100");
            resultDTO.setErrFlg(true);
            // formMessages(resultDTO, Constants.MSG_KEY_LOGIN_USERNAME_ERROR,
            // null);
        } else {
            if (!"".equals(resultEntity.getU_password()) && resultEntity.getU_password().equals(paramDTO.getU_password())) {
                // 结果Entity -> 结果DTO
                // resultDTO.setU_id(resultEntity.getU_id());
                // CommonUtil.reflectClass(resultEntity, resultDTO);
            } else {// 用户名正确，密码错误
                    // 参数DTO -> 结果DTO

                CommonUtil.reflectClass(paramDTO, resultDTO);
                userClientDTO.setReturnCode("-101");
                resultDTO.setErrFlg(true);
                // formMessages(resultDTO,
                // Constants.MSG_KEY_LOGIN_PASSWORD_ERROR,
                // null);
            }
        }
        if (!resultDTO.isErrFlg()) {
            userClientDTO.setReturnCode("0");
            userClientDTO.setU_id(resultEntity.getU_id());
            userClientDTO.setU_code(resultEntity.getU_code());
            userClientDTO.setU_name(resultEntity.getU_name());

        }
        // android
        toJson(response, userClientDTO);
        // ;

        return null;

    }

    /**
     * 
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request
     * @param request
     *            The servlet request we are processing
     * @param response
     *            The servlet response we are creating
     * 
     * @exception Exception
     *                if business logic throws an exception
     */
    public ActionForward doRegisterUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 参数DTO
        UserDTO userDTO = (UserDTO) form;
        // 返回给页面的DTO
        BaseDTO baseDTO = new BaseDTO();
        UserEntity userEntity = new UserEntity();
        CommonUtil.reflectClass(userDTO, userEntity);
        try {
            // 检测用户是否存在
            boolean existUser = checkExistUser(userEntity.getU_code());
            if (!existUser) {
                // 昵称默认为用户Code
                userEntity.setU_name(userEntity.getU_code());
                iUserService.addUser(userEntity);
            } else {
                baseDTO.setReturnCode("-201");
                baseDTO.setErrFlg(true);
                // formMessages(baseDTO,
                // Constants.MSG_KEY_INSERT_USER_EXIST_ERROR, null);
            }
        } catch (Exception e) {
            baseDTO.setReturnCode("-200");
            baseDTO.setErrFlg(true);
            // formMessages(baseDTO, Constants.MSG_KEY_INSERT_USER_ERROR, null);
        }

        // android
        toJson(response, baseDTO);
        // ;

        return null;

    }

    public ActionForward doUserRegister(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 参数DTO
        UserDTO userDTO = (UserDTO) form;
        UserClientDTO userClientDTO = new UserClientDTO();
        // 返回给页面的DTO
        // BaseDTO baseDTO = new BaseDTO();
        UserDTO resultDTO = new UserDTO();
        UserEntity userEntity = new UserEntity();
        UserEntity resultEntity = null;
        // test
        // UserDTO userDTO = (UserDTO) new UserDTO();
        // userDTO.setU_code("9999922");
        // userDTO.setU_password("111111");
        // userDTO.setU_name("333");
        // userDTO.setU_answer("2");
        // userDTO.setU_question(0);
        CommonUtil.reflectClass(userDTO, userEntity);
        try {
            // 检测用户是否存在
            boolean existUser = checkExistUser(userEntity.getU_code());
            if (!existUser) {
                // 昵称默认为用户Code
                userEntity.setU_name(userEntity.getU_code());
                iUserService.addUser(userEntity);
            } else {
                userClientDTO.setReturnCode("-201");
                resultDTO.setErrFlg(true);
                // formMessages(baseDTO,
                // Constants.MSG_KEY_INSERT_USER_EXIST_ERROR, null);
            }
        } catch (Exception e) {
            userClientDTO.setReturnCode("-200");
            resultDTO.setErrFlg(true);
            // formMessages(baseDTO, Constants.MSG_KEY_INSERT_USER_ERROR, null);
        }
        if (!resultDTO.isErrFlg()) {
            userClientDTO.setReturnCode("0");
            resultEntity = iUserService.getObject(userEntity);
            userClientDTO.setU_id(resultEntity.getU_id());
            userClientDTO.setU_code(userEntity.getU_code());
            userClientDTO.setU_name(userEntity.getU_code());
        }
        // android
        toJson(response, userClientDTO);
        // ;

        return null;

    }

    /**
     * 
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request
     * @param request
     *            The servlet request we are processing
     * @param response
     *            The servlet response we are creating
     * 
     * @exception Exception
     *                if business logic throws an exception
     */
    public ActionForward doInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 参数DTO
        UserDTO userDTO = (UserDTO) form;
        // 返回给页面的DTO
        BaseDTO baseDTO = new BaseDTO();

        String verCode = null;
        // test
        // UserDTO userDTO = (UserDTO) new UserDTO();
        // userDTO.setU_code("jack2331");
        // userDTO.setU_password("123456");
        if (userDTO.getVerificationCode() != null) {
            verCode = userDTO.getVerificationCode();
        }
        // verCode= "123456";
        UserEntity userEntity = new UserEntity();
        // BeanUtils.copyProperties(userEntity, userDTO);
        CommonUtil.reflectClass(userDTO, userEntity);
        try {
            // 检测用户是否存在
            boolean existUser = checkExistUser(userEntity.getU_code());
            if (!existUser) {

                if (verCode != null && !("".equals(verCode)) && verCode.equals(mverificationCode)) {

                    // 检测手机号码和验证码是不是一对的键值对
                    String _mverificationCode = (String) mapVerificationCode.get(userEntity.getU_code());
                    if (_mverificationCode != null && mverificationCode.equals(_mverificationCode)) {
                        iUserService.addUser(userEntity);
                        mverificationCode = null;
                        mapVerificationCode = null;
                    } else {
                        // userDTO.setErrFlg(true);
                        baseDTO.setErrFlg(true);
                        formMessages(baseDTO, Constants.MSG_KEY_INPUT_USERCODE_ERROR, null);
                    }

                } else {
                    // userDTO.setErrFlg(true);
                    baseDTO.setErrFlg(true);
                    formMessages(baseDTO, Constants.MSG_KEY_INPUT_VERIFICATIONCODE_ERROR, null);
                }
            } else {
                // return MessageInfo
                // userDTO.setErrFlg(true);
                baseDTO.setErrFlg(true);
                formMessages(baseDTO, Constants.MSG_KEY_INSERT_USER_EXIST_ERROR, null);
            }
        } catch (Exception e) {
            // return MessageInfo
            // userDTO.setErrFlg(true);
            baseDTO.setErrFlg(true);
            formMessages(baseDTO, Constants.MSG_KEY_INSERT_USER_ERROR, null);
        }

        // android
        toJson(response, baseDTO);
        // ;

        return null;

    }

    /**
     * 
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request
     * @param request
     *            The servlet request we are processing
     * @param response
     *            The servlet response we are creating
     * 
     * @exception Exception
     *                if business logic throws an exception
     */
    public ActionForward doUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        UserDTO userDTO = (UserDTO) form;
        BaseDTO baseDTO = new BaseDTO();
        UserEntity userEntity = new UserEntity();

        CommonUtil.reflectClass(userDTO, userEntity);

        try {
            iUserService.updateUser(userEntity);
        } catch (Exception e) {
            baseDTO.setReturnCode("-351");
            baseDTO.setErrFlg(true);
            // formMessages(baseDTO, Constants.MSG_KEY_UPDATE_USER_ERROR, null);
        }
        if (!baseDTO.isErrFlg()) {
            baseDTO.setReturnCode("0");
        }
        return null;

    }

    /**
     * 
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request
     * @param request
     *            The servlet request we are processing
     * @param response
     *            The servlet response we are creating
     * 
     * @exception Exception
     *                if business logic throws an exception
     */
    public ActionForward doUpdateUserName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        UserDTO userDTO = (UserDTO) form;
        // userDTO.setU_code("999999");
        // userDTO.setU_name("111111");
        // 返回操作状态
        BaseDTO baseDTO = new BaseDTO();
        BaseClientDTO baseClientDTO = new BaseClientDTO();
        try {
            doUpdate(mapping, userDTO, request, response);
        } catch (Exception e) {
            // return MessageInfo
            // userDTO.setErrFlg(true);
            baseClientDTO.setReturnCode("-500");
            baseDTO.setErrFlg(true);
            // formMessages(baseDTO, Constants.MSG_KEY_UPDATE_USERNAME_ERROR,
            // null);
        }
        if (!baseDTO.isErrFlg()) {
            baseClientDTO.setReturnCode("0");
        }
        // android
        toJson(response, baseClientDTO);
        // ;
        return null;

    }

    public ActionForward doForgotPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        UserDTO userDTO = (UserDTO) form;
        // 返回操作状态
        BaseDTO baseDTO = new BaseDTO();
        BaseClientDTO baseClientDTO = new BaseClientDTO();
        // test
        // userDTO.setU_code("999999");
        // userDTO.setU_password("123");
        try {
            doUpdate(mapping, userDTO, request, response);
        } catch (Exception e) {
            // return MessageInfo
            // userDTO.setErrFlg(true);
            baseClientDTO.setReturnCode("-304");
            baseDTO.setErrFlg(true);
            // formMessages(baseDTO, Constants.MSG_KEY_RETRIEVE_PWD_ERROR,
            // null);
        }
        // android
        if (!baseDTO.isErrFlg()) {
            baseClientDTO.setReturnCode("0");
        }
        toJson(response, baseClientDTO);
        // ;
        return null;

    }

    /**
     * 
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request
     * @param request
     *            The servlet request we are processing
     * @param response
     *            The servlet response we are creating
     * 
     * @exception Exception
     *                if business logic throws an exception
     */
    public ActionForward doUpdatePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        UserDTO userDTO = (UserDTO) form;
        // 返回操作状态
        BaseDTO baseDTO = new BaseDTO();
        BaseClientDTO baseClientDTO = new BaseClientDTO();
        // test
        // UserDTO userDTO = new UserDTO();
        // userDTO.setU_code("999999");
        // userDTO.setU_old_password("111111");
        // userDTO.setU_password("111111");

        UserEntity userEntity = new UserEntity();
        // BeanUtils.copyProperties(userEntity, userDTO);
        CommonUtil.reflectClass(userDTO, userEntity);

        // 验证旧密码是否正确
        String userCode = userDTO.getU_code();
        String password = userDTO.getU_old_password();
        try {
            if (checkLogin(userCode, password)) {
                // 旧正确正确，修改密码操作
                doUpdate(mapping, userDTO, request, response);
            } else {
                // return MessageInfo
                // userDTO.setErrFlg(true);
                baseClientDTO.setReturnCode("-520");
                baseDTO.setErrFlg(true);
                // formMessages(baseDTO, Constants.MSG_KEY_UPDATE_PWD_ERROR,
                // null);
            }
        } catch (Exception e) {
            // return MessageInfo
            // userDTO.setErrFlg(true);
            baseClientDTO.setReturnCode("-521");
            baseDTO.setErrFlg(true);
            // formMessages(baseDTO, Constants.MSG_KEY_RETRIEVE_PWD_ERROR,
            // null);
        }
        if (!baseDTO.isErrFlg()) {
            baseClientDTO.setReturnCode("0");
        }
        // android
        toJson(response, baseClientDTO);
        // ;
        return null;

    }

    public ActionForward doRetrievePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 参数DTO
        UserDTO userDTO = (UserDTO) form;
        // 返回操作状态
        BaseDTO baseDTO = new BaseDTO();

        // test
        // UserDTO userDTO = new UserDTO();
        // userDTO.setU_code("13591277216");

        // userDTO.setU_old_password("jack1");
        // userDTO.setU_password("123456");
        UserEntity userEntity = new UserEntity();
        // BeanUtils.copyProperties(userEntity, userDTO);
        CommonUtil.reflectClass(userDTO, userEntity);

        // 验证码是否正确

        String verifiCode = userDTO.getVerificationCode();
        // test
        // verifiCode = "12345s6";
        // mverificationCode = "123456";
        try {
            boolean existUser = checkExistUser(userDTO.getU_code());
            if (existUser) {

                // System.out.println("mverificationCode:" + mverificationCode);
                // System.out.println("verifiCode:" + verifiCode);
                if (verifiCode != null && verifiCode.equals(mverificationCode)) {

                    // 检测手机号码和验证码是不是一对的键值对
                    String _mverificationCode = (String) mapVerificationCode.get(userEntity.getU_code());
                    if (_mverificationCode != null && mverificationCode.equals(_mverificationCode)) {
                        // 验证码输入正确，修改密码操作
                        doUpdate(mapping, userDTO, request, response);
                        mverificationCode = null;
                        mapVerificationCode = null;
                    } else {
                        // userDTO.setErrFlg(true);
                        baseDTO.setErrFlg(true);
                        formMessages(baseDTO, Constants.MSG_KEY_INPUT_USERCODE_ERROR, null);
                    }

                } else {
                    // return MessageInfo
                    // userDTO.setErrFlg(true);
                    baseDTO.setErrFlg(true);
                    formMessages(baseDTO, Constants.MSG_KEY_INPUT_VERIFICATIONCODE_ERROR, null);

                }

            } else {
                // return MessageInfo
                // userDTO.setErrFlg(true);
                baseDTO.setErrFlg(true);
                // 提示：用户不存在
                formMessages(baseDTO, Constants.MSG_KEY_USERNAME_ERROR, null);
            }

        } catch (Exception e) {
            // return MessageInfo
            // userDTO.setErrFlg(true);
            baseDTO.setErrFlg(true);
            formMessages(baseDTO, Constants.MSG_KEY_RETRIEVE_PWD_ERROR, null);
        }

        // android
        toJson(response, baseDTO);
        // ;

        return null;

    }

    public ActionForward doFindPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        UserDTO userDTO = (UserDTO) form;

        // test
        // userDTO.setU_code("lucyli123");
        // userDTO.setU_question(3);
        // userDTO.setU_answer("1236");

        // 返回操作状态
        BaseDTO baseDTO = new BaseDTO();
        BaseClientDTO baseClientDTO = new BaseClientDTO();
        UserEntity userEntity = new UserEntity();
        CommonUtil.reflectClass(userDTO, userEntity);
        // 结果Entity
        UserEntity resultEntity = null;
        // DB操作
        resultEntity = iUserService.getObject(userEntity);
        // resultEntity = iUserService.getObject(paramEntity);
        try {
            boolean existUser = checkExistUser(userDTO.getU_code());
            if (existUser) {
                if (!"".equals(resultEntity.getU_question()) && resultEntity.getU_question().equals(userDTO.getU_question())) {
                    if (!"".equals(resultEntity.getU_answer()) && resultEntity.getU_answer().equals(userDTO.getU_answer())) {

                        CommonUtil.reflectClass(resultEntity, baseDTO);

                    } else {
                        CommonUtil.reflectClass(userDTO, baseDTO);
                        baseDTO.setErrFlg(true);
                        baseClientDTO.setReturnCode("-302");
                        // 答案不正确
                        // formMessages(baseDTO,
                        // Constants.MSG_KEY_INPUT_ANSWER_ERROR, null);
                        // formMessages(baseDTO,
                        // Constants.MSG_KEY_INPUT_ANSWER_ERROR,
                        // null);
                    }
                } else {
                    baseClientDTO.setReturnCode("-301");
                    baseDTO.setErrFlg(true);
                    // 问题不正确
                    // formMessages(baseDTO,
                    // Constants.MSG_KEY_INPUT_QUESTION_ERROR,
                    // null);
                }
            } else {
                baseClientDTO.setReturnCode("-300");
                baseDTO.setErrFlg(true);
                // 用户名输入错误
                // formMessages(baseDTO, Constants.MSG_KEY_USERNAME_ERROR,
                // null);
            }
        } catch (Exception e) {
            baseClientDTO.setReturnCode("-303");
            baseDTO.setErrFlg(true);
            // 通信错误：获取数据失败
            // formMessages(baseDTO, Constants.MSG_KEY_INPUT_DATA_ERROR, null);
        }
        if (!baseDTO.isErrFlg()) {
            baseClientDTO.setReturnCode("0");
        }
        // android
        toJson(response, baseClientDTO);
        // ;
        return null;
    }

    public boolean checkLogin(String userCode, String password) {

        UserEntity userEntity = new UserEntity();
        userEntity.setU_code(userCode);
        UserEntity resultEntity = new UserEntity();
        resultEntity = iUserService.getObject(userEntity);

        if (resultEntity != null) {
            String oldPassword = resultEntity.getU_password();
            if (oldPassword.equals(password)) {
                return true;
            } else {

                return false;
            }

        } else {

            return false;
        }

    }

    // /**
    // *
    // * @param mapping
    // * The ActionMapping used to select this instance
    // * @param form
    // * The optional ActionForm bean for this request
    // * @param request
    // * The servlet request we are processing
    // * @param response
    // * The servlet response we are creating
    // *
    // * @exception Exception
    // * if business logic throws an exception
    // */
    // public ActionForward doUpdateBatch(ActionMapping mapping, ActionForm
    // form,
    // HttpServletRequest request, HttpServletResponse response)
    // throws Exception {
    //
    // UserDTO userDTO = (UserDTO) form;
    // List<UserEntity> list = new ArrayList<UserEntity>();
    // UserEntity userEntity = new UserEntity();
    // userEntity.setU_id(1);
    // list.add(userEntity);
    // userEntity = new UserEntity();
    // userEntity.setU_id(2);
    // list.add(userEntity);
    // iUserService.deleteUserBatch(list);
    // // android
    // toJson(response, userDTO);
    // // ;
    // return null;
    //
    // }
    //
    // /**
    // *
    // * @param mapping
    // * The ActionMapping used to select this instance
    // * @param form
    // * The optional ActionForm bean for this request
    // * @param request
    // * The servlet request we are processing
    // * @param response
    // * The servlet response we are creating
    // *
    // * @exception Exception
    // * if business logic throws an exception
    // */
    // public ActionForward editView(ActionMapping mapping, ActionForm form,
    // HttpServletRequest request, HttpServletResponse response)
    // throws Exception {
    // UserDTO userDTO = (UserDTO) form;
    // if (null == userDTO.getU_id() || "".equals(userDTO.getU_id())) {
    // userDTO.setMethodName("doInsert");
    // } else {
    //
    // /*
    // * // 使用BaseDAOImpl操作DB，不能这样用，会出错，看User.xml注释�?select //
    // * id="getObject"定义处）�?UserEntity userEntity = (UserEntity)
    // * iUserService .getUserById(userDTO.getU_id());
    // */
    //
    // // 结果取得DTO
    // UserEntity userEntity = new UserEntity();
    // // BeanUtils.copyProperties(userEntity, userDTO);
    // CommonUtil.reflectClass(userDTO, userEntity);
    // userEntity = (UserEntity) iUserService.getObject(userEntity);
    //
    // // 设置form
    // // BeanUtils.copyProperties(userDTO, userEntity);
    // CommonUtil.reflectClass(userEntity, userDTO);
    //
    // userDTO.setMethodName("doUpdate");
    // }
    // return null;
    //
    // }

    /**
     * 
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request
     * @param request
     *            The servlet request we are processing
     * @param response
     *            The servlet response we are creating
     * 
     * @exception Exception
     *                if business logic throws an exception
     */
    // 用于密码找回时，获取验证码
    public ActionForward doVerificationCodeRetrievePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 参数DTO
        UserDTO userDTO = (UserDTO) form;
        String uCode = userDTO.getU_code();
        // test
        // uCode = "13591277216";

        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setU_code(uCode);
            UserEntity resultEntity = new UserEntity();
            resultEntity = iUserService.getObject(userEntity);
            if (resultEntity != null) {
                generateVerificationCode(uCode);
                // System.out.println("mverificationCode:" + mverificationCode);
            } else {
                userDTO.setErrFlg(true);
                formMessages(userDTO, Constants.MSG_KEY_USERNAME_ERROR, null);
            }

        } catch (Exception e) {

            userDTO.setErrFlg(true);
            formMessages(userDTO, Constants.MSG_KEY_VERIFICATIONCODE_ERROR, null);

        }
        // android
        toJson(response, userDTO);
        // ;
        return null;

    }

    /**
     * 
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request
     * @param request
     *            The servlet request we are processing
     * @param response
     *            The servlet response we are creating
     * 
     * @exception Exception
     *                if business logic throws an exception
     */
    // 用于用户注册时，获取验证码
    public ActionForward doGenerateVerificationCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 参数DTO
        UserDTO userDTO = (UserDTO) form;
        String uCode = userDTO.getU_code();
        // test
        // uCode = "11111111112";
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setU_code(uCode);
            UserEntity resultEntity = new UserEntity();
            resultEntity = iUserService.getObject(userEntity);
            if (resultEntity == null) {
                generateVerificationCode(uCode);
                // System.out.println("mverificationCode:" + mverificationCode);
            } else {
                userDTO.setErrFlg(true);
                formMessages(userDTO, Constants.MSG_KEY_INSERT_USER_EXIST_ERROR, null);
            }

        } catch (Exception e) {

            userDTO.setErrFlg(true);
            formMessages(userDTO, Constants.MSG_KEY_VERIFICATIONCODE_ERROR, null);

        }
        // android
        toJson(response, userDTO);
        // ;
        return null;

    }

    // 生成验证码方法，并发送验证码给手机端

    public void generateVerificationCode(String uCode) {
        String verificationCode = null;

        // 生成验证
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        String temp = null;

        for (int i = 0; i < 6; i++) {
            temp = "" + random.nextInt(10);
            sb.append(temp);
        }

        verificationCode = sb.toString();
        mverificationCode = verificationCode;

        // test:
        mverificationCode = "123456";

        // map:
        mapVerificationCode = new HashMap<String, String>();
        // mapVerificationCode.put(uCode, mverificationCode);

        // 手机发送验证码
        sendVerificationCode(uCode, verificationCode);
    }

    // 手机发送验证码方法
    public void sendVerificationCode(String tel, String verificationCode) {
        // System.out.println("tel:" + tel);
        // System.out.println("verificationCode:" + verificationCode);
    }

    // 检测用户是否存在的方法

    public boolean checkExistUser(String UserCode) {

        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setU_code(UserCode);
            UserEntity resultEntity = iUserService.getObject(userEntity);

            if (resultEntity == null) {

                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return true;
        }

    }

    /**
     * 
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request
     * @param request
     *            The servlet request we are processing
     * @param response
     *            The servlet response we are creating
     * 
     * @exception Exception
     *                if business logic throws an exception
     */
    // 自动生成用户Code，用于用户登录的账号
    public ActionForward generateUserCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 结果DTO
        UserDTO resultDTO = new UserDTO();
        CreateUserNameDTO createUserNameDTO = new CreateUserNameDTO();
        try {

            // 生成用户账号
            String usreCode = randomGenerateCode();
            // 如果用户账号已存在，重新生成用户账号
            UserEntity user = new UserEntity();
            user.setU_code(usreCode);
            while (true) {
                UserEntity userFlag = iUserService.getObject(user);
                if (userFlag == null) {
                    break;
                } else {
                    usreCode = randomGenerateCode();
                    user.setU_code(usreCode);
                }
            }
            createUserNameDTO.setUserCode(usreCode);

            // 打印随机生成的UserCode
            // System.out.println("---userCode---" +
            // createUserNameDTO.getUserCode());

        } catch (Exception e) {
            createUserNameDTO.setReturnCode("-202");
            resultDTO.setErrFlg(true);
            // formMessages(createUserNameDTO,
            // Constants.MSG_KEY_GENERATEUSERCODE_ERROR, null);

        }
        if (!resultDTO.isErrFlg()) {
            createUserNameDTO.setReturnCode("0");
        }
        // android
        toJson(response, createUserNameDTO);
        // ;
        return null;

    }

    public synchronized String randomGenerateCode() {

        // int count = iUserService.countUserNumber();
        // 1000是任意实用的一个基数
        // int userCodeTemp = 1000 + count;
        // 添加随机数,防止同时有多个用户在同一时刻注册
        // Random random = new Random();
        // int randomInt = random.nextInt(90);
        // randomInt = randomInt + 10;
        // 拼凑用户账号
        // String usreCode = "" + userCodeTemp + randomInt;
        // 添加一个临时变量tempNum，变量递增

        // 测试使用：图片处理
        // CompressImage compressImage = new CompressImage();

        // String inputDir = "D:/img_camera";
        // String outputDir = "D:/img_camera1/";
        // compressImage.compressPic(inputDir, outputDir);
        // String resultStr = compressImage.compressPic(inputDir, outputDir,
        // 120,
        // 120, false);
        // System.out.println("----resultStr:" + resultStr);

        // 服务器重启后，生成注册账号的开始账号,通过配置文件获取
        if (userCodeTemp == 100000 && tempNum == 1) {
            // 获取最后一个用户的注册Code
            // 待解决的问题
            String keyName = "userCodeStart";
            long userCode = Long.parseLong(ResourceLocator.getI18NMessage(this, keyName));
            int currUserNum = iUserService.countUserNumber();
            userCodeTemp = userCode + currUserNum;
        }
        String usreCode = "" + (userCodeTemp + tempNum);
        tempNum++;
        return usreCode;
    }

    // 用于用户绑定手机的方法
    public ActionForward doUpdateUserTel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        UserDTO userDTO = (UserDTO) form;
        // 返回操作状态
        BaseDTO baseDTO = new BaseDTO();
        try {
            // if("123456".equals(userDTO.getVerificationCode())){
            // doUpdate(mapping, userDTO, request, response);
            // }
            // 在更新电话号码之前，判断电话号码是否已经被使用，并且使用的账号不是当前的账号，将原来绑定的手机号码解除绑定
            // 待解决的问题
            doUpdate(mapping, userDTO, request, response);
        } catch (Exception e) {
            baseDTO.setErrFlg(true);
            formMessages(baseDTO, Constants.MSG_KEY_UPDATE_USERTEL_ERROR, null);
        }
        // android
        toJson(response, baseDTO);
        // ;
        return null;

    }

    public ActionForward doCreateVerificationCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        UserDTO userDTO = (UserDTO) form;
        UserDTO resultDTO = new UserDTO();
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();

        String verificationCode = null;
        try {
            // 生成验证
            Random random = new Random();
            StringBuffer sb = new StringBuffer();
            String temp = null;

            for (int i = 0; i < 4; i++) {
                temp = "" + random.nextInt(10);
                sb.append(temp);
            }

            verificationCode = sb.toString();

            // VerificationCodeDTO verificationCodeDTO = new
            // VerificationCodeDTO();

            verificationCodeDTO.setVerificationCode(verificationCode);
        } catch (Exception e) {
            verificationCodeDTO.setReturnCode("-203");
            resultDTO.setErrFlg(true);
        }
        if (!resultDTO.isErrFlg()) {
            verificationCodeDTO.setReturnCode("0");
        }
        // android
        toJson(response, verificationCodeDTO);
        // ;
        return null;

    }

}
