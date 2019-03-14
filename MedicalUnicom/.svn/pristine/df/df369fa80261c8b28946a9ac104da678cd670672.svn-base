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
import com.djb.ylt.medicine.dto.MedicineQuestionDTO;
import com.djb.ylt.medicine.dtoclient.MedicineAnswerDetailDTO;
import com.djb.ylt.medicine.dtoclient.MedicineQuestionClientDTO;
import com.djb.ylt.medicine.dtoclient.MedicineQuestionDetailClientDTO;
import com.djb.ylt.medicine.dtoclient.MedicineQuestionDetailDTO;
import com.djb.ylt.medicine.entity.MedicineAnswerEntity;
import com.djb.ylt.medicine.entity.MedicineInfoEntity;
import com.djb.ylt.medicine.entity.MedicineQuestionEntity;
import com.djb.ylt.medicine.service.IMedicineAnswerService;
import com.djb.ylt.medicine.service.IMedicineQuestionService;
import com.djb.ylt.user.entity.DoctorEntity;
import com.djb.ylt.user.entity.PatientEntity;
import com.djb.ylt.user.entity.UserLoginEntity;
import com.djb.ylt.user.service.IDoctorService;
import com.djb.ylt.user.service.IPatientService;
import com.djb.ylt.user.service.IUserLoginService;

@Controller("/MedicineQuestion")
public class MedicineQuestionAction extends BaseAction {

	@Autowired
	@Qualifier("iMedicineQuestionService")
	private IMedicineQuestionService iMedicineQuestionService;

	@Autowired
	@Qualifier("iMedicineAnswerService")
	private IMedicineAnswerService iMedicineAnswerService;
	@Autowired
	@Qualifier("iUserLoginService")
	private IUserLoginService iUserLoginService;
	@Autowired
	@Qualifier("iDoctorService")
	private IDoctorService iDoctorService;
	@Autowired
	@Qualifier("iPatientService")
	private IPatientService iPatientService;

