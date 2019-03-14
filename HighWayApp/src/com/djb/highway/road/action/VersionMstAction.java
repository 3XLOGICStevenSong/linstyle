package com.djb.highway.road.action;

import java.util.ArrayList;
import java.util.List;

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
import com.djb.highway.road.dto.VersionMstDTO;
import com.djb.highway.road.dtoclient.AllCameraBakClientDTO;
import com.djb.highway.road.dtoclient.CameraBakClientDTO;
import com.djb.highway.road.entity.CameraEntity;
import com.djb.highway.road.entity.VersionMstEntity;
import com.djb.highway.road.service.ICameraService;
import com.djb.highway.road.service.IVersionMstService;

@Controller("/VersionMst")
public class VersionMstAction extends BaseAction {

    @Autowired
    @Qualifier("iVersionMstService")
    private IVersionMstService iVersionMstService;

    @Autowired
    @Qualifier("iCameraService")
    private ICameraService iCameraService;

    public VersionMstAction() {
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
    public ActionForward getCameraVersionMst(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        VersionMstDTO versionMstDTO = (VersionMstDTO) form;
        int version = versionMstDTO.getVersion();
        int dbVersion = 0;

        // 客户端DTO
        AllCameraBakClientDTO allCameraBakClientDTO = new AllCameraBakClientDTO();

        // DB操作
        try {
            // 结果Entity
            VersionMstEntity resultEntity = iVersionMstService.getObject(null);
            if (resultEntity != null) {
                // 重新设置客户端version
                allCameraBakClientDTO.setVersion(resultEntity.getCamera_version());
                if (allCameraBakClientDTO.getVersion() != null) {
                    dbVersion = allCameraBakClientDTO.getVersion().intValue();
                    // 如果数据库中的version大于客户端传来的version，返回所有摄像头数据
                    if (dbVersion > version) {
                        allCameraBakClientDTO.setCameraList(this.getAllCameraList());
                    }
                }

            }

            allCameraBakClientDTO.setReturnCode("0");

        } catch (Exception e) {
            allCameraBakClientDTO.setReturnCode("-3000");
            this.logger.error("doGetVersionMst", e);
        }

        // android
        toJson(response, allCameraBakClientDTO);
        // ;

        return null;
    }

    private List<CameraBakClientDTO> getAllCameraList() throws Exception {

        // 结果Entity
        List<CameraBakClientDTO> cameraList = null;

        // DB操作
        try {

            List<CameraEntity> cameraEntityList = iCameraService.getCameraList();

            if (cameraEntityList != null && cameraEntityList.size() > 0) {

                cameraList = new ArrayList<CameraBakClientDTO>();

                for (CameraEntity resultEntity : cameraEntityList) {

                    CameraBakClientDTO cameraBakClientDTO = new CameraBakClientDTO();

                    if (resultEntity.getPic_path() != null && !"".equals(resultEntity.getPic_path())) {
                        cameraBakClientDTO.setImageUrl(CommonUtil.isNulltoBlank(ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS)
                                        + ResourceLocator.getI18NMessage(this, Constants.CAMERA_SERVLET_PATH) + resultEntity.getPic_path()));
                    }
                    cameraBakClientDTO.setCid(resultEntity.getC_id());
                    cameraBakClientDTO.setPileNumInfo(CommonUtil.isNulltoBlank(resultEntity.getStake_id()));
                    cameraBakClientDTO.setAddr(CommonUtil.isNulltoBlank(resultEntity.getC_addr()));

                    cameraList.add(cameraBakClientDTO);
                }

            }

        } catch (Exception e) {
            this.logger.error("getCameraList", e);
        }

        return cameraList;
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
    public ActionForward getCameraList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 结果Entity
        List<CameraEntity> cameraEntityList = null;

        // 客户端DTO
        AllCameraBakClientDTO allCameraBakClientDTO = new AllCameraBakClientDTO();

        // DB操作
        try {

            cameraEntityList = iCameraService.getCameraList();

            if (cameraEntityList != null && cameraEntityList.size() > 0) {

                List<CameraBakClientDTO> cameraList = new ArrayList<CameraBakClientDTO>();

                for (CameraEntity resultEntity : cameraEntityList) {

                    CameraBakClientDTO cameraBakClientDTO = new CameraBakClientDTO();

                    if (resultEntity.getPic_path() != null && !"".equals(resultEntity.getPic_path())) {
                        cameraBakClientDTO.setImageUrl(ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS)
                                        + ResourceLocator.getI18NMessage(this, Constants.CAMERA_SERVLET_PATH) + resultEntity.getPic_path());
                    }
                    cameraBakClientDTO.setCid(resultEntity.getC_id());
                    cameraBakClientDTO.setPileNumInfo(resultEntity.getStake_id());
                    cameraBakClientDTO.setAddr(resultEntity.getC_addr());

                    cameraList.add(cameraBakClientDTO);
                }

                allCameraBakClientDTO.setCameraList(cameraList);

            }

            allCameraBakClientDTO.setReturnCode("0");

        } catch (Exception e) {
            allCameraBakClientDTO.setReturnCode("-xxxx");
            this.logger.error("getCameraList", e);
        }

        // android
        toJson(response, allCameraBakClientDTO);
        // ;

        return null;

    }

}
