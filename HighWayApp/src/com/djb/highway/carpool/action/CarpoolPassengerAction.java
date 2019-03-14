package com.djb.highway.carpool.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import com.djb.highway.framework.SessionManager;
import com.djb.highway.carpool.dto.CarpoolUserDTO;
import com.djb.highway.carpool.dto.DriverRouteDTO;
import com.djb.highway.carpool.dto.PassengerRouteDTO;
import com.djb.highway.carpool.dtoclient.CarpoolUserClientDTO;
import com.djb.highway.carpool.dtoclient.DriverClientDTO;
import com.djb.highway.carpool.dtoclient.DriverRouteDetailDTO;
import com.djb.highway.carpool.dtoclient.PassengerRouteDetailDTO;
import com.djb.highway.carpool.dtoclient.SearchRouteDTO;
import com.djb.highway.carpool.dtoutil.PassengerRouteDTOUtil;
import com.djb.highway.carpool.entity.CarpoolRouteEntity;
import com.djb.highway.carpool.entity.CarpoolUserEntity;
import com.djb.highway.carpool.entity.DriverRouteEntity;
import com.djb.highway.carpool.entity.PassengerRouteEntity;
import com.djb.highway.carpool.service.ICarpoolRouteService;
import com.djb.highway.carpool.service.ICarpoolUserService;
import com.djb.highway.carpool.service.IDriverRouteService;
import com.djb.highway.carpool.service.IPassengerRouteService;
import com.djb.highway.common.util.CommonUtil;

import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.road.dtoclient.BaseClientDTO;

@Controller("/CarpoolPassenger")
public class CarpoolPassengerAction extends BaseAction {

    @Autowired
    @Qualifier("iDriverRouteService")
    private IDriverRouteService iDriverRouteService;

    @Autowired
    @Qualifier("iPassengerRouteService")
    private IPassengerRouteService iPassengerRouteService;

    @Autowired
    @Qualifier("iCarpoolRouteService")
    private ICarpoolRouteService iCarpoolRouteService;

    @Autowired
    @Qualifier("iCarpoolUserService")
    private ICarpoolUserService iCarpoolUserService;

