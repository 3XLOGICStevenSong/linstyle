package com.djb.ylt.health.dto;

import java.util.Date;
import java.util.List;

import com.djb.ylt.framework.dto.BaseDTO;

public class InqueryQuestionDTO extends BaseDTO {
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = 1L;

	private Integer iqId;

    private Integer depId;

    private String iqName;

    private String iqType;

    private String iqStatus;

    private Date createTime;

    private String iqMemo;
    
    private Integer patientId;
    
    private List<InqueryAnswerDTO> inqueryAnswerDTOs;
    
    private String[] inqueryAnswerList;
    
    private String[] imageList; 

    public Integer getIqId() {
        return iqId;
    }

    public void setIqId(Integer iqId) {
        this.iqId = iqId;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getIqName() {
        return iqName;
    }

    public void setIqName(String iqName) {
        this.iqName = iqName;
    }

    public String getIqType() {
        return iqType;
    }

    public void setIqType(String iqType) {
        this.iqType = iqType;
    }

    public String getIqStatus() {
        return iqStatus;
    }

    public void setIqStatus(String iqStatus) {
        this.iqStatus = iqStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIqMemo() {
        return iqMemo;
    }

    public void setIqMemo(String iqMemo) {
        this.iqMemo = iqMemo;
    }

	/**
	 * 返回patientId的值
	 * @return Integer patientId的值
	 */
	public Integer getPatientId() {
		return patientId;
	}

	/**
	 * 设置patientId的值
	 * @param  patientId patientId的值
	 */
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	/**
	 * 返回inqueryAnswerDTOs的值
	 * @return List<InqueryAnswerDTO> inqueryAnswerDTOs的值
	 */
	public List<InqueryAnswerDTO> getInqueryAnswerDTOs() {
		return inqueryAnswerDTOs;
	}

	/**
	 * 设置inqueryAnswerDTOs的值
	 * @param  inqueryAnswerDTOs inqueryAnswerDTOs的值
	 */
	public void setInqueryAnswerDTOs(List<InqueryAnswerDTO> inqueryAnswerDTOs) {
		this.inqueryAnswerDTOs = inqueryAnswerDTOs;
	}

	/**
	 * 返回imageList的值
	 * @return String[] imageList的值
	 */
	public String[] getImageList() {
		return imageList;
	}
	/**
	 * 设置imageList的值
	 * @param  imageList imageList的值
	 */
	public void setImageList(String[] imageList) {
		this.imageList = imageList;
	}

	/**
	 * 返回inqueryAnswerList的值
	 * @return String[] inqueryAnswerList的值
	 */
	public String[] getInqueryAnswerList() {
		return inqueryAnswerList;
	}

	/**
	 * 设置inqueryAnswerList的值
	 * @param  inqueryAnswerList inqueryAnswerList的值
	 */
	public void setInqueryAnswerList(String[] inqueryAnswerList) {
		this.inqueryAnswerList = inqueryAnswerList;
	}
}