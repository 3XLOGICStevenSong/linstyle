package com.djb.ylt.health.service;

import java.util.List;

import com.djb.ylt.health.entity.InqueryQuestionEntity;






public interface IInqueryQuestionService {
	
    public void addInqueryQuestion(InqueryQuestionEntity InqueryQuestion);

    public void deleteInqueryQuestion(InqueryQuestionEntity InqueryQuestion);

    public void deleteInqueryQuestionBatch(List<InqueryQuestionEntity> list);

    public void updateInqueryQuestion(InqueryQuestionEntity InqueryQuestion);

    public InqueryQuestionEntity getObject(InqueryQuestionEntity InqueryQuestion);

    public List<InqueryQuestionEntity> getInqueryQuestionList();

    public List<InqueryQuestionEntity> getInqueryQuestionList(InqueryQuestionEntity InqueryQuestion);

}
