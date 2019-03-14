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
import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.road.dto.SectionDTO;
import com.djb.highway.road.dtoclient.TmSectionClearBaseDTO;
import com.djb.highway.road.dtoclient.TmSectionClearDTO;
import com.djb.highway.road.entity.SectionEntity;
import com.djb.highway.road.service.ISectionService;
import com.djb.highway.user.entity.OftenUsedRoadEntity;
import com.djb.highway.user.service.IOftenUsedRoadService;

@Controller("/Section")
public class SectionAction extends BaseAction {

    @Autowired
    @Qualifier("iSectionService")
    private ISectionService iSectionService;

    @Autowired
    @Qualifier("iOftenUsedRoadService")
    private IOftenUsedRoadService iOftenUsedRoadService;

    public SectionAction() {
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
    public ActionForward doGetSectionList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        SectionDTO paramDTO = (SectionDTO) form;
        // test
        // paramDTO.setH_id(1);
        // 结果DTO
        SectionDTO resultDTO = new SectionDTO();
        TmSectionClearBaseDTO resultClearBaseDTO = new TmSectionClearBaseDTO();
        // 参数Entity
        SectionEntity paramEntity = new SectionEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果Entity
        List<SectionEntity> resultListEntity = null;
        // DB操作
        try {
            List<SectionDTO> resultListDTO = null;
            resultListEntity = iSectionService.getSectionList(paramEntity);
            resultListDTO = new ArrayList<SectionDTO>();
            for (SectionEntity section : resultListEntity) {
                SectionDTO _resultDTO = new SectionDTO();
                _resultDTO.setSection_id(section.getSection_id());
                _resultDTO.setLevel(section.getLevel());
                _resultDTO.setSpeed(section.getSpeed());
                resultListDTO.add(_resultDTO);
            }
            resultDTO.setList(resultListDTO);

            // 给页面DTO赋值
            // 结果DTO

            List<TmSectionClearDTO> resultListClearDTO = new ArrayList<TmSectionClearDTO>();
            for (SectionDTO section : resultDTO.getList()) {
                TmSectionClearDTO _resultDTO = new TmSectionClearDTO();

                if (section.getSection_id() != null) {
                    _resultDTO.setSectionId(CommonUtil.toLong(section.getSection_id().toString(), 0));
                }
                if (section.getSpeed() != null) {
                    _resultDTO.setLevel(getLevelBySpeed(section.getSpeed()));
                } else {
                    _resultDTO.setLevel(1L);
                }

                if (_resultDTO.getLevel() != 1L) {
                    resultListClearDTO.add(_resultDTO);
                }

            }
            resultClearBaseDTO.setList(resultListClearDTO);
            resultClearBaseDTO.setMarkFlag(doMarkFlagMethod(paramDTO));

        } catch (Exception e) {
            resultDTO.setErrFlg(true);
            resultClearBaseDTO.setReturnCode("-1506");
            // resultClearBaseDTO.setErrFlg(true);
            // formMessages(resultClearBaseDTO,
            // Constants.MSG_KEY_TMSECTION_ERROR,
            // null);
        }

        if (!resultDTO.isErrFlg()) {
            resultClearBaseDTO.setReturnCode("0");

        }

        // android
        toJson(response, resultClearBaseDTO);
        // ;
        return null;
    }

    // 处理markFlag收藏标志位

    public boolean doMarkFlagMethod(SectionDTO sectionDTO) {

        OftenUsedRoadEntity oftenUsedRoadEntity = new OftenUsedRoadEntity();

        oftenUsedRoadEntity.setU_id(sectionDTO.getU_id());
        // Road_type:字段添加
        oftenUsedRoadEntity.setRoad_type(sectionDTO.getRoad_type());
        oftenUsedRoadEntity.setPlaz_code_start(sectionDTO.getPlaz_code_start());
        oftenUsedRoadEntity.setPlaz_code_end(sectionDTO.getPlaz_code_end());

        List<OftenUsedRoadEntity> list = iOftenUsedRoadService.getOftenUsedRoadList(oftenUsedRoadEntity);

        // 如果检索到数据，该路段已经被收藏
        if (list != null && list.size() != 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 
     * 严重拥堵:<20 中度拥堵:20--40 轻度拥堵:40--60 基本畅通:60--80 畅通:>80
     */
    // 绿色： 1, speed>80;黄色：2 ,speed>= 40 && speed <=80;红色：3 ,speed<40
    private static long getLevelBySpeed(Float speed) {
        if (speed == null) {
            return 1;
        }

        long level = 1;
        float floatLevel = speed.floatValue();
        if (floatLevel < 40 && floatLevel > 0) {
            level = 3;
        } else if (floatLevel >= 40 && floatLevel <= 80) {
            level = 2;
        } else {
            level = 1;
        }
        return level;
    }

}
