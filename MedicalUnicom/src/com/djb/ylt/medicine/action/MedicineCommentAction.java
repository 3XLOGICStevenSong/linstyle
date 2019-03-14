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
import com.djb.ylt.common.util.Constants;
import com.djb.ylt.common.util.ResourceLocator;
import com.djb.ylt.framework.action.BaseAction;
import com.djb.ylt.medicine.dto.MedicineCommentDTO;
import com.djb.ylt.medicine.dtoclient.MedicineCommentClientDTO;
import com.djb.ylt.medicine.dtoclient.MedicineCommentDetailClientDTO;
import com.djb.ylt.medicine.dtoclient.MedicineCommentDetailDTO;
import com.djb.ylt.medicine.entity.MedicineCommentEntity;
import com.djb.ylt.medicine.entity.MedicineInfoEntity;
import com.djb.ylt.medicine.service.IMedicineCommentService;
import com.djb.ylt.user.entity.DoctorEntity;
import com.djb.ylt.user.entity.PatientEntity;
import com.djb.ylt.user.entity.UserLoginEntity;
import com.djb.ylt.user.service.IDoctorService;
import com.djb.ylt.user.service.IPatientService;
import com.djb.ylt.user.service.IUserLoginService;

@Controller("/MedicineComment")
public class MedicineCommentAction extends BaseAction {

    @Autowired
    @Qualifier("iMedicineCommentService")
    private IMedicineCommentService iMedicineCommentService;

    @Autowired
	@Qualifier("iDoctorService")
	private IDoctorService iDoctorService;
    @Autowired
	@Qualifier("iPatientService")
	private IPatientService iPatientService;
    
