package com.djb.ylt.gene.entity;

import com.djb.ylt.framework.entity.PageModel;

public class TestResultEntity  extends PageModel{
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = 8903246865467790271L;

	private Integer trId;

    private Integer rmId;

    private String geneName;

    private String detectPoint;

    private String classification;

    private String riskType;
    
    private String bioFunction;
    
    private String chiName;
    
    private Integer dgnId;

    public Integer getTrId() {
        return trId;
    }

    public void setTrId(Integer trId) {
        this.trId = trId;
    }

    public Integer getRmId() {
        return rmId;
    }

    public void setRmId(Integer rmId) {
        this.rmId = rmId;
    }

    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }

    public String getDetectPoint() {
        return detectPoint;
    }

    public void setDetectPoint(String detectPoint) {
        this.detectPoint = detectPoint;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
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
        TestResultEntity other = (TestResultEntity) that;
        return (this.getTrId() == null ? other.getTrId() == null : this.getTrId().equals(other.getTrId()))
            && (this.getRmId() == null ? other.getRmId() == null : this.getRmId().equals(other.getRmId()))
            && (this.getGeneName() == null ? other.getGeneName() == null : this.getGeneName().equals(other.getGeneName()))
            && (this.getDetectPoint() == null ? other.getDetectPoint() == null : this.getDetectPoint().equals(other.getDetectPoint()))
            && (this.getClassification() == null ? other.getClassification() == null : this.getClassification().equals(other.getClassification()))
            && (this.getRiskType() == null ? other.getRiskType() == null : this.getRiskType().equals(other.getRiskType()))
            && (this.getDgnId() == null ? other.getDgnId() == null : this.getDgnId().equals(other.getDgnId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTrId() == null) ? 0 : getTrId().hashCode());
        result = prime * result + ((getRmId() == null) ? 0 : getRmId().hashCode());
        result = prime * result + ((getGeneName() == null) ? 0 : getGeneName().hashCode());
        result = prime * result + ((getDetectPoint() == null) ? 0 : getDetectPoint().hashCode());
        result = prime * result + ((getClassification() == null) ? 0 : getClassification().hashCode());
        result = prime * result + ((getRiskType() == null) ? 0 : getRiskType().hashCode());
        result = prime * result + ((getDgnId() == null) ? 0 : getDgnId().hashCode());
        return result;
    }

	/**
	 * 返回bioFunction的值
	 * @return String bioFunction的值
	 */
	public String getBioFunction() {
		return bioFunction;
	}

	/**
	 * 设置bioFunction的值
	 * @param  bioFunction bioFunction的值
	 */
	public void setBioFunction(String bioFunction) {
		this.bioFunction = bioFunction;
	}

	/**
	 * 返回chiName的值
	 * @return String chiName的值
	 */
	public String getChiName() {
		return chiName;
	}

	/**
	 * 设置chiName的值
	 * @param  chiName chiName的值
	 */
	public void setChiName(String chiName) {
		this.chiName = chiName;
	}

	/**
	 * 返回dgnId的值
	 * @return Integer dgnId的值
	 */
	public Integer getDgnId() {
		return dgnId;
	}

	/**
	 * 设置dgnId的值
	 * @param  dgnId dgnId的值
	 */
	public void setDgnId(Integer dgnId) {
		this.dgnId = dgnId;
	}
}