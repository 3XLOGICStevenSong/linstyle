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
import com.djb.ylt.framework.action.BaseAction;
import com.djb.ylt.medicine.dto.PrescriptionInfoDTO;
import com.djb.ylt.medicine.dtoclient.PrescriptionInfoClientDTO;
import com.djb.ylt.medicine.dtoclient.PrescriptionInfoDetailClientDTO;
import com.djb.ylt.medicine.entity.PrescriptionInfoEntity;
import com.djb.ylt.medicine.service.IPrescriptionInfoService;



@Controller("/PrescriptionInfo")
public class PrescriptionInfoAction extends BaseAction {
    @Autowired
    @Qualifier("iPrescriptionInfoService")
    private IPrescriptionInfoService iPrescriptionInfoService;
   
    public PrescriptionInfoAction() {
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

    public ActionForward doGetPrescriptionInfoList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        PrescriptionInfoDTO paramDTO = (PrescriptionInfoDTO) form;
        //sparamDTO.setUser_id(9);
        PrescriptionInfoEntity paramEntity = new PrescriptionInfoEntity();
        List<PrescriptionInfoEntity> resultEntity = null;
       paramEntity.setUser_id(paramDTO.getPatientId());
        PrescriptionInfoClientDTO resultClientDTO = new PrescriptionInfoClientDTO();
        List<PrescriptionInfoDetailClientDTO> PrescriptionInfoList = null;
        try {
            resultEntity = iPrescriptionInfoService.getPrescriptionInfoList(paramEntity);
            if (resultEntity != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                PrescriptionInfoList = new ArrayList<PrescriptionInfoDetailClientDTO>();
                for (PrescriptionInfoEntity prescriptionInfo : resultEntity) {
                    PrescriptionInfoDetailClientDTO prescriptionInfoDTO = new PrescriptionInfoDetailClientDTO();
                    prescriptionInfoDTO.setPrescription_id(prescriptionInfo.getPrescription_id());
                    prescriptionInfoDTO.setPrescription_name(prescriptionInfo.getPrescription_name());
                    prescriptionInfoDTO.setPrescription_start_time(sdf.format(prescriptionInfo.getPrescription_start_time()));
                    prescriptionInfoDTO.setPrescription_end_time(sdf.format(prescriptionInfo.getPrescription_end_time()));
                    // CommonUtil.reflectClass(prescriptionInfo,
                    // prescriptionInfoDTO);
                    PrescriptionInfoList.add(prescriptionInfoDTO);

                }
                resultClientDTO.setList(PrescriptionInfoList);
            }
        } catch (Exception e) {
            resultClientDTO.setReturnCode("-1100");
            paramDTO.setErrFlg(true);
        }
        if (!paramDTO.isErrFlg()) {
            resultClientDTO.setReturnCode("0");
        }

        toJson(response, resultClientDTO);
        return null;
    }

   
}
