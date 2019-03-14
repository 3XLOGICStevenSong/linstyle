package com.djb.ylt.gene.dtoclient;

import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;

public class BuyHistoryClientDTO extends BaseClientDTO {

	private List<ProductsPurchaseClientDTO> buyRecordsList;

	/**
	 * 返回buyRecordsList的值
	 * @return List<ProductsPurchaseClientDTO> buyRecordsList的值
	 */
	public List<ProductsPurchaseClientDTO> getBuyRecordsList() {
		return buyRecordsList;
	}

	/**
	 * 设置buyRecordsList的值
	 * @param  buyRecordsList buyRecordsList的值
	 */
	public void setBuyRecordsList(List<ProductsPurchaseClientDTO> buyRecordsList) {
		this.buyRecordsList = buyRecordsList;
	}
	
	

}