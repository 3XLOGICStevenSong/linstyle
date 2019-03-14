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
import com.djb.highway.road.dto.PlazaDTO;
import com.djb.highway.road.dtoclient.PlazaPointDTO;
import com.djb.highway.road.dtoclient.PlazaPointListDTO;
import com.djb.highway.road.dtoclient.TollStationBaseDTO;
import com.djb.highway.road.dtoclient.TollStationDTO;
import com.djb.highway.road.entity.CameraEntity;
import com.djb.highway.road.entity.InfoBoardEntity;
import com.djb.highway.road.entity.PlazaEntity;
import com.djb.highway.road.service.ICameraService;
import com.djb.highway.road.service.IInfoBoardService;
import com.djb.highway.road.service.IPlazaService;

@Controller("/Plaza")
public class PlazaAction extends BaseAction {

    @Autowired
    @Qualifier("iPlazaService")
    private IPlazaService iPlazaService;

    @Autowired
    @Qualifier("iCameraService")
    private ICameraService iCameraService;

    @Autowired
    @Qualifier("iInfoBoardService")
    private IInfoBoardService iInfoBoardService;

    public PlazaAction() {
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
    @SuppressWarnings("unchecked")
    public ActionForward doGetPlazaByCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        PlazaDTO paramDTO = (PlazaDTO) form;

        // 参数paramPlazaAgs
        String[] plazaCodeAgs = null;

        // paramDTO.setSection_id(52);
        // paramDTO.setPlaz_code("0521");
        // 参数Entity
        PlazaEntity paramEntity = new PlazaEntity();
        // 参数DTO -> 参数Entity
        // CommonUtil.reflectClass(paramDTO, paramEntity);

        // 结果Entity
        List<PlazaEntity> resultEntityList = new ArrayList<PlazaEntity>();
        // 结果DTO
        PlazaDTO resultDTO = new PlazaDTO();
        // 页面DTO
        TollStationDTO tollStationDTO = new TollStationDTO();

        TollStationBaseDTO tollStationBaseDTO = new TollStationBaseDTO();
        List<TollStationDTO> tollStationDTOList = null;
        // DB操作

        try {

            if (paramDTO.getSection_id() != null) {

                int section_id = paramDTO.getSection_id();
                if (section_id >= 34 && section_id <= 59) {
                    // 获取对应路段的摄像头
                    String[] paramPlazaAgs = getPlazaId(section_id);
                    // 对paramPlazaAgs[2]:收费站Code处理，并且给PlazaCodeAgs赋值
                    if (paramPlazaAgs != null) {
                        plazaCodeAgs = doParamPlazaAgs(paramPlazaAgs[0]);
                        // paramEntity.setPlaz_code(paramPlazaAgs[0]);
                    }
                }
            } else {
                // 获取收费站Code，并且给PlazaCodeAgs赋值
                plazaCodeAgs = doParamPlazaAgs(paramDTO.getPlaz_code());
                // paramEntity.setPlaz_code(paramDTO.getPlaz_code());

            }

            if (plazaCodeAgs != null) {
                paramEntity.setPlazaCodeArgs(plazaCodeAgs);
            }
            // resultEntity = iPlazaService.getObject(paramEntity);

            resultEntityList = iPlazaService.getPlazaList(paramEntity);

            // 结果Entity -> 结果DTO
            if (resultEntityList != null) {
                tollStationDTOList = new ArrayList<TollStationDTO>();
                for (PlazaEntity resultEntity : resultEntityList) {
                    // 页面DTO
                    TollStationDTO _tollStationDTO = new TollStationDTO();
                    // 初始化其他属性
                    tollStationDTOList.add(doBackDTOtoClientDTO(resultEntity, _tollStationDTO));
                }

                tollStationBaseDTO.setList(tollStationDTOList);
            }

        } catch (Exception e) {
            resultDTO.setErrFlg(true);
            tollStationBaseDTO.setReturnCode("-700");
        }

        if (!resultDTO.isErrFlg()) {
            tollStationBaseDTO.setReturnCode("0");

        }
        // android
        toJson(response, tollStationBaseDTO);
        // ;
        return null;
    }

