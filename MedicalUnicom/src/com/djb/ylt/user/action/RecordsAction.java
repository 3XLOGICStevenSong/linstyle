/**
 * Project Name:MedicalUnicomAPP
 * File Name:PatientAction.java
 * Package Name:com.djb.ylt.user.action
 * Copyright © 大连必捷必信息技术有限公司  All Rights Reserved.
*/

package com.djb.ylt.user.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.djb.ylt.alipay.config.AlipayConfig;
import com.djb.ylt.alipay.dto.AlipayNotification;
import com.djb.ylt.alipay.refund.src.com.alipay.util.AlipayNotify;
import com.djb.ylt.alipay.refund.src.com.alipay.util.AlipaySubmit;
import com.djb.ylt.common.util.CommonUtil;
import com.djb.ylt.common.util.Constants;
import com.djb.ylt.common.util.GsonUtil;
import com.djb.ylt.common.util.OrderNumberUtil;
import com.djb.ylt.common.util.ResourceLocator;
import com.djb.ylt.common.util.UploadUtil;
import com.djb.ylt.framework.action.BaseAction;
import com.djb.ylt.framework.dto.BaseClientDTO;
import com.djb.ylt.health.dto.InqueryAnswerDTO;
import com.djb.ylt.health.dto.InqueryQuestionDTO;

import com.djb.ylt.health.entity.InqueryAnswerEntity;
import com.djb.ylt.health.entity.InqueryQuestionEntity;
import com.djb.ylt.health.entity.SymptomEntity;
import com.djb.ylt.health.service.IDepartmentService;
import com.djb.ylt.health.service.ISymptomService;
import com.djb.ylt.jpush.api.PushConfig;
import com.djb.ylt.user.dto.AppointInquiryDTO;
import com.djb.ylt.user.dto.RecordsDTO;
import com.djb.ylt.user.dtoclient.PatientAppointClientDTO;
import com.djb.ylt.user.dtoclient.RecordsDetailClientDTO;
import com.djb.ylt.user.dtoclient.RecordsDetailInfoDTO;
import com.djb.ylt.user.entity.AppointInquiryEntity;
import com.djb.ylt.user.entity.DoctorCommentEntity;
import com.djb.ylt.user.entity.DoctorEntity;
import com.djb.ylt.user.entity.DoctorInquiryViewEntity;
import com.djb.ylt.user.entity.DoctorScheduleEntity;
import com.djb.ylt.user.entity.PatientScheduleEntity;
import com.djb.ylt.user.entity.RecordsEntity;
import com.djb.ylt.user.service.IAppointInquiryService;
import com.djb.ylt.user.service.IDoctorCommentService;
import com.djb.ylt.user.service.IDoctorInqueryViewService;
import com.djb.ylt.user.service.IDoctorScheduleService;
import com.djb.ylt.user.service.IDoctorService;
import com.djb.ylt.user.service.IPatientScheduleService;
import com.djb.ylt.user.service.IRecordsService;
import com.djb.ylt.wechat.Utils.HttpXmlUtils;
import com.djb.ylt.wechat.Utils.JdomParseXmlUtils;
import com.djb.ylt.wechat.Utils.RandCharsUtils;
import com.djb.ylt.wechat.Utils.WXSignUtils;
import com.djb.ylt.wechat.Utils.WeixinConfigUtils;
import com.djb.ylt.wechat.entity.UnifiedorderResult;
import com.djb.ylt.wechat.entity.WXPayResult;
import com.djb.ylt.wechat.entity.WXRefundResult;

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

@Controller("/Records")
public class RecordsAction extends BaseAction {

	@Autowired
	@Qualifier("iRecordsService")
	private IRecordsService iRecordsService;
	@Autowired
	@Qualifier("iDoctorCommentService")
	private IDoctorCommentService iDoctorCommentService;

	@Autowired
	@Qualifier("iDepartmentService")
	private IDepartmentService iDepartmentService;

	@Autowired
	@Qualifier("iDoctorInqueryViewService")
	private IDoctorInqueryViewService iDoctorInqueryViewService;
	@Autowired
	@Qualifier("iSymptomService")
	private ISymptomService iSymptomService;

	@Autowired
	@Qualifier("iDoctorScheduleService")
	private IDoctorScheduleService iDoctorScheduleService;

	@Autowired
	@Qualifier("iPatientScheduleService")
	private IPatientScheduleService iPatientScheduleService;
	@Autowired
	@Qualifier("iAppointInquiryService")
	private IAppointInquiryService iAppointInquiryService;

	@Autowired
	@Qualifier("iDoctorService")
	private IDoctorService iDoctorService;

	public RecordsAction() {
		super();
	}

