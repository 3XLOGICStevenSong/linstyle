package com.djb.highway.bus.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.djb.highway.bus.service.IBusGroupService;
import com.djb.highway.bus.service.IBusUserService;
import com.djb.highway.common.util.CommonUtil;

import com.djb.highway.framework.action.BaseAction;

import com.djb.highway.bus.dto.BusUserDTO;
import com.djb.highway.bus.dtoclient.BusUserClientDTO;
import com.djb.highway.bus.dtoclient.BusUserClientListDTO;
import com.djb.highway.bus.entity.BusGroupEntity;
import com.djb.highway.bus.entity.BusUserEntity;

@Controller("/BusUser")
public class BusUserAction extends BaseAction {

    @Autowired
    @Qualifier("iBusUserService")
    private IBusUserService iBusUserService;

    @Autowired
    @Qualifier("iBusGroupService")
    private IBusGroupService iBusGroupService;

    public BusUserAction() {
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
    // 上传用户位置

    public ActionForward uL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        BusUserDTO paramDTO = (BusUserDTO) form;

        // test code
        logger.info("客户端传参 uL:", "uid:" + paramDTO.getUid() + "longitude:" + paramDTO.getO() + "，lat:" + paramDTO.getA());

        BusUserClientDTO resultDTO = new BusUserClientDTO();
        // test
        // paramDTO.setUid(612);
        //paramDTO.setO("143.223232");
        //paramDTO.setA("143.124457555");
        // paramDTO.setO("143.223232");
        // paramDTO.setA("143.124457555");

        // paramDTO.setBuser_tel("18809812208");
        // 参数Entity
        try {
            BusUserEntity paramEntity = new BusUserEntity();
            paramEntity.setBuser_id(paramDTO.getUid());
            // paramEntity.setLatitude(paramDTO.getA());
            // paramEntity.setLongitude(paramDTO.getO());
            paramEntity.setUpdate_time(new Date());

            if ("".equals(paramDTO.getO()) || paramDTO.getO() == null || "".equals(paramDTO.getA()) || paramDTO.getA() == null) {
                resultDTO.setR("-4001");
                paramDTO.setErrFlg(true);
            } else {
                // 截取字符串

                String lad = this.LASubString(paramDTO.getA());

                if (!"".equals(lad)) {
                    paramEntity.setLatitude(lad);
                }
                String lon = LASubString(paramDTO.getO());
                if (!"".equals(lon)) {

                    paramEntity.setLongitude(lon);
                }

                // test code
                logger.info("更新之前uL:", ">>inputBusUser>>" + "uid:" + paramDTO.getUid() + "，longitude:" + paramDTO.getO() + "，lat:" + paramDTO.getA());

                iBusUserService.updateBusUser(paramEntity);

                // test code
                BusUserEntity paramBUEntity = new BusUserEntity();
                paramBUEntity.setBuser_id(paramDTO.getUid());
                BusUserEntity resultEntity = null;

                // logger.info("uL:", ">>beforeSelectBusUser>>" + "uid:" +
                // paramDTO.getUid());
                // resultEntity = iBusUserService.getObject(paramBUEntity);
                // logger.info("uL:", ">>afterSelectBusUser>>" + "uid:" +
                // resultEntity.getBuser_id() + "，longitude:" +
                // resultEntity.getLongitude() + "，lat:"
                // + resultEntity.getLatitude());

            }
        } catch (Exception e) {
            resultDTO.setR("-4001");
            paramDTO.setErrFlg(true);
            logger.error("uL", e);
        }
        if (!paramDTO.isErrFlg()) {
            resultDTO.setR("0");

            // test code
            resultDTO.setBuser_id(paramDTO.getUid());

        }
        toJson(response, resultDTO);
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

    public ActionForward addUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        BusUserDTO paramDTO = (BusUserDTO) form;
        BusUserClientDTO resultDTO = new BusUserClientDTO();

        // paramDTO.setBuser_id(268);
        // paramDTO.setBuser_name("lhsss");
        // paramDTO.setBuser_tel("13988888888");
        // 参数Entity
        try {
            BusUserEntity paramEntity = new BusUserEntity();
            BusUserEntity resultEntity = null;
            paramEntity.setBuser_id(paramDTO.getBuser_id());
            resultEntity = iBusUserService.getObject(paramEntity);
            if (resultEntity != null) {
                resultDTO.setReturnCode("-4100");
                paramDTO.setErrFlg(true);

            } else {
                CommonUtil.reflectClass(paramDTO, paramEntity);
                paramEntity.setInsert_time(new Date());
                iBusUserService.addBusUser(paramEntity);
            }

        } catch (Exception e) {
            resultDTO.setReturnCode("-4002");
            paramDTO.setErrFlg(true);
            logger.error("addUser", e);
        }
        if (!paramDTO.isErrFlg()) {
            resultDTO.setReturnCode("0");
        }
        toJson(response, resultDTO);
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
    public ActionForward updateUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        BusUserDTO paramDTO = (BusUserDTO) form;
        BusUserClientDTO resultDTO = new BusUserClientDTO();
        BusUserEntity paramEntity = new BusUserEntity();

        // 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);
        try {
            iBusUserService.updateBusUser(paramEntity);
            BusUserEntity busUserEntity = new BusUserEntity();
            BusUserEntity resultEntity = null;
            busUserEntity.setBuser_id(paramDTO.getBuser_id());
            // 获取用户信息
            resultEntity = iBusUserService.getObject(busUserEntity);

            if (resultEntity.getGroup_id() != null && -1 != resultEntity.getGroup_id().intValue()) {
                BusGroupEntity paramGroupEntity = new BusGroupEntity();
                BusGroupEntity resultGroupEntity = null;
                paramGroupEntity.setGroup_id(resultEntity.getGroup_id());
                // 获取组信息更新版本号
                resultGroupEntity = iBusGroupService.getObject(paramGroupEntity);
                if (resultGroupEntity != null) {
                    int version = resultGroupEntity.getVersion() + 1;
                    resultGroupEntity.setVersion(version);
                    iBusGroupService.updateGroup(resultGroupEntity);
                }
            }
        } catch (Exception e) {
            resultDTO.setReturnCode("-4003");
            paramDTO.setErrFlg(true);
            logger.error("updateUser", e);
        }
        if (!paramDTO.isErrFlg()) {
            resultDTO.setReturnCode("0");
        }
        toJson(response, resultDTO);
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


    public ActionForward getUserDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("getUserDertail",">>getUserDertail");
        long lngTime = new Date().getTime();
        // 参数DTO
        BusUserDTO paramDTO = (BusUserDTO) form;
        BusUserClientDTO resultDTO = new BusUserClientDTO();

        // paramDTO.setBuser_id(30);
        // paramDTO.setBuser_name("buser_name");
        // paramDTO.setBuser_tel("18809812208");
        // 参数Entity
        BusUserEntity paramEntity = new BusUserEntity();
        paramEntity.setBuser_id(paramDTO.getBuser_id());

        BusUserEntity resultEntity = null;
        try {
            //testCode
            logger.info("getUserDertail:", ">>inputBusUser>>" + "uid:" + paramDTO.getBuser_id().toString());
            resultEntity = iBusUserService.getObject(paramEntity);


            if (resultEntity == null) {
                resultDTO.setReturnCode("-4007");
                paramDTO.setErrFlg(true);
                logger.info("getUserDertail","resultEntity == null"); 
            } else {
                logger.info("getUserDertail:", ">>outputBusUser>>" + "uid:" + resultEntity.getBuser_id().toString() + "，name:" + resultEntity.getBuser_name()
                                + "，tel:" + resultEntity.getBuser_tel());
                
                resultDTO.setGroupid(resultEntity.getGroup_id());
                resultDTO.setName(resultEntity.getBuser_name());
                resultDTO.setTel(resultEntity.getBuser_tel());
                logger.info("getUserDertail","resultEntity != null"); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultDTO.setReturnCode("-4004");
            paramDTO.setErrFlg(true);
            logger.error("getUserDetail", e);

        }
        if (!paramDTO.isErrFlg()) {
            resultDTO.setReturnCode("0");
        }
        toJson(response, resultDTO);
        lngTime = (new Date().getTime()) - lngTime;
       
        logger.info("getUserDertail","getUserDertail"+"经过时间[" + lngTime + "]毫秒");
        logger.info("getUserDertail","getUserDertail<<");
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
    public ActionForward joinGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        BusUserDTO paramDTO = (BusUserDTO) form;
        BusUserClientDTO resultDTO = new BusUserClientDTO();
        // paramDTO.setBuser_id(277);
        // paramDTO.setGroup_id(-1);
        BusUserEntity busUserEntity = new BusUserEntity();
        busUserEntity.setBuser_id(paramDTO.getBuser_id());
        // 参数Entity
        try {

            BusUserEntity resultEntity = null;
            resultEntity = iBusUserService.getObject(busUserEntity);
            // 如果之前加入组，则更新之前组的版本信息
            if (resultEntity != null) {
                if (resultEntity.getGroup_id() != null && -1 != resultEntity.getGroup_id().intValue()) {
                    BusGroupEntity paramGroupEntity = new BusGroupEntity();
                    BusGroupEntity resultGroupEntity = null;
                    paramGroupEntity.setGroup_id(resultEntity.getGroup_id());
                    resultGroupEntity = iBusGroupService.getObject(paramGroupEntity);
                    if (resultGroupEntity != null) {
                        int version = resultGroupEntity.getVersion() + 1;
                        resultGroupEntity.setVersion(version);
                        iBusGroupService.updateGroup(resultGroupEntity);

                    }
                }
            }
            // 更新用户加入的组
            BusUserEntity paramEntity = new BusUserEntity();
            CommonUtil.reflectClass(paramDTO, paramEntity);
            iBusUserService.updateBusUser(paramEntity);
            if (paramDTO.getGroup_id() != null && -1 != paramDTO.getGroup_id().intValue()) {
                BusGroupEntity paramGroupEntity = new BusGroupEntity();
                BusGroupEntity resultGroup = null;
                paramGroupEntity.setGroup_id(paramDTO.getGroup_id());
                resultGroup = iBusGroupService.getObject(paramGroupEntity);
                if (resultEntity != null) {
                    int version = resultGroup.getVersion() + 1;
                    resultGroup.setVersion(version);
                    iBusGroupService.updateGroup(resultGroup);
                }
            }

        } catch (Exception e) {
            resultDTO.setReturnCode("-4005");
            paramDTO.setErrFlg(true);
            logger.error("joinGroup", e);
        }
        if (!paramDTO.isErrFlg()) {
            resultDTO.setReturnCode("0");
        }
        toJson(response, resultDTO);
        return null;
    }

    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward gGL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        BusUserDTO paramDTO = (BusUserDTO) form;
        logger.info("gGL:", ">>inputGGL>>" + paramDTO.getGid());
        // 客户端结果
        BusUserClientListDTO resultListDTO = new BusUserClientListDTO();

        // paramDTO.setGroup_id(1);
        // paramDTO.setGid(41);
        // 参数Entity
        BusUserEntity paramEntity = new BusUserEntity();
        paramEntity.setGroup_id(paramDTO.getGid());
        // 结果Entity
        List<BusUserEntity> resultEntityList = null;
        List<BusUserClientDTO> resultDTOList = new ArrayList<BusUserClientDTO>();
        try {
            resultEntityList = iBusUserService.getBusUserList(paramEntity);
            for (BusUserEntity resultEntity : resultEntityList) {
                if (!"0".equals(resultEntity.getLatitude()) && !"0".equals(resultEntity.getLongitude())) {
                    // 显示5分钟内上传位置的
                    if (resultEntity.getUpdate_time() != null) {
                        Date temDate = new Date();
                        long teptime = (temDate.getTime() - resultEntity.getUpdate_time().getTime()) / 1000;
                        long day = teptime / (24 * 3600);
                        long hour = teptime % (24 * 3600) / 3600;
                        long minute = teptime % 3600 / 60;

                        if ((hour == 0) && (day == 0) && (minute <= 5)) {
                            // 结果赋值
                            BusUserClientDTO resultDTO = new BusUserClientDTO();
                            resultDTO.setA(resultEntity.getLatitude());
                            resultDTO.setO(resultEntity.getLongitude());
                            resultDTO.setUid(resultEntity.getBuser_id());
                            resultDTOList.add(resultDTO);
                        }
                    }
                }
            }
            // 获取组的版本号
            BusGroupEntity paramGroupEntity = new BusGroupEntity();
            BusGroupEntity resultEntity = null;
            paramGroupEntity.setGroup_id(paramDTO.getGid());
            resultEntity = iBusGroupService.getObject(paramGroupEntity);
            if (resultEntity != null) {
                resultListDTO.setV(resultEntity.getVersion());
            }
            resultListDTO.setL(resultDTOList);
        } catch (Exception e) {
            resultListDTO.setR("-4006");
            paramDTO.setErrFlg(true);
            logger.error("gGL", e);
        }
        if (!paramDTO.isErrFlg()) {
            resultListDTO.setR("0");
            logger.info("gGL:", ">>outputGGL>>" + paramDTO.getGid());
        }
        toJson(response, resultListDTO);
        return null;
    }

    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getGroupUsers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        BusUserDTO paramDTO = (BusUserDTO) form;

