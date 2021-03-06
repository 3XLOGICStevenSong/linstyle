/**
 * Project Name:MedicalUnicomAPP
 * File Name:PatientAction.java
 * Package Name:com.djb.ylt.user.action
 * Copyright © 大连必捷必信息技术有限公司  All Rights Reserved.
*/

package com.djb.ylt.user.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

import com.djb.ylt.health.service.ISymptomService;
import com.djb.ylt.user.dto.DoctorDTO;
import com.djb.ylt.user.dtoclient.DoctorClientDTO;
import com.djb.ylt.user.dtoclient.DoctorListClientDTO;
import com.djb.ylt.user.dtoclient.DoctorPackageClientDTO;
import com.djb.ylt.user.dtoclient.DoctorPackageInfoClientDTO;
import com.djb.ylt.user.dtoclient.InqueryViewClientDTO;
import com.djb.ylt.user.dtoclient.InqueryViewListDTO;
import com.djb.ylt.user.dtoclient.TypicalCaseClient;
import com.djb.ylt.user.entity.AppointInquiryEntity;
import com.djb.ylt.user.entity.DoctorCommentEntity;
import com.djb.ylt.user.entity.DoctorEntity;

import com.djb.ylt.user.entity.InterrogationPackageEntity;
import com.djb.ylt.user.entity.TypicalCaseEntity;
import com.djb.ylt.user.service.IAppointInquiryService;
import com.djb.ylt.user.service.IDoctorCommentService;
import com.djb.ylt.user.service.IDoctorService;
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

@Controller("/Doctor")
public class DoctorAction extends BaseAction {

	@Autowired
	@Qualifier("iDoctorService")
	private IDoctorService iDoctorService;

	@Autowired
	@Qualifier("iDoctorCommentService")
	private IDoctorCommentService iDoctorCommentService;

	@Autowired
	@Qualifier("iSymptomService")
	private ISymptomService iSymptomService;

	@Autowired
	@Qualifier("iFollowInterestService")
	private IFollowInterestService iFollowInterestService;

	@Autowired
	@Qualifier("iAppointInquiryService")
	private IAppointInquiryService iAppointInquiryService;

