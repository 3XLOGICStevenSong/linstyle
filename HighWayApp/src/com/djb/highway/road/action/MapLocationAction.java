package com.djb.highway.road.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.djb.highway.common.util.CommonUtil;
import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.framework.dto.BaseDTO;
import com.djb.highway.road.dto.MapLocationDTO;
import com.djb.highway.road.dtoclient.BaseClientDTO;
import com.djb.highway.road.entity.MapLocationEntity;
import com.djb.highway.road.service.IMapLocationService;

@Controller("/MapLocation")
public class MapLocationAction extends BaseAction {

    @Autowired
    @Qualifier("iMapLocationService")
    private IMapLocationService iMapLocationService;

    public MapLocationAction() {
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
    public ActionForward doInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        MapLocationDTO mapLocationDTO = (MapLocationDTO) form;

        // test
        // mapLocationDTO.setU_tel("13591277216");
        // mapLocationDTO.setLatitude("45.455455");
        // mapLocationDTO.setLongitude("145.455455");

        // 用于返回操作状态
        BaseDTO baseDTO = new BaseDTO();
        BaseClientDTO baseClientDTO = new BaseClientDTO();

        MapLocationEntity mapLocationEntity = new MapLocationEntity();
        CommonUtil.reflectClass(mapLocationDTO, mapLocationEntity);
        try {

            iMapLocationService.addMapLocation(mapLocationEntity);

        } catch (Exception e) {
            baseClientDTO.setReturnCode("-2900");
            baseDTO.setErrFlg(true);
        }
        if (!baseDTO.isErrFlg()) {
            baseClientDTO.setReturnCode("0");
        }
        // android
        toJson(response, baseClientDTO);
        // ;
        return null;

    }

}