        BusUserClientListDTO resultListDTO = new BusUserClientListDTO();

        // paramDTO.setGroup_id(35);

        // 参数Entity
        BusUserEntity paramEntity = new BusUserEntity();
        paramEntity.setGroup_id(paramDTO.getGroup_id());
        // CommonUtil.reflectClass(paramDTO, paramEntity);
        List<BusUserEntity> resultEntityList = null;
        List<BusUserClientDTO> resultDTOList = new ArrayList<BusUserClientDTO>();
        try {
            resultEntityList = iBusUserService.getBusUserList(paramEntity);
            for (BusUserEntity resultEntity : resultEntityList) {

                BusUserClientDTO resultDTO = new BusUserClientDTO();
                resultDTO.setName(resultEntity.getBuser_name());
                resultDTO.setBuser_id(resultEntity.getBuser_id());
                resultDTO.setTel(resultEntity.getBuser_tel());
                resultDTOList.add(resultDTO);
            }
            BusGroupEntity paramGroupEntity = new BusGroupEntity();
            BusGroupEntity resultEntity = null;
            paramGroupEntity.setGroup_id(paramDTO.getGroup_id());
            resultEntity = iBusGroupService.getObject(paramGroupEntity);
            if (resultEntity != null) {
                resultListDTO.setV(resultEntity.getVersion());
            }

            resultListDTO.setList(resultDTOList);
        } catch (Exception e) {
            resultListDTO.setReturnCode("-4008");
            paramDTO.setErrFlg(true);
            logger.error("getGroupUsers", e);
        }
        if (!paramDTO.isErrFlg()) {
            resultListDTO.setReturnCode("0");
        }
        toJson(response, resultListDTO);
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
    // 上传下载用户位置