    @Autowired
	@Qualifier("iUserLoginService")
	private IUserLoginService iUserLoginService;
    public MedicineCommentAction() {
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

    public ActionForward doGetMedicineCommentList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 参数DTO
        MedicineCommentDTO paramDTO = (MedicineCommentDTO) form;
        // 参数Entity
        MedicineCommentEntity paramEntity = new MedicineCommentEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 客户端DTO
        MedicineCommentClientDTO medicineClientDTO = new MedicineCommentClientDTO();
        List<MedicineCommentEntity> resultList = null;
        List<MedicineCommentDetailDTO> medicineDetailDTOList = null;

        try {
            resultList = iMedicineCommentService.getMedicineCommentList(paramEntity);

            if (resultList != null && resultList.size() > 0) {

                // 设置时间标示： 日期格式化处理
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                medicineDetailDTOList = new ArrayList<MedicineCommentDetailDTO>();
                for (MedicineCommentEntity medicineEntity : resultList) {
                    MedicineCommentDetailDTO medicineDetailDTO = new MedicineCommentDetailDTO();
                    CommonUtil.reflectClass(medicineEntity, medicineDetailDTO);
                    // 设置时间格式：
                    if (medicineEntity.getComment_time() != null) {
                        medicineDetailDTO.setComment_time(sdf.format(medicineEntity.getComment_time()));
                    }

                    // 给药品的相关信息赋值
                    MedicineInfoEntity medicineinfo = medicineEntity.getMedicineInfoEntity();
                    if (medicineinfo != null) {
                        CommonUtil.reflectClass(medicineinfo, medicineDetailDTO);
                        // 药品图片路径
                        if (medicineinfo.getMedicine_pic() != null && !"".equals(medicineinfo.getMedicine_pic())) {
                            medicineDetailDTO.setMedicine_pic(ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS)
                                            + medicineinfo.getMedicine_pic());
                        }
                        // 药品分数
                        medicineDetailDTO.setComment_grade(medicineEntity.getComment_grade());
                    }
                    // 网友的基本信息
                    UserLoginEntity userLoginEntity = medicineEntity.getUserLoginEntity();
                    if (userLoginEntity != null) {
                    	if("0".equals(userLoginEntity.getRole())){
                    		// 参数Entity
                    		DoctorEntity doctorEntity = new DoctorEntity();
                    		// 结果Entity
                    		DoctorEntity doctorResult = new DoctorEntity();
                    		doctorEntity.setUserId(userLoginEntity.getUserId());
                    		doctorResult=iDoctorService.getObject(doctorEntity);
                    		if(doctorResult!=null){
                    			medicineDetailDTO.setU_name(doctorResult.getName());
                    			medicineDetailDTO.setUser_top_pic(doctorResult.getHeadPic());
                    		
                    		}}
                    		if("1".equals(userLoginEntity.getRole())){
                        		// 参数Entity
                        		PatientEntity patientEntity = new PatientEntity();
                        		// 结果Entity
                        		PatientEntity patientResult = new PatientEntity();
                        		patientEntity.setUserId(userLoginEntity.getUserId());
                        		patientResult=iPatientService.getObject(patientEntity);
                        		if(patientResult!=null){
                        			medicineDetailDTO.setU_name(patientResult.getName());
                        			medicineDetailDTO.setUser_top_pic(patientResult.getPatientPic());
                        		
                        		}
                    	}
                    	
                      //   CommonUtil.reflectClass(userLoginEntity, medicineDetailDTO);
                        // 用户昵称
                      //  medicineDetailDTO.setU_name(userLoginEntity.getU_name());
                        // 用户头像路径
//                        if (userLoginEntity.getUser_top_pic() != null && !"".equals(userLoginEntity.getUser_top_pic())) {
//                            medicineDetailDTO.setUser_top_pic(ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS)
//                                            + userLoginEntity.getUser_top_pic());
//                        }
                    }

                    medicineDetailDTOList.add(medicineDetailDTO);
                }
                medicineClientDTO.setList(medicineDetailDTOList);
            }

        } catch (Exception e) {
            medicineClientDTO.setReturnCode("-1020");
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

    public ActionForward doGetMedicineComment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 参数DTO
        MedicineCommentDTO paramDTO = (MedicineCommentDTO) form;
        // 参数Entity
        MedicineCommentEntity paramEntity = new MedicineCommentEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果DTO
        MedicineCommentEntity medicineEntity = null;
        // 客户端DTO
        MedicineCommentDetailClientDTO medicineDetailDTO = new MedicineCommentDetailClientDTO();
        try {
            medicineEntity = iMedicineCommentService.getObject(paramEntity);
            if (medicineEntity != null) {
                CommonUtil.reflectClass(medicineEntity, medicineDetailDTO);
            }

        } catch (Exception e) {
            medicineDetailDTO.setReturnCode("-1021");
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
        MedicineCommentDTO paramDTO = (MedicineCommentDTO) form;
        // 参数Entity
        MedicineCommentEntity paramEntity = new MedicineCommentEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果DTO
        MedicineCommentDetailClientDTO medicineCommentDetailClientDTO = new MedicineCommentDetailClientDTO();
        try {
            // test
            // paramEntity.setUser_id(1);
            // paramEntity.setMedicine_id(1);
            // paramEntity.setComment_content("测试数据：评论3");
        	if(paramDTO.getComment_grade()!=null){
        	paramEntity.setComment_grade((Float)paramDTO.getComment_grade());}
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
						paramEntity.setU_name(doctorResult.getName());
						paramEntity.setU_top_pic(doctorResult.getHeadPic());

					}}
					if ("1".equals(userLoginEntity.getRole())) {
						// 参数Entity
						PatientEntity patientEntity = new PatientEntity();
						// 结果Entity
						PatientEntity patientResult = new PatientEntity();
						patientEntity.setUserId(userLoginEntity.getUserId());
						patientResult = iPatientService.getObject(patientEntity);
						if (patientResult != null) {
							paramEntity.setU_name(patientResult.getName());
							paramEntity.setU_top_pic(patientResult.getPatientPic());

						}
					}
				}
            iMedicineCommentService.addMedicineComment(paramEntity);

        } catch (Exception e) {
            medicineCommentDetailClientDTO.setReturnCode("-1022");
            paramDTO.setErrFlg(true);
        }
        if (!paramDTO.isErrFlg()) {
            medicineCommentDetailClientDTO.setReturnCode("0");
        }
        // android
        toJson(response, medicineCommentDetailClientDTO);
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
    public ActionForward doDeleteMedicineComment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 参数DTO
        MedicineCommentDTO paramDTO = (MedicineCommentDTO) form;
        // paramDTO.setMedicine_id(1);
        // paramDTO.setUser_id(1);
        // 参数Entity
        MedicineCommentEntity paramEntity = new MedicineCommentEntity();
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果DTO
        MedicineCommentDetailClientDTO medicineCommentDetailClientDTO = new MedicineCommentDetailClientDTO();
        try {
            iMedicineCommentService.deleteMedicineComment(paramEntity);
        } catch (Exception e) {
            medicineCommentDetailClientDTO.setReturnCode("-1023");
            paramDTO.setErrFlg(true);
        }
        if (!paramDTO.isErrFlg()) {
            medicineCommentDetailClientDTO.setReturnCode("0");
        }
        // android
        toJson(response, medicineCommentDetailClientDTO);
        // ;
        return null;

    }

}
