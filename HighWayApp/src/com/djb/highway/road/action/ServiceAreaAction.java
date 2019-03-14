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
import com.djb.highway.road.dto.ServiceAreaDTO;
import com.djb.highway.road.dtoclient.ServiceAreaClientDTO;
import com.djb.highway.road.dtoclient.ServiceAreaInfoDTO;
import com.djb.highway.road.dtoclient.ServiceAreaPointDTO;
import com.djb.highway.road.dtoclient.ServiceAreaPointListDTO;
import com.djb.highway.road.entity.CameraEntity;
import com.djb.highway.road.entity.InfoBoardEntity;
import com.djb.highway.road.entity.ServiceAreaEntity;
import com.djb.highway.road.service.ICameraService;
import com.djb.highway.road.service.IInfoBoardService;
import com.djb.highway.road.service.IServiceAreaService;

@Controller("/ServiceArea")
public class ServiceAreaAction extends BaseAction {

    @Autowired
    @Qualifier("iServiceAreaService")
    private IServiceAreaService iServiceAreaService;

    @Autowired
    @Qualifier("iCameraService")
    private ICameraService iCameraService;

    @Autowired
    @Qualifier("iInfoBoardService")
    private IInfoBoardService iInfoBoardService;

    public ServiceAreaAction() {
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
    public ActionForward doGetServiceAreaByLinkCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        ServiceAreaDTO paramDTO = (ServiceAreaDTO) form;
        // paramDTO.setPrevious_plaza_code("0453");
        // paramDTO.setNext_plaza_code("0454");
        ServiceAreaEntity paramEntity = new ServiceAreaEntity();
        paramEntity.setLink_code(paramDTO.getPrevious_plaza_code() + paramDTO.getNext_plaza_code());
        ServiceAreaDTO resultDTO = new ServiceAreaDTO();
        ServiceAreaEntity resultEntity = null;
        ServiceAreaInfoDTO serviceAreaInfoDTO = new ServiceAreaInfoDTO();
        ServiceAreaClientDTO serviceAreaClientDTO = new ServiceAreaClientDTO();
        try {
            resultEntity = iServiceAreaService.getObjectByLinkCode(paramEntity);
        } catch (Exception e) {

            resultDTO.setErrFlg(true);
            serviceAreaInfoDTO.setReturnCode("-1000");
        }

        if (resultEntity != null) {

            serviceAreaClientDTO = doBackDTOtoClientDTO(resultEntity, serviceAreaClientDTO);
        }

        if (!resultDTO.isErrFlg()) {
            serviceAreaInfoDTO.setReturnCode("0");
        }

        List<ServiceAreaClientDTO> eventList = new ArrayList<ServiceAreaClientDTO>();
        eventList.add(serviceAreaClientDTO);
        serviceAreaInfoDTO.setEventList(eventList);
        // android
        toJson(response, serviceAreaInfoDTO);
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
    public ActionForward doGetServiceAreaListByLinkCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // // 参数DTO
        ServiceAreaDTO paramDTO = (ServiceAreaDTO) form;
        // test
        // paramDTO.setKeyName("61");

        // // 参数Entity
        ServiceAreaEntity paramEntity = new ServiceAreaEntity();

        String linkCode = null;
        // int section_id = paramDTO.getSection_id();
        String keyName = paramDTO.getKeyName();
        if (keyName != null) {
            // 获取对应路段的服务区
            int section_id = CommonUtil.toInt(keyName, 60);
            String[] paramServiceAgs = getServiceId(section_id);
            linkCode = paramServiceAgs[0];
            paramEntity.setLink_code(linkCode);
        } else {
            linkCode = "";
        }

        // 结果DTO
        ServiceAreaDTO resultDTO = new ServiceAreaDTO();
        // 结果Entity
        ServiceAreaEntity resultEntity = null;
        // 页面DTO

        ServiceAreaInfoDTO serviceAreaInfoDTO = new ServiceAreaInfoDTO();
        List<ServiceAreaClientDTO> ServiceAreaClientList = new ArrayList<ServiceAreaClientDTO>();

        // DB操作
        try {
            // 获取第一个服务区的信息
            resultEntity = iServiceAreaService.getObject(paramEntity);
            if (resultEntity != null) {
                // 给serviceAreaInfoDTO赋值
                ServiceAreaClientDTO serviceAreaClientDTO = new ServiceAreaClientDTO();
                serviceAreaClientDTO = doBackDTOtoClientDTO(resultEntity, serviceAreaClientDTO);
                ServiceAreaClientList.add(serviceAreaClientDTO);
            }
            // 获取第二个服务区的信息

            paramEntity.setLink_code(linkCode.substring(4, 8) + linkCode.substring(0, 4));

            resultEntity = iServiceAreaService.getObject(paramEntity);
            if (resultEntity != null) {
                // 给serviceAreaInfoDTO赋值
                ServiceAreaClientDTO serviceAreaClientDTO = new ServiceAreaClientDTO();
                serviceAreaClientDTO = doBackDTOtoClientDTO(resultEntity, serviceAreaClientDTO);
                ServiceAreaClientList.add(serviceAreaClientDTO);
            }

            // 结果Entity -> 结果DTO
            serviceAreaInfoDTO.setEventList(ServiceAreaClientList);

        } catch (Exception e) {

            resultDTO.setErrFlg(true);
            serviceAreaInfoDTO.setReturnCode("-1001");
            // formMessages(resultDTO, Constants.MSG_KEY_SAALL_ERROR, null);
        }

        // 页面DTO
        if (!resultDTO.isErrFlg()) {
            serviceAreaInfoDTO.setReturnCode("0");
        }

        // android
        toJson(response, serviceAreaInfoDTO);
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
    public ActionForward doGetServiceAreaList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        ServiceAreaDTO paramDTO = (ServiceAreaDTO) form;
        // 参数Entity
        ServiceAreaEntity paramEntity = new ServiceAreaEntity();
        // 参数DTO ->paramEntity

        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果DTO
        ServiceAreaDTO resultDTO = new ServiceAreaDTO();
        // 结果List
        List<ServiceAreaEntity> serviceAreaEntityList = new ArrayList<ServiceAreaEntity>();
        // 客户端DTO
        ServiceAreaInfoDTO serviceAreaInfoDTO = new ServiceAreaInfoDTO();
        ServiceAreaClientDTO serviceAreaClientDTO = null;
        List<ServiceAreaClientDTO> eventList = new ArrayList<ServiceAreaClientDTO>();
        String[] serviceIdArray = null;
        // DB操作
        try {
            // test
            // String[] serviceIdArray = new String[] { "1", "2", "7", "8" };
            if (!"".equals(paramDTO.getServiceIdArgs()) && paramDTO.getServiceIdArgs() != null) {
                serviceIdArray = paramDTO.getServiceIdArgs().split(",");
                paramEntity.setServiceIdArgs(serviceIdArray);
                paramEntity.setServiceIdArgs(serviceIdArray);
            }

            serviceAreaEntityList = iServiceAreaService.getServiceAreaList(paramEntity);
            if (serviceAreaEntityList != null) {
                for (ServiceAreaEntity sa : serviceAreaEntityList) {
                    serviceAreaClientDTO = new ServiceAreaClientDTO();
                    doBackDTOtoClientDTO(sa, serviceAreaClientDTO);
                    eventList.add(serviceAreaClientDTO);
                }

                serviceAreaInfoDTO.setEventList(eventList);

            }

        } catch (Exception e) {

            resultDTO.setErrFlg(true);
            serviceAreaInfoDTO.setReturnCode("-1000");
            // formMessages(resultDTO, Constants.MSG_KEY_SAALL_ERROR, null);
        }

        if (!resultDTO.isErrFlg()) {
            serviceAreaInfoDTO.setReturnCode("0");
        }

        // android
        toJson(response, serviceAreaInfoDTO);
        // ;
        return null;
    }

