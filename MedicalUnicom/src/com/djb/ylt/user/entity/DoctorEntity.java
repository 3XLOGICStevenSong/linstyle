package com.djb.ylt.user.entity;

import java.util.Date;
import java.util.List;

import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.health.entity.DepartmentEntity;

public class DoctorEntity extends PageModel {


	/**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = 4999554351088537693L;

	private Integer doctorId;

	private Integer userId;

	private String name;

	private String sex;

	private Date age;

	private String positional;

	private String cardNum;

	private String certificateNum;

	private String certificatePic;

	private String headPic;

	private String hospitalName;

	private String introduction;

	private String verifyFlg;

	private String statusFlg;

	private String bankOwner;

	private String bankNum;

	private String bankName;

	private String healDisease;

	private String email;

	private Date createTime;

	private Date updateTime;

	private Integer serviceCount;

	private String grade;

	private Integer departmentId;

	private List<InterrogationPackageEntity> interrogationEntitys;

	private Float commentGrade;

	private Integer commentNum;

	private DepartmentEntity departmentEntity;

	private List<DoctorSymptomEntity> doctorSymptomEntitys;

	private String doctorTel;

	private UserLoginEntity userLoginEntity;

	private String departmentName;

	private String dcName;

	private Integer dcId;

	private String methodFlg;

	private String sortType;

	private List<DoctorCommentEntity> commentEntitys;

	private Integer patientId;

	private Integer followCount;
	
	private List<FollowContentEntity> followInterestEntitys;
	
	private Double maxTotal;
	
	private Double minTotal;
	
	private String doctorType;
	
	private String  bankTel;
	
	private String serviceType;
	
	private Integer scheduleFlag;
	
	
	private Integer commentCount;
	
	private List<TypicalCaseEntity>typicalCaseEntitys;
	
	public Integer getDoctorId() {
		return doctorId;
	}
 
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPositional() {
		return positional;
	}

	public void setPositional(String positional) {
		this.positional = positional;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public String getCertificatePic() {
		return certificatePic;
	}

	public void setCertificatePic(String certificatePic) {
		this.certificatePic = certificatePic;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getVerifyFlg() {
		return verifyFlg;
	}

	public void setVerifyFlg(String verifyFlg) {
		this.verifyFlg = verifyFlg;
	}

	public String getStatusFlg() {
		return statusFlg;
	}

	public void setStatusFlg(String statusFlg) {
		this.statusFlg = statusFlg;
	}

	public String getBankOwner() {
		return bankOwner;
	}

	public void setBankOwner(String bankOwner) {
		this.bankOwner = bankOwner;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getHealDisease() {
		return healDisease;
	}

	public void setHealDisease(String healDisease) {
		this.healDisease = healDisease;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Integer getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(Integer serviceCount) {
		this.serviceCount = serviceCount;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		DoctorEntity other = (DoctorEntity) that;
		return (this.getDoctorId() == null ? other.getDoctorId() == null
				: this.getDoctorId().equals(other.getDoctorId()))
				&& (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
				&& (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
				&& (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
				&& (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
				&& (this.getPositional() == null ? other.getPositional() == null
						: this.getPositional().equals(other.getPositional()))
				&& (this.getCardNum() == null ? other.getCardNum() == null
						: this.getCardNum().equals(other.getCardNum()))
				&& (this.getCertificateNum() == null ? other.getCertificateNum() == null
						: this.getCertificateNum().equals(other.getCertificateNum()))
				&& (this.getCertificatePic() == null ? other.getCertificatePic() == null
						: this.getCertificatePic().equals(other.getCertificatePic()))
				&& (this.getHeadPic() == null ? other.getHeadPic() == null
						: this.getHeadPic().equals(other.getHeadPic()))
				&& (this.getHospitalName() == null ? other.getHospitalName() == null
						: this.getHospitalName().equals(other.getHospitalName()))
				&& (this.getIntroduction() == null ? other.getIntroduction() == null
						: this.getIntroduction().equals(other.getIntroduction()))
				&& (this.getVerifyFlg() == null ? other.getVerifyFlg() == null
						: this.getVerifyFlg().equals(other.getVerifyFlg()))
				&& (this.getStatusFlg() == null ? other.getStatusFlg() == null
						: this.getStatusFlg().equals(other.getStatusFlg()))
				&& (this.getBankOwner() == null ? other.getBankOwner() == null
						: this.getBankOwner().equals(other.getBankOwner()))
				&& (this.getBankNum() == null ? other.getBankNum() == null
						: this.getBankNum().equals(other.getBankNum()))
				&& (this.getBankName() == null ? other.getBankName() == null
						: this.getBankName().equals(other.getBankName()))
				&& (this.getHealDisease() == null ? other.getHealDisease() == null
						: this.getHealDisease().equals(other.getHealDisease()))
				&& (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getUpdateTime() == null ? other.getUpdateTime() == null
						: this.getUpdateTime().equals(other.getUpdateTime()))
				&& (this.getServiceCount() == null ? other.getServiceCount() == null
						: this.getServiceCount().equals(other.getServiceCount()))
				&& (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getDoctorId() == null) ? 0 : getDoctorId().hashCode());
		result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
		result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
		result = prime * result + ((getPositional() == null) ? 0 : getPositional().hashCode());
		result = prime * result + ((getCardNum() == null) ? 0 : getCardNum().hashCode());
		result = prime * result + ((getCertificateNum() == null) ? 0 : getCertificateNum().hashCode());
		result = prime * result + ((getCertificatePic() == null) ? 0 : getCertificatePic().hashCode());
		result = prime * result + ((getHeadPic() == null) ? 0 : getHeadPic().hashCode());
		result = prime * result + ((getHospitalName() == null) ? 0 : getHospitalName().hashCode());
		result = prime * result + ((getIntroduction() == null) ? 0 : getIntroduction().hashCode());
		result = prime * result + ((getVerifyFlg() == null) ? 0 : getVerifyFlg().hashCode());
		result = prime * result + ((getStatusFlg() == null) ? 0 : getStatusFlg().hashCode());
		result = prime * result + ((getBankOwner() == null) ? 0 : getBankOwner().hashCode());
		result = prime * result + ((getBankNum() == null) ? 0 : getBankNum().hashCode());
		result = prime * result + ((getBankName() == null) ? 0 : getBankName().hashCode());
		result = prime * result + ((getHealDisease() == null) ? 0 : getHealDisease().hashCode());
		result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
		result = prime * result + ((getServiceCount() == null) ? 0 : getServiceCount().hashCode());
		result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
		return result;
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
	 * 返回interrogationEntitys的值
	 * 
	 * @return List<InterrogationPackageEntity> interrogationEntitys的值
	 */
	public List<InterrogationPackageEntity> getInterrogationEntitys() {
		return interrogationEntitys;
	}