    public CarpoolPassengerAction() {
        super();
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

    public ActionForward searchCarpoolList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        PassengerRouteDTO passengerRouteDTO = (PassengerRouteDTO) form;

        PassengerRouteEntity paramEntity = new PassengerRouteEntity();
        // test
        // passengerRouteDTO.setBegin_time("2015/08/25 09:00");
        // passengerRouteDTO.setEnd_time("2015/10/27 09:10");
        // passengerRouteDTO.setStart_city("沈阳");
        // passengerRouteDTO.setEnd_city("大连");
        // passengerRouteDTO.setRequestBy("app");
        // passengerRouteDTO.setCharter_flg("0");
        // 参数DTO -> 参数Entity
        if ("起点".equals(passengerRouteDTO.getStart_city())) {
            passengerRouteDTO.setStart_city(null);
        }
        if ("终点".equals(passengerRouteDTO.getEnd_city())) {
            passengerRouteDTO.setEnd_city(null);
        }
        if ("".equals(passengerRouteDTO.getBegin_time())) {
            passengerRouteDTO.setBegin_time(null);
        }
        if ("".equals(passengerRouteDTO.getPend_time())) {
            passengerRouteDTO.setPend_time(null);
        }
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (!"".equals(passengerRouteDTO.getBegin_time()) && passengerRouteDTO.getBegin_time() != null) {
            Date date = new Date();
            if (CommonUtil.stringToDate(passengerRouteDTO.getBegin_time(), "yyyy-MM-dd HH:mm").before(date)) {
                paramEntity.setBegin_time(date);
            } else {

                paramEntity.setBegin_time(CommonUtil.stringToDate(passengerRouteDTO.getBegin_time(), "yyyy-MM-dd HH:mm"));
            }
            // paramEntity.setBegin_time(sdf.parse(passengerRouteDTO.getBegin_time()));
        }
        if (!"".equals(passengerRouteDTO.getPend_time()) && passengerRouteDTO.getPend_time() != null) {
            paramEntity.setEnd_time(CommonUtil.stringToDate(passengerRouteDTO.getPend_time(), "yyyy-MM-dd HH:mm"));
            // paramEntity.setEnd_time(sdf.parse(passengerRouteDTO.getEnd_time()));
        }
        if (passengerRouteDTO.getBegin_time() == null && passengerRouteDTO.getPend_time() == null) {
            Date date = new Date();
            // System.out.println(date+"date");
            paramEntity.setBegin_time(date);
        }
        paramEntity.setEnd_city(passengerRouteDTO.getEnd_city());
        paramEntity.setStart_city(passengerRouteDTO.getStart_city());
        if (!"".equals(passengerRouteDTO.getCharter_flg())) {
            paramEntity.setCharter_flg(passengerRouteDTO.getCharter_flg());
        }
        if (!"".equals(passengerRouteDTO.getEnd_area()) && passengerRouteDTO.getEnd_area() != null) {
            paramEntity.setEnd_area(passengerRouteDTO.getEnd_area());
        }
        if (!"".equals(passengerRouteDTO.getStart_area()) && passengerRouteDTO.getStart_area() != null) {
            paramEntity.setStart_area(passengerRouteDTO.getStart_area());
        }
        // 结果Entity
        List<PassengerRouteEntity> resultList = null;
        // app客户端DTO
        List<PassengerRouteDetailDTO> searchRouteDetailList = null;
        SearchRouteDTO searchRouteDTO = new SearchRouteDTO();
        // h5客户端DTO
        List<PassengerRouteDTO> passengerRouteList = null;

        // DB操作
        try {
            resultList = iPassengerRouteService.getPassengerRouteListBySearch(paramEntity);
            if (resultList != null && resultList.size() > 0) {
                if ("app".equals(passengerRouteDTO.getRequestBy())) {
                    searchRouteDetailList = new ArrayList<PassengerRouteDetailDTO>();
                    for (PassengerRouteEntity passengerEntity : resultList) {

                        PassengerRouteDetailDTO paramDetailDTO = new PassengerRouteDetailDTO();
                        PassengerRouteDTOUtil.doSearchBacktoAppClient(passengerEntity, paramDetailDTO);
                        searchRouteDetailList.add(paramDetailDTO);
                    }
                    searchRouteDTO.setList(searchRouteDetailList);
                } else {
                    passengerRouteList = new ArrayList<PassengerRouteDTO>();
                    for (PassengerRouteEntity passengerEntity : resultList) {
                        PassengerRouteDTO paramDTO = new PassengerRouteDTO();
                        PassengerRouteDTOUtil.doSearchBacktoH5Client(passengerEntity, paramDTO);
                        passengerRouteList.add(paramDTO);

                    }
                    passengerRouteDTO.setPassengerRoutelist(passengerRouteList);
                }
            }

        } catch (Exception e) {

            doErrorMessage(mapping, response, request, searchRouteDTO, passengerRouteDTO, passengerRouteDTO.getRequestBy(), "搜索乘客", "-6001");
            if ("h5".equals(passengerRouteDTO.getRequestBy())) {
                return mapping.findForward("searcherror");
            }
        }
        if (!passengerRouteDTO.isErrFlg()) {

            searchRouteDTO.setReturnCode("0");
        }
        // 返回结果给客户端
        if ("app".equals(passengerRouteDTO.getRequestBy())) {
            toJson(response, searchRouteDTO);

        } else {
            // if(paramEntity.getBegin_time()!=null){
            // passengerRouteDTO.setBegin_time(
            // CommonUtil.dateToString(paramEntity.getBegin_time(),
            // "MM月dd日 HH:mm"));
            // }
            // if(paramEntity.getEnd_time()!=null){
            // passengerRouteDTO.setPend_time(
            // CommonUtil.dateToString(paramEntity.getEnd_time(),
            // "MM月dd日 HH:mm"));
            // }
            return mapping.findForward("searchSuccess");
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

    public ActionForward publishPassengerRoute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        PassengerRouteDTO passengerRouteDTO = (PassengerRouteDTO) form;
        // test

        // passengerRouteDTO.setCu_id(266);
        // passengerRouteDTO.setStart_city("大连");
        // passengerRouteDTO.setEnd_city("沈阳");
        // passengerRouteDTO.setStart_area("中山区");
        // passengerRouteDTO.setEnd_area("皇姑区");
        // passengerRouteDTO.setPeople_count(4);
        // passengerRouteDTO.setRequestBy("app");
        // passengerRouteDTO.setPrice((float) 80);
        // passengerRouteDTO.setGo_time("2015-08-26 09:15");
        // passengerRouteDTO.setCharter_flg("1");
        // passengerRouteDTO.setPr_memo("带孩子");
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if ("".equals(passengerRouteDTO.getGo_time())) {
            passengerRouteDTO.setGo_time(null);
        }

        // 参数Entity
        PassengerRouteEntity paramEntity = new PassengerRouteEntity();

        // 结果Entity
        PassengerRouteEntity resultEntity = null;
        // 参数DTO->参数Entity
        if (passengerRouteDTO.getCu_id() == null || 0 == passengerRouteDTO.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            if (sessionManager.getCarpoolUserInfo() == null) {
                return mapping.findForward("login");
            } else {
                passengerRouteDTO.setCu_id(sessionManager.getCarpoolUserInfo().getCu_id());
            }
        }

        CommonUtil.reflectClass(passengerRouteDTO, paramEntity);
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (passengerRouteDTO.getGo_time() != null && !"".equals(passengerRouteDTO.getGo_time())) {
            // String time = passengerRouteDTO.getGo_time().replace("/", "-");
            // System.out.println(time);
            paramEntity.setStart_time(CommonUtil.stringToDate(passengerRouteDTO.getGo_time(), "yyyy-MM-dd HH:mm"));
            // passengerRouteDTO.setStart_time(sdf.parse(time));
        }
        if (passengerRouteDTO.getPrice() != null) {
            paramEntity.setPrice((float) passengerRouteDTO.getPrice());
        }
        Date date = new Date();
        paramEntity.setPr_insert_time(date);
        paramEntity.setState("0");
        if (passengerRouteDTO.getCharter_flg() == null) {
            paramEntity.setCharter_flg("0");
        }
        // APPClientDTO
        PassengerRouteDetailDTO clientDTO = new PassengerRouteDetailDTO();

        try {

            iPassengerRouteService.addPassengerRoute(paramEntity);
            PassengerRouteEntity paramResult = new PassengerRouteEntity();
            CommonUtil.reflectClass(paramEntity, paramResult);
            resultEntity = iPassengerRouteService.getObject(paramResult);
            if (resultEntity != null) {
                if ("app".equals(passengerRouteDTO.getRequestBy())) {
                    clientDTO.setPassenger_route_id(resultEntity.getPr_id());
                } else {

                    passengerRouteDTO.setPr_id(resultEntity.getPr_id());
                }
            } else {
                doErrorMessage(mapping, response, request, clientDTO, passengerRouteDTO, passengerRouteDTO.getRequestBy(), "添加路线", "-6006");
                if ("h5".equals(passengerRouteDTO.getRequestBy())) {
                    return mapping.findForward("changetoPassenger");
                }
            }
        } catch (Exception e) {

            doErrorMessage(mapping, response, request, clientDTO, passengerRouteDTO, passengerRouteDTO.getRequestBy(), "添加路线", "-6006");
            if ("h5".equals(passengerRouteDTO.getRequestBy())) {
                return mapping.findForward("changetoPassenger");
            }

        }
        if (!passengerRouteDTO.isErrFlg()) {

            clientDTO.setReturnCode("0");
        }

        // 返回结果给客户端

        if ("app".equals(passengerRouteDTO.getRequestBy())) {
            toJson(response, clientDTO);

        } else {
            return mapping.findForward("getPassengerRoute");
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

    public ActionForward cancelPassengerRoute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        PassengerRouteDTO passengerRouteDTO = (PassengerRouteDTO) form;
        // test
        // passengerRouteDTO.setPr_id(24);
        // passengerRouteDTO.setCu_id(266);
        // passengerRouteDTO.setRequestBy("app");

        // 参数Entity
        PassengerRouteEntity paramEntity = new PassengerRouteEntity();
        CarpoolRouteEntity paramCarpoolEntity = new CarpoolRouteEntity();
        DriverRouteEntity paramDriverEntity = new DriverRouteEntity();
        // 结果Entity
        PassengerRouteEntity resultPassenger = null;
        DriverRouteEntity driverResultEntity = null;
        List<CarpoolRouteEntity> carpoolResultEntity = null;
        // 参数DTO->参数Entity
        if (passengerRouteDTO.getCu_id() == null || 0 == passengerRouteDTO.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            if (sessionManager.getCarpoolUserInfo() == null) {
                return mapping.findForward("login");
            } else {
                passengerRouteDTO.setCu_id(sessionManager.getCarpoolUserInfo().getCu_id());
            }
        }
        CommonUtil.reflectClass(passengerRouteDTO, paramEntity);
        // APPClientDTO
        PassengerRouteDetailDTO passengerClientDTO = new PassengerRouteDetailDTO();

        try {
            // 该乘客路线的状态是否是接单状态
            PassengerRouteEntity passengerRouteEntity = new PassengerRouteEntity();
            passengerRouteEntity.setPr_id(passengerRouteDTO.getPr_id());
            resultPassenger = iPassengerRouteService.getObjectByState(passengerRouteEntity);
            if (resultPassenger != null && "10".equals(resultPassenger.getState())) {

                // 更新乘客路线状态
                paramEntity.setPr_id(passengerRouteDTO.getPr_id());
                paramEntity.setState(passengerRouteDTO.getState());
                paramEntity.setState("21");
                paramEntity.setPr_update_time(new Date());
                iPassengerRouteService.updatePassengerRoute(paramEntity);
                Date max = null;
                Date min = null;
                // 获取司机路线ID
                if (resultPassenger.getCarpoolRouteEntity() != null) {

                    // 修改司机路线时间和搭乘人数
                    paramDriverEntity.setDr_id(resultPassenger.getCarpoolRouteEntity().getDr_id());
                    driverResultEntity = iDriverRouteService.getObject(paramDriverEntity);

                    // 开始时间和结束时间都不等于乘客路线的时间 ，则只修改人数
                    if (!resultPassenger.getStart_time().equals(driverResultEntity.getStart_time())
                                    && !resultPassenger.getStart_time().equals(driverResultEntity.getEnd_time())) {
                        paramDriverEntity.setTotal_num(driverResultEntity.getTotal_num() - resultPassenger.getPeople_count());
                        if (paramDriverEntity.getTotal_num() < 0) {
                            paramDriverEntity.setTotal_num(0);
                        }
                    } else {

                        paramCarpoolEntity.setDr_id(resultPassenger.getCarpoolRouteEntity().getDr_id());
                        carpoolResultEntity = iCarpoolRouteService.getCarpoolRouteList(paramCarpoolEntity);
                        // 该路线在关联表中的记录如果=1时间不变，该路线已经被取消，如果该路线的记录大于1则选取路线不是该乘客路线的时间的最大值和
                        // 和最小值作为出发时间最大值和出发时间最小值
                        if (carpoolResultEntity != null && carpoolResultEntity.size() > 1) {
                            for (CarpoolRouteEntity param : carpoolResultEntity) {
                                if (param.getPr_id() != passengerRouteDTO.getPr_id()) {
                                    PassengerRouteEntity paramRouteEntity = new PassengerRouteEntity();
                                    paramRouteEntity.setPr_id(param.getPr_id());
                                    paramRouteEntity = iPassengerRouteService.getObject(paramRouteEntity);
                                    if (paramRouteEntity != null && "0".equals(paramRouteEntity.getState())) {
                                        if (max == null) {
                                            max = paramRouteEntity.getStart_time();
                                        } else {
                                            if (max.before(paramRouteEntity.getStart_time())) {

                                                max = paramRouteEntity.getStart_time();
                                            }
                                        }
                                        if (min == null) {

                                            min = paramRouteEntity.getStart_time();
                                        } else {

                                            if (min.after(paramRouteEntity.getStart_time())) {
                                                min = paramRouteEntity.getStart_time();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (min != null) {

                            paramEntity.setStart_time(min);
                        }
                        if (max != null) {

                            paramEntity.setEnd_time(max);
                        }
                        paramDriverEntity.setTotal_num(driverResultEntity.getTotal_num() - resultPassenger.getPeople_count());
                        if (paramDriverEntity.getTotal_num() < 0) {
                            paramDriverEntity.setTotal_num(0);
                        }
                    }

                    iDriverRouteService.updateDriverRoute(paramDriverEntity);

                }
                // 显示乘客路线的已完成模块
                passengerRouteDTO.setTab_show("2");
            }

        } catch (Exception e) {

            doErrorMessage(mapping, response, request, passengerClientDTO, passengerRouteDTO, passengerRouteDTO.getRequestBy(), "取消路线", "-6008");
            if ("h5".equals(passengerRouteDTO.getRequestBy())) {
                return mapping.findForward("getPassengerRoute");
            }

        }
        if (!passengerRouteDTO.isErrFlg()) {
            passengerClientDTO.setReturnCode("0");
        }
        // 返回结果给客户端
        // responseResult(mapping, response, passengerClientDTO, "forwardName",
        // passengerRouteDTO.getRequestBy());
        if ("app".equals(passengerRouteDTO.getRequestBy())) {
            toJson(response, passengerClientDTO);

        }
        if ("h5".equals(passengerRouteDTO.getRequestBy())) {
            return mapping.findForward("getPassengerRoute");
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
    public ActionForward changeToPassenger(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        PassengerRouteDTO passengerRouteDTO = (PassengerRouteDTO) form;
        passengerRouteDTO.setStart_city(null);
        passengerRouteDTO.setEnd_city(null);
        passengerRouteDTO.setCharter_flg(null);
        if ("h5".equals(passengerRouteDTO.getRequestBy())) {

            return (mapping.findForward("changetoPassenger"));
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
    public ActionForward backToSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        PassengerRouteDTO passengerRouteDTO = (PassengerRouteDTO) form;

        if ("h5".equals(passengerRouteDTO.getRequestBy())) {

            return (mapping.findForward("searchError"));
        }
        return null;

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
                    PassengerRouteDTO passengerRouteDTO, String requestBy, String errorMessage, String returnCode) throws Exception {
        if ("app".equals(requestBy)) {
            clientDTO.setReturnCode(returnCode);
            passengerRouteDTO.setErrFlg(true);
        } else {
            ActionErrors errors = new ActionErrors();
            // String errorMessage = "加入路线";
            errors.add("errorMessage", new ActionMessage("errors.isFailed", new Object[] { errorMessage }));
            this.saveErrors(request, errors);
            // return mapping.findForward("error");
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
    // （乘客）我发布的搭车列表
    public ActionForward getPassengerRouteList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        PassengerRouteDTO paramDTO = (PassengerRouteDTO) form;
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY年MM月dd日 HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-MM-dd");
        PassengerRouteEntity paramEntity = new PassengerRouteEntity();
        paramEntity.setCu_id(paramDTO.getCu_id());
        // 参数cu_id为空时，从session中获取cu_id
        if (null == paramDTO.getCu_id() || 0 == paramDTO.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            CarpoolUserDTO carpoolUserInfo = sessionManager.getCarpoolUserInfo();
            if (null == carpoolUserInfo) {
                return mapping.findForward("login");
            } else {
                paramEntity.setCu_id(carpoolUserInfo.getCu_id());
            }
        }
        // 定义返回结果变量resultPassengerRouteClientDTO
        SearchRouteDTO resultPassengerRouteClientDTO = new SearchRouteDTO();
        try {
            if ("app".equals(paramDTO.getRequestBy())) {
                // app操作

                List<PassengerRouteDetailDTO> resultPREntityList = new ArrayList<PassengerRouteDetailDTO>();
                List<PassengerRouteEntity> resultEntityList = null;
                resultEntityList = iPassengerRouteService.getPassengerRouteListByTime(paramEntity);
                if (resultEntityList != null && resultEntityList.size() > 0) {
                    for (PassengerRouteEntity resultEntity : resultEntityList) {

                        DriverClientDTO resultDriverClientDTO = new DriverClientDTO();
                        // 定义结果变量resultPassengerRouteDTOList中的成员resultPRClientDTO，乘客的路线信息
                        PassengerRouteDetailDTO resultPRClientDTO = new PassengerRouteDetailDTO();

                        resultPRClientDTO.setPassenger_route_id(resultEntity.getPr_id());
                        resultPRClientDTO.setStartcity(resultEntity.getStart_city());
                        resultPRClientDTO.setStartarea(resultEntity.getStart_area());
                        resultPRClientDTO.setEndcity(resultEntity.getEnd_city());
                        resultPRClientDTO.setEndarea(resultEntity.getEnd_area());
                        resultPRClientDTO.setStarttime(sdf.format(resultEntity.getStart_time()));
                        resultPRClientDTO.setNumber(resultEntity.getPeople_count());
                        resultPRClientDTO.setCharter(resultEntity.getCharter_flg());
                        resultPRClientDTO.setPrice(resultEntity.getPrice());
                        resultPRClientDTO.setRemark(resultEntity.getPr_memo().trim());
                        resultPRClientDTO.setState(resultEntity.getState());
                        /*
                         * Date date = new Date(); if ("12" !=
                         * resultEntity.getState() && "21" !=
                         * resultEntity.getState() && "22" !=
                         * resultEntity.getState() &&
                         * date.before(CommonUtil.addOneDay
                         * (resultEntity.getStart_time()))) {
                         * resultPRDTO.setState("23"); }
                         */
                        // 用pr_id到CarpoolRoute表中检索dr_id
                        if (resultEntity.getPr_id() != null && !("".equals(resultEntity.getPr_id()))) {
                            CarpoolRouteEntity paramCREntity = new CarpoolRouteEntity();
                            paramCREntity.setPr_id(resultEntity.getPr_id());
                            // paramCREntity.setCr_id(39);
                            CarpoolRouteEntity resultCREntity = null;
                            resultCREntity = iCarpoolRouteService.getObject(paramCREntity);
                            // 用dr_id到DriverRoute表中检索cu_id
                            if (resultCREntity != null && resultCREntity.getDr_id() != null && !("".equals(resultCREntity.getDr_id()))) {
                                DriverRouteEntity paramDREntity = new DriverRouteEntity();
                                paramDREntity.setDr_id(resultCREntity.getDr_id());
                                DriverRouteEntity resultDREntity = null;
                                resultDREntity = iDriverRouteService.getObject(paramDREntity);
                                if (resultDREntity != null) {
                                    // 用cu_id到CarpoolUser表中检索司机信息
                                    CarpoolUserEntity paramCarpoolUser = new CarpoolUserEntity();
                                    CarpoolUserEntity resultCarpoolUser = new CarpoolUserEntity();
                                    paramCarpoolUser.setCu_id(resultDREntity.getCu_id());
                                    resultCarpoolUser = iCarpoolUserService.getObject(paramCarpoolUser);
                                    if (resultCarpoolUser != null) {
                                        resultDriverClientDTO.setDriver_route_id(resultDREntity.getDr_id().toString());
                                        resultDriverClientDTO.setDriver_user_id(resultCarpoolUser.getCu_id().toString());
                                        resultDriverClientDTO.setNickname(resultCarpoolUser.getCu_nick());
                                        resultDriverClientDTO.setTele(resultCarpoolUser.getCu_tel());
                                        resultDriverClientDTO.setCharter(Integer.parseInt(resultEntity.getCharter_flg()));
                                        resultDriverClientDTO.setPrice(resultEntity.getPrice());
                                        resultDriverClientDTO.setRemark(resultEntity.getPr_memo().trim());
                                        resultDriverClientDTO.setCarbrand(resultCarpoolUser.getCar_brand());
                                        resultDriverClientDTO.setCartype(resultCarpoolUser.getCar_type());
                                        resultDriverClientDTO.setCarcolor(resultCarpoolUser.getCar_color());
                                        resultDriverClientDTO.setCarnum(resultCarpoolUser.getCar_num());
                                        resultDriverClientDTO.setSuccesscount(resultCarpoolUser.getD_success_count());
                                        if (null != resultCarpoolUser.getInsert_time() && !("".equals(resultCarpoolUser.getInsert_time()))) {
                                            resultDriverClientDTO.setCreatetime(sdf1.format(resultCarpoolUser.getInsert_time()));
                                        }
                                        resultPRClientDTO.setDriver(resultDriverClientDTO);
                                        resultPREntityList.add(resultPRClientDTO);
                                    }
                                } else {
                                    resultPassengerRouteClientDTO.setReturnCode("-6028");
                                    paramDTO.setErrFlg(true);
                                }
                            }

                        } else {
                            resultPassengerRouteClientDTO.setReturnCode("-6029");
                            paramDTO.setErrFlg(true);
                        }
                    }
                    resultPassengerRouteClientDTO.setList(resultPREntityList);
                } else {
                    resultPassengerRouteClientDTO.setReturnCode("-6029");
                    paramDTO.setErrFlg(true);
                }
            } else {// html5

                List<PassengerRouteDTO> resultPRDTOList = new ArrayList<PassengerRouteDTO>();
                List<PassengerRouteEntity> resultEntityList = null;
                // resultEntityList =
                // iPassengerRouteService.getPassengerRouteList(paramEntity);
                resultEntityList = iPassengerRouteService.getPassengerRouteListByTime(paramEntity);
                if (resultEntityList != null && resultEntityList.size() > 0) {
                    for (PassengerRouteEntity resultEntity : resultEntityList) {

                        // 定义结果变量resultPassengerRouteDTOList中的成员resultPRClientDTO，乘客的路线信息
                        PassengerRouteDTO resultPRDTO = new PassengerRouteDTO();

                        resultPRDTO.setPr_id(resultEntity.getPr_id());
                        resultPRDTO.setStart_city(resultEntity.getStart_city());
                        resultPRDTO.setStart_area(resultEntity.getStart_area());
                        resultPRDTO.setEnd_city(resultEntity.getEnd_city());
                        resultPRDTO.setEnd_area(resultEntity.getEnd_area());
                        resultPRDTO.setGo_time(sdf.format(resultEntity.getStart_time()));
                        resultPRDTO.setPeople_count(resultEntity.getPeople_count());
                        resultPRDTO.setCharter_flg(resultEntity.getCharter_flg());
                        resultPRDTO.setPrice(resultEntity.getPrice());
                        resultPRDTO.setPr_memo(resultEntity.getPr_memo());
                        resultPRDTO.setState(resultEntity.getState());
                        Date date = new Date();
                        /*
                         * if ("12" != resultEntity.getState() && "21" !=
                         * resultEntity.getState() && "22" !=
                         * resultEntity.getState() &&
                         * CommonUtil.addOneDay(resultEntity
                         * .getStart_time()).before(date)) {
                         * resultPRDTO.setState("23"); }
                         */
                        if (null != resultEntity.getState() && "0".equals(resultEntity.getState()) && resultEntity.getStart_time().before(date)) {
                            // 乘客发单后，当前时间大于出发时间，仍没有司机抢单，更新该路线状态为“已过期”

                            resultPRDTO.setState("23");
                        } else if (null != resultEntity.getState() && "10".equals(resultEntity.getState())
                                        && CommonUtil.addOneDay(resultEntity.getStart_time()).before(date)) {
                            // 乘客发单后，司机抢单，出发时间+24小时后，该单状态仍为司机已抢单状态，更新该路线状态为“已过期”
                            resultPRDTO.setState("23");
                        } else if (null != resultEntity.getState() && "11".equals(resultEntity.getState())
                                        && CommonUtil.addOneDay(resultEntity.getStart_time()).before(date)) {
                            // 乘客已上车状态下，出发时间+24小时，如果该路线状态不是“已完成”，更新该路线状态为“已完成”
                            // PassengerRouteEntity stateEntity = new
                            // PassengerRouteEntity();
                            PassengerRouteDTO passengerRouteDTO = new PassengerRouteDTO();
                            passengerRouteDTO.setCu_id(passengerRouteDTO.getCu_id());
                            passengerRouteDTO.setPr_id(resultEntity.getPr_id());
                            passengerRouteDTO.setState("12");
                            this.updateRouteStatus1(passengerRouteDTO);
                            resultPRDTO.setState("12");
                        }
                        // 用pr_id到CarpoolRoute表中检索dr_id
                        if (resultEntity.getPr_id() != null && !("".equals(resultEntity.getPr_id()))) {
                            CarpoolRouteEntity paramCREntity = new CarpoolRouteEntity();
                            paramCREntity.setPr_id(resultEntity.getPr_id());
                            CarpoolRouteEntity resultCREntity = null;
                            resultCREntity = iCarpoolRouteService.getObject(paramCREntity);
                            // 用dr_id到DriverRoute表中检索cu_id
                            if (resultCREntity != null && resultCREntity.getDr_id() != null && !("".equals(resultCREntity.getDr_id()))) {
                                DriverRouteEntity paramDREntity = new DriverRouteEntity();
                                paramDREntity.setDr_id(resultCREntity.getDr_id());
                                DriverRouteEntity resultDREntity = null;
                                resultDREntity = iDriverRouteService.getObject(paramDREntity);
                                // 用cu_id到CarpoolUser表中检索司机信息
                                if (resultDREntity != null && resultDREntity.getCu_id() != null && !("".equals(resultDREntity.getCu_id()))) {
                                    CarpoolUserDTO resultCarpoolUserDTO = new CarpoolUserDTO();
                                    CarpoolUserEntity paramCUEntity = new CarpoolUserEntity();
                                    paramCUEntity.setCu_id(resultDREntity.getCu_id());
                                    CarpoolUserEntity resultCUEntity = null;
                                    resultCUEntity = iCarpoolUserService.getObject(paramCUEntity);

                                    resultCarpoolUserDTO.setDr_id(resultDREntity.getDr_id());
                                    resultCarpoolUserDTO.setCu_id(resultCUEntity.getCu_id());
                                    resultCarpoolUserDTO.setCu_nick(resultCUEntity.getCu_nick());
                                    resultCarpoolUserDTO.setCu_tel(resultCUEntity.getCu_tel());
                                    // 乘客发布路线时的是否包车、价格、备注信息
                                    resultCarpoolUserDTO.setCharter_flg(resultEntity.getCharter_flg());
                                    resultCarpoolUserDTO.setPrice(resultEntity.getPrice());
                                    resultCarpoolUserDTO.setMemo(resultEntity.getPr_memo());

                                    resultCarpoolUserDTO.setCar_brand(resultCUEntity.getCar_brand());
                                    resultCarpoolUserDTO.setCar_type(resultCUEntity.getCar_type());
                                    resultCarpoolUserDTO.setCar_color(resultCUEntity.getCar_color());
                                    resultCarpoolUserDTO.setCar_num(resultCUEntity.getCar_num());
                                    resultCarpoolUserDTO.setD_success_count(resultCUEntity.getD_success_count());
                                    // SimpleDateFormat sdf = new
                                    // SimpleDateFormat("MM月dd日 HH:mm");
                                    if (null != resultCUEntity.getInsert_time() && !("".equals(resultCUEntity.getInsert_time()))) {
                                        resultCarpoolUserDTO.setInsert_time(sdf1.format(resultCUEntity.getInsert_time()));
                                    }

                                    // 司机信息列表添加到乘客路线中
                                    resultPRDTO.setCarpoolUser(resultCarpoolUserDTO);
                                    resultPRDTOList.add(resultPRDTO);
                                }
                            } else {
                                resultPRDTOList.add(resultPRDTO);
                            }
                        }
                    }
                    paramDTO.setPassengerRoutelist(resultPRDTOList);
                    // resultPassengerRouteDTOList.setPassengerRoutelist(resultPRDTOList);
                    /*
                     * if(null != paramDTO.getScreenId() &&
                     * "passenger_publish".equals(paramDTO.getScreenId())){
                     * paramDTO.setReturnCode("0"); }else if(null !=
                     * paramDTO.getScreenId() &&
                     * "passenger_route".equals(paramDTO.getScreenId())){
                     * paramDTO.setReturnCode("1"); }
                     */
                    return mapping.findForward("getPassengerRouteInfoSuccess");
                } else {
                    return mapping.findForward("getPassengerRouteInfoSuccess");
                }
            }
        } catch (Exception e) {
            doErrorMessage(mapping, response, request, resultPassengerRouteClientDTO, paramDTO, paramDTO.getRequestBy(), "获取乘客已发布路线", "-6030");
            if ("h5".equals(paramDTO.getRequestBy())) {
                return mapping.findForward("error");
            }
        }
        if ("app".equals(paramDTO.getRequestBy())) {
            if (!paramDTO.isErrFlg()) {
                resultPassengerRouteClientDTO.setReturnCode("0");
            }
            toJson(response, resultPassengerRouteClientDTO);
        }
        if ("h5".equals(paramDTO.getRequestBy())) {
            return mapping.findForward("error");
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
    // 更新搭车信息状态（上/下）车
    public ActionForward updateRouteStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        PassengerRouteDTO paramDTO = (PassengerRouteDTO) form;
        paramDTO.setReturnCode("");
        PassengerRouteEntity paramEntity = new PassengerRouteEntity();
        paramEntity.setPr_id(paramDTO.getPr_id());
        // 参数cu_id为空时，从session中获取cu_id
        if (null == paramDTO.getCu_id() || 0 == paramDTO.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            CarpoolUserDTO carpoolUserInfo = sessionManager.getCarpoolUserInfo();
            if (null == carpoolUserInfo) {
                return mapping.findForward("login");
            } else {
                paramEntity.setCu_id(carpoolUserInfo.getCu_id());
            }
        }
        PassengerRouteDetailDTO resultDTO = new PassengerRouteDetailDTO();
        try {
            PassengerRouteEntity paramPREntity = new PassengerRouteEntity();
            paramPREntity.setPr_id(paramDTO.getPr_id());
            CarpoolUserEntity carpoolUserEntity = new CarpoolUserEntity();
            CarpoolUserEntity carpoolUserResultEntity = new CarpoolUserEntity();
            PassengerRouteEntity resultEntity = null;
            resultEntity = iPassengerRouteService.getObject(paramEntity);

            if (resultEntity != null) {
                // 乘客成功搭车次数加1,更新乘客位置
                carpoolUserEntity.setCu_id(resultEntity.getCu_id());
                carpoolUserEntity.setLongitude(paramDTO.getP_longitude());
                carpoolUserEntity.setLatitude(paramDTO.getP_latitude());
                carpoolUserResultEntity = iCarpoolUserService.getObject(carpoolUserEntity);

                if ("10".equals(resultEntity.getState()) && "11".equals(paramDTO.getState())) {// 司机抢单之后，乘客才能更新状态为已上车
                    paramPREntity.setState(paramDTO.getState());
                    iPassengerRouteService.updatePassengerRoute(paramPREntity);
                    if ("h5".equals(paramDTO.getRequestBy())) {
                        return mapping.findForward("getPassengerRoute");
                    }
                } else if ("11".equals(resultEntity.getState()) && "12".equals(paramDTO.getState())) {// 乘客已上车状态下，乘客才能更新状态为已下车
                    paramPREntity.setState(paramDTO.getState());
                    if (carpoolUserResultEntity.getP_success_count() == null) {
                        carpoolUserEntity.setP_success_count(1);
                    } else {
                        carpoolUserEntity.setP_success_count(carpoolUserResultEntity.getP_success_count() + 1);
                    }
                    iCarpoolUserService.updateCarpoolUser(carpoolUserEntity);
                    iPassengerRouteService.updatePassengerRoute(paramPREntity);
                    // 显示乘客路线的已完成模块
                    paramDTO.setTab_show("2");
                    // 当前司机路线下的所有乘客路线状态当且仅当只有一个为已完成时，司机成功次数加1
                    CarpoolRouteEntity carpoolRouteEntity = new CarpoolRouteEntity();
                    carpoolRouteEntity.setDr_id(paramDTO.getDr_id());
                    // 获取相同dr_id下的所有路线
                    List<CarpoolRouteEntity> resultCRList = new ArrayList<CarpoolRouteEntity>();
                    resultCRList = iCarpoolRouteService.getCarpoolRouteList(carpoolRouteEntity);
                    if (null != resultCRList && resultCRList.size() > 0) {
                        int count = 0;
                        for (CarpoolRouteEntity result : resultCRList) {
                            // 遍历每条路线下的状态值
                            PassengerRouteEntity prEntity = new PassengerRouteEntity();
                            prEntity.setPr_id(result.getPr_id());
                            PassengerRouteEntity resultPREntity = null;
                            resultPREntity = iPassengerRouteService.getObject(prEntity);
                            if (null != resultPREntity && "12".equals(resultPREntity.getState())) {
                                // 每当有状态为已完成时，count++
                                count++;
                            }
                        }
                        /*
                         * if(count == 1){
                         * if(carpoolUserResultEntity.getD_success_count() ==
                         * null){ carpoolUserEntity.setD_success_count(1);
                         * }else{ carpoolUserEntity.setD_success_count(
                         * carpoolUserResultEntity.getD_success_count() + 1); }
                         * iCarpoolUserService
                         * .updateCarpoolUser(carpoolUserEntity); }
                         */
                        DriverRouteEntity driverRouteEntity = new DriverRouteEntity();
                        DriverRouteEntity resultDREntity = null;
                        driverRouteEntity.setDr_id(paramDTO.getDr_id());
                        resultDREntity = iDriverRouteService.getObject(driverRouteEntity);
                        if (null != resultDREntity) {
                            CarpoolUserEntity carpoolUEntity = new CarpoolUserEntity();
                            CarpoolUserEntity carpoolUResultEntity = null;
                            carpoolUEntity.setCu_id(resultDREntity.getCu_id());
                            carpoolUResultEntity = iCarpoolUserService.getObject(carpoolUEntity);
                            if (count == 1) {// 当路线只有一个为已完成时，司机成功次数+1
                                if (carpoolUResultEntity.getD_success_count() == null) {
                                    carpoolUEntity.setD_success_count(1);
                                } else {
                                    carpoolUEntity.setD_success_count(carpoolUResultEntity.getD_success_count() + 1);
                                }
                                iCarpoolUserService.updateCarpoolUser(carpoolUEntity);
                            }
                        }
                    }

                    if ("h5".equals(paramDTO.getRequestBy())) {
                        return mapping.findForward("getPassengerRoute");
                    }
                } else {
                    if ("app".equals(paramDTO.getRequestBy())) {
                        // app操作
                        resultDTO.setReturnCode("-6031");
                        paramDTO.setErrFlg(true);
                    } else if ("h5".equals(paramDTO.getRequestBy())) {
                        // h5操作
                        return mapping.findForward("PassengerRouteUpdFailed");
                    }
                }

            } else {// 该路线不存在
                if ("app".equals(paramDTO.getRequestBy())) {
                    // app操作
                    resultDTO.setReturnCode("-6032");
                    paramDTO.setErrFlg(true);
                } else if ("h5".equals(paramDTO.getRequestBy())) {
                    // h5操作
                    return mapping.findForward("PassengerRouteUpdFailed");
                }
            }
        } catch (Exception e) {

            doErrorMessage(mapping, response, request, resultDTO, paramDTO, paramDTO.getRequestBy(), "更新状态", "-6033");
            if ("h5".equals(paramDTO.getRequestBy())) {
                return mapping.findForward("error");
            }
        }
        // 返回结果给客户端
        if ("app".equals(paramDTO.getRequestBy())) {
            if (!paramDTO.isErrFlg()) {
                resultDTO.setReturnCode("0");
            }
            toJson(response, resultDTO);
        }
        if ("h5".equals(paramDTO.getRequestBy())) {
            return mapping.findForward("getPassengerRoute");
        }

        return null;
    }

    // 更新搭车信息状态（上/下）车
    public void updateRouteStatus1(PassengerRouteDTO passengerRouteDTO) throws Exception {

        PassengerRouteDTO paramDTO = passengerRouteDTO;

        PassengerRouteEntity paramEntity = new PassengerRouteEntity();
        paramEntity.setPr_id(paramDTO.getPr_id());
        PassengerRouteEntity resultEntity = null;
        resultEntity = iPassengerRouteService.getObject(paramEntity);

        if (resultEntity != null) {
            // 更新路线状态
            PassengerRouteEntity paramPREntity = new PassengerRouteEntity();
            paramPREntity.setPr_id(paramDTO.getPr_id());
            paramPREntity.setState(paramDTO.getState());
            iPassengerRouteService.updatePassengerRoute(paramPREntity);

            // 乘客成功搭车次数加1
            CarpoolUserEntity carpoolUserEntity = new CarpoolUserEntity();
            CarpoolUserEntity carpoolUserResultEntity = null;
            carpoolUserEntity.setCu_id(resultEntity.getCu_id());
            carpoolUserResultEntity = iCarpoolUserService.getObject(carpoolUserEntity);
            if (carpoolUserResultEntity.getP_success_count() == null) {
                carpoolUserEntity.setP_success_count(1);
            } else {
                carpoolUserEntity.setP_success_count(carpoolUserResultEntity.getP_success_count() + 1);
            }
            iCarpoolUserService.updateCarpoolUser(carpoolUserEntity);

            // 当前司机路线下的所有乘客路线状态当且仅当只有一个为已完成时，司机成功次数加1
            CarpoolRouteEntity carpoolRouteEntity = new CarpoolRouteEntity();
            CarpoolRouteEntity resultCREntity = null;
            carpoolRouteEntity.setPr_id(paramDTO.getPr_id());
            resultCREntity = iCarpoolRouteService.getObject(carpoolRouteEntity);
            if (null != resultCREntity) {
                carpoolRouteEntity.setDr_id(resultCREntity.getDr_id());
                List<CarpoolRouteEntity> resultCRList = new ArrayList<CarpoolRouteEntity>();
                resultCRList = iCarpoolRouteService.getCarpoolRouteList(carpoolRouteEntity);
                if (null != resultCRList && resultCRList.size() > 0) {
                    int count = 0;
                    for (CarpoolRouteEntity result : resultCRList) {
                        PassengerRouteEntity prEntity = new PassengerRouteEntity();
                        prEntity.setPr_id(result.getPr_id());
                        PassengerRouteEntity resultPREntity = null;
                        resultPREntity = iPassengerRouteService.getObject(prEntity);
                        if (null != resultPREntity && "12".equals(resultPREntity.getState())) {
                            count++;
                        }

                    }
                    DriverRouteEntity driverRouteEntity = new DriverRouteEntity();
                    DriverRouteEntity resultDREntity = null;
                    driverRouteEntity.setDr_id(resultCREntity.getDr_id());
                    resultDREntity = iDriverRouteService.getObject(driverRouteEntity);
                    if (null != resultDREntity) {
                        CarpoolUserEntity carpoolUEntity = new CarpoolUserEntity();
                        CarpoolUserEntity carpoolUResultEntity = null;
                        carpoolUEntity.setCu_id(resultDREntity.getCu_id());
                        carpoolUResultEntity = iCarpoolUserService.getObject(carpoolUEntity);
                        if (count == 1) {
                            if (carpoolUResultEntity.getD_success_count() == null) {
                                carpoolUEntity.setD_success_count(1);
                            } else {
                                carpoolUEntity.setD_success_count(carpoolUResultEntity.getD_success_count() + 1);
                            }
                            iCarpoolUserService.updateCarpoolUser(carpoolUEntity);
                        }
                    }
                }
            } else {// 该路线不存在

            }
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
    public ActionForward changeToDriver(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        PassengerRouteDTO passengerRouteDTO = (PassengerRouteDTO) form;
        passengerRouteDTO.setStart_city(null);
        passengerRouteDTO.setEnd_city(null);
        passengerRouteDTO.setBegin_time(null);
        passengerRouteDTO.setPend_time(null);
        passengerRouteDTO.setCharter_flg(null);
        if ("h5".equals(passengerRouteDTO.getRequestBy())) {

            return mapping.findForward("searchError");
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
    public ActionForward deletePassengerRoute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // 参数DTO
        PassengerRouteDTO paramDTO = (PassengerRouteDTO) form;
        PassengerRouteDetailDTO resultDTO = new PassengerRouteDetailDTO();
        PassengerRouteEntity paramEntity = new PassengerRouteEntity();
        paramEntity.setPr_id(paramDTO.getPr_id());
        // 参数cu_id为空时，从session中获取cu_id
        if (null == paramDTO.getCu_id() || 0 == paramDTO.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            CarpoolUserDTO carpoolUserInfo = sessionManager.getCarpoolUserInfo();
            if (null == carpoolUserInfo) {
                return mapping.findForward("login");
            } else {
                paramDTO.setCu_id(carpoolUserInfo.getCu_id());
            }
        }
        // 定义返回结果变量resultPassengerRoute
        PassengerRouteEntity resultPassengerRoute = null;
        try {
            
            resultPassengerRoute = iPassengerRouteService.getObject(paramEntity);
            //pr_id是当前用户的pr_id
            if(null != resultPassengerRoute && null != resultPassengerRoute.getState()
                            && "0".equals(resultPassengerRoute.getState())
                            && null != paramDTO.getCu_id() 
                            && null != resultPassengerRoute.getCu_id() 
                            && paramDTO.getCu_id().equals(resultPassengerRoute.getCu_id())){
                PassengerRouteEntity passengerRouteEntity = new PassengerRouteEntity();
                passengerRouteEntity.setPr_id(paramEntity.getPr_id());
                iPassengerRouteService.deletePassengerRoute(passengerRouteEntity);
            }
        }catch(Exception e){
            doErrorMessage(mapping, response, request, resultDTO, paramDTO, paramDTO.getRequestBy(), "删除路线", "-6040");
            if ("h5".equals(paramDTO.getRequestBy())) {
                return mapping.findForward("login");
            }
        }
            
        // 返回结果给客户端
        if ("app".equals(paramDTO.getRequestBy())) {// app操作
            if (!paramDTO.isErrFlg()) {
                resultDTO.setReturnCode("0");
            }
            toJson(response, resultDTO);
        }
        if ("h5".equals(paramDTO.getRequestBy())) {// h5操作
            return mapping.findForward("getPassengerRoute");
        }
        
        return null;
        
    }
    

}
