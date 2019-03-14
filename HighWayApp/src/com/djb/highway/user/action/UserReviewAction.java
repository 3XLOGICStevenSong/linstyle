package com.djb.highway.user.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.user.dto.UserReviewDTO;
import com.djb.highway.user.entity.UserReviewEntity;
import com.djb.highway.user.service.IUserReviewService;
import com.google.gson.Gson;

@Controller("/UserReview")
public class UserReviewAction extends BaseAction {

	@Autowired
	@Qualifier("iUserReviewService")
	private IUserReviewService iUserReviewService;

	public UserReviewAction() {
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
	public ActionForward doGetUserReviewList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserReviewDTO userReviewDTO = (UserReviewDTO) form;

		// 結果取得用DTO
		List<UserReviewDTO> list = new ArrayList<UserReviewDTO>();
		List<UserReviewEntity> resultList = iUserReviewService
				.getUserReviewList();

		// page info
		UserReviewEntity paramEntity = new UserReviewEntity();
		// test
		userReviewDTO.setStartRow(1);
		userReviewDTO.setEndRow(10);
		// ;
		// BeanUtils.copyProperties(paramEntity, userDTO);
		CommonUtil.reflectClass(userReviewDTO, paramEntity);
		// do page;
		PageModel pageModel = iUserReviewService.findPagedList(paramEntity);
		resultList = (List<UserReviewEntity>) pageModel.getList();
		// ;
		// do find All list
		// resultList = iUserService.getUserList();
		// ;

		for (UserReviewEntity userEntity : resultList) {
			logger.info("The user userid is: {}", userEntity.getU_id());
			UserReviewDTO _userDTO = new UserReviewDTO();
			// BeanUtils.copyProperties(_userDTO, userEntity);
			CommonUtil.reflectClass(userEntity, _userDTO);
			list.add(_userDTO);
		}

		userReviewDTO.setList(list);

		// show error message
		userReviewDTO.setErrFlg(true);
		userReviewDTO.getErrorMessageList().add(
				userReviewDTO.new MessageData("err msg1", "err msg1"));
		userReviewDTO.getErrorMessageList().add(
				userReviewDTO.new MessageData("err msg2", "err msg2"));
		// ;

		// android
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(userReviewDTO));
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

		UserReviewDTO userReviewDTO = (UserReviewDTO) form;
		userReviewDTO.setUr_deploy_time(new Date());
		UserReviewEntity userReviewEntity = new UserReviewEntity();
		// BeanUtils.copyProperties(userEntity, userDTO);
		CommonUtil.reflectClass(userReviewDTO, userReviewEntity);
		try {
			iUserReviewService.addUserReview(userReviewEntity);
		} catch (Exception e) {

			userReviewDTO = new UserReviewDTO();
			userReviewDTO.setErrFlg(true);
			formMessages(userReviewDTO,
					Constants.MSG_KEY_INSERT_USERREVIEW_ERROR, null);
		}

		// android
		toJson(response, userReviewDTO);
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
	public ActionForward doInsertReview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		UserReviewDTO userReviewDTO = (UserReviewDTO) form;
		//图片添加的时间
		userReviewDTO.setUr_deploy_time(new Date());
		userReviewDTO.setUr_status(0);
		//test
		//评论者ID
//		userReviewDTO.setU_id(1);
		//话题主题ID
//		userReviewDTO.setUdp_id(4);
		//评论内容
//		userReviewDTO.setUr_content("test content");
		//回复对象ID
//		userReviewDTO.setReply_u_id(1);
		
//		获取评论对象的用户名称
//		获取发布评论的用户名称
//		userReviewDTO.setU_name("name1");
//		userReviewDTO.setReply_u_name("name1");
//		
		
