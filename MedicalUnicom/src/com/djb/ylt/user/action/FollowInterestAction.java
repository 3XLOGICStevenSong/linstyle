/**
 * Project Name:MedicalUnicomAPP
 * File Name:PatientAction.java
 * Package Name:com.djb.ylt.user.action
 * Copyright © 大连必捷必信息技术有限公司  All Rights Reserved.
*/

package com.djb.ylt.user.action;

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
import com.djb.ylt.framework.dto.BaseClientDTO;
import com.djb.ylt.user.dto.FollowInterestDTO;

import com.djb.ylt.user.dtoclient.InqueryViewClientDTO;
import com.djb.ylt.user.dtoclient.InqueryViewListDTO;
import com.djb.ylt.user.entity.FollowInterestEntity;

import com.djb.ylt.user.service.IFollowInterestService;

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

@Controller("/FollowInterest")
public class FollowInterestAction extends BaseAction {

	@Autowired
	@Qualifier("iFollowInterestService")
	private IFollowInterestService iFollowInterestService;

	public FollowInterestAction() {
		super();
	}

	/**
	 * 添加关注
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
	public ActionForward doInsertFollow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		FollowInterestDTO paramDTO = (FollowInterestDTO) form;

		// 画面项目check

		// 参数Entity
		FollowInterestEntity paramEntity = new FollowInterestEntity();

		// 结果ClientDTO
		BaseClientDTO BaseClientDTO = new BaseClientDTO();
		// 参数赋值
		paramEntity.setPatientId(paramDTO.getPatientId());
		paramEntity.setDoctorId(paramDTO.getDoctorId());

		// DB查询
		try {
			// 参数Entity
			FollowInterestEntity InfoEntity = new FollowInterestEntity();
			InfoEntity.setPatientId(paramDTO.getPatientId());
			InfoEntity.setDoctorId(paramDTO.getDoctorId());
			FollowInterestEntity resultEntity = iFollowInterestService.getObject(InfoEntity);
			if (resultEntity == null) {
				iFollowInterestService.addFollowInterest(paramEntity);
			} else {
				paramDTO.setErrFlg(true);
				BaseClientDTO.setReturnCode("-5011");
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			BaseClientDTO.setReturnCode("-5010");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			BaseClientDTO.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, BaseClientDTO);

		return null;
	}

	/**
	 * 取消关注
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
	public ActionForward doCancelFollow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		FollowInterestDTO paramDTO = (FollowInterestDTO) form;

		// 画面项目check

		// 参数Entity
		FollowInterestEntity paramEntity = new FollowInterestEntity();

		// 结果ClientDTO
		BaseClientDTO BaseClientDTO = new BaseClientDTO();
		// 参数赋值
		paramEntity.setPatientId(paramDTO.getPatientId());
		paramEntity.setDoctorId(paramDTO.getDoctorId());

		// DB查询
		try {
			iFollowInterestService.deleteFollowInterest(paramEntity);

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			BaseClientDTO.setReturnCode("-5020");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			BaseClientDTO.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, BaseClientDTO);

		return null;
	}

	/**
	 * 获取医生简介
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
	public ActionForward doGetFollowList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		FollowInterestDTO paramDTO = (FollowInterestDTO) form;

		// 参数Entity
		FollowInterestEntity paramEntity = new FollowInterestEntity();
		// 结果Entity
		List<FollowInterestEntity> resultEntity = null;

		// 结果ClientDTO
		// BaseClientDTO resultsClientDTO = new BaseClientDTO();

		// 结果ClientDTO
		InqueryViewListDTO resultClientDTO = new InqueryViewListDTO();
		List<InqueryViewClientDTO> inqueryViewList = null;

		// CommonUtil.reflectClass(paramDTO, paramEntity);

		// paramEntity.setDoctorId(2);

		// DB查询
		try {
			// 参数赋值
			paramEntity.setPatientId(paramDTO.getPatientId());
			paramEntity.setPageNum(paramDTO.getPageNum());
			paramEntity.setPageSize(paramDTO.getPageSize());
			//paramEntity.setMethodFlg(paramDTO.getMethodFlg());
			//paramEntity.setSortType(paramDTO.getSortType());
			resultEntity = iFollowInterestService.getFollowInterestList(paramEntity);
			//
			if (resultEntity != null && resultEntity.size() > 0) {
				inqueryViewList = new ArrayList<InqueryViewClientDTO>();
				for (FollowInterestEntity param : resultEntity) {
					InqueryViewClientDTO paramClientDTO = new InqueryViewClientDTO();
					if (param.getDoctorEntity() != null) {
						paramClientDTO.setDoctorId(param.getDoctorEntity().getDoctorId());
						paramClientDTO.setName(param.getDoctorEntity().getName());
						paramClientDTO.setHeadPic(param.getDoctorEntity().getHeadPic());
						paramClientDTO.setHealDisease(param.getDoctorEntity().getHealDisease());
						paramClientDTO.setHospitalName(param.getDoctorEntity().getHospitalName());
						paramClientDTO.setPositional(param.getDoctorEntity().getPositional());
						paramClientDTO.setDepartmentName(param.getDoctorEntity().getDepartmentName());
						paramClientDTO.setDcName(param.getDoctorEntity().getDcName());
						paramClientDTO.setServiceCount(param.getDoctorEntity().getServiceCount());
						paramClientDTO.setScheduleFlag(param.getScheduleFlag());
						paramClientDTO.setServiceType(param.getDoctorEntity().getServiceType());
						if(param.getMinTotal()!=null){
							paramClientDTO.setMinTotal(param.getMinTotal().toString());	
						}
						if (param.getCommentGrade() != null) {
							paramClientDTO.setCommentGrade((float) (Math.round(param.getCommentGrade() * 10)) / 10);
						}
					}
					inqueryViewList.add(paramClientDTO);
				}

				resultClientDTO.setDoctorList(inqueryViewList);

			}

			// 给客户端结果赋值

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-5030");

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
