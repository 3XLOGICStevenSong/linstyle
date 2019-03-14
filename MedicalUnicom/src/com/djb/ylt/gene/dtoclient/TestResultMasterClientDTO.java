package com.djb.ylt.gene.dtoclient;

import java.util.Date;

import com.djb.ylt.framework.dto.BaseClientDTO;



public class TestResultMasterClientDTO  extends BaseClientDTO{


	private Integer rmId;

    private String detectedName;

    private String detectedTel;

    private String doctorName;

    private String doctorTel;

    private String evaluate;

    private Integer totalItems;

    private String detectTime;

    private Date insertTime;

    private String userCode;

    private String memo;
    
    private Integer patientId;
    private String productsName;

    public Integer getRmId() {
        return rmId;
    }

    public void setRmId(Integer rmId) {
        this.rmId = rmId;
    }



    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorTel() {
        return doctorTel;
    }

    public void setDoctorTel(String doctorTel) {
        this.doctorTel = doctorTel;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }


    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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
	 * 返回detectedName的值
	 * @return String detectedName的值
	 */
	public String getDetectedName() {
		return detectedName;
	}

	/**
	 * 设置detectedName的值
	 * @param  detectedName detectedName的值
	 */
	public void setDetectedName(String detectedName) {
		this.detectedName = detectedName;
	}

	/**
	 * 返回detectedTel的值
	 * @return String detectedTel的值
	 */
	public String getDetectedTel() {
		return detectedTel;
	}

	/**
	 * 设置detectedTel的值
	 * @param  detectedTel detectedTel的值
	 */
	public void setDetectedTel(String detectedTel) {
		this.detectedTel = detectedTel;
	}

	/**
	 * 返回productsName的值
	 * @return String productsName的值
	 */
	public String getProductsName() {
		return productsName;
	}

	/**
	 * 设置productsName的值
	 * @param  productsName productsName的值
	 */
	public void setProductsName(String productsName) {
		this.productsName = productsName;
	}

	/**
	 * 设置detectTime的值
	 * @param  detectTime detectTime的值
	 */
	public void setDetectTime(String detectTime) {
		this.detectTime = detectTime;
	}

	/**
	 * 返回detectTime的值
	 * @return String detectTime的值
	 */
	public String getDetectTime() {
		return detectTime;
	}


}