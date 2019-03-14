/**
 * Project Name:MedicalUnicomAPP
 * File Name:PatientAction.java
 * Package Name:com.djb.ylt.user.action
 * Copyright © 大连必捷必信息技术有限公司  All Rights Reserved.
*/

package com.djb.ylt.user.action;

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
import com.djb.ylt.framework.dto.BaseClientDTO;
import com.djb.ylt.user.dto.DoctorCommentDTO;
import com.djb.ylt.user.dtoclient.CommentClientDTO;
import com.djb.ylt.user.dtoclient.CommentListClientDTO;
import com.djb.ylt.user.entity.DoctorCommentEntity;
import com.djb.ylt.user.service.IDoctorCommentService;
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

@Controller("/DoctorComment")
public class DoctorCommentAction extends BaseAction {

	@Autowired
	@Qualifier("iDoctorCommentService")
	private IDoctorCommentService iDoctorCommentService;
	@Autowired
	@Qualifier("iPatientService")
	private IPatientService iPatientService;

	public DoctorCommentAction() {
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
	public ActionForward doInsertDoctorComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		DoctorCommentDTO paramDTO = (DoctorCommentDTO) form;

		//  画面项目check

		// 参数Entity
		DoctorCommentEntity paramEntity = new DoctorCommentEntity();

		// 结果ClientDTO
		BaseClientDTO BaseClientDTO = new BaseClientDTO();
		//  paramDTO.setGrade((float)paramDTO.getGrade());
		// 参数DTO->参数Entity
		// TEST data
		// paramDTO.setPatientId(1);
		// paramDTO.setDoctorId(1);
		// paramDTO.setGrade((float)5.0);
		// paramDTO.setContent("服务态度好不错哦");
		// paramDTO.setRecordsId(2);

		CommonUtil.reflectClass(paramDTO, paramEntity);
		paramEntity.setGrade((Float) paramDTO.getGrade());
		paramEntity.setDeleteFlg("0");
		// DB查询
		try {
			iDoctorCommentService.addDoctorComment(paramEntity);

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			BaseClientDTO.setReturnCode("-1050");

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
	 * 获取医生评价
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
	public ActionForward doGetDoctorCommentList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		DoctorCommentDTO paramDTO = (DoctorCommentDTO) form;

		

		// 参数Entity
		DoctorCommentEntity paramEntity = new DoctorCommentEntity();

		// 结果ClientDTO
		CommentListClientDTO resultClientDTO = new CommentListClientDTO();
		//  paramDTO.setGrade((float)paramDTO.getGrade());
		// 参数DTO->参数Entity
		// test
		// 
		// paramDTO.setDoctorId(1);
		List<DoctorCommentEntity> resultEntity = null;
		CommonUtil.reflectClass(paramDTO, paramEntity);
		// paramDTO.setStartDate("2016-08-05");
		// paramDTO.setStartTime("21:20");
		// SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		// SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
		// paramEntity.setStartDate(paramDTO.getStartDate());
		// List<AppointTimeClientDTO> appointTimeClientDTO=null;

		// paramEntity.setGrade((Float) paramDTO.getGrade());
		// paramEntity.setDeleteFlg("0");
		// DB查询
		try {
			//TODO SQL
			resultEntity = iDoctorCommentService.getDoctorCommentList(paramEntity);
			// 处理时间格式

			// SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-DD");
			if (resultEntity != null && resultEntity.size() > 0) {
				List<CommentClientDTO> commentClientDTO = new ArrayList<CommentClientDTO>();
				for (DoctorCommentEntity param : resultEntity) {
					CommentClientDTO paramClientDTO = new CommentClientDTO();
					if (param.getCreateTime() != null) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						paramClientDTO.setCreateTime(sdf.format(param.getCreateTime()));
					}
					paramClientDTO.setContent(param.getContent());
					paramClientDTO.setGrade(param.getGrade().toString());
					//PatientEntity patientEntity = new PatientEntity();
					//PatientEntity patientResult = new PatientEntity();
					//patientEntity.setPatientId(param.getPatientId());
					//patientResult = iPatientService.getObject(patientEntity);
					if (param.getPatientEntity() != null && param.getPatientEntity().getName()!=null) {
						String temp = param.getPatientEntity().getName();
						//StringBuffer sb=new StringBuffer();
						if ( param.getPatientEntity().getName().length() == 2||param.getPatientEntity().getName().length() == 1) {
							String c = temp.substring(0,1);
							temp = temp.replaceFirst(c , "*");
						} else {
							for (int i = 0; i <temp.length() - 1; i++) {
								String c = temp.substring(i,i+1);
								temp = temp.replaceFirst(c, "*");
							}
						}
						//System.out.println(patientResult.getName().length());
						paramClientDTO.setName(temp);
					}
					commentClientDTO.add(paramClientDTO);
				}
				resultClientDTO.setCommentList(commentClientDTO);
			}

		} catch (Exception e) {
			// log.info(e);
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-1062");
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