	/**
	 * 预约问诊详情(患者)
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
	public ActionForward doGetAppointDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		RecordsDTO paramDTO = (RecordsDTO) form;
		// 参数Entity
		RecordsEntity paramEntity = new RecordsEntity();

		// 结果ClientDTO
		RecordsDetailClientDTO decordsDetailClientDTO = new RecordsDetailClientDTO();

		// 参数DTO->参数Entity

		CommonUtil.reflectClass(paramDTO, paramEntity);
		// paramEntity.setRecordsId(1);
		// 结果Entity
		RecordsEntity resultEntity = null;
		// 结果DTO
		// AppointInquiryDTO resultDTO=new AppointInquiryDTO();
		// DB查询
		try {
			// TODO SQL
			resultEntity = iRecordsService.getObject(paramEntity);

			// 给客户端结果赋值
			if (resultEntity != null) {
				decordsDetailClientDTO.setAnalysis(resultEntity.getAnalysis());
				if (resultEntity.getAppointTime() != null) {
					decordsDetailClientDTO
							.setAppointTime(CommonUtil.dateToString(resultEntity.getAppointTime(), "yyyy-MM-dd HH:mm"));
				}
				decordsDetailClientDTO.setGuidance(resultEntity.getGuidance());
				if (resultEntity.getCreateTime() != null) {
					decordsDetailClientDTO
							.setCreateTime(CommonUtil.dateToString(resultEntity.getCreateTime(), "yyyy-MM-dd HH:mm"));
				}
				decordsDetailClientDTO.setRecordsType(resultEntity.getRecordsType());
				decordsDetailClientDTO.setUpFlag(resultEntity.getUpFlag());
				decordsDetailClientDTO.setScheduleId(resultEntity.getScheduleId());
				// 判断时间和状态
				if ("1".equals(resultEntity.getInquiryStatus()) && "0".equals(resultEntity.getRecordsType())) {
					if (resultEntity.getAppointTime() != null) {
						Date date = null;
						Date nowDate = new Date();
						Calendar cal = Calendar.getInstance();
						cal.setTime(resultEntity.getAppointTime());
						cal.add(Calendar.MINUTE, 60);// 24小时制
						date = cal.getTime();
						// 当前时间大于预约时间则更改状态为未出结果
						int i = nowDate.compareTo(date);
						if (i > 0) {
							// 更新状态
							RecordsEntity paramRecordsEntity = new RecordsEntity();
							paramRecordsEntity.setRecordsId(resultEntity.getRecordsId());

							paramRecordsEntity.setInquiryStatus("9");
							iRecordsService.updateRecords(paramRecordsEntity);
							decordsDetailClientDTO.setInquiryStatus("9");
						} else {
							decordsDetailClientDTO.setInquiryStatus(resultEntity.getInquiryStatus());
						}
					}
				} else {
					decordsDetailClientDTO.setInquiryStatus(resultEntity.getInquiryStatus());
				}
				// decordsDetailClientDTO.setInquiryStatus(resultEntity.getInquiryStatus());
				decordsDetailClientDTO.setSymptonDescribe(resultEntity.getSymptonDescribe());
				decordsDetailClientDTO.setRecordsId(resultEntity.getRecordsId());
				if (resultEntity.getSymptomEntity() != null) {
					decordsDetailClientDTO.setSymptomName(resultEntity.getSymptomEntity().getSymptomName());
				}
				if (resultEntity.getDoctorEntity() != null) {
					decordsDetailClientDTO.setDoctorId(resultEntity.getDoctorEntity().getDoctorId());
					decordsDetailClientDTO.setHeadPic(resultEntity.getDoctorEntity().getHeadPic());
					decordsDetailClientDTO.setHealDisease(resultEntity.getDoctorEntity().getHealDisease());
					decordsDetailClientDTO.setName(resultEntity.getDoctorEntity().getName());
					decordsDetailClientDTO.setPositional(resultEntity.getDoctorEntity().getPositional());
					decordsDetailClientDTO.setHospitalName(resultEntity.getDoctorEntity().getHospitalName());
					decordsDetailClientDTO.setDcName(resultEntity.getDoctorEntity().getDcName());
					decordsDetailClientDTO.setDepartmentName(resultEntity.getDoctorEntity().getDepartmentName());
					// DepartmentEntity departEntity = new DepartmentEntity();
					// departEntity.setDepId(resultEntity.getDoctorEntity().getDepartmentId());
					// DepartmentEntity departresultEntity = new
					// DepartmentEntity();
					// departresultEntity =
					// iDepartmentService.getObject(departEntity);
					// if (departresultEntity != null) {
					// decordsDetailClientDTO.setDepartmentName(departresultEntity.getDepName());
					// }
					// 获取平均评分
					DoctorCommentEntity commenEntity = new DoctorCommentEntity();
					DoctorCommentEntity commenresultEntity = new DoctorCommentEntity();
					commenEntity.setDoctorId(resultEntity.getDoctorEntity().getDoctorId());
					commenresultEntity = iDoctorCommentService.getAverageGrade(commenEntity);
					if (commenresultEntity != null && commenresultEntity.getGrade() != null) {
						decordsDetailClientDTO
								.setCommentGrade((float) (Math.round(commenresultEntity.getGrade() * 10)) / 10);
					}
				}
				if (resultEntity.getAppointInqueryEntity() != null
						&& resultEntity.getAppointInqueryEntity().getSymptonId() != null) {
					SymptomEntity symptomEntity = new SymptomEntity();
					symptomEntity.setSymptomId(resultEntity.getAppointInqueryEntity().getSymptonId());
					SymptomEntity symptomResult = new SymptomEntity();
					symptomResult = iSymptomService.getObject(symptomEntity);
					decordsDetailClientDTO.setSymptomName(symptomResult.getSymptomName());
				}
			}
			// 参数Entity
			DoctorCommentEntity commentEntity = new DoctorCommentEntity();
			commentEntity.setRecordsId(paramDTO.getRecordsId());
			commentEntity.setPatientId(paramDTO.getPatientId());
			DoctorCommentEntity commentResult = new DoctorCommentEntity();
			commentResult = iDoctorCommentService.getObject(commentEntity);
			if (commentResult == null) {
				decordsDetailClientDTO.setCommentFlag("0");
			} else {
				decordsDetailClientDTO.setCommentFlag("1");
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			decordsDetailClientDTO.setReturnCode("-1071");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			decordsDetailClientDTO.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, decordsDetailClientDTO);

		return null;
	}

	/**
	 * 预约问诊详情
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
	public ActionForward doAddPrivateRecords(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		RecordsDTO paramDTO = (RecordsDTO) form;
		// 参数Entity
		RecordsEntity paramEntity = new RecordsEntity();
		// paramDTO.setInqueryQuestionList("[{\"iqId\":1,\"depId\":1,\"inqueryAnswerDTOs\":[{\"patientId\":1,\"iaContent\":\"哇哈哈哈\",\"depId\":1}]},{\"iqId\":12,\"depId\":1,\"inqueryAnswerDTOs\":[{\"patientId\":1,\"irId\":10,\"depId\":1},{\"patientId\":1,\"irId\":7,\"depId\":1}]}]");
		// 结果ClientDTO
		BaseClientDTO resultsClientDTO = new BaseClientDTO();

		// 参数DTO->参数Entity
		if (paramDTO.getInqueryQuestionList() != null && !"".equals(paramDTO.getInqueryQuestionList())) {
			paramEntity = this.getJsonStringToObject(paramDTO);
		}
		CommonUtil.reflectClass(paramDTO, paramEntity);

		if ("0".equals(paramEntity.getRecordsType()) && paramEntity.getScheduleId() != null) {
			// 参数Entity
			// 视频更新日程
			if (paramEntity.getScheduleId() != null) {
				DoctorScheduleEntity scheduleEntity = new DoctorScheduleEntity();
				scheduleEntity.setScheduleId(paramEntity.getScheduleId());
				DoctorScheduleEntity resultSchelduleEntity = new DoctorScheduleEntity();
				resultSchelduleEntity = iDoctorScheduleService.getObject(scheduleEntity);
				if (resultSchelduleEntity != null) {
					if (resultSchelduleEntity.getAppointNum() >= resultSchelduleEntity.getPatientNum()) {
						resultsClientDTO.setReturnCode("-1022");
						toJson(response, resultsClientDTO);
						return null;
					}
				}
			}
		}
		// 预约成功
		// if ("0".equals(paramDTO.getRecordsType())) {
		// paramEntity.setInquiryStatus("0");
		// }
		// if ("1".equals(paramDTO.getRecordsType())) {
		paramEntity.setInquiryStatus("1");
		// }
		// 预约时间
		if (paramDTO.getAppointTime() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			paramEntity.setAppointTime(sdf.parse(paramDTO.getAppointTime()));
		}

		// 拼接患者症状描述
		StringBuffer describe = new StringBuffer();
		if (paramEntity.getInqueryQuestionEntitys() != null && paramEntity.getInqueryQuestionEntitys().size() > 0) {

			for (InqueryQuestionEntity inqueryQuestion : paramEntity.getInqueryQuestionEntitys()) {
				describe.append(inqueryQuestion.getIqName() + "\n");

				if (inqueryQuestion.getInqueryAnswerEntitys() != null
						&& inqueryQuestion.getInqueryAnswerEntitys().size() > 0) {
					for (InqueryAnswerEntity answer : inqueryQuestion.getInqueryAnswerEntitys()) {
						if (answer.getIrName() != null) {
							describe.append("  " + answer.getIrName() + "\n");
						} else {
							describe.append("  " + answer.getIaContent() + "\n");
						}
					}
				}
			}
			// 自己填写的备注会放在医生看到的症状描述里面
			if (paramDTO.getMemo() != null && !"".equals(paramDTO.getMemo())) {
				describe.append(paramDTO.getMemo());
			}
			if (describe != null && describe.length() > 0) {
				paramEntity.setSymptonDescribe(describe.toString());
			}
		}
		// 患者上传图片 (在问诊票中上传图片)图片插入到问诊票回答和就诊记录中

		// 图片参数格式化

		if (paramDTO.getImageList() != null) {
			StringBuffer imageString = new StringBuffer();
			for (FormFile images : paramDTO.getImageList()) {
				if (images != null && images.getFileSize() > 0) {
					String paId = paramDTO.getPatientId().toString() + "p";
					String filePath = UploadUtil.doUpload(request, response, images, paId);
					String iamgePath = ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS) + filePath;
					imageString.append(iamgePath);

					imageString.append("|");
				}
				if (imageString != null && imageString.length() > 0) {
					String str = imageString.toString();
					paramEntity.setInquiryPic(str.substring(0, str.length() - 1));
				}
			}
		}
		// DB查询
		try {
			paramEntity.setPatientId(paramDTO.getPatientId());
			paramEntity.setScheduleId(paramDTO.getScheduleId());
			iRecordsService.addRecords(paramEntity);
			if (paramEntity.getRecordsId() != null) {
				DoctorEntity doctor = new DoctorEntity();
				doctor.setDoctorId(paramDTO.getDoctorId());
				DoctorEntity resultDoctor = iDoctorService.getDoctorTel(doctor);
				if (resultDoctor != null) {
					Map<String, String> extras = new HashMap<String, String>();
					extras.put("patientId", paramDTO.getPatientId().toString());
					extras.put("recordsId", paramEntity.getRecordsId().toString());
					PushConfig.sendNotificationWithAlias(resultDoctor.getDoctorTel(), extras);
				}
			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultsClientDTO.setReturnCode("-1021");

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
	 * 预约问诊详情(医生)OK
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
	public ActionForward doGetAppointDetailByDoctor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		RecordsDTO paramDTO = (RecordsDTO) form;
		// 参数Entity
		DoctorInquiryViewEntity paramEntity = new DoctorInquiryViewEntity();

		// 结果ClientDTO
		RecordsDetailInfoDTO decordsDetailClientDTO = new RecordsDetailInfoDTO();

		// 参数DTO->参数Entity

		// CommonUtil.reflectClass(paramDTO, paramEntity);
		paramEntity.setRecordsId(paramDTO.getRecordsId());
		// paramEntity.setRecordsId(1);
		// 结果Entity
		DoctorInquiryViewEntity resultEntity = null;
		// 结果DTO
		// AppointInquiryDTO resultDTO=new AppointInquiryDTO();
		// DB查询
		try {
			resultEntity = iDoctorInqueryViewService.getObject(paramEntity);
			// 给客户端结果赋值
			if (resultEntity != null) {
				decordsDetailClientDTO.setAnalysis(resultEntity.getAnalysis());
				decordsDetailClientDTO
						.setAppointTime(CommonUtil.dateToString(resultEntity.getAppointTime(), "yyyy-MM-dd HH:mm"));
				decordsDetailClientDTO.setGuidance(resultEntity.getGuidance());
				if (resultEntity.getCreateTime() != null) {
					decordsDetailClientDTO
							.setCreateTime(CommonUtil.dateToString(resultEntity.getCreateTime(), "yyyy-MM-dd HH:mm"));
				}
				decordsDetailClientDTO.setRecordsType(resultEntity.getRecordsType());
				decordsDetailClientDTO.setTelNum(resultEntity.getTelNum());
				decordsDetailClientDTO.setInquiryPic(resultEntity.getInquiryPic());
				// 判断时间和状态
				if ("1".equals(resultEntity.getInquiryStatus()) && "0".equals(resultEntity.getRecordsType())) {
					if (resultEntity.getAppointTime() != null) {
						Date date = null;
						Date nowDate = new Date();
						Calendar cal = Calendar.getInstance();
						cal.setTime(resultEntity.getAppointTime());
						cal.add(Calendar.MINUTE, 60);// 24小时制
						date = cal.getTime();
						// 当前时间大于预约时间则更改状态为未出结果
						int i = nowDate.compareTo(date);
						if (i > 0) {
							// 更新状态
							RecordsEntity paramRecordsEntity = new RecordsEntity();
							paramRecordsEntity.setRecordsId(resultEntity.getRecordsId());

							paramRecordsEntity.setInquiryStatus("9");
							iRecordsService.updateRecords(paramRecordsEntity);
							decordsDetailClientDTO.setInquiryStatus("9");
						} else {
							decordsDetailClientDTO.setInquiryStatus(resultEntity.getInquiryStatus());
						}
					}
				} else {
					decordsDetailClientDTO.setInquiryStatus(resultEntity.getInquiryStatus());
				}

				// decordsDetailClientDTO.setInquiryStatus(resultEntity.getInquiryStatus());
				decordsDetailClientDTO.setSymptonDescribe(resultEntity.getSymptonDescribe());
				decordsDetailClientDTO.setRecordsId(resultEntity.getRecordsId());
				decordsDetailClientDTO.setPatientId(resultEntity.getPatientId());
				decordsDetailClientDTO.setName(resultEntity.getName());
				decordsDetailClientDTO.setPatientPic(resultEntity.getPatientPic());
				decordsDetailClientDTO.setDepartmentName(resultEntity.getDepName());
				decordsDetailClientDTO.setSymptomName(resultEntity.getSymptomName());
				decordsDetailClientDTO.setSex(resultEntity.getSex());
				if (resultEntity.getBirth() != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					decordsDetailClientDTO.setBirth(sdf.format(resultEntity.getBirth()));
				}

			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			decordsDetailClientDTO.setReturnCode("-2021");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			decordsDetailClientDTO.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, decordsDetailClientDTO);

		return null;
	}

	/**
	 * 预约问诊详情
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
	public ActionForward doUpdateRecordsInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		RecordsDTO paramDTO = (RecordsDTO) form;
		// 参数Entity
		RecordsEntity paramEntity = new RecordsEntity();

		// 结果ClientDTO
		BaseClientDTO resultsClientDTO = new BaseClientDTO();

		// 参数DTO->参数Entity

		CommonUtil.reflectClass(paramDTO, paramEntity);

		// paramEntity.setRecordsId(12);
		// paramEntity.setAnalysis("流行性感冒");
		// paramEntity.setGuidance("建议化验血");
		// DB查询
		try {
			iRecordsService.updateRecords(paramEntity);

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultsClientDTO.setReturnCode("-2022");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultsClientDTO.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, resultsClientDTO);
		// 获取患者电话推送
		// 参数Entity
		RecordsEntity pushParam = new RecordsEntity();
		pushParam.setRecordsId(paramDTO.getRecordsId());
		RecordsEntity resultParam = null;
		resultParam = iRecordsService.getPatientPushInfo(pushParam);
		// 给客户端结果赋值
		if (resultParam != null) {
			Map<String, String> extras = new HashMap<String, String>();
			extras.put("patientId", resultParam.getAppointInqueryEntity().getPatientId().toString());
			extras.put("recordsId", paramDTO.getRecordsId().toString());
			if (resultParam.getPatientEntity().getPatientTel() != null) {
				PushConfig.sendNotificationWithAliasForPatient(resultParam.getPatientEntity().getPatientTel(), extras);
			}
		}
		return null;
	}

	/**
	 * 预约问诊详情
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
	public ActionForward doUpdateRecordsTime(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		RecordsDTO paramDTO = (RecordsDTO) form;
		// 参数Entity
		RecordsEntity paramEntity = new RecordsEntity();

		// 结果ClientDTO
		BaseClientDTO resultsClientDTO = new BaseClientDTO();

		// 参数DTO->参数Entity

		CommonUtil.reflectClass(paramDTO, paramEntity);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (paramDTO.getAppointTime() != null && !"".equals(paramDTO.getAppointTime())) {
			paramEntity.setAppointTime(sdf.parse(paramDTO.getAppointTime()));
		}
		;
		// paramEntity.setRecordsId(12);
		// paramEntity.setAnalysis("流行性感冒");
		// paramEntity.setGuidance("建议化验血");
		// DB查询
		paramEntity.setRecordsId(paramDTO.getRecordsId());
		paramEntity.setScheduleId(paramDTO.getScheduleId());
		paramEntity.setSchId(paramDTO.getSchId());
		paramEntity.setPatientId(paramDTO.getPatientId());

		try {
			int flag = iRecordsService.updateRecordsTime(paramEntity);
			// 给客户端结果赋值
			if (flag != 0) {
				paramDTO.setErrFlg(true);
				resultsClientDTO.setReturnCode("-1012");
				toJson(response, resultsClientDTO);
				return null;
			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultsClientDTO.setReturnCode("-1011");

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
	 * 预约问诊详情
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
	public ActionForward doDoctorAgreeCancel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		RecordsDTO paramDTO = (RecordsDTO) form;
		// 参数Entity
		RecordsEntity paramEntity = new RecordsEntity();

		// 结果ClientDTO
		BaseClientDTO resultsClientDTO = new BaseClientDTO();

		// 参数DTO->参数Entity

		CommonUtil.reflectClass(paramDTO, paramEntity);

		// 结果

		RecordsEntity resultEntity = new RecordsEntity();
		try {
			resultEntity = iRecordsService.getObject(paramEntity);

			if (resultEntity != null) {
				if ("5".equals(resultEntity.getInquiryStatus())) {
					// 参数Entity
					RecordsEntity paramUpdate = new RecordsEntity();
					paramUpdate.setRecordsId(paramDTO.getRecordsId());
					paramUpdate.setInquiryStatus("6");
					paramUpdate.setScheduleId(resultEntity.getScheduleId());
					paramUpdate.setPatschId(resultEntity.getPatschId());
					iRecordsService.updateRecords(paramUpdate);
					String paramJson = resultEntity.getAppointInqueryEntity().getPayParam();
					if (paramJson != null && !"".equals(paramJson)) {
						AlipayNotification alipayInfo = this.getParamFromJson(paramJson);
						// 原付款支付宝交易号^退款总金额^退款理由。
						// this.sendRefundInfo(alipayInfo.getOutTradeNo(),
						// alipayInfo.getTotalFee());
						// OrderNumberUtil orderNumberUtil = new
						// OrderNumberUtil();
						// 采番订单编号
						// String
						// orderNum=orderNumberUtil.getOrderNumber(request);
						// this.sendRefundInfo(alipayInfo.getTradeNo(),
						// alipayInfo.getTotalFee(), orderNum);
					}
				} else {
					paramDTO.setErrFlg(true);
					resultsClientDTO.setReturnCode("-2061");
				}

			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultsClientDTO.setReturnCode("-2060");

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
	 * 请求支付宝退款
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
	public void sendRefundInfo(String tradeNo, Double refund_amount, String orderNum, String refundReason)
			throws Exception {

		// 服务器异步通知页面路径
		String hostIp = ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS);
		// String
		StringBuffer url = new StringBuffer();
		url.append(hostIp);
		//url.append("Records.do" + "receiveRefundInfo" + "=");
		url.append("Records.do");
		String notify_url = url.toString();
		// 需http://格式的完整路径，不允许加?id=123这类自定义参数

		// 退款批次号
		String batch_no = orderNum.toString();
		// 必填，每进行一次即时到账批量退款，都需要提供一个批次号，必须保证唯一性
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		// 退款请求时间
		String refund_date = sdf1.format(new Date());
		// 必填，格式为：yyyy-MM-dd hh:mm:ss
		// 退款总笔数
		String batch_num = "1";
		// 必填，即参数detail_data的值中，“#”字符出现的数量加1，最大支持1000笔（即“#”字符出现的最大数量999个）
		// 单笔数据集
		String detail_data = tradeNo + "^" + refund_amount + "^" + refundReason;
		// 必填，格式详见“4.3 单笔数据集参数说明”

		//////////////////////////////////////////////////////////////////////////////////

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "refund_fastpay_by_platform_nopwd");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("batch_no", batch_no);
		sParaTemp.put("refund_date", refund_date);
		sParaTemp.put("batch_num", batch_num);
		sParaTemp.put("detail_data", detail_data);
		// 建立请求
		String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp);
		// String sHtmlText = AlipaySubmit.
		// out.println(sHtmlText);
		System.out.println(">>>>>>>" + sHtmlText);

	}

	/**
	 * 请求微信退款
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
	public String sendWechatRefundInfo(WXPayResult wxPayResult, String num) throws Exception {

		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		StringBuffer buffer = new StringBuffer();
		parameters.put("appid", WeixinConfigUtils.appid);
		parameters.put("mch_id", WeixinConfigUtils.mch_id);
		String noce = RandCharsUtils.getRandomString(16);
		parameters.put("nonce_str", noce);
		// 在notify_url中解析微信返回的信息获取到 transaction_id，此项不是必填，详细请看上图文档
		parameters.put("transaction_id", wxPayResult.getTransaction_id());
		parameters.put("out_trade_no", wxPayResult.getOut_trade_no());
		parameters.put("out_refund_no", num.toString()); // 我们自己设定的退款申请号，约束为UK
		parameters.put("total_fee", wxPayResult.getTotal_fee()); // 单位为分
		parameters.put("refund_fee", wxPayResult.getTotal_fee()); // 单位为分
		parameters.put("op_user_id", WeixinConfigUtils.mch_id);
		String sign = WXSignUtils.createSign("UTF-8", parameters);
		parameters.put("sign", sign);
		String reuqestXml = HttpXmlUtils.getRequestXml(parameters);

		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		FileInputStream instream = new FileInputStream(new File("D:/apiclient_cert.p12"));// 放退款证书的路径
		try {
			keyStore.load(instream, WeixinConfigUtils.mch_id.toCharArray());
		} finally {
			instream.close();
		}

		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, WeixinConfigUtils.mch_id.toCharArray())
				.build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		try {

			HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");// 退款接口

			System.out.println("executing request" + httpPost.getRequestLine());
			StringEntity reqEntity = new StringEntity(reuqestXml);
			// 设置类型
			reqEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(reqEntity);
			CloseableHttpResponse responseWechat = httpclient.execute(httpPost);
			try {
				HttpEntity entity = responseWechat.getEntity();

				System.out.println("----------------------------------------");
				System.out.println(responseWechat.getStatusLine());
				if (entity != null) {
					System.out.println("Response content length: " + entity.getContentLength());
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(entity.getContent(), "UTF-8"));
					String text = null;

					while ((text = bufferedReader.readLine()) != null) {
						buffer.append(text);
					}

				}
				EntityUtils.consume(entity);
				buffer.toString();
			} finally {
				responseWechat.close();
			}
		} finally {
			httpclient.close();
		}

		return buffer.toString();

	}

	/**
	 * 支付宝退款回调函数
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
	public ActionForward receiveRefundInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 参数DTO
	@SuppressWarnings("unused")
	RecordsDTO paramDTO = (RecordsDTO) form;

		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		@SuppressWarnings("rawtypes")
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 退款批次号
		String batch_no = new String(request.getParameter("batch_no").getBytes("ISO-8859-1"), "UTF-8");
		// 必填

		// 退款成功总数
		//String success_num = new String(request.getParameter("success_num").getBytes("ISO-8859-1"), "UTF-8");
		// 必填，0<= success_num<= batch_num

		// 处理结果详情
		//String result_details = new String(request.getParameter("result_details").getBytes("ISO-8859-1"), "UTF-8");
		// 必填，详见“6.3 处理结果详情说明”

		// 解冻结果明细
		//String unfreezed_deta = new String(request.getParameter("unfreezed_deta").getBytes("ISO-8859-1"), "UTF-8");
		// 格式：解冻结订单号^冻结订单号^解冻结金额^交易号^处理时间^状态^描述码
		PrintWriter printWriter = null;
		printWriter = response.getWriter();
		String resultResponse = "success";
		try {
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			AppointInquiryEntity appointEntity = new AppointInquiryEntity();
			appointEntity.setRefundNum(batch_no);
			AppointInquiryEntity appointResult = new AppointInquiryEntity();
			appointResult = iAppointInquiryService.getObject(appointEntity);
			AppointInquiryEntity paramAppoint = new AppointInquiryEntity();
			paramAppoint.setAppointId(appointResult.getAppointId());
			if (AlipayNotify.verify(params)) {// 验证成功		
				if (appointResult != null) {				
					paramAppoint.setPayStatus("3");
					
				}
			} else {// 验证失败
				
				paramAppoint.setPayStatus("4");
				//iAppointInquiryService.updateAppointInquiry(appointEntity);
				resultResponse = "fail";

			}
			paramAppoint.setRefundParam(params.toString());
		//	appointEntity.setRefundNum(batch_no);
			iAppointInquiryService.updateAppointInquiry(paramAppoint);
			//推送
			AppointInquiryEntity pushEntity = new AppointInquiryEntity();
			pushEntity.setAppointId(appointResult.getAppointId());
//			//pushEntity.setOrderNumber(wxPayResult.getOut_trade_no());
//			pushEntity.setRefundNum(batch_no);
			AppointInquiryEntity pushResultEntity = iAppointInquiryService
					.getInfoForPush(pushEntity);
			if (pushResultEntity != null) {
				if (!"1".equals(pushResultEntity.getRecordsEntitys().get(0).getDoctorFlag())) {
					Map<String, String> extras = new HashMap<String, String>();
					extras.put("patientId", pushResultEntity.getPatientId().toString());
					extras.put("recordsId",
							pushResultEntity.getRecordsEntitys().get(0).getRecordsId().toString());
					if (pushResultEntity.getDoctorEntity().getDoctorTel() != null) {
						PushConfig.sendNotification(
								pushResultEntity.getDoctorEntity().getDoctorTel(), extras,PushConfig.ALERT_CANCEL,PushConfig.TITLE_CANCEL);
						RecordsEntity records = new RecordsEntity();
						records.setRecordsId(
								pushResultEntity.getRecordsEntitys().get(0).getRecordsId());
						records.setDoctorFlag("1");
						iRecordsService.updateRecords(records);
					}
				}
			}
		} catch (Exception e) {
			logger.error("alipay notify error :", e);
			// paramDTO.setErrFlg(true);
			// resultClientDTO.setReturnCode("-1080");
			resultResponse = "fail";
			printWriter.close();

		}
		if (printWriter != null) {
			printWriter.print(resultResponse);
		}

		return null;
	}

	/**
	 * 患者取消预约
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
	public ActionForward doCancelRecordsInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		RecordsDTO paramDTO = (RecordsDTO) form;
		// 参数Entity
		RecordsEntity paramEntity = new RecordsEntity();
		// 参数Entity
		RecordsEntity resultEntity = new RecordsEntity();
		// 结果ClientDTO
		BaseClientDTO resultsClientDTO = new BaseClientDTO();

		// 参数DTO->参数Entity

		CommonUtil.reflectClass(paramDTO, paramEntity);

		// DB查询
		try {
			// 获取订单信息
			resultEntity = iRecordsService.getObject(paramEntity);
			if (resultEntity != null && resultEntity.getAppointInqueryEntity() != null) {
				if ("1".equals(resultEntity.getInquiryStatus())) {
					// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd
					// HH:mm:ss");
					Date date = new Date();
					Calendar appointTime = Calendar.getInstance();
					appointTime.setTime(resultEntity.getAppointTime());
					appointTime.add(Calendar.HOUR, -1);
					appointTime.getTime();
					Calendar nowDate = Calendar.getInstance();
					nowDate.setTime(date);
					if (nowDate.before(appointTime)) {
						// 参数Entity
						// 换者取消的时候同时取消医生日程
						paramEntity.setAppointId(resultEntity.getAppointInqueryEntity().getAppointId());
						paramEntity.setScheduleId(resultEntity.getScheduleId());
						paramEntity.setPatschId(resultEntity.getPatschId());
						//本地退款单号
						OrderNumberUtil orderNumberUtil = new OrderNumberUtil();
						// 采番订单编号
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
						String dateNum = sdf.format(new Date());
						String orderNum = orderNumberUtil.getOrderNumber(request);
						StringBuffer num = new StringBuffer();
						num.append(dateNum);
						num.append(orderNum);
						//paramEntity.setOutTradeNum(num.toString());
						paramEntity.setRefundNum(num.toString());
						iRecordsService.updateRecords(paramEntity);
					
						//end 微信参数
						// 获取支付信息
						if (resultEntity.getAppointInqueryEntity().getPayType() != null) {
							String paramJson = resultEntity.getAppointInqueryEntity().getPayParam();
							// 支付宝退款 支付宝退款需要在回调里处理业务逻辑
							if ("0".equals(resultEntity.getAppointInqueryEntity().getPayType()) && paramJson != null
									&& !"".equals(paramJson)) {
								AlipayNotification alipayInfo = this.getParamFromJson(paramJson);
								// 原付款支付宝交易号^退款总金额^退款理由。
								this.sendRefundInfo(alipayInfo.getTradeNo(), alipayInfo.getTotalFee(), num.toString(),
										paramDTO.getRefundReason());
							} else if ("1".equals(resultEntity.getAppointInqueryEntity().getPayType())
									&& paramJson != null && !"".equals(paramJson)) {
								//微信退款处理参数的更新
								AppointInquiryEntity appointEntity = new AppointInquiryEntity();
								appointEntity.setAppointId(resultEntity.getAppointInqueryEntity().getAppointId());
								//appointEntity.setRefundNum(num.toString());
								// 微信退款 无需回调直接处理
								WXPayResult wxPayResult = JdomParseXmlUtils.getWXPayResult(paramJson);
								String result = this.sendWechatRefundInfo(wxPayResult, num.toString());
								if (!"".equals(result) && result != null) {
									appointEntity.setRefundParam(result);
									WXRefundResult wXRefundResult = JdomParseXmlUtils.getWXRefundResult(result);
									if (wXRefundResult != null) {
										if ("SUCCESS".equals(wXRefundResult.getReturn_code())) {
											appointEntity.setPayStatus("3");
										} else {
											appointEntity.setPayStatus("4");
										}
									}
									iAppointInquiryService.updateAppointInquiry(appointEntity);
									//推送
									AppointInquiryEntity pushEntity = new AppointInquiryEntity();
									pushEntity.setAppointId(resultEntity.getAppointInqueryEntity().getAppointId());
									//pushEntity.setOrderNumber(wxPayResult.getOut_trade_no());
									AppointInquiryEntity pushResultEntity = iAppointInquiryService
											.getInfoForPush(pushEntity);
									if (pushResultEntity != null) {
										if (!"1".equals(pushResultEntity.getRecordsEntitys().get(0).getDoctorFlag())) {
											Map<String, String> extras = new HashMap<String, String>();
											extras.put("patientId", pushResultEntity.getPatientId().toString());
											extras.put("recordsId",
													pushResultEntity.getRecordsEntitys().get(0).getRecordsId().toString());
											if (pushResultEntity.getDoctorEntity().getDoctorTel() != null) {
												PushConfig.sendNotification(
														pushResultEntity.getDoctorEntity().getDoctorTel(), extras,PushConfig.ALERT_CANCEL,PushConfig.TITLE_CANCEL);
												RecordsEntity records = new RecordsEntity();
												records.setRecordsId(
														pushResultEntity.getRecordsEntitys().get(0).getRecordsId());
												records.setDoctorFlag("1");
												iRecordsService.updateRecords(records);
											}
										}
									}
								}
							}

						} 
						//else {
						//	paramDTO.setErrFlg(true);
						//	resultsClientDTO.setReturnCode("-1301");
							// toJson(response, resultsClientDTO);
						//}
						
					} else {
						paramDTO.setErrFlg(true);
						resultsClientDTO.setReturnCode("-1301");
					}
				}
			}
			// 给客户端结果赋值

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultsClientDTO.setReturnCode("-1300");

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
	 * 预约问诊详情
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
	public ActionForward doUpdateRecordsBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		RecordsDTO paramDTO = (RecordsDTO) form;
		// 参数Entity
		// RecordsEntity paramEntity = new RecordsEntity();
		// 结果ClientDTO
		// BaseClientDTO resultsClientDTO = new BaseClientDTO();
		// 参数DTO->参数Entity
		List<RecordsEntity> records = this.getPushJsonToObject(paramDTO);

		// paramEntity.setRecordsId(12);
		// paramEntity.setAnalysis("流行性感冒");
		// paramEntity.setGuidance("建议化验血");
		// DB查询
		try {
			if (records != null) {
				iRecordsService.updateRecordsBatch(records);
			}
			// 给客户端结果赋值

		} catch (Exception e) {
			// paramDTO.setErrFlg(true);
			// resultsClientDTO.setReturnCode("-2022");

		}
		// return 处理
		// if (!paramDTO.isErrFlg()) {
		// resultsClientDTO.setReturnCode("0");
		//
		// }
		// 返回结果
		// toJson(response, resultsClientDTO);

		return null;
	}

	/**
	 * 客户端请求json格式的字符串转换成对象。
	 * 
	 * @param vo
	 *            用户端请求值
	 */
	private RecordsEntity getJsonStringToObject(RecordsDTO paramDTO) {

		// 取得客户端传入的json格式的字符串
		String param = paramDTO.getInqueryQuestionList();
		RecordsEntity recordsEntity = new RecordsEntity();
		if (!"".equals(param)) {
			List<InqueryQuestionDTO> inqueryQuestionList = GsonUtil.getBeanList(param, InqueryQuestionDTO.class);
			//

			List<InqueryQuestionEntity> paramList = new ArrayList<InqueryQuestionEntity>();

			for (InqueryQuestionDTO inqueryQuestion : inqueryQuestionList) {
				InqueryQuestionEntity inqueryEntity = new InqueryQuestionEntity();
				// 问题信息
				inqueryEntity.setIqName(inqueryQuestion.getIqName());
				inqueryEntity.setIqId(inqueryQuestion.getIqId());
				// 转换答案Json
				List<InqueryAnswerEntity> answerList = new ArrayList<InqueryAnswerEntity>();
				if (inqueryQuestion.getInqueryAnswerDTOs() != null
						&& inqueryQuestion.getInqueryAnswerDTOs().size() > 0) {
					for (InqueryAnswerDTO inquery : inqueryQuestion.getInqueryAnswerDTOs()) {
						InqueryAnswerEntity paramAnswer = new InqueryAnswerEntity();
						paramAnswer.setDepId(paramDTO.getDepartmentId());
						paramAnswer.setPatientId(paramDTO.getPatientId());
						paramAnswer.setIqId(inqueryQuestion.getIqId());
						paramAnswer.setIrId(inquery.getIrId());
						paramAnswer.setIaContent(inquery.getIaContent());
						paramAnswer.setIaMemo(inquery.getIaMemo());
						paramAnswer.setIrName(inquery.getIrName());
						answerList.add(paramAnswer);
					}
					inqueryEntity.setInqueryAnswerEntitys(answerList);

				}
				paramList.add(inqueryEntity);
			}
			recordsEntity.setInqueryQuestionEntitys(paramList);
		}
		// CommonUtil.reflectClass(paramDTO, recordsEntity);
		return recordsEntity;
	}

