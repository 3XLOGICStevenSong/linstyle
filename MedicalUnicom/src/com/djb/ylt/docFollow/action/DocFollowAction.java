package com.djb.ylt.docFollow.action;

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

import com.djb.ylt.docFollow.dto.DocFollowDTO;
import com.djb.ylt.docFollow.dto.FollowDocDTO;
import com.djb.ylt.docFollow.dto.FollowResultClientDTO;
import com.djb.ylt.docFollow.entity.DocFollowEntity;
import com.djb.ylt.docFollow.service.IDocFollowService;
import com.djb.ylt.framework.action.BaseAction;
import com.djb.ylt.framework.dto.BaseClientDTO;

@Controller("/DocFollowInterest")
public class DocFollowAction extends BaseAction {

	@Autowired
	@Qualifier("iDocFollowService")
	private IDocFollowService iDocFollowService;

	public DocFollowAction() {
		super();
	}

	// 医生端 - 获取关注登录医生的患者列表
	public ActionForward doGetDocFollowList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		DocFollowDTO paramDTO = (DocFollowDTO) form;
		// 参数Entity
		DocFollowEntity paramEntity = new DocFollowEntity();
		// 结果Entity
		List<DocFollowEntity> resultEntity = null;

		// 结果ClientDTO
		// BaseClientDTO resultsClientDTO = new BaseClientDTO();

		// 结果ClientDTO
		FollowResultClientDTO resultClientDTO = new FollowResultClientDTO();
		// List<DocFollowDTO> docFollowList = null;

		List<FollowDocDTO> inqueryViewList = null;

		// CommonUtil.reflectClass(paramDTO, paramEntity);

		// paramEntity.setDoctorId(2);

