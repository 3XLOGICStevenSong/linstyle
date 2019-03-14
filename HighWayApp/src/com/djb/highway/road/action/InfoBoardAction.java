package com.djb.highway.road.action;

import java.text.SimpleDateFormat;
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
import com.djb.highway.road.dto.InfoBoardDTO;
import com.djb.highway.road.dtoclient.InfoBoardClearBaseDTO;
import com.djb.highway.road.dtoclient.InfoBoardPointDTO;
import com.djb.highway.road.dtoclient.InfoBoardPointListDTO;
import com.djb.highway.road.dtoclient.ReportClearBaseDTO;
import com.djb.highway.road.dtoclient.ReportClearDTO;
import com.djb.highway.road.entity.InfoBoardEntity;
import com.djb.highway.road.service.IInfoBoardService;

@Controller("/InfoBoard")
public class InfoBoardAction extends BaseAction {

    @Autowired
    @Qualifier("iInfoBoardService")
    private IInfoBoardService iInfoBoardService;

    public InfoBoardAction() {
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

    // 返回显示的摄像头所在的路段ID
    public ActionForward getShowInfoBoardList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 结果DTO
        InfoBoardDTO resultDTO = new InfoBoardDTO();
        // 页面DTO

        InfoBoardClearBaseDTO infoBoardClearBaseDTO = new InfoBoardClearBaseDTO();

        // 获取所有需要显示的路段List信息
        List<String[]> allSectionStakeIdArgs = null;
        // 获取所有需要显示的收费站List信息
        List<String[]> allPlazIdArgs = null;
        // // 获取所有需要显示的立交List信息
        // List<String[]> allOverpaIdArgs = null;
        // 获取所有需要显示的服务区List信息
        List<String[]> allServiceAreaArgs = null;

        // 所有的路段ID
        // List<String> secIds = null;
        // List<String> plazIds = null;
        // List<String> serviceAreaIds = null;
        // 可用情报板信息集合
        List<String> infoBoardAllIds = null;
        // 返回的结果集合：section_id
        List<String> allIds = null;

        try {

            // 所有途经高速的List
            String[] h_codeArgs = new String[] { "G1113", "G1", "G1501", "G15", "G1212" };
            InfoBoardEntity paramInfoBoardEntity = new InfoBoardEntity();
            paramInfoBoardEntity.setH_codeArgs(h_codeArgs);
            allIds = new ArrayList<String>();
            // 获取所有的情报板信息
            List<InfoBoardEntity> infoBoardAllList = iInfoBoardService.getInfoBoardList(paramInfoBoardEntity);
            // 初始化可用情报板信息集合
            if (infoBoardAllList != null && infoBoardAllList.size() > 0) {
                infoBoardAllIds = new ArrayList<String>();
                for (InfoBoardEntity info : infoBoardAllList) {
                    if (info.getIb_id() != null) {
                        infoBoardAllIds.add("" + info.getIb_id());
                    }

                }

            }

            // 获取所有需要显示的路段List信息
            allSectionStakeIdArgs = getAllSectionStakeId(infoBoardAllIds);
            // 获取所有需要显示的收费站List信息
            allPlazIdArgs = getAllPlazStakeId(infoBoardAllIds);
            // 获取所有需要显示的立交List信息
            // allOverpaIdArgs = getAllOverpassStakeId();
            // 获取所有需要显示的服务区List信息
            allServiceAreaArgs = getAllServiceAreaStakeId(infoBoardAllIds);

            // 给路段List赋值
            if (allSectionStakeIdArgs != null) {
                // secIds = new ArrayList<String>();
                for (String[] sectionArgs : allSectionStakeIdArgs) {
                    boolean isShow = false;
                    if (sectionArgs != null) {
                        isShow = false;

                        if (infoBoardAllList != null) {
                            for (InfoBoardEntity infoBoardEntity : infoBoardAllList) {

                                if (sectionArgs[3] != null && infoBoardEntity != null && infoBoardEntity.getH_code() != null) {

                                    isShow = isShowElement(infoBoardEntity.getStake_id(), sectionArgs[1], sectionArgs[2]);
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

            // 给收费站List赋值,并且添加逻辑判断：当情报板的状态可用，才能显示对应的图标
            if (allPlazIdArgs != null) {
                // plazIds = new ArrayList<String>();
                for (String[] plazArgs : allPlazIdArgs) {
                    allIds.add(plazArgs[0]);
                }

            }

            // 给服务区List赋值,并且添加逻辑判断：当情报板的状态可用，才能显示对应的图标
            if (allServiceAreaArgs != null) {
                // serviceAreaIds = new ArrayList<String>();
                for (String[] serviceAreazArgs : allServiceAreaArgs) {
                    allIds.add(serviceAreazArgs[0]);
                }

            }

            // // 排序allIds：升序
            // if (allIds != null && allIds.size() > 0) {
            // Collections.sort(allIds);
            // }

            infoBoardClearBaseDTO.setIds(allIds);

        } catch (Exception e) {
            resultDTO.setErrFlg(true);
            infoBoardClearBaseDTO.setReturnCode("-1502");

        }

        if (!resultDTO.isErrFlg()) {
            infoBoardClearBaseDTO.setReturnCode("0");

        }

        // android
        toJson(response, infoBoardClearBaseDTO);
        // ;
        return null;
    }

    // 返回显示的摄像头所在的路段ID
    public ActionForward getInfoBoardListBySecId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 参数DTO
        InfoBoardDTO paramDTO = (InfoBoardDTO) form;
        // 客户端DTO
        ReportClearBaseDTO reportClearBaseDTO = new ReportClearBaseDTO();

        // test
        // paramDTO.setSection_id(26);
        try {
            // 获取SectionID
            int section_id = paramDTO.getSection_id();
            if (section_id <= 0 || section_id > 68) {
                return null;
            }

            // 获取路段的摄像头数据
            if (section_id >= 1 && section_id <= 27) {
                // 获取对应路段的情报板
                String[] paramCameraAgs = getStakeId(section_id);

                if (paramCameraAgs != null) {
                    InfoBoardEntity paramInfoBoardEntity = new InfoBoardEntity();
                    paramInfoBoardEntity.setStakeIdStart(paramCameraAgs[0]);
                    paramInfoBoardEntity.setStakeIdEnd(paramCameraAgs[1]);
                    paramInfoBoardEntity.setH_code(paramCameraAgs[2]);

                    // 设置LeftList的值
                    paramInfoBoardEntity.setDirection("0");
                    List<InfoBoardEntity> infoBoardEntityLeftList = iInfoBoardService.getInfoBoardList(paramInfoBoardEntity);
                    // 页面DTO赋值
                    List<ReportClearDTO> _reportList = null;
                    String sectionName = null;
                    sectionName = paramCameraAgs[3] + "->" + paramCameraAgs[4];

                    if (infoBoardEntityLeftList != null && infoBoardEntityLeftList.size() > 0) {
                        _reportList = new ArrayList<ReportClearDTO>();
                        for (InfoBoardEntity rep : infoBoardEntityLeftList) {

                            // 字段赋值操作
                            if ("Z".equals(rep.getLocation_type())) {
                                ReportClearDTO repDTO = new ReportClearDTO();
                                repDTO = doBackDTOtoClientDTO(rep, repDTO, "0", sectionName);
                                _reportList.add(repDTO);

                            }

                        }
                    }

                    reportClearBaseDTO.setListLeft(_reportList);
                    reportClearBaseDTO.setLeftSectionName(sectionName);
                    // 设置RightList的值
                    paramInfoBoardEntity.setDirection("1");
                    List<InfoBoardEntity> infoBoardEntityRightList = iInfoBoardService.getInfoBoardList(paramInfoBoardEntity);

                    // 页面DTO赋值
                    List<ReportClearDTO> _reportListRight = null;

                    sectionName = paramCameraAgs[4] + "->" + paramCameraAgs[3];
                    if (infoBoardEntityRightList != null && infoBoardEntityRightList.size() > 0) {
                        _reportListRight = new ArrayList<ReportClearDTO>();
                        for (InfoBoardEntity rep : infoBoardEntityRightList) {

                            // 字段赋值操作
                            if ("Z".equals(rep.getLocation_type())) {
                                ReportClearDTO repDTO = new ReportClearDTO();
                                repDTO = doBackDTOtoClientDTO(rep, repDTO, "1", sectionName);
                                _reportListRight.add(repDTO);
                            }

                        }
                    }

                    reportClearBaseDTO.setListRight(_reportListRight);
                    reportClearBaseDTO.setRightSectionName(sectionName);
                }
            }
            // 获取收费站的情报板数据
            if ((section_id >= 34 && section_id <= 59) || (section_id >= 63 && section_id <= 68)) {
                String[] infoBoardArgs = getInfoBoardInfo(section_id, Constants.KEY_INFOBOARD_PLAZ_STAKE_ID_LIST);
                List<String> infoBoardIds = new ArrayList<String>();
                String[] iDs = StringUtils.split(infoBoardArgs[0], ":");
                String plazName = infoBoardArgs[1];
                if (iDs != null && iDs.length > 0) {
                    for (String str : iDs) {
                        if (!"-1".endsWith(str)) {
                            infoBoardIds.add(str);
                        }
                    }

                }

                // 设置LeftList的值
                List<InfoBoardEntity> infoBoardEntityLeftList = null;
                if (infoBoardIds != null && infoBoardIds.size() > 0) {
                    InfoBoardEntity paramInfoBoardEntity = new InfoBoardEntity();
                    String[] idsAgs = infoBoardIds.toArray(new String[0]);
                    if (idsAgs != null && idsAgs.length > 0) {
                        paramInfoBoardEntity.setIdsAgs(idsAgs);
                    }
                    infoBoardEntityLeftList = iInfoBoardService.getInfoBoardList(paramInfoBoardEntity);
                }

                // 页面DTO赋值
                List<ReportClearDTO> _reportList = null;
                if (infoBoardEntityLeftList != null && infoBoardEntityLeftList.size() > 0) {
                    _reportList = new ArrayList<ReportClearDTO>();
                    for (InfoBoardEntity rep : infoBoardEntityLeftList) {
                        ReportClearDTO repDTO = new ReportClearDTO();
                        // 字段赋值操作
                        // 调用方法
                        repDTO = doBackDTOtoClientDTO(rep, repDTO, "P", plazName);
                        _reportList.add(repDTO);
                    }
                }

                reportClearBaseDTO.setListLeft(_reportList);
                reportClearBaseDTO.setLeftSectionName(plazName);

            }

            // 获取服务区的情报板数据
            if (section_id >= 60 && section_id <= 62) {
                String[] infoBoardArgs = getInfoBoardInfo(section_id, Constants.KEY_INFOBOARD_SERVICE_STAKE_ID_LIST);
                List<String> infoBoardIds = new ArrayList<String>();
                String[] iDs = StringUtils.split(infoBoardArgs[0], ":");
                String serviceName = infoBoardArgs[1];
                if (iDs != null && iDs.length > 0) {
                    for (String str : iDs) {
                        if (!"-1".endsWith(str)) {
                            infoBoardIds.add(str);
                        }
                    }

                }

                // 设置LeftList的值
                List<InfoBoardEntity> infoBoardEntityLeftList = null;
                if (infoBoardIds != null && infoBoardIds.size() > 0) {

                    InfoBoardEntity paramInfoBoardEntity = new InfoBoardEntity();
                    String[] idsAgs = infoBoardIds.toArray(new String[0]);
                    if (idsAgs != null && idsAgs.length > 0) {
                        paramInfoBoardEntity.setIdsAgs(idsAgs);
                    }
                    infoBoardEntityLeftList = iInfoBoardService.getInfoBoardList(paramInfoBoardEntity);
                }
                // 页面DTO赋值
                List<ReportClearDTO> _reportList = null;
                if (infoBoardEntityLeftList != null && infoBoardEntityLeftList.size() > 0) {
                    _reportList = new ArrayList<ReportClearDTO>();
                    for (InfoBoardEntity rep : infoBoardEntityLeftList) {
                        ReportClearDTO repDTO = new ReportClearDTO();
                        // 字段赋值操作
                        // 调用方法
                        repDTO = doBackDTOtoClientDTO(rep, repDTO, "F", serviceName);
                        _reportList.add(repDTO);
                    }

                }

                reportClearBaseDTO.setListLeft(_reportList);
                reportClearBaseDTO.setLeftSectionName(serviceName);

            }

        } catch (Exception e) {
            paramDTO.setErrFlg(true);
            reportClearBaseDTO.setReturnCode("-901");
            formMessages(paramDTO, Constants.MSG_KEY_REPORT_ERROR, null);
        }
        if (!paramDTO.isErrFlg()) {
            reportClearBaseDTO.setReturnCode("0");

        }

        toJson(response, reportClearBaseDTO);
        return null;

    }

    // 获取所有的路段信息：起点桩号、终点 桩号、高速Code
    private List<String[]> getAllSectionStakeId(List<String> infoBoardAllIds) {

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
    private List<String[]> getAllPlazStakeId(List<String> infoBoardAllIds) {

        List<String[]> allPlazStakeIdArgs = null;
        String[] plazArgs = StringUtils.split(ResourceLocator.getI18NMessage(this, Constants.KEY_INFOBOARD_PLAZ_STAKE_ID_LIST), "|");
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
                            if (infoBoardAllIds.contains(str)) {
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

    // 获取所有的服务区List信息：起点桩号、终点 桩号、高速Code
    private List<String[]> getAllServiceAreaStakeId(List<String> infoBoardAllIds) {

        List<String[]> allServiceAreaStakeIdArgs = null;
        String[] serviceAreaArgs = StringUtils.split(ResourceLocator.getI18NMessage(this, Constants.KEY_INFOBOARD_SERVICE_STAKE_ID_LIST), "|");
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
                            if (infoBoardAllIds.contains(str)) {
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

    // 字段赋值操作
    public ReportClearDTO doBackDTOtoClientDTO(InfoBoardEntity resultEntity, ReportClearDTO reportClearDTO, String direction, String name) {
        // 桩号
        reportClearDTO.setPileNumber(resultEntity.getStake_id());
        // 内容
        reportClearDTO.setReportContent(resultEntity.getIb_content());
        // 添加时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        if (resultEntity.getDeploy_time() != null) {
            reportClearDTO.setInsertTime(sdf.format(resultEntity.getDeploy_time()));
        }
        // reportClearDTO.setInsertTime(resultEntity.getDeploy_time());
        // 状态
        reportClearDTO.setStatus(resultEntity.getIb_status());
        // 路段名称
        // if ("0".equals(direction)) {
        // reportClearDTO.setSectionName(name);
        // } else if ("1".equals(direction)) {
        // reportClearDTO.setSectionName(name);
        // } else if ("P".equals(direction)) {
        // reportClearDTO.setSectionName(name);
        //
        // }
        // 添加情报板名称
        // resultEntity.getIb_addr()
        reportClearDTO.setInfoBoardName(resultEntity.getIb_addr());

        // 图片路径
        reportClearDTO.setPic_path(resultEntity.getPic_path());
        // 暂时写死gif图片
        // String hostIp = ResourceLocator.getI18NMessage(this,
        // Constants.MSG_KEY_HOST_ADDRESS);
        // reportClearDTO.setPic_path(hostIp + "img_gif/28_vms_lc_preview.gif");

        return reportClearDTO;

    }

    // 字段赋值操作(不区分方向：用于收费站、服务区的情报板)
    public ReportClearDTO doBackDTOtoClientDTO(InfoBoardEntity resultEntity, ReportClearDTO reportClearDTO) {
        // 桩号
        reportClearDTO.setPileNumber(resultEntity.getStake_id());
        // 内容
        reportClearDTO.setReportContent(resultEntity.getIb_content());
        // 添加时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        if (resultEntity.getDeploy_time() != null) {
            reportClearDTO.setInsertTime(sdf.format(resultEntity.getDeploy_time()));
        }
        // 状态
        reportClearDTO.setStatus(resultEntity.getIb_status());
        // 添加情报板名称
        reportClearDTO.setInfoBoardName(resultEntity.getIb_addr());
        // 图片路径
        reportClearDTO.setPic_path(resultEntity.getPic_path());
        return reportClearDTO;

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
                entryExitStakeId = new String[] { _sectionArgs[1], _sectionArgs[2], _sectionArgs[3], _sectionArgs[4], _sectionArgs[5] };
                break;
            }

        }
        return entryExitStakeId;

    }

    // 获取指定的路段信息：起点桩号、终点 桩号、高速Code
    private String[] getInfoBoardInfo(int sectionId, String keyName) {

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

    public ActionForward getInfoBoardListById(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        InfoBoardDTO paramDTO = (InfoBoardDTO) form;
        // test
        // paramDTO.setLocation_id(5);
        // paramDTO.setLocation_type("P");
        // 参数Entity
        InfoBoardEntity paramEntity = new InfoBoardEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果DTO
        List<InfoBoardEntity> infoBoardEntityList = null;

        // 客户端DTO
        List<ReportClearDTO> reportClearDTOList = null;
        ReportClearBaseDTO reportClearBaseDTO = new ReportClearBaseDTO();
        try {

            // 获取所有的情报板信息
            infoBoardEntityList = iInfoBoardService.getInfoBoardList(paramEntity);
            if (infoBoardEntityList != null && infoBoardEntityList.size() > 0) {
                reportClearDTOList = new ArrayList<ReportClearDTO>();
                for (InfoBoardEntity _infoBoardEntity : infoBoardEntityList) {
                    ReportClearDTO _reportClearDTO = new ReportClearDTO();
                    // 将_infoBoardEntity赋值于_reportClearDTO
                    // 添加_reportClearDTO到reportClearDTOList
                    reportClearDTOList.add(doBackDTOtoClientDTO(_infoBoardEntity, _reportClearDTO));
                }

                reportClearBaseDTO.setListLeft(reportClearDTOList);

            }

        } catch (Exception e) {

            paramDTO.setErrFlg(true);
            reportClearBaseDTO.setReturnCode("-1504");
        }

        if (!paramDTO.isErrFlg()) {
            reportClearBaseDTO.setReturnCode("0");

        }

        // android
        toJson(response, reportClearBaseDTO);
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

    public ActionForward getInfoBoardById(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        InfoBoardDTO paramDTO = (InfoBoardDTO) form;
        // test
        // paramDTO.setIb_id(1);
        // 参数Entity
        InfoBoardEntity paramEntity = new InfoBoardEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 客户端DTO
        InfoBoardPointDTO infoBoardPointDTO = new InfoBoardPointDTO();
        try {
            // 获取所有的情报板信息

            InfoBoardEntity infoBoardEntity = iInfoBoardService.getObject(paramEntity);

            CommonUtil.reflectClass(infoBoardEntity, infoBoardPointDTO);
        } catch (Exception e) {
            paramDTO.setErrFlg(true);
            infoBoardPointDTO.setReturnCode("-3080");

        }

        if (!paramDTO.isErrFlg()) {
            infoBoardPointDTO.setReturnCode("0");

        }

        // android
        toJson(response, infoBoardPointDTO);
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
    public ActionForward doGetInfoBoardPointList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        InfoBoardDTO paramDTO = (InfoBoardDTO) form;
        // test
        // paramDTO.setLatitude_b("41.69752200");
        // paramDTO.setLongitude_b("123.41989800");
        //paramDTO.setH_code("G1501");
        // 参数Entity
        InfoBoardEntity paramEntity = new InfoBoardEntity();
        // 参数DTO ->paramEntity
        CommonUtil.reflectClass(paramDTO, paramEntity);

        // 结果DTO
        InfoBoardPointListDTO resultDTO = new InfoBoardPointListDTO();
        // 结果List
        List<InfoBoardEntity> infoBoardEntityList = null;
        InfoBoardPointDTO infoBoardPointDTO = null;
        List<InfoBoardPointDTO> infoBoardPointDTOList = null;
        // DB操作
        try {

            infoBoardEntityList = iInfoBoardService.getInfoBoardPointList(paramEntity);

            if (infoBoardEntityList != null && infoBoardEntityList.size() > 0) {
                infoBoardPointDTOList = new ArrayList<InfoBoardPointDTO>();
                for (InfoBoardEntity info : infoBoardEntityList) {
                    infoBoardPointDTO = new InfoBoardPointDTO();
                    // CommonUtil.reflectClass(info, infoBoardPointDTO);
                    infoBoardPointDTO.setIb_id(info.getIb_id());
                    infoBoardPointDTO.setLongitude_b(info.getLongitude_b());
                    infoBoardPointDTO.setLatitude_b(info.getLatitude_b());
                    infoBoardPointDTO.setLocation_type(info.getLocation_type());
                    infoBoardPointDTOList.add(infoBoardPointDTO);
                }

                resultDTO.setPointList(infoBoardPointDTOList);

            }

        } catch (Exception e) {
            resultDTO.setReturnCode("-3020");
            paramDTO.setErrFlg(true);
        }

        if (!paramDTO.isErrFlg()) {
            resultDTO.setReturnCode("0");
        }

        // android
        toJson(response, resultDTO);
        // ;
        return null;
    }
}
