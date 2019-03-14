package com.djb.highway.carpool.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import com.djb.highway.road.dtoclient.BaseClientDTO;
import com.djb.highway.user.dto.UserDTO;
import com.djb.highway.user.entity.UserEntity;
import com.djb.highway.carpool.dtoclient.CarpoolUserClientDTO;
import com.djb.highway.carpool.dto.CarpoolUserDTO;
import com.djb.highway.carpool.entity.CarpoolUserEntity;
import com.djb.highway.carpool.service.ICarpoolUserService;
import com.djb.highway.common.util.CommonUtil;
import com.djb.highway.common.util.Constants;
import com.djb.highway.common.util.ResourceLocator;
import com.djb.highway.common.util.UploadUtil;
import com.djb.highway.framework.SessionManager;
import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.framework.dto.BaseDTO;
import com.djb.highway.user.service.IUserService;

@Controller("/CarpoolUser")
public class CarpoolUserAction extends BaseAction {

    @Autowired
    @Qualifier("iCarpoolUserService")
    private ICarpoolUserService iCarpoolUserService;

    @Autowired
    @Qualifier("iUserService")
    private IUserService iUserService;

    private String mverificationCode = null;
    private Map mapVerificationCode = null;

    // 自动生成账号时，用户CODE增加的起始值
    static long tempNum = 1;
    // 自动生成账号时，用户CODE的起始值
    static long userCodeTemp = 100000;

    public CarpoolUserAction() {
        super();
    }

    // 测试入口函数
    public ActionForward test(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        paramDTO.setCu_nick("天空之城");
        paramDTO.setCu_id(280);
        // paramDTO.setCu_tel("18809818888");
        paramDTO.setDp_flag("0");
        paramDTO.setRequestBy("h5");
        return mapping.findForward("loginSuccess");

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
    // initeRole界面转发
    public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return mapping.findForward("loginSuccess");

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
    // 用户登录
    public ActionForward doLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

       
        CarpoolUserDTO carpoolUserDTO = (CarpoolUserDTO) form;

        // 参数Entity
        CarpoolUserEntity paramEntity = new CarpoolUserEntity();
        // 结果Entity
        CarpoolUserEntity resultEntity = null;
        paramEntity.setCu_code(carpoolUserDTO.getCu_code());

        try {
            resultEntity = iCarpoolUserService.getObject(paramEntity);
            ActionErrors errors = new ActionErrors();

            if (resultEntity == null) {// 登录code错误
                carpoolUserDTO.setCu_code(null);
                // check
                boolean blnResult = this.logicCheck(request, errors, carpoolUserDTO);
                if (blnResult) {
                    return (mapping.findForward("registerSuccess"));
                }

            } else {
                if (!"".equals(resultEntity.getCu_password()) && resultEntity.getCu_password().equals(carpoolUserDTO.getCu_password())) {
                    HttpSession session = request.getSession();
                    SessionManager sessionManager = new SessionManager(session);
                    CommonUtil.reflectClass(resultEntity, carpoolUserDTO);
                    sessionManager.setCarpoolUserInfo(carpoolUserDTO);
                    // System.out.println(sessionManager.getCarpoolUserInfo().getCu_id());
                    carpoolUserDTO.setCu_id(resultEntity.getCu_id());
                    return mapping.findForward("loginSuccess");
                    //
                } else {// 登录code正确，密码错误
                    carpoolUserDTO.setCu_password(null);
                    // check
                    boolean blnResult = this.logicCheck(request, errors, carpoolUserDTO);
                    if (blnResult) {
                        return mapping.findForward("registerSuccess");
                    }
                }
            }
            return null;

        } catch (Exception e) {
            carpoolUserDTO = null;
            ActionErrors errors = new ActionErrors();
            String errorMessage = "登录";
            errors.add("errorMessage", new ActionMessage("errors.isFailed", new Object[] { errorMessage }));
            this.saveErrors(request, errors);
            return (mapping.findForward("registerSuccess"));
        }
    }

    // 错误消息验证
    private boolean logicCheck(HttpServletRequest request, ActionErrors errors, CarpoolUserDTO carpoolUser) {

        boolean blnCheckError = false;
        // request.getMethod().equals(arg0)
        if (carpoolUser.getCu_code() == null) {
            errors.add("cu_code", new ActionMessage("errors.isWrong", new Object[] { "账号", carpoolUser.getCu_code() }));
            this.saveErrors(request, errors);
            blnCheckError = true;
        }
        if (carpoolUser.getCu_password() == null) {
            errors.add("cu_password", new ActionMessage("errors.isWrong", new Object[] { "密码", carpoolUser.getCu_password() }));
            this.saveErrors(request, errors);
            blnCheckError = true;
        }
        return blnCheckError;
    }