    public ActionForward ugL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        BusUserDTO paramDTO = (BusUserDTO) form;
        
        logger.info("客户端传参 ugL:", "uid:" + paramDTO.getUid() + "longitude:" + paramDTO.getO() + "，lat:" + paramDTO.getA()+"gid:" + paramDTO.getGid() +"u:" + paramDTO.getU());
        // 客户端结果
        BusUserClientListDTO resultListDTO = new BusUserClientListDTO();
        // test
        // paramDTO.setUid(612);
        // paramDTO.setO("123.5858");
        // paramDTO.setA("143.124457555");
        // paramDTO.setU("1");
        // paramDTO.setGid(74);
        // paramDTO.setBuser_tel("18809812208");
        // 参数Entity

        // 上传位置
        try {
            if ("1".equals(paramDTO.getU())) {
                BusUserEntity paramEntity = new BusUserEntity();
                paramEntity.setBuser_id(paramDTO.getUid());
                paramEntity.setUpdate_time(new Date());
                if ("".equals(paramDTO.getO()) || paramDTO.getO() == null || "".equals(paramDTO.getA()) || paramDTO.getA() == null || paramDTO.getUid() == null) {
                    resultListDTO.setR("-4202");
                    paramDTO.setErrFlg(true);
                } else {
                    // 截取字符串
                    String lad = this.LASubString(paramDTO.getA());
                    if (!"".equals(lad)) {
                        paramEntity.setLatitude(lad);
                    }
                    String lon = LASubString(paramDTO.getO());
                    if (!"".equals(lon)) {
                        paramEntity.setLongitude(lon);
                    }

                    logger.info("更新之前ugl:", ">>>>" + "uid:" + paramEntity.getBuser_id() + "，longitude:" +paramEntity.getLongitude() + "，lat:" + paramEntity.getLatitude());
                    iBusUserService.updateBusUser(paramEntity);
                }
            }
        } catch (Exception e) {
            resultListDTO.setR("-4200");
            paramDTO.setErrFlg(true);
            // logger.error("uL", e);
        }

