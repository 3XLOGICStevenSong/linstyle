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
import com.djb.highway.road.dto.OverpassDTO;
import com.djb.highway.road.dto.OverpassDetailDTO;
import com.djb.highway.road.dtoclient.OverpassBaseDTO;
import com.djb.highway.road.dtoclient.OverpassDetailClientDTO;
import com.djb.highway.road.entity.OverpassDetailEntity;
import com.djb.highway.road.entity.OverpassEntity;
import com.djb.highway.road.service.IOverpassDetailService;
import com.djb.highway.road.service.IOverpassService;

@Controller("/Overpass")
public class OverpassAction extends BaseAction {

    @Autowired
    @Qualifier("iOverpassDetailService")
    private IOverpassDetailService iOverpassDetailService;

    @Autowired
    @Qualifier("iOverpassService")
    private IOverpassService iOverpassService;

    public OverpassAction() {
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
    public ActionForward doGetOverpass(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        OverpassDTO paramDTO = (OverpassDTO) form;

        // 测试数据：
        // paramDTO.setO_id(1);

        // 参数Entity
        OverpassEntity paramEntity = new OverpassEntity();

        // 给参数Entity赋值
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果DTO
        OverpassDTO resultDTO = new OverpassDTO();
        // 结果Entity
        // 页面DTO
        OverpassBaseDTO overpassBaseDTO = new OverpassBaseDTO();

        OverpassEntity resultEntity = null;

        try {

            // DB操作
            resultEntity = iOverpassService.getObject(paramEntity);
            // 立交明细对象参数Entity
            OverpassDetailEntity overpassEntity = new OverpassDetailEntity();
            overpassEntity.setO_id(paramDTO.getO_id());
            overpassEntity.setEntry_plaz_code(paramDTO.getEntry_plaz_code());
            overpassEntity.setExit_plaz_code(paramDTO.getExit_plaz_code());
            List<OverpassDetailEntity> overpassDetailEntityList = new ArrayList<OverpassDetailEntity>();
            overpassDetailEntityList = iOverpassDetailService.getOverpassDetailList(overpassEntity);
            resultEntity.setOverpassDetailEntitys(overpassDetailEntityList);

            // 给ResultDTO赋值
            CommonUtil.reflectClass(resultEntity, resultDTO);
            // 遍历List
            List<OverpassDetailDTO> overpassDetailDTOList = new ArrayList<OverpassDetailDTO>();
            for (OverpassDetailEntity overp : overpassDetailEntityList) {
                OverpassDetailDTO overpassDetailDTO = new OverpassDetailDTO();
                CommonUtil.reflectClass(overp, overpassDetailDTO);
                overpassDetailDTOList.add(overpassDetailDTO);

            }
            // 给List 赋值
            resultDTO.setOverpassDetailDTOs(overpassDetailDTOList);
            String hostIp = ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS);

            // 赋值操作：
            List<OverpassDetailClientDTO> _overpassDetailDTOList = new ArrayList<OverpassDetailClientDTO>();
            OverpassDetailClientDTO _overpassDetailClientDTO = null;
            for (OverpassDetailDTO overpassDetail : resultDTO.getOverpassDetailDTOs()) {
                _overpassDetailClientDTO = new OverpassDetailClientDTO();

                if (overpassDetail.getOb_desc() != null && !("".equals(overpassDetail.getOb_desc()))) {
                    _overpassDetailClientDTO.setDirection_content(overpassDetail.getOb_desc());
                }

                // 路径获取
                // 绕行方案图片路径
                if (overpassDetail.getOb_pic() != null && !("".equals(overpassDetail.getOb_pic()))) {
                    _overpassDetailClientDTO.setPic_path(hostIp + "img_overpassdetail/" + overpassDetail.getOb_pic());

                    _overpassDetailDTOList.add(_overpassDetailClientDTO);

                }

            }

            if (_overpassDetailDTOList.size() == 0) {
                overpassBaseDTO.setList(null);
            } else {
                overpassBaseDTO.setList(_overpassDetailDTOList);
            }

            // 路径获取

            if (resultDTO.getO_pic_1() != null && !("".equals(resultDTO.getO_pic_1()))) {
                overpassBaseDTO.setPicUrl(hostIp + "img_overpass/" + resultDTO.getO_pic_1());

            }

            overpassBaseDTO.setLocation_des(resultDTO.getO_desc());

        } catch (Exception e) {

            resultDTO.setErrFlg(true);
            overpassBaseDTO.setReturnCode("-2600");
        }

        if (!resultDTO.isErrFlg()) {
            overpassBaseDTO.setReturnCode("0");

        }

        // android
        toJson(response, overpassBaseDTO);
        // ;

        return null;

    }
}
