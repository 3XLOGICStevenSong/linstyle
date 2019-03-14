/**
 * Project Name:MedicalUnicomAPP
 * File Name:PatientAction.java
 * Package Name:com.djb.ylt.user.action
 * Copyright © 大连必捷必信息技术有限公司  All Rights Reserved.
*/

package com.djb.ylt.user.action;

import java.text.DecimalFormat;
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
import com.djb.ylt.framework.dto.BaseClientDTO;
import com.djb.ylt.user.dto.InterrogationPackageDTO;
import com.djb.ylt.user.dtoclient.InterrogationClientDTO;
import com.djb.ylt.user.dtoclient.InterrogationListDTO;
import com.djb.ylt.user.entity.DoctorCommentEntity;
import com.djb.ylt.user.entity.InterrogationPackageEntity;
import com.djb.ylt.user.service.IDoctorCommentService;
import com.djb.ylt.user.service.IInterrogationPackageService;

/**
 * <p>
 * <strong>类名: </strong>
 * </p>
 * PatientAction <br/>
 * <p>
 * <strong>功能说明: </strong>
 * </p>
 * 这个类是封装那个哪个模块，起什么用的，需要手动填写. <br/>
 * <p>
 * <strong>创建日期: </strong><br/>
 * </p>
 * 2016年7月29日下午1:19:01 <br/>
 * 
 * @author 林行
 * @version 1.0
 * @since JDK 1.8
 */

@Controller("/Interrogation")
public class InterrogationAction extends BaseAction {

	@Autowired
	@Qualifier("iInterrogationPackageService")
	private IInterrogationPackageService iInterrogationPackageService;

	@Autowired
	@Qualifier("iDoctorCommentService")
	private IDoctorCommentService iDoctorCommentService;

	public InterrogationAction() {
		super();
	}

