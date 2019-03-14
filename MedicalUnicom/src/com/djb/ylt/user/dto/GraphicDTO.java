package com.djb.ylt.user.dto;

import java.util.Date;

import org.apache.struts.upload.FormFile;

import com.djb.ylt.framework.dto.BaseDTO;

public class GraphicDTO extends BaseDTO {
	
	
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = 1L;

	private Long graphicId;

    private Integer appointId;

    private String graphicContent;

    private String graphicType;

    private Date createTime;

    private String deleteFlag;

    private String role;

    private Integer doctorId;

    private Integer patientId;
    
    private FormFile  graphicFile;

    public Long getGraphicId() {
        return graphicId;
    }

    public void setGraphicId(Long graphicId) {
        this.graphicId = graphicId;
    }

  
    public String getGraphicContent() {
        return graphicContent;
    }

    public void setGraphicContent(String graphicContent) {
        this.graphicContent = graphicContent;
    }

    public String getGraphicType() {
        return graphicType;
    }

    public void setGraphicType(String graphicType) {
        this.graphicType = graphicType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

	/**
	 * 返回graphicFile的值
	 * @return FormFile graphicFile的值
	 */
	public FormFile getGraphicFile() {
		return graphicFile;
	}

	/**
	 * 设置graphicFile的值
	 * @param  graphicFile graphicFile的值
	 */
	public void setGraphicFile(FormFile graphicFile) {
		this.graphicFile = graphicFile;
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