		UserReviewEntity userReviewEntity = new UserReviewEntity();
		// BeanUtils.copyProperties(userEntity, userDTO);
		CommonUtil.reflectClass(userReviewDTO, userReviewEntity);
		try {
			iUserReviewService.addUserReview(userReviewEntity);
		} catch (Exception e) {

			userReviewDTO = new UserReviewDTO();
			userReviewDTO.setErrFlg(true);
			formMessages(userReviewDTO,
					Constants.MSG_KEY_INSERT_USERREVIEW_ERROR, null);
		}

		// android
		toJson(response, userReviewDTO);
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
	public ActionForward doReplyReview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		UserReviewDTO userReviewDTO = (UserReviewDTO) form;
		//图片添加的时间
		userReviewDTO.setUr_deploy_time(new Date());
		userReviewDTO.setUr_status(1);
		//test
		//评论者ID
//		userReviewDTO.setU_id(1);
		//话题主题ID
//		userReviewDTO.setUdp_id(4);
		//评论内容
//		userReviewDTO.setUr_content("test content");
		//回复对象ID(回复者ID和评论者ID不同)
//		userReviewDTO.setReply_u_id(2);
//		userReviewDTO.setU_name("name1");
//		userReviewDTO.setReply_u_name("name2");
		
		UserReviewEntity userReviewEntity = new UserReviewEntity();
		// BeanUtils.copyProperties(userEntity, userDTO);
		CommonUtil.reflectClass(userReviewDTO, userReviewEntity);
		try {
			iUserReviewService.addUserReview(userReviewEntity);
		} catch (Exception e) {

			userReviewDTO = new UserReviewDTO();
			userReviewDTO.setErrFlg(true);
			formMessages(userReviewDTO,
					Constants.MSG_KEY_REPLAY_USERREVIEWA_ERROR, null);
		}

		// android
		toJson(response, userReviewDTO);
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

		UserReviewDTO userReviewDTO = (UserReviewDTO) form;
		UserReviewEntity userReviewEntity = new UserReviewEntity();
		userReviewEntity.setUr_id(userReviewDTO.getUr_id());
		try{
			iUserReviewService.deleteUserReview(userReviewEntity);
		}catch(Exception e){
			
			userReviewDTO.setErrFlg(true);
			formMessages(userReviewDTO,
					Constants.MSG_KEY_DELETE_USERREVIEW_ERROR, null);
		}
	

		// android
		toJson(response, userReviewDTO);
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
	public ActionForward doDeleteBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		UserReviewDTO userReviewDTO = (UserReviewDTO) form;

		List<UserReviewEntity> list = new ArrayList<UserReviewEntity>();
		String chkVals = request.getParameter("chkVals");
		String[] chkValArgs = chkVals.split(",");
		for (String chkVal : chkValArgs) {
			UserReviewEntity userReviewEntity = new UserReviewEntity();
			userReviewEntity.setUr_id(Integer.parseInt(chkVal));
			list.add(userReviewEntity);
		}

		iUserReviewService.deleteUserReviewBatch(list);

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

		UserReviewDTO userReviewDTO = (UserReviewDTO) form;
		UserReviewEntity userReviewEntity = new UserReviewEntity();
		// BeanUtils.copyProperties(userEntity, userDTO);
		CommonUtil.reflectClass(userReviewDTO, userReviewEntity);

		iUserReviewService.updateUserReview(userReviewEntity);

		// android
		toJson(response, userReviewDTO);
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
	public ActionForward doUpdateBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		UserReviewDTO userReviewDTO = (UserReviewDTO) form;
		List<UserReviewEntity> list = new ArrayList<UserReviewEntity>();
		UserReviewEntity userReviewEntity = new UserReviewEntity();
		userReviewEntity.setU_id(1);
		list.add(userReviewEntity);
		userReviewEntity = new UserReviewEntity();
		userReviewEntity.setU_id(2);
		list.add(userReviewEntity);
		iUserReviewService.deleteUserReviewBatch(list);

		// android
		toJson(response, userReviewDTO);
		// ;

		return null;

	}

}
