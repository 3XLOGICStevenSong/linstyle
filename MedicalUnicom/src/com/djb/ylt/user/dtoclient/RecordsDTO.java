package com.djb.ylt.user.dtoclient;

import java.util.Date;

import com.djb.ylt.framework.dto.BaseDTO;

public class RecordsDTO extends BaseDTO {
	
	
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = 1L;

	private Integer recordsId;

    private Integer appointId;

    private String result;

    private String guidance;

    private String analysis;

    private String inquiryStatus;

    private String symptonDescribe;

    private String doctorMemo;

    private Date appointTime;

    private Date startTime;

    private Date endTime;

    private String commentContent;

    private Float commentGrade;

    private String role;

    private String recordsType;

    private Date createTime;

    private Date updateTime;

    private String telNum;

    public Integer getRecordsId() {
        return recordsId;
    }

    public void setRecordsId(Integer recordsId) {
        this.recordsId = recordsId;
    }

    public Integer getAppointId() {
        return appointId;
    }

    public void setAppointId(Integer appointId) {
        this.appointId = appointId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getGuidance() {
        return guidance;
    }

    public void setGuidance(String guidance) {
        this.guidance = guidance;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getInquiryStatus() {
        return inquiryStatus;
    }

    public void setInquiryStatus(String inquiryStatus) {
        this.inquiryStatus = inquiryStatus;
    }

    public String getSymptonDescribe() {
        return symptonDescribe;
    }

    public void setSymptonDescribe(String symptonDescribe) {
        this.symptonDescribe = symptonDescribe;
    }

    public String getDoctorMemo() {
        return doctorMemo;
    }

    public void setDoctorMemo(String doctorMemo) {
        this.doctorMemo = doctorMemo;
    }

    public Date getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(Date appointTime) {
        this.appointTime = appointTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Float getCommentGrade() {
        return commentGrade;
    }

    public void setCommentGrade(Float commentGrade) {
        this.commentGrade = commentGrade;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRecordsType() {
        return recordsType;
    }

    public void setRecordsType(String recordsType) {
        this.recordsType = recordsType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }
}