        try {

            if (!"-4201".equals(resultListDTO.getR())) {
                BusUserEntity userEntiy = new BusUserEntity();
                userEntiy.setGroup_id(paramDTO.getGid());
                // 结果Entity
                List<BusUserEntity> resultEntityList = null;
                List<BusUserClientDTO> resultDTOList = new ArrayList<BusUserClientDTO>();

                resultEntityList = iBusUserService.getBusUserList(userEntiy);
                for (BusUserEntity resultEntity : resultEntityList) {
                    if (!"0".equals(resultEntity.getLatitude()) && !"0".equals(resultEntity.getLongitude())) {
                        // 显示5分钟内上传位置的
                        if (resultEntity.getUpdate_time() != null) {
                            Date temDate = new Date();
                            long teptime = (temDate.getTime() - resultEntity.getUpdate_time().getTime()) / 1000;
                            long day = teptime / (24 * 3600);
                            long hour = teptime % (24 * 3600) / 3600;
                            long minute = teptime % 3600 / 60;

                            if ((hour == 0) && (day == 0) && (minute <= 5)) {
                                // 结果赋值
                                BusUserClientDTO resultDTO = new BusUserClientDTO();
                                resultDTO.setA(resultEntity.getLatitude());
                                resultDTO.setO(resultEntity.getLongitude());
                                resultDTO.setUid(resultEntity.getBuser_id());
                                resultDTOList.add(resultDTO);
                            }
                        }
                    }
                }
                // 获取组的版本号
                BusGroupEntity paramGroupEntity = new BusGroupEntity();
                BusGroupEntity resultEntity = null;
                paramGroupEntity.setGroup_id(paramDTO.getGid());
                resultEntity = iBusGroupService.getObject(paramGroupEntity);
                if (resultEntity != null) {
                    resultListDTO.setV(resultEntity.getVersion());
                }
                resultListDTO.setL(resultDTOList);
            }
        } catch (Exception e) {
            resultListDTO.setR("-4201");
            paramDTO.setErrFlg(true);
            // logger.error("gGL", e);
        }