    // 错误消息验证
    private boolean checkResult(HttpServletRequest request, ActionErrors errors, CarpoolUserDTO carpoolUser) {

        boolean blnCheckError = false;
        // request.getMethod().equals(arg0)
        if (carpoolUser.getCu_code() == null) {
            errors.add("cu_code", new ActionMessage("errors.isWrong", new Object[] { "账号", carpoolUser.getCu_code() }));
            this.saveErrors(request, errors);
            blnCheckError = true;
        }

        if (carpoolUser.getCu_question() == null) {
            errors.add("question", new ActionMessage("errors.isWrong", new Object[] { "问题", carpoolUser.getCu_question() }));
            this.saveErrors(request, errors);
            blnCheckError = true;
        }
        if (carpoolUser.getCu_answer() == null) {
            errors.add("answer", new ActionMessage("errors.isWrong", new Object[] { "答案", carpoolUser.getCu_answer() }));
            this.saveErrors(request, errors);
            blnCheckError = true;
        }
        return blnCheckError;
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
    // 初始化用户角色，如果是新用户，先完善个人信息，再初始化用户角色
    public ActionForward doInitRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        CarpoolUserEntity paramEntity = new CarpoolUserEntity();
        paramEntity.setCu_id(paramDTO.getCu_id());
        // 参数cu_id为空时，从session中获取cu_id
        if (null == paramEntity.getCu_id() || 0 == paramEntity.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            CarpoolUserDTO carpoolUserInfo = sessionManager.getCarpoolUserInfo();
            if (null == carpoolUserInfo) {
                return mapping.findForward("registerSuccess");
            } else {
                paramEntity.setCu_id(carpoolUserInfo.getCu_id());
            }
        }
        try {
            CarpoolUserEntity resultEntity = null;
            resultEntity = iCarpoolUserService.getObject(paramEntity);
            if (null != resultEntity && null != paramDTO.getDp_flag()) {
                if (null == resultEntity.getCu_tel() || "".equals(resultEntity.getCu_tel())) {
                    return mapping.findForward("addCarpoolUser");
                } else if (null != paramDTO.getDp_flag() && "0".equals(paramDTO.getDp_flag())) {// 司机
                    return mapping.findForward("initDriverSuccess");
                } else if (null != paramDTO.getDp_flag() && "1".equals(paramDTO.getDp_flag())) {// 乘客
                    return mapping.findForward("initPassengerSuccess");
                } else {
                	ActionErrors errors = new ActionErrors();
                    String errorMessage = "系统";
                    errors.add("errorMessage", new ActionMessage("errors.isFailed", new Object[] { errorMessage }));
                    this.saveErrors(request, errors);
                    return mapping.findForward("loginSuccess");
                }
            } else {// 首次使用高速搭车的用户
                return mapping.findForward("addCarpoolUser");

            }
        } catch (Exception e) {
            ActionErrors errors = new ActionErrors();
            String errorMessage = "系统";
            errors.add("errorMessage", new ActionMessage("errors.isFailed", new Object[] { errorMessage }));
            this.saveErrors(request, errors);
            return mapping.findForward("loginSuccess");
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

    // 添加用户
    public ActionForward addCarpoolUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        CarpoolUserEntity paramEntity = new CarpoolUserEntity();
        paramEntity.setCu_id(paramDTO.getCu_id());
        // 参数cu_id为空时，从session中获取cu_id
        if (null == paramEntity.getCu_id() || 0 == paramEntity.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            CarpoolUserDTO carpoolUserInfo = sessionManager.getCarpoolUserInfo();
            if (null == carpoolUserInfo) {
                return mapping.findForward("registerSuccess");
            } else {
                paramEntity.setCu_id(carpoolUserInfo.getCu_id());
            }
        }

        CarpoolUserClientDTO resultDTO = new CarpoolUserClientDTO();
        try {

            if ("app".equals(paramDTO.getRequestBy())) {
                paramEntity.setCu_nick(paramDTO.getCu_nick());
                UserEntity userEntity = new UserEntity();
                UserEntity resultUserEntity = null;
                userEntity.setU_id(paramDTO.getCu_id());
                resultUserEntity = iUserService.getObject(userEntity);
                if (null != resultUserEntity) {// 用户存在
                    CarpoolUserEntity paramCUEntity = new CarpoolUserEntity();
                    paramCUEntity.setCu_id(paramDTO.getCu_id());
                    CarpoolUserEntity resutltEntity = null;
                    resutltEntity = iCarpoolUserService.getObject(paramCUEntity);
                    // 用户注册顺风车功能，但电话号为空
                    if (null != resutltEntity) {
                        if (null == resutltEntity.getCu_tel() || "".equals(resutltEntity.getCu_tel())) {
                            resutltEntity.setCu_tel(paramDTO.getCu_tel());
                            iCarpoolUserService.updateCarpoolUser(resutltEntity);
                        }
                    } else {// 用户未注册顺风车功能
                        if (null == paramDTO.getCu_nick() || "".equals(paramDTO.getCu_nick())) {// 若昵称为空，取用户名称为昵称
                            paramEntity.setCu_nick(resultUserEntity.getU_name());
                        }
                        paramEntity.setCu_tel(paramDTO.getCu_tel());
                        paramEntity.setInsert_time(new Date());
                        iCarpoolUserService.addCarpoolUser(paramEntity);
                    }
                } else {// 用户id不存在

                }
            } else {// heml5
                paramEntity.setCu_nick(paramDTO.getCu_nick());
                CarpoolUserEntity paramCUEntity = new CarpoolUserEntity();
                paramCUEntity.setCu_id(paramDTO.getCu_id());
                CarpoolUserEntity resutltEntity = null;
                resutltEntity = iCarpoolUserService.getObject(paramCUEntity);
                // 用户注册顺风车功能，但电话号为空
                if (null != resutltEntity) {
                    if (null == resutltEntity.getCu_tel() || "".equals(resutltEntity.getCu_tel())) {
                        resutltEntity.setCu_tel(paramDTO.getCu_tel());
                        iCarpoolUserService.updateCarpoolUser(resutltEntity);
                    }
                } else {// 用户未注册顺风车功能
                    paramEntity.setCu_tel(paramDTO.getCu_tel());
                    paramEntity.setInsert_time(new Date());
                    iCarpoolUserService.addCarpoolUser(paramEntity);
                }
            }

        } catch (Exception e) {
            doErrorMessage(mapping, response, request, resultDTO, paramDTO, paramDTO.getRequestBy(), "系统", "-6011");
            if ("h5".equals(paramDTO.getRequestBy())) {
                if (null != paramDTO.getDp_flag() && "0".equals(paramDTO.getDp_flag())) {// 司机用户添加失败
                    return mapping.findForward("loginSuccess");
                } else {// 乘客用户添加失败
                    return mapping.findForward("loginSuccess");
                }
            }
        }
        if ("app".equals(paramDTO.getRequestBy())) {
            // app操作
            if (!paramDTO.isErrFlg()) {
                resultDTO.setReturnCode("0");
            }
            toJson(response, resultDTO);
        } else if ("h5".equals(paramDTO.getRequestBy())) {
            // html5操作
            if (null != paramDTO.getDp_flag() && "0".equals(paramDTO.getDp_flag())) {// 司机注册成功
                // return mapping.findForward("driverUpdSuccess");
                return mapping.findForward("initDriverSuccess");
            } else {// 乘客注册成功
                return mapping.findForward("initPassengerSuccess");
            }
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
    // 切换用户角色
    public ActionForward doChangeRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        CarpoolUserEntity paramEntity = new CarpoolUserEntity();
        paramEntity.setCu_id(paramDTO.getCu_id());
        // 参数cu_id为空时，从session中获取cu_id
        if (null == paramEntity.getCu_id() || 0 == paramEntity.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            CarpoolUserDTO carpoolUserInfo = sessionManager.getCarpoolUserInfo();
            if (null == carpoolUserInfo) {
                return mapping.findForward("registerSuccess");
            } else {
                paramEntity.setCu_id(carpoolUserInfo.getCu_id());
            }
        }

        if (null != paramDTO.getDp_flag()) {
            if ("1".equals(paramDTO.getDp_flag())) {// 乘客切换为司机
                paramDTO.setDp_flag("0");
                return mapping.findForward("initDriverSuccess");
            } else if ("0".equals(paramDTO.getDp_flag())) {// 司机切换为乘客
                paramDTO.setDp_flag("1");
                return mapping.findForward("initPassengerSuccess");
            } else {
                return mapping.findForward("loginSuccess");
            }
        } else {
            return mapping.findForward("loginSuccess");
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
    // 修改乘客用户界面跳转
    public ActionForward doModifyPassenger(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return mapping.findForward("gotoModifyPassenger");
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
    // 修改司机用户界面跳转
    public ActionForward doModifyDriver(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return mapping.findForward("gotoModifyDriver");
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

    // 修改用户
    public ActionForward modifyCarpoolUserDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        CarpoolUserEntity paramEntity = new CarpoolUserEntity();
        paramEntity.setCu_id(paramDTO.getCu_id());
        // 参数cu_id为空时，从session中获取cu_id
        if (null == paramEntity.getCu_id() || 0 == paramEntity.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            CarpoolUserDTO carpoolUserInfo = sessionManager.getCarpoolUserInfo();
            if (null == carpoolUserInfo) {
                return mapping.findForward("registerSuccess");
            } else {
                paramEntity.setCu_id(carpoolUserInfo.getCu_id());
            }
        }
        CarpoolUserClientDTO resultDTO = new CarpoolUserClientDTO();
        // 参数Entity
        try {
            // CommonUtil.reflectClass(paramDTO, paramEntity);
            paramEntity.setCu_nick(paramDTO.getCu_nick());
            paramEntity.setCu_tel(paramDTO.getCu_tel());
            paramEntity.setUpdate_time(new Date());
            iCarpoolUserService.updateCarpoolUser(paramEntity);

        } catch (Exception e) {
            doErrorMessage(mapping, response, request, resultDTO, paramDTO, paramDTO.getRequestBy(), "修改用户信息", "-6012");
            if ("h5".equals(paramDTO.getRequestBy())) {
                if (null != paramDTO.getDp_flag() && "0".equals(paramDTO.getDp_flag())) {// 司机修改失败
                    return mapping.findForward("getDriverSuccess");
                } else {// 乘客修改失败
                    return mapping.findForward("getPassengerSuccess");
                }
            }
        }
        if ("app".equals(paramDTO.getRequestBy())) {
            // app操作
            if (!paramDTO.isErrFlg()) {
                resultDTO.setReturnCode("0");
            }
            toJson(response, resultDTO);
        } else if ("h5".equals(paramDTO.getRequestBy())) {
            // html5操作
            if (null != paramDTO.getDp_flag() && "0".equals(paramDTO.getDp_flag())) {// 司机修改成功
                // return mapping.findForward("driverUpdSuccess");
                return mapping.findForward("getDriverSuccess");
            } else {// 乘客修改成功
                return mapping.findForward("getPassengerSuccess");
            }
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
    // 获取用户详细信息
    public ActionForward getCarpoolUserDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        CarpoolUserEntity paramEntity = new CarpoolUserEntity();
        paramEntity.setCu_id(paramDTO.getCu_id());
        CarpoolUserEntity resultEntity = null;
        // 参数cu_id为空时，从session中获取cu_id
        if (null == paramEntity.getCu_id() || 0 == paramEntity.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            CarpoolUserDTO carpoolUserInfo = sessionManager.getCarpoolUserInfo();
            if (null == carpoolUserInfo) {
                return mapping.findForward("registerSuccess");
            } else {
                paramEntity.setCu_id(carpoolUserInfo.getCu_id());
            }
        }
        CarpoolUserClientDTO resultDTO = new CarpoolUserClientDTO();
        try {
            resultEntity = iCarpoolUserService.getObject(paramEntity);
            if ("app".equals(paramDTO.getRequestBy())) {
                if (resultEntity != null) {
                    resultDTO.setNickname(resultEntity.getCu_nick());
                    resultDTO.setTele(resultEntity.getCu_tel());
                    // 获取用户账号
                    // paramDTO.setCu_code(resultEntity.getCu_code());

                } else {// 未注册顺风车功能
                    resultDTO.setReturnCode("-6014");
                    paramDTO.setErrFlg(true);
                }
            } else if ("h5".equals(paramDTO.getRequestBy())) {
                if (resultEntity != null) {
                    paramDTO.setCu_nick(resultEntity.getCu_nick());
                    paramDTO.setCu_tel(resultEntity.getCu_tel());
                    paramDTO.setCu_code(resultEntity.getCu_code());

                    if (null != paramDTO.getDp_flag() && "0".equals(paramDTO.getDp_flag())) {// 获取司机信息
                        return mapping.findForward("getDriverSuccess");
                    } else {
                        return mapping.findForward("getPassengerSuccess");
                    }
                }
            }

        } catch (Exception e) {
            doErrorMessage(mapping, response, request, resultDTO, paramDTO, paramDTO.getRequestBy(), "获取用户信息", "-6013");
            if ("h5".equals(paramDTO.getRequestBy())) {
                if (null != paramDTO.getDp_flag() && "0".equals(paramDTO.getDp_flag())) {// 司机
                    return mapping.findForward("getDriverSuccess");
                } else {// 乘客
                    return mapping.findForward("getPassengerSuccess");
                }
            }
        }
        if ("app".equals(paramDTO.getRequestBy())) {
            // app操作
            if (!paramDTO.isErrFlg()) {
                resultDTO.setReturnCode("0");
            }
            toJson(response, resultDTO);
        }/* else if ("h5".equals(paramDTO.getRequestBy())) {
            // html5操作
            if (null != paramDTO.getDp_flag() && "0".equals(paramDTO.getDp_flag())) {// 司机修改成功
                // return mapping.findForward("driverUpdSuccess");
                return mapping.findForward("loginSuccess");
            } else {// 乘客修改成功
                return mapping.findForward("getPassengerFailed");
            }
        }*/
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
    // 用户实名认证，页面跳转
    public ActionForward doVerifyPassenger(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        CarpoolUserEntity paramEntity = new CarpoolUserEntity();
        paramEntity.setCu_id(paramDTO.getCu_id());
        // 参数cu_id为空时，从session中获取cu_id
        if (null == paramEntity.getCu_id() || 0 == paramEntity.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            CarpoolUserDTO carpoolUserInfo = sessionManager.getCarpoolUserInfo();
            if (null == carpoolUserInfo) {
                return mapping.findForward("registerSuccess");
            } else {
                paramEntity.setCu_id(carpoolUserInfo.getCu_id());
            }
        }
        //乘客认证或司机认证已经成功时，跳转到乘客认证成功界面
        CarpoolUserEntity resultEntity = null;
        resultEntity = iCarpoolUserService.getObject(paramEntity);
        
        if(null != resultEntity && null != resultEntity.getCu_name() && !("".equals(resultEntity.getCu_name())) && null != resultEntity.getId_number() && !("".equals(resultEntity.getId_number()))){
        	return mapping.findForward("verifyPassengerSuccess");
        }
        return mapping.findForward("verifyPassenger");
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

    public ActionForward verifyPassenger(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        CarpoolUserEntity paramEntity = new CarpoolUserEntity();
        paramEntity.setCu_id(paramDTO.getCu_id());
        // 参数cu_id为空时，从session中获取cu_id
        if (null == paramEntity.getCu_id() || 0 == paramEntity.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            CarpoolUserDTO carpoolUserInfo = sessionManager.getCarpoolUserInfo();
            if (null == carpoolUserInfo) {
                return mapping.findForward("registerSuccess");
            } else {
                paramEntity.setCu_id(carpoolUserInfo.getCu_id());
            }
        }

        CarpoolUserClientDTO resultDTO = new CarpoolUserClientDTO();
        try {
            // if (paramDTO.getCu_name() != null
            // && !("".equals(paramDTO.getCu_name()))
            // && paramDTO.getId_number() != null
            // && !("".equals(paramDTO.getId_number()))) {
            // CommonUtil.reflectClass(paramDTO, paramEntity);
            paramEntity.setCu_name(paramDTO.getCu_name());
            paramEntity.setId_number(paramDTO.getId_number());
            paramEntity.setStatus_flg("1");// 默认认证成功
            // } else {// 个人信息填写不完整
            // resultDTO.setReturnCode("-6026");
            // paramDTO.setErrFlg(true);
            // }
            iCarpoolUserService.updateCarpoolUser(paramEntity);
        } catch (Exception e) {
            doErrorMessage(mapping, response, request, resultDTO, paramDTO, paramDTO.getRequestBy(), "乘客认证", "-6015");
            if ("h5".equals(paramDTO.getRequestBy())) {
                return mapping.findForward("verifyPassenger");
            }
        }
        if ("app".equals(paramDTO.getRequestBy())) {
            // app操作
            if (!paramDTO.isErrFlg()) {
                resultDTO.setReturnCode("0");
            }
            toJson(response, resultDTO);
        } else if ("h5".equals(paramDTO.getRequestBy())) {
            // html5操作
            return mapping.findForward("verifyPassengerSuccess");
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
    // 司机实名认证，页面跳转
    public ActionForward doVerifyDriver(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        CarpoolUserEntity paramEntity = new CarpoolUserEntity();
        paramEntity.setCu_id(paramDTO.getCu_id());
        // 参数cu_id为空时，从session中获取cu_id
        if (null == paramEntity.getCu_id() || 0 == paramEntity.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            CarpoolUserDTO carpoolUserInfo = sessionManager.getCarpoolUserInfo();
            if (null == carpoolUserInfo) {
                return mapping.findForward("registerSuccess");
            } else {
                paramEntity.setCu_id(carpoolUserInfo.getCu_id());
            }
        }
        //司机认证已经成功时，跳转到司机认证成功界面
        CarpoolUserEntity resultEntity = null;
        resultEntity = iCarpoolUserService.getObject(paramEntity);
        
        if(null != resultEntity && null != resultEntity.getCu_name() && !("".equals(resultEntity.getCu_name())) 
        		&& null != resultEntity.getId_number() && !("".equals(resultEntity.getId_number())) 
        		&& null != resultEntity.getCar_num() &&!("".equals(resultEntity.getCar_num()))){
        	CommonUtil.reflectClass(resultEntity, paramDTO);
        	//核载人数=实际可搭乘乘客数量+1
        	paramDTO.setCar_seat_num(resultEntity.getCar_seat_num()+1);
        	return mapping.findForward("verifyDriverSuccess");
        }
        CommonUtil.reflectClass(resultEntity, paramDTO);
        return mapping.findForward("verifyDriver");
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
    //上传图片的verifyDriver函数
    /*public ActionForward verifyDriver00(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        CarpoolUserEntity paramEntity = new CarpoolUserEntity();
        paramEntity.setCu_id(paramDTO.getCu_id());
        // 参数cu_id为空时，从session中获取cu_id
        if (null == paramEntity.getCu_id() || 0 == paramEntity.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            CarpoolUserDTO carpoolUserInfo = sessionManager.getCarpoolUserInfo();
            if (null == carpoolUserInfo) {
                return mapping.findForward("registerSuccess");
            } else {
                paramEntity.setCu_id(carpoolUserInfo.getCu_id());
            }
        }

        CarpoolUserClientDTO resultDTO = new CarpoolUserClientDTO();
        try {
            CommonUtil.reflectClass(paramDTO, paramEntity);
            //paramEntity.setStatus_flg("1");// 默认认证成功

            if (paramDTO.getDriving_pic() != null && paramDTO.getDriving_pic().getFileSize() > 0 && paramDTO.getCar_pic() != null
                            && paramDTO.getCar_pic().getFileSize() > 0 && paramDTO.getPeople_license_pic() != null
                            && paramDTO.getPeople_license_pic().getFileSize() > 0) {

                String drivingFiledPath1 = UploadUtil.doUpload(request, response, paramDTO.getDriving_pic(), paramEntity.getCu_id());
                String drivingFiledPath = UploadUtil.getSmallFileName(drivingFiledPath1);
                paramEntity.setDriving_license_pic(ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS) + drivingFiledPath);

                String carFilePath1 = UploadUtil.doUpload(request, response, paramDTO.getCar_pic(), paramEntity.getCu_id());
                String carFilePath = UploadUtil.getSmallFileName(carFilePath1);
                paramEntity.setCar_license_pic(ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS) + carFilePath);

                String pepoleLicensefilePath1 = UploadUtil.doUpload(request, response, paramDTO.getPeople_license_pic(), paramEntity.getCu_id());
                String pepoleLicensefilePath = UploadUtil.getSmallFileName(pepoleLicensefilePath1);
                paramEntity.setPeople_license(ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS) + pepoleLicensefilePath);
                paramEntity.setStatus_flg("1");// 默认认证成功
            }

            iCarpoolUserService.updateCarpoolUser(paramEntity);
        } catch (Exception e) {
            doErrorMessage(mapping, response, request, resultDTO, paramDTO, paramDTO.getRequestBy(), "司机认证", "-6016");
            if ("h5".equals(paramDTO.getRequestBy())) {
                return mapping.findForward("verifyDriverSecondStep");
            }
        }
        if ("app".equals(paramDTO.getRequestBy())) {
            // app操作
            if (!paramDTO.isErrFlg()) {
                resultDTO.setReturnCode("0");
            }
            toJson(response, resultDTO);
        } else if ("h5".equals(paramDTO.getRequestBy())) {
            // html5操作
            return mapping.findForward("verifyDriverSuccess");
        }
        return null;
    }*/
    
    //不上传图片的verifyDriver函数
    public ActionForward verifyDriver(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        CarpoolUserEntity paramEntity = new CarpoolUserEntity();
        paramEntity.setCu_id(paramDTO.getCu_id());
        // 参数cu_id为空时，从session中获取cu_id
        if (null == paramEntity.getCu_id() || 0 == paramEntity.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            CarpoolUserDTO carpoolUserInfo = sessionManager.getCarpoolUserInfo();
            if (null == carpoolUserInfo) {
                return mapping.findForward("registerSuccess");
            } else {
                paramEntity.setCu_id(carpoolUserInfo.getCu_id());
            }
        }
        
      //司机认证已经成功时，跳转到司机认证成功界面
        CarpoolUserEntity resultEntity = null;
        resultEntity = iCarpoolUserService.getObject(paramEntity);
        
        if(null != resultEntity && null != resultEntity.getCu_name() && !("".equals(resultEntity.getCu_name())) 
                && null != resultEntity.getId_number() && !("".equals(resultEntity.getId_number())) 
                && null != resultEntity.getCar_num() &&!("".equals(resultEntity.getCar_num()))){
            CommonUtil.reflectClass(resultEntity, paramDTO);
          //核载人数=实际可搭乘乘客数量+1
            paramDTO.setCar_seat_num(resultEntity.getCar_seat_num()+1);
            return mapping.findForward("verifyDriverSuccess");
        }

        CarpoolUserClientDTO resultDTO = new CarpoolUserClientDTO();
        try {
            CommonUtil.reflectClass(paramDTO, paramEntity);
            //实际可搭乘乘客数量
            if(paramDTO.getCar_seat_num() > 0){
                paramEntity.setCar_seat_num(paramDTO.getCar_seat_num()-1);
            }else{
                paramEntity.setCar_seat_num(0);
            }
            
            //上传图片处理
            /*if (paramDTO.getDriving_pic() != null && paramDTO.getDriving_pic().getFileSize() > 0 && paramDTO.getCar_pic() != null
                            && paramDTO.getCar_pic().getFileSize() > 0 && paramDTO.getPeople_license_pic() != null
                            && paramDTO.getPeople_license_pic().getFileSize() > 0) {

                String drivingFiledPath1 = UploadUtil.doUpload(request, response, paramDTO.getDriving_pic(), paramEntity.getCu_id());
                String drivingFiledPath = UploadUtil.getSmallFileName(drivingFiledPath1);
                paramEntity.setDriving_license_pic(ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS) + drivingFiledPath);

                String carFilePath1 = UploadUtil.doUpload(request, response, paramDTO.getCar_pic(), paramEntity.getCu_id());
                String carFilePath = UploadUtil.getSmallFileName(carFilePath1);
                paramEntity.setCar_license_pic(ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS) + carFilePath);

                String pepoleLicensefilePath1 = UploadUtil.doUpload(request, response, paramDTO.getPeople_license_pic(), paramEntity.getCu_id());
                String pepoleLicensefilePath = UploadUtil.getSmallFileName(pepoleLicensefilePath1);
                paramEntity.setPeople_license(ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS) + pepoleLicensefilePath);
                paramEntity.setStatus_flg("1");// 默认认证成功
            }*/
            
            paramEntity.setStatus_flg("1");// 默认认证成功

            iCarpoolUserService.updateCarpoolUser(paramEntity);
        } catch (Exception e) {
            doErrorMessage(mapping, response, request, resultDTO, paramDTO, paramDTO.getRequestBy(), "司机认证", "-6016");
            if ("h5".equals(paramDTO.getRequestBy())) {
                //return mapping.findForward("verifyDriverSecondStep");
                return mapping.findForward("verifyDriverSecondFirst");
            }
        }
        if ("app".equals(paramDTO.getRequestBy())) {
            // app操作
            if (!paramDTO.isErrFlg()) {
                resultDTO.setReturnCode("0");
            }
            toJson(response, resultDTO);
        } else if ("h5".equals(paramDTO.getRequestBy())) {
            // html5操作
            return mapping.findForward("verifyDriverSuccess");
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
    // 司机实名认证，页面跳转
    public ActionForward verifyDriverSecondStep(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        paramDTO.setCu_id(paramDTO.getCu_id());
        // 参数cu_id为空时，从session中获取cu_id
        if (null == paramDTO.getCu_id() || 0 == paramDTO.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            CarpoolUserDTO carpoolUserInfo = sessionManager.getCarpoolUserInfo();
            if (null == carpoolUserInfo) {
                return mapping.findForward("registerSuccess");
            } else {
            	paramDTO.setCu_id(carpoolUserInfo.getCu_id());
            }
        }

        return mapping.findForward("verifyDriverSecondStep");
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
    public ActionForward getVerifyPassengerDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        CarpoolUserEntity paramEntity = new CarpoolUserEntity();
        paramEntity.setCu_id(paramDTO.getCu_id());
        // 参数cu_id为空时，从session中获取cu_id
        if (null == paramEntity.getCu_id() || 0 == paramEntity.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            CarpoolUserDTO carpoolUserInfo = sessionManager.getCarpoolUserInfo();
            if (null == carpoolUserInfo) {
                return mapping.findForward("registerSuccess");
            } else {
                paramEntity.setCu_id(carpoolUserInfo.getCu_id());
            }
        }
        CarpoolUserClientDTO resultDTO = new CarpoolUserClientDTO();
        try {
            CarpoolUserEntity resultEntity = null;
            resultEntity = iCarpoolUserService.getObject(paramEntity);
            if ("app".equals(paramDTO.getRequestBy())) {
                // app操作
                if (resultEntity != null) {
                    resultDTO.setName(resultEntity.getCu_name());
                    resultDTO.setIdentity(resultEntity.getId_number());
                    // resultDTO.setIdentity("");
                    resultDTO.setVerify(Integer.parseInt(resultEntity.getStatus_flg()));
                } else {// 未注册顺风车功能
                    resultDTO.setReturnCode("-6024");
                    paramDTO.setErrFlg(true);
                }
            } else if ("h5".equals(paramDTO.getRequestBy())) {
                // html5操作
                if (resultEntity != null) {
                    paramDTO.setCu_name(resultEntity.getCu_name());
                    paramDTO.setId_number(resultEntity.getId_number());
                    paramDTO.setStatus_flg(resultEntity.getStatus_flg());
                    return mapping.findForward("getVerifyPassengerSuccess");
                }
            }
        } catch (Exception e) {
            doErrorMessage(mapping, response, request, resultDTO, paramDTO, paramDTO.getRequestBy(), "获取乘客认证信息", "-6017");
            if ("h5".equals(paramDTO.getRequestBy())) {
            	return mapping.findForward("getPassengerSuccess");
            }
        }
        if ("app".equals(paramDTO.getRequestBy())) {
            // app操作
            if (!paramDTO.isErrFlg()) {
                resultDTO.setReturnCode("0");
            }
            toJson(response, resultDTO);
        } else if ("h5".equals(paramDTO.getRequestBy())) {
            // html5操作
        	ActionErrors errors = new ActionErrors();
            String errorMessage = "获取乘客认证信息";
            errors.add("errorMessage", new ActionMessage("errors.isFailed", new Object[] { errorMessage }));
            this.saveErrors(request, errors);
            return mapping.findForward("getPassengerSuccess");
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
    public ActionForward getVerifyDriverDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        CarpoolUserEntity paramEntity = new CarpoolUserEntity();
        paramEntity.setCu_id(paramDTO.getCu_id());
        // 参数cu_id为空时，从session中获取cu_id
        if (null == paramEntity.getCu_id() || 0 == paramEntity.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            CarpoolUserDTO carpoolUserInfo = sessionManager.getCarpoolUserInfo();
            if (null == carpoolUserInfo) {
                return mapping.findForward("registerSuccess");
            } else {
                paramEntity.setCu_id(carpoolUserInfo.getCu_id());
            }
        }
        CarpoolUserClientDTO resultDTO = new CarpoolUserClientDTO();
        try {
            CarpoolUserEntity resultEntity = null;
            resultEntity = iCarpoolUserService.getObject(paramEntity);
            if ("app".equals(paramDTO.getRequestBy())) {
                // app操作
                if (resultEntity != null) {
                    resultDTO.setName(resultEntity.getCu_name());
                    resultDTO.setIdentity(resultEntity.getId_number());
                    resultDTO.setCarnum(resultEntity.getCar_num());
                    resultDTO.setCarbrand(resultEntity.getCar_brand());
                    resultDTO.setCarcolor(resultEntity.getCar_color());
                    resultDTO.setCartype(resultEntity.getCar_type());
                    resultDTO.setCarseatnum(resultEntity.getCar_seat_num()+1);
                    resultDTO.setDrivinglicense(resultEntity.getDriving_license_pic());
                    resultDTO.setCarlicense(resultEntity.getCar_license_pic());
                    resultDTO.setPeople_license(resultEntity.getPeople_license());
                    resultDTO.setVerify(Integer.parseInt(resultEntity.getStatus_flg()));
                } else {// 未注册顺风车功能
                    resultDTO.setReturnCode("-6025");
                    paramDTO.setErrFlg(true);
                }
            } else if ("h5".equals(paramDTO.getRequestBy())) {
                // html5操作
                if (resultEntity != null) {
                    CommonUtil.reflectClass(resultEntity, paramDTO);
                  //核载人数=实际可搭乘乘客数量+1
                    paramDTO.setCar_seat_num(resultEntity.getCar_seat_num()+1);
                    return mapping.findForward("getVerifyDriverSuccess");
                }
            }
        } catch (Exception e) {
            doErrorMessage(mapping, response, request, resultDTO, paramDTO, paramDTO.getRequestBy(), "获取司机认证信息", "-6018");
            if ("h5".equals(paramDTO.getRequestBy())) {
            	return mapping.findForward("getDriverSuccess");
            }
        }
        if ("app".equals(paramDTO.getRequestBy())) {
            // app操作
            if (!paramDTO.isErrFlg()) {
                resultDTO.setReturnCode("0");
            }
            toJson(response, resultDTO);
        } else if ("h5".equals(paramDTO.getRequestBy())) {
            // html5操作
        	ActionErrors errors = new ActionErrors();
            String errorMessage = "获取司机认证信息";
            errors.add("errorMessage", new ActionMessage("errors.isFailed", new Object[] { errorMessage }));
            this.saveErrors(request, errors);
        	return mapping.findForward("getDriverSuccess");
        }
        return null;
    }

    // check用户是否存在
    public boolean checkExistUser(Integer CarpoolUserId) {

        try {
            CarpoolUserEntity carpoolUserEntity = new CarpoolUserEntity();
            carpoolUserEntity.setCu_id(CarpoolUserId);
            CarpoolUserEntity resultEntity = iCarpoolUserService.getObject(carpoolUserEntity);
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
     * @param response
     * @param dto
     * @param forwardName
     * @param requestBy
     * @return
     * @throws Exception
     */
    public void doErrorMessage(ActionMapping mapping, HttpServletResponse response, HttpServletRequest request, BaseClientDTO clientDTO,
                    CarpoolUserDTO carpoolUserDTO, String requestBy, String errorMessage, String returnCode) throws Exception {
        if ("app".equals(requestBy)) {
            clientDTO.setReturnCode(returnCode);
            carpoolUserDTO.setErrFlg(true);
        } else if ("h5".equals(requestBy)) {
            ActionErrors errors = new ActionErrors();
            // String errorMessage = "加入路线";
            errors.add("errorMessage", new ActionMessage("errors.isFailed", new Object[] { errorMessage }));
            this.saveErrors(request, errors);
            // return mapping.findForward("error");
        }
    }

    public ActionForward doCarpoolUserRegister(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 参数DTO
        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        CarpoolUserClientDTO userClientDTO = new CarpoolUserClientDTO();
        // 返回给页面的DTO

        CarpoolUserEntity userEntity = new CarpoolUserEntity();
        CarpoolUserEntity resultEntity = null;

        CommonUtil.reflectClass(paramDTO, userEntity);
        try {
            // 检测用户是否存在
            boolean existUser = checkExistUser(userEntity.getCu_code());
            if (!existUser) {
                // 昵称默认为用户Code
                userEntity.setCu_nick(userEntity.getCu_code());
                userEntity.setInsert_time(new Date());
                userEntity.setD_success_count(0);
                userEntity.setP_success_count(0);
                iCarpoolUserService.addCarpoolUser(userEntity);

            } else {
                if ("h5".equals(paramDTO.getRequestBy())) {
                    paramDTO.setCu_code(null);
                    ActionErrors errors = new ActionErrors();
                    boolean blnResult = this.logicCheck(request, errors, paramDTO);
                    if (blnResult) {
                        return mapping.findForward("registererror");
                    }
                } else {
                    userClientDTO.setReturnCode("-10004");
                    paramDTO.setErrFlg(true);
                }
            }

        } catch (Exception e) {
            if ("h5".equals(paramDTO.getRequestBy())) {
                paramDTO = null;
                ActionErrors errors = new ActionErrors();
                String errorMessage = "注册";
                errors.add("errorMessage", new ActionMessage("errors.isFailed", new Object[] { errorMessage }));
                this.saveErrors(request, errors);
                return mapping.findForward("registererror");
            } else {
                userClientDTO.setReturnCode("-10004");
                paramDTO.setErrFlg(true);
            }
        }
        if ("app".equals(paramDTO.getRequestBy())) {
            if (!paramDTO.isErrFlg()) {
                userClientDTO.setReturnCode("0");
            }
            toJson(response, userClientDTO);
        }
        return mapping.findForward("registerSuccess");

    }

    public ActionForward doVerifGenerate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;

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
            paramDTO.setVerificationCode(verificationCode);

        } catch (Exception e) {
            paramDTO = null;
            ActionErrors errors = new ActionErrors();
            String errorMessage = "获取验证码";
            errors.add("errorMessage", new ActionMessage("errors.isFailed", new Object[] { errorMessage }));
            this.saveErrors(request, errors);
            return (mapping.findForward("registerSuccess"));
        }

        return mapping.findForward("retrieveError");

    }

    public ActionForward findCarpoolPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;

        CarpoolUserEntity userEntity = new CarpoolUserEntity();
        //
        CarpoolUserClientDTO appClientDTO = new CarpoolUserClientDTO();
        if (0 == paramDTO.getCu_id()) {
            userEntity.setCu_id(null);
        }

        // 结果Entity
        CarpoolUserEntity resultEntity = null;
        // DB操作
        userEntity.setCu_code(paramDTO.getCu_code());
        resultEntity = iCarpoolUserService.getObject(userEntity);

        try {
            ActionErrors errors = new ActionErrors();
            if (resultEntity != null) {

                if ("h5".equals(paramDTO.getRequestBy())) {
                    boolean existUser = checkExistUser(paramDTO.getCu_code());
                    if (existUser) {
                        if (!"".equals(resultEntity.getCu_question()) && resultEntity.getCu_question().equals(paramDTO.getCu_question())) {
                            if (!"".equals(resultEntity.getCu_answer()) && resultEntity.getCu_answer().equals(paramDTO.getCu_answer())) {

                                CommonUtil.reflectClass(resultEntity, paramDTO);
                                return mapping.findForward("retrieveSuccess");
                            } else {
                                // 答案错误
                                paramDTO.setCu_answer(null);
                                // check
                                boolean blnResult = this.checkResult(request, errors, paramDTO);
                                if (blnResult) {
                                    return mapping.findForward("retrieveError");
                                }
                            }
                        } else {
                            paramDTO.setCu_question(null);
                            // check
                            boolean blnResult = this.checkResult(request, errors, paramDTO);
                            if (blnResult) {
                                return mapping.findForward("retrieveError");
                            }
                        }
                    } else {
                        // 账号错误
                        paramDTO.setCu_code(null);
                        // check
                        boolean blnResult1 = this.checkResult(request, errors, paramDTO);
                        if (blnResult1) {
                            return mapping.findForward("retrieveError");
                        }
                    }
                } else {
                    boolean existUser = checkExistUser(paramDTO.getCu_code());
                    if (existUser) {
                        if (!"".equals(resultEntity.getCu_question()) && resultEntity.getCu_question().equals(paramDTO.getCu_question())) {
                            if (!"".equals(resultEntity.getCu_answer()) && resultEntity.getCu_answer().equals(paramDTO.getCu_answer())) {
                                appClientDTO.setCu_id(resultEntity.getCu_id());
                            } else {
                                // 答案
                                appClientDTO.setReturnCode("-10000");
                                paramDTO.setErrFlg(true);
                            }
                        } else {
                            // 问题
                            appClientDTO.setReturnCode("-10001");
                            paramDTO.setErrFlg(true);
                        }
                    } else {
                        // 账号错误
                        appClientDTO.setReturnCode("-10002");
                        paramDTO.setErrFlg(true);
                    }
                }

            } else {// 账号错误
                appClientDTO.setReturnCode("-10002");
                paramDTO.setErrFlg(true);
            }
        } catch (Exception e) {
            if ("app".equals(paramDTO.getRequestBy())) {
                appClientDTO.setReturnCode("-10003");
                paramDTO.setErrFlg(true);
            } else {
                paramDTO = null;
                ActionErrors errors = new ActionErrors();
                String errorMessage = "找回密码";
                errors.add("errorMessage", new ActionMessage("errors.isFailed", new Object[] { errorMessage }));
                this.saveErrors(request, errors);
                return mapping.findForward("retrieveError");
            }
        }
        if ("app".equals(paramDTO.getRequestBy())) {
            if (!paramDTO.isErrFlg()) {
                appClientDTO.setReturnCode("0");
            }
            toJson(response, appClientDTO);
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
    // 自动生成用户Code，用于用户登录的账号
    public ActionForward generateUserCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 结果DTO
        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        try {

            // 生成用户账号
            String userCode = randomGenerateCode();
            // 如果用户账号已存在，重新生成用户账号
            CarpoolUserEntity carpoolUser = new CarpoolUserEntity();
            carpoolUser.setCu_code(userCode);
            while (true) {
                CarpoolUserEntity resultEntity = iCarpoolUserService.getObject(carpoolUser);
                if (resultEntity == null) {
                    break;
                } else {
                    userCode = randomGenerateCode();
                    carpoolUser.setCu_code(userCode);
                }
            }
            paramDTO.setCu_code(userCode);

            // 打印随机生成的UserCode
            // System.out.println("---userCode---" +
            // createUserNameDTO.getUserCode());

        } catch (Exception e) {
            paramDTO = null;
            ActionErrors errors = new ActionErrors();
            String errorMessage = "获取账号";
            errors.add("errorMessage", new ActionMessage("errors.isFailed", new Object[] { errorMessage }));
            this.saveErrors(request, errors);
            return (mapping.findForward("registererror"));
            // formMessages(createUserNameDTO,
            // Constants.MSG_KEY_GENERATEUSERCODE_ERROR, null);

        }
        this.gengerateByAjax(request, response, paramDTO);
        return null;

    }

    public ActionForward doCreateVerificationCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;

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
            paramDTO.setVerificationCode(verificationCode);
            paramDTO.setCu_code(null);
            paramDTO.setCu_password(null);

        } catch (Exception e) {
            paramDTO = null;
            ActionErrors errors = new ActionErrors();
            String errorMessage = "获取验证码";
            errors.add("errorMessage", new ActionMessage("errors.isFailed", new Object[] { errorMessage }));
            this.saveErrors(request, errors);
            return (mapping.findForward("registererror"));
        }

        return mapping.findForward("registererror");

    }

    public ActionForward doUpdateUserPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;

        CarpoolUserEntity paramEntity = new CarpoolUserEntity();
        paramEntity.setCu_id(paramDTO.getCu_id());
        paramEntity.setCu_password(paramDTO.getCu_password());
        CarpoolUserClientDTO appClientDTO = new CarpoolUserClientDTO();

        try {
            iCarpoolUserService.updateCarpoolUser(paramEntity);

        } catch (Exception e) {
            if ("h5".equals(paramDTO.getRequestBy())) {
                paramDTO = null;
                ActionErrors errors = new ActionErrors();
                String errorMessage = "修改密码";
                errors.add("errorMessage", new ActionMessage("errors.isFailed", new Object[] { errorMessage }));
                this.saveErrors(request, errors);
                return (mapping.findForward("retrieveSuccess"));
            } else {
                appClientDTO.setReturnCode("-10003");
                paramDTO.setErrFlg(true);
            }
        }

        if ("h5".equals(paramDTO.getRequestBy())) {
            return mapping.findForward("registerSuccess");
        } else {
            if (!paramDTO.isErrFlg()) {
                appClientDTO.setReturnCode("0");
            }
            toJson(response, appClientDTO);

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
    public ActionForward doUpdatePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        // 返回操作状态

        CarpoolUserClientDTO userClientDTO = new CarpoolUserClientDTO();
        // test
        // UserDTO userDTO = new UserDTO();
        // userDTO.setU_code("999999");
        // userDTO.setU_old_password("111111");
        // userDTO.setU_password("111111");

        CarpoolUserEntity userEntity = new CarpoolUserEntity();
        CarpoolUserEntity resultEntity = null;
        if (paramDTO.getCu_id() == null || 0 == paramDTO.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            if (sessionManager.getCarpoolUserInfo() == null) {
                return mapping.findForward("registerSuccess");
            } else {
                paramDTO.setCu_id(sessionManager.getCarpoolUserInfo().getCu_id());
            }
        }
        // BeanUtils.copyProperties(userEntity, userDTO);
        // CommonUtil.reflectClass(userDTO, userEntity);
        userEntity.setCu_id(paramDTO.getCu_id());
        try {
            resultEntity = iCarpoolUserService.getObject(userEntity);
            if (resultEntity != null) {
                if (paramDTO.getOld_password().equals(resultEntity.getCu_password())) {
                    userEntity.setCu_password(paramDTO.getCu_password());
                    iCarpoolUserService.updateCarpoolUser(userEntity);
                    } else {
                        if ("app".equals(paramDTO.getRequestBy())) {
                            userClientDTO.setReturnCode("-10003");
                            paramDTO.setErrFlg(true);
                        } else {
                            ActionErrors errors = new ActionErrors();
                            String errorMessage = "旧密码";
                            errors.add("errorMessage", new ActionMessage("errors.isWrong", new Object[] { errorMessage }));
                            this.saveErrors(request, errors);
                            return (mapping.findForward("changePassword"));
                        }
                    }}
           else {
               this.doErrorMessage(mapping, response, request, userClientDTO, paramDTO, paramDTO.getRequestBy(), "修改密码", "-10000");
                if ("h5".equals(paramDTO.getRequestBy())) {
                    return (mapping.findForward("updatePassword"));
                }
            }
        } catch (Exception e) {
            this.doErrorMessage(mapping, response, request, userClientDTO, paramDTO, paramDTO.getRequestBy(), "修改密码", "-10000");

        }

        if ("h5".equals(paramDTO.getRequestBy())) {
            paramDTO.setReturnCode("0");       
            return mapping.findForward("updatePassword");
        } else {
            if (!paramDTO.isErrFlg()) {
                userClientDTO.setReturnCode("0");
            }
            toJson(response, userClientDTO);

        }
        return null;
    }

    public ActionForward doLogout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        HttpSession session = request.getSession();
        SessionManager sessionManager = new SessionManager(session);
        if (sessionManager.getCarpoolUserInfo()!= null) {
            sessionManager.setCarpoolUserInfo(null);
        }
        return mapping.findForward("registerSuccess");
    
    
       
    }
    
    
    
    public ActionForward doUpdateDriverInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;

        CarpoolUserEntity paramEntity = new CarpoolUserEntity();
        if (paramDTO.getCu_id() == null || 0 == paramDTO.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            if (sessionManager.getCarpoolUserInfo() == null) {
                return mapping.findForward("registerSuccess");
            } else {
                paramDTO.setCu_id(sessionManager.getCarpoolUserInfo().getCu_id());
            }
        }
        paramEntity.setCu_id(paramDTO.getCu_id());
        paramEntity.setCu_name(paramDTO.getCu_name());
        paramEntity.setId_number(paramDTO.getId_number());
        CarpoolUserClientDTO appClientDTO = new CarpoolUserClientDTO();

        try {
            iCarpoolUserService.updateCarpoolUser(paramEntity);

        } catch (Exception e) {
            if ("h5".equals(paramDTO.getRequestBy())) {
                paramDTO = null;
                ActionErrors errors = new ActionErrors();
                String errorMessage = "认证";
                errors.add("errorMessage", new ActionMessage("errors.isFailed", new Object[] { errorMessage }));
                this.saveErrors(request, errors);
                return (mapping.findForward("verfDriverError"));
            } else {
                appClientDTO.setReturnCode("-10005");
                paramDTO.setErrFlg(true);
            }
        }

        if ("h5".equals(paramDTO.getRequestBy())) {
            return mapping.findForward("verfSuccess");
        } else {
            if (!paramDTO.isErrFlg()) {
                appClientDTO.setReturnCode("0");
            }
            toJson(response, appClientDTO);

        }
        return null;
    }

    public ActionForward doBackToLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        if ("h5".equals(paramDTO.getRequestBy())) {

            return mapping.findForward("registerSuccess");
        }
        return null;
    }
    
   
    
    
    public ActionForward  doChangeToAgreement(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        if ("h5".equals(paramDTO.getRequestBy())) {

            return mapping.findForward("agreement");
        }
        return null;
    }
    public ActionForward  doChangeToRegister(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        if ("h5".equals(paramDTO.getRequestBy())) {

            return mapping.findForward("registererror");
        }
        return null;
    }
    public ActionForward changeToPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        if ("h5".equals(paramDTO.getRequestBy())) {

            return mapping.findForward("changePassword");
        }
        return null;
    }
    public ActionForward backToUserInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        if ("h5".equals(paramDTO.getRequestBy())) {
      
            return mapping.findForward("updatePassword");
        }
        return null;
    }
    
    
    public ActionForward backToRetrivePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        if ("h5".equals(paramDTO.getRequestBy())) {
      
            return mapping.findForward("retrieveError");
        }
        return null;
    }
    
    private void gengerateByAjax(HttpServletRequest req, HttpServletResponse resp, CarpoolUserDTO paramDTO) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/xml;charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        PrintWriter out = resp.getWriter();
        out.println("<response>");
        out.println("<usercode>" + paramDTO.getCu_code() + "</usercode>");

        out.println("</response>");
        out.close();
    }

    public synchronized String randomGenerateCode() {

        // 服务器重启后，生成注册账号的开始账号,通过配置文件获取
        if (userCodeTemp == 100000 && tempNum == 1) {
            String keyName = "userCodeStart";
            long userCode = Long.parseLong(ResourceLocator.getI18NMessage(this, keyName));
            int currUserNum = iCarpoolUserService.countCarpoolUserNumber();
            userCodeTemp = userCode + currUserNum;
        }
        String userCode = "" + (userCodeTemp + tempNum);
        tempNum++;
        return userCode;
    }

    // 检测用户是否存在的方法

    public boolean checkExistUser(String UserCode) {

        try {
            CarpoolUserEntity carpoolUserEntity = new CarpoolUserEntity();
            carpoolUserEntity.setCu_code(UserCode);
            CarpoolUserEntity resultEntity = iCarpoolUserService.getObject(carpoolUserEntity);

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
    // 实现passenger_route页面不同来源的后退
    public ActionForward backToPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        CarpoolUserDTO paramDTO = (CarpoolUserDTO) form;
        //CarpoolUserEntity paramEntity = new CarpoolUserEntity();
        if (paramDTO.getCu_id() == null || 0 == paramDTO.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            if (sessionManager.getCarpoolUserInfo() == null) {
                return mapping.findForward("registerSuccess");
            } else {
                paramDTO.setCu_id(sessionManager.getCarpoolUserInfo().getCu_id());
            }
        }
        try{
        	if("passengerInfo".equals(paramDTO.getScreenId())){
        		paramDTO.setDp_flag("1");
        		return mapping.findForward("updatePassword");
        	}else if("passenger_publish".equals(paramDTO.getScreenId())){
        	//else{	
        		paramDTO.setDp_flag("1");
        		return mapping.findForward("initPassengerSuccess");
        	}
        } catch (Exception e) {
            paramDTO = null;
            ActionErrors errors = new ActionErrors();
            String errorMessage = "返回";
            errors.add("errorMessage", new ActionMessage("errors.isFailed", new Object[] { errorMessage }));
            this.saveErrors(request, errors);
            return (mapping.findForward("registerSuccess"));

        }
        //返回登录页
        return mapping.findForward("registerSuccess");
    }
    
}