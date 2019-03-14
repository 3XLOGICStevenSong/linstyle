package com.djb.ylt.medicine.action;

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
import com.djb.ylt.medicine.dto.MedicineAnswerDTO;
import com.djb.ylt.medicine.dtoclient.MedicineAnswerClientDTO;
import com.djb.ylt.medicine.entity.MedicineAnswerEntity;
import com.djb.ylt.medicine.service.IMedicineAnswerService;
import com.djb.ylt.user.entity.DoctorEntity;
import com.djb.ylt.user.entity.PatientEntity;
import com.djb.ylt.user.entity.UserLoginEntity;
import com.djb.ylt.user.service.IDoctorService;
import com.djb.ylt.user.service.IPatientService;
import com.djb.ylt.user.service.IUserLoginService;

@Controller("/MedicineAnswer")
public class MedicineAnswerAction extends BaseAction {

    @Autowired
    @Qualifier("iMedicineAnswerService")
    private IMedicineAnswerService iMedicineAnswerService;
    @Autowired
   	@Qualifier("iDoctorService")
   	private IDoctorService iDoctorService;
       @Autowired
   	@Qualifier("iPatientService")
   	private IPatientService iPatientService;
       
       @Autowired
   	@Qualifier("iUserLoginService")
   	private IUserLoginService iUserLoginService;
    public MedicineAnswerAction() {
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

    public ActionForward doInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        MedicineAnswerDTO paramDTO = (MedicineAnswerDTO) form;
        // test:
        // paramDTO.setQuestion_id(1);
        // paramDTO.setAnswer_user_id(2);
        // 参数Entity
        MedicineAnswerEntity paramEntity = new MedicineAnswerEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果DTO
        MedicineAnswerClientDTO medicineAnswerClientDTO = new MedicineAnswerClientDTO();
        try {
        	// 获取头像和名字
        				UserLoginEntity userLoginEntity = new UserLoginEntity();
        				UserLoginEntity userLoginResult = new UserLoginEntity();
        				userLoginEntity.setUserId(paramDTO.getUserId());
        				userLoginResult = iUserLoginService.getObject(userLoginEntity);
        				if (userLoginResult != null) {
        					if ("0".equals(userLoginResult.getRole())) {
        						// 参数Entity
        						DoctorEntity doctorEntity = new DoctorEntity();
        						// 结果Entity
        						DoctorEntity doctorResult = new DoctorEntity();
        						doctorEntity.setUserId(userLoginResult.getUserId());
        						doctorResult = iDoctorService.getObject(doctorEntity);
        						if (doctorResult != null) {
        							paramEntity.setAnswer_user_name(doctorResult.getName());
        							paramEntity.setAnswer_user_top_pic(doctorResult.getHeadPic());
        						}}
        						if ("1".equals(userLoginEntity.getRole())) {
        							// 参数Entity
        							PatientEntity patientEntity = new PatientEntity();
        							// 结果Entity
        							PatientEntity patientResult = new PatientEntity();
        							patientEntity.setUserId(userLoginEntity.getUserId());
        							patientResult = iPatientService.getObject(patientEntity);
        							if (patientResult != null) {
        								paramEntity.setAnswer_user_name(patientResult.getName());
        								paramEntity.setAnswer_user_top_pic(patientResult.getPatientPic());

        							}
        						}
        					}
            iMedicineAnswerService.addMedicineAnswer(paramEntity);

        } catch (Exception e) {
            medicineAnswerClientDTO.setReturnCode("-1042");
            paramDTO.setErrFlg(true);
        }
        if (!paramDTO.isErrFlg()) {
            medicineAnswerClientDTO.setReturnCode("0");
        }
        // android
        toJson(response, medicineAnswerClientDTO);
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
    public ActionForward doDeleteMedicineAnswer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 参数DTO
        MedicineAnswerDTO paramDTO = (MedicineAnswerDTO) form;
        // paramDTO.setMedicine_id(1);
        // paramDTO.setUser_id(1);
        // 参数Entity
        MedicineAnswerEntity paramEntity = new MedicineAnswerEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果DTO
        MedicineAnswerClientDTO medicineAnswerClientDTO = new MedicineAnswerClientDTO();
        try {
            iMedicineAnswerService.deleteMedicineAnswer(paramEntity);
        } catch (Exception e) {
            medicineAnswerClientDTO.setReturnCode("-1043");
            paramDTO.setErrFlg(true);
        }
        if (!paramDTO.isErrFlg()) {
            medicineAnswerClientDTO.setReturnCode("0");
        }
        // android
        toJson(response, medicineAnswerClientDTO);
        // ;
        return null;

    }

}
