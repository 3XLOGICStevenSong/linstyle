package com.djb.ylt.user.dtoclient;

import com.djb.ylt.framework.dto.BaseClientDTO;

public class TypicalCaseInfoDTO extends BaseClientDTO {
	/**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = -1210534597943869612L;

	private Integer tcid;
	
	private String caseName;
	
	private String head_pic;

	private String doctor_name;

	private String positional;

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public Integer getTcid() {
		return tcid;
	}

	public void setTcid(Integer tcid) {
		this.tcid = tcid;
	}

	public String getHead_pic() {
		return head_pic;
	}

	public void setHead_pic(String head_pic) {
		this.head_pic = head_pic;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getPositional() {
		return positional;
	}

	public void setPositional(String positional) {
		this.positional = positional;
	}


	

}