package com.djb.ylt.health.service;

import java.util.List;

import com.djb.ylt.health.entity.InqueryResultEntity;






public interface IInqueryResultService {
	
    public void addInqueryResult(InqueryResultEntity InqueryResult);

    public void deleteInqueryResult(InqueryResultEntity InqueryResult);

    public void deleteInqueryResultBatch(List<InqueryResultEntity> list);

    public void updateInqueryResult(InqueryResultEntity InqueryResult);

    public InqueryResultEntity getObject(InqueryResultEntity InqueryResult);

    public List<InqueryResultEntity> getInqueryResultList();

    public List<InqueryResultEntity> getInqueryResultList(InqueryResultEntity InqueryResult);

}
