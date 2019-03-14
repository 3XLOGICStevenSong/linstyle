package com.djb.ylt.user.dto;

import java.util.Date;
import java.util.List;

import org.apache.struts.upload.FormFile;

import com.djb.ylt.framework.dto.BaseDTO;
import com.djb.ylt.health.dto.InqueryQuestionDTO;

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

	private String appointTime;

	private Date startTime;

	private Date endTime;

	private String commentContent;

	private Float commentGrade;

	private String role;

	private String recordsType;

	private Date createTime;

	private Date updateTime;

	private String telNum;

	private Integer scheduleId;

	private Integer patientId;

	private List<InqueryQuestionDTO> inqueryQuestionDTOs;

	private String inqueryQuestionList;

	private Integer departmentId;

	private String[] imageArgs;

	// private String imageList;
	private List<FormFile> imageList;

	private String memo;

	private Integer schId;

	private String reasonMemo;

	private Integer refundId;

	private String refundReason;

	private FormFile testImage;

	private Integer doctorId;

	private String delFlag;

	private List<RecordsDTO> recordsList;

	private String pdelFlag;

	private String pushString;

	private Integer patschId;

	public Integer getRecordsId() {
		return recordsId;
	}

	public void setRecordsId(Integer recordsId) {
		this.recordsId = recordsId;
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

	/**
	 * 返回appointTime的值
	 * 
	 * @return String appointTime的值
	 */
	public String getAppointTime() {
		return appointTime;
	}

	/**
	 * 设置appointTime的值
	 * 
	 * @param appointTime
	 *            appointTime的值
	 */
	public void setAppointTime(String appointTime) {
		this.appointTime = appointTime;
	}

	/**
	 * 返回scheduleId的值
	 * 
	 * @return Integer scheduleId的值
	 */
	public Integer getScheduleId() {
		return scheduleId;
	}

	/**
	 * 设置scheduleId的值
	 * 
	 * @param scheduleId
	 *            scheduleId的值
	 */
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	/**
	 * 返回patientId的值
	 * 
	 * @return Integer patientId的值
	 */
	public Integer getPatientId() {
		return patientId;
	}

	/**
	 * 设置patientId的值
	 * 
	 * @param patientId
	 *            patientId的值
	 */
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	/**
	 * 返回inqueryQuestionDTOs的值
	 * 
	 * @return List<InqueryQuestionDTO> inqueryQuestionDTOs的值
	 */
	public List<InqueryQuestionDTO> getInqueryQuestionDTOs() {
		return inqueryQuestionDTOs;
	}

	/**
	 * 设置inqueryQuestionDTOs的值
	 * 
	 * @param inqueryQuestionDTOs
	 *            inqueryQuestionDTOs的值
	 */
	public void setInqueryQuestionDTOs(List<InqueryQuestionDTO> inqueryQuestionDTOs) {
		this.inqueryQuestionDTOs = inqueryQuestionDTOs;
	}

	/**
	 * 返回inqueryQuestionList的值
	 * 
	 * @return String inqueryQuestionList的值
	 */
	public String getInqueryQuestionList() {
		return inqueryQuestionList;
	}

	/**
	 * 设置inqueryQuestionList的值
	 * 
	 * @param inqueryQuestionList
	 *            inqueryQuestionList的值
	 */
	public void setInqueryQuestionList(String inqueryQuestionList) {
		this.inqueryQuestionList = inqueryQuestionList;
	}

	/**
	 * 返回departmentId的值
	 * 
	 * @return Integer departmentId的值
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}

	/**
	 * 设置departmentId的值
	 * 
	 * @param departmentId
	 *            departmentId的值
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * 返回imageArgs的值
	 * 
	 * @return String[] imageArgs的值
	 */
	public String[] getImageArgs() {
		return imageArgs;
	}

	/**
	 * 设置imageArgs的值
	 * 
	 * @param imageArgs
	 *            imageArgs的值
	 */
	public void setImageArgs(String[] imageArgs) {
		this.imageArgs = imageArgs;
	}

	/**
	 * 返回memo的值
	 * 
	 * @return String memo的值
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置memo的值
	 * 
	 * @param memo
	 *            memo的值
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 返回schId的值
	 * 
	 * @return Integer schId的值
	 */
	public Integer getSchId() {
		return schId;
	}

	/**
	 * 设置schId的值
	 * 
	 * @param schId
	 *            schId的值
	 */
	public void setSchId(Integer schId) {
		this.schId = schId;
	}

	/**
	 * 返回imageList的值
	 * 
	 * @return List<FormFile> imageList的值
	 */
	public List<FormFile> getImageList() {
		return imageList;
	}

	/**
	 * 设置imageList的值
	 * 
	 * @param imageList
	 *            imageList的值
	 */
	public void setImageList(List<FormFile> imageList) {
		this.imageList = imageList;
	}

	/**
	 * 返回reasonMemo的值
	 * 
	 * @return String reasonMemo的值
	 */
	public String getReasonMemo() {
		return reasonMemo;
	}

	/**
	 * 设置reasonMemo的值
	 * 
	 * @param reasonMemo
	 *            reasonMemo的值
	 */
	public void setReasonMemo(String reasonMemo) {
		this.reasonMemo = reasonMemo;
	}

	/**
	 * 返回refundId的值
	 * 
	 * @return Integer refundId的值
	 */
	public Integer getRefundId() {
		return refundId;
	}

	/**
	 * 设置refundId的值
	 * 
	 * @param refundId
	 *            refundId的值
	 */
	public void setRefundId(Integer refundId) {
		this.refundId = refundId;
	}

	/**
	 * 返回refundReason的值
	 * 
	 * @return String refundReason的值
	 */
	public String getRefundReason() {
		return refundReason;
	}

	/**
	 * 设置refundReason的值
	 * 
	 * @param refundReason
	 *            refundReason的值
	 */
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	/**
	 * 返回testImage的值
	 * 
	 * @return FormFile testImage的值
	 */
	public FormFile getTestImage() {
		return testImage;
	}

	/**
	 * 设置testImage的值
	 * 
	 * @param testImage
	 *            testImage的值
	 */
	public void setTestImage(FormFile testImage) {
		this.testImage = testImage;
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
	 * 返回recordsList的值
	 * 
	 * @return List<RecordsDTO> recordsList的值
	 */
	public List<RecordsDTO> getRecordsList() {
		return recordsList;
	}

	/**
	 * 设置recordsList的值
	 * 
	 * @param recordsList
	 *            recordsList的值
	 */
	public void setRecordsList(List<RecordsDTO> recordsList) {
		this.recordsList = recordsList;
	}

	/**
	 * 返回pushString的值
	 * 
	 * @return String pushString的值
	 */
	public String getPushString() {
		return pushString;
	}

	/**
	 * 设置pushString的值
	 * 
	 * @param pushString
	 *            pushString的值
	 */
	public void setPushString(String pushString) {
		this.pushString = pushString;
	}

	/**
	 * 返回delFlag的值
	 * 
	 * @return String delFlag的值
	 */
	public String getDelFlag() {
		return delFlag;
	}

	/**
	 * 设置delFlag的值
	 * 
	 * @param delFlag
	 *            delFlag的值
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * 返回pdelFlag的值
	 * 
	 * @return String pdelFlag的值
	 */
	public String getPdelFlag() {
		return pdelFlag;
	}

	/**
	 * 设置pdelFlag的值
	 * 
	 * @param pdelFlag
	 *            pdelFlag的值
	 */
	public void setPdelFlag(String pdelFlag) {
		this.pdelFlag = pdelFlag;
	}

	/**
	 * 返回patschId的值
	 * 
	 * @return Integer patschId的值
	 */
	public Integer getPatschId() {
		return patschId;
	}

	/**
	 * 设置patschId的值
	 * 
	 * @param patschId
	 *            patschId的值
	 */
	public void setPatschId(Integer patschId) {
		this.patschId = patschId;
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