package com.djb.ylt.user.dtoclient;

import java.util.List;

/**
 * @date 2017年04月25日 下午6:04:35
 * @author fanzeng
 * 内容：android端首页轮播图的返回pojo类包含RoleClientDTO
 */
import com.djb.ylt.framework.dto.BaseClientDTO;

public class TypicalCaseClientListDTO extends BaseClientDTO {

	private List<TypicalCaseClientDTO> typicalCaseInfoList;

	public List<TypicalCaseClientDTO> getTypicalCaseInfoList() {
		return typicalCaseInfoList;
	}

	public void setTypicalCaseInfoList(List<TypicalCaseClientDTO> typicalCaseInfoList) {
		this.typicalCaseInfoList = typicalCaseInfoList;
	}

}