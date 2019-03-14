package com.djb.ylt.gene.dtoclient;

import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;


public class DetectAnalysisClientDTO  extends BaseClientDTO{
	


    private String diseaseName;

    private String geneName;

    private String geneType;

    private String riskAdded;

    private List< DiseaseAnalysisClientDTO> diseaseAnalysis;
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

	/**
	 * 返回diseaseAnalysis的值
	 * @return List<DiseaseAnalysisClientDTO> diseaseAnalysis的值
	 */
	public List<DiseaseAnalysisClientDTO> getDiseaseAnalysis() {
		return diseaseAnalysis;
	}

	/**
	 * 设置diseaseAnalysis的值
	 * @param  diseaseAnalysis diseaseAnalysis的值
	 */
	public void setDiseaseAnalysis(List<DiseaseAnalysisClientDTO> diseaseAnalysis) {
		this.diseaseAnalysis = diseaseAnalysis;
	}


}