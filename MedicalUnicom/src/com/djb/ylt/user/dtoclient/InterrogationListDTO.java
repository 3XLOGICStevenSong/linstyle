package com.djb.ylt.user.dtoclient;

import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;


public class InterrogationListDTO extends BaseClientDTO {

	/**
	 * serialVersionUID:用一句话描述这个变量表示什么。 1.医生ID:doctorId 2.医生头像:headPic
	 * 3.医生姓名:name 4.医生职称名:positional 5.医生简介:healDisease 6.医生评分：grade
	 * 7.医生的电话问诊价格：total 8.预订过该医生的人数：serviceCount
	 */

	private List<InterrogationClientDTO> interrogationClientDTO;

	/**
	 * 返回interrogationClientDTO的值
	 * @return List<InterrogationClientDTO> interrogationClientDTO的值
	 */
	public List<InterrogationClientDTO> getInterrogationClientDTO() {
		return interrogationClientDTO;
	}

	/**
	 * 设置interrogationClientDTO的值
	 * @param  interrogationClientDTO interrogationClientDTO的值
	 */
	public void setInterrogationClientDTO(List<InterrogationClientDTO> interrogationClientDTO) {
		this.interrogationClientDTO = interrogationClientDTO;
	}


	

	
}