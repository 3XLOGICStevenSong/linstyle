package com.djb.ylt.gene.entity;

import java.util.Date;
import java.util.List;

import com.djb.ylt.framework.entity.PageModel;

public class TestResultMasterEntity  extends PageModel{
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = 575432941455980878L;

	private Integer rmId;

    private String detectedName;

    private String detectedTel;

    private String doctorName;

    private String doctorTel;

    private String evaluate;

    private Integer totalItems;

    private Date detectTime;

    private Date insertTime;

    private String userCode;

    private String memo;
    
    private String sex;
    
    private Integer age;
    
    private String productsName;
    
    private String  detectNum;
    
    
    private List<TestResultEntity> testResultEntitys;
    
    private List<DetectAnalysisEntity> detectAnalysisEntitys;
    
    private Integer patientId;
    

     
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

    public Date getDetectTime() {
        return detectTime;
    }

    public void setDetectTime(Date detectTime) {
        this.detectTime = detectTime;
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
        TestResultMasterEntity other = (TestResultMasterEntity) that;
        return (this.getRmId() == null ? other.getRmId() == null : this.getRmId().equals(other.getRmId()))
            && (this.getDetectedName() == null ? other.getDetectedName() == null : this.getDetectedName().equals(other.getDetectedName()))
            && (this.getDetectedTel() == null ? other.getDetectedTel() == null : this.getDetectedTel().equals(other.getDetectedTel()))
            && (this.getDoctorName() == null ? other.getDoctorName() == null : this.getDoctorName().equals(other.getDoctorName()))
            && (this.getDoctorTel() == null ? other.getDoctorTel() == null : this.getDoctorTel().equals(other.getDoctorTel()))
            && (this.getEvaluate() == null ? other.getEvaluate() == null : this.getEvaluate().equals(other.getEvaluate()))
            && (this.getTotalItems() == null ? other.getTotalItems() == null : this.getTotalItems().equals(other.getTotalItems()))
            && (this.getDetectTime() == null ? other.getDetectTime() == null : this.getDetectTime().equals(other.getDetectTime()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()))
            && (this.getUserCode() == null ? other.getUserCode() == null : this.getUserCode().equals(other.getUserCode()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRmId() == null) ? 0 : getRmId().hashCode());
        result = prime * result + ((getDetectedName() == null) ? 0 : getDetectedName().hashCode());
        result = prime * result + ((getDetectedTel() == null) ? 0 : getDetectedTel().hashCode());
        result = prime * result + ((getDoctorName() == null) ? 0 : getDoctorName().hashCode());
        result = prime * result + ((getDoctorTel() == null) ? 0 : getDoctorTel().hashCode());
        result = prime * result + ((getEvaluate() == null) ? 0 : getEvaluate().hashCode());
        result = prime * result + ((getTotalItems() == null) ? 0 : getTotalItems().hashCode());
        result = prime * result + ((getDetectTime() == null) ? 0 : getDetectTime().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        return result;
    }

	/**
	 * 返回testResultEntitys的值
	 * @return List<TestResultEntity> testResultEntitys的值
	 */
	public List<TestResultEntity> getTestResultEntitys() {
		return testResultEntitys;
	}

	/**
	 * 设置testResultEntitys的值
	 * @param  testResultEntitys testResultEntitys的值
	 */
	public void setTestResultEntitys(List<TestResultEntity> testResultEntitys) {
		this.testResultEntitys = testResultEntitys;
	}

	/**
	 * 返回detectAnalysisEntitys的值
	 * @return List<DetectAnalysisEntity> detectAnalysisEntitys的值
	 */
	public List<DetectAnalysisEntity> getDetectAnalysisEntitys() {
		return detectAnalysisEntitys;
	}

	/**
	 * 设置detectAnalysisEntitys的值
	 * @param  detectAnalysisEntitys detectAnalysisEntitys的值
	 */
	public void setDetectAnalysisEntitys(List<DetectAnalysisEntity> detectAnalysisEntitys) {
		this.detectAnalysisEntitys = detectAnalysisEntitys;
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
	 * 返回sex的值
	 * @return String sex的值
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置sex的值
	 * @param  sex sex的值
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 返回age的值
	 * @return Integer age的值
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * 设置age的值
	 * @param  age age的值
	 */
	public void setAge(Integer age) {
		this.age = age;
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
}