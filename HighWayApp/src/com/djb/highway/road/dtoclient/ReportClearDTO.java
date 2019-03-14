package com.djb.highway.road.dtoclient;


public class ReportClearDTO {

	/**
	 * 桩号
	 */
	private String pileNumber;

	/**
	 * 发布时间
	 */
	private String insertTime;

	/**
	 * 情报板名称
	 */
	private String infoBoardName;
	/**
	 * 图片路径
	 */
	private String pic_path;

	// 情报板内容
	private String reportContent;

	private String status;

	public String getPic_path() {
		return pic_path;
	}

	public void setPic_path(String pic_path) {
		this.pic_path = pic_path;
	}

	public String getPileNumber() {
		return pileNumber;
	}

	public void setPileNumber(String pileNumber) {
		this.pileNumber = pileNumber;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public String getInfoBoardName() {
		return infoBoardName;
	}

	public void setInfoBoardName(String infoBoardName) {
		this.infoBoardName = infoBoardName;
	}

}