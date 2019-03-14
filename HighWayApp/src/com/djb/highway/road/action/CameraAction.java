package com.djb.highway.road.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.djb.highway.road.dto.CameraDTO;
import com.djb.highway.road.dtoclient.CameraClearBaseDTO;
import com.djb.highway.road.dtoclient.CameraPointDTO;
import com.djb.highway.road.dtoclient.CameraPointListDTO;
import com.djb.highway.road.dtoclient.RealTimeImageDTO;
import com.djb.highway.road.dtoclient.SectionImageDTO;
import com.djb.highway.road.dtoclient.SectionImageListDTO;
import com.djb.highway.road.entity.CameraEntity;
import com.djb.highway.road.service.ICameraService;

@Controller("/Camera")
public class CameraAction extends BaseAction {

    @Autowired
    @Qualifier("iCameraService")
    private ICameraService iCameraService;

    public CameraAction() {
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
    public ActionForward doGetCamera(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        CameraDTO paramDTO = (CameraDTO) form;
        // test
        // paramDTO.setC_id(1);
        // 结果DTO
        CameraDTO resultDTO = new CameraDTO();
        // 参数Entity
        CameraEntity paramEntity = new CameraEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果Entity
        CameraEntity resultEntity = null;
        // 客户端DTO
        RealTimeImageDTO realTimeImageDTO = new RealTimeImageDTO();

        // DB操作

        try {
            String keyName = paramDTO.getKeyName();
            if (keyName != null) {
                String messageContent = ResourceLocator.getI18NMessage(this, keyName);

                String[] messageContentArray = messageContent.split(",");
                // 摄像头ID：
                int cameraId = Integer.parseInt(messageContentArray[0]);
                paramEntity.setC_id(cameraId);

            } else {
                paramEntity.setC_id(paramDTO.getC_id());
            }

            resultEntity = iCameraService.getObject(paramEntity);

            if (resultEntity == null) {
                resultEntity = new CameraEntity();

            } else {

                // 页面DTO
                // realTimeImageDTO.setImageUrl(resultEntity.getPic_path());
                if (resultEntity.getPic_path() != null && !"".equals(resultEntity.getPic_path())) {
                    realTimeImageDTO.setImageUrl(ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS)
                                    + ResourceLocator.getI18NMessage(this, Constants.CAMERA_SERVLET_PATH) + resultEntity.getPic_path());
                }

                realTimeImageDTO.setPileNumInfo(resultEntity.getStake_id());

                // SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                // realTimeImageDTO.setTakeTime(sdf.format(resultEntity.getShoot_time()));
                realTimeImageDTO.setTakeTime(CommonUtil.dateToString(resultEntity.getShoot_time(), "yyyy/MM/dd HH:mm"));
                realTimeImageDTO.setC_addr(resultEntity.getC_addr());

            }
        } catch (Exception e) {

            resultDTO.setErrFlg(true);
            realTimeImageDTO.setReturnCode("-1101");
            logger.error("doGetCamera", e);
        }

        if (!resultDTO.isErrFlg()) {
            realTimeImageDTO.setReturnCode("0");
        }
        // android
        toJson(response, realTimeImageDTO);
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
    // 返回显示的摄像头所在的路段ID
    public ActionForward getShowCameraList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 结果DTO
        CameraDTO resultDTO = new CameraDTO();
        // 页面DTO

        CameraClearBaseDTO cameraClearBaseDTO = new CameraClearBaseDTO();

        // 获取所有需要显示的路段List信息
        List<String[]> allSectionStakeIdArgs = null;
        // 获取所有需要显示的收费站List信息
        List<String[]> allPlazIdArgs = null;
        // 获取所有需要显示的立交List信息
        List<String[]> allOverpaIdArgs = null;
        // 获取所有需要显示的服务区List信息
        List<String[]> allServiceAreaArgs = null;

        // 所有的路段ID
        List<String> secIds = null;
        List<String> plazIds = null;
        List<String> overpassIds = null;
        List<String> serviceAreaIds = null;
        List<String> allIds = null;

        // 可用摄像头Id集合
        List<String> cameraAllIds = null;

        try {

            // 所有途经高速的List
            String[] h_codeArgs = new String[] { "G1113", "G1", "G1501", "G15", "G1212" };
            CameraEntity paramCameraEntity = new CameraEntity();
            paramCameraEntity.setH_codeArgs(h_codeArgs);
            allIds = new ArrayList<String>();
            // 获取所有的摄像头信息
            List<CameraEntity> cameraEntityList = iCameraService.getCameraList(paramCameraEntity);

            // 初始化可用摄像头信息集合
            if (cameraEntityList != null && cameraEntityList.size() > 0) {
                cameraAllIds = new ArrayList<String>();
                for (CameraEntity info : cameraEntityList) {
                    if (info.getC_id() != null) {
                        cameraAllIds.add("" + info.getC_id());
                    }

                }

            }

            // 获取所有需要显示的路段List信息
            allSectionStakeIdArgs = getAllSectionStakeId(cameraAllIds);
            // 获取所有需要显示的收费站List信息
            allPlazIdArgs = getAllPlazStakeId(cameraAllIds);
            // 获取所有需要显示的立交List信息
            allOverpaIdArgs = getAllOverpassStakeId(cameraAllIds);
            // 获取所有需要显示的服务区List信息
            allServiceAreaArgs = getAllServiceAreaStakeId(cameraAllIds);

            // 给路段List赋值
            if (allSectionStakeIdArgs != null) {
                secIds = new ArrayList<String>();
                for (String[] sectionArgs : allSectionStakeIdArgs) {
                    boolean isShow = false;
                    if (sectionArgs != null) {
                        isShow = false;

                        if (cameraEntityList != null) {
                            for (CameraEntity cameraEntity : cameraEntityList) {

                                if (sectionArgs[3] != null && cameraEntity != null && cameraEntity.getH_code() != null
                                                && ("0".equals(cameraEntity.getDirection()) || "1".equals(cameraEntity.getDirection()))) {

                                    isShow = isShowElement(cameraEntity.getStake_id(), sectionArgs[1], sectionArgs[2]);
                                    if (isShow) {
                                        // secIds.add(sectionArgs[0]);
                                        allIds.add(sectionArgs[0]);
                                        break;
                                    }
                                }

                            }

                        }
                    }

                }

            }

            // 给收费站List赋值
            if (allPlazIdArgs != null) {
                plazIds = new ArrayList<String>();
                for (String[] plazArgs : allPlazIdArgs) {
                    // plazIds.add(plazArgs[0]);
                    allIds.add(plazArgs[0]);
                }

            }

            // 给立交List赋值
            if (allOverpaIdArgs != null) {
                overpassIds = new ArrayList<String>();
                for (String[] overpassArgs : allOverpaIdArgs) {
                    // overpassIds.add(overpassArgs[0]);
                    allIds.add(overpassArgs[0]);
                }

            }
            // 给服务区List赋值
            if (allServiceAreaArgs != null) {
                serviceAreaIds = new ArrayList<String>();
                for (String[] serviceAreaArgs : allServiceAreaArgs) {
                    // serviceAreaIds.add(serviceAreaArgs[0]);
                    allIds.add(serviceAreaArgs[0]);
                }

            }

            // 排序allIds：升序
            // if (allIds != null && allIds.size() > 0) {
            // Collections.sort(allIds);
            // System.out.println(allIds);
            // }
            cameraClearBaseDTO.setIds(allIds);

        } catch (Exception e) {
            resultDTO.setErrFlg(true);
            cameraClearBaseDTO.setReturnCode("-1504");

        }

        if (!resultDTO.isErrFlg()) {
            cameraClearBaseDTO.setReturnCode("0");

        }

        // android
        toJson(response, cameraClearBaseDTO);
        // ;
        return null;
    }