	/**
	 * 设置interrogationEntitys的值
	 * 
	 * @param interrogationEntitys
	 *            interrogationEntitys的值
	 */
	public void setInterrogationEntitys(List<InterrogationPackageEntity> interrogationEntitys) {
		this.interrogationEntitys = interrogationEntitys;
	}

	/**
	 * 返回commentNum的值
	 * 
	 * @return Integer commentNum的值
	 */
	public Integer getCommentNum() {
		return commentNum;
	}

	/**
	 * 设置commentNum的值
	 * 
	 * @param commentNum
	 *            commentNum的值
	 */
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	/**
	 * 返回age的值
	 * 
	 * @return Date age的值
	 */
	public Date getAge() {
		return age;
	}

	/**
	 * 设置age的值
	 * 
	 * @param age
	 *            age的值
	 */
	public void setAge(Date age) {
		this.age = age;
	}

	/**
	 * 返回departmentEntity的值
	 * 
	 * @return DepartmentEntity departmentEntity的值
	 */
	public DepartmentEntity getDepartmentEntity() {
		return departmentEntity;
	}

	/**
	 * 设置departmentEntity的值
	 * 
	 * @param departmentEntity
	 *            departmentEntity的值
	 */
	public void setDepartmentEntity(DepartmentEntity departmentEntity) {
		this.departmentEntity = departmentEntity;
	}

