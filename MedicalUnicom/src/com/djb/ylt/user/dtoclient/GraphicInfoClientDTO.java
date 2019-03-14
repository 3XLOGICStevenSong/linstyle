package com.djb.ylt.user.dtoclient;


import com.djb.ylt.framework.dto.BaseClientDTO;


public class GraphicInfoClientDTO extends BaseClientDTO {
	
	
  

	private Long graphicId;

    private String graphicContent;

    private String graphicType;

    private String role;
    
    private String createTime;

	/**
	 * 返回graphicId的值
	 * @return Long graphicId的值
	 */
	public Long getGraphicId() {
		return graphicId;
	}

	/**
	 * 设置graphicId的值
	 * @param  graphicId graphicId的值
	 */
	public void setGraphicId(Long graphicId) {
		this.graphicId = graphicId;
	}

	/**
	 * 返回graphicContent的值
	 * @return String graphicContent的值
	 */
	public String getGraphicContent() {
		return graphicContent;
	}

	/**
	 * 设置graphicContent的值
	 * @param  graphicContent graphicContent的值
	 */
	public void setGraphicContent(String graphicContent) {
		this.graphicContent = graphicContent;
	}

	/**
	 * 返回graphicType的值
	 * @return String graphicType的值
	 */
	public String getGraphicType() {
		return graphicType;
	}

	/**
	 * 设置graphicType的值
	 * @param  graphicType graphicType的值
	 */
	public void setGraphicType(String graphicType) {
		this.graphicType = graphicType;
	}

	/**
	 * 返回role的值
	 * @return String role的值
	 */
	public String getRole() {
		return role;
	}

	/**
	 * 设置role的值
	 * @param  role role的值
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * 返回createTime的值
	 * @return String createTime的值
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置createTime的值
	 * @param  createTime createTime的值
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


  
    
}