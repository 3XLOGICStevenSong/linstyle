package com.djb.highway.road.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
import com.djb.highway.common.util.RoadInfoUtil;
import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.road.dao.IHighWayDao;
import com.djb.highway.road.dto.RoadControlInfoDTO;
import com.djb.highway.road.dto.push.RoadControlInfoData;
import com.djb.highway.road.dto.push.RoadControlInfoPushDTO;
import com.djb.highway.road.dtoclient.InfoIdsDetailClientDTO;
import com.djb.highway.road.dtoclient.InfoIdsDetailDTO;
import com.djb.highway.road.dtoclient.RoadControlInfoClearBaseDTO;
import com.djb.highway.road.dtoclient.RoadControlInfoClearDTO;
import com.djb.highway.road.dtoclient.RoadEventBaseDTO;
import com.djb.highway.road.dtoclient.RoadEventDTO;
import com.djb.highway.road.entity.HighWayEntity;
import com.djb.highway.road.entity.RoadControlInfoEntity;
import com.djb.highway.road.service.IRoadControlInfoService;

@Controller("/RoadControlInfo")
public class RoadControlInfoAction extends BaseAction {

    @Autowired
    @Qualifier("iRoadControlInfoService")
    private IRoadControlInfoService iRoadControlInfoService;

    @Autowired
    @Qualifier("highWayDao")
    private IHighWayDao highWayDao;

    // 经过的收费站map
    private static Map<String, HighWayEntity> highwayMap;

    public RoadControlInfoAction() {
        super();
    }

    @SuppressWarnings("unchecked")
    private Integer getHIdByHCode(String h_code) {

        if (h_code == null) {
            return null;
        }
        if (highwayMap == null) {
            highwayMap = (Map<String, HighWayEntity>) highWayDao.selectMap("h_code");
        }

        Integer h_id = null;
        HighWayEntity highWayEntity = highwayMap.get(h_code);
        if (highWayEntity != null) {
            h_id = highWayEntity.getH_id();
        }
        return h_id;
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

    // 分页处理道路事件
    public ActionForward doGetRoadControlInfoList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        RoadControlInfoDTO paramDTO = (RoadControlInfoDTO) form;
        // test
        // paramDTO.setH_code("G1501");

        if ("All".equals(paramDTO.getH_code())) {
            paramDTO.setH_code(null);
        }
        // test分页
        // paramDTO.setStartRow(1);
        // paramDTO.setEndRow(5);

        // 结果DTO
        RoadControlInfoDTO resultDTO = new RoadControlInfoDTO();
        RoadControlInfoClearBaseDTO resultClearBaseDTO = new RoadControlInfoClearBaseDTO();
        // 参数Entity
        RoadControlInfoEntity paramEntity = new RoadControlInfoEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);
        paramEntity.setStartRow(paramDTO.getStartRow());
        paramEntity.setEndRow(paramDTO.getEndRow());
        // 结果Entity
        List<RoadControlInfoEntity> resultEntityList = null;
        // 结果DTO
        List<RoadControlInfoDTO> resultDTOList = new ArrayList<RoadControlInfoDTO>();
        String[] roadControlIdArray = null;
        // DB操作

