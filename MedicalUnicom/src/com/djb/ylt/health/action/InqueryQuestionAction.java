/**
 * Project Name:MedicalUnicomAPP
 * File Name:PatientAction.java
 * Package Name:com.djb.ylt.user.action
 * Copyright © 大连必捷必信息技术有限公司  All Rights Reserved.
*/

package com.djb.ylt.health.action;

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
import com.djb.ylt.health.dto.InqueryQuestionDTO;
import com.djb.ylt.health.dtoclient.InqueryAnswerClientDTO;
import com.djb.ylt.health.dtoclient.InqueryQuestionClientDTO;
import com.djb.ylt.health.dtoclient.InqueryQuestionList;
import com.djb.ylt.health.dtoclient.InqueryResultClientDTO;
import com.djb.ylt.health.entity.InqueryAnswerEntity;
import com.djb.ylt.health.entity.InqueryQuestionEntity;
import com.djb.ylt.health.entity.InqueryResultEntity;
import com.djb.ylt.health.service.IInqueryAnswerService;
import com.djb.ylt.health.service.IInqueryQuestionService;

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

@Controller("/InqueryQuestion")
public class InqueryQuestionAction extends BaseAction {

	@Autowired
	@Qualifier("iInqueryQuestionService")
	private IInqueryQuestionService iInqueryQuestionService;

	@Autowired
	@Qualifier("iInqueryAnswerService")
	private IInqueryAnswerService iInqueryAnswerService;

