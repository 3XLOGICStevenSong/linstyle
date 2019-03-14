package com.djb.highway.carpool.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.djb.highway.carpool.dto.CarpoolUserDTO;
import com.djb.highway.carpool.dto.DriverRouteDTO;
import com.djb.highway.carpool.dto.PassengerRouteDTO;
import com.djb.highway.carpool.dtoclient.CarpoolUserClientDTO;
import com.djb.highway.carpool.dtoclient.DriverClientDTO;
import com.djb.highway.carpool.dtoclient.DriverRouteDetailDTO;
import com.djb.highway.carpool.dtoclient.DriverRouteListDTO;
import com.djb.highway.carpool.dtoclient.PassengerRouteDetailDTO;

import com.djb.highway.carpool.dtoutil.DriverRouteDTOUtil;
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
import com.djb.highway.common.util.Constants;
import com.djb.highway.common.util.ResourceLocator;
import com.djb.highway.common.util.UploadUtil;
import com.djb.highway.framework.SessionManager;
import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.road.dtoclient.BaseClientDTO;

@Controller("/CarpoolDriver")
public class CarpoolDriverAction extends BaseAction {

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

    public CarpoolDriverAction() {
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
    public ActionForward getDriverRouteList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 参数DTO
        DriverRouteDTO driverRouteDTO = (DriverRouteDTO) form;

        // test
        // driverRouteDTO.setCu_id(266);
        // driverRouteDTO.setRequestBy("h5");
        // 参数Entity
        DriverRouteEntity paramEntity = new DriverRouteEntity();
        // 参数DTO->参数Entity
        // 参数DTO->参数Entity
        if (driverRouteDTO.getCu_id() == null || 0 == driverRouteDTO.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            if (sessionManager.getCarpoolUserInfo() == null) {
                return mapping.findForward("login");
            } else {
                driverRouteDTO.setCu_id(sessionManager.getCarpoolUserInfo().getCu_id());
            }
        }
        paramEntity.setCu_id(driverRouteDTO.getCu_id());
        // 结果DTO
        List<DriverRouteEntity> resultList = null;
        // app客户端DTO
        DriverRouteListDTO driverRouteListDTO = new DriverRouteListDTO();
        List<DriverRouteDetailDTO> driverRouteParamDTO = null;
        List<PassengerRouteDetailDTO> searchRouteDetailList = null;
        // h5客户端DTO
        List<DriverRouteDTO> driverRouteDTOResult = null;
        List<PassengerRouteDTO> paramEntityResult = null;
        // DB 操作
        try {
            // 检索司机路线和关联信息
            resultList = iDriverRouteService.getDriverRouteList(paramEntity);

            if (resultList != null && resultList.size() > 0) {

                if ("app".equals(driverRouteDTO.getRequestBy())) {
                    List<String> args = null;
                    driverRouteParamDTO = new ArrayList<DriverRouteDetailDTO>();

                    args = new ArrayList<String>();
                    for (DriverRouteEntity driverRouteEntity : resultList) {
                        DriverRouteDetailDTO detailParamDTO = new DriverRouteDetailDTO();
                        // 检索乘客路线和乘客信息
                        if (driverRouteEntity.getCarpoolRouteEntitys() != null && driverRouteEntity.getCarpoolRouteEntitys().size() > 0) {
                            PassengerRouteEntity passengerRouteResult = null;
                            DriverRouteDTOUtil.driverListBacktoAppClient(driverRouteEntity, detailParamDTO);
                            searchRouteDetailList = new ArrayList<PassengerRouteDetailDTO>();
                            for (CarpoolRouteEntity carpoolRouteEntity : driverRouteEntity.getCarpoolRouteEntitys()) {
                                PassengerRouteEntity passengerRouteEntity = new PassengerRouteEntity();
                                passengerRouteEntity.setPr_id(carpoolRouteEntity.getPr_id());
                                passengerRouteResult = iPassengerRouteService.getObject(passengerRouteEntity);
                                // 检索乘客路线Entity->APPClientDTO
                                if (passengerRouteResult != null) {
                                    PassengerRouteDetailDTO paramDetailDTO = new PassengerRouteDetailDTO();
                                    DriverRouteDTOUtil.getPassengerBacktoAppClient(passengerRouteResult, paramDetailDTO);
                                    args.add(passengerRouteResult.getState());
                                    searchRouteDetailList.add(paramDetailDTO);
                                }
                            }

                            detailParamDTO.setPassengerRouteList(searchRouteDetailList);

                        }
                        if (args != null && !"3".equals(detailParamDTO.getState_flg())) {
                            if (args.contains("12")) {
                                detailParamDTO.setState_flg("1");
                            } else if (args.contains("22")) {

                                int cnt = 0;
                                for (String s : args) {
                                    if ("22".equals(s) || "21".equals(s)) {
                                        cnt++;
                                    }
                                    // System.out.println("size"+args.size() +
                                    // "size");
                                    // System.out.println(cnt + "size");
                                    if (cnt == args.size()) {
                                        // System.out.println(args.size());
                                        // System.out.println(cnt);
                                        detailParamDTO.setState_flg("2");
                                    } else {
                                        if (args.contains("11") || args.contains("10")) {
                                            detailParamDTO.setState_flg("0");
                                        }
                                    }
                                }
                            } else if (args.contains("21")) {
                                int count = 0;
                                for (String s : args) {
                                    if ("21".equals(s) || "22".equals(s)) {
                                        count++;
                                    }
                                    if (count == args.size()) {
                                        detailParamDTO.setState_flg("2");
                                    } else {
                                        if (args.contains("11") || args.contains("10")) {
                                            detailParamDTO.setState_flg("0");
                                        }
                                    }
                                }
                            } else {
                                detailParamDTO.setState_flg("0");
                            }
                        }
                        // 司机路线Entity->APPClientDTO
                        driverRouteParamDTO.add(detailParamDTO);
                    }
                    driverRouteListDTO.setList(driverRouteParamDTO);

                } else if ("h5".equals(driverRouteDTO.getRequestBy())) {
                    driverRouteDTOResult = new ArrayList<DriverRouteDTO>();
                    List<String> args = null;
                    // 标识是否自动下车
                    String flg = "";
                    for (DriverRouteEntity driverRouteEntity : resultList) {
                        DriverRouteDTO driverParamDTO = new DriverRouteDTO();
                        //
                        DriverRouteDTOUtil.getPassengerBacktoH5Client(driverRouteEntity, driverParamDTO);
                        if (driverRouteEntity.getCarpoolRouteEntitys() != null && driverRouteEntity.getCarpoolRouteEntitys().size() > 0) {
                            PassengerRouteEntity passengerRouteResult = null;
                            PassengerRouteEntity paramRouteResult = null;
                            paramEntityResult = new ArrayList<PassengerRouteDTO>();
                            // 获取乘客路线和乘客信息
                            args = new ArrayList<String>();
                            for (CarpoolRouteEntity carpoolRouteEntity : driverRouteEntity.getCarpoolRouteEntitys()) {
                                PassengerRouteEntity passengerRouteEntity = new PassengerRouteEntity();
                                passengerRouteEntity.setPr_id(carpoolRouteEntity.getPr_id());
                                paramRouteResult = iPassengerRouteService.getObject(passengerRouteEntity);
                                // 如果状态是已上车24小时后自动完成
                                if (paramRouteResult != null) {
                                    if ("11".equals(paramRouteResult.getState())) {
                                        Calendar ca = Calendar.getInstance();
                                        ca.setTime(paramRouteResult.getStart_time());
                                        ca.add(Calendar.HOUR, +24);
                                        Date date = new Date();
                                        if (ca.getTime().before(date)) {
                                            paramRouteResult.setState("12");
                                            iPassengerRouteService.updatePassengerRoute(paramRouteResult);
                                            flg = "1";
                                            // 更新乘客的成功搭车次数
                                            CarpoolUserEntity userEntity = new CarpoolUserEntity();
                                            CarpoolUserEntity userResultEntity = null;
                                            // 更新乘客
                                            userEntity.setCu_id(passengerRouteEntity.getCu_id());
                                            userResultEntity = iCarpoolUserService.getObject(userEntity);
                                            if (userResultEntity != null) {
                                                userEntity.setP_success_count(userResultEntity.getP_success_count() + 1);
                                                iCarpoolUserService.updateCarpoolUser(userEntity);
                                            }
                                        }
                                    }
                                }
                                passengerRouteResult = iPassengerRouteService.getObject(passengerRouteEntity);
                                // 给H5客户端赋值
                                if (passengerRouteResult != null) {
                                    //
                                    PassengerRouteDTO passengerRouteResultDTO = new PassengerRouteDTO();
                                    PassengerRouteDTOUtil.doDriverBacktoH5Client(passengerRouteResult, passengerRouteResultDTO);
                                    args.add(passengerRouteResult.getState());
                                    paramEntityResult.add(passengerRouteResultDTO);
                                }

                            }
                            driverParamDTO.setPassengerRouteDTOs(paramEntityResult);
                        }
                        // 判断司机路线的状态过期时间是当天00:00分以前
                        Date date1 = new Date();
                        // Calendar ca = Calendar.getInstance();
                        String str = CommonUtil.dateToString(date1, "yyyy-MM-dd HH:mm");
                        str = str.substring(0, 11) + "00:00";
                        Date date = CommonUtil.stringToDate(str, "yyyy-MM-dd HH:mm");

                        // Date date2 =
                        // CommonUtil.addOneDay(driverRouteEntity.getEnd_time());
                       // System.out.println(driverRouteEntity.getEnd_time());
                        if (driverRouteEntity.getEnd_time().before(date)) {
                           // System.out.println("111111");
                            if (args != null) {
                                // 如果包含一个已下车状态就是已完成
                                if (args.contains("12")) {
                                    driverParamDTO.setState_flg("1");
                                } else if (args.contains("22")) {
                                    // 都取消才是取消
                                    int cnt = 0;
                                    for (String s : args) {
                                        if ("22".equals(s) || "21".equals(s)) {
                                            cnt++;
                                        }
                                    }
                                    if (cnt == args.size()) {
                                        driverParamDTO.setState_flg("2");
                                    }
                                } else if (args.contains("21")) {
                                    int count = 0;
                                    for (String s : args) {
                                        if ("21".equals(s) || "22".equals(s)) {
                                            count++;
                                        }
                                    }
                                    if (count == args.size()) {
                                        driverParamDTO.setState_flg("2");
                                    }
                                } else if (args.contains("10") && !args.contains("12")) {
                                   // System.out.println("已过期");
                                    driverParamDTO.setState_flg("3");
                                }
                            }
                        } else {
                           // System.out.println("没过时间");
                            if (args != null) {
                                if(args.contains("12")){
                                    driverParamDTO.setState_flg("1");
                                }
                            else if (args.contains("10") || args.contains("11")) {
                                    driverParamDTO.setState_flg("0");
                                } else if (args.contains("22")) {
                                    // 都取消才是取消
                                    int cnt = 0;
                                    for (String s : args) {
                                        if ("22".equals(s) || "21".equals(s)) {
                                            cnt++;
                                        }
                                    }
                                    if (cnt == args.size()) {
                                        driverParamDTO.setState_flg("2");
                                    }
                                } else if (args.contains("21")) {
                                    int count = 0;
                                    for (String s : args) {
                                        if ("21".equals(s) || "22".equals(s)) {
                                            count++;
                                        }
                                    }
                                    if (count == args.size()) {
                                        driverParamDTO.setState_flg("2");
                                    }
                                } 
                            }
                        }
                        driverRouteDTOResult.add(driverParamDTO);
                    }
                    driverRouteDTO.setList(driverRouteDTOResult);
                    // 修改司机成功搭车次数
                    if ("1".equals(flg)) {
                        CarpoolUserEntity userEntity = new CarpoolUserEntity();
                        userEntity.setCu_id(driverRouteDTO.getCu_id());
                        CarpoolUserEntity userResultEntity = null;
                        userResultEntity = iCarpoolUserService.getObject(userEntity);
                        if (userResultEntity != null) {
                            userEntity.setD_success_count(userResultEntity.getD_success_count() + 1);
                            iCarpoolUserService.updateCarpoolUser(userEntity);
                        }
                    }

                }

            }
        } catch (Exception e) {

            doErrorMessage(mapping, response, request, driverRouteListDTO, driverRouteDTO, driverRouteDTO.getRequestBy(), "创建路线", "-6003");
            if ("h5".equals(driverRouteDTO.getRequestBy())) {
                if ("validRoute".equals(driverRouteDTO.getScreenId())) {
                    driverRouteDTO.setReturnCode("0");
                    return mapping.findForward("getValidInfo");
                }else if("routeList".equals(driverRouteDTO.getScreenId())){
                    driverRouteDTO.setReturnCode("1");
                    return mapping.findForward("getValidInfo");
                }
                else {
                    return mapping.findForward("getUserInfo");
                }

            }
        }
        if (!driverRouteDTO.isErrFlg()) {
            driverRouteListDTO.setReturnCode("0");
        }
        // 返回结果给客户端
        if ("app".equals(driverRouteDTO.getRequestBy())) {
            toJson(response, driverRouteListDTO);

        }
        if ("h5".equals(driverRouteDTO.getRequestBy())) {
            if ("validRoute".equals(driverRouteDTO.getScreenId())) {
                driverRouteDTO.setReturnCode("0");

            }else if("routeList".equals(driverRouteDTO.getScreenId())){
                driverRouteDTO.setReturnCode("1");           
            }
            return mapping.findForward("getDriverRoute");
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
    public ActionForward createDriverRoute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        DriverRouteDTO driverRouteDTO = (DriverRouteDTO) form;
        // Test
        // driverRouteDTO.setCu_id(266);
        // driverRouteDTO.setRequestBy("app");
        // driverRouteDTO.setPr_id(17);
        // 参数Entity
        DriverRouteEntity paramEntity = new DriverRouteEntity();

        PassengerRouteEntity paramPassengerEntity = new PassengerRouteEntity();
        // 结果Entity
        PassengerRouteEntity resultPassengerEntity = null;

        // 参数DTO -> 参数Entity
        // CommonUtil.reflectClass(driverRouteDTO, paramEntity);

        if (driverRouteDTO.getCu_id() == null || 0 == driverRouteDTO.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            if (sessionManager.getCarpoolUserInfo() == null) {
                return mapping.findForward("login");
            } else {
                driverRouteDTO.setCu_id(sessionManager.getCarpoolUserInfo().getCu_id());
            }
        }
        paramEntity.setCu_id(driverRouteDTO.getCu_id());

        paramPassengerEntity.setPr_id(driverRouteDTO.getPr_id());
        // APPClientDTO
        DriverRouteDetailDTO resultAppClientDTO = new DriverRouteDetailDTO();

        // DB操作
        try {
            resultPassengerEntity = iPassengerRouteService.getObject(paramPassengerEntity);
            if (resultPassengerEntity != null && "0".equals(resultPassengerEntity.getState())) {
                // 赋值操作
                paramEntity.setStart_city(resultPassengerEntity.getStart_city());
                paramEntity.setEnd_city(resultPassengerEntity.getEnd_city());
                paramEntity.setStart_time(resultPassengerEntity.getStart_time());
                paramEntity.setEnd_time(resultPassengerEntity.getStart_time());
                // paramEntity.setEnd_time(resultPassengerEntity.getStart_time());
                paramEntity.setCharter_flg(resultPassengerEntity.getCharter_flg());
                Date date = new Date();
                paramEntity.setDr_insert_time(date);
                paramEntity.setTotal_num(resultPassengerEntity.getPeople_count());
                paramEntity.setTotal_price(resultPassengerEntity.getPrice());
                // 创建司机路线
                iDriverRouteService.addDriverRoute(paramEntity);
                DriverRouteEntity paramResult = new DriverRouteEntity();
                CommonUtil.reflectClass(paramEntity, paramResult);
                DriverRouteEntity paramDriverEntity = null;
                // 获取刚创建的司机路线ID
                paramDriverEntity = iDriverRouteService.getObject(paramResult);
                if (paramDriverEntity != null) {
                    if ("app".equals(driverRouteDTO.getRequestBy())) {
                        resultAppClientDTO.setDriver_route_id(paramDriverEntity.getDr_id());
                    } else if ("h5".equals(driverRouteDTO.getRequestBy())) {
                        driverRouteDTO.setDr_id(paramDriverEntity.getDr_id());
                    }
                    CarpoolRouteEntity CarpoolRouteEntity = new CarpoolRouteEntity();
                    CarpoolRouteEntity.setPr_id(driverRouteDTO.getPr_id());
                    CarpoolRouteEntity.setDr_id(paramDriverEntity.getDr_id());
                    CarpoolRouteEntity.setInsert_time(new Date());
                    iCarpoolRouteService.addCarpoolRoute(CarpoolRouteEntity);
                }
                // 更改乘客路线状态
                PassengerRouteEntity paramRoute = new PassengerRouteEntity();
                paramRoute.setPr_id(driverRouteDTO.getPr_id());
                paramRoute.setState("10");
                paramRoute.setPr_update_time(new Date());
                iPassengerRouteService.updatePassengerRoute(paramRoute);
            } else {         
                doErrorMessage(mapping, response, request, resultAppClientDTO, driverRouteDTO, driverRouteDTO.getRequestBy(), "创建路线", "-6003");
                if ("h5".equals(driverRouteDTO.getRequestBy())) {
                    return mapping.findForward("createError");
                }
            }

        } catch (Exception e) {
            doErrorMessage(mapping, response, request, resultAppClientDTO, driverRouteDTO, driverRouteDTO.getRequestBy(), "创建路线", "-6003");
            if ("h5".equals(driverRouteDTO.getRequestBy())) {
                return mapping.findForward("createError");
            }
        }

        if (!driverRouteDTO.isErrFlg()) {
            resultAppClientDTO.setReturnCode("0");
        }

        // 返回结果给客户端
        if ("app".equals(driverRouteDTO.getRequestBy())) {
            toJson(response, resultAppClientDTO);

        }
        if ("h5".equals(driverRouteDTO.getRequestBy())) {

            return (mapping.findForward("joinSuccess"));
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

    public ActionForward joinDriverRoute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 参数DTO
        DriverRouteDTO driverRouteDTO = (DriverRouteDTO) form;

        // test
        // driverRouteDTO.setCu_id(285);
        // driverRouteDTO.setPr_id(24);
        // driverRouteDTO.setDr_id(33);
        // driverRouteDTO.setRequestBy("app");
        // 参数Entity
        DriverRouteEntity paramEntity = new DriverRouteEntity();
        PassengerRouteEntity paramPassengerEntity = new PassengerRouteEntity();
        CarpoolRouteEntity paramCarpoolEntity = new CarpoolRouteEntity();

        // 参数DTO->参数Entity
        if (driverRouteDTO.getCu_id() == null || 0 == driverRouteDTO.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            if (sessionManager.getCarpoolUserInfo() == null) {
                return mapping.findForward("login");
            } else {
                driverRouteDTO.setCu_id(sessionManager.getCarpoolUserInfo().getCu_id());
            }
        }
        paramEntity.setDr_id(driverRouteDTO.getDr_id());
        paramPassengerEntity.setPr_id(driverRouteDTO.getPr_id());
        // 结果Entity
        DriverRouteEntity resultEntity = null;
        PassengerRouteEntity resultPassenger = null;
        // AppClientDTO
        DriverRouteDetailDTO driverClientDTO = new DriverRouteDetailDTO();
        try {
            Date date1 = null;
            Date date2 = null;
            // 乘客路线
            resultPassenger = iPassengerRouteService.getObject(paramPassengerEntity);

            if (resultPassenger != null) {
                date1 = resultPassenger.getStart_time();

            }
            // 司机路线
            resultEntity = iDriverRouteService.getObject(paramEntity);
            if (resultEntity != null) {
                date2 = resultEntity.getStart_time();
                if (date1 != null && date2 != null) {
                    if (date1.before(date2)) {
                        paramEntity.setStart_time(date1);
                    }
                } else {
                    paramEntity.setStart_time(date1);
                }
                if (date1 != null && resultEntity.getEnd_time() != null) {
                    if (date1.after(resultEntity.getEnd_time())) {
                        paramEntity.setEnd_time(date1);
                    }
                } else {
                    paramEntity.setEnd_time(date1);
                }

                CarpoolUserEntity paramUserEntity = new CarpoolUserEntity();
                CarpoolUserEntity paramUserResult = null;
                paramUserEntity.setCu_id(driverRouteDTO.getCu_id());
                paramUserResult = iCarpoolUserService.getObject(paramUserEntity);
                int count_num = resultPassenger.getPeople_count() + resultEntity.getTotal_num();
                if (paramUserResult != null && paramUserResult.getCar_seat_num() != null) {
                    if (count_num <= paramUserResult.getCar_seat_num()) {
                        paramEntity.setTotal_num(resultPassenger.getPeople_count() + resultEntity.getTotal_num());
                    } else {
                        // 人数超过了数量
                        doErrorMessage(mapping, response, request, driverClientDTO, driverRouteDTO, driverRouteDTO.getRequestBy(), "搭乘人数超出", "-6200");
                        if ("h5".equals(driverRouteDTO.getRequestBy())) {
                            return mapping.findForward("getValidInfo");
                        }
                    }
                }
            }
            // 乘客路线状态为0，司机路线不包车,加入路线
            if (resultPassenger != null && "0".equals(resultPassenger.getState()) && !"1".equals(resultPassenger.getCharter_flg()) && resultEntity != null
                            && !"1".equals(resultEntity.getCharter_flg())) {
                // 更新司机路线
                iDriverRouteService.updateDriverRoute(paramEntity);
                // 加入路线
                paramCarpoolEntity.setPr_id(driverRouteDTO.getPr_id());
                paramCarpoolEntity.setDr_id(driverRouteDTO.getDr_id());
                paramCarpoolEntity.setInsert_time(new Date());
                iCarpoolRouteService.addCarpoolRoute(paramCarpoolEntity);
                // 更新乘客路线状态
                resultPassenger.setState("10");
                iPassengerRouteService.updatePassengerRoute(resultPassenger);
            } else {
                ActionErrors errors = new ActionErrors();
                if(!"0".equals(resultPassenger.getState())){
                    doErrorMessage(mapping, response, request, driverClientDTO, driverRouteDTO, driverRouteDTO.getRequestBy(), "加入路线", "-6201");
                    if ("h5".equals(driverRouteDTO.getRequestBy())) {
                        return mapping.findForward("getValidInfo");
              }else{
               
                 String errorMessage = "不能加入包车路线";
                errors.add("errorMessage", new ActionMessage("errors.isOperat", new Object[] { errorMessage }));
                this.saveErrors(request, errors);
                //doErrorMessage(mapping, response, request, driverClientDTO, driverRouteDTO, driverRouteDTO.getRequestBy(), "不能加入包车的路线", "-6201");
                if ("h5".equals(driverRouteDTO.getRequestBy())) {
                    return mapping.findForward("getValidInfo");
            }}
        }}} catch (Exception e) {
            doErrorMessage(mapping, response, request, driverClientDTO, driverRouteDTO, driverRouteDTO.getRequestBy(), "加入路线", "-6004");
            if ("h5".equals(driverRouteDTO.getRequestBy())) {
                return mapping.findForward("getValidInfo");
            }

        }
        if (!driverRouteDTO.isErrFlg()) {
            driverClientDTO.setReturnCode("0");
        }
        // 返回结果给客户端
        if ("app".equals(driverRouteDTO.getRequestBy())) {
            toJson(response, driverClientDTO);

        }
        if ("h5".equals(driverRouteDTO.getRequestBy())) {
            return mapping.findForward("joinSuccess");
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

    public ActionForward cancelDriverRoute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 参数DTO
        DriverRouteDTO driverRouteDTO = (DriverRouteDTO) form;

        // test
        // driverRouteDTO.setCu_id(266);
        // driverRouteDTO.setPr_id(19);
        // driverRouteDTO.setDr_id(33);
        // driverRouteDTO.setRequestBy("app");
        // 参数Entity
        DriverRouteEntity paramEntity = new DriverRouteEntity();
        PassengerRouteEntity paramPassengerEntity = new PassengerRouteEntity();
        CarpoolRouteEntity paramCarpoolEntity = new CarpoolRouteEntity();
        // 结果Entity
        DriverRouteEntity resultEntity = null;
        PassengerRouteEntity resultPassenger = null;
        List<CarpoolRouteEntity> resultList = null;
        // 是否登录，获取userID
        if (driverRouteDTO.getCu_id() == null || 0 == driverRouteDTO.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            if (sessionManager.getCarpoolUserInfo() == null) {
                return mapping.findForward("login");
            } else {
                driverRouteDTO.setCu_id(sessionManager.getCarpoolUserInfo().getCu_id());
            }
        }
        // APPClientDTO
        DriverRouteDetailDTO driverClientDTO = new DriverRouteDetailDTO();
        try {
            // 获取乘客路线的状态
            PassengerRouteEntity paramTemEntity = new PassengerRouteEntity();
            paramTemEntity.setPr_id(driverRouteDTO.getPr_id());
            paramTemEntity = iPassengerRouteService.getObject(paramTemEntity);
            if ("10".equals(paramTemEntity.getState())) {
                // 更新乘客路线状态
                paramPassengerEntity.setPr_id(driverRouteDTO.getPr_id());
                paramPassengerEntity.setState(driverRouteDTO.getState());
                paramPassengerEntity.setState("22");
                paramPassengerEntity.setPr_update_time(new Date());
                iPassengerRouteService.updatePassengerRoute(paramPassengerEntity);
                // 新创建一个乘客路线
                PassengerRouteEntity passengerRouteEntity = new PassengerRouteEntity();
                paramPassengerEntity.setPr_id(driverRouteDTO.getPr_id());
                resultPassenger = iPassengerRouteService.getObject(paramPassengerEntity);
                Date max = null;
                Date min = null;
                if (resultPassenger != null) {
                    CommonUtil.reflectClass(resultPassenger, passengerRouteEntity);
                    passengerRouteEntity.setPr_insert_time(new Date());
                    passengerRouteEntity.setPrice((float) resultPassenger.getPrice());
                    passengerRouteEntity.setPr_update_time(null);
                    passengerRouteEntity.setPr_id(null);
                    passengerRouteEntity.setState("0");
                    iPassengerRouteService.addPassengerRoute(passengerRouteEntity);
                    // 修改司机路线时间和搭乘人数
                    paramEntity.setDr_id(driverRouteDTO.getDr_id());
                    resultEntity = iDriverRouteService.getObject(paramEntity);

                    // 开始时间和结束时间都不等于乘客路线的时间 ，则只修改人数
                    if (!resultPassenger.getStart_time().equals(resultEntity.getStart_time())
                                    && !resultPassenger.getStart_time().equals(resultEntity.getEnd_time())) {
                        paramEntity.setTotal_num(resultEntity.getTotal_num() - resultPassenger.getPeople_count());
                    } else {

                        paramCarpoolEntity.setDr_id(driverRouteDTO.getDr_id());
                        resultList = iCarpoolRouteService.getCarpoolRouteList(paramCarpoolEntity);
                        // 该路线在关联表中的记录如果=1时间不变，该路线已经被取消，如果该路线的记录大于1则选取路线不是该乘客路线的时间的最大值和
                        // 和最小值作为出发时间最大值和出发时间最小值
                        if (resultList != null && resultList.size() > 1) {
                            for (CarpoolRouteEntity param : resultList) {
                                if (param.getPr_id() != driverRouteDTO.getPr_id()) {
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
                        paramEntity.setTotal_num(resultEntity.getTotal_num() - resultPassenger.getPeople_count());
                    }

                    iDriverRouteService.updateDriverRoute(paramEntity);

                }
            } else {

                doErrorMessage(mapping, response, request, driverClientDTO, driverRouteDTO, driverRouteDTO.getRequestBy(), "取消路线", "-6005");
                if ("h5".equals(driverRouteDTO.getRequestBy())) {
                    driverRouteDTO.setReturnCode("1");
                    return mapping.findForward("joinSuccess");

                }

            }

        } catch (Exception e) {

            doErrorMessage(mapping, response, request, driverClientDTO, driverRouteDTO, driverRouteDTO.getRequestBy(), "取消路线", "-6005");
            if ("h5".equals(driverRouteDTO.getRequestBy())) {
                return mapping.findForward("joinSuccess");
                // driverRouteDTO.setResult("0");
                // this.cancelByAjax(request, response, driverRouteDTO);
                // return null;
            }

        }
        if (!driverRouteDTO.isErrFlg()) {
            driverClientDTO.setReturnCode("0");
        }
        // 返回结果给客户端

        if ("app".equals(driverRouteDTO.getRequestBy())) {
            toJson(response, driverClientDTO);

        }
        if ("h5".equals(driverRouteDTO.getRequestBy())) {
            // driverRouteDTO.setResult("1");
            // this.cancelByAjax(request, response, driverRouteDTO);
            // return null;
            return mapping.findForward("joinSuccess");
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
    public ActionForward getValidRoute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 参数DTO
        DriverRouteDTO driverRouteDTO = (DriverRouteDTO) form;

        // test
        // driverRouteDTO.setCu_id(266);
        // driverRouteDTO.setPr_id(17);
        // driverRouteDTO.setRequestBy("app");
        // 参数Entity
        DriverRouteEntity paramEntity = new DriverRouteEntity();
        // 参数DTO->参数Entity

        if (driverRouteDTO.getCu_id() == null || 0 == driverRouteDTO.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            if (sessionManager.getCarpoolUserInfo() == null) {
                return mapping.findForward("login");
            } else {
                System.out.println(sessionManager.getCarpoolUserInfo().getCu_id());
                driverRouteDTO.setCu_id(sessionManager.getCarpoolUserInfo().getCu_id());
            }
        }
        paramEntity.setCu_id(driverRouteDTO.getCu_id());
        if ("h5".equals(driverRouteDTO.getRequestBy())) {
            CarpoolUserEntity userEntity = new CarpoolUserEntity();
            userEntity.setCu_id(paramEntity.getCu_id());
            CarpoolUserEntity userResultEntity = null;
            userResultEntity = iCarpoolUserService.getObject(userEntity);
            if (userResultEntity != null) {
                if (userResultEntity.getCar_num() == null || "".equals(userResultEntity.getCar_num())) {
                    return mapping.findForward("verfDriver");
                }
            }

        }
        // 结果DTO
        List<DriverRouteEntity> resultList = null;
        // app客户端DTO
        DriverRouteListDTO driverRouteListDTO = new DriverRouteListDTO();
        List<DriverRouteDetailDTO> driverRouteParamDTO = null;
        // h5客户端DTO
        List<DriverRouteDTO> driverRouteDTOResult = null;

        // DB 操作
        try {
            // 获取司机车位数
            CarpoolUserEntity userEntity = new CarpoolUserEntity();
            userEntity.setCu_id(driverRouteDTO.getCu_id());
            userEntity = iCarpoolUserService.getObject(userEntity);
            if (userEntity != null) {
                driverRouteDTO.setSeat_num(userEntity.getCar_seat_num());
            }
            // 获取乘客路线开始时间一小时以内的司机路线
            PassengerRouteEntity passengerRouteEntity = new PassengerRouteEntity();
            PassengerRouteEntity resultRouteEntity = null;
            passengerRouteEntity.setPr_id(driverRouteDTO.getPr_id());
            resultRouteEntity = iPassengerRouteService.getObject(passengerRouteEntity);
            // 获取司机路线和乘客路线相匹配的路线（一小时内的）
            if (resultRouteEntity != null) {
                Date time = resultRouteEntity.getStart_time();
                Calendar cl = Calendar.getInstance();
                cl.setTime(time);
                cl.add(Calendar.MINUTE, -30);
                paramEntity.setFrom_time(cl.getTime());
                Calendar c2 = Calendar.getInstance();
                c2.setTime(time);
                c2.add(Calendar.MINUTE, 30);
                paramEntity.setTo_time(c2.getTime());
                paramEntity.setStart_city(resultRouteEntity.getStart_city());
                paramEntity.setEnd_city(resultRouteEntity.getEnd_city());
                // 检索司机路线和关联信息
                resultList = iDriverRouteService.getValidList(paramEntity);
                if (resultList != null && resultList.size() > 0) {
                    if ("app".equals(driverRouteDTO.getRequestBy())) {
                        driverRouteParamDTO = new ArrayList<DriverRouteDetailDTO>();
                        for (DriverRouteEntity driverRouteEntity : resultList) {
                            DriverRouteDetailDTO detailParamDTO = new DriverRouteDetailDTO();
                            detailParamDTO.setStartcity(driverRouteEntity.getStart_city());
                            detailParamDTO.setEndcity(driverRouteEntity.getEnd_city());
                            detailParamDTO.setDriver_route_id(driverRouteEntity.getDr_id());
                            if (driverRouteEntity.getStart_time() != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
                                detailParamDTO.setStarttime(sdf.format(driverRouteEntity.getStart_time()));
                            }
                            driverRouteParamDTO.add(detailParamDTO);
                        }
                        driverRouteListDTO.setList(driverRouteParamDTO);
                    } else if ("h5".equals(driverRouteDTO.getRequestBy())) {
                        driverRouteDTOResult = new ArrayList<DriverRouteDTO>();
                        for (DriverRouteEntity driverRouteEntity : resultList) {
                            DriverRouteDTO driverParamDTO = new DriverRouteDTO();
                            driverParamDTO.setStart_city(driverRouteEntity.getStart_city());
                            driverParamDTO.setEnd_city(driverRouteEntity.getEnd_city());
                            driverParamDTO.setDr_id(driverRouteEntity.getDr_id());
                            if (driverRouteDTO.getSeat_num() != null) {
                                driverParamDTO.setSup_num(driverRouteDTO.getSeat_num() - driverRouteEntity.getTotal_num());
                            }
                            if (driverRouteEntity.getStart_time() != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
                                driverParamDTO.setDriverGo_time(sdf.format(driverRouteEntity.getStart_time()));
                            }
                            if (driverParamDTO.getSup_num() > 0) {
                                driverRouteDTOResult.add(driverParamDTO);
                            }
                        }
                        if (driverRouteDTOResult.size() > 0) {
                            driverRouteDTO.setList(driverRouteDTOResult);
                        }
                        driverRouteDTO.setStart_city(resultRouteEntity.getStart_city());
                        driverRouteDTO.setEnd_city(resultRouteEntity.getEnd_city());
                        driverRouteDTO.setGo_time(CommonUtil.dateToString(resultRouteEntity.getStart_time(), "MM月dd日 HH:mm"));
                        driverRouteDTO.setPass_num(resultRouteEntity.getPeople_count());
                    }

                } else {
                    if ("h5".equals(driverRouteDTO.getRequestBy())) {
                        driverRouteDTO.setStart_city(resultRouteEntity.getStart_city());
                        driverRouteDTO.setEnd_city(resultRouteEntity.getEnd_city());
                        driverRouteDTO.setGo_time(CommonUtil.dateToString(resultRouteEntity.getStart_time(), "MM月dd日 HH:mm"));
                        driverRouteDTO.setPass_num(resultRouteEntity.getPeople_count());
                    }
                }
            }
        } catch (Exception e) {

            doErrorMessage(mapping, response, request, driverRouteListDTO, driverRouteDTO, driverRouteDTO.getRequestBy(), "创建路线", "-6003");
            if ("h5".equals(driverRouteDTO.getRequestBy())) {
                return mapping.findForward("error");
            }

        }
        if (!driverRouteDTO.isErrFlg()) {
            driverRouteListDTO.setReturnCode("0");
        }
        // 返回结果给客户端

        if ("app".equals(driverRouteDTO.getRequestBy())) {
            toJson(response, driverRouteListDTO);

        }
        if ("h5".equals(driverRouteDTO.getRequestBy())) {
            // this.getRouteByAjax(request, response, driverRouteDTO);
            // return null;
            return mapping.findForward("validRoute");
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
    public ActionForward nextVerfDriver(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 参数DTO
        DriverRouteDTO driverRouteDTO = (DriverRouteDTO) form;
        return mapping.findForward("validInfoError");
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
    public ActionForward backToVerfDriver(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 参数DTO
        DriverRouteDTO driverRouteDTO = (DriverRouteDTO) form;
        return mapping.findForward("verfDriver");
       
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
    public ActionForward driverVerifInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 参数DTO
        DriverRouteDTO driverRouteDTO = (DriverRouteDTO) form;
        CarpoolUserEntity paramEntity = new CarpoolUserEntity();

        DriverClientDTO paramClient = new DriverClientDTO();
        if (driverRouteDTO.getCu_id() == null || 0 == driverRouteDTO.getCu_id()) {
            HttpSession session = request.getSession();
            SessionManager sessionManager = new SessionManager(session);
            CarpoolUserDTO carpoolUserInfo = sessionManager.getCarpoolUserInfo();
            if (carpoolUserInfo == null) {
                return mapping.findForward("login");
            } else {
                driverRouteDTO.setCu_id(carpoolUserInfo.getCu_id());
            }
        }
        try {
            paramEntity.setCu_id(driverRouteDTO.getCu_id());
            // CommonUtil.reflectClass(paramDTO, paramEntity);
            paramEntity.setStatus_flg("1");// 默认认证成功
//            if (driverRouteDTO.getCar_license_pic() != null && driverRouteDTO.getCar_license_pic().getFileSize() > 0) {
//                String drivingFiledPath1 = UploadUtil.doUpload(request, response, driverRouteDTO.getCar_license_pic(), paramEntity.getCu_id());
//                String drivingFiledPath = UploadUtil.getSmallFileName(drivingFiledPath1);
//                paramEntity.setCar_license_pic(ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS) + drivingFiledPath);
//            }
//
//            if (driverRouteDTO.getPeople_license() != null && driverRouteDTO.getPeople_license().getFileSize() > 0) {
//                String drivingFiledPath1 = UploadUtil.doUpload(request, response, driverRouteDTO.getPeople_license(), paramEntity.getCu_id());
//                String drivingFiledPath = UploadUtil.getSmallFileName(drivingFiledPath1);
//                paramEntity.setPeople_license(ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS) + drivingFiledPath);
//            }
//            if (driverRouteDTO.getDriving_license_pic() != null && driverRouteDTO.getDriving_license_pic().getFileSize() > 0) {
//                String drivingFiledPath1 = UploadUtil.doUpload(request, response, driverRouteDTO.getDriving_license_pic(), paramEntity.getCu_id());
//                String drivingFiledPath = UploadUtil.getSmallFileName(drivingFiledPath1);
//                paramEntity.setDriving_license_pic(ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS) + drivingFiledPath);
//            }
            paramEntity.setCar_license_pic(driverRouteDTO.getCar_license_pic());
            paramEntity.setCu_name(driverRouteDTO.getCu_name());
            paramEntity.setCar_num(driverRouteDTO.getCar_num());
            paramEntity.setCar_seat_num(driverRouteDTO.getCar_seat_num() - 1);
            paramEntity.setCar_type(driverRouteDTO.getCar_type());
            paramEntity.setId_number(driverRouteDTO.getId_number());
            iCarpoolUserService.updateCarpoolUser(paramEntity);
        } catch (Exception e) {
            if ("app".equals(driverRouteDTO.getRequestBy())) {
                paramClient.setReturnCode("-10003");
                driverRouteDTO.setErrFlg(true);
            } else {
                driverRouteDTO = null;
                ActionErrors errors = new ActionErrors();
                String errorMessage = "验证司机信息";
                errors.add("errorMessage", new ActionMessage("errors.isFailed", new Object[] { errorMessage }));
                this.saveErrors(request, errors);
                return mapping.findForward("validInfoError");
            }
        }
        if ("app".equals(driverRouteDTO.getRequestBy())) {
            // app操作
            if (!driverRouteDTO.isErrFlg()) {
                paramClient.setReturnCode("0");
            }
            toJson(response, paramClient);
        } else if ("h5".equals(driverRouteDTO.getRequestBy())) {
            // html5操作
            return mapping.findForward("getValidInfo");
        }
        return null;
    }

    private void cancelByAjax(HttpServletRequest req, HttpServletResponse resp, DriverRouteDTO driverRouteDTO) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/xml;charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        PrintWriter out = resp.getWriter();
        out.println("<response>");
        out.println("<result>" + driverRouteDTO.getResult() + "</result>");
        // 行号
        out.println("<rowIndex>" + driverRouteDTO.getRowIndex() + "</rowIndex>");
        // 外层行号
        out.println("<outerIndex>" + driverRouteDTO.getOuterIndex() + "</outerIndex>");
        // out.println()
        out.println("</response>");
        out.close();
    }

    private void getRouteByAjax(HttpServletRequest req, HttpServletResponse resp, DriverRouteDTO driverRouteDTO) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/xml;charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        PrintWriter out = resp.getWriter();

        String passaengerRoute = new String();

        // passaengerRoute="<li><section class="top1"style="float:left;border:none;font-size:14px"><p style="font-size:14px;font-weight:700"><font style="color:#404040">出发时间：</font>${CarpoolDriverForm.go_time} <font style="color:#404040;text-align:right">（剩余车位：${CarpoolDriverForm.seat_num}）</font></p><font class="start">起</font>${CarpoolDriverForm.start_city}<span class="icon-to"></span><font class="end">终</font>${CarpoolDriverForm.end_city}<font style="color:#0086ff;"> </font></section> <a href="#" style="position:absolute;right:7px;padding:2px
        // 10px;background:#0086ff;border-radius:5px;color:#fff;margin-top:30px"onClick="doButtonSubmit(document.forms[0],
        // 'createDriverRoute', 'submitField');">新建</a></li>";
        out.println("<response>");
        out.println("<result>" + driverRouteDTO.getResult() + "</result>");
        // 行号
        out.println("<rowIndex>" + driverRouteDTO.getRowIndex() + "</rowIndex>");
        // 外层行号
        out.println("<outerIndex>" + driverRouteDTO.getOuterIndex() + "</outerIndex>");
        // out.println()
        out.println("</response>");
        out.close();
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
    public void doErrorMessage(ActionMapping mapping, HttpServletResponse response, HttpServletRequest request, BaseClientDTO driverClientDTO,
                    DriverRouteDTO driverRouteDTO, String requestBy, String errorMessage, String returnCode) throws Exception {
        if ("app".equals(requestBy)) {
            driverClientDTO.setReturnCode(returnCode);
            driverRouteDTO.setErrFlg(true);
        } else {
            ActionErrors errors = new ActionErrors();
            // String errorMessage = "加入路线";
            errors.add("errorMessage", new ActionMessage("errors.isFailed", new Object[] { errorMessage }));
            this.saveErrors(request, errors);
            // return mapping.findForward("error");
        }
    }
}