package com.djb.ylt.medicine.action;

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

import com.djb.ylt.common.util.CommonUtil;
import com.djb.ylt.framework.action.BaseAction;
import com.djb.ylt.medicine.dto.MedicineCollectionDTO;
import com.djb.ylt.medicine.dtoclient.MedicineCollectionClientDTO;
import com.djb.ylt.medicine.dtoclient.MedicineCollectionDetailClientDTO;
import com.djb.ylt.medicine.dtoclient.MedicineCollectionDetailDTO;
import com.djb.ylt.medicine.entity.MedicineCollectionEntity;
import com.djb.ylt.medicine.entity.MedicineInfoEntity;
import com.djb.ylt.medicine.service.IMedicineCollectionService;
import com.djb.ylt.medicine.service.IMedicineInfoService;



@Controller("/MedicineCollection")
public class MedicineCollectionAction extends BaseAction {

    @Autowired
    @Qualifier("iMedicineCollectionService")
    private IMedicineCollectionService iMedicineCollectionService;

    @Autowired
    @Qualifier("iMedicineInfoService")
    private IMedicineInfoService iMedicineInfoService;

    public MedicineCollectionAction() {
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

    public ActionForward doGetMedicineCollectionList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 参数DTO
        MedicineCollectionDTO paramDTO = (MedicineCollectionDTO) form;
        // 参数Entity
        MedicineCollectionEntity paramEntity = new MedicineCollectionEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 客户端DTO
        MedicineCollectionClientDTO medicineClientDTO = new MedicineCollectionClientDTO();
        List<MedicineCollectionEntity> resultList = null;
        List<MedicineCollectionDetailDTO> medicineDetailDTOList = null;

        try {
            resultList = iMedicineCollectionService.getMedicineCollectionList(paramEntity);

            if (resultList != null && resultList.size() > 0) {

                medicineDetailDTOList = new ArrayList<MedicineCollectionDetailDTO>();
                for (MedicineCollectionEntity medicineEntity : resultList) {
                    MedicineCollectionDetailDTO medicineDetailDTO = new MedicineCollectionDetailDTO();
                    CommonUtil.reflectClass(medicineEntity, medicineDetailDTO);

                    // 获取药物的基本信息
                    MedicineInfoEntity medicineinfo = medicineEntity.getMedicineInfoEntity();
                    if (medicineinfo != null) {
                        // 给客户端DTO赋值
                        CommonUtil.reflectClass(medicineinfo, medicineDetailDTO);
                        // 获取平均评分
                        MedicineInfoEntity _medicineInfoEntity = new MedicineInfoEntity();
                        _medicineInfoEntity.setMedicine_id(medicineinfo.getMedicine_id());
                        _medicineInfoEntity = iMedicineInfoService.getAverageGrade(_medicineInfoEntity);
                        if (_medicineInfoEntity != null && _medicineInfoEntity.getMedicine_grade() != null) {
                            medicineDetailDTO.setMedicine_grade(_medicineInfoEntity.getMedicine_grade());
                        }
                        // 药品价格
                        medicineDetailDTO.setMedicine_price(medicineinfo.getMedicine_price());

                    }

                    medicineDetailDTOList.add(medicineDetailDTO);
                }
                medicineClientDTO.setList(medicineDetailDTOList);
            }

        } catch (Exception e) {
            medicineClientDTO.setReturnCode("-1010");
            paramDTO.setErrFlg(true);
        }
        if (!paramDTO.isErrFlg()) {
            medicineClientDTO.setReturnCode("0");
        }

        toJson(response, medicineClientDTO);
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

    public ActionForward doGetMedicineCollection(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 参数DTO
        MedicineCollectionDTO paramDTO = (MedicineCollectionDTO) form;
        // 参数Entity
        MedicineCollectionEntity paramEntity = new MedicineCollectionEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果DTO
        MedicineCollectionEntity medicineEntity = null;
        // 客户端DTO
        MedicineCollectionDetailClientDTO medicineDetailDTO = new MedicineCollectionDetailClientDTO();
        try {
            medicineEntity = iMedicineCollectionService.getObject(paramEntity);
            if (medicineEntity != null) {
                CommonUtil.reflectClass(medicineEntity, medicineDetailDTO);
            }

        } catch (Exception e) {
            medicineDetailDTO.setReturnCode("-1011");
            paramDTO.setErrFlg(true);
        }
        if (!paramDTO.isErrFlg()) {
            medicineDetailDTO.setReturnCode("0");
        }

        toJson(response, medicineDetailDTO);
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

    public ActionForward doInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        MedicineCollectionDTO paramDTO = (MedicineCollectionDTO) form;
        // 参数Entity
        MedicineCollectionEntity paramEntity = new MedicineCollectionEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果DTO
        MedicineCollectionDetailClientDTO medicineCollectionDetailClientDTO = new MedicineCollectionDetailClientDTO();
        try {
            // test
            // paramEntity.setUser_id(1);
            // paramEntity.setMedicine_id(1);
            // paramEntity.setMedicine_name("水果茶");
            iMedicineCollectionService.addMedicineCollection(paramEntity);

        } catch (Exception e) {
            medicineCollectionDetailClientDTO.setReturnCode("-1012");
            paramDTO.setErrFlg(true);
        }
        if (!paramDTO.isErrFlg()) {
            medicineCollectionDetailClientDTO.setReturnCode("0");
        }
        // android
        toJson(response, medicineCollectionDetailClientDTO);
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
    public ActionForward doDeleteMedicineCollection(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 参数DTO
        MedicineCollectionDTO paramDTO = (MedicineCollectionDTO) form;
        // paramDTO.setMedicine_id(1);
        // paramDTO.setUser_id(1);
        // 参数Entity
        MedicineCollectionEntity paramEntity = new MedicineCollectionEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果DTO
        MedicineCollectionDetailClientDTO medicineCollectionDetailClientDTO = new MedicineCollectionDetailClientDTO();
        try {
            iMedicineCollectionService.deleteMedicineCollection(paramEntity);
        } catch (Exception e) {
            medicineCollectionDetailClientDTO.setReturnCode("-1013");
            paramDTO.setErrFlg(true);
        }
        if (!paramDTO.isErrFlg()) {
            medicineCollectionDetailClientDTO.setReturnCode("0");
        }
        // android
        toJson(response, medicineCollectionDetailClientDTO);
        // ;
        return null;

    }

}