    // 通过IDs获取摄像头信息

    // 返回显示的摄像头所在的路段ID
    public ActionForward getCameraListBySecId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 参数DTO
        CameraDTO paramDTO = (CameraDTO) form;

        // test
        // paramDTO.setSection_id(35);

        // 页面DTO
        SectionImageListDTO sectionImageListDTO = new SectionImageListDTO();

        List<String> cameraPlazIds = null;
        List<String> cameraServiceIds = null;
        List<String> cameraOverPassIds = null;
        try {
            // 获取SectionID
            int section_id = paramDTO.getSection_id();
            if (section_id <= 0 || section_id > 62) {
                return null;
            }

            // 获取路段的摄像头数据
            if (section_id >= 1 && section_id <= 27) {
                // 获取对应路段的摄像头
                String[] paramCameraAgs = getStakeId(section_id);

                // 获取路段的起点和终点的桩号和名称
                sectionImageListDTO.setmStationPileStart(paramCameraAgs[0]);
                sectionImageListDTO.setmStationPileEnd(paramCameraAgs[1]);
                sectionImageListDTO.setmStationNameStart(paramCameraAgs[3]);
                sectionImageListDTO.setmStationNameEnd(paramCameraAgs[4]);
                sectionImageListDTO.setmStationCodeStart(paramCameraAgs[7]);
                sectionImageListDTO.setmStationCodeEnd(paramCameraAgs[8]);

                // 判断当前端点是收费站还是立交，当为true时，表示收费站；当为false时，表示立交
                if ("1".endsWith(paramCameraAgs[5])) {
                    sectionImageListDTO.setmFlagStart(true);
                } else {
                    sectionImageListDTO.setmFlagStart(false);
                }

                if ("0".endsWith(paramCameraAgs[6])) {
                    sectionImageListDTO.setmFlagEnd(true);
                } else {
                    sectionImageListDTO.setmFlagEnd(false);
                }

                if (paramCameraAgs != null) {
                    CameraEntity paramCameraEntity = new CameraEntity();
                    paramCameraEntity.setStakeIdStart(paramCameraAgs[0]);
                    paramCameraEntity.setStakeIdEnd(paramCameraAgs[1]);
                    paramCameraEntity.setH_code(paramCameraAgs[2]);
                    paramCameraEntity.setDirection("0");
                    List<CameraEntity> cameraEntityList = iCameraService.getCameraList(paramCameraEntity);

                    if (cameraEntityList != null) {
                        List<SectionImageDTO> listUp = new ArrayList<SectionImageDTO>();
                        for (CameraEntity camera : cameraEntityList) {
                            SectionImageDTO sectionImageDTO = new SectionImageDTO();
                            sectionImageDTO.setmCameraId(String.valueOf(camera.getC_id()));
                            sectionImageDTO.setmPile(camera.getStake_id());
                            listUp.add(sectionImageDTO);
                        }
                        sectionImageListDTO.setListUp(listUp);

                    }

                    paramCameraEntity.setDirection("1");
                    List<CameraEntity> _cameraEntityList = iCameraService.getCameraList(paramCameraEntity);

                    if (_cameraEntityList != null) {
                        List<SectionImageDTO> listDown = new ArrayList<SectionImageDTO>();
                        for (CameraEntity camera : _cameraEntityList) {
                            SectionImageDTO sectionImageDTO = new SectionImageDTO();
                            sectionImageDTO.setmCameraId(String.valueOf(camera.getC_id()));
                            sectionImageDTO.setmPile(camera.getStake_id());
                            listDown.add(sectionImageDTO);
                        }
                        // 不要求逆序排列
                        // if (listDown != null) {
                        // Collections.reverse(listDown);
                        // }
                        sectionImageListDTO.setListDown(listDown);
                    }

                }

            }

            // 获取收费站的摄像头数据
            if (section_id >= 34 && section_id <= 59) {
                String[] cameraArgs = getCameraInfo(section_id, Constants.KEY_PLAZ_STAKE_ID_LIST);
                cameraPlazIds = new ArrayList<String>();
                String[] iDs = StringUtils.split(cameraArgs[0], ":");
                if (iDs != null && iDs.length > 0) {
                    for (String str : iDs) {
                        if (!"-1".endsWith(str)) {
                            cameraPlazIds.add(str);
                        }
                    }
                    sectionImageListDTO.setListIds(cameraPlazIds);

                }

            }

            // 获取服务区的摄像头数据
            if (section_id >= 60 && section_id <= 62) {
                String[] cameraArgs = getCameraInfo(section_id, Constants.KEY_SERVICE_STAKE_ID_LIST);
                cameraServiceIds = new ArrayList<String>();
                String[] iDs = StringUtils.split(cameraArgs[0], ":");
                if (iDs != null && iDs.length > 0) {
                    for (String str : iDs) {
                        if (!"-1".endsWith(str)) {
                            cameraServiceIds.add(str);
                        }

                    }
                    sectionImageListDTO.setListIds(cameraServiceIds);
                }

            }
            // 获取互通立交的摄像头数据
            if (section_id >= 28 && section_id <= 33) {
                String[] cameraArgs = getCameraInfo(section_id, Constants.KEY_OVERPASS_STAKE_ID_LIST);
                cameraOverPassIds = new ArrayList<String>();
                String[] iDs = StringUtils.split(cameraArgs[0], ":");
                if (iDs != null && iDs.length > 0) {
                    for (String str : iDs) {
                        if (!"-1".endsWith(str)) {
                            cameraOverPassIds.add(str);
                        }

                    }
                    sectionImageListDTO.setListIds(cameraOverPassIds);
                }

            }

        } catch (Exception e) {
            paramDTO.setErrFlg(true);
            sectionImageListDTO.setReturnCode("-1103");

        }
        if (!paramDTO.isErrFlg()) {
            sectionImageListDTO.setReturnCode("0");

        }