        try {

            // test

            // String[] roadControlIdArray = new String[] { "72321", "72332" };
            if (!"".equals(paramDTO.getRoadControlIdArgs()) && paramDTO.getRoadControlIdArgs() != null) {
                roadControlIdArray = paramDTO.getRoadControlIdArgs().split(",");
                paramEntity.setRoadControlIdArgs(roadControlIdArray);
            }

            resultEntityList = iRoadControlInfoService.getRoadControlInfoPageList(paramEntity);
            if (resultEntityList != null) {
                for (RoadControlInfoEntity r : resultEntityList) {
                    RoadControlInfoDTO _resultDTO = new RoadControlInfoDTO();
                    CommonUtil.reflectClass(r, _resultDTO);
                    resultDTOList.add(_resultDTO);
                }
                resultDTO.setList(resultDTOList);
            }

            // 给页面DTO赋值
            // 结果DTO
            List<RoadControlInfoClearDTO> resultListClearDTO = new ArrayList<RoadControlInfoClearDTO>();
            for (RoadControlInfoDTO section : resultDTO.getList()) {
                RoadControlInfoClearDTO _resultDTO = new RoadControlInfoClearDTO();

                if (section.getH_id() == null) {
                    _resultDTO.setH_id(this.getHIdByHCode(section.getH_code()));
                } else {
                    Integer h_id = this.getHIdByHCode(section.getH_code());
                    if (h_id != null) {
                        _resultDTO.setH_id(h_id);
                    }
                }

                _resultDTO.setH_code(section.getH_code());
                // 之前的版本没有返回该字段
                _resultDTO.setRci_id(section.getRci_id());
                _resultDTO.setH_name(section.getH_name());

                // 管制范围：暂时为：起点桩号-终点桩号
                String control_scope = section.getStart_stake_id() + "公里至" + section.getEnd_stake_id() + "公里";
                _resultDTO.setControl_scope(control_scope);

                _resultDTO.setControl_mode(section.getControl_mode());
                _resultDTO.setControl_reason(section.getRci_type());

                // 开始时间
                _resultDTO.setStart_time(CommonUtil.dateToString(section.getStart_time(), "yyyy年MM月dd日 HH时"));
                // 预计结束时间
                _resultDTO.setPlan_end_time(CommonUtil.dateToString(section.getPlan_end_time(), "yyyy年MM月dd日 HH时"));
                // 发布时间
                _resultDTO.setDeploy_time(CommonUtil.dateToString(section.getDeploy_time(), "yyyy/MM/dd HH:mm"));

                // 所在方向
                _resultDTO.setDirection(section.getControl_direction());

                // 绕行方案标记
                if (section.getPassing_round_pic_url() == null || section.getPassing_round_desc() == null) {
                    _resultDTO.setRoundFlag(true);
                } else {
                    _resultDTO.setRoundFlag(false);
                }

                // 事件描述
                _resultDTO.setEventInfo(section.getRci_content());
                // 绕行方案URL
                _resultDTO.setRoundingUrl(section.getPassing_round_pic_url());
                // 绕行方案Content
                _resultDTO.setRoundingWord(section.getPassing_round_desc());

                resultListClearDTO.add(_resultDTO);
            }
            resultClearBaseDTO.setList(resultListClearDTO);

        } catch (Exception e) {

            // resultDTO.setErrFlg(true);
            resultDTO.setErrFlg(true);
            resultClearBaseDTO.setReturnCode("-800");
            // formMessages(resultClearBaseDTO,
            // Constants.MSG_KEY_ROADCONTROLINFO_ERROR, null);
        }
        if (!resultDTO.isErrFlg()) {
            resultClearBaseDTO.setReturnCode("0");
        }
        // android
        toJson(response, resultClearBaseDTO);
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
    public ActionForward getControlInfoBySectionId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // PrintWriter out = response.getWriter();

        // 参数DTO
        RoadControlInfoDTO paramDTO = (RoadControlInfoDTO) form;

        // test
        // paramDTO.setSection_id(1);

        if (paramDTO.getSection_id() == null) {
            return null;
        }

        // 结果DTO
        RoadControlInfoDTO resultDTO = new RoadControlInfoDTO();
        // 参数Entity
        RoadControlInfoEntity paramEntity = new RoadControlInfoEntity();

        String[] entryExitStakeId = getStakeId(paramDTO.getSection_id().toString());
        if (entryExitStakeId == null) {
            return null;
        }

        paramEntity.setStart_stake_id(entryExitStakeId[0]);
        paramEntity.setEnd_stake_id(entryExitStakeId[1]);
        paramEntity.setH_code(entryExitStakeId[2]);

        // 结果Entity
        List<RoadControlInfoEntity> resultEntityList = null;
        // 临时Entity
        List<RoadControlInfoEntity> _resultEntityList = null;

