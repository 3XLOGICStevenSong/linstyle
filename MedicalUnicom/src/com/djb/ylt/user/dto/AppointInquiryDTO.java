package com.djb.ylt.user.dto;

import java.util.Date;
import java.util.List;

import org.apache.struts.upload.FormFile;

import com.djb.ylt.framework.dto.PageDTO;
import com.djb.ylt.health.dto.InqueryQuestionDTO;

public class AppointInquiryDTO extends PageDTO {

	/**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = 1L;

	private Integer appointId;

	private Integer patientId;

	private Integer doctorId;

	private Integer packageId;

	private Integer depClassId;

	private Integer depId;

	private Integer symptonId;

	private Date createTime;

	private Date updateTime;

	private String appointType;

	private Integer buyTotal;

	private Integer scheduleId;

	private String payStatus;

	private String payType;

	private Date payTime;

	private String symptonDescribe;

	private String appointTime;

	private String telNum;

	private String beginTime;

	private List<InqueryQuestionDTO> inqueryQuestionDTOs;

	private String inqueryQuestionList;

	private String[] imageArgs;

	private String memo;

	private List<FormFile> imageList;

	private String totalFee;

	private List<AppointInquiryDTO> appointList;

	private List<RecordsDTO> recordsDTOs;

	private String insertTime;

	private String statusFlag;

	private String quartzString;
	
	private String detectNum;
	
	
	
	// 2016.12.20 chengming modify for 常用医生 start
	// 此字段为传入参数排序用
	private int sortType;

	//早晚班
	private String workType;
	
	
	private String queryDate;
	
	
	private Integer geneId;
	
	
	private Integer recordsId;
	
	private String  userCode;
	
	private String type;
	
	private String freeFlag;
	
	private Integer freeTotal;
	
	private String followUpFlag;
	
	private String docDel;
	
	private String patDel;

	public String getDocDel() {
		return docDel;
	}

	public void setDocDel(String docDel) {
		this.docDel = docDel;
	}

	public String getPatDel() {
		return patDel;
	}

	public void setPatDel(String patDel) {
		this.patDel = patDel;
	}

	public int getSortType() {
		return sortType;
	}

	public void setSortType(int sortType) {
		this.sortType = sortType;
	}

	// 2016.12.20 chengming modify for 常用医生 end

	public Integer getAppointId() {
		return appointId;
	}

	public void setAppointId(Integer appointId) {
		this.appointId = appointId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Integer getDepClassId() {
		return depClassId;
	}

	public void setDepClassId(Integer depClassId) {
		this.depClassId = depClassId;
	}

	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
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

	/**
	 * 返回symptonId的值
	 * 
	 * @return Integer symptonId的值
	 */
	public Integer getSymptonId() {
		return symptonId;
	}

	/**
	 * 设置symptonId的值
	 * 
	 * @param symptonId
	 *            symptonId的值
	 */
	public void setSymptonId(Integer symptonId) {
		this.symptonId = symptonId;
	}

	/**
	 * 返回buyTotal的值
	 * 
	 * @return Integer buyTotal的值
	 */
	public Integer getBuyTotal() {
		return buyTotal;
	}

	/**
	 * 设置buyTotal的值
	 * 
	 * @param buyTotal
	 *            buyTotal的值
	 */
	public void setBuyTotal(Integer buyTotal) {
		this.buyTotal = buyTotal;
	}

	/**
	 * 返回payStatus的值
	 * 
	 * @return String payStatus的值
	 */
	public String getPayStatus() {
		return payStatus;
	}

	/**
	 * 设置payStatus的值
	 * 
	 * @param payStatus
	 *            payStatus的值
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * 返回payType的值
	 * 
	 * @return String payType的值
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * 设置payType的值
	 * 
	 * @param payType
	 *            payType的值
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * 返回payTime的值
	 * 
	 * @return Date payTime的值
	 */
	public Date getPayTime() {
		return payTime;
	}

	/**
	 * 设置payTime的值
	 * 
	 * @param payTime
	 *            payTime的值
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	/**
	 * 返回symptonDescribe的值
	 * 
	 * @return String symptonDescribe的值
	 */
	public String getSymptonDescribe() {
		return symptonDescribe;
	}

	/**
	 * 设置symptonDescribe的值
	 * 
	 * @param symptonDescribe
	 *            symptonDescribe的值
	 */
	public void setSymptonDescribe(String symptonDescribe) {
		this.symptonDescribe = symptonDescribe;
	}

	/**
	 * 返回telNum的值
	 * 
	 * @return String telNum的值
	 */
	public String getTelNum() {
		return telNum;
	}

	/**
	 * 设置telNum的值
	 * 
	 * @param telNum
	 *            telNum的值
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	/**
	 * 返回appointType的值
	 * 
	 * @return String appointType的值
	 */
	public String getAppointType() {
		return appointType;
	}

