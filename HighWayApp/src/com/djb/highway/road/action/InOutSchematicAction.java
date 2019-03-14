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
import com.djb.highway.road.dto.InOutSchematicDTO;
import com.djb.highway.road.dtoclient.StationExitBaseDTO;
import com.djb.highway.road.dtoclient.StationExitDTO;
import com.djb.highway.road.entity.InOutSchematicEntity;
import com.djb.highway.road.service.IInOutSchematicService;

@Controller("/InOutSchematic")
public class InOutSchematicAction extends BaseAction {

    @SuppressWarnings("unused")
    @Autowired
    @Qualifier("iInOutSchematicService")
    private IInOutSchematicService iInOutSchematicService;

    public InOutSchematicAction() {
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
    public ActionForward doGetSchematicPicList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        InOutSchematicDTO inOutSchematicDTO = (InOutSchematicDTO) form;

        // test
        // inOutSchematicDTO.setPlaz_code("0521");

        // 結果取得用DTO
        // List<InOutSchematicDTO> list = new ArrayList<InOutSchematicDTO>();
        List<InOutSchematicEntity> resultList = new ArrayList<InOutSchematicEntity>();

        // 画面DTO
        StationExitBaseDTO stationExitBaseDTO = new StationExitBaseDTO();
        InOutSchematicEntity paramEntity = new InOutSchematicEntity();
        List<StationExitDTO> stationExitDTOList = new ArrayList<StationExitDTO>();

        CommonUtil.reflectClass(inOutSchematicDTO, paramEntity);
        try {
            resultList = iInOutSchematicService.getInOutSchematicList(paramEntity);
            StationExitDTO stationExitDTO = null;
            for (InOutSchematicEntity inOutSchematicEntity : resultList) {
                stationExitDTO = new StationExitDTO();
                // 字段赋值
                stationExitDTO.setDirection_content(inOutSchematicEntity.getDirection_content());
                String hostIp = ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS);

                stationExitDTO.setPic_path(hostIp + "img_in_out/" + inOutSchematicEntity.getPic_path());

                stationExitDTOList.add(stationExitDTO);
            }

            // inOutSchematicDTO.setList(list);
            stationExitBaseDTO.setList(stationExitDTOList);

        } catch (Exception e) {
            stationExitBaseDTO.setReturnCode("-703");
            // resultDTO = new UserDTO();
            // resultDTO.setErrFlg(true);
            inOutSchematicDTO.setErrFlg(true);
            // formMessages(myShareDTO,
            // Constants.MSG_KEY_GET_USERDEPLOYPIC_ERROR,
            // null);
        }
        if (!inOutSchematicDTO.isErrFlg()) {
            stationExitBaseDTO.setReturnCode("0");
        }
        toJson(response, stationExitBaseDTO);
        return null;

    }
}