	/**
	 * 返回doctorSymptomEntitys的值
	 * 
	 * @return List<DoctorSymptomEntity> doctorSymptomEntitys的值
	 */
	public List<DoctorSymptomEntity> getDoctorSymptomEntitys() {
		return doctorSymptomEntitys;
	}

	/**
	 * 设置doctorSymptomEntitys的值
	 * 
	 * @param doctorSymptomEntitys
	 *            doctorSymptomEntitys的值
	 */
	public void setDoctorSymptomEntitys(List<DoctorSymptomEntity> doctorSymptomEntitys) {
		this.doctorSymptomEntitys = doctorSymptomEntitys;
	}

	/**
	 * 返回userLoginEntity的值
	 * 
	 * @return UserLoginEntity userLoginEntity的值
	 */
	public UserLoginEntity getUserLoginEntity() {
		return userLoginEntity;
	}

	/**
	 * 设置userLoginEntity的值
	 * 
	 * @param userLoginEntity
	 *            userLoginEntity的值
	 */
	public void setUserLoginEntity(UserLoginEntity userLoginEntity) {
		this.userLoginEntity = userLoginEntity;
	}

	/**
	 * 返回doctorTel的值
	 * 
	 * @return String doctorTel的值
	 */
	public String getDoctorTel() {
		return doctorTel;
	}

	/**
	 * 设置doctorTel的值
	 * 
	 * @param doctorTel
	 *            doctorTel的值
	 */
	public void setDoctorTel(String doctorTel) {
		this.doctorTel = doctorTel;
	}

	/**
	 * 返回departmentName的值
	 * 
	 * @return String departmentName的值
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * 设置departmentName的值
	 * 
	 * @param departmentName
	 *            departmentName的值
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * 返回dcName的值
	 * 
	 * @return String dcName的值
	 */
	public String getDcName() {
		return dcName;
	}

	/**
	 * 设置dcName的值
	 * 
	 * @param dcName
	 *            dcName的值
	 */
	public void setDcName(String dcName) {
		this.dcName = dcName;
	}

	/**
	 * 返回dcId的值
	 * 
	 * @return Integer dcId的值
	 */
	public Integer getDcId() {
		return dcId;
	}

	/**
	 * 设置dcId的值
	 * 
	 * @param dcId
	 *            dcId的值
	 */
	public void setDcId(Integer dcId) {
		this.dcId = dcId;
	}

	/**
	 * 返回methodFlg的值
	 * 
	 * @return String methodFlg的值
	 */
	public String getMethodFlg() {
		return methodFlg;
	}

	/**
	 * 设置methodFlg的值
	 * 
	 * @param methodFlg
	 *            methodFlg的值
	 */
	public void setMethodFlg(String methodFlg) {
		this.methodFlg = methodFlg;
	}

	/**
	 * 返回sortType的值
	 * 
	 * @return String sortType的值
	 */
	public String getSortType() {
		return sortType;
	}

	/**
	 * 设置sortType的值
	 * 
	 * @param sortType
	 *            sortType的值
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	/**
	 * 返回commentGrade的值
	 * 
	 * @return Float commentGrade的值
	 */
	public Float getCommentGrade() {
		return commentGrade;
	}

	/**
	 * 设置commentGrade的值
	 * 
	 * @param commentGrade
	 *            commentGrade的值
	 */
	public void setCommentGrade(Float commentGrade) {
		this.commentGrade = commentGrade;
	}

	/**
	 * 返回commentEntitys的值
	 * 
	 * @return List<DoctorCommentEntity> commentEntitys的值
	 */
	public List<DoctorCommentEntity> getCommentEntitys() {
		return commentEntitys;
	}