        // 页面结果DTOList
        List<RoadEventDTO> roadEventDTOList = new ArrayList<RoadEventDTO>();
        RoadEventBaseDTO roadEventBaseDTO = new RoadEventBaseDTO();
        try {

            // 临时参数Entity
            RoadControlInfoEntity _paramEntity = new RoadControlInfoEntity();
            _paramEntity.setH_code(paramEntity.getH_code());
            _resultEntityList = iRoadControlInfoService.getRoadControlInfoList(_paramEntity);
            if (_resultEntityList != null) {
                // 判断当前路段和道路事件是否有交集，如果有交集，将当前事件添加至道路事件List中
                resultEntityList = new ArrayList<RoadControlInfoEntity>();
                for (RoadControlInfoEntity rciEntity : _resultEntityList) {
                    boolean isShow = false;
                    if (rciEntity != null && rciEntity.getH_code() != null) {
                        float entry_stake_id = CommonUtil.toFloat(rciEntity.getStart_stake_id(), 0);
                        float exit_stake_id = CommonUtil.toFloat(rciEntity.getEnd_stake_id(), 0);
                        if (entry_stake_id == 0 && exit_stake_id == 0) {
                            continue;
                        }
                        isShow = RoadInfoUtil.isShowRciElement(entryExitStakeId[2], rciEntity.getH_code(), rciEntity.getControl_direction(),
                                        rciEntity.getStart_stake_id(), rciEntity.getEnd_stake_id(), entryExitStakeId[0], entryExitStakeId[1]);
                        if (isShow) {
                            resultEntityList.add(rciEntity);
                        }
                    }

                }
            }

            // 给页面DTO字段赋值
            if (resultEntityList != null && resultEntityList.size() != 0) {
                for (RoadControlInfoEntity r : resultEntityList) {
                    RoadEventDTO _resultDTO = new RoadEventDTO();
                    // 赋值操作start
                    // 高速名称
                    _resultDTO.setHighwayName(r.getH_name());
                    // 高速Id
                    if (r.getH_id() != null) {
                        _resultDTO.setHighwayId(r.getH_id().toString());
                    } else {
                        Integer h_id = this.getHIdByHCode(paramEntity.getH_code());
                        if (h_id != null) {
                            _resultDTO.setHighwayId(h_id.toString());
                        }
                    }

                    // 管制类型
                    _resultDTO.setEventType(r.getRci_type());

                    // 管制方式
                    _resultDTO.setControlWay(r.getControl_mode());
                    // 管制范围
                    // _resultDTO.setControlScope(r.getControl_scope());
                    // 管制范围：暂时为：起点桩号-终点桩号
                    String control_scope = r.getStart_stake_id() + "公里至" + r.getEnd_stake_id() + "公里";
                    _resultDTO.setControlScope(control_scope);

                    // 所在方向
                    _resultDTO.setDirection(r.getControl_direction());

                    // 绕行方案标记
                    if (r.getPassing_round_pic_url() == null || r.getPassing_round_desc() == null) {
                        _resultDTO.setRoundFlag(true);
                    } else {
                        _resultDTO.setRoundFlag(false);
                    }

                    // 事件描述
                    _resultDTO.setEventInfo(r.getRci_content());
                    // 绕行方案URL
                    _resultDTO.setRoundingUrl(r.getPassing_round_pic_url());
                    // 绕行方案Content
                    _resultDTO.setRoundingWord(r.getPassing_round_desc());

                    // 开始时间
                    _resultDTO.setStartTime(CommonUtil.dateToString(r.getStart_time(), "yyyy年MM月dd日 HH时"));
                    // 预计结束时间
                    _resultDTO.setEndTime(CommonUtil.dateToString(r.getPlan_end_time(), "yyyy年MM月dd日 HH时"));
                    // 发布时间
                    _resultDTO.setPublishTime(CommonUtil.dateToString(r.getDeploy_time(), "yyyy/MM/dd HH:mm"));

                    roadEventDTOList.add(_resultDTO);
                }
                roadEventBaseDTO.setRoadEventBaseDTOList(roadEventDTOList);
            }

        } catch (Exception e) {
            // out.println(this.getStackTrace(e));
            resultDTO.setErrFlg(true);
            roadEventBaseDTO.setReturnCode("-1300");

        }

        if (!resultDTO.isErrFlg()) {
            roadEventBaseDTO.setReturnCode("0");

        }

