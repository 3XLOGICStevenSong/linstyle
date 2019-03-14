/**
 * Project Name:MedicalUnicomAPP
 * File Name:PatientAction.java
 * Package Name:com.djb.ylt.user.action
 * Copyright © 大连必捷必信息技术有限公司  All Rights Reserved.
*/

package com.djb.ylt.health.action;

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
import com.djb.ylt.health.dto.DepartmentDTO;
import com.djb.ylt.health.dtoclient.DepartmentClientDTO;
import com.djb.ylt.health.dtoclient.DepartmentListClientDTO;
import com.djb.ylt.health.dtoclient.SymptomClientDTO;
import com.djb.ylt.health.entity.DepartmentEntity;
import com.djb.ylt.health.entity.SymptomEntity;
import com.djb.ylt.health.service.IDepartmentService;

import com.djb.ylt.user.service.IAppointInquiryService;
import com.djb.ylt.user.service.IDoctorCommentService;

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

@Controller("/Department")
public class DepartmentAction extends BaseAction {

	@Autowired
	@Qualifier("iDepartmentService")
	private IDepartmentService iDepartmentService;

	@Autowired
	@Qualifier("iAppointInquiryService")
	private IAppointInquiryService iAppointInquiryService;

	@Autowired
	@Qualifier("iDoctorCommentService")
	private IDoctorCommentService iDoctorCommentService;

	public DepartmentAction() {
		super();
	}

	/**
	 * 获取科室症状列表
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
	public ActionForward doGetDepartmentSymptom(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		DepartmentDTO paramDTO = (DepartmentDTO) form;
		// 参数Entity
		DepartmentEntity paramEntity = new DepartmentEntity();

		// 结果ClientDTO
		DepartmentListClientDTO resultClientDTO = new DepartmentListClientDTO();
		// ClientParamDTO
		List<DepartmentClientDTO> departmentClientList = null;
		List<SymptomClientDTO> symptomClientDTOList = null;
		// 参数DTO->参数Entity
		CommonUtil.reflectClass(paramDTO, paramEntity);

		// 结果Entity
		List<DepartmentEntity> resultEntity = null;

		// DB查询
		try {
			// DB 查询
			resultEntity = iDepartmentService.getDepartmentList(paramEntity);

			if (resultEntity != null && resultEntity.size() > 0) {
				departmentClientList = new ArrayList<DepartmentClientDTO>();
				// 获取主机IP
				String hostIp = ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS);
				// 对结果的处理
				for (DepartmentEntity param : resultEntity) {
					// 给客户端结果赋值
					DepartmentClientDTO paramClientDTO = new DepartmentClientDTO();
					paramClientDTO.setCellPic(hostIp + "image_dep/" + param.getCellPic());
					paramClientDTO.setIconPic(hostIp + "image_dep/" + param.getIconPic());
					paramClientDTO.setDepName(param.getDepName());
					paramClientDTO.setDepId(param.getDepId());
					paramClientDTO.setMemo(param.getMemo());
					// 对症状结果处理
					if (param.getSymptomEntitys() != null && param.getSymptomEntitys().size() > 0) {
						symptomClientDTOList = new ArrayList<SymptomClientDTO>();
						for (SymptomEntity symParam : param.getSymptomEntitys()) {
							SymptomClientDTO symptomClientDTO = new SymptomClientDTO();
							symptomClientDTO.setSymptomId(symParam.getSymptomId());
							symptomClientDTO.setSymptomName(symParam.getSymptomName());
							symptomClientDTOList.add(symptomClientDTO);
						}
						paramClientDTO.setSymptomList(symptomClientDTOList);
					}
					departmentClientList.add(paramClientDTO);
				}
			//	resultClientDTO.setDepartmentList(departmentClientList);

			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-1000");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultClientDTO.setReturnCode("0");
		//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//resultClientDTO.setSysTime(sdf.format(new Date()));
		}
		// 返回结果
		toJson(response, resultClientDTO);

		return null;
	}
}
