package com.djb.ylt.gene.entity;

import com.djb.ylt.framework.entity.PageModel;

public class AnalysisTemplateEntity  extends PageModel{
	
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = -3471220379312176042L;

	private Integer atId;

    private String diseaseName;

    private String geneName;

    private String geneType;

    private String riskAdded;

    public Integer getAtId() {
        return atId;
    }

    public void setAtId(Integer atId) {
        this.atId = atId;
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

    public void setGenoType(String geneType) {
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
        AnalysisTemplateEntity other = (AnalysisTemplateEntity) that;
        return (this.getAtId() == null ? other.getAtId() == null : this.getAtId().equals(other.getAtId()))
            && (this.getDiseaseName() == null ? other.getDiseaseName() == null : this.getDiseaseName().equals(other.getDiseaseName()))
            && (this.getGeneName() == null ? other.getGeneName() == null : this.getGeneName().equals(other.getGeneName()))
            && (this.getGeneType() == null ? other.getGeneType() == null : this.getGeneType().equals(other.getGeneType()))
            && (this.getRiskAdded() == null ? other.getRiskAdded() == null : this.getRiskAdded().equals(other.getRiskAdded()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAtId() == null) ? 0 : getAtId().hashCode());
        result = prime * result + ((getDiseaseName() == null) ? 0 : getDiseaseName().hashCode());
        result = prime * result + ((getGeneName() == null) ? 0 : getGeneName().hashCode());
        result = prime * result + ((getGeneType() == null) ? 0 : getGeneType().hashCode());
        result = prime * result + ((getRiskAdded() == null) ? 0 : getRiskAdded().hashCode());
        return result;
    }
}