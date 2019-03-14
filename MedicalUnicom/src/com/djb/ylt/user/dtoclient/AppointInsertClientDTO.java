package com.djb.ylt.user.dtoclient;

import com.djb.ylt.framework.dto.BaseClientDTO;

public class AppointInsertClientDTO extends BaseClientDTO {

	/**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */

	private Integer appointId;

	private String aliPayURL;

	private String userTel;

	private String orderNumber;

	private String aliPaySignInfo;

	private Integer recordsId;

	private Integer doctorId;

	/**
	 * 返回aliPayURL的值
	 * 
	 * @return String aliPayURL的值
	 */
	public String getAliPayURL() {
		return aliPayURL;
	}

	/**
	 * 设置aliPayURL的值
	 * 
	 * @param aliPayURL
	 *            aliPayURL的值
	 */
	public void setAliPayURL(String aliPayURL) {
		this.aliPayURL = aliPayURL;
	}

	/**
	 * 返回userTel的值
	 * 
	 * @return String userTel的值
	 */
	public String getUserTel() {
		return userTel;
	}

	/**
	 * 设置userTel的值
	 * 
	 * @param userTel
	 *            userTel的值
	 */
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	/**
	 * 返回orderNumber的值
	 * 
	 * @return String orderNumber的值
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * 设置orderNumber的值
	 * 
	 * @param orderNumber
	 *            orderNumber的值
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * 返回aliPaySignInfo的值
	 * 
	 * @return String aliPaySignInfo的值
	 */
	public String getAliPaySignInfo() {
		return aliPaySignInfo;
	}

	/**
	 * 设置aliPaySignInfo的值
	 * 
	 * @param aliPaySignInfo
	 *            aliPaySignInfo的值
	 */
	public void setAliPaySignInfo(String aliPaySignInfo) {
		this.aliPaySignInfo = aliPaySignInfo;
	}

	/**
	 * 返回recordsId的值
	 * 
	 * @return Integer recordsId的值
	 */
	public Integer getRecordsId() {
		return recordsId;
	}

	/**
	 * 设置recordsId的值
	 * 
	 * @param recordsId
	 *            recordsId的值
	 */
	public void setRecordsId(Integer recordsId) {
		this.recordsId = recordsId;
	}

	/**
	 * 返回doctorId的值
	 * 
	 * @return Integer doctorId的值
	 */
	public Integer getDoctorId() {
		return doctorId;
	}

	/**
	 * 设置doctorId的值
	 * 
	 * @param doctorId
	 *            doctorId的值
	 */
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * 返回appointId的值
	 * @return Integer appointId的值
	 */
	public Integer getAppointId() {
		return appointId;
	}

	/**
	 * 设置appointId的值
	 * @param  appointId appointId的值
	 */
	public void setAppointId(Integer appointId) {
		this.appointId = appointId;
	}

	
}