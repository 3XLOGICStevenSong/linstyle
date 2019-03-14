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

import com.djb.ylt.user.dto.RefundReasonDTO;

import com.djb.ylt.user.dtoclient.RefundReasonClientDTO;
import com.djb.ylt.user.dtoclient.RefundReasonList;

import com.djb.ylt.user.entity.RefundReasonEntity;

import com.djb.ylt.user.service.IRefundReasonService;

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

@Controller("/RefundReason")
public class RefundReasonAction extends BaseAction {

	@Autowired
	@Qualifier("iRefundReasonService")
	private IRefundReasonService iRefundReasonService;

	public RefundReasonAction() {
		super();
	}

	/**
	 * 获取患者信息
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
	public ActionForward doGetReasonList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		RefundReasonDTO paramDTO = (RefundReasonDTO) form;

		// 结果Entity
		List<RefundReasonEntity> resultEntity = new ArrayList<RefundReasonEntity>();

		// 结果ClientDTO
		// PatientClientDTO resultsClientDTO = new PatientClientDTO();
		// PatientClientDTO resultsClientDTO=new PatientClientDTO();
		// 参数DTO->参数Entity

		// CommonUtil.reflectClass(paramDTO, paramEntity);

		RefundReasonList resultClient = new RefundReasonList();
		List<RefundReasonClientDTO> resultClientEntity = new ArrayList<RefundReasonClientDTO>();
		// DB查询
		try {
			// 获取医生的信息 看是否
			resultEntity = iRefundReasonService.getRefundReasonList();
			if (resultEntity != null && resultEntity.size() > 0) {
				for (RefundReasonEntity param : resultEntity) {
					RefundReasonClientDTO paramClient = new RefundReasonClientDTO();
					paramClient.setReasonContent(param.getReasonContent());
					paramClient.setReasonId(param.getReasonId());
					resultClientEntity.add(paramClient);
				}
				resultClient.setRefundReasonList(resultClientEntity);

			}
			// 给客户端结果赋值

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClient.setReturnCode("-1020");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultClient.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, resultClient);

		return null;
	}

	/**
	 * 获取患者信息
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
	public ActionForward doReasonTest(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		RefundReasonDTO paramDTO = (RefundReasonDTO) form;

		// 结果Entity
		//List<RefundReasonEntity> resultEntity = new ArrayList<RefundReasonEntity>();

		RefundReasonEntity resultEntity = new RefundReasonEntity();
		// 结果ClientDTO
		// PatientClientDTO resultsClientDTO = new PatientClientDTO();
		// PatientClientDTO resultsClientDTO=new PatientClientDTO();
		// 参数DTO->参数Entity

		// CommonUtil.reflectClass(paramDTO, paramEntity);

		RefundReasonList resultClient = new RefundReasonList();
		List<RefundReasonClientDTO> resultClientEntity = new ArrayList<RefundReasonClientDTO>();
		// DB查询
		try {
			// 获取医生的信息 看是否
			resultEntity = iRefundReasonService.getObject(resultEntity);
//			if (resultEntity != null && resultEntity.size() > 0) {
//				for (RefundReasonEntity param : resultEntity) {
//					RefundReasonClientDTO paramClient = new RefundReasonClientDTO();
//					paramClient.setReasonContent(param.getReasonContent());
//					paramClient.setReasonId(param.getReasonId());
//					resultClientEntity.add(paramClient);
//				}
//				resultClient.setRefundReasonList(resultClientEntity);
//
//			}
			// 给客户端结果赋值

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClient.setReturnCode("-1020");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultClient.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, resultClient);

		return null;
	}

}
