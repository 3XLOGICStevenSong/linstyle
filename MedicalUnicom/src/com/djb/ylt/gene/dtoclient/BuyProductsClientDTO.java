package com.djb.ylt.gene.dtoclient;

import com.djb.ylt.framework.dto.BaseClientDTO;

public class BuyProductsClientDTO extends BaseClientDTO {

	private String payInfo;

	private Long prId;

	/**
	 * 返回payInfo的值
	 * @return String payInfo的值
	 */
	public String getPayInfo() {
		return payInfo;
	}

	/**
	 * 设置payInfo的值
	 * @param  payInfo payInfo的值
	 */
	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	/**
	 * 返回prId的值
	 * @return Integer prId的值
	 */
	public Long getPrId() {
		return prId;
	}

	/**
	 * 设置prId的值
	 * @param  prId prId的值
	 */
	public void setPrId(Long prId) {
		this.prId = prId;
	}
	

}