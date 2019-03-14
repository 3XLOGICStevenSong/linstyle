package com.djb.ylt.user.dtoclient;


import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;



public class AppoinPushDTOList extends BaseClientDTO{
	
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */

	List<AppoinPushDTO> appoinPushList;

	/**
	 * 返回appoinPushList的值
	 * @return List<AppoinPushDTOList> appoinPushList的值
	 */
	public List<AppoinPushDTO> getAppoinPushList() {
		return appoinPushList;
	}

	/**
	 * 设置appoinPushList的值
	 * @param  appoinPushList appoinPushList的值
	 */
	public void setAppoinPushList(List<AppoinPushDTO> appoinPushList) {
		this.appoinPushList = appoinPushList;
	}
	
}