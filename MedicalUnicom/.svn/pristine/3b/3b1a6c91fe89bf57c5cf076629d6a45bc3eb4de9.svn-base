package com.djb.ylt.gene.entity;

import com.djb.ylt.framework.entity.PageModel;

public class DetectAnalysisEntity  extends PageModel{
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = 1L;

	private Integer daId;

    private Integer rmId;

    private String diseaseName;

    private String geneName;

    private String geneType;

    private String riskAdded;
    
    private String diseaseAnalysis;

    public Integer getDaId() {
        return daId;
    }

    public void setDaId(Integer daId) {
        this.daId = daId;
    }

    public Integer getRmId() {
        return rmId;
    }

    public void setRmId(Integer rmId) {
        this.rmId = rmId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }

    public String getGeneType() {
        return geneType;
    }

    public void setGeneType(String geneType) {
        this.geneType = geneType;
    }

    public String getRiskAdded() {
        return riskAdded;
    }

    public void setRiskAdded(String riskAdded) {
        this.riskAdded = riskAdded;
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
        DetectAnalysisEntity other = (DetectAnalysisEntity) that;
        return (this.getDaId() == null ? other.getDaId() == null : this.getDaId().equals(other.getDaId()))
            && (this.getRmId() == null ? other.getRmId() == null : this.getRmId().equals(other.getRmId()))
            && (this.getDiseaseName() == null ? other.getDiseaseName() == null : this.getDiseaseName().equals(other.getDiseaseName()))
            && (this.getGeneName() == null ? other.getGeneName() == null : this.getGeneName().equals(other.getGeneName()))
            && (this.getGeneType() == null ? other.getGeneType() == null : this.getGeneType().equals(other.getGeneType()))
            && (this.getRiskAdded() == null ? other.getRiskAdded() == null : this.getRiskAdded().equals(other.getRiskAdded()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDaId() == null) ? 0 : getDaId().hashCode());
        result = prime * result + ((getRmId() == null) ? 0 : getRmId().hashCode());
        result = prime * result + ((getDiseaseName() == null) ? 0 : getDiseaseName().hashCode());
        result = prime * result + ((getGeneName() == null) ? 0 : getGeneName().hashCode());
        result = prime * result + ((getGeneType() == null) ? 0 : getGeneType().hashCode());
        result = prime * result + ((getRiskAdded() == null) ? 0 : getRiskAdded().hashCode());
        return result;
    }

	/**
	 * 返回diseaseAnalysis的值
	 * @return String diseaseAnalysis的值
	 */
	public String getDiseaseAnalysis() {
		return diseaseAnalysis;
	}

	/**
	 * 设置diseaseAnalysis的值
	 * @param  diseaseAnalysis diseaseAnalysis的值
	 */
	public void setDiseaseAnalysis(String diseaseAnalysis) {
		this.diseaseAnalysis = diseaseAnalysis;
	}
}