	/**
	 * 客户端请求json格式的字符串转换成对象。
	 * 
	 * @param vo
	 *            用户端请求值
	 */
	private List<RecordsEntity> getPushJsonToObject(RecordsDTO paramDTO) {

		// 取得客户端传入的json格式的字符串
		String param = paramDTO.getPushString();
		List<RecordsEntity> recordsEntityList = null;
		if (!"".equals(param)) {
			recordsEntityList = GsonUtil.getBeanList(param, RecordsEntity.class);
		}
		return recordsEntityList;
	}

	private AlipayNotification getParamFromJson(String paramString) {

		// 取得客户端传入的json格式的字符串
		// String param = paramDTO.getInqueryQuestionList();
		AlipayNotification parmaAlipay = new AlipayNotification();

		parmaAlipay = GsonUtil.getBeanFromJson(paramString, AlipayNotification.class);

		return parmaAlipay;
	}

	/**
	 * 预约问诊详情
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
	public ActionForward doDeleteRecords(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		RecordsDTO paramDTO = (RecordsDTO) form;
		// 参数Entity
		RecordsEntity paramEntity = new RecordsEntity();

		// 结果ClientDTO
		BaseClientDTO resultsClientDTO = new BaseClientDTO();

		// 参数DTO->参数Entity

		// CommonUtil.reflectClass(paramDTO, paramEntity);
		paramEntity.setRecordsId(paramDTO.getRecordsId());
		if ("0".equals(paramDTO.getDelFlag())) {
			paramEntity.setDdelFlag("0");
		} else if ("1".equals(paramDTO.getDelFlag())) {
			paramEntity.setPdelFlag("0");
		}
		// paramEntity.setPdelFlag(paramDTO.getPdelFlag());
		// paramEntity.setRecordsId(12);
		// paramEntity.setAnalysis("流行性感冒");
		// paramEntity.setGuidance("建议化验血");
		// DB查询
		if (paramDTO.getPdelFlag() != null) {
			RecordsEntity resultEntity = new RecordsEntity();
			RecordsEntity param = new RecordsEntity();
			param.setRecordsId(paramDTO.getRecordsId());
			resultEntity = iRecordsService.getObject(param);
			if (resultEntity != null) {
				paramEntity.setScheduleId(resultEntity.getScheduleId());
				paramEntity.setPatschId(resultEntity.getPatientId());
			}
		}
		try {
			iRecordsService.updateRecords(paramEntity);

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultsClientDTO.setReturnCode("-6000");

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
	 * 获取我的问诊列表
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

	// public ActionForward doUpdateStatusForBatch(ActionMapping mapping,
	// ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	//
	// // 参数DTO
	// RecordsDTO paramDTO = (RecordsDTO) form;
	// // 参数Entity
	// RecordsEntity paramEntity = new RecordsEntity();
	// // 参数Entity
	// //AppointInquiryEntity paramEntity = new AppointInquiryEntity();
	//
	// // 结果ClientDTO
	// AppointInquiryDTO resultClientDTO = new AppointInquiryDTO();
	// List<PatientAppointClientDTO> patientAppointList = null;
	//
	// // 参数DTO->参数Entity
	// CommonUtil.reflectClass(paramDTO, paramEntity);
	// // paramEntity.setPageSize(paramDTO.getPageSize());
	// // paramEntity.setPageNum(paramDTO.getPageNum());
	// // 结果Entity
	// List<AppointInquiryEntity> resultEntity = null;
	//
	// // DB查询
	// //try {
	//
	// // 预约问诊状态是未支付，分为视频，电话和私人医生状况,视频需要释放医生的日程,
	// // 如果是私人医生和视频问诊的情况
	// // 如果是私人医生和电话问诊的情况直接判断时间+1小时是否大于当前时间大于则更新状态
	//
	////
	// }
}