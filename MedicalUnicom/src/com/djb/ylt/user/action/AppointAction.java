/**
 * Project Name:MedicalUnicomAPP
 * File Name:PatientAction.java
 * Package Name:com.djb.ylt.user.action
 * Copyright © 大连必捷必信息技术有限公司  All Rights Reserved.
*/

package com.djb.ylt.user.action;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.internal.util.AlipaySignature;
import com.djb.ylt.alipay.action.AsynAlipayNotifyAction;
import com.djb.ylt.alipay.config.AlipayConfig;
import com.djb.ylt.alipay.dto.AlipayNotification;
import com.djb.ylt.alipay.util.RequestUtils;
import com.djb.ylt.alipay.util.SignUtils;
import com.djb.ylt.common.util.CommonUtil;
import com.djb.ylt.common.util.Constants;
import com.djb.ylt.common.util.GsonUtil;
import com.djb.ylt.common.util.OrderNumberUtil;
import com.djb.ylt.common.util.ResourceLocator;
import com.djb.ylt.common.util.UploadUtil;
import com.djb.ylt.framework.action.BaseAction;
import com.djb.ylt.framework.dto.BaseClientDTO;
import com.djb.ylt.gene.dtoclient.DetectAnalysisClientDTO;
import com.djb.ylt.gene.dtoclient.DetectResultClientDTO;
import com.djb.ylt.gene.dtoclient.DiseaseAnalysisClientDTO;
import com.djb.ylt.gene.dtoclient.TestResultClientDTO;
import com.djb.ylt.gene.entity.DetectAnalysisEntity;
import com.djb.ylt.gene.entity.TestResultEntity;
import com.djb.ylt.gene.entity.TestResultMasterEntity;
import com.djb.ylt.gene.service.IDetectAnalysisService;
import com.djb.ylt.gene.service.ITestResultMasterService;
import com.djb.ylt.health.dto.InqueryAnswerDTO;
import com.djb.ylt.health.dto.InqueryQuestionDTO;
import com.djb.ylt.health.entity.InqueryAnswerEntity;
import com.djb.ylt.health.entity.InqueryQuestionEntity;
import com.djb.ylt.health.service.IDepartmentService;
import com.djb.ylt.jpush.api.PushConfig;
import com.djb.ylt.user.dto.AppointInquiryDTO;
import com.djb.ylt.user.dto.RecordsDTO;
import com.djb.ylt.user.dtoclient.AppoinPushDTO;
import com.djb.ylt.user.dtoclient.AppoinPushDTOList;
import com.djb.ylt.user.dtoclient.AppointInquiryGraphicClientDTO;
import com.djb.ylt.user.dtoclient.AppointInsertClientDTO;
import com.djb.ylt.user.dtoclient.InqueryViewClientDTO;
import com.djb.ylt.user.dtoclient.InqueryViewListDTO;
import com.djb.ylt.user.dtoclient.MonthRecordsClientDTO;
import com.djb.ylt.user.dtoclient.PatientAppointClientDTO;
import com.djb.ylt.user.dtoclient.PatientAppointListClientDTO;
import com.djb.ylt.user.dtoclient.PatientAppointListDTO;
import com.djb.ylt.user.dtoclient.PatientClientDTO;
import com.djb.ylt.user.dtoclient.PrivateDoctorClientDTO;
import com.djb.ylt.user.dtoclient.PrivateDoctorListDTO;
import com.djb.ylt.user.dtoclient.RecordsClientDTO;
import com.djb.ylt.user.dtoclient.RecordsInfoDTO;
import com.djb.ylt.user.dtoclient.RecordsInfoDetailDTO;
import com.djb.ylt.user.entity.AppointInquiryEntity;
import com.djb.ylt.user.entity.DoctorCommentEntity;
import com.djb.ylt.user.entity.DoctorEntity;
import com.djb.ylt.user.entity.DoctorInquiryViewEntity;
import com.djb.ylt.user.entity.DoctorScheduleEntity;
import com.djb.ylt.user.entity.GraphicEntity;
import com.djb.ylt.user.entity.InterrogationPackageEntity;
import com.djb.ylt.user.entity.PatientScheduleEntity;
import com.djb.ylt.user.entity.RecordsEntity;
import com.djb.ylt.user.service.IAppointInquiryService;
import com.djb.ylt.user.service.IDoctorCommentService;
import com.djb.ylt.user.service.IDoctorInqueryViewService;
import com.djb.ylt.user.service.IDoctorScheduleService;
import com.djb.ylt.user.service.IDoctorService;
import com.djb.ylt.user.service.IGraphicService;
import com.djb.ylt.user.service.IInterrogationPackageService;
import com.djb.ylt.user.service.IPatientScheduleService;
import com.djb.ylt.user.service.IRecordsService;
import com.djb.ylt.user.service.IUserLoginService;
import com.djb.ylt.wechat.Utils.HttpXmlUtils;
import com.djb.ylt.wechat.Utils.JdomParseXmlUtils;
import com.djb.ylt.wechat.Utils.RandCharsUtils;
import com.djb.ylt.wechat.Utils.WXSignUtils;
import com.djb.ylt.wechat.Utils.WeixinConfigUtils;
import com.djb.ylt.wechat.entity.Unifiedorder;
import com.djb.ylt.wechat.entity.UnifiedorderResult;
import com.djb.ylt.wechat.entity.WXPayResult;

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

@Controller("/Appoint")
public class AppointAction extends BaseAction {

	@Autowired
	@Qualifier("iAppointInquiryService")
	private IAppointInquiryService iAppointInquiryService;
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
	IDoctorInqueryViewService iDoctorInqueryViewService;

	@Autowired
	@Qualifier("iDoctorScheduleService")
	private IDoctorScheduleService iDoctorScheduleService;
	@Autowired
	@Qualifier("iUserLoginService")
	private IUserLoginService iUserLoginService;

	@Autowired
	@Qualifier("iDoctorService")
	private IDoctorService iDoctorService;

	@Autowired
	@Qualifier("iInterrogationPackageService")
	private IInterrogationPackageService iInterrogationPackageService;

	@Autowired
	@Qualifier("iPatientScheduleService")
	private IPatientScheduleService iPatientScheduleService;

	@Autowired
	@Qualifier("iTestResultMasterService")
	private ITestResultMasterService iTestResultMasterService;

	@Autowired
	@Qualifier("iDetectAnalysisService")
	private IDetectAnalysisService iDetectAnalysisService;

	@Autowired
	@Qualifier("iGraphicService")
	private IGraphicService iGraphicService;
	
	public AppointAction() {
		super();
	}

