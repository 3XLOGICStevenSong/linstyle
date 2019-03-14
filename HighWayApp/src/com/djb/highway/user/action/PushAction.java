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
import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.framework.dto.BaseDTO;
import com.djb.highway.user.dto.PushDTO;
import com.djb.highway.user.dtoclient.TuiSongClientDTO;
import com.djb.highway.user.dtoclient.TuiSongDetailDTO;
import com.djb.highway.user.entity.PushEntity;
import com.djb.highway.user.service.IPushService;

@Controller("/Push")
public class PushAction extends BaseAction {

	@SuppressWarnings("unused")
	@Autowired
	@Qualifier("iPushService")
	private IPushService iPushService;

	public PushAction() {
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
	public ActionForward doGetPushList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// Form表单参数
		PushDTO pushDTO = (PushDTO) form;
		// 結果取得用DTO
		TuiSongClientDTO tuiSongClientDTO = new TuiSongClientDTO();
		// 参数DTO
		PushEntity paramEntity = new PushEntity();
		CommonUtil.reflectClass(pushDTO, paramEntity);
		List<PushEntity> resultList = new ArrayList<PushEntity>();
		List<TuiSongDetailDTO> tuiSongDetailDTOList = new ArrayList<TuiSongDetailDTO>();
		// paramEntity.setU_id(pushDTO.getU_id());
		// test数据
		// paramEntity.setU_id(1);
		

		try {
			resultList = iPushService.getPushList(paramEntity);

			for (PushEntity pushEntity : resultList) {
				TuiSongDetailDTO tuiSongDetailDTO = new TuiSongDetailDTO();
				// 给WeiBoDetailDTO赋值

				tuiSongDetailDTO.setH_id(pushEntity.getH_id());

				// 将tuiSongDetailDTO添加进List中
				tuiSongDetailDTOList.add(tuiSongDetailDTO);
			}
			tuiSongClientDTO.setList(tuiSongDetailDTOList);
		} catch (Exception e) {
			tuiSongClientDTO.setReturnCode("-2400");
			pushDTO.setErrFlg(true);
		}
		if (!pushDTO.isErrFlg()) {
			tuiSongClientDTO.setReturnCode("0");
		}
		toJson(response, tuiSongClientDTO);
		return null;

	}

	public ActionForward doInsert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 参数DTO
		PushDTO pushParamDTO = (PushDTO) form;
		// 用于返回操作状态

		BaseDTO baseDTO = new BaseDTO();
		// test
		TuiSongDetailDTO tuiSongDetailDTO =new  TuiSongDetailDTO();
		PushEntity pushEntity = new PushEntity();
		// BeanUtils.copyProperties(userEntity, userDTO);
		CommonUtil.reflectClass(pushParamDTO, pushEntity);
		// pushEntity.setU_id(3);
		// pushEntity.setRoad_name("京哈");
		try {

			iPushService.addPush(pushEntity);
			tuiSongDetailDTO.setH_id(pushEntity.getH_id());
		} catch (Exception e) {
			tuiSongDetailDTO.setReturnCode("-2401");
			// opinionDTO = new OpinionDTO();
			// opinionDTO.setErrFlg(true);
			baseDTO.setErrFlg(true);
			// formMessages(baseDTO, Constants.MSG_KEY_OPINION_ERROR,
			// null);
		}
		if (!baseDTO.isErrFlg()) {
			tuiSongDetailDTO.setReturnCode("0");
		}
		// android
		
		toJson(response, tuiSongDetailDTO);
		// ;
		return null;

	}

	public ActionForward doDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PushDTO pushDTO = (PushDTO) form;
		TuiSongDetailDTO tuiSongDetailDTO =new  TuiSongDetailDTO();
		PushEntity pushEntity = new PushEntity();
		pushEntity.setU_id(pushDTO.getU_id());
		pushEntity.setH_id(pushDTO.getH_id());
		// pushEntity.setU_id(3);
		// pushEntity.setRoad_name("京哈");
		try {
			iPushService.deletePush(pushEntity);
			tuiSongDetailDTO.setH_id(pushEntity.getH_id());
		} catch (Exception e) {

			pushDTO.setErrFlg(true);
			pushDTO.setReturnCode("-2402");
			// formMessages(userReviewDTO,
			// Constants.MSG_KEY_DELETE_USERREVIEW_ERROR, null);
		}

		if (!pushDTO.isErrFlg()) {
			tuiSongDetailDTO.setReturnCode("0");
			
		}
		// android
		toJson(response, tuiSongDetailDTO);
		// ;
		return null;

	}
}