package com.djb.highway.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.djb.highway.common.util.CommonUtil;
import com.djb.highway.common.util.ResourceLocator;
import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.user.dto.CheckApkDTO;
import com.djb.highway.user.dtoclient.CheckApkClientDTO;

@Controller("/CheckApk")
public class CheckApkAction extends BaseAction {

	public CheckApkAction() {
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
	public ActionForward doCheckApkForUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		CheckApkDTO checkApkDTO = (CheckApkDTO) form;
		// 结果DTO
		CheckApkClientDTO resultDTO = new CheckApkClientDTO();
		try {

			// 客户端当前版本号
			int clientVersion = checkApkDTO.getApkVersionCode();

			// 服务器当前版本号
			int curVersion = CommonUtil.toInt(
					ResourceLocator.getI18NMessage(this, "apkCurVersionCode"),
					1);

			// 当服务器的版本号大于客户端的版本号时，将更新标示位设置为true，并提供最新
			// 最新apk的下载地址
			// test
			// clientVersion = 2;
			// curVersion = 3;
			if (clientVersion >= curVersion) {
				resultDTO.setUpdateFlag(false);
			} else {
				// 需要更新
				resultDTO.setUpdateFlag(true);
				String apkCurPath = ResourceLocator.getI18NMessage(this,
						"apkCurPaht");
				// 更新路径
				resultDTO.setApkPath(apkCurPath);
			}
			// 版本更新时间
			String apkUpdateTime = ResourceLocator.getI18NMessage(this,
					"apkUpdateTime");
			resultDTO.setApkUpdateTime(apkUpdateTime);
			// 版本更新名称
			String apkVersionName = ResourceLocator.getI18NMessage(this,
					"apkVersionName");
			resultDTO.setApkVersionName(apkVersionName);

			// 更新简介
			String apkVersionContent = ResourceLocator.getI18NMessage(this,
					"apkVersionContent");
			resultDTO.setApkVersionContent(apkVersionContent);

		} catch (Exception e) {
			resultDTO.setReturnCode("-1");
			checkApkDTO.setErrFlg(true);
		}

		if (!checkApkDTO.isErrFlg()) {
			resultDTO.setReturnCode("0");
		}
		toJson(response, resultDTO);
		return null;

	}

}
