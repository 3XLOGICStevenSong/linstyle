package com.djb.ylt.cases.dtoclient;

import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;

public class CasesClientDTO extends BaseClientDTO{

	private Integer tcId;
	
	private Integer doctorId;
	
	private String doctorName;

    private String dcName;

    private String depName;

    private String caseName;
    
    private String content;

    private String grade;

    private String hospitalName;
    
    private String detailUrl;
    
    private String positional;
    
    private String headPic;
    
    private List<CasesClientDTO>casesDTOList;
    
    private Integer viewTimes;
    
    public Integer getTcId() {
		return tcId;
	}

	public void setTcId(Integer tcId) {
		this.tcId = tcId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDcName() {
		return dcName;
	}

	public void setDcName(String dcName) {
		this.dcName = dcName;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Integer getViewTimes() {
		return viewTimes;
	}

	public void setViewTimes(Integer viewTimes) {
		this.viewTimes = viewTimes;
	}

	public List<CasesClientDTO> getCasesDTOList() {
		return casesDTOList;
	}

	public void setCasesDTOList(List<CasesClientDTO> casesDTOList) {
		this.casesDTOList = casesDTOList;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	public String getPositional() {
		return positional;
	}

	public void setPositional(String positional) {
		this.positional = positional;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String picUrl) {
		this.headPic = picUrl;
	}
}