	/**
	 * 获取我的私人医生列表：获取我购买的私人医生 通过用户ID获取
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
	public ActionForward doGetPrivateDoctor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		AppointInquiryEntity paramEntity = new AppointInquiryEntity();

		// 结果ClientDTO
		PrivateDoctorListDTO resultClientDTO = new PrivateDoctorListDTO();
		// 结果DTO
		List<PrivateDoctorClientDTO> privateDoctorList = null;
		// paramDTO.setStartRow(1);
		// paramDTO.setEndRow(10);
		// 参数DTO->参数Entity
		// paramDTO.setPageNum(1);
		// paramDTO.setPageSize(10);
		CommonUtil.reflectClass(paramDTO, paramEntity);
		paramEntity.setPageSize(paramDTO.getPageSize());
		paramEntity.setPageNum(paramDTO.getPageNum());
		// 结果Entity
		List<AppointInquiryEntity> resultEntity = null;

		// DB查询
		try {

			resultEntity = iAppointInquiryService.getAppointInquiryList(paramEntity);

			if (resultEntity != null && resultEntity.size() > 0) {
				privateDoctorList = new ArrayList<PrivateDoctorClientDTO>();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				for (AppointInquiryEntity param : resultEntity) {

					PrivateDoctorClientDTO paramClientDTO = new PrivateDoctorClientDTO();
					paramClientDTO.setAppointId(param.getAppointId());
					paramClientDTO.setPayStatus(param.getPayStatus());
					paramClientDTO.setAliPaySignInfo(param.getPayInfo());
					if (param.getInterrogationPackageEntity() != null) {
						paramClientDTO.setTotal(param.getInterrogationPackageEntity().getTotal());
						// 创建时间
						// 计算套餐剩余时间
						if (param.getBeginTime() != null
								&& param.getInterrogationPackageEntity().getEffectTime() != null) {
							Date BeginDate = sdf.parse(sdf.format(param.getBeginTime()));
							Date nowDate = sdf.parse(sdf.format(new Date()));
							Calendar cal = Calendar.getInstance();
							cal.setTime(BeginDate);
							long time1 = cal.getTimeInMillis();
							cal.setTime(nowDate);
							long time2 = cal.getTimeInMillis();
							long between_days = (time2 - time1) / (1000 * 3600 * 24);
							// 计算套餐的剩余时间
							int effectTime = Integer.parseInt(param.getInterrogationPackageEntity().getEffectTime());
							if (Integer.parseInt(String.valueOf(between_days)) >= 0) {
								paramClientDTO
										.setEffectTime(effectTime - Integer.parseInt(String.valueOf(between_days)));
							} else {
								paramClientDTO.setEffectTime(effectTime);
							}
							if (paramClientDTO.getEffectTime() < 0) {
								paramClientDTO.setEffectTime(0);
							}
						}
						//
						paramClientDTO.setCreateTime(CommonUtil.dateToString(param.getInsertTime(), "yyyy-MM-dd"));
						// 0视频 获取私人医生视频问诊使用几次

						int vioCount = param.getVioCount();

						paramClientDTO.setVio_num(param.getInterrogationPackageEntity().getCount() - vioCount);
						// 1电话 获取私人医生电话问诊使用几次

						int telCount = param.getTCount();
						paramClientDTO.setTel_num(param.getInterrogationPackageEntity().getTelCount() - telCount);
					}
					// 获取科室信息 通过医生表里的科室ID获取科室信息
					// 参数Entity
					if (param.getDoctorEntity() != null) {

						// if (param.getDoctorEntity().getDepartmentEntity() !=
						// null) {
						// paramClientDTO
						// .setDepartmentName(param.getDoctorEntity().getDepartmentEntity().getDepName());
						// }
						// 医生信息
						paramClientDTO.setDoctorId(param.getDoctorEntity().getDoctorId());
						paramClientDTO.setHealDisease(param.getDoctorEntity().getHealDisease());
						paramClientDTO.setName(param.getDoctorEntity().getName());
						paramClientDTO.setHeadPic(param.getDoctorEntity().getHeadPic());
						paramClientDTO.setIntroduction(param.getDoctorEntity().getIntroduction());
						paramClientDTO.setPositional(param.getDoctorEntity().getPositional());
						paramClientDTO.setDepartmentId(param.getDoctorEntity().getDepartmentId());
						paramClientDTO.setHospitalName(param.getDoctorEntity().getHospitalName());
						paramClientDTO.setDepartmentName(param.getDoctorEntity().getDepartmentName());
						paramClientDTO.setDcName(param.getDoctorEntity().getDcName());

						// 获取医生电话：通过医生表里的用户Id获取医生的登录账号

						if (param.getDoctorEntity().getUserLoginEntity() != null) {
							paramClientDTO.setTel(param.getDoctorEntity().getUserLoginEntity().getUserTel());
						}
					}
					// 更新支付状态，预约的创建时间的一小时之后更新状态
					// if ("0".equals(param.getPayStatus())) {
					// String flag = this.doUpDatePayInfo(param.getInsertTime(),
					// param.getAppointId());
					// if ("1".equals(flag)) {
					// paramClientDTO.setPayStatus("2");
					// }
					// }
					privateDoctorList.add(paramClientDTO);
				}
				resultClientDTO.setDoctorList(privateDoctorList);

			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-1060");

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
	 * 获取我的私人医生信息
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
	public ActionForward doGetPrivateDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		AppointInquiryEntity paramEntity = new AppointInquiryEntity();

		// 结果ClientDTO
		PrivateDoctorClientDTO resultClientDTO = new PrivateDoctorClientDTO();
		List<RecordsClientDTO> recordsClientDTOList = null;

		// 参数DTO->参数Entity
		CommonUtil.reflectClass(paramDTO, paramEntity);
		// test
		// paramEntity.setAppointId(1);
		// 结果Entity
		List<AppointInquiryEntity> resultEntity = null;

		// DB查询
		try {
			resultEntity = iAppointInquiryService.getPrivateDoctorList(paramEntity);

			if (resultEntity != null && resultEntity.size() > 0) {
				// 获取医生和私人医生套餐的信息
				recordsClientDTOList = new ArrayList<RecordsClientDTO>();
				AppointInquiryEntity paramAppoint = resultEntity.get(0);
				PrivateDoctorClientDTO paramClientDTO = new PrivateDoctorClientDTO();
				if (paramAppoint.getInterrogationPackageEntity() != null) {
					paramClientDTO.setTotal(paramAppoint.getInterrogationPackageEntity().getTotal());
					// 创建时间
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					// 计算套餐剩余时间
					if (paramAppoint.getBeginTime() != null
							&& paramAppoint.getInterrogationPackageEntity().getEffectTime() != null) {
						Date BeginDate = sdf.parse(sdf.format(paramAppoint.getBeginTime()));
						Date nowDate = sdf.parse(sdf.format(new Date()));
						Calendar cal = Calendar.getInstance();
						cal.setTime(BeginDate);
						long time1 = cal.getTimeInMillis();
						cal.setTime(nowDate);
						long time2 = cal.getTimeInMillis();
						long between_days = (time2 - time1) / (1000 * 3600 * 24);
						int effectTime = Integer.parseInt(paramAppoint.getInterrogationPackageEntity().getEffectTime());
						if (Integer.parseInt(String.valueOf(between_days)) >= 0) {
							paramClientDTO.setEffectTime(effectTime - Integer.parseInt(String.valueOf(between_days)));
						} else {
							paramClientDTO.setEffectTime(effectTime);
						}
						if (paramClientDTO.getEffectTime() < 0) {
							paramClientDTO.setEffectTime(0);
						}
					}

					// paramClientDTO.setEffectTime(paramAppoint.getInterrogationPackageEntity().getEffectTime());

					// 类型 0 视频 获取使用几次视频问诊
					RecordsEntity recordEntity = new RecordsEntity();
					recordEntity.setAppointId(paramAppoint.getAppointId());
					recordEntity.setRecordsType("0");

					int vioCount = iRecordsService.getCount(recordEntity);
					paramClientDTO.setVio_num(paramAppoint.getInterrogationPackageEntity().getCount() - vioCount);
					// 类型 1 电话 获取使用几次电话问诊
					RecordsEntity recordEntity1 = new RecordsEntity();
					recordEntity1.setAppointId(paramAppoint.getAppointId());
					recordEntity1.setRecordsType("1");
					int telCount = iRecordsService.getCount(recordEntity1);
					paramClientDTO.setTel_num(paramAppoint.getInterrogationPackageEntity().getTelCount() - telCount);
				}
				// 获取科室信息
				// 参数Entity
				if (paramAppoint.getDoctorEntity() != null) {

					// 医生信息
					paramClientDTO.setDoctorId(paramAppoint.getDoctorEntity().getDoctorId());
					paramClientDTO.setHealDisease(paramAppoint.getDoctorEntity().getHealDisease());
					paramClientDTO.setName(paramAppoint.getDoctorEntity().getName());
					paramClientDTO.setDcName(paramAppoint.getDoctorEntity().getDcName());
					paramClientDTO.setDepartmentName(paramAppoint.getDoctorEntity().getDepartmentName());
					//
					// String hostIp = ResourceLocator.getI18NMessage(this,
					// Constants.MSG_KEY_HOST_ADDRESS);
					paramClientDTO.setHeadPic(paramAppoint.getDoctorEntity().getHeadPic());
					paramClientDTO.setIntroduction(paramAppoint.getDoctorEntity().getIntroduction());
					paramClientDTO.setPositional(paramAppoint.getDoctorEntity().getPositional());
					paramClientDTO.setHospitalName(paramAppoint.getDoctorEntity().getHospitalName());
					// 医生评分

					// 获取平均评分
					DoctorCommentEntity commenEntity = new DoctorCommentEntity();
					DoctorCommentEntity commenresultEntity = new DoctorCommentEntity();

					commenEntity.setDoctorId(paramAppoint.getDoctorEntity().getDoctorId());
					commenresultEntity = iDoctorCommentService.getAverageGrade(commenEntity);
					if (commenresultEntity != null && commenresultEntity.getGrade() != null) {
						resultClientDTO.setCommentGrade((float) (Math.round(commenresultEntity.getGrade() * 10)) / 10);
					}

					// 获取医生评论条数
					DoctorCommentEntity doctorComment = new DoctorCommentEntity();
					doctorComment.setDoctorId(paramAppoint.getDoctorEntity().getDoctorId());
					// 结果Entity
					int commentCount = iDoctorCommentService.getCount(doctorComment);
					if (commentCount != 0) {
						paramClientDTO.setCommentCount(commentCount);
					}
				}
				for (AppointInquiryEntity param : resultEntity) {
					// 就诊记录信息赋值
					if (param.getRecordsEntitys() != null && param.getRecordsEntitys().size() > 0) {

						for (RecordsEntity record : param.getRecordsEntitys()) {
							if (record.getRecordsId() != null) {
								RecordsClientDTO recordsparam = new RecordsClientDTO();
								// recordsparam.setAppointId(record.getAppointId());
								recordsparam.setRecordsId(record.getRecordsId());
								// 预约时间格式化
								if (record.getAppointTime() != null) {
									recordsparam.setAppointTime(
											CommonUtil.dateToString(record.getAppointTime(), "yyyy-MM-dd HH:mm"));
								}
								if (record.getCreateTime() != null) {
									recordsparam.setCreateTime(
											CommonUtil.dateToString(record.getCreateTime(), "yyyy-MM-dd HH:mm"));
								}
								recordsparam.setRecordsType(record.getRecordsType());
								// 判断时间和状态
								// 视频问诊在医生确认后 一个小时后更新状态未出诊断结果
								// if ("1".equals(record.getInquiryStatus()) &&
								// "0".equals(record.getRecordsType())) {
								// if (record.getAppointTime() != null) {
								// Date date = null;
								// Date nowDate = new Date();
								// Calendar cal = Calendar.getInstance();
								// cal.setTime(record.getAppointTime());
								// cal.add(Calendar.MINUTE, 60);// 24小时制
								// date = cal.getTime();
								// // 当前时间大于预约时间则更改状态为未出结果
								// int i = nowDate.compareTo(date);
								// if (i > 0) {
								// // 更新状态
								// RecordsEntity paramRecordsEntity = new
								// RecordsEntity();
								// paramRecordsEntity.setRecordsId(record.getRecordsId());
								// paramRecordsEntity.setInquiryStatus("9");
								// iRecordsService.updateRecords(paramRecordsEntity);
								// recordsparam.setInquiryStatus("9");
								// } else {
								// recordsparam.setInquiryStatus(record.getInquiryStatus());
								// }
								// }
								// } else {
								// recordsparam.setInquiryStatus(record.getInquiryStatus());
								// }
								recordsparam.setInquiryStatus(record.getInquiryStatus());
								recordsClientDTOList.add(recordsparam);
							}
						}
					}

				}
				CommonUtil.reflectClass(paramClientDTO, resultClientDTO);
				resultClientDTO.setRecordsList(recordsClientDTOList);

			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-1061");

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

	public ActionForward doGetAppointListByPatient(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		AppointInquiryEntity paramEntity = new AppointInquiryEntity();

		// 结果ClientDTO
		PatientAppointListClientDTO resultClientDTO = new PatientAppointListClientDTO();
		List<PatientAppointClientDTO> patientAppointList = null;

		// 参数DTO->参数Entity
		CommonUtil.reflectClass(paramDTO, paramEntity);
		paramEntity.setPageSize(paramDTO.getPageSize());
		paramEntity.setPageNum(paramDTO.getPageNum());
		// 结果Entity
		List<AppointInquiryEntity> resultEntity = null;

		// DB查询
		try {
			resultEntity = iAppointInquiryService.getAppointListByPatient(paramEntity);

			if (resultEntity != null && resultEntity.size() > 0) {
				patientAppointList = new ArrayList<PatientAppointClientDTO>();
				for (AppointInquiryEntity param : resultEntity) {
					PatientAppointClientDTO paramClientDTO = new PatientAppointClientDTO();
					// 获取科室信息
					// 参数Entity
					paramClientDTO.setPayStatus(param.getPayStatus());
					paramClientDTO.setAliPaySignInfo(param.getPayInfo());
					paramClientDTO.setBuyTotal(param.getBuyTotal());
					if (param.getDoctorEntity() != null) {
						// 医生信息
						paramClientDTO.setDoctorId(param.getDoctorEntity().getDoctorId());
						paramClientDTO.setHealDisease(param.getDoctorEntity().getHealDisease());
						paramClientDTO.setName(param.getDoctorEntity().getName());
						paramClientDTO.setHeadPic(param.getDoctorEntity().getHeadPic());
						paramClientDTO.setIntroduction(param.getDoctorEntity().getIntroduction());
						paramClientDTO.setPositional(param.getDoctorEntity().getPositional());
						paramClientDTO.setHospitalName(param.getDoctorEntity().getHospitalName());
						paramClientDTO.setTel(param.getDoctorEntity().getUserLoginEntity().getUserTel());
						paramClientDTO.setDepartmentName(param.getDoctorEntity().getDepartmentName());
						paramClientDTO.setDcName(param.getDoctorEntity().getDcName());
						paramClientDTO.setDepartmentName(param.getDoctorEntity().getDepartmentName());
						paramClientDTO.setDcName(param.getDoctorEntity().getDcName());
						paramClientDTO.setTel(param.getDoctorEntity().getDoctorTel());
					}

					if (param.getRecordsEntitys() != null && param.getRecordsEntitys().size() > 0) {
						// 预约记录
						for (RecordsEntity record : param.getRecordsEntitys()) {

							PatientAppointClientDTO recordsparam = new PatientAppointClientDTO();
							// recordsparam.setAppointId(record.getAppointId());
							recordsparam.setRecordsId(record.getRecordsId());
							if (record.getAppointTime() != null) {
								recordsparam.setAppointTime(
										CommonUtil.dateToString(record.getAppointTime(), "yyyy-MM-dd HH:mm"));
							}
							if (record.getCreateTime() != null) {
								recordsparam.setCreateTime(
										CommonUtil.dateToString(record.getCreateTime(), "yyyy-MM-dd HH:mm"));
							}

							recordsparam.setRecordsType(record.getRecordsType());

							recordsparam.setInquiryStatus(record.getInquiryStatus());
							CommonUtil.reflectClass(paramClientDTO, recordsparam);
							patientAppointList.add(recordsparam);
						}
					}
					resultClientDTO.setPatientAppointList(patientAppointList);

				}
			}

		} catch (Exception e) {
			// paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-1070");

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
	 * 预约问诊
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
	public ActionForward doInsertAppoint(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		// paramDTO.setPayType("1");
		AppointInquiryEntity paramEntity = new AppointInquiryEntity();

		// 结果ClientDTO
		AppointInsertClientDTO resultClientDTO = new AppointInsertClientDTO();
		// test 0:视频1电话2私人
		// 参数DTO->参数Entity
		// 问诊票json转换
		if (paramDTO.getInqueryQuestionList() != null && !"".equals(paramDTO.getInqueryQuestionList())) {
			paramEntity = this.getJsonStringToObject(paramDTO);
		}
		CommonUtil.reflectClass(paramDTO, paramEntity);
		if (paramDTO.getTotalFee() != null) {
			paramEntity.setBuyTotal(Double.parseDouble(paramDTO.getTotalFee()));
			BigDecimal price = new BigDecimal(paramDTO.getTotalFee());
			BigDecimal mul = new BigDecimal("0.7");// 医生获取0.7的比例
			BigDecimal result = price.multiply(mul);// 相乘结果
			System.out.println(result);
			BigDecimal one = new BigDecimal("1");
			double doctorTotal = result.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue();// 保留1位数
			paramEntity.setDoctorTotal(doctorTotal);
		}

		paramEntity.setWorkType(paramDTO.getWorkType());
		// 更新日程
		// 视频的时候更新日程信息

		if ("0".equals(paramEntity.getAppointType()) && paramEntity.getScheduleId() != null) {
			// 参数Entity
			// 视频更新日程
			if (paramEntity.getScheduleId() != null) {
				DoctorScheduleEntity scheduleEntity = new DoctorScheduleEntity();
				scheduleEntity.setScheduleId(paramEntity.getScheduleId());
				DoctorScheduleEntity resultSchelduleEntity = new DoctorScheduleEntity();
				resultSchelduleEntity = iDoctorScheduleService.getObject(scheduleEntity);
				if (resultSchelduleEntity != null) {
					if (resultSchelduleEntity.getAppointNum() >= resultSchelduleEntity.getPatientNum()) {
						resultClientDTO.setReturnCode("-1041");
						toJson(response, resultClientDTO);
						return null;
					}

				}
			}
		}
		// 私人医生开始时间格式化
		if (paramDTO.getBeginTime() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			paramEntity.setBeginTime(sdf.parse(paramDTO.getBeginTime()));
		}
		// 预约时间格式化
		if (paramDTO.getAppointTime() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			paramEntity.setAppointTime(sdf.parse(paramDTO.getAppointTime()));
			paramEntity.setAppointDate(paramDTO.getAppointTime().substring(0, 7));
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
		}
		// 自己填写的备注会放在医生看到的症状描述里面
		if (paramDTO.getMemo() != null && !"".equals(paramDTO.getMemo())) {
			describe.append(paramDTO.getMemo());
		}
		if (describe != null && describe.length() > 0) {
			paramEntity.setSymptonDescribe(describe.toString());
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
			// 0:视频1电话2私人
			// 日期时间戳
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			StringBuffer stringBuffer = new StringBuffer();
			String str = sdf.format(date);
			stringBuffer.append(str);
			// 订单号（程序自动生成）
			OrderNumberUtil orderNumberUtil = new OrderNumberUtil();
			// 采番订单编号
			stringBuffer.append(orderNumberUtil.getOrderNumber(request));
			paramEntity.setOrderNumber(stringBuffer.toString());
			// 如果是0元设置成下单直接是支付状态
			if ("0".equals(paramDTO.getTotalFee()) || "0.0".equals(paramDTO.getTotalFee())
					|| "0.00".equals(paramDTO.getTotalFee())) {
				paramEntity.setPayStatus("1");
			}
			// 录入订单
			iAppointInquiryService.addAppointInquiry(paramEntity);
			// RecordsEntity recordsEntity = new RecordsEntity();
			// 视频 电话
			// if ("0".equals(paramDTO.getAppointType()) ||
			// "1".equals(paramDTO.getAppointType())) {
			if (paramEntity.getAppointId() == null) {
				paramDTO.setErrFlg(true);
				resultClientDTO.setReturnCode("-1040");
			}
			if (paramEntity.getAppointId() != null && paramEntity.getAppointId() != 0) {
				if (!"0".equals(paramDTO.getTotalFee()) && !"0.0".equals(paramDTO.getTotalFee())
						&& !"0.00".equals(paramDTO.getTotalFee())) {
					// resultClientDTO.setOrderNumber(paramEntity.getOrderNumber());
					resultClientDTO.setAppointId(paramEntity.getAppointId());
					String hostIp = ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS);
					// 支付宝支付
					if ("0".equals(paramDTO.getPayType())) {
						StringBuffer url = new StringBuffer();
						url.append(hostIp);
						url.append("Appoint.do?" + "receiveNotify" + "=");
						// url.append("&orderNumber=");
						url.append(stringBuffer.toString());
						String service_url = url.toString();
						String subject = "";
						if ("0".equals(paramDTO.getAppointType())) {
							subject = "购买视频问诊";
						} else if ("1".equals(paramDTO.getAppointType())) {
							subject = "购买电话问诊";
						} else {
							subject = "购买私人医生";
						}
						String orderContent = AsynAlipayNotifyAction.getOrderInfo(stringBuffer.toString(), subject,
								"支付宝支付", paramDTO.getTotalFee(), service_url);
						String sign = sign(orderContent);
						try {
							// 签名
							sign = URLEncoder.encode(sign, "UTF-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						StringBuffer sb = new StringBuffer();
						sb.append(orderContent);
						sb.append("&sign=\"" + sign + "\"&");
						sb.append("sign_type=\"RSA\"");
						resultClientDTO.setAliPaySignInfo(sb.toString());
						AppointInquiryEntity payInfoEntity = new AppointInquiryEntity();
						payInfoEntity.setAppointId(paramEntity.getAppointId());
						payInfoEntity.setOrderNumber(paramEntity.getOrderNumber());
						payInfoEntity.setPayInfo(sb.toString());
						payInfoEntity.setPayType("0");
						iAppointInquiryService.updateAppointInquiry(payInfoEntity);
					} else if ("1".equals(paramDTO.getPayType())) {// 微信支付
						// 构造xml参数
						Unifiedorder unifiedorder = this.weChatParam(stringBuffer.toString(), "购买视频问诊",
								paramDTO.getTotalFee(), request);

						String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);

						String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

						String method = "POST";

						String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();

						UnifiedorderResult unifiedorderResult = JdomParseXmlUtils.getUnifiedorderResult(weixinPost);

						if ("SUCCESS".equals(unifiedorderResult.getReturn_code())) {
							// 再次验签
							UnifiedorderResult result = this.weChatParamAgain(unifiedorderResult);
							String reslutxml = GsonUtil.getJsonStringFormBean(result);
							// System.out.println("reslutxml>>>>" + reslutxml);
							String payInfo = HttpXmlUtils.xmlInfoJson(result);
							// System.out.println("payInfo>>>>" + payInfo);
							resultClientDTO.setAliPaySignInfo(reslutxml);
							AppointInquiryEntity payInfoEntity = new AppointInquiryEntity();
							payInfoEntity.setAppointId(paramEntity.getAppointId());
							payInfoEntity.setOrderNumber(paramEntity.getOrderNumber());
							payInfoEntity.setPayInfo(payInfo);
							payInfoEntity.setPayType("1");
							iAppointInquiryService.updateAppointInquiry(payInfoEntity);
						} else {
							paramDTO.setErrFlg(true);
							resultClientDTO.setReturnCode("-1040");
						}

						// System.out.println(xmlInfo);
						// ParseXMLUtils.jdomParseXml(weixinPost);
					}
				}
			} else {
				paramDTO.setErrFlg(true);
				resultClientDTO.setReturnCode("-1040");
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-1040");
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
	 * 修改
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
	public ActionForward doUpdatePayType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		// paramDTO.setPayType("1");
		// paramDTO.set
		AppointInquiryEntity paramEntity = new AppointInquiryEntity();

		// 结果ClientDTO
		AppointInsertClientDTO resultClientDTO = new AppointInsertClientDTO();
		// test 0:视频1电话2私人
		// 参数DTO->参数Entity
		paramEntity.setAppointId(paramDTO.getAppointId());

		// DB查询
		try {
			AppointInquiryEntity resultEntity = new AppointInquiryEntity();
			resultEntity = iAppointInquiryService.getObject(paramEntity);

			if (resultEntity != null) {
				if ("0".equals(resultEntity.getPayStatus())) {
					// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd
					// HH:mm:ss");
					// 未支付状态
					Date cdate = null;
					Date now = new Date();
					Calendar cal1 = Calendar.getInstance();
					if (resultEntity.getInsertTime() != null) {
						cal1.setTime(resultEntity.getInsertTime());
					}
					cal1.add(Calendar.MINUTE, 60);// 24小时制
					cdate = cal1.getTime();
					// 当前时间不大于创建时间+1小时才可以支付,否则不可以支付

					int time = now.compareTo(cdate);
					if (time <= 0) {
						resultClientDTO.setAppointId(paramDTO.getAppointId());
						// 如果支付方式是之前选择的
						if (paramDTO.getPayType().equals(resultEntity.getPayType())) {
							if ("0".equals(resultEntity.getPayType())) {
								resultClientDTO.setAliPaySignInfo(resultEntity.getPayInfo());
							} else if ("1".equals(resultEntity.getPayType())) {// 微信支付
								UnifiedorderResult unifiedorderResult = JdomParseXmlUtils
										.getUnifiedorderResult(resultEntity.getPayInfo());
								UnifiedorderResult result = new UnifiedorderResult();
								result.setAppid(unifiedorderResult.getAppid());
								// result.setMch_id(mch_id);

								result.setNoncestr(unifiedorderResult.getNoncestr());
								result.setPrepayid(unifiedorderResult.getPrepayid());
								result.setSign(unifiedorderResult.getSign());
								result.setTrade_type("APP");
								result.setResult_code(unifiedorderResult.getResult_code());
								result.setReturn_code(unifiedorderResult.getReturn_code());
								result.setReturn_msg(unifiedorderResult.getReturn_msg());
								result.setPartnerid(unifiedorderResult.getPartnerid());
								result.setTimestamp(unifiedorderResult.getTimestamp());
								result.setPackagesign("Sign=WXPay");
								String resultxml = GsonUtil.getJsonStringFormBean(result);
								resultClientDTO.setAliPaySignInfo(resultxml);
							}

						} else {
							// 生成订单号
							String hostIp = ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS);
							// 支付宝支付
							AppointInquiryEntity payInfoEntity = new AppointInquiryEntity();
							payInfoEntity.setAppointId(paramEntity.getAppointId());
							payInfoEntity.setOrderNumber(resultEntity.getOrderNumber());
							if ("0".equals(paramDTO.getPayType())) {
								StringBuffer url = new StringBuffer();
								url.append(hostIp);
								url.append("Appoint.do?" + "receiveNotify" + "=");
								// url.append("&orderNumber=");
								// url.append(stringBuffer.toString());
								String service_url = url.toString();
								String subject = "购买视频问诊";
								String orderContent = AsynAlipayNotifyAction.getOrderInfo(resultEntity.getOrderNumber(),
										subject, "支付宝支付", Double.toString(resultEntity.getBuyTotal()), service_url);
								String sign = sign(orderContent);
								try {
									// 签名
									sign = URLEncoder.encode(sign, "UTF-8");
								} catch (UnsupportedEncodingException e) {
									e.printStackTrace();
								}
								StringBuffer sb = new StringBuffer();
								sb.append(orderContent);
								sb.append("&sign=\"" + sign + "\"&");
								sb.append("sign_type=\"RSA\"");
								resultClientDTO.setAliPaySignInfo(sb.toString());

								payInfoEntity.setPayInfo(sb.toString());
								payInfoEntity.setPayType("0");

							} else if ("1".equals(paramDTO.getPayType())) {// 微信支付
								// 构造xml参数
								Unifiedorder unifiedorder = this.weChatParam(resultEntity.getOrderNumber(), "购买视频问诊",
										Double.toString(resultEntity.getBuyTotal()), request);

								String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);

								String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

								String method = "POST";

								String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();

								UnifiedorderResult unifiedorderResult = JdomParseXmlUtils
										.getUnifiedorderResult(weixinPost);

								if ("SUCCESS".equals(unifiedorderResult.getReturn_code())) {
									// 再次验签
									UnifiedorderResult result = this.weChatParamAgain(unifiedorderResult);
									String reslutxml = GsonUtil.getJsonStringFormBean(result);
									String payInfo = HttpXmlUtils.xmlInfoJson(result);
									resultClientDTO.setAliPaySignInfo(reslutxml);

									payInfoEntity.setPayInfo(payInfo);
									payInfoEntity.setPayType("1");

								} else {
									paramDTO.setErrFlg(true);
									resultClientDTO.setReturnCode("-7000");
								}

							}
							iAppointInquiryService.updateAppointInquiry(payInfoEntity);
						}
					} else {
						paramDTO.setErrFlg(true);
						resultClientDTO.setReturnCode("-7001");
					}
				}
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-7000");
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
	 * 获取医生统计记录
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
	public ActionForward doGetMonthRecords(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// paramDTO.setDoctorId(1);
		// paramDTO.setQueryDate("2017-01");
		// 参数Entity
		AppointInquiryEntity paramEntity = new AppointInquiryEntity();

		// 结果ClientDTO
		MonthRecordsClientDTO resultClientDTO = new MonthRecordsClientDTO();
		// 结果DTO
		// List<PrivateDoctorClientDTO> privateDoctorList = null;
		// paramDTO.setStartRow(1);
		// paramDTO.setEndRow(10);
		// 参数DTO->参数Entity
		// paramDTO.setPageNum(1);
		// paramDTO.setPageSize(10);
		// CommonUtil.reflectClass(paramDTO, paramEntity);
		// paramEntity.setPageSize(paramDTO.getPageSize());
		// paramEntity.setPageNum(paramDTO.getPageNum());
		// 结果Entity
		// List<AppointInquiryEntity> resultEntity = null;
		paramEntity.setAppointDate(paramDTO.getQueryDate());
		paramEntity.setDoctorId(paramDTO.getDoctorId());
		AppointInquiryEntity resultEntity = new AppointInquiryEntity();
		// DB查询
		try {
			resultEntity = iAppointInquiryService.getMonthRecords(paramEntity);
			if (resultEntity != null) {
				resultClientDTO.setAllCount(resultEntity.getAllCount());
				resultClientDTO.setdCount(resultEntity.getdCount());
				resultClientDTO.setnCount(resultEntity.getnCount());
				resultClientDTO.seteCount(resultEntity.geteCount());
				resultClientDTO.setTotal(resultEntity.getTotal());
				resultClientDTO.setnTotal(resultEntity.getnTotal());
				resultClientDTO.setdTotal(resultEntity.getdTotal());
				resultClientDTO.seteTotal(resultEntity.geteTotal());
				// CommonUtil.reflectClass(resultEntity, resultClientDTO);
			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-7020");

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
	 * 医生获取首页的患者预约列表
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
	public ActionForward doGetAppointListByDoctor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 视频用Entity
		DoctorInquiryViewEntity paramEntity = new DoctorInquiryViewEntity();
		// 图文用Entity
		AppointInquiryEntity paramImageEntity = new AppointInquiryEntity();
		// 视频用结果ClientDTO
		PatientAppointListDTO resultClientDTO = new PatientAppointListDTO();
		List<PatientClientDTO> patientClientList = null;
		// 图文用结果ClientDTO
		AppointInquiryGraphicClientDTO resultImageClientDTO = 
				new AppointInquiryGraphicClientDTO();
		List<AppointInquiryGraphicClientDTO> appointGraphicClientList = null;
		// List<SymptomClientDTO> SymptomClientDTOList=null;
		// 参数DTO->参数Entity
		// 视频用
		paramEntity.setDoctorId(paramDTO.getDoctorId());
		// 图文用
		paramImageEntity.setDoctorId(paramDTO.getDoctorId());
		// paramEntity.setDoctorId(1);

		// 视频用结果Entity
		List<DoctorInquiryViewEntity> resultEntity = null;
		// 图文用结果Entity
		List<AppointInquiryEntity> imageResultEntity = null;
		// 结果DTO
		// AppointInquiryDTO resultDTO=new AppointInquiryDTO();
		// DB查询
		try {
			// 视频
			if ("0".equals(paramDTO.getType())) {
				resultEntity = iDoctorInqueryViewService.getInqueryViewList(paramEntity);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if (resultEntity != null && resultEntity.size() > 0) {
					patientClientList = new ArrayList<PatientClientDTO>();
					for (DoctorInquiryViewEntity param : resultEntity) {
						PatientClientDTO paramClientDTO = new PatientClientDTO();
						paramClientDTO.setPayStatus(param.getPayStatus());
						// 获取症状名称
						paramClientDTO.setSymptonName(param.getSymptomName());

						paramClientDTO.setDepName(param.getDepName());
						paramClientDTO.setQaTime(param.getQaTime());
						// 获取患者信息
						// 参数Entity
						paramClientDTO.setPatientId(param.getPatientId());
						paramClientDTO.setName(param.getName());
						if (param.getBirth() != null) {
							paramClientDTO.setBirth(sdf.format(param.getBirth()));
						}
						paramClientDTO.setSex(param.getSex());
						paramClientDTO.setPatientPic(param.getPatientPic());
						if (param.getUserLoginEntity() != null) {
							paramClientDTO.setTel(param.getUserLoginEntity().getUserTel());
						}

						// 预约记录
						paramClientDTO.setAppointId(param.getAppointId());
						paramClientDTO.setUserCode(param.getUserCode());
						paramClientDTO.setRecordsId(param.getRecordsId());
						// 电话问诊
						paramClientDTO.setTelNum(param.getTelNum());
						if (param.getAppointTime() != null) {
							paramClientDTO
									.setAppointTime(CommonUtil.dateToString(param.getAppointTime(), "yyyy-MM-dd HH:mm"));
						}
						if (param.getCreateTime() != null) {
							paramClientDTO
									.setCreateTime(CommonUtil.dateToString(param.getCreateTime(), "yyyy-MM-dd HH:mm"));
						}
						paramClientDTO.setRecordsType(param.getRecordsType());

						paramClientDTO.setInquiryStatus(param.getInquiryStatus());

						patientClientList.add(paramClientDTO);
					}
					resultClientDTO.setPatientClientDTO(patientClientList);
				}
			}
			
			// 图文
			if ("1".equals(paramDTO.getType())) {
				imageResultEntity = iAppointInquiryService
						.getAppointListByImage(paramImageEntity);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				Date now = new Date();
				calendar.setTime(now);
				calendar.add(Calendar.DATE, -1);
				if (imageResultEntity != null && imageResultEntity.size() > 0 
						&& imageResultEntity.get(0) != null) {
					appointGraphicClientList = 
							new ArrayList<AppointInquiryGraphicClientDTO>();
					for (AppointInquiryEntity param : imageResultEntity) {
						AppointInquiryGraphicClientDTO paramClientDTO = 
								new AppointInquiryGraphicClientDTO();
						// 现在系统时间
						String date1 = sdf.format(calendar.getTime());
						Date systemDate = sdf.parse(date1);
						// 最新的聊天时间
						String date2 = "";
						Date maxRecordTime = new Date();
						if (param.getMaxChatTime() != null 
								&& !"".equals(param.getMaxChatTime())) {
							date2 = sdf.format(param.getMaxChatTime());
							maxRecordTime = sdf.parse(date2);
						} 
						// 是否免费
						String freeFlag = param.getFreeFlag();
						// 是否是随访
						String followupFlag = param.getFollowUpFlag();
						// 聊天记录条数
						int count = param.getCount();
						// 是否免费判断
						if ("1".equals(freeFlag) && !"1".equals(followupFlag)) {
							// 图文聊天条数
							if (count >= param.getFreeTotal()) {
								continue;
							} else {
								// 聊天时间判定
								if (maxRecordTime.compareTo(systemDate) <= 0) {
									continue;
								} else {
									// 剩余免费浏览次数
									int freeTotal = param.getFreeTotal();
									paramClientDTO.setSupTotal(String.valueOf(freeTotal - count));
									// 预约就诊ID
									paramClientDTO.setAppointId(
											String.valueOf(param.getAppointId()));
									// 患者ID
									paramClientDTO.setPatientId(
											param.getPatientId());
									// 患者头像
									paramClientDTO.setPatientPic(
											param.getPatientPic());
									// 患者姓名
									paramClientDTO.setPatientName(
											param.getPatientName());
									// 年龄
									if (param.getAge() != null) {
										paramClientDTO.setBirth(sdft.format(
												param.getAge()));
									}
									// 患者电话
									paramClientDTO.setPatientTel(
											param.getPatientEntity().getPatientTel());
									// 性别
									paramClientDTO.setSex(param.getSex());
									
									// 科室
									paramClientDTO.setDcName(param
											.getDoctorEntity().getDcName());				
									// 最后访问时间
									if (param.getMaxChatTime() != null 
											&& !"".equals(param.getMaxChatTime())) {
										paramClientDTO.setMaxChatTime(sdf.format(param.getMaxChatTime()));
									}
									if (param.getGraphicEntitys() != null 
											&&param.getGraphicEntitys().size() > 0) {
										// 图文内容
										paramClientDTO.setGraphicContent(
												param.getGraphicEntitys().get(0).getGraphicContent());
										// 角色
										paramClientDTO.setRole(
												param.getGraphicEntitys().get(0).getRole());
										// 类型
										paramClientDTO.setGraphicType(
												param.getGraphicEntitys().get(0).getGraphicType());
									}
									// 是否免费
									paramClientDTO.setFreeFlag(param.getFreeFlag());
									// 是否是随访
									paramClientDTO.setFollowUpFlag(param.getFollowUpFlag());
								}
							}
						} else {
							// 剩余免费浏览次数
							if (param.getFreeTotal() != null) {
								int freeTotal = param.getFreeTotal();
								paramClientDTO.setSupTotal(String.valueOf(freeTotal - count));
							} else {
								paramClientDTO.setSupTotal("0");
							}
							// 预约就诊ID
							paramClientDTO.setAppointId(
									String.valueOf(param.getAppointId()));
							// 患者ID
							paramClientDTO.setPatientId(
									param.getPatientId());
							// 患者头像
							paramClientDTO.setPatientPic(
									param.getPatientPic());
							// 患者姓名
							paramClientDTO.setPatientName(
									param.getPatientName());
							// 年龄
							if (param.getAge() != null) {
								paramClientDTO.setBirth(sdft.format(
										param.getAge()));
							}
							// 患者电话
							paramClientDTO.setPatientTel(
									param.getPatientEntity().getPatientTel());
							// 性别
							paramClientDTO.setSex(param.getSex());
							
							// 科室
							paramClientDTO.setDcName(param
									.getDoctorEntity().getDcName());				
							// 最后访问时间
							if (param.getMaxChatTime() != null 
									&& !"".equals(param.getMaxChatTime())) {
								paramClientDTO.setMaxChatTime(sdf.format(param.getMaxChatTime()));
							}
							if (param.getGraphicEntitys() != null 
									&&param.getGraphicEntitys().size() > 0) {
								// 图文内容
								paramClientDTO.setGraphicContent(
										param.getGraphicEntitys().get(0).getGraphicContent());
								// 角色
								paramClientDTO.setRole(
										param.getGraphicEntitys().get(0).getRole());
								// 类型
								paramClientDTO.setGraphicType(
										param.getGraphicEntitys().get(0).getGraphicType());
							}
							// 是否免费
							paramClientDTO.setFreeFlag(param.getFreeFlag());
							// 是否是随访
							paramClientDTO.setFollowUpFlag(param.getFollowUpFlag());
						}
						appointGraphicClientList.add(paramClientDTO);
					}
					resultImageClientDTO.setAppointGraphicClientList(
							appointGraphicClientList);
				}
			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			if ("0".equals(paramDTO.getType())) {
				resultClientDTO.setReturnCode("-2000");
			} else {
				resultImageClientDTO.setReturnCode("-2000");
			}
		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			if ("0".equals(paramDTO.getType())) {
				resultClientDTO.setReturnCode("0");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				resultClientDTO.setSysTime(sdf.format(new Date()));
			} else {
				resultImageClientDTO.setReturnCode("0");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				resultImageClientDTO.setSysTime(sdf.format(new Date()));
			}
		}
		// 返回结果
		if ("0".equals(paramDTO.getType())) {
			toJson(response, resultClientDTO);
		} else {
			toJson(response, resultImageClientDTO);
		}

		return null;
	}

	/**
	 * 医生获取所有患者
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
	public ActionForward doGetMinutePushInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		List<AppointInquiryEntity> paramEntityList = new ArrayList<AppointInquiryEntity>();

		// 结果ClientDTO
		// PatientAppointListDTO resultClientDTO = new PatientAppointListDTO();
		List<AppoinPushDTO> appoinPushDTOList = null;
		AppoinPushDTOList appoinPushInfo = new AppoinPushDTOList();
		// DB查询
		try {
			paramEntityList = iAppointInquiryService.getInfoForMinutesPush();
			if (paramEntityList != null && paramEntityList.size() > 0) {
				appoinPushDTOList = new ArrayList<AppoinPushDTO>();
				for (AppointInquiryEntity param : paramEntityList) {
					AppoinPushDTO paramClientDTO = new AppoinPushDTO();
					// 获取症状名称
					paramClientDTO.setAppointId(param.getAppointId());
					if (param.getPatientEntity() != null) {
						paramClientDTO.setPatientId(param.getPatientEntity().getPatientId());
						paramClientDTO.setPatientTel(param.getPatientEntity().getPatientTel());
					}
					if (param.getDoctorEntity() != null) {
						paramClientDTO.setDoctorId(param.getDoctorEntity().getDoctorId());
						paramClientDTO.setDoctorTel(param.getDoctorEntity().getDoctorTel());
					}
					List<RecordsClientDTO> recordsList = null;
					if (param.getRecordsEntitys() != null && param.getRecordsEntitys().size() > 0) {
						recordsList = new ArrayList<RecordsClientDTO>();
						for (RecordsEntity records : param.getRecordsEntitys()) {

							RecordsClientDTO recordsClient = new RecordsClientDTO();
							recordsClient.setRecordsId(records.getRecordsId());
							recordsClient.setPatientFlag(records.getPatientFlag());
							recordsClient.setDoctorFlag(records.getDoctorFlag());
							recordsList.add(recordsClient);
						}
						paramClientDTO.setRecordsClientList(recordsList);
					}
					appoinPushDTOList.add(paramClientDTO);
				}
				appoinPushInfo.setAppoinPushList(appoinPushDTOList);
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			appoinPushInfo.setReturnCode("-21000");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			appoinPushInfo.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, appoinPushInfo);

		return null;
	}

	/**
	 * 医生获取会员
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
	public ActionForward doGetMembers(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		AppointInquiryEntity paramEntity = new AppointInquiryEntity();

		// 结果ClientDTO
		PatientAppointListDTO resultClientDTO = new PatientAppointListDTO();
		List<PatientClientDTO> patientClientList = null;
		// List<SymptomClientDTO> SymptomClientDTOList=null;
		// 参数DTO->参数Entity

		// CommonUtil.reflectClass(paramDTO, paramEntity);
		paramEntity.setAppointType("2");
		paramEntity.setDoctorId(paramDTO.getDoctorId());
		// paramEntity.setPageSize(paramDTO.getPageSize());
		// paramEntity.setPageNum(paramDTO.getPageNum());
		// paramEntity.setDoctorId(1);

		// 结果Entity
		List<AppointInquiryEntity> resultEntity = null;
		// 结果DTO
		// AppointInquiryDTO resultDTO=new AppointInquiryDTO();
		// DB查询
		try {
			resultEntity = iAppointInquiryService.getDoctorMemberInfo(paramEntity);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (resultEntity != null && resultEntity.size() > 0) {
				patientClientList = new ArrayList<PatientClientDTO>();
				for (AppointInquiryEntity param : resultEntity) {
					PatientClientDTO paramClientDTO = new PatientClientDTO();
					// 获取患者信息
					if (param.getPatientEntity() != null) {
						paramClientDTO.setPatientId(param.getPatientEntity().getPatientId());
						paramClientDTO.setName(param.getPatientEntity().getName());
						if (param.getPatientEntity().getBirth() != null) {
							paramClientDTO.setBirth(sdf.format(param.getPatientEntity().getBirth()));
						}
						paramClientDTO.setSex(param.getPatientEntity().getSex());

						paramClientDTO.setPatientPic(param.getPatientEntity().getPatientPic());

						paramClientDTO.setTel(param.getPatientEntity().getPatientTel());
					}
					// 预约记录
					patientClientList.add(paramClientDTO);
				}
				// }
				// }
				resultClientDTO.setPatientClientDTO(patientClientList);
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-2000");

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
	 * 医生获取会员
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
	public ActionForward doGetMembersOld(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		DoctorInquiryViewEntity paramEntity = new DoctorInquiryViewEntity();

		// 结果ClientDTO
		PatientAppointListDTO resultClientDTO = new PatientAppointListDTO();
		List<PatientClientDTO> patientClientList = null;
		// List<SymptomClientDTO> SymptomClientDTOList=null;
		// 参数DTO->参数Entity

		// CommonUtil.reflectClass(paramDTO, paramEntity);
		paramEntity.setAppointType("2");
		paramEntity.setDoctorId(paramDTO.getDoctorId());
		paramEntity.setPageSize(paramDTO.getPageSize());
		paramEntity.setPageNum(paramDTO.getPageNum());
		// paramEntity.setDoctorId(1);

		// 结果Entity
		List<DoctorInquiryViewEntity> resultEntity = null;
		// 结果DTO
		// AppointInquiryDTO resultDTO=new AppointInquiryDTO();
		// DB查询
		try {
			resultEntity = iDoctorInqueryViewService.getAllPatient(paramEntity);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (resultEntity != null && resultEntity.size() > 0) {
				patientClientList = new ArrayList<PatientClientDTO>();
				for (DoctorInquiryViewEntity param : resultEntity) {
					PatientClientDTO paramClientDTO = new PatientClientDTO();

					// 获取患者信息
					// 参数Entity
					paramClientDTO.setPatientId(param.getPatientId());
					paramClientDTO.setName(param.getName());
					if (param.getBirth() != null) {
						paramClientDTO.setBirth(sdf.format(param.getBirth()));
					}
					paramClientDTO.setSex(param.getSex());

					paramClientDTO.setPatientPic(param.getPatientPic());

					paramClientDTO.setTel(param.getUserLoginEntity().getUserTel());
					// }
					// 预约记录
					patientClientList.add(paramClientDTO);
				}
				resultClientDTO.setPatientClientDTO(patientClientList);
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-2000");

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
	 * 获取某个患者的就诊记录
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
	public ActionForward doGetPatientInfoByDoctor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		AppointInquiryEntity paramEntity = new AppointInquiryEntity();

		// 结果ClientDTO
		PatientClientDTO resultClientDTO = new PatientClientDTO();
		// List<PatientAppointClientDTO> patientAppointList = null;
		// List<SymptomClientDTO> SymptomClientDTOList=null;
		// 参数DTO->参数Entity

		CommonUtil.reflectClass(paramDTO, paramEntity);
		// paramEntity.setPatientId(1);
		// paramEntity.setDoctorId(1);
		// 结果Entity
		List<AppointInquiryEntity> resultEntity = null;
		// 结果DTO
		// AppointInquiryDTO resultDTO=new AppointInquiryDTO();
		// DB查询
		try {
			resultEntity = iAppointInquiryService.getMemberInfo(paramEntity);

			if (resultEntity != null && resultEntity.size() > 0) {
				if (resultEntity.get(0).getPatientEntity() != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					resultClientDTO.setPatientId(resultEntity.get(0).getPatientEntity().getPatientId());
					if (resultEntity.get(0).getPatientEntity().getBirth() != null) {
						resultClientDTO.setBirth(sdf.format(resultEntity.get(0).getPatientEntity().getBirth()));
					}
					resultClientDTO.setName(resultEntity.get(0).getPatientEntity().getName());
					resultClientDTO.setSex(resultEntity.get(0).getPatientEntity().getSex());
					resultClientDTO.setPatientPic(resultEntity.get(0).getPatientEntity().getPatientPic());
				}
				List<RecordsClientDTO> recordstList = new ArrayList<RecordsClientDTO>();

				for (AppointInquiryEntity param : resultEntity) {

					// if ("0".equals(param.getPayStatus())) {
					// // 未支付状态
					// Date cdate = null;
					// Date now = new Date();
					// Calendar cal1 = Calendar.getInstance();
					// cal1.setTime(param.getInsertTime());
					// cal1.add(Calendar.MINUTE, 60);// 24小时制
					// cdate = cal1.getTime();
					// // 当前时间大于创建时间+1则更改支付状态2：支付超时
					// int time = now.compareTo(cdate);
					//
					// if (time > 0) {
					// // 视频
					// if ("0".equals(param.getAppointType())) {
					// // 更新日程信息
					// if (param.getRecordsEntitys() != null &&
					// param.getRecordsEntitys().size() > 0) {
					// for (RecordsEntity record : param.getRecordsEntitys()) {
					// // 更新患者
					// DoctorScheduleEntity scheduleEntity = new
					// DoctorScheduleEntity();
					// if (record.getScheduleId() != null) {
					// // 更新医生日程的已经预约的人数
					// scheduleEntity.setScheduleId(record.getScheduleId());
					// DoctorScheduleEntity resultSchelduleEntity = new
					// DoctorScheduleEntity();
					// resultSchelduleEntity =
					// iDoctorScheduleService.getObject(scheduleEntity);
					// if (resultSchelduleEntity != null) {
					// if (resultSchelduleEntity.getAppointNum() <= 1) {
					// scheduleEntity.setAppointNum(0);
					// scheduleEntity.setAppointStatus("0");
					// } else {
					// scheduleEntity
					// .setAppointNum(resultSchelduleEntity.getAppointNum() -
					// 1);
					// }
					// iDoctorScheduleService.updateDoctorSchedule(scheduleEntity);
					// }
					// }
					// if (record.getPatschId() != null) {
					// // 解除患者和医生日程的关系
					// PatientScheduleEntity patientSchelduleEntity = new
					// PatientScheduleEntity();
					// patientSchelduleEntity.setPsId(record.getPatschId());
					// patientSchelduleEntity.setPsFlag("1");
					// iPatientScheduleService.updatePatientSchedule(patientSchelduleEntity);
					// }
					//
					// }
					// }
					// }
					// // 更新支付状态
					// AppointInquiryEntity updateEntity = new
					// AppointInquiryEntity();
					// updateEntity.setAppointId(param.getAppointId());
					// updateEntity.setPayStatus("2");
					// iAppointInquiryService.updateAppointInquiry(updateEntity);
					// resultClientDTO.setPayStatus("2");
					//
					// }
					// }
					if (param.getRecordsEntitys() != null && param.getRecordsEntitys().size() > 0) {

						for (RecordsEntity record : param.getRecordsEntitys()) {
							if (record.getRecordsId() != null) {
								RecordsClientDTO recordsparam = new RecordsClientDTO();
								// recordsparam.setAppointId(record.getAppointId());
								recordsparam.setPayStatus(param.getPayStatus());
								recordsparam.setRecordsId(record.getRecordsId());
								recordsparam.setTelNum(record.getTelNum());
								if (record.getAppointTime() != null) {
									recordsparam.setAppointTime(
											CommonUtil.dateToString(record.getAppointTime(), "yyyy-MM-dd HH:mm"));
								}
								if (record.getCreateTime() != null) {
									recordsparam.setCreateTime(
											CommonUtil.dateToString(record.getCreateTime(), "yyyy-MM-dd HH:mm"));
								}
								recordsparam.setRecordsType(record.getRecordsType());

								// 判断时间和状态
								// if ("1".equals(record.getInquiryStatus()) &&
								// "0".equals(record.getRecordsType())) {
								// if (param.getAppointTime() != null) {
								// Date date = null;
								// Date nowDate = new Date();
								// Calendar cal = Calendar.getInstance();
								// cal.setTime(param.getAppointTime());
								// cal.add(Calendar.MINUTE, 60);// 24小时制
								// date = cal.getTime();
								// // 当前时间大于预约时间则更改状态为未出结果
								// int i = nowDate.compareTo(date);
								// if (i > 0) {
								// // 更新状态
								// RecordsEntity paramRecordsEntity = new
								// RecordsEntity();
								// paramRecordsEntity.setRecordsId(param.getRecordsId());
								// paramRecordsEntity.setInquiryStatus("9");
								// iRecordsService.updateRecords(paramRecordsEntity);
								// recordsparam.setInquiryStatus("9");
								// } else {
								// recordsparam.setInquiryStatus(record.getInquiryStatus());
								// }
								// }
								// } else {
								// recordsparam.setInquiryStatus(record.getInquiryStatus());
								// }
								recordsparam.setInquiryStatus(record.getInquiryStatus());
								recordstList.add(recordsparam);
							}
						}
					}

				}
				resultClientDTO.setRecordsList(recordstList);

			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-2011");

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
	 * 获取最近常用医生（暂时不用）
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
	 *                if business logic throws an exception 2016.12.20 chengming
	 *                modify for 常用医生
	 */
	public ActionForward doGetRecentDoctorInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 2016.12.20 chengming modify for 常用医生 start
		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		AppointInquiryEntity paramEntity = new AppointInquiryEntity();