	public InqueryQuestionAction() {
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
	public ActionForward doGetList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		InqueryQuestionDTO paramDTO = (InqueryQuestionDTO) form;
		// 参数Entity
		InqueryQuestionEntity paramEntity = new InqueryQuestionEntity();

		// 结果ClientDTO
		InqueryQuestionList resultClientDTO = new InqueryQuestionList();
		// ClientParamDTO
		List<InqueryQuestionClientDTO> inqueryQuestionClient = null;
		List<InqueryResultClientDTO> inqueryResultClient = null;

		// 参数DTO->参数Entity
		CommonUtil.reflectClass(paramDTO, paramEntity);
		paramEntity.setDepId(1);
		paramEntity.setPatientId(1);
		// 结果Entity
		List<InqueryQuestionEntity> resultEntity = null;

		// DB查询
		try {
			// DB 查询 获取问题和选项
			resultEntity = iInqueryQuestionService.getInqueryQuestionList(paramEntity);

			if (resultEntity != null && resultEntity.size() > 0) {
				inqueryQuestionClient = new ArrayList<InqueryQuestionClientDTO>();
				// 问题存在
				for (InqueryQuestionEntity param : resultEntity) {
					// 给客户端结果赋值
					InqueryQuestionClientDTO paramClientDTO = new InqueryQuestionClientDTO();
					CommonUtil.reflectClass(param, paramClientDTO);
					inqueryResultClient = new ArrayList<InqueryResultClientDTO>();
					// 获取问题
					// 填空题获取答案
					if ("2".equals(param.getIqType())) {
						InqueryAnswerEntity answerParam = new InqueryAnswerEntity();
						answerParam.setPatientId(paramDTO.getPatientId());
						answerParam.setIqId(paramDTO.getIqId());
						List<InqueryAnswerEntity> inqueryAnswerList = null;
						inqueryAnswerList = iInqueryAnswerService.getInqueryAnswerList(answerParam);
						if (inqueryAnswerList != null && inqueryAnswerList.size() > 0) {
							// for (InqueryAnswerEntity answer :
							// inqueryAnswerList) {
							// InqueryResultClientDTO inqueryResultClientDTO =
							// new InqueryResultClientDTO();
							// inqueryResultClientDTO.setIaId(answer.getIaId());
							// inqueryResultClientDTO.setIaContent(answer.getIaContent());
							// inqueryResultClientDTO.setIaMemo(answer.getIaMemo());
							// inqueryResultClientDTO.setRecordId(answer.getRecordId());
							// inqueryResultClient.add(inqueryResultClientDTO);
							// }

						}
						// 选择题
					} else if (param.getInqueryResultEntitys() != null && param.getInqueryResultEntitys().size() > 0) {
						for (InqueryResultEntity resultParam : param.getInqueryResultEntitys()) {
							if (resultParam.getIrId() != null) {
								InqueryResultClientDTO inqueryResultClientDTO = new InqueryResultClientDTO();
								CommonUtil.reflectClass(resultParam, inqueryResultClientDTO);
								InqueryAnswerEntity answerParam = new InqueryAnswerEntity();
								answerParam.setIrId(resultParam.getIrId());
								InqueryAnswerEntity answerResult = new InqueryAnswerEntity();
								answerResult = iInqueryAnswerService.getObject(answerParam);
								if (answerResult != null) {
									// inqueryResultClientDTO.setIaContent(answerResult.getIaContent());
									// inqueryResultClientDTO.setIaMemo(answerResult.getIaMemo());
									// inqueryResultClientDTO.setAnswerFlag("1");

									inqueryResultClient.add(inqueryResultClientDTO);

								}
							}
						}
					}
					paramClientDTO.setInqueryResultDTO(inqueryResultClient);
					// 添加结果
					inqueryQuestionClient.add(paramClientDTO);
				}
				resultClientDTO.setInqueryQuestionList(inqueryQuestionClient);
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-1100");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultClientDTO.setReturnCode("0");

		}
		// 返回结果
		toJson(response, resultClientDTO);

		return null;

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
	public ActionForward doGetInqueryQuestionList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		InqueryQuestionDTO paramDTO = (InqueryQuestionDTO) form;
		// 参数Entity
		InqueryQuestionEntity paramEntity = new InqueryQuestionEntity();

		// 结果ClientDTO
		InqueryQuestionList resultClientDTO = new InqueryQuestionList();
		// ClientParamDTO
		List<InqueryQuestionClientDTO> inqueryQuestionClient = null;
	//	List<InqueryResultClientDTO> inqueryResultClient = null;
		List<InqueryAnswerClientDTO> inqueryAnswerClient = null;
		// 参数DTO->参数Entity
		CommonUtil.reflectClass(paramDTO, paramEntity);
		//paramEntity.setDepId(1);
		//paramEntity.setPatientId(1);
		// 结果Entity
		List<InqueryQuestionEntity> resultEntity = null;
		 //List<InqueryAnswerEntity> inqueryAnswerList = null;

		// DB查询
		try {
			// DB 查询 获取问题和选项
			resultEntity = iInqueryQuestionService.getInqueryQuestionList(paramEntity);

			if (resultEntity != null && resultEntity.size() > 0) {
				inqueryQuestionClient = new ArrayList<InqueryQuestionClientDTO>();
				// 问题赋值
				for (InqueryQuestionEntity param : resultEntity) {
					InqueryQuestionClientDTO paramClientDTO = new InqueryQuestionClientDTO();
					paramClientDTO.setIqId(param.getIqId());
					paramClientDTO.setIqName(param.getIqName());
					paramClientDTO.setIqType(param.getIqType());
					paramClientDTO.setIqMemo(param.getIqMemo());
					// 给客户端结果赋值
//					if (param.getInqueryResultEntitys() != null && param.getInqueryResultEntitys().size() > 0) {
//						inqueryResultClient = new ArrayList<InqueryResultClientDTO>();
//						for (InqueryResultEntity resultParam : param.getInqueryResultEntitys()) {
//							if (resultParam.getIrId() != null) {
//								InqueryResultClientDTO inqueryResultClientDTO = new InqueryResultClientDTO();
//								inqueryResultClientDTO.setIrId(resultParam.getIrId());
//								inqueryResultClientDTO.setIrName(resultParam.getIrName());
//								inqueryResultClientDTO.setIrType(resultParam.getIrType());
//								inqueryResultClientDTO.setIrMemo(resultParam.getIrMemo());
//								inqueryResultClient.add(inqueryResultClientDTO);
//							}
//						}
//						if (inqueryResultClient != null && inqueryResultClient.size() > 0) {
//							paramClientDTO.setInqueryResultDTO(inqueryResultClient);
//						}
//					}
					// 获取回答的内容
					if (param.getInqueryAnswerEntitys() != null && param.getInqueryAnswerEntitys().size() > 0) {
						inqueryAnswerClient = new ArrayList<InqueryAnswerClientDTO>();
						for (InqueryAnswerEntity answer : param.getInqueryAnswerEntitys()) {
							if(answer.getIaId()!=null){
							InqueryAnswerClientDTO inqueryAnswerClientDTO = new InqueryAnswerClientDTO();
							//inqueryAnswerClientDTO.setIrId(answer.getIrId());
							inqueryAnswerClientDTO.setIaContent(answer.getIaContent());
							inqueryAnswerClientDTO.setIaMemo(answer.getIaMemo());
							// 获取回答的选项的名字
//							for (InqueryResultEntity resultParam : param.getInqueryResultEntitys()) {
//								if (answer.getIrId() == resultParam.getIrId()) {
//									inqueryAnswerClientDTO.setIrName(resultParam.getIrName());
//									break;
//								}
//							}
							inqueryAnswerClient.add(inqueryAnswerClientDTO);
						}}
						if (inqueryAnswerClient != null && inqueryAnswerClient.size() > 0) {
							paramClientDTO.setInqueryAnswerDTO(inqueryAnswerClient);
						}
					}

					// 添加结果
					inqueryQuestionClient.add(paramClientDTO);
				}
				resultClientDTO.setInqueryQuestionList(inqueryQuestionClient);
			}
			InqueryAnswerEntity answerParam = new InqueryAnswerEntity();
			answerParam.setPatientId(paramDTO.getPatientId());
			answerParam.setDepId(paramDTO.getDepId());
			// answerParam.setIaFlag("1");
			List<InqueryAnswerEntity> inqueryAnswerList = null;
			inqueryAnswerList = iInqueryAnswerService.getInqueryAnswerList(answerParam);
			StringBuffer image = new StringBuffer();
			StringBuffer memo = new StringBuffer();
			if (inqueryAnswerList != null && inqueryAnswerList.size() > 0) {

				for (InqueryAnswerEntity answer : inqueryAnswerList) {
					if ("1".equals(answer.getIaFlag())) {
						image.append(answer.getIaContent() );
					} else if ("2".equals(answer.getIaFlag())) {
						memo.append(answer.getIaContent());
					}
				}
				if (image.length() > 0) {
					String strTem = image.toString();
					resultClientDTO.setImages(strTem);
				}
				if (memo != null && !"".equals(memo)) {
					String strMemo = memo.toString();
					resultClientDTO.setMemo(strMemo);
				}
			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-1100");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultClientDTO.setReturnCode("0");

		}
		// 返回结果
		toJson(response, resultClientDTO);
		return null;

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
	public ActionForward doGetInqueryQuestionListForOld(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		InqueryQuestionDTO paramDTO = (InqueryQuestionDTO) form;
		// 参数Entity
		InqueryQuestionEntity paramEntity = new InqueryQuestionEntity();

		// 结果ClientDTO
		InqueryQuestionList resultClientDTO = new InqueryQuestionList();
		// ClientParamDTO
		List<InqueryQuestionClientDTO> inqueryQuestionClient = null;
		List<InqueryResultClientDTO> inqueryResultClient = null;
		List<InqueryAnswerClientDTO> inqueryAnswerClient = null;
		// 参数DTO->参数Entity
		CommonUtil.reflectClass(paramDTO, paramEntity);
		//paramEntity.setDepId(1);
		//paramEntity.setPatientId(1);
		// 结果Entity
		List<InqueryQuestionEntity> resultEntity = null;
		 //List<InqueryAnswerEntity> inqueryAnswerList = null;

		// DB查询
		try {
			// DB 查询 获取问题和选项
			resultEntity = iInqueryQuestionService.getInqueryQuestionList(paramEntity);

			if (resultEntity != null && resultEntity.size() > 0) {
				inqueryQuestionClient = new ArrayList<InqueryQuestionClientDTO>();
				// 问题赋值
				for (InqueryQuestionEntity param : resultEntity) {
					InqueryQuestionClientDTO paramClientDTO = new InqueryQuestionClientDTO();
					paramClientDTO.setIqId(param.getIqId());
					paramClientDTO.setIqName(param.getIqName());
					paramClientDTO.setIqType(param.getIqType());
					paramClientDTO.setIqMemo(param.getIqMemo());
					// 给客户端结果赋值
					if (param.getInqueryResultEntitys() != null && param.getInqueryResultEntitys().size() > 0) {
						inqueryResultClient = new ArrayList<InqueryResultClientDTO>();
						for (InqueryResultEntity resultParam : param.getInqueryResultEntitys()) {
							if (resultParam.getIrId() != null) {
								InqueryResultClientDTO inqueryResultClientDTO = new InqueryResultClientDTO();
								inqueryResultClientDTO.setIrId(resultParam.getIrId());
								inqueryResultClientDTO.setIrName(resultParam.getIrName());
								inqueryResultClientDTO.setIrType(resultParam.getIrType());
								inqueryResultClientDTO.setIrMemo(resultParam.getIrMemo());
								inqueryResultClient.add(inqueryResultClientDTO);
							}
						}
						if (inqueryResultClient != null && inqueryResultClient.size() > 0) {
							paramClientDTO.setInqueryResultDTO(inqueryResultClient);
						}
					}
					// 获取回答的内容
					if (param.getInqueryAnswerEntitys() != null && param.getInqueryAnswerEntitys().size() > 0) {
						inqueryAnswerClient = new ArrayList<InqueryAnswerClientDTO>();
						for (InqueryAnswerEntity answer : param.getInqueryAnswerEntitys()) {
							if(answer.getIaId()!=null){
							InqueryAnswerClientDTO inqueryAnswerClientDTO = new InqueryAnswerClientDTO();
							inqueryAnswerClientDTO.setIrId(answer.getIrId());
							inqueryAnswerClientDTO.setIaContent(answer.getIaContent());
							inqueryAnswerClientDTO.setIaMemo(answer.getIaMemo());
							// 获取回答的选项的名字
							for (InqueryResultEntity resultParam : param.getInqueryResultEntitys()) {
								if (answer.getIrId() == resultParam.getIrId()) {
									inqueryAnswerClientDTO.setIrName(resultParam.getIrName());
									break;
								}
							}
							inqueryAnswerClient.add(inqueryAnswerClientDTO);
						}}
						if (inqueryAnswerClient != null && inqueryAnswerClient.size() > 0) {
							paramClientDTO.setInqueryAnswerDTO(inqueryAnswerClient);
						}
					}

					// 添加结果
					inqueryQuestionClient.add(paramClientDTO);
				}
				resultClientDTO.setInqueryQuestionList(inqueryQuestionClient);
			}
			InqueryAnswerEntity answerParam = new InqueryAnswerEntity();
			answerParam.setPatientId(paramDTO.getPatientId());
			answerParam.setDepId(paramDTO.getDepId());
			// answerParam.setIaFlag("1");
			List<InqueryAnswerEntity> inqueryAnswerList = null;
			inqueryAnswerList = iInqueryAnswerService.getInqueryAnswerList(answerParam);
			StringBuffer image = new StringBuffer();
			StringBuffer memo = new StringBuffer();
			if (inqueryAnswerList != null && inqueryAnswerList.size() > 0) {

				for (InqueryAnswerEntity answer : inqueryAnswerList) {
					if ("1".equals(answer.getIaFlag())) {
						image.append(answer.getIaContent() );
					} else if ("2".equals(answer.getIaFlag())) {
						memo.append(answer.getIaContent());
					}
				}
				if (image.length() > 0) {
					String strTem = image.toString();
					resultClientDTO.setImages(strTem);
				}
				if (memo != null && !"".equals(memo)) {
					String strMemo = memo.toString();
					resultClientDTO.setMemo(strMemo);
				}
			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-1100");

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