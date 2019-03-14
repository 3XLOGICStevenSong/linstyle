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
import com.djb.highway.road.dtoclient.BaseClientDTO;
import com.djb.highway.user.dto.PhoneBookDTO;
import com.djb.highway.user.dtoclient.PhoneClientDTO;
import com.djb.highway.user.dtoclient.PhoneDetailDTO;
import com.djb.highway.user.entity.PhoneBookEntity;
import com.djb.highway.user.service.IPhoneBookService;

@Controller("/PhoneBook")
public class PhoneBookAction extends BaseAction {

	@Autowired
	@Qualifier("iPhoneBookService")
	private IPhoneBookService iPhoneBookService;

	public PhoneBookAction() {
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
	public ActionForward doGetPhoneBookList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PhoneBookDTO paramDTO = (PhoneBookDTO) form;
		// 結果取得用DTO
		PhoneClientDTO phoneClientDTO = new PhoneClientDTO();
		PhoneBookEntity paramEntity = new PhoneBookEntity();
		List<PhoneBookEntity> resultList = new ArrayList<PhoneBookEntity>();
		List<PhoneDetailDTO> phoneDetailDTOList = new ArrayList<PhoneDetailDTO>();
		// test
		// paramDTO.setU_id(1);
		// ParamDTO.setTel_num("123346564");
		paramEntity.setU_id(paramDTO.getU_id());

		// do page;
		try {
			resultList = iPhoneBookService.getPhoneBookList(paramEntity);

			// resultList = iPhoneBookService.getPhoneBookList(paramEntity);

			for (PhoneBookEntity phoneBookEntity : resultList) {
				PhoneDetailDTO phoneDetailDTO = new PhoneDetailDTO();
				// BeanUtils.copyProperties(_userDTO, userEntity);
				phoneDetailDTO.setP_id(phoneBookEntity.getP_id());
				phoneDetailDTO.setUserId(phoneBookEntity.getU_id());
				phoneDetailDTO.setName(phoneBookEntity.getMemo());
				phoneDetailDTO.setTel(phoneBookEntity.getTel_num());
				phoneDetailDTOList.add(phoneDetailDTO);
			}
			phoneClientDTO.setList(phoneDetailDTOList);
			// phoneClientDTO.setList(phoneDetailDTOList);

		} catch (Exception e) {
			phoneClientDTO.setReturnCode("-2200");
			// resultDTO = new UserDTO();
			// resultDTO.setErrFlg(true);
			paramDTO.setErrFlg(true);
			// formMessages(myShareDTO,
			// Constants.MSG_KEY_GET_USERDEPLOYPIC_ERROR,
			// null);
		}
		if (!paramDTO.isErrFlg()) {
			phoneClientDTO.setReturnCode("0");
		}

		toJson(response, phoneClientDTO);
		return null;

	}

	public ActionForward doInsert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 参数DTO
		PhoneBookDTO phoneBookParamDTO = (PhoneBookDTO) form;
		// 用于返回操作状态
		// PhoneDetailDTO phoneDetailDTO = new PhoneDetailDTO();
		BaseDTO baseDTO = new BaseDTO();
		BaseClientDTO baseClientDTO = new BaseClientDTO();
		// test
		// phoneBookParamDTO.setU_id(7);
		// phoneBookParamDTO.setTel_num("123346564");

		PhoneBookEntity phoneBookEntity = new PhoneBookEntity();
		// BeanUtils.copyProperties(userEntity, userDTO);
		CommonUtil.reflectClass(phoneBookParamDTO, phoneBookEntity);
		try {

			iPhoneBookService.addPhoneBook(phoneBookEntity);
			// phoneDetailDTO.setUserId(phoneBookEntity.getU_id());
			// phoneDetailDTO.setName(phoneBookEntity.getMemo());
			// phoneDetailDTO.setTel(phoneBookEntity.getTel_num());

		} catch (Exception e) {
			baseClientDTO.setReturnCode("-2100");
			// opinionDTO = new OpinionDTO();
			// opinionDTO.setErrFlg(true);
			baseDTO.setErrFlg(true);
			// formMessages(baseDTO, Constants.MSG_KEY_OPINION_ERROR,
			// null);
		}
		if (!baseDTO.isErrFlg()) {
			baseClientDTO.setReturnCode("0");
		}
		// android
		toJson(response, baseClientDTO);
		// ;
		return null;

	}

	public ActionForward doUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 参数DTO
		PhoneBookDTO phoneBookDTO = (PhoneBookDTO) form;
		// PhoneDetailDTO phoneDetailDTO = new PhoneDetailDTO();
		BaseDTO baseDTO = new BaseDTO();
		// PhoneBookDTO phoneBookDTO = new PhoneBookDTO();
		// phoneBookDTO.setU_id(5);
		// phoneBookDTO.setTel_num("1233465e");
		PhoneBookEntity phoneBookEntity = new PhoneBookEntity();
		CommonUtil.reflectClass(phoneBookDTO, phoneBookEntity);

		try {
			iPhoneBookService.updatePhoneBook(phoneBookEntity);
			// phoneDetailDTO.setUserId(phoneBookEntity.getU_id());
			// phoneDetailDTO.setName(phoneBookEntity.getMemo());
			// phoneDetailDTO.setTel(phoneBookEntity.getTel_num());
		} catch (Exception e) {
			baseDTO.setReturnCode("-2401");
			baseDTO.setErrFlg(true);
			// formMessages(baseDTO, Constants.MSG_KEY_UPDATE_USER_ERROR, null);
		}
		if (!baseDTO.isErrFlg()) {
			baseDTO.setReturnCode("0");
		}
		toJson(response, baseDTO);
		return null;

	}

	public ActionForward doDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PhoneBookDTO phoneBookDTO = (PhoneBookDTO) form;
		BaseClientDTO baseClientDTO = new BaseClientDTO();
		PhoneBookEntity phoneBookEntity = new PhoneBookEntity();

		// PhoneBookDTO phoneBookDTO = new PhoneBookDTO();
		// phoneBookDTO.setU_id(1);
		// phoneBookDTO.setTel_num("585");
		// phoneBookDTO.setMemo("gnj");
		// phoneBookDTO.setP_id(3);
		PhoneDetailDTO phoneDetailDTO = new PhoneDetailDTO();
		BaseDTO baseDTO = new BaseDTO();
		phoneBookEntity.setU_id(phoneBookDTO.getU_id());
		phoneBookEntity.setTel_num(phoneBookDTO.getTel_num());
		phoneBookEntity.setMemo(phoneBookDTO.getMemo());
		// CommonUtil.reflectClass(phoneBookDTO, phoneBookEntity);
		try {
			iPhoneBookService.deletePhoneBook(phoneBookEntity);
			// phoneDetailDTO.setUserId(phoneBookEntity.getU_id());
			// phoneDetailDTO.setName(phoneBookEntity.getMemo());
			// phoneDetailDTO.setTel(phoneBookEntity.getTel_num());
		} catch (Exception e) {
			baseClientDTO.setReturnCode("-2101");
			baseDTO.setErrFlg(true);
			// formMessages(UserSupportDTO, Constants.MSG_DELETE_SUPPORT_ERROR,
			// null);
		}
		if (!baseDTO.isErrFlg()) {
			baseClientDTO.setReturnCode("0");
		}
		// android
		toJson(response, baseClientDTO);
		// ;
		return null;

	}
}