		// 结果ClientDTO
		InqueryViewListDTO resultClientDTO = new InqueryViewListDTO();
		CommonUtil.reflectClass(paramDTO, paramEntity);

		// 结果Entity
		List<AppointInquiryEntity> resultEntity = null;
		// 参数Entity
		paramEntity.setPatientId(paramDTO.getPatientId());
		paramEntity.setPageNum(paramDTO.getPageNum());
		paramEntity.setPageSize(paramDTO.getPageSize());
		// paramEntity.setPageNum(0);
		// paramEntity.setPageSize(10);
		// 结果Entity
		try {
			resultEntity = iAppointInquiryService.getAppointListByRecent(paramEntity);
			if (resultEntity != null && resultEntity.size() > 0) {
				List<InqueryViewClientDTO> doctorClientDTOList = new ArrayList<InqueryViewClientDTO>();
				for (AppointInquiryEntity param : resultEntity) {
					InqueryViewClientDTO doctorClientDTO = new InqueryViewClientDTO();
					doctorClientDTO.setScheduleFlag(param.getScheduleFlag());
					// doctorClientDTO.setDepartmentId(param.getDepId());
					doctorClientDTO.setDoctorId(param.getDoctorId());
					doctorClientDTO.setDepartmentName(param.getDoctorEntity().getDepartmentEntity().getDepName());
					doctorClientDTO.setDepartmentClassName(
							param.getDoctorEntity().getDepartmentEntity().getDepartmentClassEntity().getDcName());
					// doctorClientDTO.setSymptomId(param.getSymptonId());
					doctorClientDTO.setHospitalName(param.getDoctorEntity().getHospitalName());
					// 是否关注
					doctorClientDTO.setFollowFlag(param.getFollowFlag());
					if (param.getDoctorEntity() != null) {
						doctorClientDTO.setServiceType(param.getDoctorEntity().getServiceType());
						doctorClientDTO.setHeadPic(param.getDoctorEntity().getHeadPic());
						doctorClientDTO.setName(param.getDoctorEntity().getName());
						doctorClientDTO.setServiceCount(param.getDoctorEntity().getServiceCount());
						doctorClientDTO.setPositional(param.getDoctorEntity().getPositional());
						doctorClientDTO.setHealDisease(param.getDoctorEntity().getHealDisease());
						// 此字段为了常用医生接口 返回的为该患者问诊过的医生次数
						doctorClientDTO.setAppointCount(param.getCount());
						// 2016.12.26 chengming start modify for CommonDoctor
						// 此字段为常用医生接口，返回数据为患者预约过医生的时间
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						// doctorClientDTO.setAppointTime(sdf.format(param.getRecordsEntitys().get(0).getAppointTime()));
						doctorClientDTO.setAppointTime(sdf.format(param.getMaxAppointTime()));
						// 2016.12.26 chengming end modify for CommonDoctor

						// 获取评论
						DoctorCommentEntity commenEntity = new DoctorCommentEntity();
						DoctorCommentEntity commenresultEntity = new DoctorCommentEntity();
						commenEntity.setDoctorId(param.getDoctorEntity().getDoctorId());

						commenresultEntity = iDoctorCommentService.getAverageGrade(commenEntity);
						if (commenresultEntity != null) {
							doctorClientDTO.setCommentGrade((Float) commenresultEntity.getGrade());
						}
						// 获取视频问诊钱数
						// 参数Entity
						InterrogationPackageEntity intergationEntity = new InterrogationPackageEntity();
						intergationEntity.setDoctorId(param.getDoctorEntity().getDoctorId());
						// 2016.12.21 chengming modify for 常用医生 start
						// intergationEntity.setType("2");
						// 0表示视频问诊
						// intergationResult =
						// iInterrogationPackageService.getObject(intergationEntity);
						InterrogationPackageEntity intergationResult = new InterrogationPackageEntity();
						intergationEntity.setType("0");
						intergationResult = iInterrogationPackageService.getOtherObject(intergationEntity);
						if (intergationResult != null) {
							doctorClientDTO.setTotal(intergationResult.getMinTotal().toString());
							// 2016.12.21 chengming modify for 常用医生 end
						} else {
							doctorClientDTO.setTotal("0");
						}
						doctorClientDTOList.add(doctorClientDTO);
					}
				}
				resultClientDTO.setDoctorList(doctorClientDTOList);
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			e.printStackTrace();
			resultClientDTO.setReturnCode("-1065");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultClientDTO.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, resultClientDTO);
		// 2016.12.20 chengming modify for 常用医生 end
		return null;
	}

