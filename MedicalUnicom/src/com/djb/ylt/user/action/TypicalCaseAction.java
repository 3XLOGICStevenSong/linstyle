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

import com.djb.ylt.common.util.CommonUtil;
import com.djb.ylt.framework.action.BaseAction;
import com.djb.ylt.user.dto.TypicalCaseDTO;
import com.djb.ylt.user.dtoclient.TypicalCaseClientDTO;
import com.djb.ylt.user.dtoclient.TypicalCaseClientListDTO;
import com.djb.ylt.user.dtoclient.TypicalCaseInfoDTO;
import com.djb.ylt.user.entity.TypicalCaseEntity;
import com.djb.ylt.user.service.ITypicalCaseService;

/**
 * <p>
 * <strong>类名: </strong>
 * </p>
 * TypicalCaseAction <br/>
 * <p>
 * <strong>功能说明: </strong>
 * </p>
 * 这个类是封装那个哪个模块，起什么用的，需要手动填写. <br/>
 * <p>
 * <strong>创建日期: </strong><br/>
 * </p>
 * 2017年4月25日下午1:19:01 <br/>
 * 
 * @author 林行
 * @version 1.0
 * @since JDK 1.8
 */

@Controller("/TypicalCase")
public class TypicalCaseAction extends BaseAction {

	@Autowired
	@Qualifier("iTypicalCaseService")
	private ITypicalCaseService iTypicalCaseService;

	public TypicalCaseAction() {
		super();
	}

	/**
	 * 获取我的私人医生列表
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
	public ActionForward doGetTypicalCaseInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		TypicalCaseDTO inputParamDTO =  (TypicalCaseDTO) form;
		TypicalCaseInfoDTO paramDTO = new TypicalCaseInfoDTO();
		List<TypicalCaseInfoDTO> paramListDTO = new ArrayList<TypicalCaseInfoDTO>();
		
		// 结果ClientDTO
		TypicalCaseClientDTO typicalCaseClientDTO = new TypicalCaseClientDTO();
		
		List<TypicalCaseClientDTO> typicalCaseClientList = 
				new ArrayList<TypicalCaseClientDTO>();
		
		TypicalCaseClientListDTO resultClientDTO  = new TypicalCaseClientListDTO();

		// 参数DTO->参数Entity
		List<TypicalCaseEntity> resultEntity = new ArrayList<TypicalCaseEntity>();

		try {
			resultEntity = iTypicalCaseService.getTypicalCaseList();
			if (resultEntity != null && resultEntity.size() != 0) {
				List<String> listNum = new ArrayList<String>();
				int m = 0;
				for (int j = 1;j<resultEntity.size();j++) {
					if (resultEntity.get(m).getDcName().equals(resultEntity.get(j).getDcName())
							&& resultEntity.get(m).getDepName().equals(resultEntity.get(j).getDepName())) {
					} else {
						m=j;
						listNum.add(String.valueOf(m));
					}
				}
				
				for (int i = 0;i<=listNum.size();i++) {
					typicalCaseClientDTO = new TypicalCaseClientDTO();
					int x = 0;
					int y = 0;
					if (i == 0) {
						x = Integer.valueOf(0);
						if (listNum.size() == 0) {
							y = 1;
						} else {
							y = Integer.valueOf(listNum.get(i));
						}
					} else if (i == listNum.size()) {
						x = Integer.valueOf(listNum.get(i-1));
						y = resultEntity.size();
					} else {
						x = Integer.valueOf(listNum.get(i-1));
						y = Integer.valueOf(listNum.get(i));
					}
					paramListDTO = new ArrayList<TypicalCaseInfoDTO>();
					for (int j = x;j<y;j++) {
						// 科室,专业判断
						// 科室
						typicalCaseClientDTO.setDc_name(resultEntity.get(j).getDcName());
						// 专业
						typicalCaseClientDTO.setDep_name(resultEntity.get(j).getDepName());
						paramDTO = new TypicalCaseInfoDTO();
						// 典型病例ID
						paramDTO.setTcid(resultEntity.get(j).getTcId());
						// 典型病例名称
						paramDTO.setCaseName(resultEntity.get(j).getCaseName());
						// 头像
						paramDTO.setHead_pic(resultEntity.get(j).getHeadPic());
						// 姓名
						paramDTO.setDoctor_name(resultEntity.get(j).getDoctorName());
						// 地位
						paramDTO.setPositional(resultEntity.get(j).getPositional());
						
						paramListDTO.add(paramDTO);
						typicalCaseClientDTO.setTypicalCaseList(paramListDTO);
					}
					typicalCaseClientList.add(typicalCaseClientDTO);
				}
				resultClientDTO.setTypicalCaseInfoList(typicalCaseClientList);
			}
		} catch (Exception e) {
			inputParamDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-8002");
		}
		// return 处理
		if (!inputParamDTO.isErrFlg()) {
			resultClientDTO.setReturnCode("0");
		}
		// 返回结果
		toJson(response, resultClientDTO);

		return null;
	}
	
	/**
	 * 获取我的私人医生列表
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
	public ActionForward doGetTypicalCaseDetailsInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		TypicalCaseDTO paramDTO = (TypicalCaseDTO) form;
		
		TypicalCaseEntity paramEntity = new TypicalCaseEntity();
		
		// 结果ClientDTO
		TypicalCaseClientDTO resultClientDTO  = new TypicalCaseClientDTO();
		TypicalCaseEntity resultEntity = new TypicalCaseEntity();
		
		CommonUtil.reflectClass(paramDTO, paramEntity);
		paramEntity.setTcId(paramDTO.getTcId());

		try {
			// 典型病例浏览次数取得
			resultEntity = iTypicalCaseService.getObject(paramEntity);
			if (resultEntity != null && !"".equals(resultEntity)) {
				// 浏览次数
				int viewTimes = 0;
				if (resultEntity.getViewTimes() != null) {
					viewTimes = resultEntity.getViewTimes();
				}
				paramEntity = new TypicalCaseEntity();
				// 典型病例ID
				paramEntity.setTcId(resultEntity.getTcId());
				// 浏览次数+1
				paramEntity.setViewTimes(viewTimes + 1);
				iTypicalCaseService.updateTypicalCase(paramEntity);
				// 典型病例详情数据取得
				resultEntity = new TypicalCaseEntity();
				resultEntity = iTypicalCaseService.getTypicalCaseDc(paramEntity);
				
				if (resultEntity != null && !"".equals(resultEntity)) {
					// 医生ID
					resultClientDTO.setDoctorId(
							resultEntity.getDoctorEntity().getDoctorId());
					// 医生头像
					resultClientDTO.setHeadPic(
							resultEntity.getDoctorEntity().getHeadPic());
					// 医生姓名
					resultClientDTO.setDoctorName(resultEntity.getDoctorName());
					// 医生职务
					resultClientDTO.setPositional(resultEntity.getPositional());
					// 医院名称
					resultClientDTO.setHospitalName(
							resultEntity.getDoctorEntity().getHospitalName());
					// 科室类别
					resultClientDTO.setDc_name(resultEntity.getDcName());
					// 科室名称
					resultClientDTO.setDep_name(resultEntity.getDepName());
					// 病例名称
					resultClientDTO.setCaseName(resultEntity.getCaseName());
					// 病例内容
					resultClientDTO.setContent(resultEntity.getContent());
					// 医生评价
					resultClientDTO.setDoctorGrade(resultEntity.getDoctorGrade());
				}
			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-8004");
		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultClientDTO.setReturnCode("0");
		}
		// 返回结果
		toJson(response, resultClientDTO);

		return null;
	}
}