    public ServiceAreaClientDTO doBackDTOtoClientDTO(ServiceAreaEntity resultEntity, ServiceAreaClientDTO serviceAreaClientDTO) {
        if (resultEntity.getSa_id() != null) {
            serviceAreaClientDTO.setAreaId(resultEntity.getSa_id());
        }
        // 服务区名称
        if (resultEntity.getSa_name() != null) {
            serviceAreaClientDTO.setAreaName(resultEntity.getSa_name());
        }
        // 城市名称
        if (resultEntity.getSa_city() != null) {
            serviceAreaClientDTO.setCityName(resultEntity.getSa_city());
        }

        // 桩号
        if (resultEntity.getStake_id() != null) {
            serviceAreaClientDTO.setPileNum(resultEntity.getStake_id());
        }

        // 电话号
        if (resultEntity.getSa_tel() != null) {
            serviceAreaClientDTO.setTelNum(resultEntity.getSa_tel());
        }

        // 下一服务区
        if (resultEntity.getNext_service_area_name() != null) {
            serviceAreaClientDTO.setNextArea(resultEntity.getNext_service_area_name());
        }

        // 下一收费站
        if (resultEntity.getNext_plaza_name() != null) {
            serviceAreaClientDTO.setNextGasStation(resultEntity.getNext_plaza_name());
        }

        // 超市标志位
        if (resultEntity.getSupermarket_flg() != null) {
            if (resultEntity.getSupermarket_flg().equals("1")) {
                serviceAreaClientDTO.setSuperMarktFlag(true);

            }

        }

        // 餐厅标志位
        if (resultEntity.getRestaurant_flg() != null) {
            if (resultEntity.getRestaurant_flg().equals("1")) {
                serviceAreaClientDTO.setRestaurantFlag(true);

            }

        }
        // 旅馆标志位
        if (resultEntity.getHotel_flg() != null) {
            if (resultEntity.getHotel_flg().equals("1")) {
                serviceAreaClientDTO.setHotelFlag(true);

            }

        }

        // 修车标志位
        if (resultEntity.getCar_repair_flg() != null) {
            if (resultEntity.getCar_repair_flg().equals("1")) {
                serviceAreaClientDTO.setCarrepairFlag(true);
            }

        }
        // 加油站标志位
        if (resultEntity.getGas_station_flg() != null) {
            if (resultEntity.getGas_station_flg().equals("1")) {
                serviceAreaClientDTO.setGasStationFlag(true);
            }

        }

        // 油的种类
        if (resultEntity.getGas_type() != null) {
            serviceAreaClientDTO.setGasClass(resultEntity.getGas_type());
        }
        // 停车场车辆总数量
        if ("1".equals(resultEntity.getPark_status())) {
            if (resultEntity.getPark_remain() != null && resultEntity.getPark_total() != null) {
                serviceAreaClientDTO.setParkNumber(resultEntity.getPark_remain() + "/" + resultEntity.getPark_total());
            }
        } else {
            if ("0".equals(resultEntity.getPark_status())) {
                serviceAreaClientDTO.setParkNumber("维修中");
            }
        }

        // 初始化服务区的摄像头ids集合信息
        List<Integer> c_ids = initCameraOfServiceArea(resultEntity);
        if (c_ids != null && c_ids.size() > 0) {
            serviceAreaClientDTO.setC_ids(c_ids);
        }
        // 服务区ID
        if (resultEntity.getSa_id() != null) {
            serviceAreaClientDTO.setLocation_id(resultEntity.getSa_id());
        }
        // 情报板是否显示的标示
        serviceAreaClientDTO.setIb_flag(initInfoBoardOfServiceArea(resultEntity));

        return serviceAreaClientDTO;

    }

