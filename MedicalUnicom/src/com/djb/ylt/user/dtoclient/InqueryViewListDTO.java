package com.djb.ylt.user.dtoclient;


import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;



public class InqueryViewListDTO extends BaseClientDTO {

	/**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */

	private List<InqueryViewClientDTO> doctorList;

	/**
	 * 返回doctorList的值
	 * @return List<InterrogationClientDTO> doctorList的值
	 */
	public List<InqueryViewClientDTO> getDoctorList() {
		return doctorList;
	}

	/**
	 * 设置doctorList的值
	 * @param  doctorList doctorList的值
	 */
	public void setDoctorList(List<InqueryViewClientDTO> doctorList) {
		this.doctorList = doctorList;
	}

	

}