	/**
	 * 支付宝回调函数
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
	public ActionForward receiveNotify(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		// Map<String, String> underScoreKeyMap =
		// RequestUtils.getStringParams(request);
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			if (!"receiveNotify".equals(name)) {
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
					params.put(name, valueStr);
				}
			}
		}
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ali_public_key,
				AlipayConfig.input_charset);

		// 首先验证调用是否来自支付宝
		Map<String, String> camelCaseKeyMap = RequestUtils.convertKeyToCamelCase(params);
		String jsonString = JSON.toJSONString(camelCaseKeyMap);
		// String jsonParam=JSON.toJSONString(underScoreKeyMap);
		AlipayNotification notice = JSON.parseObject(jsonString, AlipayNotification.class);
		notice.setVerifyResult(signVerified);
		String resultResponse = "success";
		PrintWriter printWriter = null;
		AppointInquiryEntity paramEntity = new AppointInquiryEntity();
		AppointInquiryEntity resultEntiy = new AppointInquiryEntity();
		try {
			printWriter = response.getWriter();
			// do business
			if (signVerified) {
				// 参数Entity
				paramEntity.setOrderNumber(notice.getOutTradeNo());
				resultEntiy = iAppointInquiryService.getObject(paramEntity);
				if (resultEntiy != null && "0".equals(resultEntiy.getPayStatus())) {
					paramEntity.setPayType("0");
					paramEntity.setPayStatus("1");
					paramEntity.setPayTime(new Date());
					paramEntity.setPayParam(jsonString);
					iAppointInquiryService.updateAppointInquiry(paramEntity);
				}
				// else {
				// resultResponse = "fail";
				// }
			}
			// fail due to verification error
			else {
				resultResponse = "fail";
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
		if ("success".equals(resultResponse) && "0".equals(resultEntiy.getAppointType())
				&& "1".equals(resultEntiy.getPayStatus())) {
			AppointInquiryEntity pushEntity = new AppointInquiryEntity();
			pushEntity.setOrderNumber(notice.getOutTradeNo());
			AppointInquiryEntity pushResultEntity = iAppointInquiryService.getInfoForPush(pushEntity);
			if (pushResultEntity != null) {
				if (!"1".equals(pushResultEntity.getRecordsEntitys().get(0).getDoctorFlag())) {
					Map<String, String> extras = new HashMap<String, String>();
					extras.put("patientId", pushResultEntity.getPatientId().toString());
					extras.put("recordsId", pushResultEntity.getRecordsEntitys().get(0).getRecordsId().toString());
					if (pushResultEntity.getDoctorEntity().getDoctorTel() != null) {
						PushConfig.sendNotificationWithAlias(pushResultEntity.getDoctorEntity().getDoctorTel(), extras);
						RecordsEntity records = new RecordsEntity();
						records.setRecordsId(pushResultEntity.getRecordsEntitys().get(0).getRecordsId());
						records.setDoctorFlag("1");
						iRecordsService.updateRecords(records);
					}
				}
			}
		}
		// 返回结果
		return null;
	}

	/**
	 * 微信支付参数拼接
	 */
	public Unifiedorder weChatParam(String orderNum, String subject, String price, HttpServletRequest request) {

		// 参数组
		String appid = WeixinConfigUtils.appid;
		System.out.println("appid是：" + appid);
		String mch_id = WeixinConfigUtils.mch_id;
		System.out.println("mch_id是：" + mch_id);
		String nonce_str = RandCharsUtils.getRandomString(16);
		System.out.println("随机字符串是：" + nonce_str);
		// String body = subject;
		// String detail = "谢彬0.01_元测试开始";
		String attach = "备用参数，先留着，后面会有用的";
		// String out_trade_no = "2015112500001000811017342394";

		float temPrice = Float.parseFloat(price);
		String fee = String.format("%.2f", temPrice);
		fee = fee.replace(".", "");
		int pay = Integer.parseInt(fee);
		int total_fee = pay;// 单位是分，即是0.01元

		String spbill_create_ip = request.getRemoteAddr();
		String time_start = RandCharsUtils.timeStart();
		System.out.println(time_start);
		String time_expire = RandCharsUtils.timeExpire();
		System.out.println(time_expire);
		String hostIp = ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS);
		StringBuffer url = new StringBuffer();
		url.append(hostIp);
		// url.append("Appoint.do?" + "receiveWechatNotify" + "=");
		url.append("Appoint.do");
		String notify_url = url.toString();
		System.out.println("notify_url是：" + notify_url);
		String trade_type = "APP";

