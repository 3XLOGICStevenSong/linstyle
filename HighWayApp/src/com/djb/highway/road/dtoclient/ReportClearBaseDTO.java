package com.djb.highway.road.dtoclient;

import java.util.List;

public class ReportClearBaseDTO extends BaseClientDTO {

	private List<ReportClearDTO> listLeft;
	private List<ReportClearDTO> listRight;
	private String leftSectionName;
	private String rightSectionName;

	public List<ReportClearDTO> getListLeft() {
		return listLeft;
	}

	public void setListLeft(List<ReportClearDTO> listLeft) {
		this.listLeft = listLeft;
	}

	public List<ReportClearDTO> getListRight() {
		return listRight;
	}

	public void setListRight(List<ReportClearDTO> listRight) {
		this.listRight = listRight;
	}

	public String getLeftSectionName() {
		return leftSectionName;
	}

	public void setLeftSectionName(String leftSectionName) {
		this.leftSectionName = leftSectionName;
	}

	public String getRightSectionName() {
		return rightSectionName;
	}

	public void setRightSectionName(String rightSectionName) {
		this.rightSectionName = rightSectionName;
	}
	
	
	

}