        toJson(response, sectionImageListDTO);
        return null;

    }

    // 不区分方向,判断显示
    private boolean isShowElement(String stakeId, String entryStakeId, String eixtStakeId) {

        if (stakeId == null || entryStakeId == null || eixtStakeId == null) {
            return false;
        }

        boolean isShow = false;

        float stake_id = CommonUtil.toFloat(stakeId, 0);
        float entry_stake_id = CommonUtil.toFloat(entryStakeId, 0);
        float exit_stake_id = CommonUtil.toFloat(eixtStakeId, 0);

        isShow = (stake_id <= exit_stake_id && stake_id >= entry_stake_id) || (stake_id >= exit_stake_id && stake_id <= entry_stake_id);
        return isShow;

    }

    // 获取指定的路段信息：起点桩号、终点 桩号、高速Code
    private String[] getCameraInfo(int sectionId, String keyName) {

        String _sectionId = String.valueOf(sectionId);
        if (_sectionId == null) {
            return null;
        }

        String[] cameraInfo = null;
        String[] sectionArgs = StringUtils.split(ResourceLocator.getI18NMessage(this, keyName), "|");
        for (String section : sectionArgs) {
            String[] _sectionArgs = StringUtils.split(section, ",");
            if (_sectionId.equals(_sectionArgs[0])) {
                cameraInfo = new String[] { _sectionArgs[1], _sectionArgs[2] };
                break;
            }

        }
        return cameraInfo;

    }

    // 获取所有的路段信息：起点桩号、终点 桩号、高速Code
    private List<String[]> getAllSectionStakeId(List<String> cameraAllIds) {

        List<String[]> allSectionStakeIdArgs = null;
        String[] sectionArgs = StringUtils.split(ResourceLocator.getI18NMessage(this, Constants.KEY_SECION_STAKE_ID_LIST), "|");
        if (sectionArgs != null && sectionArgs.length > 0) {
            allSectionStakeIdArgs = new ArrayList<String[]>();

            for (String section : sectionArgs) {
                String[] _sectionArgs = StringUtils.split(section, ",");
                allSectionStakeIdArgs.add(_sectionArgs);
            }
        }

        return allSectionStakeIdArgs;

    }

    // 获取所有的收费站List信息：起点桩号、终点 桩号、高速Code
    private List<String[]> getAllPlazStakeId(List<String> cameraAllIds) {

        List<String[]> allPlazStakeIdArgs = null;
        String[] plazArgs = StringUtils.split(ResourceLocator.getI18NMessage(this, Constants.KEY_PLAZ_STAKE_ID_LIST), "|");
        if (plazArgs != null && plazArgs.length > 0) {
            allPlazStakeIdArgs = new ArrayList<String[]>();

            for (String plaz : plazArgs) {
                String[] _plazArgs = StringUtils.split(plaz, ",");
                if ((_plazArgs[1] != null) && (!"-1".equals(_plazArgs[1]))) {

                    String[] iDs = StringUtils.split(_plazArgs[1], ":");
                    if (iDs != null && iDs.length > 0) {
                        // iDs和可用情报板信息集合有交集，结束本次循环
                        boolean containFlag = false;
                        for (String str : iDs) {
                            if (cameraAllIds.contains(str)) {
                                containFlag = true;
                                break;
                            }
                        }
                        if (containFlag) {
                            allPlazStakeIdArgs.add(_plazArgs);
                        }

                    }

                }

            }
        }

        return allPlazStakeIdArgs;

    }

    // 获取所有的立交List信息：起点桩号、终点 桩号、高速Code
    private List<String[]> getAllOverpassStakeId(List<String> cameraAllIds) {

        List<String[]> allOverpassStakeIdArgs = null;
        String[] overpassArgs = StringUtils.split(ResourceLocator.getI18NMessage(this, Constants.KEY_OVERPASS_STAKE_ID_LIST), "|");
        if (overpassArgs != null && overpassArgs.length > 0) {
            allOverpassStakeIdArgs = new ArrayList<String[]>();

            for (String overpass : overpassArgs) {
                String[] _overpassArgs = StringUtils.split(overpass, ",");
                if ((_overpassArgs[1] != null) && (!"-1".equals(_overpassArgs[1]))) {

                    String[] iDs = StringUtils.split(_overpassArgs[1], ":");
                    if (iDs != null && iDs.length > 0) {
                        // iDs和可用情报板信息集合有交集，结束本次循环
                        boolean containFlag = false;
                        for (String str : iDs) {
                            if (cameraAllIds.contains(str)) {
                                containFlag = true;
                                break;
                            }
                        }
                        if (containFlag) {
                            allOverpassStakeIdArgs.add(_overpassArgs);
                        }

                    }
                }

            }
        }

        return allOverpassStakeIdArgs;

    }

    // 获取服务区摄像头信息：起点桩号、终点 桩号、高速Code
    private List<String[]> getAllServiceAreaStakeId(List<String> cameraAllIds) {

        List<String[]> allServiceAreaStakeIdArgs = null;
        String[] serviceAreaArgs = StringUtils.split(ResourceLocator.getI18NMessage(this, Constants.KEY_SERVICE_STAKE_ID_LIST), "|");
        if (serviceAreaArgs != null && serviceAreaArgs.length > 0) {
            allServiceAreaStakeIdArgs = new ArrayList<String[]>();

            for (String serviceArea : serviceAreaArgs) {
                String[] _serviceAreaArgs = StringUtils.split(serviceArea, ",");
                if ((_serviceAreaArgs[1] != null) && (!"-1".equals(_serviceAreaArgs[1]))) {

                    String[] iDs = StringUtils.split(_serviceAreaArgs[1], ":");
                    if (iDs != null && iDs.length > 0) {
                        // iDs和可用情报板信息集合有交集，结束本次循环
                        boolean containFlag = false;
                        for (String str : iDs) {
                            if (cameraAllIds.contains(str)) {
                                containFlag = true;
                                break;
                            }
                        }
                        if (containFlag) {
                            allServiceAreaStakeIdArgs.add(_serviceAreaArgs);
                        }

                    }

                }

            }
        }

        return allServiceAreaStakeIdArgs;

    }

    private String[] getStakeId(int sectionId) {

        String _sectionId = String.valueOf(sectionId);

        if (_sectionId == null) {
            return null;
        }

        String[] entryExitStakeId = null;
        String[] sectionArgs = StringUtils.split(ResourceLocator.getI18NMessage(this, Constants.KEY_SECION_STAKE_ID_LIST), "|");
        for (String section : sectionArgs) {
            String[] _sectionArgs = StringUtils.split(section, ",");
            if (_sectionId.equals(_sectionArgs[0])) {
                entryExitStakeId = new String[] { _sectionArgs[1], _sectionArgs[2], _sectionArgs[3], _sectionArgs[4], _sectionArgs[5], _sectionArgs[6],
                                _sectionArgs[7], _sectionArgs[8], _sectionArgs[9] };
                break;
            }

        }
        return entryExitStakeId;

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
    public ActionForward doGetCameraPointList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        CameraDTO paramDTO = (CameraDTO) form;
        // test
        // paramDTO.setH_code("G1");
        // paramDTO.setLatitude_b("41.6957");
        // paramDTO.setLongitude_b("123.414");
        // 参数Entity
        CameraEntity paramEntity = new CameraEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);
        List<CameraEntity> cameraEntityList = null;
        List<CameraPointDTO> cameraDTOList = null;
        // 客户端DTO
        CameraPointListDTO cameraPointListDTO = new CameraPointListDTO();
        // DB操作
        try {

            cameraEntityList = iCameraService.getCameraPointList(paramEntity);

            if (cameraEntityList != null && cameraEntityList.size() > 0) {

                cameraDTOList = new ArrayList<CameraPointDTO>();
                for (CameraEntity camera : cameraEntityList) {
                    CameraPointDTO cameraDTO = new CameraPointDTO();
                    // CommonUtil.reflectClass(camera, cameraDTO);
                    cameraDTO.setC_id(camera.getC_id());
                    cameraDTO.setLongitude_b(camera.getLongitude_b());
                    cameraDTO.setLatitude_b(camera.getLatitude_b());
                    cameraDTO.setLocation_type(camera.getLocation_type());
                    cameraDTOList.add(cameraDTO);
                }
                cameraPointListDTO.setPointList(cameraDTOList);

            }

        } catch (Exception e) {
            cameraPointListDTO.setReturnCode("-3010");
            paramDTO.setErrFlg(true);
        }

        if (!paramDTO.isErrFlg()) {
            cameraPointListDTO.setReturnCode("0");
        }

        // android
        toJson(response, cameraPointListDTO);
        // ;
        return null;
    }

    /**
     * 更新辽宁省摄像头状态用
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
    public ActionForward doUpdateCamera(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        // CameraDTO paramDTO = (CameraDTO) form;
        try {
            String c_status_1 = request.getParameter("c_status_1");
            String c_status_2 = request.getParameter("c_status_2");
            // String c_status_3 = request.getParameter("c_status_3");
            String c_codes_1 = request.getParameter("c_codes_1");
            String c_codes_2 = request.getParameter("c_codes_2");
            // String c_codes_3 = request.getParameter("c_codes_3");

            CameraEntity allParam = new CameraEntity();
            allParam.setC_status("9");
            // 先将所有所有摄像头状态设置为9，沈阳绕城除外。
            iCameraService.updateCamera(allParam);

            if (!CommonUtil.isNullOrBlank(c_codes_1)) {
                CameraEntity param = new CameraEntity();
                param.setC_codeArgs(StringUtils.split(c_codes_1, "|"));
                param.setC_status(c_status_1);
                iCameraService.updateCamera(param);
            }
            if (!CommonUtil.isNullOrBlank(c_codes_2)) {
                CameraEntity param = new CameraEntity();
                param.setC_codeArgs(StringUtils.split(c_codes_2, "|"));
                param.setC_status(c_status_2);
                iCameraService.updateCamera(param);
            }

        } catch (Exception e) {
            this.logger.error("doUpdateCamera", e);
        }

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

    // 返回服务区和收费站的摄像头ID
    // public ActionForward getCameraListByLocId(ActionMapping mapping,
    // ActionForm form, HttpServletRequest request, HttpServletResponse
    // response)
    // throws Exception {
    // // 参数DTO
    // CameraDTO paramDTO = (CameraDTO) form;
    // // test
    // // paramDTO.setLocation_id(7);
    // // paramDTO.setLocation_type("p");
    // // paramDTO.setH_code("G1501");
    //
    // // 参数Entity
    // CameraEntity paramCameraEntity = new CameraEntity();
    // // 结果DTO
    // List<CameraEntity> cameraEntityList = null;
    // // APPClientDTO
    // SectionImageListDTO sectionImageListDTO = new SectionImageListDTO();
    //
    // List<String> cameraIds = null;
    // try {
    // // 获取SectionID
    // if (!"G1501".equals(paramDTO.getH_code())) {
    //
    // paramCameraEntity.setLocation_id(paramDTO.getLocation_id());
    // paramCameraEntity.setLocation_type(paramDTO.getLocation_type());
    // cameraEntityList = iCameraService.getCameraList(paramCameraEntity);
    // if (cameraEntityList != null && cameraEntityList.size() > 0) {
    // cameraIds = new ArrayList<String>();
    // for (CameraEntity camera : cameraEntityList) {
    // String id = camera.getC_id().toString();
    // cameraIds.add(id);
    // }
    // sectionImageListDTO.setListIds(cameraIds);
    //
    // }
    // } else {
    // return null;
    // }
    // } catch (Exception e) {
    // paramDTO.setErrFlg(true);
    // sectionImageListDTO.setReturnCode("-1106");
    //
    // }
    // if (!paramDTO.isErrFlg()) {
    // sectionImageListDTO.setReturnCode("0");
    //
    // }
    //
    // toJson(response, sectionImageListDTO);
    // return null;
    //
    // }

}
