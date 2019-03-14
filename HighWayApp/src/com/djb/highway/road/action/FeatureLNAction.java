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
import com.djb.highway.common.util.UploadUtil;
import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.road.dto.FeatureLNDTO;

import com.djb.highway.road.dtoclient.LNUniqueDTO;
import com.djb.highway.road.dtoclient.LNUniqueListDTO;
import com.djb.highway.road.dtoclient.UnitDetailDTO;
import com.djb.highway.road.dtoclient.UnitPicDTO;
import com.djb.highway.road.entity.FeatureLNEntity;

import com.djb.highway.road.service.IFeatureLNService;

@Controller("/FeatureLN")
public class FeatureLNAction extends BaseAction {

    @Autowired
    @Qualifier("iFeatureLNService")
    private IFeatureLNService iFeatureLNService;

    public FeatureLNAction() {
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
    public ActionForward doGetFeatureLNList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        FeatureLNDTO paramDTO = (FeatureLNDTO) form;
        // if("0".equals( String.valueOf((paramDTO.getAd_city_id())))){
        if (paramDTO.getAd_city_id() == null || 0 == paramDTO.getAd_city_id()) {
            paramDTO.setAd_city_id(null);
        }
        // paramDTO.setAd_city_id(2);
        // paramDTO.setAd_type("1");

        // paramDTO.setStartRow(1);
        // paramDTO.setEndRow(5);
        // 参数Entity
        FeatureLNEntity paramEntity = new FeatureLNEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);

        // 页面DTO
        LNUniqueListDTO resultDTO = new LNUniqueListDTO();
        // 结果Entity
        List<FeatureLNEntity> resultEntityList = null;
        List<LNUniqueDTO> lNUniqueDTOList = new ArrayList<LNUniqueDTO>();

        try {
            paramEntity.setStartRow(paramDTO.getStartRow());
            paramEntity.setEndRow(paramDTO.getEndRow());
            resultEntityList = iFeatureLNService.getFeatureLNList(paramEntity);
            for (FeatureLNEntity featureLNEntity : resultEntityList) {
                LNUniqueDTO lNUniqueDTO = new LNUniqueDTO();
                lNUniqueDTO.setAd_id(featureLNEntity.getAd_id());
                lNUniqueDTO.setAd_title(featureLNEntity.getAd_title());
                String hostIp = ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS_FEATURELN);
                if (featureLNEntity.getAd_pic() != null) {
                    lNUniqueDTO.setAd_pic(hostIp + "Image/" + UploadUtil.getSmallFileName(featureLNEntity.getAd_pic()));
                }
                lNUniqueDTO.setAd_sum_content(featureLNEntity.getAd_sum_content());
                lNUniqueDTOList.add(lNUniqueDTO);
            }
            resultDTO.setLNUniqueInfoList(lNUniqueDTOList);
        } catch (Exception e) {
            resultDTO.setReturnCode("-2800");
            paramDTO.setErrFlg(true);
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
    public ActionForward doGetFeatureLNDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        FeatureLNDTO paramDTO = (FeatureLNDTO) form;
        // paramDTO.setAd_id(1);

        FeatureLNEntity paramEntity = new FeatureLNEntity();
        FeatureLNEntity resultEntity = new FeatureLNEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 画面结果DTO
        UnitDetailDTO resultDTO = new UnitDetailDTO();
        List<UnitPicDTO> unitPicDTOList = new ArrayList<UnitPicDTO>();
        try {
            resultEntity = iFeatureLNService.getObject(paramEntity);
            resultDTO.setAd_content(resultEntity.getAd_content());
            resultDTO.setAd_road_line(resultEntity.getAd_road_line());
            if ((resultEntity.getAd_pic_list() != null) && (!"".equals(resultEntity.getAd_pic_list()))) {
                String[] arrayUrl = null;

                arrayUrl = StringUtils.split(resultEntity.getAd_pic_list(), "\\|");
                if(arrayUrl != null && arrayUrl.length >0){
                    for (String paramArray : arrayUrl) {
                        UnitPicDTO unitPicDTO = new UnitPicDTO();
                        String hostIp = ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS_FEATURELN);
                        unitPicDTO.setAd_pic_list(hostIp + "Image/" + paramArray);
                        unitPicDTOList.add(unitPicDTO);
                    }
                }
               
                resultDTO.setPicList(unitPicDTOList);
            }

        } catch (Exception e) {
            resultDTO.setReturnCode("-2801");
            paramDTO.setErrFlg(true);
        }
        if (!paramDTO.isErrFlg()) {
            resultDTO.setReturnCode("0");
        }
        toJson(response, resultDTO);
        // ;
        return null;
    }
}