	/**
	 * 获取电话问诊的医生列表
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
	public ActionForward doGetTelList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		InterrogationPackageDTO paramDTO = (InterrogationPackageDTO) form;
		// 参数Entity
		InterrogationPackageEntity paramEntity = new InterrogationPackageEntity();

		// 结果ClientDTO
		InterrogationListDTO resultClientDTO = new InterrogationListDTO();
		List<InterrogationClientDTO> interrogationList = null;
		// List<SymptomClientDTO> SymptomClientDTOList=null;
		// 参数DTO->参数Entity

		CommonUtil.reflectClass(paramDTO, paramEntity);
		// paramEntity.setPatientId(1);
		// 结果Entity
		List<InterrogationPackageEntity> resultEntity = null;
		// 结果DTO
		// AppointInquiryDTO resultDTO=new AppointInquiryDTO();
		// DB查询
		try {

			paramEntity.setType("1");
			resultEntity = iInterrogationPackageService.getInterrogationPackageList(paramEntity);

			if (resultEntity != null && resultEntity.size() > 0) {
				interrogationList = new ArrayList<InterrogationClientDTO>();
				for (InterrogationPackageEntity param : resultEntity) {
					InterrogationClientDTO paramClientDTO = new InterrogationClientDTO();
					paramClientDTO.setPackageId(param.getPackageId());
					DecimalFormat format = new DecimalFormat("#.00");
					if(param.getTotal()!=null){
					paramClientDTO.setTotal(format.format(param.getTotal()));}
					//paramClientDTO.setTotal(param.getTotal());
					// 获取医生平均评分
					if (param.getDoctorEntity() != null) {
						DoctorCommentEntity doctorCommentEntity = new DoctorCommentEntity();
						doctorCommentEntity.setDoctorId(param.getDoctorEntity().getDoctorId());
						DoctorCommentEntity doctorCommentResult = new DoctorCommentEntity();
						doctorCommentResult = iDoctorCommentService.getAverageGrade(doctorCommentEntity);
						if (doctorCommentResult != null) {
							paramClientDTO.setGrade(doctorCommentResult.getGrade());
						}
						// 医生信息赋值
						paramClientDTO.setDoctorId(param.getDoctorEntity().getDoctorId());
						paramClientDTO.setHealDisease(param.getDoctorEntity().getHealDisease());
						paramClientDTO.setHeadPic(param.getDoctorEntity().getHeadPic());
						paramClientDTO.setServiceCount(param.getDoctorEntity().getServiceCount());
						paramClientDTO.setName(param.getDoctorEntity().getName());
						paramClientDTO.setPositional(param.getDoctorEntity().getPositional());
					}
					interrogationList.add(paramClientDTO);
				}

				resultClientDTO.setInterrogationClientDTO(interrogationList);
				//
			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-1051");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultClientDTO.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, resultClientDTO);

		return null;
	}

	/**
	 * 获取电话问诊的医生列表
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
	public ActionForward doGetInqueryList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		InterrogationPackageDTO paramDTO = (InterrogationPackageDTO) form;
		// 参数Entity
		InterrogationPackageEntity paramEntity = new InterrogationPackageEntity();

		// 结果ClientDTO
		InterrogationListDTO resultClientDTO = new InterrogationListDTO();
		List<InterrogationClientDTO> interrogationList = null;
		// List<SymptomClientDTO> SymptomClientDTOList=null;
		// 参数DTO->参数Entity

		CommonUtil.reflectClass(paramDTO, paramEntity);
		// paramEntity.setPatientId(1);
		// 结果Entity
		List<InterrogationPackageEntity> resultEntity = null;
		// 结果DTO
		// AppointInquiryDTO resultDTO=new AppointInquiryDTO();
		// DB查询
		try {
			// TODO SQL
			paramEntity.setType("0");
			resultEntity = iInterrogationPackageService.getInterrogationPackageList(paramEntity);

			if (resultEntity != null && resultEntity.size() > 0) {
				interrogationList = new ArrayList<InterrogationClientDTO>();
				for (InterrogationPackageEntity param : resultEntity) {
					InterrogationClientDTO paramClientDTO = new InterrogationClientDTO();
					DecimalFormat format = new DecimalFormat("#.00");
					if(param.getTotal()!=null){
					paramClientDTO.setTotal(format.format(param.getTotal()));}
					// 获取医生平均评分
					if (param.getDoctorEntity() != null) {
						DoctorCommentEntity doctorCommentEntity = new DoctorCommentEntity();
						doctorCommentEntity.setDoctorId(param.getDoctorEntity().getDoctorId());
						DoctorCommentEntity doctorCommentResult = new DoctorCommentEntity();
						doctorCommentResult = iDoctorCommentService.getAverageGrade(doctorCommentEntity);
						if (doctorCommentResult != null) {
							paramClientDTO.setGrade(doctorCommentResult.getGrade());
						}
						// 医生信息赋值
						paramClientDTO.setDoctorId(param.getDoctorEntity().getDoctorId());
						paramClientDTO.setHealDisease(param.getDoctorEntity().getHealDisease());
						paramClientDTO.setHeadPic(param.getDoctorEntity().getHeadPic());
						paramClientDTO.setServiceCount(param.getDoctorEntity().getServiceCount());
						paramClientDTO.setName(param.getDoctorEntity().getName());

					}
					interrogationList.add(paramClientDTO);
				}

				resultClientDTO.setInterrogationClientDTO(interrogationList);
				//
			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-1051");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultClientDTO.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, resultClientDTO);

		return null;
	}

	/**
	 * 医生定价
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
	public ActionForward doAddPackage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		InterrogationPackageDTO paramDTO = (InterrogationPackageDTO) form;
		// 参数Entity
		InterrogationPackageEntity paramEntity = new InterrogationPackageEntity();
		// 参数Entity
		InterrogationPackageEntity paramEntity1 = new InterrogationPackageEntity();
		// 参数Entity
		InterrogationPackageEntity paramEntity2 = new InterrogationPackageEntity();

		// 结果ClientDTO
		BaseClientDTO resultClientDTO = new BaseClientDTO();
		// test
		// paramDTO.setInqueryNum(5);
		// paramDTO.setTelNum(3);
		// paramDTO.setDoctorId(1);
		// paramDTO.setPrivateTotal(100);
		// paramDTO.setInqueryTotal(20);
		// paramDTO.setTelTotal(10);
		// paramDTO.setEffectTime("10");
		// DB查询
		try {
			// 参数Entity
			InterrogationPackageEntity packageEntity = new InterrogationPackageEntity();
			packageEntity.setDoctorId(paramDTO.getDoctorId());
			List<InterrogationPackageEntity> resultList = null;
			resultList = iInterrogationPackageService.getInterrogationPackageList(packageEntity);
			if (resultList != null && resultList.size() > 0) {
				paramDTO.setErrFlg(true);
				resultClientDTO.setReturnCode("-2057");
			} else {
				// 视频
				if (paramDTO.getInqueryTotal() != null) {
					paramEntity.setCount(1);
					//DecimalFormat format = new DecimalFormat("#.00");
					if(paramDTO.getInqueryTotal()!=null)
					paramEntity.setTotal(Double.valueOf(paramDTO.getInqueryTotal()));
					paramEntity.setType("0");
					paramEntity.setDoctorId(paramDTO.getDoctorId());
					iInterrogationPackageService.addInterrogationPackage(paramEntity);
				}
				// 电话
				if (paramDTO.getTelTotal() != null) {
					paramEntity1.setTotal(Double.valueOf(paramDTO.getTelTotal()));
					paramEntity1.setTelCount(1);
					paramEntity1.setType("1");
					paramEntity1.setDoctorId(paramDTO.getDoctorId());
					iInterrogationPackageService.addInterrogationPackage(paramEntity1);
				}
				// 私人医生
				if (paramDTO.getPrivateTotal() != null) {
					paramEntity2.setTotal(Double.valueOf(paramDTO.getPrivateTotal()));
					paramEntity2.setTelCount(paramDTO.getTelNum());
					paramEntity2.setCount(paramDTO.getInqueryNum());
					paramEntity2.setEffectTime(paramDTO.getEffectTime());
					paramEntity2.setType("2");
					paramEntity2.setDoctorId(paramDTO.getDoctorId());
					iInterrogationPackageService.addInterrogationPackage(paramEntity2);
				}
			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-2054");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultClientDTO.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, resultClientDTO);

		return null;
	}

	/**
	 * 获取电话问诊的医生列表
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
	public ActionForward doGetDoctorPackage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		InterrogationPackageDTO paramDTO = (InterrogationPackageDTO) form;
		// 参数Entity
		InterrogationPackageEntity paramEntity = new InterrogationPackageEntity();

		// 结果ClientDTO
		InterrogationListDTO resultClientDTO = new InterrogationListDTO();
		List<InterrogationClientDTO> interrogationList = null;
		// List<SymptomClientDTO> SymptomClientDTOList=null;
		// 参数DTO->参数Entity
		paramEntity.setDoctorId(paramDTO.getDoctorId());
		// paramEntity.setDoctorId(1);
		// paramEntity.setPatientId(1);
		// 结果Entity
		List<InterrogationPackageEntity> resultEntity = null;
		// 结果DTO
		// AppointInquiryDTO resultDTO=new AppointInquiryDTO();
		// DB查询
		try {

			resultEntity = iInterrogationPackageService.getInterrogationPackageList(paramEntity);

			if (resultEntity != null && resultEntity.size() > 0) {
				interrogationList = new ArrayList<InterrogationClientDTO>();
				DecimalFormat format = new DecimalFormat("#.00");
				for (InterrogationPackageEntity param : resultEntity) {
					
					InterrogationClientDTO paramClientDTO = new InterrogationClientDTO();
					paramClientDTO.setTotal(format.format(param.getTotal()));
					paramClientDTO.setTelCount(param.getTelCount());
					paramClientDTO.setEffectTime(param.getEffectTime());
					paramClientDTO.setCount(param.getCount());
					paramClientDTO.setType(param.getType());
					interrogationList.add(paramClientDTO);
				}
				resultClientDTO.setInterrogationClientDTO(interrogationList);
			}
			//
			else {
				paramDTO.setErrFlg(true);
				resultClientDTO.setReturnCode("-2053");
			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-2060");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultClientDTO.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, resultClientDTO);
		return null;
	}
}