        if (!paramDTO.isErrFlg()) {
            resultListDTO.setR("0");

        }
        toJson(response, resultListDTO);
        return null;
    }

    public String LASubString(String str) {
        String preA = str.substring(0, str.indexOf(".") + 1);
        String tempA = str.substring(str.indexOf(".") + 1);
        String sufA = "";
        if (tempA.length() > 7) {
            sufA = str.substring(str.indexOf(".") + 1, str.indexOf(".") + 8);
        } else {
            sufA = tempA;
        }
        String result = preA + sufA;
        return result;
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
    // 上传用户位置

    public ActionForward uLh5(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        BusUserDTO paramDTO = (BusUserDTO) form;
        BusUserClientDTO resultDTO = new BusUserClientDTO();

        logger.info("input" + "uid:" + paramDTO.getBuser_id() + "uL", "lngLa:" + paramDTO.getLnglat());
        // test
        // paramDTO.setUid(612);
        // paramDTO.setO("143.223232");
        // paramDTO.setA("143.124457555");
        // paramDTO.setBuser_tel("18809812208");
        // 参数Entity

        if (paramDTO.getLnglat() != null) {
            String[] str = paramDTO.getLnglat().split(",");

            paramDTO.setO(str[0]);
            paramDTO.setA(str[1]);
        }
        try {
            BusUserEntity paramEntity = new BusUserEntity();
            paramEntity.setBuser_id(paramDTO.getBuser_id());
            // paramEntity.setLatitude(paramDTO.getA());
            // paramEntity.setLongitude(paramDTO.getO());
            paramEntity.setUpdate_time(new Date());

            if ("".equals(paramDTO.getO()) || paramDTO.getO() == null || "".equals(paramDTO.getA()) || paramDTO.getA() == null) {
                resultDTO.setR("-4001");
                paramDTO.setErrFlg(true);
            } else {
                // 截取字符串

                String lad = this.LASubString(paramDTO.getA());

                if (!"".equals(lad)) {
                    paramEntity.setLatitude(lad);
                }
                String lon = LASubString(paramDTO.getO());
                if (!"".equals(lon)) {

                    paramEntity.setLongitude(lon);
                }

                iBusUserService.updateBusUser(paramEntity);
                BusUserEntity backEntity = new BusUserEntity();
                backEntity.setBuser_id(paramDTO.getBuser_id());
                BusUserEntity result = null;
                result = iBusUserService.getObject(backEntity);

                this.updateByAjax(request, response, result);
                logger.info("uid:" + paramEntity.getBuser_id() + "uL", "longitude:" + paramEntity.getLongitude() + "，lat:" + paramEntity.getLongitude());
                return null;
            }
        } catch (Exception e) {
            resultDTO.setR("-4001");
            paramDTO.setErrFlg(true);
            logger.error("uL", e);
        }
        if (!paramDTO.isErrFlg()) {
            resultDTO.setR("0");
        }
        toJson(response, resultDTO);
        return null;
    }

    private void updateByAjax(HttpServletRequest req, HttpServletResponse resp, BusUserEntity paramEntity) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/xml;charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        PrintWriter out = resp.getWriter();
        out.println("<response>");
        out.println("<latitude>" + paramEntity.getLatitude() + "</latitude>");
        out.println("<longitude>" + paramEntity.getLongitude() + "</longitude>");
        out.println("<userId>" + paramEntity.getBuser_id() + "</userId>");

        out.println("</response>");
        out.close();
    }

}
