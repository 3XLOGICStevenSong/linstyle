package com.djb.ylt.gene.service;

import java.util.List;

import com.djb.ylt.gene.entity.AnalysisTemplateEntity;

public interface IAnalysisTemplateService {

	public void addAnalysisTemplate(AnalysisTemplateEntity analysisTemplate);

	public void deleteAnalysisTemplate(AnalysisTemplateEntity analysisTemplate);

	public void deleteAnalysisTemplateBatch(List<AnalysisTemplateEntity> list);

	public void updateAnalysisTemplate(AnalysisTemplateEntity analysisTemplate);

	public AnalysisTemplateEntity getObject(AnalysisTemplateEntity analysisTemplate);

	public List<AnalysisTemplateEntity> getAnalysisTemplateList();

	public List<AnalysisTemplateEntity> getAnalysisTemplateList(AnalysisTemplateEntity analysisTemplate);
}
