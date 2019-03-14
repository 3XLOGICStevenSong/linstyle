package com.djb.ylt.medicine.action;

import java.text.SimpleDateFormat;
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
import com.djb.ylt.medicine.dto.PrescriptionMedicineDTO;
import com.djb.ylt.medicine.dtoclient.MedicineInfoClientDTO;
import com.djb.ylt.medicine.dtoclient.MedicineInfoDetailDTO;
import com.djb.ylt.medicine.dtoclient.PrescriptionInfoDetailDTO;
import com.djb.ylt.medicine.dtoclient.PrescriptionMedicineClientDTO;
import com.djb.ylt.medicine.dtoclient.PrescriptionMedicineDetailDTO;
import com.djb.ylt.medicine.entity.MedicineInfoEntity;
import com.djb.ylt.medicine.entity.PrescriptionMedicineEntity;
import com.djb.ylt.medicine.service.IMedicineInfoService;
import com.djb.ylt.medicine.service.IPrescriptionInfoService;
import com.djb.ylt.medicine.service.IPrescriptionMedicineService;


@Controller("/PrescriptionMedicine")
public class PrescriptionMedicineAction extends BaseAction {
    @Autowired
    @Qualifier("iPrescriptionInfoService")
    private IPrescriptionInfoService iPrescriptionInfoService;
    @Autowired
    @Qualifier("iPrescriptionMedicineService")
    private IPrescriptionMedicineService iPrescriptionMedicineService;
    @Autowired
    @Qualifier("iMedicineInfoService")
    private IMedicineInfoService iMedicineInfoService;

    public PrescriptionMedicineAction() {
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
   
    public ActionForward doGetPrescriptionMedicine(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        PrescriptionMedicineDTO paramDTO = (PrescriptionMedicineDTO) form;
        //paramDTO.setPrescription_id(1);
        PrescriptionMedicineEntity paramEntity = new PrescriptionMedicineEntity();
        paramEntity.setPrescription_id(paramDTO.getPrescription_id());
        List<PrescriptionMedicineEntity> resultEntity = null;
        PrescriptionMedicineDetailDTO prescriptionMedicineList = null;
        PrescriptionMedicineClientDTO resultDTO = null;

        PrescriptionMedicineDTO pmDTO = new PrescriptionMedicineDTO();
        try {
            resultEntity = iPrescriptionMedicineService.getPrescriptionMedicineList(paramEntity);
            resultDTO = new PrescriptionMedicineClientDTO();
            MedicineInfoClientDTO medicineresult = new MedicineInfoClientDTO();
            List<MedicineInfoDetailDTO> medicineInfoDTOs = null;
            prescriptionMedicineList = new PrescriptionMedicineDetailDTO();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (resultEntity != null && resultEntity.size()>0) {
                
                MedicineInfoEntity medicineEntity = null;
                // 获取处方信息
                if (resultEntity.get(0).getPrescriptionInfoEntity() != null) {
                    PrescriptionInfoDetailDTO pIDTO = new PrescriptionInfoDetailDTO();
                    PrescriptionMedicineEntity prescriptionMedicineEntity = resultEntity.get(0);
                    CommonUtil.reflectClass(prescriptionMedicineEntity.getPrescriptionInfoEntity(), pIDTO);
                    pIDTO.setPrescription_end_time(sdf.format(prescriptionMedicineEntity.getPrescriptionInfoEntity().getPrescription_end_time()));
                    pIDTO.setPrescription_start_time(sdf.format(prescriptionMedicineEntity.getPrescriptionInfoEntity().getPrescription_start_time()));
                    prescriptionMedicineList.setPrescriptionInfoDTO(pIDTO);
                }
                medicineInfoDTOs = new ArrayList<MedicineInfoDetailDTO>();
                for (PrescriptionMedicineEntity pmEntity : resultEntity) {
                    
                    // 获取药的信息
                   
                    MedicineInfoDetailDTO medicineDetailDTO = new MedicineInfoDetailDTO();
                    if (0 != pmEntity.getMedicine_id() && pmEntity.getMedicine_id() != null) {
                        MedicineInfoEntity medicineInfoEntiy = new MedicineInfoEntity();
                        medicineInfoEntiy.setMedicine_id(pmEntity.getMedicine_id());
                        medicineEntity = iMedicineInfoService.getObject(medicineInfoEntiy);
                        medicineDetailDTO.setMedicine_name(medicineEntity.getMedicine_name());
                        medicineDetailDTO.setMedicine_norms(medicineEntity.getMedicine_norms());
                        medicineDetailDTO.setMedicine_pic(medicineEntity.getMedicine_pic());
                        medicineDetailDTO.setMedicine_id(medicineEntity.getMedicine_id());
                       
                        // 获取吃法
                        medicineDetailDTO.setTaking_method(pmEntity.getTaking_method());
                        medicineDetailDTO.setMemo(pmEntity.getMemo());
                        medicineInfoDTOs.add(medicineDetailDTO);
                       
                    }

                }
                medicineresult.setList(medicineInfoDTOs);
                prescriptionMedicineList.setMedicineInfoDTOs(medicineresult);

                // 结果
                // resultDTO.setList(prescriptionMedicineList);

            }

        } catch (Exception e) {
            prescriptionMedicineList.setReturnCode("-1101");
            paramDTO.setErrFlg(true);
        }
        if (!paramDTO.isErrFlg()) {
            prescriptionMedicineList.setReturnCode("0");
        }

        toJson(response, prescriptionMedicineList);
        return null;
    }
}