    // 给paramPlazaAgs赋值的方法
    public String[] doParamPlazaAgs(String plazCodes) {
        String[] plazaAgs = null;
        if (!"".equals(plazCodes) && plazCodes != null) {
            plazaAgs = plazCodes.split(":");

        } else {
            return null;
        }

        return plazaAgs;
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
    // 获取未开通的收费站List
    @SuppressWarnings("unchecked")
    public ActionForward doGetPlazaListForState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        PlazaDTO paramDTO = (PlazaDTO) form;
        // test
        // 参数Entity
        PlazaEntity paramEntity = new PlazaEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果Entity

        List<PlazaEntity> resultEntityList = null;

        // 结果DTO
        // PlazaDTO resultDTO = new PlazaDTO();
        // TollStationStateDTO tollStationStateDTO = new TollStationStateDTO();
        // DB操作

        try {
            // 开通状态："1"；未开通状态："-1"
            // String plazState = ResourceLocator.getI18NMessage(this,
            // "PLAZ_STATE_OFF");
            // paramEntity.setStat(plazState);
            paramEntity.setPlaz_status("0");
            resultEntityList = iPlazaService.getPlazaList(paramEntity);
            if (resultEntityList != null) {
                List<String> plazCodeList = new ArrayList<String>();
                for (PlazaEntity plaz : resultEntityList) {
                    String plazCode = plaz.getPlaz_code();
                    if (plazCode != null) {
                        plazCodeList.add(plazCode);
                    }

                }
                // resultDTO.setPlazStateList(keyNameList);
                // tollStationStateDTO.setPlazStateList(plazCodeList);

            } else {
                // tollStationStateDTO.setPlazStateList(null);
            }

        } catch (Exception e) {
            // tollStationStateDTO.setErrFlg(true);
            // formMessages(tollStationStateDTO,
            // Constants.MSG_KEY_PLAZ_STATE_ERROR, null);
        }
        // android
        toJson(response, paramDTO);
        // ;
        return null;
    }

    public TollStationDTO doBackDTOtoClientDTO(PlazaEntity resultEntity, TollStationDTO tollStationDTO) {

        // 收费站名称
        tollStationDTO.setStationName(resultEntity.getPlaz_name());
        tollStationDTO.setToll_station_code(resultEntity.getPlaz_code());
        // 高速名
        tollStationDTO.setHighWayName(resultEntity.getH_name());

        // 判断ETC自动收费口:0 没有,1 有
        tollStationDTO.setEtc(resultEntity.getEtc_flg());
        // 桩号
        tollStationDTO.setMileAge(resultEntity.getStake_id());

        // 城市
        tollStationDTO.setCtiy(resultEntity.getPlaz_city());
        // 收费站状态 0开通，1出口封闭，2入口封闭，3双向封闭
        tollStationDTO.setStationState(resultEntity.getPlaz_status());
        // 出口车道数
        tollStationDTO.setExitRoadNum(resultEntity.getExit_all());
        // 入口车道数
        tollStationDTO.setEntranceRoadNum(resultEntity.getEntry_all());
        // 出口通达地点
        tollStationDTO.setExitReach(resultEntity.getPlaz_exit_place());
        // 入口通达地点
        tollStationDTO.setEntranceReach(resultEntity.getPlaz_enter_place());
        // 出口拥堵指数
        tollStationDTO.setExitFlowInfo(resultEntity.getExit_pass_condition());
        // 入口拥堵指数
        tollStationDTO.setEntranceFlowInfo(resultEntity.getEntry_pass_condition());
        // 即时影响图片路径
        // tollStationDTO.setSketchMap(resultEntity.getPic_url());
        // 摄像头ID
        tollStationDTO.setC_id(resultEntity.getC_id());
        // 初始化摄像头iDs
        List<Integer> c_ids = initCameraOfPlaz(resultEntity);
        if (c_ids != null && c_ids.size() > 0) {
            tollStationDTO.setC_ids(c_ids);
        }
        // 收费站ID
        if (resultEntity.getC_id() != null) {
            tollStationDTO.setLocation_id(resultEntity.getPlaz_id());
        }
        // 情报板是否显示的标示
        tollStationDTO.setIb_flag(initInfoBoardOfPlaz(resultEntity));

        // 百度经纬度：
        if (resultEntity.getLatitude_b() != null && resultEntity.getLongitude_b() != null) {
            tollStationDTO.setLocation_desc(resultEntity.getLatitude_b() + "," + resultEntity.getLongitude_b());
        }

        // 出入口示意图路径
        String hostIp = ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS);

        if (resultEntity.getPic_url() != null && !("".equals(resultEntity.getPic_url()))) {
            tollStationDTO.setSketchMap(hostIp + "img_plaz/" + resultEntity.getPic_url().trim());
        }

