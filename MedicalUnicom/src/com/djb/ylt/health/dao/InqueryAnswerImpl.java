package com.djb.ylt.health.dao;

import org.springframework.stereotype.Repository;
import com.djb.ylt.framework.dao.BaseDAOImpl;
import com.djb.ylt.health.entity.InqueryAnswerEntity;





@Repository("inqueryAnswerDao")
public class InqueryAnswerImpl extends BaseDAOImpl<InqueryAnswerEntity> implements
 IInqueryAnswerDao {
}