		// DB查询
		try {
			// 参数赋值
			paramEntity.setDoctorId(paramDTO.getDoctorId());
			// paramEntity.setPageNum(paramDTO.getPageNum());
			// paramEntity.setPageSize(paramDTO.getPageSize());
			// paramEntity.setMethodFlg(paramDTO.getMethodFlg());
			// paramEntity.setSortType(paramDTO.getSortType());
			resultEntity = iDocFollowService.getList(paramEntity);
			//
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (resultEntity != null && resultEntity.size() > 0) {
				inqueryViewList = new ArrayList<FollowDocDTO>();
				for (DocFollowEntity param : resultEntity) {
					FollowDocDTO paramClientDTO = new FollowDocDTO();
					if (param.getPaitentEntity() != null) {
						paramClientDTO.setPatientId(param.getPaitentEntity().getPatientId());
						paramClientDTO.setName(param.getPaitentEntity().getName());
						paramClientDTO.setHeadPic(param.getPaitentEntity().getPatientPic());

						if (param.getPaitentEntity().getBirth() != null) {

							String birthStr = sdf.format(param.getPaitentEntity().getBirth());
							paramClientDTO.setBirth(birthStr);

						}
						paramClientDTO.setSex(param.getPaitentEntity().getSex());
						if (paramDTO.getDoctorId() == null) {
							paramClientDTO.setFollowFlag("0");
						} else {
							paramClientDTO.setFollowFlag(param.getFollowFlag());
						}

						// if(param.getMinTotal()!=null){
						// paramClientDTO.setMinTotal(param.getMinTotal().toString());
						// }
						// if (param.getCommentGrade() != null) {
						// paramClientDTO.setCommentGrade((float)
						// (Math.round(param.getCommentGrade() * 10)) / 10);
						// }
					}
					inqueryViewList.add(paramClientDTO);
				}
				resultClientDTO.setDoctorList(inqueryViewList);
			}

			// 给客户端结果赋值
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-8010");
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

	// 医生端 - 获取登录医生关注的患者列表
	public ActionForward doGetFollowDocList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		DocFollowDTO paramDTO = (DocFollowDTO) form;
		// 参数Entity
		DocFollowEntity paramEntity = new DocFollowEntity();
		// 结果Entity
		List<DocFollowEntity> resultEntity = null;

		// 结果ClientDTO
		// BaseClientDTO resultsClientDTO = new BaseClientDTO();

		// 结果ClientDTO
		FollowResultClientDTO resultClientDTO = new FollowResultClientDTO();
		// List<DocFollowDTO> docFollowList = null;

		List<FollowDocDTO> inqueryViewList = null;

		// CommonUtil.reflectClass(paramDTO, paramEntity);

		// paramEntity.setDoctorId(2);

		// DB查询
		try {
			// 参数赋值
			paramEntity.setDoctorId(paramDTO.getDoctorId());
			// paramEntity.setPageNum(paramDTO.getPageNum());
			// paramEntity.setPageSize(paramDTO.getPageSize());
			// paramEntity.setMethodFlg(paramDTO.getMethodFlg());
			// paramEntity.setSortType(paramDTO.getSortType());
			resultEntity = iDocFollowService.getFollowList(paramEntity);
			//
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (resultEntity != null && resultEntity.size() > 0) {
				inqueryViewList = new ArrayList<FollowDocDTO>();
				for (DocFollowEntity param : resultEntity) {
					FollowDocDTO paramClientDTO = new FollowDocDTO();
					if (param.getPaitentEntity() != null ) {
						paramClientDTO.setPatientId(param.getPaitentEntity().getPatientId());
						paramClientDTO.setName(param.getPaitentEntity().getName());
						paramClientDTO.setHeadPic(param.getPaitentEntity().getPatientPic());
						// paramClientDTO.setBirth(param.getPaitentEntity().getBirth());

						if (param.getPaitentEntity().getBirth() != null) {
							String birthStr = sdf.format(param.getPaitentEntity().getBirth());
							paramClientDTO.setBirth(birthStr);
						}
						paramClientDTO.setSex(param.getPaitentEntity().getSex());
						paramClientDTO.setTel(param.getPaitentEntity().getPatientTel());
						if (param.getPaitentEntity().getAppointId() != null) {
							paramClientDTO.setAppointId(param.getPaitentEntity().getAppointId());
						}
						// if (param.getCommentGrade() != null) {
						// paramClientDTO.setCommentGrade((float)
						// (Math.round(param.getCommentGrade() * 10)) / 10);
						// }
					}
					inqueryViewList.add(paramClientDTO);
				}
				resultClientDTO.setDoctorList(inqueryViewList);
			}

			// 给客户端结果赋值
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-8011");
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
	 * 添加关注
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
	public ActionForward doAddFollow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		DocFollowDTO paramDTO = (DocFollowDTO) form;

		// 画面项目check

		// 参数Entity
		DocFollowEntity paramEntity = new DocFollowEntity();
		// 结果ClientDTO
		BaseClientDTO BaseClientDTO = new BaseClientDTO();
		// 参数赋值
		paramEntity.setPatientId(paramDTO.getPatientId());
		paramEntity.setDoctorId(paramDTO.getDoctorId());

		// DB查询
		try {
			// 参数Entity
			DocFollowEntity InfoEntity = new DocFollowEntity();
			InfoEntity.setPatientId(paramDTO.getPatientId());
			InfoEntity.setDoctorId(paramDTO.getDoctorId());
			// 判断要添加的患者是否存在于DB中
			DocFollowEntity resultEntity = iDocFollowService.getObject(InfoEntity);
			if (resultEntity == null) {
				iDocFollowService.addFollowInterest(paramEntity);
			} else {
				paramDTO.setErrFlg(true);
				BaseClientDTO.setReturnCode("-5011");
			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			BaseClientDTO.setReturnCode("-5010");

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
	 * 取消关注
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
	public ActionForward doDeleteFollow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		DocFollowDTO paramDTO = (DocFollowDTO) form;

		// 画面项目check

		// 参数Entity
		DocFollowEntity paramEntity = new DocFollowEntity();

		// 结果ClientDTO
		BaseClientDTO BaseClientDTO = new BaseClientDTO();
		// 参数赋值
		paramEntity.setPatientId(paramDTO.getPatientId());
		paramEntity.setDoctorId(paramDTO.getDoctorId());

		// DB查询
		try {
			iDocFollowService.deleteFollowInterest(paramEntity);

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			BaseClientDTO.setReturnCode("-5020");

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
}