	public DoctorAction() {
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
	public ActionForward doGetPackageByDoctor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		DoctorDTO paramDTO = (DoctorDTO) form;

		// 参数Entity
		DoctorEntity paramEntity = new DoctorEntity();

		// 结果ClientDTO
		DoctorPackageClientDTO resultClientDTO = new DoctorPackageClientDTO();

		// 参数DTO->参数Entity
		// test
		// paramDTO.setDoctorId(1);
		DoctorEntity resultEntity = new DoctorEntity();
		CommonUtil.reflectClass(paramDTO, paramEntity);

		List<DoctorPackageInfoClientDTO> paramResultInfo = null;

		// paramEntity.setGrade((Float) paramDTO.getGrade());
		// paramEntity.setDeleteFlg("0");
		// DB查询
		try {
			resultEntity = iDoctorService.getObject(paramEntity);
			if (resultEntity != null) {
				if (resultEntity.getCommentGrade() != null) {
					resultClientDTO.setCommentGrade((float) (Math.round(resultEntity.getCommentGrade() * 10)) / 10);
				}
				if (paramDTO.getPatientId() == null) {
					resultClientDTO.setFollowFlag("0");
				} else {
					if (resultEntity.getFollowCount() > 0) {
						resultClientDTO.setFollowFlag("1");
					} else {
						resultClientDTO.setFollowFlag("0");
					}
				}
				CommonUtil.reflectClass(resultEntity, resultClientDTO);
				if (resultEntity.getInterrogationEntitys() != null
						&& resultEntity.getInterrogationEntitys().size() > 0) {
					paramResultInfo = new ArrayList<DoctorPackageInfoClientDTO>();
					for (InterrogationPackageEntity param : resultEntity.getInterrogationEntitys()) {
						DoctorPackageInfoClientDTO packageInfoClientDTO = new DoctorPackageInfoClientDTO();
						packageInfoClientDTO.setCount(param.getCount());
						packageInfoClientDTO.setEffectTime(param.getEffectTime());
						packageInfoClientDTO.setPackageId(param.getPackageId());
						packageInfoClientDTO.setTelCount(param.getTelCount());
						if (param.getTotal() != null) {
							packageInfoClientDTO.setTotal(param.getTotal().toString());
						}
						packageInfoClientDTO.setType(param.getType());
						paramResultInfo.add(packageInfoClientDTO);
					}
					resultClientDTO.setPackageInfoList(paramResultInfo);
				}

			}
			// TODO SQL
			// 获取平均评分
			DoctorCommentEntity commenEntity = new DoctorCommentEntity();
			// DoctorCommentEntity commenresultEntity = new
			// DoctorCommentEntity();

			commenEntity.setDoctorId(paramDTO.getDoctorId());
			// commenresultEntity =
			// iDoctorCommentService.getAverageGrade(commenEntity);
			// if (commenresultEntity != null && commenresultEntity.getGrade()
			// != null) {
			// resultClientDTO.setCommentGrade((float)
			// (Math.round(commenresultEntity.getGrade() * 10)) / 10);
			// }
			// 获取评论条数
			int commentNum = iDoctorCommentService.getCount(commenEntity);
			if (commentNum > 0) {
				resultClientDTO.setCommentNum(commentNum);
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-1020");

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
	public ActionForward doGetAllDoctor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		DoctorDTO paramDTO = (DoctorDTO) form;

		// 参数Entity
		DoctorEntity paramEntity = new DoctorEntity();
		// 结果Entity
		List<DoctorEntity> resultEntity = null;

		// 结果ClientDTO
		// BaseClientDTO resultsClientDTO = new BaseClientDTO();

		// DoctorListClientDTO
		DoctorListClientDTO resultsClientDTO = new DoctorListClientDTO();
		// 参数DTO->参数Entity

		List<DoctorClientDTO> doctorClientListDTO = null;

		// CommonUtil.reflectClass(paramDTO, paramEntity);

		// paramEntity.setDoctorId(2);

		// DB查询
		try {
			// 获取医生的信息 看是否
			paramEntity.setPatientId(paramDTO.getPatientId());
			// paramEntity.setPatientId(1);
			resultEntity = iDoctorService.getAllDoctorList(paramEntity);
			// resultEntity = iDoctorService.getDoctorList();
			if (resultEntity != null && resultEntity.size() > 0) {
				doctorClientListDTO = new ArrayList<DoctorClientDTO>();
				for (DoctorEntity param : resultEntity) {
					DoctorClientDTO doctorClientDTO = new DoctorClientDTO();
					if (paramDTO.getPatientId() == null) {
						doctorClientDTO.setFollowFlag("0");
					} else {
						if (param.getFollowInterestEntitys() != null && param.getFollowInterestEntitys().size() > 0) {
							doctorClientDTO.setFollowFlag("1");
						} else {
							doctorClientDTO.setFollowFlag("0");
						}
					}
					doctorClientDTO.setDoctorId(param.getDoctorId());
					doctorClientDTO.setName(param.getName());
					doctorClientDTO.setDcName(param.getDcName());
					doctorClientDTO.setDepartmentName(param.getDepartmentName());
					doctorClientDTO.setHealDisease(param.getHealDisease());
					doctorClientDTO.setPositional(param.getPositional());
					doctorClientDTO.setHospitalName(param.getHospitalName());
					doctorClientDTO.setHeadPic(param.getHeadPic());
					doctorClientDTO.setServiceCount(param.getServiceCount());
					doctorClientDTO.setScheduleFlag(param.getScheduleFlag());
					doctorClientDTO.setServiceType(param.getServiceType());
					if (param.getMinTotal() != null) {
						doctorClientDTO.setMinTotal(param.getMinTotal().toString());
					}
					if (param.getCommentGrade() != null) {
						doctorClientDTO.setCommentGrade((float) (Math.round(param.getCommentGrade() * 10)) / 10);
					}
					doctorClientListDTO.add(doctorClientDTO);
				}
				resultsClientDTO.setDoctorInfoList(doctorClientListDTO);
			}

			// 给客户端结果赋值
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultsClientDTO.setReturnCode("-5000");

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
	public ActionForward doUpdateDoctorInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		DoctorDTO paramDTO = (DoctorDTO) form;

		// 参数Entity
		DoctorEntity paramEntity = new DoctorEntity();
		// 结果Entity
		// DoctorEntity resultEntity = new DoctorEntity();

		// 结果ClientDTO
		BaseClientDTO resultsClientDTO = new BaseClientDTO();
		// DoctorClientDTO resultsClientDTO=new DoctorClientDTO();
		// 参数DTO->参数Entity

		CommonUtil.reflectClass(paramDTO, paramEntity);
		// paramEntity.setDoctorId(paramDTO.getDoctorId());
		// paramEntity.setHealDisease(paramDTO.getHealDisease());
		// paramEntity.setDoctorId(2);
		// paramEntity.setHealDisease("主要治疗各种疑难杂症");
		// DB查询
		try {

			// 获取医生的信息 看是否
			if (paramDTO.getHeadPic() != null) {
				DoctorEntity picParam = new DoctorEntity();
				picParam.setDoctorId(paramDTO.getDoctorId());
				DoctorEntity picResultParam = new DoctorEntity();
				picResultParam = iDoctorService.getObject(picParam);
				// 删除原来的图片
				if (picResultParam != null && picResultParam.getHeadPic() != null) {
					String[] args = StringUtils.split(picResultParam.getHeadPic(), "/");
					StringBuffer strBuffer = new StringBuffer();
					if (args.length > 1) {
						strBuffer.append(args[args.length - 2]);
						strBuffer.append("/");
						strBuffer.append(args[args.length - 1]);
						// System.out.println("str>>>>>" +
						// strBuffer.toString());
					}
					if (strBuffer != null) {
						String fileName = strBuffer.toString();
						UploadUtil.doDeleteFile(request, response, fileName);
					}
				}
				// 获取医生的信息 看是否
				if (paramDTO.getHeadPic() != null) {
					String userId = paramDTO.getDoctorId().toString() + "d";
					paramDTO.setHeadPic(paramDTO.getHeadPic().replace("<", ""));
					paramDTO.setHeadPic(paramDTO.getHeadPic().replace(">", ""));
					String filePath = UploadUtil.doUpload(request, response, paramDTO.getHeadPic(), userId);
					paramEntity.setHeadPic(
							ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS) + filePath);
				}
			}
			iDoctorService.updateDoctor(paramEntity);

			// 给客户端结果赋值

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultsClientDTO.setReturnCode("-2052");

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
	public ActionForward doGetDoctorInfoById(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		DoctorDTO paramDTO = (DoctorDTO) form;

		// 参数Entity
		DoctorEntity paramEntity = new DoctorEntity();
		// 结果Entity
		DoctorEntity resultEntity = new DoctorEntity();

		// 结果ClientDTO
		// BaseClientDTO resultsClientDTO = new BaseClientDTO();
		DoctorClientDTO resultsClientDTO = new DoctorClientDTO();
		// 参数DTO->参数Entity

		CommonUtil.reflectClass(paramDTO, paramEntity);

		// paramEntity.setDoctorId(2);

		// DB查询
		try {
			// 获取医生的信息 看是否

			resultEntity = iDoctorService.getObject(paramEntity);
			//
			if (resultEntity != null) {
				CommonUtil.reflectClass(resultEntity, resultsClientDTO);
			}

			// 给客户端结果赋值

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultsClientDTO.setReturnCode("-3070");

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
	public ActionForward doGetDoctorDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		DoctorDTO paramDTO = (DoctorDTO) form;

		// 参数Entity
		DoctorEntity paramEntity = new DoctorEntity();
		// 结果Entity
		DoctorEntity resultEntity = new DoctorEntity();

		// 结果ClientDTO
		// BaseClientDTO resultsClientDTO = new BaseClientDTO();
		DoctorClientDTO resultsClientDTO = new DoctorClientDTO();
		// 参数DTO->参数Entity

		CommonUtil.reflectClass(paramDTO, paramEntity);

		// paramEntity.setDoctorId(2);

		// DB查询
		try {
			// 获取医生的信息 看是否
			// TODO SQL
			resultEntity = iDoctorService.getDoctorInfo(paramEntity);
			//
			if (resultEntity != null) {

				CommonUtil.reflectClass(resultEntity, resultsClientDTO);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if (resultEntity.getAge() != null) {
					resultsClientDTO.setAge(sdf.format(resultEntity.getAge()));
				}
				resultsClientDTO.setFollowCount(resultEntity.getFollowCount());
				// if (resultEntity.getDepartmentEntity() != null) {
				// resultsClientDTO.setDepartmentName(resultEntity.getDepartmentEntity().getDepName());
				// }
				// if (resultEntity.getDoctorSymptomEntitys() != null
				// && resultEntity.getDoctorSymptomEntitys().size() > 0) {
				// StringBuffer nameString = new StringBuffer();
				// for (DoctorSymptomEntity param :
				// resultEntity.getDoctorSymptomEntitys()) {
				// SymptomEntity symptomEntity = new SymptomEntity();
				// SymptomEntity symptomResult = new SymptomEntity();
				// symptomEntity.setSymptomId(param.getSymptomId());
				// symptomResult = iSymptomService.getObject(symptomEntity);
				// if (symptomResult != null) {
				// nameString.append(symptomResult.getSymptomName() + "|");
				// }
			}
			// if (nameString != null) {

			// String nameParam = nameString.toString();

			// resultsClientDTO.setSymptomName(nameParam.substring(0,
			// nameParam.length() - 1));
			// }
			// }
			// }

			// 给客户端结果赋值

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultsClientDTO.setReturnCode("-2058");

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
	public ActionForward doGetDoctorInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		DoctorDTO paramDTO = (DoctorDTO) form;

		// 参数Entity
		DoctorEntity paramEntity = new DoctorEntity();
		// 结果Entity
		DoctorEntity resultEntity = new DoctorEntity();

		// 结果ClientDTO
		// BaseClientDTO resultsClientDTO = new BaseClientDTO();
		DoctorClientDTO resultsClientDTO = new DoctorClientDTO();
		// 参数DTO->参数Entity

		CommonUtil.reflectClass(paramDTO, paramEntity);

		// paramEntity.setDoctorId(2);

		// DB查询
		try {
			// 获取医生的信息 看是否

			resultEntity = iDoctorService.getObject(paramEntity);
			//
			if (resultEntity != null && resultEntity.getHealDisease() != null) {
				resultsClientDTO.setHealDisease(resultEntity.getHealDisease());
			} else {
				paramDTO.setErrFlg(true);
				resultsClientDTO.setReturnCode("-2051");
			}

			// 给客户端结果赋值

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

		return null;
	}

	/**
	 * 通过科室获取 医生
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
	public ActionForward doGetDoctorListByDepId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		DoctorDTO paramDTO = (DoctorDTO) form;

		// 参数Entity
		DoctorEntity paramEntity = new DoctorEntity();
		// 结果Entity
		List<DoctorEntity> resultEntity = null;

		// 结果ClientDTO
		InqueryViewListDTO resultClientDTO = new InqueryViewListDTO();
		List<InqueryViewClientDTO> inqueryViewList = null;
		// List<SymptomClientDTO> SymptomClientDTOList=null;
		// 参数DTO->参数Entity

		CommonUtil.reflectClass(paramDTO, paramEntity);
		paramEntity.setPageSize(paramDTO.getPageSize());
		paramEntity.setPageNum(paramDTO.getPageNum());
		paramEntity.setPatientId(paramDTO.getPatientId());
		// paramEntity.setPatientId(1);
		// DB查询
		try {
			// 获取医生的信息 看是否

			resultEntity = iDoctorService.getDoctorListByDepId(paramEntity);
			//
			if (resultEntity != null && resultEntity.size() > 0) {
				inqueryViewList = new ArrayList<InqueryViewClientDTO>();
				for (DoctorEntity param : resultEntity) {
					InqueryViewClientDTO paramClientDTO = new InqueryViewClientDTO();
					CommonUtil.reflectClass(param, paramClientDTO);
					if (param.getInterrogationEntitys() != null && param.getInterrogationEntitys().size() > 0) {
						paramClientDTO.setTotal(param.getInterrogationEntitys().get(0).getTotal().toString());
					} else {
						paramClientDTO.setTotal("0");
					}
					if (paramDTO.getPatientId() == null) {
						paramClientDTO.setFollowFlag("0");
					} else {
						if (param.getFollowInterestEntitys() != null && param.getFollowInterestEntitys().size() > 0) {

							paramClientDTO.setFollowFlag("1");
						} else {
							paramClientDTO.setFollowFlag("0");
						}
					}
					if (param.getCommentGrade() != null) {
						paramClientDTO.setCommentGrade((float) (Math.round(param.getCommentGrade() * 10)) / 10);
					}

					if (param.getScheduleFlag() != null) {
						paramClientDTO.setScheduleFlag(param.getScheduleFlag());
					}
					paramClientDTO.setServiceType(param.getServiceType());

					paramClientDTO.setServiceCount(param.getServiceCount());
					inqueryViewList.add(paramClientDTO);
				}

				resultClientDTO.setDoctorList(inqueryViewList);
				// 给客户端结果赋值

			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-3000");

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
	public ActionForward doGetPackageByDoctorNew(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		DoctorDTO paramDTO = (DoctorDTO) form;

		// 参数Entity
		DoctorEntity paramEntity = new DoctorEntity();

		// 结果ClientDTO
		DoctorPackageClientDTO resultClientDTO = new DoctorPackageClientDTO();

		// 参数DTO->参数Entity
		// test
		// paramDTO.setDoctorId(1);
		DoctorEntity resultEntity = new DoctorEntity();
		CommonUtil.reflectClass(paramDTO, paramEntity);

		// List<DoctorPackageInfoClientDTO> paramResultInfo = null;
		// paramEntity.setGrade((Float) paramDTO.getGrade());
		// paramEntity.setDeleteFlg("0");
		// DB查询
		try {
			resultEntity = iDoctorService.getDoctoForInquery(paramEntity);
			if (resultEntity != null) {
				if (resultEntity.getCommentGrade() != null) {
					resultClientDTO.setCommentGrade((float) (Math.round(resultEntity.getCommentGrade() * 10)) / 10);
				}
				// if(resultEntity.getCommentCount())

				if (paramDTO.getPatientId() == null) {
					resultClientDTO.setFollowFlag("0");
					// 没用过免费
					resultClientDTO.setFreeStatus("1");

				} else {
					if (resultEntity.getFollowCount() > 0) {
						resultClientDTO.setFollowFlag("1");
					} else {
						resultClientDTO.setFollowFlag("0");
					}

					AppointInquiryEntity appointEntity = new AppointInquiryEntity();
					AppointInquiryEntity appointResult = new AppointInquiryEntity();
					appointEntity.setPatientId(paramDTO.getPatientId());
					appointEntity.setDoctorId(paramDTO.getDoctorId());
					appointResult = iAppointInquiryService.getAppointFree(appointEntity);
					if (appointResult != null) {
						// 用过免费的
						resultClientDTO.setFreeStatus("0");
						if (appointResult.getMaxChatTime() != null) {
							Date insertDate = null;
							Date now = null;
							Calendar cal = Calendar.getInstance();

							cal.setTime(appointResult.getMaxChatTime());
							cal.add(Calendar.DATE, 1);// 24小时制
							insertDate = cal.getTime();

							cal.setTime(new Date());
							now = cal.getTime();
							int time = now.compareTo(insertDate);
							if (time > 0) {
								resultClientDTO.setSupTotal(0);
							} else {
								if (appointResult.getGraphicCount() != null) {
									resultClientDTO.setSupTotal(
											appointResult.getFreeTotal() - appointResult.getGraphicCount());
								}
							}
						} else {
							resultClientDTO.setSupTotal(appointResult.getFreeTotal());
						}

					} else {
						resultClientDTO.setFreeStatus("1");
					}
				}

				CommonUtil.reflectClass(resultEntity, resultClientDTO);
				resultClientDTO.setCommentNum(resultEntity.getCommentCount());
				resultClientDTO.setDoctorTel(resultEntity.getDoctorTel());
				// 获取医生套餐信息
				List<DoctorPackageInfoClientDTO> paramResultInfo = null;
				if (resultEntity.getInterrogationEntitys() != null
						&& resultEntity.getInterrogationEntitys().size() > 0) {
					paramResultInfo = new ArrayList<DoctorPackageInfoClientDTO>();
					for (InterrogationPackageEntity param : resultEntity.getInterrogationEntitys()) {
						DoctorPackageInfoClientDTO packageInfoClientDTO = new DoctorPackageInfoClientDTO();
						packageInfoClientDTO.setWorkType(param.getWorkType());
						packageInfoClientDTO.setEarlyTime(param.getEarlyTime());
						packageInfoClientDTO.setPackageId(param.getPackageId());
						if (param.getTotal() != null) {
							packageInfoClientDTO.setTotal(param.getTotal().toString());
						}
						packageInfoClientDTO.setType(param.getType());
						if ("3".equals(param.getType())) {
							packageInfoClientDTO.setFreeTotal(param.getFreeTotal());
						}
						// packageInfoClientDTO.setType(param.getType());
						paramResultInfo.add(packageInfoClientDTO);
					}
					resultClientDTO.setPackageInfoList(paramResultInfo);
				}
				List<TypicalCaseClient> typicalCaseClient = null;
				if (resultEntity.getTypicalCaseEntitys() != null && resultEntity.getTypicalCaseEntitys().size() > 0) {
					typicalCaseClient = new ArrayList<TypicalCaseClient>();
					for (TypicalCaseEntity param : resultEntity.getTypicalCaseEntitys()) {
						TypicalCaseClient typicalClient = new TypicalCaseClient();
						if (param.getTcId() != null) {
							typicalClient.setTypicalId(param.getTcId());
							typicalClient.setCaseName(param.getCaseName());
							typicalCaseClient.add(typicalClient);
						}
					}
					if (typicalCaseClient != null && typicalCaseClient.size() > 0) {
						resultClientDTO.setTypicalCaseList(typicalCaseClient);
					}
				}
				// TODO SQL
				// 获取评论条数
				// DoctorCommentEntity commenEntity = new DoctorCommentEntity();
				//
				// commenEntity.setDoctorId(paramDTO.getDoctorId());
				//
				// int commentNum =
				// iDoctorCommentService.getCount(commenEntity);
				// if (commentNum > 0) {
				// resultClientDTO.setCommentNum(commentNum);
				// }

			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-1020");

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
	 * 获取基因专家列表
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
	public ActionForward doGetGeneDoctorList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		DoctorDTO paramDTO = (DoctorDTO) form;

		// 参数Entity
		DoctorEntity paramEntity = new DoctorEntity();
		// 结果Entity
		List<DoctorEntity> resultEntity = null;

		// 结果ClientDTO
		InqueryViewListDTO resultClientDTO = new InqueryViewListDTO();
		List<InqueryViewClientDTO> inqueryViewList = null;
		// 参数DTO->参数Entity
		paramEntity.setPageSize(paramDTO.getPageSize());
		paramEntity.setPageNum(paramDTO.getPageNum());
		paramEntity.setPatientId(paramDTO.getPatientId());
		paramEntity.setDoctorType("1");
		// paramEntity.setPatientId(1);
		// DB查询
		try {
			// 获取基因医生

			resultEntity = iDoctorService.findListForGene(paramEntity);
			//
			if (resultEntity != null && resultEntity.size() > 0) {
				inqueryViewList = new ArrayList<InqueryViewClientDTO>();
				for (DoctorEntity param : resultEntity) {
					InqueryViewClientDTO paramClientDTO = new InqueryViewClientDTO();
					CommonUtil.reflectClass(param, paramClientDTO);
					if (paramDTO.getPatientId() == null) {
						paramClientDTO.setFollowFlag("0");
					} else {
						if (param.getFollowInterestEntitys() != null && param.getFollowInterestEntitys().size() > 0) {
							paramClientDTO.setFollowFlag("1");
						} else {
							paramClientDTO.setFollowFlag("0");
						}
					}
					if (param.getCommentGrade() != null) {
						paramClientDTO.setCommentGrade((float) (Math.round(param.getCommentGrade() * 10)) / 10);
					}
					if (param.getServiceCount() != null) {
						paramClientDTO.setServiceCount(param.getServiceCount());
					} else {
						paramClientDTO.setServiceCount(0);
					}
					paramClientDTO.setServiceType(param.getServiceType());
					paramClientDTO.setScheduleFlag(param.getScheduleFlag());
					inqueryViewList.add(paramClientDTO);
				}
				resultClientDTO.setDoctorList(inqueryViewList);
				// 给客户端结果赋值

			}
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-7100");

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
