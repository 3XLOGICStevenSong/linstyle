package com.djb.ylt.medicine.action;

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

import com.djb.ylt.common.util.CommonUtil;
import com.djb.ylt.common.util.Constants;
import com.djb.ylt.common.util.ResourceLocator;
import com.djb.ylt.framework.action.BaseAction;
import com.djb.ylt.medicine.dto.MedicineInfoDTO;
import com.djb.ylt.medicine.dtoclient.MedicineInfoClientDTO;
import com.djb.ylt.medicine.dtoclient.MedicineInfoDetailClientDTO;
import com.djb.ylt.medicine.dtoclient.MedicineInfoDetailDTO;
import com.djb.ylt.medicine.dtoclient.MedicineTypeListClientDTO;
import com.djb.ylt.medicine.entity.MedicineCollectionEntity;
import com.djb.ylt.medicine.entity.MedicineInfoEntity;
import com.djb.ylt.medicine.entity.MedicineTypeEntity;
import com.djb.ylt.medicine.service.IMedicineCollectionService;
import com.djb.ylt.medicine.service.IMedicineInfoService;
import com.djb.ylt.medicine.service.IMedicineTypeService;

@Controller("/MedicineInfo")
public class MedicineInfoAction extends BaseAction {

    @Autowired
    @Qualifier("iMedicineInfoService")
    private IMedicineInfoService iMedicineInfoService;

    @Autowired
   @Qualifier("iMedicineCollectionService")
    private IMedicineCollectionService iMedicineCollectionService;

    @Autowired
    @Qualifier("iMedicineTypeService")
    private IMedicineTypeService iMedicineTypeService;