	/**
	 * 设置commentEntitys的值
	 * 
	 * @param commentEntitys
	 *            commentEntitys的值
	 */
	public void setCommentEntitys(List<DoctorCommentEntity> commentEntitys) {
		this.commentEntitys = commentEntitys;
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
	 * 返回followCount的值
	 * @return Integer followCount的值
	 */
	public Integer getFollowCount() {
		return followCount;
	}

	/**
	 * 设置followCount的值
	 * @param  followCount followCount的值
	 */
	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}

	/**
	 * 返回followInterestEntitys的值
	 * @return List<FollowInterestEntity> followInterestEntitys的值
	 */
	public List<FollowContentEntity> getFollowInterestEntitys() {
		return followInterestEntitys;
	}

	/**
	 * 设置followInterestEntitys的值
	 * @param  followInterestEntitys followInterestEntitys的值
	 */
	public void setFollowInterestEntitys(List<FollowContentEntity> followInterestEntitys) {
		this.followInterestEntitys = followInterestEntitys;
	}

	/**
	 * 返回maxTotal的值
	 * @return Double maxTotal的值
	 */
	public Double getMaxTotal() {
		return maxTotal;
	}

	/**
	 * 设置maxTotal的值
	 * @param  maxTotal maxTotal的值
	 */
	public void setMaxTotal(Double maxTotal) {
		this.maxTotal = maxTotal;
	}

	/**
	 * 返回minTotal的值
	 * @return Double minTotal的值
	 */
	public Double getMinTotal() {
		return minTotal;
	}

	/**
	 * 设置minTotal的值
	 * @param  minTotal minTotal的值
	 */
	public void setMinTotal(Double minTotal) {
		this.minTotal = minTotal;
	}

	/**
	 * 返回doctorType的值
	 * @return String doctorType的值
	 */
	public String getDoctorType() {
		return doctorType;
	}

	/**
	 * 设置doctorType的值
	 * @param  doctorType doctorType的值
	 */
	public void setDoctorType(String doctorType) {
		this.doctorType = doctorType;
	}

	/**
	 * 返回bankTel的值
	 * @return String bankTel的值
	 */
	public String getBankTel() {
		return bankTel;
	}

	/**
	 * 设置bankTel的值
	 * @param  bankTel bankTel的值
	 */
	public void setBankTel(String bankTel) {
		this.bankTel = bankTel;
	}

	/**
	 * 返回serviceType的值
	 * @return String serviceType的值
	 */
	public String getServiceType() {
		return serviceType;
	}

	/**
	 * 设置serviceType的值
	 * @param  serviceType serviceType的值
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * 返回scheduleFlag的值
	 * @return Integer scheduleFlag的值
	 */
	public Integer getScheduleFlag() {
		return scheduleFlag;
	}

	/**
	 * 设置scheduleFlag的值
	 * @param  scheduleFlag scheduleFlag的值
	 */
	public void setScheduleFlag(Integer scheduleFlag) {
		this.scheduleFlag = scheduleFlag;
	}

	/**
	 * 返回typicalCaseEntitys的值
	 * @return List<TypicalCaseEntity> typicalCaseEntitys的值
	 */
	public List<TypicalCaseEntity> getTypicalCaseEntitys() {
		return typicalCaseEntitys;
	}

	/**
	 * 设置typicalCaseEntitys的值
	 * @param  typicalCaseEntitys typicalCaseEntitys的值
	 */
	public void setTypicalCaseEntitys(List<TypicalCaseEntity> typicalCaseEntitys) {
		this.typicalCaseEntitys = typicalCaseEntitys;
	}

	/**
	 * 返回commentCount的值
	 * @return Integer commentCount的值
	 */
	public Integer getCommentCount() {
		return commentCount;
	}

	/**
	 * 设置commentCount的值
	 * @param  commentCount commentCount的值
	 */
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}



	
}