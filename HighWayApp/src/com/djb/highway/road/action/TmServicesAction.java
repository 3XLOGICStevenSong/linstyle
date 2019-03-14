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

import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.road.dto.TmServicesDTO;
import com.djb.highway.road.dtoclient.DetailPriceBaseDTO;
import com.djb.highway.road.dtoclient.DetailPriceDTO;
import com.djb.highway.road.entity.TmServicesDetailsEntity;
import com.djb.highway.road.entity.TmServicesEntity;
import com.djb.highway.road.service.ITmServicesService;

@Controller("/TmServices")
public class TmServicesAction extends BaseAction {

    @Autowired
    @Qualifier("iTmServicesService")
    private ITmServicesService iTmServicesService;

    public TmServicesAction() {
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
    public ActionForward doGetTmServices(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        TmServicesDTO paramDTO = (TmServicesDTO) form;
        // 参数Entity
        TmServicesEntity paramEntity = new TmServicesEntity();

        paramEntity.setSa_id(paramDTO.getSa_id());
        paramEntity.setType(paramDTO.getType());
        // paramEntity.setSa_id(1);
        // paramEntity.setType("3");
        // 页面DTO
        DetailPriceBaseDTO resultDTO = new DetailPriceBaseDTO();
        // 结果Entity
        TmServicesEntity resultEntity = null;

        try {
            resultEntity = iTmServicesService.getObject(paramEntity);

            // 页面DTO赋值
            resultDTO = doBackDTOtoClientDTO(resultEntity, resultDTO);
        } catch (Exception e) {
            paramDTO.setErrFlg(true);
            resultDTO.setReturnCode("-1002");
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
    public ActionForward doGetTmServicesList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        TmServicesDTO paramDTO = (TmServicesDTO) form;
        TmServicesEntity paramEntity = new TmServicesEntity();
        paramEntity.setSa_id(1);
        paramEntity.setType("1");

        List<TmServicesEntity> resultList = iTmServicesService.getTmServicesList(paramEntity);

        // System.out.println("---" + resultList);

        // android
        // toJson(response, serviceAreaInfoDTO);
        // ;
        return null;
    }

    public DetailPriceBaseDTO doBackDTOtoClientDTO(TmServicesEntity tmServicesEntity, DetailPriceBaseDTO detailPriceBaseDTO) {
        List<DetailPriceDTO> detailPriceDTOList = new ArrayList<DetailPriceDTO>();
        if (tmServicesEntity != null && tmServicesEntity.getTmServicesDetailsEntitys() != null && tmServicesEntity.getTmServicesDetailsEntitys().size() > 0) {
            for (TmServicesDetailsEntity tsd : (tmServicesEntity.getTmServicesDetailsEntitys())) {
                DetailPriceDTO detailPrice = new DetailPriceDTO();
                // 价格
                detailPrice.setGoods_price(tsd.getDetails_num());
                // 名称
                detailPrice.setGoods_name(tsd.getDetails_name());
                // 类别
                detailPrice.setGoods_type(tsd.getDetails_type());
                // 图片
                detailPrice.setPic_url(tsd.getDetails_pic());
                detailPriceDTOList.add(detailPrice);
            }

            detailPriceBaseDTO.setList(detailPriceDTOList);
        }

        return detailPriceBaseDTO;
    }

}