        return tollStationDTO;

    }

    @SuppressWarnings("unused")
    private String[] getPlazaId(int plazaId) {

        String _plazaId = String.valueOf(plazaId);

        if (_plazaId == null) {
            return null;
        }

        String[] resultplazaID = null;
        String[] plazaArgs = StringUtils.split(ResourceLocator.getI18NMessage(this, Constants.KEY_PLAZ_STAKE_ID_LIST), "|");
        for (String plaza : plazaArgs) {
            String[] _plazaArgs = StringUtils.split(plaza, ",");
            if (_plazaId.equals(_plazaArgs[0])) {
                resultplazaID = new String[] { _plazaArgs[2] };
                break;
            }

        }
        return resultplazaID;

    }

    // 获取收费站的摄像头IDs
    public ArrayList<Integer> initCameraOfPlaz(PlazaEntity plazaEntity) {

        // 参数Entity
        CameraEntity paramEntity = new CameraEntity();
        // 获取的摄像头信息
        List<CameraEntity> cameraEntityList = null;
        // 返回的摄像头IDs结果集合
        ArrayList<Integer> ids = null;
        paramEntity.setLocation_id(plazaEntity.getPlaz_id());
        // P:标示收费站的摄像头
        paramEntity.setLocation_type("P");
        // 获取对应的摄像头信息
        cameraEntityList = iCameraService.getCameraList(paramEntity);

        if (cameraEntityList != null && cameraEntityList.size() > 0) {
            ids = new ArrayList<Integer>();
            for (CameraEntity cameraEntity : cameraEntityList) {
                ids.add(cameraEntity.getC_id());
            }
        }

        return ids;
    }

    // 判断收费站的情报板是否显示
    public boolean initInfoBoardOfPlaz(PlazaEntity plazaEntity) {

        // 参数Entity
        InfoBoardEntity paramEntity = new InfoBoardEntity();
        // 获取的情报板信息
        List<InfoBoardEntity> infoBoardEntityList = null;
        // 返回的情报板IDs结果集合
        paramEntity.setLocation_id(plazaEntity.getPlaz_id());
        paramEntity.setLocation_type("P");
        // 获取对应的情报板信息
        infoBoardEntityList = iInfoBoardService.getInfoBoardList(paramEntity);
        if (infoBoardEntityList != null && infoBoardEntityList.size() > 0) {
            return true;
        } else {
            return false;
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
    @SuppressWarnings("unchecked")
    public ActionForward doGetPlazaById(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        PlazaDTO paramDTO = (PlazaDTO) form;

        // test
        // paramDTO.setPlaz_id(2);

        // 参数Entity
        PlazaEntity paramEntity = new PlazaEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果DTO
        PlazaDTO resultDTO = new PlazaDTO();
        PlazaEntity resultEntity = new PlazaEntity();

        // 页面DTO
        TollStationBaseDTO tollStationBaseDTO = new TollStationBaseDTO();
        List<TollStationDTO> tollStationDTOList = null;
        // DB操作

        try {
            resultEntity = iPlazaService.getObject(paramEntity);

            // 结果Entity -> 结果DTO
            if (resultEntity != null) {
                tollStationDTOList = new ArrayList<TollStationDTO>();
                TollStationDTO _tollStationDTO = new TollStationDTO();
                tollStationDTOList.add(doBackDTOtoClientDTO(resultEntity, _tollStationDTO));
                tollStationBaseDTO.setList(tollStationDTOList);
            }

        } catch (Exception e) {
            resultDTO.setErrFlg(true);
            tollStationBaseDTO.setReturnCode("-3090");
        }

        if (!resultDTO.isErrFlg()) {
            tollStationBaseDTO.setReturnCode("0");

        }
        // android
        toJson(response, tollStationBaseDTO);
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
    public ActionForward doGetPlazPointList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        PlazaDTO paramDTO = (PlazaDTO) form;
        // test
       //paramDTO.setLatitude_b("42.727644");
      // paramDTO.setLongitude_b("124.730859");
        //paramDTO.setH_code("G1501");
        // 参数Entity
        PlazaEntity paramEntity = new PlazaEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);
        List<PlazaEntity> plazEntityList = null;
        List<PlazaPointDTO> plazDTOList = null;
        // 客户端DTO
        PlazaPointListDTO plazaPointListDTO = new PlazaPointListDTO();
        // DB操作
        try {

            plazEntityList = iPlazaService.getPlazaPointList(paramEntity);

            if (plazEntityList != null && plazEntityList.size() > 0) {

                plazDTOList = new ArrayList<PlazaPointDTO>();
                for (PlazaEntity plaz : plazEntityList) {
                    PlazaPointDTO plazDTO = new PlazaPointDTO();
                    CommonUtil.reflectClass(plaz, plazDTO);
                    plazDTOList.add(plazDTO);
                }
                plazaPointListDTO.setPointList(plazDTOList);

            }

        } catch (Exception e) {
            plazaPointListDTO.setReturnCode("-3030");
            paramDTO.setErrFlg(true);
        }

        if (!paramDTO.isErrFlg()) {
            plazaPointListDTO.setReturnCode("0");
        }

        // android
        toJson(response, plazaPointListDTO);
        // ;
        return null;
    }

}