	/**
	 * 设置appointType的值
	 * 
	 * @param appointType
	 *            appointType的值
	 */
	public void setAppointType(String appointType) {
		this.appointType = appointType;
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
	 * 返回beginTime的值
	 * 
	 * @return String beginTime的值
	 */
	public String getBeginTime() {
		return beginTime;
	}

	/**
	 * 设置beginTime的值
	 * 
	 * @param beginTime
	 *            beginTime的值
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
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
	 * 返回totalFee的值
	 * 
	 * @return Float totalFee的值
	 */
	public String getTotalFee() {
		return totalFee;
	}

	/**
	 * 设置totalFee的值
	 * 
	 * @param totalFee
	 *            totalFee的值
	 */
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	/**
	 * 返回appointList的值
	 * 
	 * @return List<AppointInquiryDTO> appointList的值
	 */
	public List<AppointInquiryDTO> getAppointList() {
		return appointList;
	}

	/**
	 * 设置appointList的值
	 * 
	 * @param appointList
	 *            appointList的值
	 */
	public void setAppointList(List<AppointInquiryDTO> appointList) {
		this.appointList = appointList;
	}

	/**
	 * 返回recordsDTOs的值
	 * 
	 * @return List<RecordsDTO> recordsDTOs的值
	 */
	public List<RecordsDTO> getRecordsDTOs() {
		return recordsDTOs;
	}

	/**
	 * 设置recordsDTOs的值
	 * 
	 * @param recordsDTOs
	 *            recordsDTOs的值
	 */
	public void setRecordsDTOs(List<RecordsDTO> recordsDTOs) {
		this.recordsDTOs = recordsDTOs;
	}

	/**
	 * 返回insertTime的值
	 * 
	 * @return String insertTime的值
	 */
	public String getInsertTime() {
		return insertTime;
	}

	/**
	 * 设置insertTime的值
	 * 
	 * @param insertTime
	 *            insertTime的值
	 */
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	/**
	 * 返回statusFlag的值
	 * 
	 * @return String statusFlag的值
	 */
	public String getStatusFlag() {
		return statusFlag;
	}

	/**
	 * 设置statusFlag的值
	 * 
	 * @param statusFlag
	 *            statusFlag的值
	 */
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	/**
	 * 返回quartzString的值
	 * 
	 * @return String quartzString的值
	 */
	public String getQuartzString() {
		return quartzString;
	}

	/**
	 * 设置quartzString的值
	 * 
	 * @param quartzString
	 *            quartzString的值
	 */
	public void setQuartzString(String quartzString) {
		this.quartzString = quartzString;
	}

	/**
	 * 返回workType的值
	 * 
	 * @return String workType的值
	 */
	public String getWorkType() {
		return workType;
	}

	/**
	 * 设置workType的值
	 * 
	 * @param workType
	 *            workType的值
	 */
	public void setWorkType(String workType) {
		this.workType = workType;
	}

	/**
	 * 返回queryDate的值
	 * @return String queryDate的值
	 */
	public String getQueryDate() {
		return queryDate;
	}

	/**
	 * 设置queryDate的值
	 * @param  queryDate queryDate的值
	 */
	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

	/**
	 * 返回geneId的值
	 * @return Integer geneId的值
	 */
	public Integer getGeneId() {
		return geneId;
	}

	/**
	 * 设置geneId的值
	 * @param  geneId geneId的值
	 */
	public void setGeneId(Integer geneId) {
		this.geneId = geneId;
	}

	/**
	 * 返回recordsId的值
	 * @return Integer recordsId的值
	 */
	public Integer getRecordsId() {
		return recordsId;
	}

	/**
	 * 设置recordsId的值
	 * @param  recordsId recordsId的值
	 */
	public void setRecordsId(Integer recordsId) {
		this.recordsId = recordsId;
	}

	/**
	 * 返回userCode的值
	 * @return String userCode的值
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * 设置userCode的值
	 * @param  userCode userCode的值
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * 返回detectNum的值
	 * @return String detectNum的值
	 */
	public String getDetectNum() {
		return detectNum;
	}

	/**
	 * 设置detectNum的值
	 * @param  detectNum detectNum的值
	 */
	public void setDetectNum(String detectNum) {
		this.detectNum = detectNum;
	}

	/**
	 * 返回type的值
	 * @return String type的值
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置type的值
	 * @param  type type的值
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 返回freeFlag的值
	 * @return String freeFlag的值
	 */
	public String getFreeFlag() {
		return freeFlag;
	}

	/**
	 * 设置freeFlag的值
	 * @param  freeFlag freeFlag的值
	 */
	public void setFreeFlag(String freeFlag) {
		this.freeFlag = freeFlag;
	}

	/**
	 * 返回freeTotal的值
	 * @return Integer freeTotal的值
	 */
	public Integer getFreeTotal() {
		return freeTotal;
	}

	/**
	 * 设置freeTotal的值
	 * @param  freeTotal freeTotal的值
	 */
	public void setFreeTotal(Integer freeTotal) {
		this.freeTotal = freeTotal;
	}

	/**
	 * 返回followUpFlag的值
	 * @return String followUpFlag的值
	 */
	public String getFollowUpFlag() {
		return followUpFlag;
	}

	/**
	 * 设置followUpFlag的值
	 * @param  followUpFlag followUpFlag的值
	 */
	public void setFollowUpFlag(String followUpFlag) {
		this.followUpFlag = followUpFlag;
	}

}