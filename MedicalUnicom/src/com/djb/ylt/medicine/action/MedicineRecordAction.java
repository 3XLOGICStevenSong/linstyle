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
import com.djb.ylt.medicine.dto.MedicalRecordDTO;
import com.djb.ylt.medicine.dtoclient.InspectionReportDetailDTO;
import com.djb.ylt.medicine.dtoclient.MedicalRecordClientDTO;
import com.djb.ylt.medicine.dtoclient.MedicalRecordDetailClientDTO;
import com.djb.ylt.medicine.dtoclient.MedicalRecordDetailDTO;
import com.djb.ylt.medicine.entity.InspectionReportEntity;
import com.djb.ylt.medicine.entity.MedicalRecordEntity;
import com.djb.ylt.medicine.service.IMedicineRecordService;



@Controller("/MedicineRecord")
public class MedicineRecordAction extends BaseAction {

    @Autowired
    @Qualifier("iMedicineRecordService")
    private IMedicineRecordService iMedicineRecordService;

    public MedicineRecordAction() {
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
 
    public ActionForward doGetMedicineRecordList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 参数DTO
        MedicalRecordDTO paramDTO = (MedicalRecordDTO) form;
        // 参数Entity
        MedicalRecordEntity paramEntity = new MedicalRecordEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);
        paramEntity.setUser_id(paramDTO.getPatientId());
        // 客户端DTO
        MedicalRecordClientDTO medicineClientDTO = new MedicalRecordClientDTO();
        List<MedicalRecordEntity> resultList = null;
        List<MedicalRecordDetailDTO> medicineDetailDTOList = null;

        try {
            // 设置时间标示： 日期格式化处理
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            resultList = iMedicineRecordService.getMedicineRecordList(paramEntity);

            if (resultList != null && resultList.size() > 0) {

                medicineDetailDTOList = new ArrayList<MedicalRecordDetailDTO>();
                for (MedicalRecordEntity medicineEntity : resultList) {
                    MedicalRecordDetailDTO medicineDetailDTO = new MedicalRecordDetailDTO();

                    CommonUtil.reflectClass(medicineEntity, medicineDetailDTO);
                    // inspection_report_name
                    if (medicineEntity.getMedical_record_time() != null) {
                        medicineDetailDTO.setMedical_record_time(sdf.format(medicineEntity.getMedical_record_time()));

                    }
                    // 用户基本信息赋值
                    if (medicineEntity.getPatientEntity() != null) {
                        // 获取完整的用户信息
                        // UserInfoDetailDTO userInfoDetailDTO = new
                        // UserInfoDetailDTO();
                        // CommonUtil.reflectClass(medicineEntity.getUserInfoEntity(),
                        // userInfoDetailDTO);
                        // medicineDetailDTO.setUserInfoDetailDTO(userInfoDetailDTO);

                       // CommonUtil.reflectClass(medicineEntity.getUserInfoEntity(), medicineDetailDTO);
                    }

                    // 检查报告信息赋值
                    List<InspectionReportDetailDTO> inspectionReportDetailDTOs = null;
                    if (medicineEntity.getInspectionReportEntitys() != null && medicineEntity.getInspectionReportEntitys().size() > 0
                                    && medicineEntity.getInspectionReportEntitys().get(0).getInspection_report_id() != null) {
                        inspectionReportDetailDTOs = new ArrayList<InspectionReportDetailDTO>();
                        for (InspectionReportEntity inspectionEntity : medicineEntity.getInspectionReportEntitys()) {
                            InspectionReportDetailDTO inspectionDTO = new InspectionReportDetailDTO();
                            CommonUtil.reflectClass(inspectionEntity, inspectionDTO);

                            // inspection_report_name
                            if (inspectionEntity.getMedical_report_time() != null) {
                                inspectionDTO.setMedical_report_time(sdf.format(inspectionEntity.getMedical_report_time()));

                            }
                            inspectionReportDetailDTOs.add(inspectionDTO);
                        }

                        medicineDetailDTO.setInspectionReportDetailDTOs(inspectionReportDetailDTOs);
                    }

                    medicineDetailDTOList.add(medicineDetailDTO);
                }
                medicineClientDTO.setList(medicineDetailDTOList);
            }

        } catch (Exception e) {
            medicineClientDTO.setReturnCode("-1050");
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
   
    public ActionForward doGetMedicineRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 参数DTO
        MedicalRecordDTO paramDTO = (MedicalRecordDTO) form;
        // 参数Entity
        MedicalRecordEntity paramEntity = new MedicalRecordEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);
        paramEntity.setUser_id(paramDTO.getPatientId());
        // 结果DTO
        MedicalRecordEntity medicineEntity = null;
        // 客户端DTO
        MedicalRecordDetailClientDTO medicineDetailDTO = new MedicalRecordDetailClientDTO();
        try {
            // 设置时间标示： 日期格式化处理
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            medicineEntity = iMedicineRecordService.getObject(paramEntity);
            if (medicineEntity != null) {
                CommonUtil.reflectClass(medicineEntity, medicineDetailDTO);

                // medical_record_time
                if (medicineEntity.getMedical_record_time() != null) {
                    medicineDetailDTO.setMedical_record_time(sdf.format(medicineEntity.getMedical_record_time()));
                }

                // 用户基本信息赋值
                if (medicineEntity.getPatientEntity() != null) {
                    // 获取完整的用户信息
                    // UserInfoDetailDTO userInfoDetailDTO = new
                    // UserInfoDetailDTO();
                    // CommonUtil.reflectClass(medicineEntity.getUserInfoEntity(),
                    // userInfoDetailDTO);
                    // medicineDetailDTO.setUserInfoDetailDTO(userInfoDetailDTO);

                  //  CommonUtil.reflectClass(medicineEntity.getUserInfoEntity(), medicineDetailDTO);
                }

                // 检查报告信息赋值
                List<InspectionReportDetailDTO> inspectionReportDetailDTOs = null;
                if (medicineEntity.getInspectionReportEntitys() != null && medicineEntity.getInspectionReportEntitys().size() > 0
                                && medicineEntity.getInspectionReportEntitys().get(0).getInspection_report_id() != null) {
                    inspectionReportDetailDTOs = new ArrayList<InspectionReportDetailDTO>();
                    for (InspectionReportEntity inspectionEntity : medicineEntity.getInspectionReportEntitys()) {
                        InspectionReportDetailDTO inspectionDTO = new InspectionReportDetailDTO();
                        CommonUtil.reflectClass(inspectionEntity, inspectionDTO);
                        // inspection_report_name
                        if (inspectionEntity.getMedical_report_time() != null) {
                            inspectionDTO.setMedical_report_time(sdf.format(inspectionEntity.getMedical_report_time()));

                        }
                        inspectionReportDetailDTOs.add(inspectionDTO);
                    }

                    medicineDetailDTO.setInspectionReportDetailDTOs(inspectionReportDetailDTOs);
                }
            }

        } catch (Exception e) {
            medicineDetailDTO.setReturnCode("-1051");
            paramDTO.setErrFlg(true);
        }
        if (!paramDTO.isErrFlg()) {
            medicineDetailDTO.setReturnCode("0");
        }

        toJson(response, medicineDetailDTO);
        return null;

    }

}