		// 参数：开始生成签名
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", appid);
		parameters.put("mch_id", mch_id);
		parameters.put("nonce_str", nonce_str);
		parameters.put("body", subject);
		// parameters.put("nonce_str", nonce_str);
		parameters.put("detail", subject);
		parameters.put("attach", attach);
		parameters.put("out_trade_no", orderNum);
		parameters.put("total_fee", total_fee);
		parameters.put("time_start", time_start);
		parameters.put("time_expire", time_expire);
		parameters.put("notify_url", notify_url);
		parameters.put("trade_type", trade_type);
		parameters.put("spbill_create_ip", spbill_create_ip);

		String sign = WXSignUtils.createSign("UTF-8", parameters);
		System.out.println("签名是：" + sign);

		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(appid);
		unifiedorder.setMch_id(mch_id);
		unifiedorder.setNonce_str(nonce_str);
		unifiedorder.setSign(sign);
		unifiedorder.setBody(subject);
		unifiedorder.setDetail(subject);
		unifiedorder.setAttach(attach);
		unifiedorder.setOut_trade_no(orderNum);
		unifiedorder.setTotal_fee(total_fee);
		unifiedorder.setSpbill_create_ip(spbill_create_ip);
		unifiedorder.setTime_start(time_start);
		unifiedorder.setTime_expire(time_expire);
		unifiedorder.setNotify_url(notify_url);
		unifiedorder.setTrade_type(trade_type);

