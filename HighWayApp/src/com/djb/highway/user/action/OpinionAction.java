package com.djb.highway.user.action;

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

import com.djb.highway.common.util.CommonUtil;
import com.djb.highway.common.util.Constants;
import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.framework.dto.BaseDTO;
import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.user.dto.OpinionDTO;
import com.djb.highway.user.entity.OpinionEntity;
import com.djb.highway.user.service.IOpinionService;

@Controller("/Opinion")
public class OpinionAction extends BaseAction {

	@Autowired
	@Qualifier("iOpinionService")
	private IOpinionService iOpinionService;

	public OpinionAction() {
		super();
	}

	/**
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
	@SuppressWarnings("unchecked")
	public ActionForward doGetOpinionList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		OpinionDTO opinionDTO = (OpinionDTO) form;

		// 結果取得用DTO
		List<OpinionDTO> list = new ArrayList<OpinionDTO>();
		List<OpinionEntity> resultList = new ArrayList<OpinionEntity>();

		// page info
		OpinionEntity paramEntity = new OpinionEntity();
		// test
		opinionDTO.setStartRow(1);
		opinionDTO.setEndRow(10);
		// ;
		// BeanUtils.copyProperties(paramEntity, userDTO);
		CommonUtil.reflectClass(opinionDTO, paramEntity);
		// do page;
		PageModel pageModel = iOpinionService.findPagedList(paramEntity);
		resultList = (List<OpinionEntity>) pageModel.getList();
		// ;
		// do find All list
		// resultList = iUserService.getUserList();
		// ;

		for (OpinionEntity opinionEntity : resultList) {
			OpinionDTO _opinionDTO = new OpinionDTO();
			// BeanUtils.copyProperties(_userDTO, userEntity);
			CommonUtil.reflectClass(opinionEntity, _opinionDTO);
			list.add(_opinionDTO);
		}

		opinionDTO.setList(list);

		return null;

	}

	/**
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
	public ActionForward doSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return null;

	}

	/**
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
	public ActionForward doInsert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 参数DTO
		OpinionDTO opinionDTO = (OpinionDTO) form;
		// 用于返回操作状态
		BaseDTO baseDTO = new BaseDTO();
		// test
		// opinionDTO.setUser_code("13591277216");
		//
		// opinionDTO.setUser_id(8);
		//
		// opinionDTO.setContent("发表意见，意见保留！");

		OpinionEntity opinionEntity = new OpinionEntity();
		// BeanUtils.copyProperties(userEntity, userDTO);
		CommonUtil.reflectClass(opinionDTO, opinionEntity);
		try {
			iOpinionService.addOpinion(opinionEntity);
		} catch (Exception e) {
			 baseDTO.setReturnCode("-401");
			// opinionDTO = new OpinionDTO();
			// opinionDTO.setErrFlg(true);
			baseDTO.setErrFlg(true);
			//formMessages(baseDTO, Constants.MSG_KEY_OPINION_ERROR,
			//		null);
		}
		if (!baseDTO.isErrFlg()) {
			baseDTO.setReturnCode("0");
		}
		// android
		toJson(response, baseDTO);
		// ;
		return null;

	}

	/**
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
	public ActionForward doDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return null;

	}

	/**
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
	public ActionForward doDeleteBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return null;

	}

	/**
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
	public ActionForward doUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return null;

	}

	/**
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
	public ActionForward doUpdateBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return null;

	}

	/**
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
	public ActionForward editView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;

	}

}