    private String[] getServiceId(int sectionId) {

        String _sectionId = String.valueOf(sectionId);

        if (_sectionId == null) {
            return null;
        }

        String[] resultServiceId = null;
        String[] sectionArgs = StringUtils.split(ResourceLocator.getI18NMessage(this, Constants.KEY_SERVICE_STAKE_ID_LIST), "|");
        for (String section : sectionArgs) {
            String[] _sectionArgs = StringUtils.split(section, ",");
            if (_sectionId.equals(_sectionArgs[0])) {
                if ((_sectionArgs[2] != null) && (!"-1".equals(_sectionArgs[1])))
                    resultServiceId = new String[] { _sectionArgs[2] };
                break;
            }

        }
        return resultServiceId;

    }

    // 获取服务区的摄像头IDs
    public ArrayList<Integer> initCameraOfServiceArea(ServiceAreaEntity serviceAreaEntity) {

        // 参数Entity
        CameraEntity paramEntity = new CameraEntity();
        // 获取的摄像头信息
        List<CameraEntity> cameraEntityList = null;
        // 返回的摄像头IDs结果集合
        ArrayList<Integer> ids = null;
        paramEntity.setLocation_id(serviceAreaEntity.getSa_id());
        // F:标示服务区的摄像头
        paramEntity.setLocation_type("F");
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

    // 判断服务区的情报板是否显示
    public boolean initInfoBoardOfServiceArea(ServiceAreaEntity serviceAreaEntity) {

        // 参数Entity
        InfoBoardEntity paramEntity = new InfoBoardEntity();
        // 获取的情报板信息
        List<InfoBoardEntity> infoBoardEntityList = null;
        // 返回的情报板IDs结果集合
        paramEntity.setLocation_id(serviceAreaEntity.getSa_id());
        paramEntity.setLocation_type("F");
        // 获取对应的情报板信息
        infoBoardEntityList = iInfoBoardService.getInfoBoardList(paramEntity);
        if (infoBoardEntityList != null && infoBoardEntityList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    // 给Point点详细画面赋值操作
    public ServiceAreaClientDTO doBackDTOtoClientPointDTO(ServiceAreaEntity resultEntity, ServiceAreaClientDTO serviceAreaClientDTO) {
        // 高速Code
        if (resultEntity.getH_code() != null) {
            serviceAreaClientDTO.setH_code(resultEntity.getH_code());
        }

        // 高速名称
        if (resultEntity.getH_name() != null) {
            serviceAreaClientDTO.setH_name(resultEntity.getH_name());
        }

        // 服务区ID
        if (resultEntity.getSa_id() != null) {
            serviceAreaClientDTO.setAreaId(resultEntity.getSa_id());
        }
        // 服务区名称
        if (resultEntity.getSa_name() != null) {
            serviceAreaClientDTO.setAreaName(resultEntity.getSa_name());
        }
        // 城市名称
        if (resultEntity.getSa_city() != null) {
            serviceAreaClientDTO.setCityName(resultEntity.getSa_city());
        }

        // 桩号
        if (resultEntity.getStake_id() != null) {
            serviceAreaClientDTO.setPileNum(resultEntity.getStake_id());
        }

        // 电话号
        if (resultEntity.getSa_tel() != null) {
            serviceAreaClientDTO.setTelNum(resultEntity.getSa_tel());
        }

        // 下一服务区
        if (resultEntity.getNext_service_area_name() != null) {
            serviceAreaClientDTO.setNextArea(resultEntity.getNext_service_area_name());
        }

        // 下一收费站
        if (resultEntity.getNext_plaza_name() != null) {
            serviceAreaClientDTO.setNextGasStation(resultEntity.getNext_plaza_name());
        }

        // 给服务内容赋值：
        StringBuffer serviceContent = new StringBuffer();

        // 超市标志位
        if (resultEntity.getSupermarket_flg() != null) {
            if (resultEntity.getSupermarket_flg().equals("1")) {
                serviceContent.append("超市");
                serviceContent.append(",");

            }

        }

        // 餐厅标志位
        if (resultEntity.getRestaurant_flg() != null) {
            if (resultEntity.getRestaurant_flg().equals("1")) {
                serviceContent.append("餐厅");
                serviceContent.append(",");
            }

        }
        // 旅馆标志位
        if (resultEntity.getHotel_flg() != null) {
            if (resultEntity.getHotel_flg().equals("1")) {
                serviceContent.append("宾馆");
                serviceContent.append(",");
            }

        }

        // 修车标志位
        if (resultEntity.getCar_repair_flg() != null) {
            if (resultEntity.getCar_repair_flg().equals("1")) {
                serviceAreaClientDTO.setCarrepairFlag(true);
                serviceContent.append("修车");
                serviceContent.append(",");
            }

        }
        // 加油站标志位
        if (resultEntity.getGas_station_flg() != null) {
            if (resultEntity.getGas_station_flg().equals("1")) {
                serviceAreaClientDTO.setGasStationFlag(true);
                serviceContent.append("加油站");
                serviceContent.append(":");
                // 油的种类
                if (resultEntity.getGas_type() != null) {
                    serviceAreaClientDTO.setGasClass(resultEntity.getGas_type());
                    serviceContent.append(resultEntity.getGas_type());
                }
            }

        }

        // 停车场车辆总数量
        if ("1".equals(resultEntity.getPark_status())) {
            if (resultEntity.getPark_remain() != null && resultEntity.getPark_total() != null) {
                serviceAreaClientDTO.setParkNumber(resultEntity.getPark_remain() + "/" + resultEntity.getPark_total());
                serviceContent.append(" 车位剩余:" + resultEntity.getPark_remain());
            }
        }

        String serviceCt = serviceContent.toString();
        if (!"".equals(serviceCt)) {
            serviceAreaClientDTO.setServiceContent(serviceCt);
        }
     // 初始化服务区的摄像头ids集合信息
        List<Integer> c_ids = initCameraOfServiceArea(resultEntity);
        if (c_ids != null && c_ids.size() > 0) {
            serviceAreaClientDTO.setC_ids(c_ids);
        }
        // 服务区ID
        if (resultEntity.getSa_id() != null) {
            serviceAreaClientDTO.setLocation_id(resultEntity.getSa_id());
        }
        // 情报板是否显示的标示
        serviceAreaClientDTO.setIb_flag(initInfoBoardOfServiceArea(resultEntity));
       
        return serviceAreaClientDTO;

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
    public ActionForward doGetServiceAreaPointList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        ServiceAreaDTO paramDTO = (ServiceAreaDTO) form;
        // test
        // paramDTO.setLatitude_b("40.213598");
        // paramDTO.setLongitude_b("122.121204");
        //paramDTO.setH_code("G1501");
        // 参数Entity
        ServiceAreaEntity paramEntity = new ServiceAreaEntity();
        // 参数DTO ->paramEntity
        CommonUtil.reflectClass(paramDTO, paramEntity);

        // 结果DTO
        ServiceAreaPointListDTO resultDTO = new ServiceAreaPointListDTO();
        // 结果List
        List<ServiceAreaEntity> serviceAreaEntityList = null;
        ServiceAreaPointDTO serviceAreaPointDTO = null;
        List<ServiceAreaPointDTO> serviceAreaPointDTOList = null;
        // DB操作
        try {

            serviceAreaEntityList = iServiceAreaService.getServiceAreaPointList(paramEntity);
            if (serviceAreaEntityList != null && serviceAreaEntityList.size() > 0) {
                serviceAreaPointDTOList = new ArrayList<ServiceAreaPointDTO>();
                for (ServiceAreaEntity sa : serviceAreaEntityList) {
                    serviceAreaPointDTO = new ServiceAreaPointDTO();

                    CommonUtil.reflectClass(sa, serviceAreaPointDTO);
                    serviceAreaPointDTO.setAreaId(sa.getSa_id());
                    serviceAreaPointDTOList.add(serviceAreaPointDTO);
                }

                resultDTO.setPointList(serviceAreaPointDTOList);

            }

        } catch (Exception e) {
            resultDTO.setReturnCode("-3040");
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
    public ActionForward doGetServiceAreaById(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 参数DTO
        ServiceAreaDTO paramDTO = (ServiceAreaDTO) form;
        // test
        // paramDTO.setSa_id(1);

        // 参数Entity
        ServiceAreaEntity paramEntity = new ServiceAreaEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果DTO
        ServiceAreaDTO resultDTO = new ServiceAreaDTO();
        // 结果Entity
        ServiceAreaEntity resultEntity = null;
        // 客户端结果DTO
        ServiceAreaInfoDTO serviceAreaInfoDTO = new ServiceAreaInfoDTO();
        ServiceAreaClientDTO serviceAreaClientDTO = new ServiceAreaClientDTO();
        try {
            resultEntity = iServiceAreaService.getObject(paramEntity);
        } catch (Exception e) {

            resultDTO.setErrFlg(true);
            serviceAreaInfoDTO.setReturnCode("-3100");
        }

        if (resultEntity != null) {

            serviceAreaClientDTO = doBackDTOtoClientPointDTO(resultEntity, serviceAreaClientDTO);
        }

        if (!resultDTO.isErrFlg()) {
            serviceAreaInfoDTO.setReturnCode("0");
        }

        List<ServiceAreaClientDTO> eventList = new ArrayList<ServiceAreaClientDTO>();
        eventList.add(serviceAreaClientDTO);
        serviceAreaInfoDTO.setEventList(eventList);
        // android
        toJson(response, serviceAreaInfoDTO);
        // ;
        return null;
    }

}
