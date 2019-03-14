package com.djb.ylt.health.dtoclient;



import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;


public class InqueryQuestionList extends BaseClientDTO {
	
	
    
    private List<InqueryQuestionClientDTO> inqueryQuestionList;
    
    private String images;
    
    private String  memo;

	/**
	 * 返回inqueryQuestionList的值
	 * @return List<InqueryQuestionClientDTO> inqueryQuestionList的值
	 */
	public List<InqueryQuestionClientDTO> getInqueryQuestionList() {
		return inqueryQuestionList;
	}

	/**
	 * 设置inqueryQuestionList的值
	 * @param  inqueryQuestionList inqueryQuestionList的值
	 */
	public void setInqueryQuestionList(List<InqueryQuestionClientDTO> inqueryQuestionList) {
		this.inqueryQuestionList = inqueryQuestionList;
	}

	/**
	 * 返回memo的值
	 * @return String memo的值
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置memo的值
	 * @param  memo memo的值
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 返回images的值
	 * @return String images的值
	 */
	public String getImages() {
		return images;
	}

	/**
	 * 设置images的值
	 * @param  images images的值
	 */
	public void setImages(String images) {
		this.images = images;
	}
   
    
}