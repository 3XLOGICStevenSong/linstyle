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

import com.djb.ylt.user.dto.GradePriceDTO;

import com.djb.ylt.user.dtoclient.GradePriceClientDTO;
import com.djb.ylt.user.dtoclient.GradePriceListDTO;
import com.djb.ylt.user.entity.GradePriceEntity;
import com.djb.ylt.user.service.IGradePriceService;

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

@Controller("/GradePrice")
public class GradePriceAction extends BaseAction {

	@Autowired
	@Qualifier("iGradePriceService")
	private IGradePriceService iGradePriceService;

	public GradePriceAction() {
		super();
	}

	/**
	 * 获取医生定价标准
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
	public ActionForward doGetGradePriceList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		GradePriceDTO paramDTO = (GradePriceDTO) form;
		// 结果ClientDTO
		GradePriceListDTO resultClientDTO = new GradePriceListDTO();
		// 结果DTO
		List<GradePriceClientDTO> gradePriceList = null;
		// 结果Entity
		List<GradePriceEntity> resultEntity = null;

		// DB查询
		try {
			resultEntity = iGradePriceService.getGradePriceList();

			if (resultEntity != null && resultEntity.size() > 0) {
				gradePriceList = new ArrayList<GradePriceClientDTO>();
				for (GradePriceEntity param : resultEntity) {

					// DecimalFormat format = new DecimalFormat("#.00");
					GradePriceClientDTO paramClientDTO = new GradePriceClientDTO();
					//CommonUtil.reflectClass(param, paramClientDTO);
					paramClientDTO.setPositional(param.getPositional());
					paramClientDTO.setGrade(param.getGrade());
					if (param.getDayTotal() != null) {
						paramClientDTO.setDayTotal(param.getDayTotal().toString());
					}
					if (param.getNightTotal() != null) {
						paramClientDTO.setNightTotal(param.getNightTotal().toString());
					}
					paramClientDTO.setEarlyTime(param.getEarlyTime());
					//以下这些需要注掉
					 if (param.getPaPrice() != null) {
					 paramClientDTO.setPaPrice(param.getPaPrice().toString());
					 }
					 if (param.getPiPrice() != null) {
					 paramClientDTO.setPiPrice(param.getPiPrice().toString());
					 }
					 if (param.getPtPrice() != null) {
					 paramClientDTO.setPtPrice(param.getPtPrice().toString());
					 }
					 if (param.getPvPrice() != null) {
					 paramClientDTO.setPvPrice(param.getPvPrice().toString());
					 }
					 if (param.getTaPrice() != null) {
					 paramClientDTO.setTaPrice(param.getTaPrice().toString());
					 }
					 if (param.getTiPrice() != null) {
					 paramClientDTO.setTiPrice(param.getTiPrice().toString());
					 }
					 if (param.getTaPrice() != null) {
					 paramClientDTO.setTaPrice(param.getTaPrice().toString());
					 }
					 if (param.getVaPrice() != null) {
					 paramClientDTO.setVaPrice(param.getVaPrice().toString());
					 }
					//之前的注掉
					 if (param.getPhotoPrice() != null) {
						 paramClientDTO.setPhotoPrice(param.getPhotoPrice().toString());
						 }
					 paramClientDTO.setFreeTotal(param.getFreeTotal());
					gradePriceList.add(paramClientDTO);
				}
				resultClientDTO.setGradePriceList(gradePriceList);

			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-3080");

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
	 * 获取医生定价标准
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
	public ActionForward doGetEmergencyPrice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		GradePriceDTO paramDTO = (GradePriceDTO) form;
		// 结果ClientDTO
		GradePriceClientDTO resultClientDTO = new GradePriceClientDTO();

		// 结果Entity
		GradePriceEntity resultEntity = null;
		GradePriceEntity paramEntity = new GradePriceEntity();
		paramEntity.setGpType("1");
		// DB查询
		try {
			resultEntity = iGradePriceService.getObject(paramEntity);
			// DecimalFormat format = new DecimalFormat("#.00");
			resultClientDTO.setPrice(resultEntity.getGpPrice().toString());

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-1900");

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