		return unifiedorder;
	}

	/**
	 * 微信支付参数拼接
	 */
	public UnifiedorderResult weChatParamAgain(UnifiedorderResult unifiedorderResult) {

		long currentTimeMillis = System.currentTimeMillis();// 生成时间戳
		long second = currentTimeMillis / 1000L;// （转换成秒）
		String seconds = String.valueOf(second).substring(0, 10);// （截取前10位）
		// SortedMap<String, String> signParam = new TreeMap<String, String>();
		SortedMap<Object, Object> signParam = new TreeMap<Object, Object>();
		String appid = WeixinConfigUtils.appid;
		// System.out.println("appid是：" + appid);
		String mch_id = WeixinConfigUtils.mch_id;
		signParam.put("appid", WeixinConfigUtils.appid);// app_id
		String prepay_id = null;
		prepay_id = unifiedorderResult.getPrepay_id();
		String noce = RandCharsUtils.getRandomString(16);
		signParam.put("partnerid", WeixinConfigUtils.mch_id);// 微信商户账号
		signParam.put("prepayid", prepay_id);// 预付订单id
		signParam.put("package", "Sign=WXPay");// 默认sign=WXPay
		signParam.put("noncestr", noce);// 自定义不重复的长度不长于32位
		signParam.put("timestamp", seconds);// 北京时间时间戳
		System.out.println("shijianchuo：" + seconds);
		String sign = WXSignUtils.createSign("UTF-8", signParam);// 再次生成签名
		System.out.println("erci签名是：" + sign);
		UnifiedorderResult result = new UnifiedorderResult();
		result.setAppid(appid);
		// result.setMch_id(mch_id);
		result.setNoncestr(noce);
		result.setPrepayid(prepay_id);
		result.setSign(sign);
		result.setTrade_type("APP");
		result.setResult_code(unifiedorderResult.getResult_code());
		result.setReturn_code(unifiedorderResult.getReturn_code());
		result.setReturn_msg(unifiedorderResult.getReturn_msg());
		result.setPartnerid(mch_id);
		result.setTimestamp(seconds);
		result.setPackagesign("Sign=WXPay");
		return result;
	}

	/**
	 * 微信支付回调函数
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
	public ActionForward receiveWechatNotify(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		@SuppressWarnings("unused")
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		// Map<String, String> underScoreKeyMap =
		// RequestUtils.getStringParams(request);
		try {
			BufferedReader reader = request.getReader();

			String line = "";
			StringBuffer inputString = new StringBuffer();

			try {
				PrintWriter writer = response.getWriter();

				while ((line = reader.readLine()) != null) {
					inputString.append(line);
				}

				if (reader != null) {
					reader.close();
				}

				// System.out.println("----[微信回调]接收到的报文---" +
				// inputString.toString());

				if (!StringUtils.isEmpty(inputString.toString())) {
					WXPayResult wxPayResult = JdomParseXmlUtils.getWXPayResult(inputString.toString());
					if ("SUCCESS".equalsIgnoreCase(wxPayResult.getReturn_code())) {
						SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
						parameters.put("appid", wxPayResult.getAppid());
						parameters.put("attach", wxPayResult.getAttach());
						parameters.put("bank_type", wxPayResult.getBank_type());
						parameters.put("cash_fee", wxPayResult.getCash_fee());
						parameters.put("fee_type", wxPayResult.getFee_type());
						parameters.put("is_subscribe", wxPayResult.getIs_subscribe());
						parameters.put("mch_id", wxPayResult.getMch_id());
						parameters.put("nonce_str", wxPayResult.getNonce_str());
						parameters.put("openid", wxPayResult.getOpenid());
						parameters.put("out_trade_no", wxPayResult.getOut_trade_no());
						parameters.put("result_code", wxPayResult.getResult_code());
						parameters.put("return_code", wxPayResult.getReturn_code());
						parameters.put("time_end", wxPayResult.getTime_end());
						parameters.put("total_fee", wxPayResult.getTotal_fee());
						parameters.put("trade_type", "APP");
						parameters.put("transaction_id", wxPayResult.getTransaction_id());

						// 反校验签名
						String sign = WXSignUtils.createSign("UTF-8", parameters);
						AppointInquiryEntity paramEntity = new AppointInquiryEntity();
						AppointInquiryEntity resultEntiy = new AppointInquiryEntity();
						if (sign.equals(wxPayResult.getSign())) {
							// 修改订单的状态
							paramEntity.setOrderNumber(wxPayResult.getOut_trade_no());
							resultEntiy = iAppointInquiryService.getObject(paramEntity);
							if (resultEntiy != null && "0".equals(resultEntiy.getPayStatus())) {
								paramEntity.setPayType("1");
								paramEntity.setPayStatus("1");
								paramEntity.setPayTime(new Date());
								paramEntity.setPayParam(inputString.toString());
								iAppointInquiryService.updateAppointInquiry(paramEntity);
								// } else if
								// ("0".equals(resultEntiy.getAppointType())
								// && "1".equals(resultEntiy.getPayStatus())) {
								AppointInquiryEntity pushEntity = new AppointInquiryEntity();
								pushEntity.setOrderNumber(wxPayResult.getOut_trade_no());
								AppointInquiryEntity pushResultEntity = iAppointInquiryService
										.getInfoForPush(pushEntity);
								if (pushResultEntity != null) {
									if (!"1".equals(pushResultEntity.getRecordsEntitys().get(0).getDoctorFlag())) {
										Map<String, String> extras = new HashMap<String, String>();
										extras.put("patientId", pushResultEntity.getPatientId().toString());
										extras.put("recordsId",
												pushResultEntity.getRecordsEntitys().get(0).getRecordsId().toString());
										if (pushResultEntity.getDoctorEntity().getDoctorTel() != null) {
											PushConfig.sendNotificationWithAlias(
													pushResultEntity.getDoctorEntity().getDoctorTel(), extras);
											RecordsEntity records = new RecordsEntity();
											records.setRecordsId(
													pushResultEntity.getRecordsEntitys().get(0).getRecordsId());
											records.setDoctorFlag("1");
											iRecordsService.updateRecords(records);
										}
									}
								}
							}
							writer.write(HttpXmlUtils.backWeixin("SUCCESS", "OK"));

						} else {
							writer.write(HttpXmlUtils.backWeixin("FAIL", "签名失败"));
						}
					} else {
						writer.write(HttpXmlUtils.backWeixin("FAIL", wxPayResult.getReturn_msg()));

						// System.out.println("---------微信支付返回Fail----------" +
						// wxPayResult.getReturn_msg());
					}

					if (writer != null) {
						writer.close();
					}
				} else {
					writer.write(HttpXmlUtils.backWeixin("FAIL", "未获取到微信返回的结果"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private AppointInquiryEntity getJsonStringToObject(AppointInquiryDTO paramDTO) {

		// 取得客户端传入的json格式的字符串
		String param = paramDTO.getInqueryQuestionList();
		AppointInquiryEntity appointEntity = new AppointInquiryEntity();
		if (!"".equals(param)) {
			List<InqueryQuestionDTO> inqueryQuestionList = GsonUtil.getBeanList(param, InqueryQuestionDTO.class);
			//

			List<InqueryQuestionEntity> paramList = new ArrayList<InqueryQuestionEntity>();
			List<InqueryAnswerEntity> answerList = null;

			for (InqueryQuestionDTO inqueryQuestion : inqueryQuestionList) {
				InqueryQuestionEntity inqueryDTO = new InqueryQuestionEntity();
				inqueryDTO.setIqName(inqueryQuestion.getIqName());
				// inqueryDTO.setIqName("");
				inqueryDTO.setIqId(inqueryQuestion.getIqId());
				// 转换答案Json
				if (inqueryQuestion.getInqueryAnswerDTOs() != null
						&& inqueryQuestion.getInqueryAnswerDTOs().size() > 0) {
					answerList = new ArrayList<InqueryAnswerEntity>();
					for (InqueryAnswerDTO inquery : inqueryQuestion.getInqueryAnswerDTOs()) {
						InqueryAnswerEntity paramAnswer = new InqueryAnswerEntity();
						paramAnswer.setDepId(paramDTO.getDepId());
						paramAnswer.setPatientId(paramDTO.getPatientId());
						paramAnswer.setIqId(inqueryQuestion.getIqId());
						paramAnswer.setIrId(inquery.getIrId());
						paramAnswer.setIaContent(inquery.getIaContent());
						paramAnswer.setIaMemo(inquery.getIaMemo());
						paramAnswer.setIrName(inquery.getIrName());
						answerList.add(paramAnswer);
					}
					inqueryDTO.setInqueryAnswerEntitys(answerList);
				}
				paramList.add(inqueryDTO);
			}
			appointEntity.setInqueryQuestionEntitys(paramList);
		}
		CommonUtil.reflectClass(paramDTO, appointEntity);
		return appointEntity;
	}

	private String sign(String content) {
		return SignUtils.sign(content, AlipayConfig.private_key);
	}

	/**
	 * 医生获取所有患者
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
	public ActionForward doGetAllPatient(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		DoctorInquiryViewEntity paramEntity = new DoctorInquiryViewEntity();

		// 结果ClientDTO
		PatientAppointListDTO resultClientDTO = new PatientAppointListDTO();
		List<PatientClientDTO> patientClientList = null;
		// List<SymptomClientDTO> SymptomClientDTOList=null;
		// 参数DTO->参数Entity

		paramEntity.setDoctorId(paramDTO.getDoctorId());
		// paramEntity.setDoctorId(1);
		// paramEntity.setPageSize(paramDTO.getPageSize());
		// paramEntity.setPageNum(paramDTO.getPageNum());
		// 结果Entity
		List<DoctorInquiryViewEntity> resultEntity = null;
		// 结果DTO
		// AppointInquiryDTO resultDTO=new AppointInquiryDTO();
		// DB查询
		try {
			resultEntity = iDoctorInqueryViewService.getAllPatient(paramEntity);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (resultEntity != null && resultEntity.size() > 0) {
				patientClientList = new ArrayList<PatientClientDTO>();
				for (DoctorInquiryViewEntity param : resultEntity) {
					PatientClientDTO paramClientDTO = new PatientClientDTO();
					// 获取症状名称
					paramClientDTO.setSymptonName(param.getSymptomName());
					paramClientDTO.setDepName(param.getDepName());

					// 获取患者信息
					// 参数Entity
					paramClientDTO.setPatientId(param.getPatientId());
					paramClientDTO.setName(param.getName());
					if (param.getBirth() != null) {
						paramClientDTO.setBirth(sdf.format(param.getBirth()));
					}
					paramClientDTO.setSex(param.getSex());
					paramClientDTO.setPatientPic(param.getPatientPic());
					paramClientDTO.setTel(param.getUserLoginEntity().getUserTel());

					// 预约记录s
					paramClientDTO.setPayStatus(param.getPayStatus());
					paramClientDTO.setAppointId(param.getAppointId());
					paramClientDTO.setRecordsId(param.getRecordsId());
					if (param.getAppointTime() != null) {
						paramClientDTO
								.setAppointTime(CommonUtil.dateToString(param.getAppointTime(), "yyyy-MM-dd HH:mm"));
					}
					if (param.getCreateTime() != null) {
						paramClientDTO
								.setCreateTime(CommonUtil.dateToString(param.getCreateTime(), "yyyy-MM-dd HH:mm"));
					}
					paramClientDTO.setRecordsType(param.getRecordsType());

					// if ("0".equals(param.getPayStatus())) {
					// // 未支付状态
					// Date cdate = null;
					// Date now = new Date();
					// Calendar cal1 = Calendar.getInstance();
					// cal1.setTime(param.getInsertTime());
					// cal1.add(Calendar.MINUTE, 60);// 24小时制
					// cdate = cal1.getTime();
					// // 当前时间大于创建时间+1则更改支付状态2：支付超时
					// int time = now.compareTo(cdate);
					// if (time > 0) {
					// // 视频
					// if ("0".equals(param.getAppointType())) {
					// // 更新日程信息
					// if (param.getRecordsId() != null) {
					// // 更新患者
					// DoctorScheduleEntity scheduleEntity = new
					// DoctorScheduleEntity();
					// if (param.getScheduleId() != null) {
					// // 更新医生日程的已经预约的人数
					// scheduleEntity.setScheduleId(param.getScheduleId());
					//
					// if (param.getDoctorScheduleEntity().getAppointNum() !=
					// null) {
					// if (param.getDoctorScheduleEntity().getAppointNum() <= 1)
					// {
					// scheduleEntity.setAppointNum(0);
					// scheduleEntity.setAppointStatus("0");
					// } else {
					// scheduleEntity.setAppointNum(
					// param.getDoctorScheduleEntity().getAppointNum() - 1);
					// }
					// iDoctorScheduleService.updateDoctorSchedule(scheduleEntity);
					// }
					// }
					// if (param.getPatschId() != null) {
					// // 解除患者和医生日程的关系
					// PatientScheduleEntity patientSchelduleEntity = new
					// PatientScheduleEntity();
					// patientSchelduleEntity.setPsId(param.getPatschId());
					// patientSchelduleEntity.setPsFlag("1");
					// iPatientScheduleService.updatePatientSchedule(patientSchelduleEntity);
					// }
					//
					// }
					// }
					// }
					// // 更新支付状态
					// AppointInquiryEntity updateEntity = new
					// AppointInquiryEntity();
					// updateEntity.setAppointId(param.getAppointId());
					// updateEntity.setPayStatus("2");
					// iAppointInquiryService.updateAppointInquiry(updateEntity);
					// paramClientDTO.setPayStatus("2");
					// }
					// 判断时间和状态
					// if ("1".equals(param.getInquiryStatus()) &&
					// "0".equals(param.getRecordsType())) {
					// if (param.getAppointTime() != null) {
					// Date date = null;
					// Date nowDate = new Date();
					// Calendar cal = Calendar.getInstance();
					// cal.setTime(param.getAppointTime());
					// cal.add(Calendar.MINUTE, 60);// 24小时制
					// date = cal.getTime();
					// // 当前时间大于预约时间则更改状态为未出结果
					// int i = nowDate.compareTo(date);
					// if (i > 0) {
					// // 更新状态
					// RecordsEntity paramRecordsEntity = new RecordsEntity();
					// paramRecordsEntity.setRecordsId(param.getRecordsId());
					// paramRecordsEntity.setInquiryStatus("9");
					// iRecordsService.updateRecords(paramRecordsEntity);
					// paramClientDTO.setInquiryStatus("9");
					// } else {
					// paramClientDTO.setInquiryStatus(param.getInquiryStatus());
					// }
					// }
					// } else {
					// paramClientDTO.setInquiryStatus(param.getInquiryStatus());
					// }
					paramClientDTO.setInquiryStatus(param.getInquiryStatus());

					patientClientList.add(paramClientDTO);
				}
				resultClientDTO.setPatientClientDTO(patientClientList);
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-2000");

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

	// private String doUpDatePayInfo(Date createTime, int appointId) {
	//
	// String updateFlag = "0";
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// try {
	// // Date nowDate = sdf.parse(sdf.format(new Date()));
	// // Date createTime1 = sdf.parse(sdf.format(createTime));
	// // Calendar cal = Calendar.getInstance();
	// // cal.setTime(createTime1);
	// // cal.add(Calendar.HOUR, 1);
	// // long time1 = cal.getTimeInMillis();
	// // cal.setTime(nowDate);
	// // long time2 = cal.getTimeInMillis();
	// // long between_days = (time2 - time1) / (1000 * 3600 * 24);
	// // 当前时间>创建时间+1小时
	// // if (between_days > 0) {
	//
	// // 未支付状态
	// Date cdate = null;
	// Date now = new Date();
	// Calendar cal1 = Calendar.getInstance();
	// cal1.setTime(createTime);
	// cal1.add(Calendar.MINUTE, 60);// 24小时制
	// cdate = cal1.getTime();
	// // 当前时间大于创建时间+1则更改支付状态2：支付超时
	// int time = now.compareTo(cdate);
	//
	// if (time > 0) {
	// // 更新支付状态超时
	// AppointInquiryEntity appointEntity = new AppointInquiryEntity();
	// appointEntity.setAppointId(appointId);
	// appointEntity.setPayStatus("2");
	// iAppointInquiryService.updateAppointInquiry(appointEntity);
	// updateFlag = "1";
	// }
	// } catch (Exception e) {
	//
	// updateFlag = "2";
	// }
	// return updateFlag;
	//
	// }

	/**
	 * 医生获取会员
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
	public ActionForward doGetMembersForPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		AppointInquiryEntity paramEntity = new AppointInquiryEntity();

		// 结果ClientDTO
		PatientAppointListDTO resultClientDTO = new PatientAppointListDTO();
		List<PatientClientDTO> patientClientList = null;
		// List<SymptomClientDTO> SymptomClientDTOList=null;
		// 参数DTO->参数Entity
		// CommonUtil.reflectClass(paramDTO, paramEntity);
		paramEntity.setAppointType("2");
		paramEntity.setDoctorId(paramDTO.getDoctorId());
		paramEntity.setPageSize(paramDTO.getPageSize());
		paramEntity.setPageNum(paramDTO.getPageNum());
		// paramEntity.setDoctorId(1);

		// 结果Entity
		List<AppointInquiryEntity> resultEntity = null;
		// 结果DTO
		// AppointInquiryDTO resultDTO=new AppointInquiryDTO();
		// DB查询
		try {
			resultEntity = iAppointInquiryService.getDoctorMemberInfoForPage(paramEntity);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (resultEntity != null && resultEntity.size() > 0) {
				patientClientList = new ArrayList<PatientClientDTO>();
				for (AppointInquiryEntity param : resultEntity) {
					PatientClientDTO paramClientDTO = new PatientClientDTO();
					// 获取患者信息
					if (param.getPatientEntity() != null) {
						paramClientDTO.setPatientId(param.getPatientEntity().getPatientId());
						paramClientDTO.setName(param.getPatientEntity().getName());
						if (param.getPatientEntity().getBirth() != null) {
							paramClientDTO.setBirth(sdf.format(param.getPatientEntity().getBirth()));
						}
						paramClientDTO.setSex(param.getPatientEntity().getSex());

						paramClientDTO.setPatientPic(param.getPatientEntity().getPatientPic());

						paramClientDTO.setTel(param.getPatientEntity().getPatientTel());
					}
					// 预约记录
					patientClientList.add(paramClientDTO);
				}
				// }
				// }
				resultClientDTO.setPatientClientDTO(patientClientList);
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-2000");

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
	 * 医生获取所有患者
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
	public ActionForward doGetAllPatientForPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		DoctorInquiryViewEntity paramEntity = new DoctorInquiryViewEntity();

		// 结果ClientDTO
		PatientAppointListDTO resultClientDTO = new PatientAppointListDTO();
		List<PatientClientDTO> patientClientList = null;
		// List<SymptomClientDTO> SymptomClientDTOList=null;
		// 参数DTO->参数Entity

		paramEntity.setDoctorId(paramDTO.getDoctorId());
		// paramEntity.setDoctorId(1);
		paramEntity.setPageSize(paramDTO.getPageSize());
		paramEntity.setPageNum(paramDTO.getPageNum());
		// 结果Entity
		List<DoctorInquiryViewEntity> resultEntity = null;
		// 结果DTO
		// AppointInquiryDTO resultDTO=new AppointInquiryDTO();
		// DB查询
		try {
			resultEntity = iDoctorInqueryViewService.findAllPatientForPage(paramEntity);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (resultEntity != null && resultEntity.size() > 0) {
				patientClientList = new ArrayList<PatientClientDTO>();
				for (DoctorInquiryViewEntity param : resultEntity) {
					PatientClientDTO paramClientDTO = new PatientClientDTO();
					// 获取症状名称
					paramClientDTO.setSymptonName(param.getSymptomName());

					paramClientDTO.setDepName(param.getDepName());

					// 获取患者信息
					// 参数Entity
					paramClientDTO.setPatientId(param.getPatientId());
					paramClientDTO.setName(param.getName());
					if (param.getBirth() != null) {
						paramClientDTO.setBirth(sdf.format(param.getBirth()));
					}
					paramClientDTO.setSex(param.getSex());
					paramClientDTO.setPatientPic(param.getPatientPic());
					paramClientDTO.setTel(param.getUserLoginEntity().getUserTel());

					// 预约记录s
					paramClientDTO.setPayStatus(param.getPayStatus());
					paramClientDTO.setAppointId(param.getAppointId());
					paramClientDTO.setRecordsId(param.getRecordsId());
					if (param.getAppointTime() != null) {
						paramClientDTO
								.setAppointTime(CommonUtil.dateToString(param.getAppointTime(), "yyyy-MM-dd HH:mm"));
					}
					if (param.getCreateTime() != null) {
						paramClientDTO
								.setCreateTime(CommonUtil.dateToString(param.getCreateTime(), "yyyy-MM-dd HH:mm"));
					}
					paramClientDTO.setRecordsType(param.getRecordsType());

					// if ("0".equals(param.getPayStatus())) {
					// // 未支付状态
					// Date cdate = null;
					// Date now = new Date();
					// Calendar cal1 = Calendar.getInstance();
					// cal1.setTime(param.getInsertTime());
					// cal1.add(Calendar.MINUTE, 60);// 24小时制
					// cdate = cal1.getTime();
					// // 当前时间大于创建时间+1则更改支付状态2：支付超时
					// int time = now.compareTo(cdate);
					//
					// if (time > 0) {
					// // 视频
					// if ("0".equals(param.getAppointType())) {
					// // 更新日程信息
					// if (param.getRecordsId() != null) {
					// // 更新患者
					// DoctorScheduleEntity scheduleEntity = new
					// DoctorScheduleEntity();
					// if (param.getScheduleId() != null) {
					// // 更新医生日程的已经预约的人数
					// scheduleEntity.setScheduleId(param.getScheduleId());
					//
					// if (param.getDoctorScheduleEntity().getAppointNum() !=
					// null) {
					// if (param.getDoctorScheduleEntity().getAppointNum() <= 1)
					// {
					// scheduleEntity.setAppointNum(0);
					// scheduleEntity.setAppointStatus("0");
					// } else {
					// scheduleEntity.setAppointNum(
					// param.getDoctorScheduleEntity().getAppointNum() - 1);
					// }
					// iDoctorScheduleService.updateDoctorSchedule(scheduleEntity);
					// }
					// }
					// if (param.getPatschId() != null) {
					// // 解除患者和医生日程的关系
					// PatientScheduleEntity patientSchelduleEntity = new
					// PatientScheduleEntity();
					// patientSchelduleEntity.setPsId(param.getPatschId());
					// patientSchelduleEntity.setPsFlag("1");
					// iPatientScheduleService.updatePatientSchedule(patientSchelduleEntity);
					// }
					//
					// }
					// }
					// }
					// // 更新支付状态
					// AppointInquiryEntity updateEntity = new
					// AppointInquiryEntity();
					// updateEntity.setAppointId(param.getAppointId());
					// updateEntity.setPayStatus("2");
					// iAppointInquiryService.updateAppointInquiry(updateEntity);
					// paramClientDTO.setPayStatus("2");
					// }
					// 判断时间和状态
					// if ("1".equals(param.getInquiryStatus()) &&
					// "0".equals(param.getRecordsType())) {
					// if (param.getAppointTime() != null) {
					// Date date = null;
					// Date nowDate = new Date();
					// Calendar cal = Calendar.getInstance();
					// cal.setTime(param.getAppointTime());
					// cal.add(Calendar.MINUTE, 60);// 24小时制
					// date = cal.getTime();
					// // 当前时间大于预约时间则更改状态为未出结果
					// int i = nowDate.compareTo(date);
					// if (i > 0) {
					// // 更新状态
					// RecordsEntity paramRecordsEntity = new RecordsEntity();
					// paramRecordsEntity.setRecordsId(param.getRecordsId());
					// paramRecordsEntity.setInquiryStatus("9");
					// iRecordsService.updateRecords(paramRecordsEntity);
					// paramClientDTO.setInquiryStatus("9");
					// } else {
					// paramClientDTO.setInquiryStatus(param.getInquiryStatus());
					// }
					// }
					// } else {
					// paramClientDTO.setInquiryStatus(param.getInquiryStatus());
					// }
					paramClientDTO.setInquiryStatus(param.getInquiryStatus());

					patientClientList.add(paramClientDTO);
				}
				resultClientDTO.setPatientClientDTO(patientClientList);
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-2000");

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
	 * 我的病历
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
	public ActionForward doGetRecordsInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		AppointInquiryEntity paramEntity = new AppointInquiryEntity();

		// 结果ClientDTO
		RecordsInfoDTO recordsInfoDTO = new RecordsInfoDTO();
		List<RecordsInfoDetailDTO> recordsInfoList = null;
		// 参数DTO->参数Entity

		// CommonUtil.reflectClass(paramDTO, paramEntity);
		paramEntity.setPatientId(paramDTO.getPatientId());
		paramEntity.setPageNum(paramDTO.getPageNum());
		paramEntity.setPageSize(paramDTO.getPageSize());
		// paramEntity.setRecordsId(1);
		// 结果Entity
		List<AppointInquiryEntity> resultEntity = null;
		// 结果DTO
		// AppointInquiryDTO resultDTO=new AppointInquiryDTO();
		// DB查询
		try {
			resultEntity = iAppointInquiryService.getRecordsInfo(paramEntity);
			// 给客户端结果赋值
			if (resultEntity != null && resultEntity.size() > 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				recordsInfoList = new ArrayList<RecordsInfoDetailDTO>();
				for (AppointInquiryEntity param : resultEntity) {
					if("5".equals(param.getAppointType())){
						RecordsInfoDetailDTO recordsInfoDetailDTO = new RecordsInfoDetailDTO();
						if (param.getDoctorEntity() != null) {
							recordsInfoDetailDTO.setHospitalName(param.getDoctorEntity().getHospitalName());
							recordsInfoDetailDTO.setDepartmentName(param.getDoctorEntity().getDepartmentName());
							recordsInfoDetailDTO.setDoctorId(param.getDoctorEntity().getDoctorId());
							recordsInfoDetailDTO.setDcName(param.getDoctorEntity().getDcName());
							recordsInfoDetailDTO.setAppointType(param.getAppointType());
							recordsInfoDetailDTO.setAppointId(param.getAppointId());
							if(param.getUpdateTime()!=null){
							recordsInfoDetailDTO.setUpdateTime(sdf.format(param.getUpdateTime()));}
							recordsInfoList.add(recordsInfoDetailDTO);
						}
					}else{
					
					if (param.getRecordsEntitys() != null && param.getRecordsEntitys().size() > 0) {

						for (RecordsEntity records : param.getRecordsEntitys()) {
							RecordsInfoDetailDTO recordsInfoDetailDTO = new RecordsInfoDetailDTO();
							if (records.getAppointTime() != null) {
								recordsInfoDetailDTO.setAppointTime(sdf.format(records.getAppointTime()));
							}
							if (records.getUpdateTime() != null) {
								recordsInfoDetailDTO.setUpdateTime(sdf.format(records.getUpdateTime()));
							}
							recordsInfoDetailDTO.setAppointType(param.getAppointType());
							
							recordsInfoDetailDTO.setRecordsId(records.getRecordsId());
							recordsInfoDetailDTO.setRecordsType(records.getRecordsType());
							if (records.getCreateTime() != null) {
								recordsInfoDetailDTO.setCreateTime(sdf.format(records.getCreateTime()));
							}
							if (param.getDoctorEntity() != null) {
								recordsInfoDetailDTO.setHospitalName(param.getDoctorEntity().getHospitalName());
								recordsInfoDetailDTO.setDepartmentName(param.getDoctorEntity().getDepartmentName());
								recordsInfoDetailDTO.setDoctorId(param.getDoctorEntity().getDoctorId());
								recordsInfoDetailDTO.setDcName(param.getDoctorEntity().getDcName());
							}
							recordsInfoList.add(recordsInfoDetailDTO);

							//SortList<RecordsInfoDetailDTO> sortList = new SortList<RecordsInfoDetailDTO>();
							//sortList.Sort(recordsInfoList, "getAppointTime", "desc");
						}
					}}
				}
				recordsInfoDTO.setRecordsInfo(recordsInfoList);
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			recordsInfoDTO.setReturnCode("-5040");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			recordsInfoDTO.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, recordsInfoDTO);

		return null;
	}

	/**
	 * 批处理获取列表信息
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

	public ActionForward doGetAppointByBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		AppointInquiryEntity paramEntity = new AppointInquiryEntity();

		// 结果ClientDTO
		AppointInquiryDTO resultDTO = new AppointInquiryDTO();
		List<AppointInquiryDTO> patientAppointList = null;

		// 参数DTO->参数Entity
		CommonUtil.reflectClass(paramDTO, paramEntity);
		// paramEntity.setPageSize(paramDTO.getPageSize());
		// paramEntity.setPageNum(paramDTO.getPageNum());
		// 结果Entity
		List<AppointInquiryEntity> resultEntity = null;
		List<RecordsDTO> recordsEntity = null;
		// DB查询
		try {
			resultEntity = iAppointInquiryService.getBatchInfo(paramEntity);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (resultEntity != null && resultEntity.size() > 0) {
				patientAppointList = new ArrayList<AppointInquiryDTO>();
				for (AppointInquiryEntity param : resultEntity) {
					AppointInquiryDTO appointEntity = new AppointInquiryDTO();
					// 获取科室信息
					// 参数Entity
					// CommonUtil.reflectClass(param, appointEntity);
					appointEntity.setAppointId(param.getAppointId());
					appointEntity.setAppointType(param.getAppointType());
					appointEntity.setPayStatus(param.getPayStatus());
					if (param.getInsertTime() != null) {
						appointEntity.setInsertTime(sdf.format(param.getInsertTime()));
					}
					if (param.getRecordsEntitys() != null) {
						recordsEntity = new ArrayList<RecordsDTO>();
						for (RecordsEntity records : param.getRecordsEntitys()) {
							RecordsDTO recordsParam = new RecordsDTO();
							// CommonUtil.reflectClass(records, recordsParam);
							recordsParam.setRecordsId(records.getRecordsId());
							recordsParam.setInquiryStatus(records.getInquiryStatus());
							if (records.getAppointTime() != null) {
								recordsParam.setAppointTime(sdf.format(records.getAppointTime()));
							}
							recordsParam.setAppointId(records.getAppointId());
							recordsParam.setScheduleId(records.getScheduleId());
							recordsParam.setPatschId(records.getPatschId());
							recordsParam.setRecordsType(records.getRecordsType());
							recordsParam.setRole(records.getRole());
							recordsEntity.add(recordsParam);
						}
						appointEntity.setRecordsDTOs(recordsEntity);
					}
					patientAppointList.add(appointEntity);
				}
				resultDTO.setAppointList(patientAppointList);
			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultDTO.setReturnCode("-9000");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultDTO.setReturnCode("0");
			//

		}
		// 返回结果
		toJson(response, resultDTO);
		return null;
	}

	/**
	 * 获取我的问诊列表按照状态
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

	public ActionForward doGetAppointListByStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		AppointInquiryEntity paramEntity = new AppointInquiryEntity();

		// 结果ClientDTO
		PatientAppointListClientDTO resultClientDTO = new PatientAppointListClientDTO();
		List<PatientAppointClientDTO> patientAppointList = null;

		// 参数DTO->参数Entity
		CommonUtil.reflectClass(paramDTO, paramEntity);
		paramEntity.setPageSize(paramDTO.getPageSize());
		paramEntity.setPageNum(paramDTO.getPageNum());
		paramEntity.setStatusFlag(paramDTO.getStatusFlag());
		// 结果Entity
		List<AppointInquiryEntity> resultEntity = null;

		// DB查询
		try {
			if ("0".equals(paramDTO.getType())) {
				resultEntity = iAppointInquiryService.getAppointListByStatus(paramEntity);
			}
			if ("1".equals(paramDTO.getType())) {
				resultEntity = iAppointInquiryService.findGraphicListByStatus(paramEntity);
			}

			if (resultEntity != null && resultEntity.size() > 0) {
				patientAppointList = new ArrayList<PatientAppointClientDTO>();
				for (AppointInquiryEntity param : resultEntity) {
					PatientAppointClientDTO paramClientDTO = new PatientAppointClientDTO();
					// 获取科室信息
					// 参数Entity
					paramClientDTO.setPayStatus(param.getPayStatus());
					paramClientDTO.setPayType(param.getPayType());
					paramClientDTO.setAppointId(param.getAppointId());
					paramClientDTO.setUserCode(param.getUserCode());

					if (param.getPayInfo() != null && "0".equals(param.getPayType())) {// 支付宝支付
						paramClientDTO.setAliPaySignInfo(param.getPayInfo());

					} else if (param.getPayInfo() != null && "1".equals(param.getPayType())) {// 微信支付
						UnifiedorderResult unifiedorderResult = JdomParseXmlUtils
								.getUnifiedorderResult(param.getPayInfo());
						UnifiedorderResult result = new UnifiedorderResult();
						result.setAppid(unifiedorderResult.getAppid());
						result.setNoncestr(unifiedorderResult.getNoncestr());
						result.setPrepayid(unifiedorderResult.getPrepayid());
						result.setSign(unifiedorderResult.getSign());
						result.setTrade_type("APP");
						result.setResult_code(unifiedorderResult.getResult_code());
						result.setReturn_code(unifiedorderResult.getReturn_code());
						result.setReturn_msg(unifiedorderResult.getReturn_msg());
						result.setPartnerid(unifiedorderResult.getPartnerid());
						result.setTimestamp(unifiedorderResult.getTimestamp());
						result.setPackagesign("Sign=WXPay");
						String resultxml = GsonUtil.getJsonStringFormBean(result);
						paramClientDTO.setAliPaySignInfo(resultxml);
					}
					paramClientDTO.setBuyTotal(param.getBuyTotal());
					if (param.getDoctorEntity() != null) {
						// 医生信息
						paramClientDTO.setDoctorId(param.getDoctorEntity().getDoctorId());
						paramClientDTO.setHealDisease(param.getDoctorEntity().getHealDisease());
						paramClientDTO.setName(param.getDoctorEntity().getName());
						paramClientDTO.setHeadPic(param.getDoctorEntity().getHeadPic());
						paramClientDTO.setIntroduction(param.getDoctorEntity().getIntroduction());
						paramClientDTO.setPositional(param.getDoctorEntity().getPositional());
						paramClientDTO.setHospitalName(param.getDoctorEntity().getHospitalName());
						paramClientDTO.setTel(param.getDoctorEntity().getUserLoginEntity().getUserTel());
						paramClientDTO.setDepartmentName(param.getDoctorEntity().getDepartmentName());
						paramClientDTO.setDcName(param.getDoctorEntity().getDcName());
						paramClientDTO.setDepartmentName(param.getDoctorEntity().getDepartmentName());
						paramClientDTO.setDcName(param.getDoctorEntity().getDcName());
						paramClientDTO.setTel(param.getDoctorEntity().getDoctorTel());
					}
					if ("1".equals(paramDTO.getType())) {
						paramClientDTO.setFollowUpFlag(param.getFollowUpFlag());
						paramClientDTO.setFreeFlag(param.getFreeFlag());
						if (param.getGraphicEntitys() != null && param.getGraphicEntitys().size() > 0) {
							paramClientDTO.setGraphicContent(param.getGraphicEntitys().get(0).getGraphicContent());
							paramClientDTO.setGraphicType(param.getGraphicEntitys().get(0).getGraphicType());
							paramClientDTO.setRole(param.getGraphicEntitys().get(0).getRole());
							paramClientDTO.setRecordsTime(CommonUtil.dateToString(param.getGraphicEntitys().get(0).getMaxTime(), "yyyy-MM-dd HH:mm"));
						} else {
							paramClientDTO
									.setCreateTime(CommonUtil.dateToString(param.getPhoTime(), "yyyy-MM-dd HH:mm"));
						}
						patientAppointList.add(paramClientDTO);
					}
					// 预约问诊状态是未支付，分为视频，电话和私人医生状况,视频需要释放医生的日程,
					if ("0".equals(paramDTO.getType())) {
						if (param.getInterrogationPackageEntity() != null) {
							paramClientDTO.setQaTime(param.getInterrogationPackageEntity().getQaTime());
						}
						if (param.getRecordsEntitys() != null && param.getRecordsEntitys().size() > 0) {
							// 预约记录
							for (RecordsEntity record : param.getRecordsEntitys()) {

								PatientAppointClientDTO recordsparam = new PatientAppointClientDTO();
								// recordsparam.setAppointId(record.getAppointId());
								recordsparam.setRecordsId(record.getRecordsId());
								if (record.getAppointTime() != null) {
									recordsparam.setAppointTime(
											CommonUtil.dateToString(record.getAppointTime(), "yyyy-MM-dd HH:mm"));
								}
								if (record.getCreateTime() != null) {
									recordsparam.setCreateTime(
											CommonUtil.dateToString(record.getCreateTime(), "yyyy-MM-dd HH:mm"));
								}

								recordsparam.setRecordsType(record.getRecordsType());

								recordsparam.setInquiryStatus(record.getInquiryStatus());
								CommonUtil.reflectClass(paramClientDTO, recordsparam);
								recordsparam.setBuyTotal(param.getBuyTotal());
								patientAppointList.add(recordsparam);
							}
						}
					}
					if ("0".equals(paramDTO.getType())) {
					resultClientDTO.setPatientAppointList(patientAppointList);
					}
				}
				if ("1".equals(paramDTO.getType())) {
					resultClientDTO.setGraphicAppointList(patientAppointList);
				}
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-1070");

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
	 * 通过状态获取我的预约就诊列表
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
	public ActionForward doGetAllPatientByStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		DoctorInquiryViewEntity paramEntity = new DoctorInquiryViewEntity();
		AppointInquiryEntity appointParamEntity = new AppointInquiryEntity();
		// 结果ClientDTO
		PatientAppointListDTO resultClientDTO = new PatientAppointListDTO();
		AppointInquiryGraphicClientDTO resultAppGrapClientDto =
				new AppointInquiryGraphicClientDTO();
		
		List<PatientClientDTO> patientClientList = null;
		List<AppointInquiryGraphicClientDTO> appointInquiryGraphicClientList = null;
		// List<SymptomClientDTO> SymptomClientDTOList=null;
		// 参数DTO->参数Entity
		appointParamEntity.setDoctorId(paramDTO.getDoctorId());
		appointParamEntity.setPageSize(paramDTO.getPageSize());
		appointParamEntity.setPageNum(paramDTO.getPageNum());
		appointParamEntity.setStatusFlag(paramDTO.getStatusFlag());
		
		paramEntity.setDoctorId(paramDTO.getDoctorId());
		// paramEntity.setDoctorId(1);
		paramEntity.setPageSize(paramDTO.getPageSize());
		paramEntity.setPageNum(paramDTO.getPageNum());
		paramEntity.setStatusFlag(paramDTO.getStatusFlag());
		// 结果Entity
		List<DoctorInquiryViewEntity> resultEntity = null;
		List<AppointInquiryEntity> appointResultEntity = null;
		// 结果DTO
		// AppointInquiryDTO resultDTO=new AppointInquiryDTO();
		// DB查询
		try {
			// 视频
			if ("0".equals(paramDTO.getType())) {
				resultEntity = iDoctorInqueryViewService.getAllPatientForStatus(paramEntity);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if (resultEntity != null && resultEntity.size() > 0) {
					patientClientList = new ArrayList<PatientClientDTO>();
					for (DoctorInquiryViewEntity param : resultEntity) {
						PatientClientDTO paramClientDTO = new PatientClientDTO();
						// 获取症状名称
						paramClientDTO.setSymptonName(param.getSymptomName());

						paramClientDTO.setDepName(param.getDepName());
						paramClientDTO.setQaTime(param.getQaTime());
						// 获取患者信息
						// 参数Entity
						paramClientDTO.setPatientId(param.getPatientId());
						paramClientDTO.setName(param.getName());
						if (param.getBirth() != null) {
							paramClientDTO.setBirth(sdf.format(param.getBirth()));
						}
						paramClientDTO.setSex(param.getSex());
						paramClientDTO.setPatientPic(param.getPatientPic());
						paramClientDTO.setTel(param.getUserLoginEntity().getUserTel());

						// 预约记录s
						paramClientDTO.setPayStatus(param.getPayStatus());
						paramClientDTO.setAppointId(param.getAppointId());
						paramClientDTO.setRecordsId(param.getRecordsId());
						if (param.getAppointTime() != null) {
							paramClientDTO
									.setAppointTime(CommonUtil.dateToString(param.getAppointTime(), "yyyy-MM-dd HH:mm"));
						}
						if (param.getCreateTime() != null) {
							paramClientDTO
									.setCreateTime(CommonUtil.dateToString(param.getCreateTime(), "yyyy-MM-dd HH:mm"));
						}
						paramClientDTO.setRecordsType(param.getRecordsType());
						
						paramClientDTO.setInquiryStatus(param.getInquiryStatus());

						patientClientList.add(paramClientDTO);

					}
					resultClientDTO.setPatientClientDTO(patientClientList);
				}
			}
			// 图文
			if ("1".equals(paramDTO.getType())) {
				appointResultEntity = new ArrayList<AppointInquiryEntity>();
				appointResultEntity = iAppointInquiryService
						.getAppointGraphicListByStatus(appointParamEntity);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				if (appointResultEntity != null && appointResultEntity.size() > 0) {
					appointInquiryGraphicClientList = 
							new ArrayList<AppointInquiryGraphicClientDTO>();
					for (AppointInquiryEntity param : appointResultEntity) {
						AppointInquiryGraphicClientDTO paramClientDTO = 
								new AppointInquiryGraphicClientDTO();
						// 聊天记录条数
						if( "1".equals(param.getFreeFlag())&&"0".equals(param.getFollowUpFlag())){
						int count = param.getCount();
						if (param.getFreeTotal() != null) {
							int freeTotal = param.getFreeTotal();
							// 剩余浏览次数
							paramClientDTO.setSupTotal(String.valueOf(freeTotal - count));
						} else {
							paramClientDTO.setSupTotal("0");
						}}
						//预约就诊ID
						paramClientDTO.setAppointId(String.valueOf(param.getAppointId()));
						// 患者ID
						paramClientDTO.setPatientId(param.getPatientId());
						// 患者头像
						paramClientDTO.setPatientPic(param.getPatientPic());
						// 患者姓名
						paramClientDTO.setName(param.getPatientName());
						// 患者性别
						paramClientDTO.setSex(param.getSex());
						// 患者电话
						paramClientDTO.setPatientTel(param.getPatientEntity().getPatientTel());
						// 患者出生日期
						if (param.getAge() != null) {
							paramClientDTO.setBirth(sdf.format(
									param.getAge()));
						}
						if (param.getGraphicEntitys() != null 
								&& param.getGraphicEntitys().size() != 0) {
							// 最后访问时间
							if (param.getMaxChatTime() != null 
									&& !"".equals(param.getMaxChatTime())) {
								paramClientDTO.setRecordTime(
										sdft.format(param.getMaxChatTime()));
							}
							// 图文内容
							paramClientDTO.setGraphicContent(
									param.getGraphicEntitys().get(0).getGraphicContent());
							// 图文类别
							paramClientDTO.setGraphicType(
									param.getGraphicEntitys().get(0).getGraphicType());
							// 角色
							paramClientDTO.setRole(param.getGraphicEntitys().get(0).getRole());
						}
						// 科室名称
						paramClientDTO.setDepartmentName(
								param.getDoctorEntity().getDepartmentName());
						// 是否免费
						paramClientDTO.setFreeFlag(param.getFreeFlag());

						// 是否是随访
						paramClientDTO.setFollowUpFlag(param.getFollowUpFlag());
						
						appointInquiryGraphicClientList.add(paramClientDTO);
					}
					resultAppGrapClientDto.setAppointGraphicClientList(
							appointInquiryGraphicClientList);
				}
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			if ("0".equals(paramDTO.getType())) {
				resultClientDTO.setReturnCode("-2020");
			} else {
				resultAppGrapClientDto.setReturnCode("-2020");
			}
		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			if ("0".equals(paramDTO.getType())) {
				resultClientDTO.setReturnCode("0");
			} else {
				resultAppGrapClientDto.setReturnCode("0");
			}
		}
		// 返回结果
		if ("0".equals(paramDTO.getType())) {
			toJson(response, resultClientDTO);
		} else {
			toJson(response, resultAppGrapClientDto);
		}

		return null;
	}

	/**
	 * quartz 更新请求
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
	public ActionForward doUpdateBatchForQuartz(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		List<AppointInquiryEntity> paramList = this.getBatchJsonToObject(paramDTO);
		BaseClientDTO resultClientDTO = new BaseClientDTO();
		// 参数Entity
		try {
			if (paramList != null) {
				// iAppointInquiryService.updateBatch(paramList);
				for (AppointInquiryEntity param : paramList) {
					if (param.getAppointId() != null) {
						iAppointInquiryService.updateAppointInquiry(param);
					}
					if (param.getRecordsEntitys() != null && param.getRecordsEntitys().size() > 0) {
						if ("2".equals(param.getPayStatus())) {
							for (RecordsEntity record : param.getRecordsEntitys()) {
								if (record.getScheduleId() != null) {
									// 更新患者
									DoctorScheduleEntity scheduleEntity = new DoctorScheduleEntity();
									// 更新医生日程的已经预约的人数
									scheduleEntity.setScheduleId(record.getScheduleId());
									DoctorScheduleEntity resultSchelduleEntity = new DoctorScheduleEntity();
									resultSchelduleEntity = iDoctorScheduleService.getObject(scheduleEntity);
									if (resultSchelduleEntity != null) {
										if (resultSchelduleEntity.getAppointNum() <= 1) {
											scheduleEntity.setAppointNum(0);
											scheduleEntity.setAppointStatus("0");
										} else {
											scheduleEntity.setAppointNum(resultSchelduleEntity.getAppointNum() - 1);
										}
										iDoctorScheduleService.updateDoctorSchedule(scheduleEntity);
									}
								}
								if (record.getPatschId() != null) {
									// 解除患者和医生日程的关系
									PatientScheduleEntity patientSchelduleEntity = new PatientScheduleEntity();
									patientSchelduleEntity.setPsId(record.getPatschId());
									patientSchelduleEntity.setPsFlag("1");
									iPatientScheduleService.updatePatientSchedule(patientSchelduleEntity);
								}
							}
						} else if ("1".equals(param.getPayStatus())) {
							iRecordsService.updateRecordsBatch(param.getRecordsEntitys());
							// System.out.println(">>>>>>>");
						}

					}
				}
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-6000");

		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultClientDTO.setReturnCode("0");
			// //
		}
		// 返回结果
		toJson(response, resultClientDTO);

		return null;
	}

	/**
	 * 客户端请求json格式的字符串转换成对象。
	 * 
	 * @param vo
	 *            用户端请求值
	 */
	private List<AppointInquiryEntity> getBatchJsonToObject(AppointInquiryDTO paramDTO) {

		// 取得客户端传入的json格式的字符串
		String param = paramDTO.getQuartzString();
		AppointInquiryDTO appointDTO = null;
		List<AppointInquiryEntity> appointResultList = null;
		List<RecordsEntity> recordsEntityList = null;
		if (!"".equals(param)) {
			appointDTO = GsonUtil.getBeanFromJson(param, AppointInquiryDTO.class);
		}
		if (appointDTO.getAppointList() != null && appointDTO.getAppointList().size() > 0) {
			appointResultList = new ArrayList<AppointInquiryEntity>();
			for (AppointInquiryDTO paramAppoint : appointDTO.getAppointList()) {
				AppointInquiryEntity paramEntity = new AppointInquiryEntity();
				CommonUtil.reflectClass(paramAppoint, paramEntity);
				if (paramAppoint.getRecordsDTOs() != null && appointDTO.getAppointList().size() > 0) {
					recordsEntityList = new ArrayList<RecordsEntity>();
					for (RecordsDTO records : paramAppoint.getRecordsDTOs()) {
						RecordsEntity recordsEntity = new RecordsEntity();
						CommonUtil.reflectClass(records, recordsEntity);
						recordsEntityList.add(recordsEntity);

					}
					paramEntity.setRecordsEntitys(recordsEntityList);
				}
				appointResultList.add(paramEntity);
			}
		}
		return appointResultList;
	}

	/**
	 * 预约基因专家
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
	public ActionForward doAppointGeneDoctor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		// paramDTO.setPayType("1");
		AppointInquiryEntity paramEntity = new AppointInquiryEntity();
		// Test DATA
		// paramDTO.setAppointTime("2017-01-02 07:30");
		// paramDTO.setScheduleId(1);
		// paramDTO.setDoctorId(1);
		// paramDTO.setPackageId(1);
		// paramDTO.setGeneId(1);
		// paramDTO.setWorkType("0");
		// paramDTO.setPatientId(1);
		// 结果ClientDTO
		AppointInsertClientDTO resultClientDTO = new AppointInsertClientDTO();
		// test 0:视频1电话2私人
		CommonUtil.reflectClass(paramDTO, paramEntity);

		paramEntity.setWorkType(paramDTO.getWorkType());
		// 基因专家
		paramEntity.setAppointType("4");
		// 预约成功
		paramEntity.setPayStatus("1");
		// 视频的时候更新日程信息
		if (paramEntity.getScheduleId() != null && paramEntity.getScheduleId() != 0) {
			// 参数Entity
			// 视频更新日程
			DoctorScheduleEntity scheduleEntity = new DoctorScheduleEntity();
			scheduleEntity.setScheduleId(paramEntity.getScheduleId());
			DoctorScheduleEntity resultSchelduleEntity = new DoctorScheduleEntity();
			resultSchelduleEntity = iDoctorScheduleService.getObject(scheduleEntity);
			if (resultSchelduleEntity != null) {
				if (resultSchelduleEntity.getAppointNum() >= resultSchelduleEntity.getPatientNum()) {
					resultClientDTO.setReturnCode("-1041");
					toJson(response, resultClientDTO);
					return null;
				}
			}
		}
		// 预约时间格式化
		if (paramDTO.getAppointTime() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			paramEntity.setAppointTime(sdf.parse(paramDTO.getAppointTime()));
			paramEntity.setAppointDate(paramDTO.getAppointTime().substring(0, 7));
		}
		// DB查询
		try {
			// 获取医生所在科室和专业
			// 0:视频1电话2私人
			// 日期时间戳
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			StringBuffer stringBuffer = new StringBuffer();
			String str = sdf.format(date);
			stringBuffer.append(str);
			// 订单号（程序自动生成）
			OrderNumberUtil orderNumberUtil = new OrderNumberUtil();
			// 采番订单编号
			stringBuffer.append(orderNumberUtil.getOrderNumber(request));
			paramEntity.setOrderNumber(stringBuffer.toString());

			iAppointInquiryService.appointGeneDoctor(paramEntity);

			if (paramEntity.getAppointId() == null) {
				paramDTO.setErrFlg(true);
				resultClientDTO.setReturnCode("-7200");

			} else {
				resultClientDTO.setAppointId(paramEntity.getAppointId());
			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-7200");
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
	 * 预约基因专家
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
	public ActionForward doGetInqueryHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity

		AppointInquiryEntity paramEntity = new AppointInquiryEntity();
		// 结果Entity
		AppointInquiryEntity resultEntity = new AppointInquiryEntity();
		paramEntity.setAppointId(paramDTO.getAppointId());
		//
		// RecordsHistoryClientDTO resultClientDTO = new
		// RecordsHistoryClientDTO();
		DetectResultClientDTO resultClientDTO = new DetectResultClientDTO();
		List<DetectAnalysisClientDTO> resultAnalysisList = null;
		List<TestResultClientDTO> testResultList = null;
		try {
			RecordsEntity recordsEntity = new RecordsEntity();
			recordsEntity.setRecordsId(paramDTO.getRecordsId());
			recordsEntity.setInquiryStatus("2");
			iRecordsService.updateRecordsStatus(recordsEntity);
			resultEntity = iAppointInquiryService.getHistoryAppoint(paramEntity);
			if (resultEntity != null) {
				if (resultEntity.getDetectNum() != null) {
					// 获取基因检测结果
					TestResultMasterEntity testResultMasterEntity = new TestResultMasterEntity();
					testResultMasterEntity.setDetectNum(resultEntity.getDetectNum());
					TestResultMasterEntity masterResultEntity = new TestResultMasterEntity();
					masterResultEntity = iTestResultMasterService.getObject(testResultMasterEntity);
					if (masterResultEntity != null) {
						resultClientDTO.setEvaluate(masterResultEntity.getEvaluate());
						resultClientDTO.setDetectedName(masterResultEntity.getDetectedName());
						resultClientDTO.setAge(masterResultEntity.getAge());
						resultClientDTO.setSex(masterResultEntity.getSex());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						if (masterResultEntity.getDetectTime() != null) {
							resultClientDTO.setDetectTime(sdf.format(masterResultEntity.getDetectTime()));
						}
						// resultClientDTO.s
						// resultClientDTO.setRmId(resultEntity.getRmId());
						// 获取检查结果

						if (masterResultEntity.getTestResultEntitys() != null
								&& masterResultEntity.getTestResultEntitys().size() > 0) {
							testResultList = new ArrayList<TestResultClientDTO>();
							for (TestResultEntity param : masterResultEntity.getTestResultEntitys()) {
								TestResultClientDTO testResultClientDTO = new TestResultClientDTO();
								// CommonUtil.reflectClass(param,
								// testResultClientDTO);
								testResultClientDTO.setGeneName(param.getGeneName());
								testResultClientDTO.setRiskType(param.getRiskType());
								testResultClientDTO.setClassification(param.getClassification());
								testResultClientDTO.setDetectPoint(param.getDetectPoint());
								testResultList.add(testResultClientDTO);
							}
							resultClientDTO.setDetectResultList(testResultList);
						}

						// 结果分析
						DetectAnalysisEntity detectAnalysisEntity = new DetectAnalysisEntity();
						detectAnalysisEntity.setRmId(masterResultEntity.getRmId());
						List<DetectAnalysisEntity> resultAnalysis = null;
						resultAnalysis = iDetectAnalysisService.getDetectAnalysisList(detectAnalysisEntity);
						if (resultAnalysis != null && resultAnalysis.size() > 0) {
							resultAnalysisList = new ArrayList<DetectAnalysisClientDTO>();
							for (DetectAnalysisEntity param : resultAnalysis) {
								DetectAnalysisClientDTO analysisClientDTO = new DetectAnalysisClientDTO();
								analysisClientDTO.setDiseaseName(param.getDiseaseName());
								if (param.getDiseaseAnalysis() != null) {
									List<DiseaseAnalysisClientDTO> diseaseAnalysis = null;
									String[] gene = StringUtils.split(param.getDiseaseAnalysis(), ",");
									if (gene != null && gene.length > 0) {
										diseaseAnalysis = new ArrayList<DiseaseAnalysisClientDTO>();
										for (String str : gene) {
											String[] diseaseDetail = StringUtils.split(str, ":");
											if (diseaseDetail.length == 3) {
												DiseaseAnalysisClientDTO detailinfo = new DiseaseAnalysisClientDTO();
												for (int i = 0; i <= diseaseDetail.length; i++) {

													detailinfo.setGeneName(diseaseDetail[0]);
													detailinfo.setGeneType(diseaseDetail[1]);
													detailinfo.setRiskAdded(diseaseDetail[2]);

												}
												diseaseAnalysis.add(detailinfo);
											}
										}
									}
									analysisClientDTO.setDiseaseAnalysis(diseaseAnalysis);
								}
								resultAnalysisList.add(analysisClientDTO);
							}
							resultClientDTO.setDetectAnalysisList(resultAnalysisList);
						}

					}
				}

			}
			// 获取上一次就诊结果
			// AppointInquiryEntity param = new AppointInquiryEntity();
			// param.setAppointTime(resultEntity.getRecordsEntitys().get(0).getAppointTime());
			// param.setPatientId(resultEntity.getPatientId());
			// param.setAppointId(resultEntity.getAppointId());
			// AppointInquiryEntity paramResult = new AppointInquiryEntity();
			// paramResult = iAppointInquiryService.getHistoryAppoint(param);
			// if (paramResult != null &&) {
			//
			// }

			// if (paramResult.getDoctorId().equals(resultEntity.getDoctorId()))
			// {
			// if
			// ("3".equals(paramResult.getRecordsEntitys().get(0).getInquiryStatus()))
			// {
			//
			// TestResultMasterEntity testResultMasterEntity = new
			// TestResultMasterEntity();
			// TestResultMasterEntity testResult = new TestResultMasterEntity();
			// testResultMasterEntity.setDetectNum(paramResult.getDetectNum());
			// testResult =
			// iTestResultMasterService.getObject(testResultMasterEntity);
			// if (testResult != null) {
			// resultClientDTO.setAnalysis(paramResult.getRecordsEntitys().get(0).getAnalysis());
			// resultClientDTO.setGuidance(paramResult.getRecordsEntitys().get(0).getGuidance());
			// resultClientDTO.setAge(testResult.getAge());
			// resultClientDTO.setDetectedName(testResult.getDetectedName());
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// if (testResult.getDetectTime() != null) {
			// resultClientDTO.setDetectTime(sdf.format(testResult.getDetectTime()));
			// }
			// resultClientDTO.setSex(testResult.getSex());
			//
			// }
			// }}

			// }
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-7300");
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
	 * 预约图文问诊
	 */

	public ActionForward doAppointPhotoInquery(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		// paramDTO.setPayType("1");
		AppointInquiryEntity paramEntity = new AppointInquiryEntity();

		// 结果ClientDTO
		AppointInsertClientDTO resultClientDTO = new AppointInsertClientDTO();
		// test 0:视频1电话2私人
		// 参数DTO->参数Entity
		CommonUtil.reflectClass(paramDTO, paramEntity);
		if (paramDTO.getTotalFee() != null && "0".equals(paramDTO.getFreeFlag())) {
			paramEntity.setBuyTotal(Double.parseDouble(paramDTO.getTotalFee()));
			BigDecimal price = new BigDecimal(paramDTO.getTotalFee());
			BigDecimal mul = new BigDecimal("0.7");// 医生获取0.7的比例
			BigDecimal result = price.multiply(mul);// 相乘结果
			System.out.println(result);
			BigDecimal one = new BigDecimal("1");
			double doctorTotal = result.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue();// 保留1位数
			paramEntity.setDoctorTotal(doctorTotal);
		}

		// DB查询
		try {
			// 日期时间戳
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			StringBuffer stringBuffer = new StringBuffer();
			String str = sdf.format(date);
			stringBuffer.append(str);
			// 订单号（程序自动生成）
			OrderNumberUtil orderNumberUtil = new OrderNumberUtil();
			// 采番订单编号
			stringBuffer.append(orderNumberUtil.getOrderNumber(request));
			paramEntity.setOrderNumber(stringBuffer.toString());
			// 如果是0元设置成下单直接是支付状态
			if ("0".equals(paramDTO.getTotalFee()) || "0.0".equals(paramDTO.getTotalFee())
					|| "0.00".equals(paramDTO.getTotalFee())) {
				paramEntity.setPayStatus("1");
			}
			
			if("1".equals(paramDTO.getFreeFlag())){
				paramEntity.setPayStatus("1");
			}
			// 录入订单
			iAppointInquiryService.addAppointPhotoInquiry(paramEntity);

			if (paramEntity.getAppointId() == null) {
				paramDTO.setErrFlg(true);
				resultClientDTO.setReturnCode("-7300");
			}
			if (paramEntity.getAppointId() != null && paramEntity.getAppointId() != 0) {
				if (!"0".equals(paramDTO.getTotalFee()) && !"0.0".equals(paramDTO.getTotalFee())
						&& !"0.00".equals(paramDTO.getTotalFee())) {
					// resultClientDTO.setOrderNumber(paramEntity.getOrderNumber());
					resultClientDTO.setAppointId(paramEntity.getAppointId());
					String hostIp = ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS);
					// 支付宝支付
					if ("0".equals(paramDTO.getPayType())) {
						StringBuffer url = new StringBuffer();
						url.append(hostIp);
						url.append("Appoint.do?" + "receiveNotify" + "=");
						// url.append("&orderNumber=");
						url.append(stringBuffer.toString());
						String service_url = url.toString();

						String orderContent = AsynAlipayNotifyAction.getOrderInfo(stringBuffer.toString(), "购买图文问诊",
								"支付宝支付", paramDTO.getTotalFee(), service_url);
						String sign = sign(orderContent);
						try {
							// 签名
							sign = URLEncoder.encode(sign, "UTF-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						StringBuffer sb = new StringBuffer();
						sb.append(orderContent);
						sb.append("&sign=\"" + sign + "\"&");
						sb.append("sign_type=\"RSA\"");
						resultClientDTO.setAliPaySignInfo(sb.toString());
						AppointInquiryEntity payInfoEntity = new AppointInquiryEntity();
						payInfoEntity.setAppointId(paramEntity.getAppointId());
						payInfoEntity.setOrderNumber(paramEntity.getOrderNumber());
						payInfoEntity.setPayInfo(sb.toString());
						payInfoEntity.setPayType("0");
						iAppointInquiryService.updateAppointInquiry(payInfoEntity);
					} else if ("1".equals(paramDTO.getPayType())) {// 微信支付
						// 构造xml参数
						Unifiedorder unifiedorder = this.weChatParam(stringBuffer.toString(), "购买视频问诊",
								paramDTO.getTotalFee(), request);

						String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);

						String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

						String method = "POST";

						String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();

						UnifiedorderResult unifiedorderResult = JdomParseXmlUtils.getUnifiedorderResult(weixinPost);

						if ("SUCCESS".equals(unifiedorderResult.getReturn_code())) {
							// 再次验签
							UnifiedorderResult result = this.weChatParamAgain(unifiedorderResult);
							String reslutxml = GsonUtil.getJsonStringFormBean(result);
							String payInfo = HttpXmlUtils.xmlInfoJson(result);
							resultClientDTO.setAliPaySignInfo(reslutxml);
							AppointInquiryEntity payInfoEntity = new AppointInquiryEntity();
							payInfoEntity.setAppointId(paramEntity.getAppointId());
							payInfoEntity.setOrderNumber(paramEntity.getOrderNumber());
							payInfoEntity.setPayInfo(payInfo);
							payInfoEntity.setPayType("1");
							iAppointInquiryService.updateAppointInquiry(payInfoEntity);
						} else {
							paramDTO.setErrFlg(true);
							resultClientDTO.setReturnCode("-7300");
						}
					}
				}
			} else {
				paramDTO.setErrFlg(true);
				resultClientDTO.setReturnCode("-7300");
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-7300");
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
	 * 问诊结束更新
	 */

	public ActionForward doUpdateAppointInquery(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		AppointInquiryDTO paramDTO = (AppointInquiryDTO) form;
		// 参数Entity
		AppointInquiryEntity paramEntity = new AppointInquiryEntity();
		AppointInquiryEntity paramDoctorEntity = new AppointInquiryEntity();
		GraphicEntity graphicParamEntity = new GraphicEntity();
		
		// 结果ClientDTO
		AppointInsertClientDTO resultClientDTO = new AppointInsertClientDTO();
		// 参数DTO->参数Entity
		Calendar now = Calendar.getInstance();
		CommonUtil.reflectClass(paramDTO, paramEntity);

		// DB查询
		try {
			String docDel = paramDTO.getDocDel();
			String patDel = paramDTO.getPatDel();
			if (!"1".equals(docDel) && !"1".equals(patDel)) {
				
				paramEntity.setAppointId(paramDTO.getAppointId());
				// 服务次数加一
				paramDoctorEntity = iAppointInquiryService
						.getServiceCount(paramEntity);
				if (paramDoctorEntity!=null && paramDoctorEntity.getDoctorEntity()!=null) {
					DoctorEntity doctorEntity = new DoctorEntity();
					doctorEntity.setDoctorId(paramDoctorEntity.getDoctorEntity().getDoctorId());
						if (paramDoctorEntity.getDoctorEntity().getServiceCount() != null) {
							doctorEntity.setServiceCount(paramDoctorEntity.getDoctorEntity().getServiceCount() + 1);
						} else {
							doctorEntity.setServiceCount(1);
						}
						iDoctorService.updateDoctor(doctorEntity);
				}
				paramEntity.setUpdateTime(now.getTime());
				paramEntity.setPhotoEnd("1");
				
				iAppointInquiryService.updateAppointInquiry(paramEntity);
			} else {
				paramEntity.setAppointId(paramDTO.getAppointId());
				graphicParamEntity.setAppointId(paramDTO.getAppointId());
				paramEntity.setUpdateTime(now.getTime());
				if ("1".equals(docDel) ) {
					paramEntity.setDocDel("1");
					graphicParamEntity.setDeleteFlag("1");
				} else if ("1".equals(patDel)) {
					paramEntity.setPatDel("1");
					graphicParamEntity.setDeleteFlag("2");
				}
				iAppointInquiryService.updateAppointInquiry(paramEntity);
				iGraphicService.updateGraphicDeleteFlag(graphicParamEntity);
			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-8003");
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