        // android
        toJson(response, roadEventBaseDTO);
        // ;
        return null;
    }

    private String[] getStakeId(String sectionId) {

        if (sectionId == null) {
            return null;
        }

        String[] entryExitStakeId = null;
        String[] sectionArgs = StringUtils.split(ResourceLocator.getI18NMessage(this, Constants.STR_KEY_SECION_STAKE_ID_LIST), "|");
        for (String section : sectionArgs) {
            String[] _sectionArgs = StringUtils.split(section, ",");
            if (sectionId.equals(_sectionArgs[0])) {
                entryExitStakeId = new String[] { _sectionArgs[1], _sectionArgs[2], _sectionArgs[3] };
                break;
            }

        }
        return entryExitStakeId;

    }

    private List<String[]> getAllSectionStakeId() {

        List<String[]> allSectionStakeIdArgs = null;
        String[] sectionArgs = StringUtils.split(ResourceLocator.getI18NMessage(this, Constants.STR_KEY_SECION_STAKE_ID_LIST), "|");
        if (sectionArgs != null && sectionArgs.length > 0) {
            allSectionStakeIdArgs = new ArrayList<String[]>();

            for (String section : sectionArgs) {
                String[] _sectionArgs = StringUtils.split(section, ",");
                allSectionStakeIdArgs.add(_sectionArgs);
            }
        }

        return allSectionStakeIdArgs;

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
    public ActionForward getShowRciList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 结果DTO
        RoadControlInfoDTO resultDTO = new RoadControlInfoDTO();
        // 页面DTO
        RoadControlInfoClearBaseDTO roadControlInfoClearBaseDTO = new RoadControlInfoClearBaseDTO();

        List<String[]> allSectionStakeIdArgs = getAllSectionStakeId();

        if (allSectionStakeIdArgs == null) {
            return null;
        }

        List<String> secIds = null;
        secIds = new ArrayList<String>();
        try {

            // 所有途经高速的List
            String[] h_codeArgs = new String[] { "G1113", "G1", "G1501", "G15", "G1212" };

            RoadControlInfoEntity paramRoadControlInfoEntity = new RoadControlInfoEntity();
            paramRoadControlInfoEntity.setH_codeArgs(h_codeArgs);
            // 道路管制信息
            List<RoadControlInfoEntity> roadControlInfoEntityList = (List<RoadControlInfoEntity>) iRoadControlInfoService
                            .getRoadControlInfoList(paramRoadControlInfoEntity);

            for (String[] sectionArgs : allSectionStakeIdArgs) {
                boolean isShow = false;
                if (sectionArgs != null) {
                    isShow = false;

                    if (roadControlInfoEntityList != null) {
                        for (RoadControlInfoEntity rciEntity : roadControlInfoEntityList) {

                            if (sectionArgs[3] != null && rciEntity != null && rciEntity.getH_code() != null) {
                                float entry_stake_id = CommonUtil.toFloat(rciEntity.getStart_stake_id(), 0);
                                float exit_stake_id = CommonUtil.toFloat(rciEntity.getEnd_stake_id(), 0);
                                if (entry_stake_id == 0 && exit_stake_id == 0) {
                                    continue;
                                }
                                isShow = RoadInfoUtil.isShowRciElement(sectionArgs[3], rciEntity.getH_code(), rciEntity.getControl_direction(),
                                                rciEntity.getStart_stake_id(), rciEntity.getEnd_stake_id(), sectionArgs[1], sectionArgs[2]);
                                if (isShow) {
                                    secIds.add(sectionArgs[0]);
                                    break;
                                }
                            }

                        }

                    }
                }

            }

            // 过滤重复的secIds
            HashSet<String> secIdsSet = null;
            ArrayList<String> _secIds = null;

            if (secIds != null && secIds.size() > 0) {
                secIdsSet = new HashSet<String>();
                for (String str : secIds) {
                    secIdsSet.add(str);
                }
                _secIds = new ArrayList<String>();
                for (String str : secIdsSet) {
                    _secIds.add(str);
                }
            }

            // // 排序allIds：升序
            // if (_secIds != null && _secIds.size() > 0) {
            // Collections.sort(_secIds);
            // }

            roadControlInfoClearBaseDTO.setSecIds(_secIds);

        } catch (Exception e) {
            resultDTO.setErrFlg(true);
            roadControlInfoClearBaseDTO.setReturnCode("-1500");

        }

        if (!resultDTO.isErrFlg()) {
            roadControlInfoClearBaseDTO.setReturnCode("0");

        }

        // android
        toJson(response, roadControlInfoClearBaseDTO);
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

    // 分页处理道路事件(赋值)
    @SuppressWarnings("unchecked")
    public ActionForward doGetRoadControlInfoListByIds(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        RoadControlInfoDTO paramDTO = (RoadControlInfoDTO) form;
        // test
        // paramDTO.setH_code("G1501");

        if ("All".equals(paramDTO.getH_code())) {
            paramDTO.setH_code(null);
        }
        // test分页
        // paramDTO.setStartRow(1);
        // paramDTO.setEndRow(5);

        // 结果DTO
        RoadControlInfoDTO resultDTO = new RoadControlInfoDTO();
        // 参数Entity
        RoadControlInfoEntity paramEntity = new RoadControlInfoEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);
        paramEntity.setStartRow(paramDTO.getStartRow());
        paramEntity.setEndRow(paramDTO.getEndRow());
        // 结果Entity
        List<RoadControlInfoEntity> resultEntityList = null;
        // 结果DTO
        List<RoadEventDTO> roadEventDTOList = new ArrayList<RoadEventDTO>();
        String[] roadControlIdArray = null;
        // 页面DTO
        RoadEventBaseDTO roadEventBaseDTO = new RoadEventBaseDTO();
        // DB操作

        try {

            // test
            // roadControlIdArray = new String[] { "72321", "72332" };
            if (!"".equals(paramDTO.getRoadControlIdArgs()) && paramDTO.getRoadControlIdArgs() != null) {
                roadControlIdArray = paramDTO.getRoadControlIdArgs().split(",");
                paramEntity.setRoadControlIdArgs(roadControlIdArray);
            }

            resultEntityList = iRoadControlInfoService.getRoadControlInfoList(paramEntity);

            // 页面赋值
            // 给页面DTO字段赋值
            if (resultEntityList != null && resultEntityList.size() != 0) {
                for (RoadControlInfoEntity r : resultEntityList) {
                    RoadEventDTO _resultDTO = new RoadEventDTO();
                    // 赋值操作start
                    // 高速名称
                    _resultDTO.setHighwayName(r.getH_name());
                    // 高速Id
                    if (r.getH_id() != null) {
                        _resultDTO.setHighwayId(r.getH_id().toString());
                    } else {
                        Integer h_id = this.getHIdByHCode(r.getH_code());
                        if (h_id != null) {
                            _resultDTO.setHighwayId(h_id.toString());
                        }
                    }

                    // 管制类型
                    _resultDTO.setEventType(r.getRci_type());

                    // 管制方式
                    _resultDTO.setControlWay(r.getControl_mode());
                    // 管制范围
                    // _resultDTO.setControlScope(r.getControl_scope());
                    // 管制范围：暂时为：起点桩号-终点桩号
                    String control_scope = r.getStart_stake_id() + "公里至" + r.getEnd_stake_id() + "公里";
                    _resultDTO.setControlScope(control_scope);

                    // 所在方向
                    _resultDTO.setDirection(r.getControl_direction());

                    // 绕行方案标记
                    // true 为显示绕行方案，false为不显示绕行方案
                    if (r.getPassing_round_pic_url() == null && r.getPassing_round_desc() == null) {
                        _resultDTO.setRoundFlag(false);
                    } else {
                        _resultDTO.setRoundFlag(true);
                    }

                    // 事件描述
                    _resultDTO.setEventInfo(r.getRci_content());
                    // 绕行方案URL
                    _resultDTO.setRoundingUrl(r.getPassing_round_pic_url());
                    // 绕行方案Content
                    _resultDTO.setRoundingWord(r.getPassing_round_desc());

                    // 开始时间
                    _resultDTO.setStartTime(CommonUtil.dateToString(r.getStart_time(), "yyyy年MM月dd日 HH时"));
                    // 预计结束时间
                    _resultDTO.setEndTime(CommonUtil.dateToString(r.getPlan_end_time(), "yyyy年MM月dd日 HH时"));
                    // 发布时间
                    _resultDTO.setPublishTime(CommonUtil.dateToString(r.getDeploy_time(), "yyyy/MM/dd HH:mm"));

                    roadEventDTOList.add(_resultDTO);
                }
                roadEventBaseDTO.setRoadEventBaseDTOList(roadEventDTOList);
            }

        } catch (Exception e) {

            // resultDTO.setErrFlg(true);
            resultDTO.setErrFlg(true);
            roadEventBaseDTO.setReturnCode("-801");
            // formMessages(resultClearBaseDTO,
            // Constants.MSG_KEY_ROADCONTROLINFO_ERROR, null);
        }
        if (!resultDTO.isErrFlg()) {
            roadEventBaseDTO.setReturnCode("0");
        }
        // android
        toJson(response, roadEventBaseDTO);
        // ;
        return null;
    }

    // 使用百度云推送，推送道理管制事件
    public ActionForward doPushRoadControlInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        RoadControlInfoDTO paramDTO = (RoadControlInfoDTO) form;

        // 参数Entity
        RoadControlInfoEntity paramEntity = new RoadControlInfoEntity();

        // 结果Entity
        List<RoadControlInfoEntity> resultEntityList = null;

        // 结果DTO
        RoadControlInfoPushDTO resultDTO = new RoadControlInfoPushDTO();
        List<RoadControlInfoData> resultDTOList = null;
        try {
            // 获取一小时内的道理管制事件
            paramEntity.setCreate_time(getCurrentDate());
            resultEntityList = iRoadControlInfoService.getRoadControlInfoList(paramEntity);

            if (resultEntityList != null && resultEntityList.size() != 0) {

                resultDTOList = new ArrayList<RoadControlInfoData>();
                String[] gzYyContentArray = StringUtils.split(ResourceLocator.getI18NMessage(this, Constants.KEY_RCI_TYPE), "|");
                String[] gzFsContentArray = StringUtils.split(ResourceLocator.getI18NMessage(this, Constants.KEY_RCI_CONTROL_MODE), "|");

                for (RoadControlInfoEntity r : resultEntityList) {
                    RoadControlInfoData _resultDTO = new RoadControlInfoData();
                    // 赋值操作start
                    // 高速名称
                    _resultDTO.setH_name(r.getH_name());
                    // 高速Id
                    if (r.getH_id() != null) {
                        _resultDTO.setH_id(Integer.parseInt(r.getH_id().toString()));
                    } else {
                        Integer h_id = this.getHIdByHCode(r.getH_code());
                        if (h_id != null) {
                            _resultDTO.setH_id(h_id);
                        }
                    }

                    // 管制范围：暂时为：起点桩号-终点桩号
                    String control_scope = r.getStart_stake_id() + "公里至" + r.getEnd_stake_id() + "公里";
                    // 开始时间
                    String startTime = CommonUtil.dateToString(r.getStart_time(), "yyyy年MM月dd日 HH时");
                    // 预计结束时间
                    String endTime = CommonUtil.dateToString(r.getPlan_end_time(), "yyyy年MM月dd日 HH时");
                    // 发布时间
                    String deployTime = CommonUtil.dateToString(r.getDeploy_time(), "yyyy/MM/dd HH:mm");

                    StringBuffer tempContent = new StringBuffer();
                    if (r.getH_name() != null && !"".equals(r.getH_name())) {
                        tempContent.append(r.getH_name());
                        tempContent.append(":");
                    }
                    if (control_scope != null && !"".equals(control_scope)) {
                        tempContent.append("管制范围:" + control_scope);
                        tempContent.append("。");
                    }
                    if (_resultDTO.getControl_mode() != null && !"".equals(_resultDTO.getControl_mode())) {
                        // 管制方式：
                        String gzFs = gzFsContentArray[CommonUtil.toInt(_resultDTO.getControl_mode(), 0) + 1];
                        tempContent.append("管制方式:");
                        tempContent.append(gzFs);
                        tempContent.append("。");
                    }

                    if (_resultDTO.getRci_type() != null && !"".equals(_resultDTO.getRci_type())) {
                        // 管制原因：
                        String gzYy = gzYyContentArray[CommonUtil.toInt(_resultDTO.getRci_type(), 0) + 1];
                        tempContent.append("管制原因:");
                        tempContent.append(gzYy);
                    }

                    if (startTime != null && !"".equals(startTime)) {

                        tempContent.append("开始时间:");
                        tempContent.append(startTime);
                        tempContent.append("。");
                    }

                    if (endTime != null && !"".equals(endTime)) {
                        tempContent.append("预计结束时间:");
                        tempContent.append(endTime);
                        tempContent.append("。");
                    }
                    if (deployTime != null && !"".equals(deployTime)) {
                        tempContent.append("发布时间:");
                        tempContent.append(deployTime);
                        tempContent.append("。");
                    }

                    // String content = r.getH_name() + ":" + "管制范围:" +
                    // control_scope + "。管制方式:" + "。管制原因:" + "。开始时间:" +
                    // startTime + "。预计结束时间:" + endTime
                    // + "。发布时间:" + deployTime + "。";
                    String content = tempContent.toString();

                    // 道路事件ID
                    _resultDTO.setRci_id(r.getRci_id());
                    // 事件描述
                    _resultDTO.setRci_content(content);

                    resultDTOList.add(_resultDTO);
                }

                resultDTO.setRoadControlInfo_data(resultDTOList);

            }
        } catch (Exception e) {
            paramDTO.setErrFlg(true);
            resultDTO.setReturnCode("-5000");

        }

        if (!paramDTO.isErrFlg()) {
            resultDTO.setReturnCode("0");
        }

        // android
        toJson(response, resultDTO);
        // ;
        return null;
    }

    // 获取距离当前时间前一小时的时间
    public Date getCurrentDate() {
        Date tempDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tempDate);
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        Date curDate = calendar.getTime();
        return curDate;
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
    public ActionForward getShowRciListNewOne(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 结果DTO
        RoadControlInfoDTO resultDTO = new RoadControlInfoDTO();
        // 页面DTO
        InfoIdsDetailClientDTO infoIdsDetailClientDTO = new InfoIdsDetailClientDTO();

        List<String[]> allSectionStakeIdArgs = getAllSectionStakeId();

        if (allSectionStakeIdArgs == null) {
            return null;
        }

        List<InfoIdsDetailDTO> infoIdsDetailDTOs = null;
        try {

            // 所有途经高速的List
            String[] h_codeArgs = new String[] { "G1113", "G1", "G1501", "G15", "G1212" };

            RoadControlInfoEntity paramRoadControlInfoEntity = new RoadControlInfoEntity();
            paramRoadControlInfoEntity.setH_codeArgs(h_codeArgs);
            // 道路管制信息
            List<RoadControlInfoEntity> roadControlInfoEntityList = (List<RoadControlInfoEntity>) iRoadControlInfoService
                            .getRoadControlInfoList(paramRoadControlInfoEntity);
            List<String> sectionIds = null;
            if (roadControlInfoEntityList != null && roadControlInfoEntityList.size() > 0) {

                infoIdsDetailDTOs = new ArrayList<InfoIdsDetailDTO>();

                for (RoadControlInfoEntity rciEntity : roadControlInfoEntityList) {
                    sectionIds = new ArrayList<String>();
                    for (String[] sectionArgs : allSectionStakeIdArgs) {
                        boolean isShow = false;
                        if (sectionArgs != null) {
                            isShow = false;

                            if (sectionArgs[3] != null && rciEntity != null && rciEntity.getH_code() != null) {
                                float entry_stake_id = CommonUtil.toFloat(rciEntity.getStart_stake_id(), 0);
                                float exit_stake_id = CommonUtil.toFloat(rciEntity.getEnd_stake_id(), 0);
                                if (entry_stake_id == 0 && exit_stake_id == 0) {
                                    continue;
                                }
                                isShow = RoadInfoUtil.isShowRciElement(sectionArgs[3], rciEntity.getH_code(), rciEntity.getControl_direction(),
                                                rciEntity.getStart_stake_id(), rciEntity.getEnd_stake_id(), sectionArgs[1], sectionArgs[2]);
                                if (isShow) {
                                    // 一个事件对应着多个路段
                                    sectionIds.add(sectionArgs[0]);

                                }
                            }

                        }

                    }

                    // 对每个道路事件循环后，如果有路段Id，添加至结果集
                    if (sectionIds != null && sectionIds.size() > 0) {
                        InfoIdsDetailDTO infoIdsDetailDTO = new InfoIdsDetailDTO();
                        infoIdsDetailDTO.setIconId(rciEntity.getRci_id());
                        infoIdsDetailDTO.setSectionIds(sectionIds);
                        // 添加至客户端DTO
                        infoIdsDetailDTOs.add(infoIdsDetailDTO);
                    }

                }

            }

            infoIdsDetailClientDTO.setSecIds(infoIdsDetailDTOs);

        } catch (Exception e) {
            resultDTO.setErrFlg(true);
            infoIdsDetailClientDTO.setReturnCode("-1500");

        }

        if (!resultDTO.isErrFlg()) {
            infoIdsDetailClientDTO.setReturnCode("0");

        }

        // android
        toJson(response, infoIdsDetailClientDTO);
        // ;
        return null;
    }

}
