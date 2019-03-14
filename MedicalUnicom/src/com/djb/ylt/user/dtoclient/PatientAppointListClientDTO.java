package com.djb.ylt.user.dtoclient;

import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;

public class PatientAppointListClientDTO extends BaseClientDTO {

	/**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 * 
	 */

	private List<PatientAppointClientDTO> patientAppointList;
	
	private List<PatientAppointClientDTO> graphicAppointList;

	/**
	 * 返回patientAppointList的值
	 * @return List<PatientAppointClientDTO> patientAppointList的值
	 */
	public List<PatientAppointClientDTO> getPatientAppointList() {
		return patientAppointList;
	}

	/**
	 * 设置patientAppointList的值
	 * @param  patientAppointList patientAppointList的值
	 */
	public void setPatientAppointList(List<PatientAppointClientDTO> patientAppointList) {
		this.patientAppointList = patientAppointList;
	}

	/**
	 * 返回graphicAppointList的值
	 * @return List<PatientAppointClientDTO> graphicAppointList的值
	 */
	public List<PatientAppointClientDTO> getGraphicAppointList() {
		return graphicAppointList;
	}

	/**
	 * 设置graphicAppointList的值
	 * @param  graphicAppointList graphicAppointList的值
	 */
	public void setGraphicAppointList(List<PatientAppointClientDTO> graphicAppointList) {
		this.graphicAppointList = graphicAppointList;
	}

	

}