/**
 * Project Name:MedicalUnicomAPP
 * File Name:PatientAction.java
 * Package Name:com.djb.ylt.user.action
 * Copyright © 大连必捷必信息技术有限公司  All Rights Reserved.
*/

package com.djb.ylt.user.action;

import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import com.djb.ylt.common.util.CommonUtil;
import com.djb.ylt.common.util.Constants;
import com.djb.ylt.common.util.ResourceLocator;
import com.djb.ylt.common.util.UploadUtil;
import com.djb.ylt.framework.action.BaseAction;
import com.djb.ylt.framework.dto.BaseClientDTO;
import com.djb.ylt.user.dto.PatientDTO;
import com.djb.ylt.user.dtoclient.PatientClientDTO;
import com.djb.ylt.user.entity.PatientEntity;
import com.djb.ylt.user.service.IPatientService;

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

@Controller("/Patient")
public class PatientAction extends BaseAction {

	@Autowired
	@Qualifier("iPatientService")
	private IPatientService iPatientService;

	public PatientAction() {
		super();
	}

	/**
	 * 更新医生简介
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
	public ActionForward doUpdatePatientInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		PatientDTO paramDTO = (PatientDTO) form;

		// 参数Entity
		PatientEntity paramEntity = new PatientEntity();
		// 结果Entity
		// PatientEntity resultEntity = new PatientEntity();

		// 结果ClientDTO
		BaseClientDTO resultsClientDTO = new BaseClientDTO();
		// PatientClientDTO resultsClientDTO=new PatientClientDTO();
		// 参数DTO->参数Entity

		CommonUtil.reflectClass(paramDTO, paramEntity);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (paramDTO.getBirth() != null) {
			paramEntity.setBirth(sdf.parse(paramDTO.getBirth()));

		}

		// DB查询
		try {
			// 获取医生的信息 看是否
			if (paramDTO.getPatientPic() != null) {
				PatientEntity picParam = new PatientEntity();
				picParam.setPatientId(paramDTO.getPatientId());
				PatientEntity picResultParam = new PatientEntity();
				picResultParam = iPatientService.getObject(picParam);
				// 删除原来的图片
				if (picResultParam != null && picResultParam.getPatientPic() != null) {
					String[] args = StringUtils.split(picResultParam.getPatientPic(), "/");
					StringBuffer strBuffer = new StringBuffer();
					if (args.length > 1) {
						strBuffer.append(args[args.length - 2]);
						strBuffer.append("/");
						strBuffer.append(args[args.length - 1]);

					}
					if (strBuffer != null) {
						String fileName = strBuffer.toString();
						UploadUtil.doDeleteFile(request, response, fileName);
					}

				}
				String userId = paramDTO.getPatientId().toString() + "p";
				paramDTO.setPatientPic(paramDTO.getPatientPic().replace("<", ""));
				paramDTO.setPatientPic(paramDTO.getPatientPic().replace(">", ""));
				String filePath = UploadUtil.doUpload(request, response, paramDTO.getPatientPic(), userId);
				paramEntity
						.setPatientPic(ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS) + filePath);
			}

			iPatientService.updatePatient(paramEntity);

			// 给客户端结果赋值

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultsClientDTO.setReturnCode("-3050");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultsClientDTO.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, resultsClientDTO);

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
	public ActionForward doGetPatientInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		PatientDTO paramDTO = (PatientDTO) form;

		// 参数Entity
		PatientEntity paramEntity = new PatientEntity();
		// 结果Entity
		PatientEntity resultEntity = new PatientEntity();

		// 结果ClientDTO
		PatientClientDTO resultsClientDTO = new PatientClientDTO();
		// PatientClientDTO resultsClientDTO=new PatientClientDTO();
		// 参数DTO->参数Entity

		CommonUtil.reflectClass(paramDTO, paramEntity);

		// DB查询
		try {
			// 获取医生的信息 看是否
			resultEntity = iPatientService.getObject(paramEntity);
			if (resultEntity != null) {
				// resultsClientDTO.setPatientId(resultEntity.getPatientId());
				resultsClientDTO.setName(resultEntity.getName());
				resultsClientDTO.setPatientPic(resultEntity.getPatientPic());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if (resultEntity.getBirth() != null) {
					resultsClientDTO.setBirth(sdf.format(resultEntity.getBirth()));
				}
				resultsClientDTO.setSex(resultEntity.getSex());
				resultsClientDTO.setAllergyHistory(resultEntity.getAllergyHistory());
				resultsClientDTO.setIllnessHistory(resultEntity.getIllnessHistory());
				resultsClientDTO.setMedicalEatenHistory(resultEntity.getMedicalEatenHistory());
			}
			// 给客户端结果赋值

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultsClientDTO.setReturnCode("-3050");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultsClientDTO.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, resultsClientDTO);

		return null;
	}

}
