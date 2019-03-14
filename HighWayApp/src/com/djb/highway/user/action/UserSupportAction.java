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
import com.djb.highway.user.dto.UserSupportDTO;
import com.djb.highway.user.dtoclient.FavoCountDTO;
import com.djb.highway.user.entity.UserDeployPicEntity;
import com.djb.highway.user.entity.UserSupportEntity;
import com.djb.highway.user.service.IUserDeployPicService;
import com.djb.highway.user.service.IUserSupportService;

@Controller("/UserSupport")
public class UserSupportAction extends BaseAction {

	@Autowired
	@Qualifier("iUserSupportService")
	private IUserSupportService iUserSupportService;

	@Autowired
	@Qualifier("iUserDeployPicService")
	private IUserDeployPicService iUserDeployPicService;

	public UserSupportAction() {
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
	public ActionForward doGetUserSupportList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return null;
	}

	// 检测用户是否已经赞过,返回false表示用户已经赞过
	@SuppressWarnings("unchecked")
	public boolean checkUserForSupport(UserSupportDTO userSupportDTO) {

		UserSupportEntity userSupportEntity = new UserSupportEntity();
		userSupportEntity.setU_id(userSupportDTO.getU_id());
		userSupportEntity.setUdp_id(userSupportDTO.getUdp_id());
		List<UserSupportEntity> userSupportList = iUserSupportService
				.getUserSupportList(userSupportEntity);

		if (userSupportList != null && userSupportList.size() > 0) {
			return false;
		} else {
			return true;
		}

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

		UserSupportDTO userSupportDTO = (UserSupportDTO) form;
		UserSupportDTO resultDTO=new UserSupportDTO();
		// test
		// userSupportDTO.setU_id(1);
		//userSupportDTO.setUdp_id(1000);

		UserSupportEntity userSupportEntity = null;
		// 页面DTO
		FavoCountDTO favoCountDTO = new FavoCountDTO();

		try {
			// 如果没有赞过，执行更新操作：更新UserDeployPic表的赞字段；并且添加当前用户到赞表中
			if (checkUserForSupport(userSupportDTO)) {
				// 更新UserDeployPic表的赞字段
				UserDeployPicEntity userDeployPicEntity = new UserDeployPicEntity();
				userDeployPicEntity.setUdp_id(userSupportDTO.getUdp_id());

				UserDeployPicEntity resultTemp = iUserDeployPicService
						.getObjectForUpdate(userDeployPicEntity);
				if (resultTemp != null) {
					// 当存在分享记录时，获取用户赞的数量，并且赞数量加一
					// 更新赞的数量
					if (resultTemp.getCommend_count() != null) {
						userDeployPicEntity.setCommend_count(resultTemp
								.getCommend_count() + 1);
						favoCountDTO.setZanCount(""
								+ (resultTemp.getCommend_count() + 1));
					} else {
						userDeployPicEntity.setCommend_count(1);
						favoCountDTO.setZanCount("" + 1);
					}

					userDeployPicEntity.setUdp_id(userSupportDTO.getUdp_id());
					iUserDeployPicService
							.updateUserDeployPic(userDeployPicEntity);

				} else {
					// 当不存在分享记录时，提示错误信息：用户分享信息错误
					favoCountDTO.setReturnCode("-1203");
					resultDTO.setErrFlg(true);
				//	formMessages(favoCountDTO,
					//		Constants.MSG_KEY_UPDATE_USERDEPLOYPIC_ERROR, null);
				}

				// 添加当前用户到赞表中
				userSupportEntity = new UserSupportEntity();
				CommonUtil.reflectClass(userSupportDTO, userSupportEntity);
				iUserSupportService.addUserSupport(userSupportEntity);

			} else {
				// 已经赞过的用户，不能再点赞
				// UserSupportDTO.setErrFlg(true);
				favoCountDTO.setReturnCode("-1200");
				resultDTO.setErrFlg(true);
				//formMessages(favoCountDTO, Constants.MSG_ADD_SUPPORT_ERROR,
					//	null);
			}

		} catch (Exception e) {

			// UserSupportDTO = new UserSupportDTO();
			// UserSupportDTO.setErrFlg(true);
			favoCountDTO.setReturnCode("-1201");
			resultDTO.setErrFlg(true);
		//	formMessages(favoCountDTO, Constants.MSG_INSERT_SUPPORT_ERROR, null);
		}
		if (!resultDTO.isErrFlg()) {
			favoCountDTO.setReturnCode("0");
		}
		// android
		toJson(response, favoCountDTO);
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
	public ActionForward doDeleteSupport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserSupportDTO UserSupportDTO = (UserSupportDTO) form;
		UserSupportEntity UserSupportEntity = new UserSupportEntity();
		UserSupportEntity.setUdp_id(37);
		 UserSupportEntity.setU_id(1);

		try {
			iUserSupportService.deleteUserSupport(UserSupportEntity);
		} catch (Exception e) {
			UserSupportDTO.setReturnCode("-1202");
			UserSupportDTO.setErrFlg(true);
			//formMessages(UserSupportDTO, Constants.MSG_DELETE_SUPPORT_ERROR,
				//	null);
		}
		if (!UserSupportDTO.isErrFlg()) {
			UserSupportDTO.setReturnCode("0");
		}
		// android
		toJson(response, UserSupportDTO);
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

		UserSupportDTO UserSupportDTO = (UserSupportDTO) form;

		List<UserSupportEntity> list = new ArrayList<UserSupportEntity>();
		String chkVals = request.getParameter("chkVals");
		String[] chkValArgs = chkVals.split(",");
		for (String chkVal : chkValArgs) {
			UserSupportEntity UserSupportEntity = new UserSupportEntity();
			list.add(UserSupportEntity);
		}

		iUserSupportService.deleteUserSupportBatch(list);

		return null;

	}

}