	public MedicineQuestionAction() {
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

	public ActionForward doGetMedicineQuestionList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 参数DTO
		MedicineQuestionDTO paramDTO = (MedicineQuestionDTO) form;
		// 参数Entity
		MedicineQuestionEntity paramEntity = new MedicineQuestionEntity();
		CommonUtil.reflectClass(paramDTO, paramEntity);
		// 客户端DTO
		MedicineQuestionClientDTO medicineClientDTO = new MedicineQuestionClientDTO();
		List<MedicineQuestionEntity> resultList = null;
		List<MedicineQuestionDetailDTO> medicineDetailDTOList = null;

		try {
			resultList = iMedicineQuestionService.getMedicineQuestionList(paramEntity);

			if (resultList != null && resultList.size() > 0) {
				// 设置时间标示： 日期格式化处理
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				medicineDetailDTOList = new ArrayList<MedicineQuestionDetailDTO>();
				for (MedicineQuestionEntity medicineEntity : resultList) {
					MedicineQuestionDetailDTO medicineDetailDTO = new MedicineQuestionDetailDTO();
					CommonUtil.reflectClass(medicineEntity, medicineDetailDTO);
					// 设置时间格式：
					if (medicineEntity.getCreate_time() != null) {
						medicineDetailDTO.setAsk_question_time(sdf.format(medicineEntity.getCreate_time()));
					}

					// 设置答案：
					List<MedicineAnswerDetailDTO> medicineAnswerDTOs = null;
					if (medicineEntity.getMedicineAnswerEntitys() != null
							&& medicineEntity.getMedicineAnswerEntitys().size() > 0
							&& medicineEntity.getMedicineAnswerEntitys().get(0).getAnswer_id() != null) {
						medicineAnswerDTOs = new ArrayList<MedicineAnswerDetailDTO>();

						for (MedicineAnswerEntity answerEntity : medicineEntity.getMedicineAnswerEntitys()) {
							MedicineAnswerDetailDTO answerDTO = new MedicineAnswerDetailDTO();
							CommonUtil.reflectClass(answerEntity, answerDTO);
							// 设置时间格式：
							if (answerEntity.getCreate_time() != null) {
								answerDTO.setAnswer_question_time(sdf.format(answerEntity.getCreate_time()));
							}

							medicineAnswerDTOs.add(answerDTO);
						}
						medicineDetailDTO.setMedicineAnswerDTOs(medicineAnswerDTOs);
					}
					// 设置药品信息

					MedicineInfoEntity medicineInfoEntity = medicineEntity.getMedicineInfoEntity();
					if (medicineInfoEntity != null) {
						CommonUtil.reflectClass(medicineInfoEntity, medicineDetailDTO);
						medicineDetailDTO.setMedicine_price(medicineInfoEntity.getMedicine_price());
						medicineDetailDTO.setMedicine_grade(medicineInfoEntity.getMedicine_grade());

					}

					medicineDetailDTOList.add(medicineDetailDTO);
				}
				medicineClientDTO.setList(medicineDetailDTOList);
			}

		} catch (Exception e) {
			medicineClientDTO.setReturnCode("-1030");
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
	
	public ActionForward doGetMedicineQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 参数DTO
		MedicineQuestionDTO paramDTO = (MedicineQuestionDTO) form;
		// 参数Entity
		MedicineQuestionEntity paramEntity = new MedicineQuestionEntity();
		CommonUtil.reflectClass(paramDTO, paramEntity);
		// 结果DTO
		MedicineQuestionEntity medicineEntity = null;
		// 客户端DTO
		MedicineQuestionDetailClientDTO medicineDetailDTO = new MedicineQuestionDetailClientDTO();
		try {
			medicineEntity = iMedicineQuestionService.getObject(paramEntity);
			if (medicineEntity != null) {
				CommonUtil.reflectClass(medicineEntity, medicineDetailDTO);
			}

		} catch (Exception e) {
			medicineDetailDTO.setReturnCode("-1031");
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

	public ActionForward doInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		MedicineQuestionDTO paramDTO = (MedicineQuestionDTO) form;
		// 参数Entity
		MedicineQuestionEntity paramEntity = new MedicineQuestionEntity();
		CommonUtil.reflectClass(paramDTO, paramEntity);
		// 结果DTO
		MedicineQuestionDetailClientDTO medicineQuestionDetailClientDTO = new MedicineQuestionDetailClientDTO();
		try {
			// test
			// paramEntity.setMedicine_id(1);
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
						paramEntity.setAsk_user_name(doctorResult.getName());
						paramEntity.setAsk_user_top_pic(doctorResult.getHeadPic());

					}}
					if ("1".equals(userLoginEntity.getRole())) {
						// 参数Entity
						PatientEntity patientEntity = new PatientEntity();
						// 结果Entity
						PatientEntity patientResult = new PatientEntity();
						patientEntity.setUserId(userLoginEntity.getUserId());
						patientResult = iPatientService.getObject(patientEntity);
						if (patientResult != null) {
							paramEntity.setAsk_user_name(patientResult.getName());
							paramEntity.setAsk_user_top_pic(patientResult.getPatientPic());

						}
					}
			}
				iMedicineQuestionService.addMedicineQuestion(paramEntity);
			
		} catch (Exception e) {
			medicineQuestionDetailClientDTO.setReturnCode("-1032");
			paramDTO.setErrFlg(true);
		}
		if (!paramDTO.isErrFlg()) {
			medicineQuestionDetailClientDTO.setReturnCode("0");
		}
		// android
		toJson(response, medicineQuestionDetailClientDTO);
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
	public ActionForward doDeleteMedicineQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 参数DTO
		MedicineQuestionDTO paramDTO = (MedicineQuestionDTO) form;
		// paramDTO.setMedicine_id(1);
		// paramDTO.setUser_id(1);
		// 参数Entity
		MedicineQuestionEntity paramEntity = new MedicineQuestionEntity();
		CommonUtil.reflectClass(paramDTO, paramEntity);
		// 结果DTO
		MedicineQuestionDetailClientDTO medicineQuestionDetailClientDTO = new MedicineQuestionDetailClientDTO();
		try {
			// 删除所有和当前问题的所有答案
			MedicineAnswerEntity medicineAnswer = new MedicineAnswerEntity();
			medicineAnswer.setQuestion_id(paramDTO.getQuestion_id());
			List<MedicineAnswerEntity> list = iMedicineAnswerService.getMedicineAnswerList(medicineAnswer);
			iMedicineAnswerService.deleteMedicineAnswerBatch(list);
			// 删除问题
			iMedicineQuestionService.deleteMedicineQuestion(paramEntity);
		} catch (Exception e) {
			medicineQuestionDetailClientDTO.setReturnCode("-1013");
			paramDTO.setErrFlg(true);
		}
		if (!paramDTO.isErrFlg()) {
			medicineQuestionDetailClientDTO.setReturnCode("0");
		}
		// android
		toJson(response, medicineQuestionDetailClientDTO);
		// ;
		return null;

	}

}