    public MedicineInfoAction() {
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

    public ActionForward doGetMedicineInfoList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 参数DTO
        MedicineInfoDTO paramDTO = (MedicineInfoDTO) form;
        // test:
        // paramDTO.setMedicine_type_id(1);
        // paramDTO.setMedicine_effect("感染");
        // 参数Entity
        MedicineInfoEntity paramEntity = new MedicineInfoEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 客户端DTO
        MedicineInfoClientDTO medicineClientDTO = new MedicineInfoClientDTO();
        List<MedicineInfoEntity> resultList = null;
        List<MedicineInfoDetailDTO> medicineDetailDTOList = null;

        try {
            resultList = iMedicineInfoService.getMedicineInfoList(paramEntity);

            if (resultList != null && resultList.size() > 0) {

                medicineDetailDTOList = new ArrayList<MedicineInfoDetailDTO>();
                for (MedicineInfoEntity medicineEntity : resultList) {
                    MedicineInfoDetailDTO medicineDetailDTO = new MedicineInfoDetailDTO();
                    CommonUtil.reflectClass(medicineEntity, medicineDetailDTO);
                    // 获取平均评分
                    MedicineInfoEntity _medicineInfoEntity = new MedicineInfoEntity();
                    _medicineInfoEntity.setMedicine_id(medicineEntity.getMedicine_id());
                    _medicineInfoEntity = iMedicineInfoService.getAverageGrade(_medicineInfoEntity);
                    if (_medicineInfoEntity != null && _medicineInfoEntity.getMedicine_grade() != null) {
                        medicineDetailDTO.setMedicine_grade(_medicineInfoEntity.getMedicine_grade());
                    }
                    // 药品价格
                    medicineDetailDTO.setMedicine_price(medicineEntity.getMedicine_price());

                    medicineDetailDTOList.add(medicineDetailDTO);
                }
                medicineClientDTO.setList(medicineDetailDTOList);
            }

        } catch (Exception e) {
            medicineClientDTO.setReturnCode("-1000");
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

    public ActionForward doGetMedicineInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 参数DTO
        MedicineInfoDTO paramDTO = (MedicineInfoDTO) form;
        // paramDTO.setMedicine_id(3);
        // paramDTO.setUser_id(1);
        // 参数Entity
        MedicineInfoEntity paramEntity = new MedicineInfoEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果DTO
        MedicineInfoEntity medicineEntity = null;
        // 客户端DTO
        MedicineInfoDetailClientDTO medicineDetailDTO = new MedicineInfoDetailClientDTO();
        try {
            medicineEntity = iMedicineInfoService.getObject(paramEntity);
            if (medicineEntity != null) {
                CommonUtil.reflectClass(medicineEntity, medicineDetailDTO);
            	String hostIp = ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS);
            	medicineDetailDTO.setMedicine_pic(hostIp+medicineEntity.getMedicine_pic());
                // 获取平均评分
                MedicineInfoEntity _medicineInfoEntity = new MedicineInfoEntity();
                _medicineInfoEntity.setMedicine_id(medicineEntity.getMedicine_id());
                _medicineInfoEntity = iMedicineInfoService.getAverageGrade(_medicineInfoEntity);
                if (_medicineInfoEntity != null && _medicineInfoEntity.getMedicine_grade() != null) {
                    medicineDetailDTO.setMedicine_grade(_medicineInfoEntity.getMedicine_grade());
                }
                // 药品价格
                medicineDetailDTO.setMedicine_price(medicineEntity.getMedicine_price());

                // Boolean：收藏标示符
                if (paramDTO.getUserId() != null) {
                    MedicineCollectionEntity medicineCollection = new MedicineCollectionEntity();
                    medicineCollection.setUserId(paramDTO.getUserId());
                    medicineCollection.setMedicine_id(paramDTO.getMedicine_id());
                    MedicineCollectionEntity collectionEntity = iMedicineCollectionService.getObject(medicineCollection);
                    if (collectionEntity != null) {
                        medicineDetailDTO.setMedicine_collect_flag(true);
                    } else {
                        medicineDetailDTO.setMedicine_collect_flag(false);
                    }

                } else {
                    // 当用户未登录时，false：默认为没有收藏
                    medicineDetailDTO.setMedicine_collect_flag(false);

                }

            }

        } catch (Exception e) {
            medicineDetailDTO.setReturnCode("-1001");
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
  
    public ActionForward doInitMedicineTypeList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 参数DTO
        MedicineInfoDTO paramDTO = (MedicineInfoDTO) form;
        MedicineTypeListClientDTO medicineTypeListClientDTO = new MedicineTypeListClientDTO();
        List<String> medicineTypeList = null;
        try {
        	//logger.
            List<MedicineTypeEntity> medicineTypeEntityList = iMedicineTypeService.getMedicineTypeList();
            if (medicineTypeEntityList != null && medicineTypeEntityList.size() > 0) {
                medicineTypeList = new ArrayList<String>();
                // 药品类别列表
                for (MedicineTypeEntity medicineTypeEntity : medicineTypeEntityList) {
                    if (medicineTypeEntity.getMedicine_type_name() != null && !"".equals(medicineTypeEntity.getMedicine_type_name()))
                        medicineTypeList.add(medicineTypeEntity.getMedicine_type_name());
                }

            }
            // 疾病类别列表
            medicineTypeListClientDTO.setMedicineForIllList(getAllIllList());
            medicineTypeListClientDTO.setMedicineTypeList(medicineTypeList);

        } catch (Exception e) {
            medicineTypeListClientDTO.setReturnCode("-1002");
            paramDTO.setErrFlg(true);
        }
        if (!paramDTO.isErrFlg()) {
            medicineTypeListClientDTO.setReturnCode("0");
        }

        toJson(response, medicineTypeListClientDTO);
        return null;

    }

    // 获取疾病列表
    private List<String> getAllIllList() {

        List<String> allIllNessArgs = null;
        String[] illNessArgs = StringUtils.split(ResourceLocator.getI18NMessage(this, Constants.KEY_ILLNESS_LIST), "|");
        if (illNessArgs != null && illNessArgs.length > 0) {
            allIllNessArgs = new ArrayList<String>();
            for (String illness : illNessArgs) {
                allIllNessArgs.add(illness);
            }
        }

        return allIllNessArgs;

    }
}
