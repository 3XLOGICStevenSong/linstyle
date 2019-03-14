package com.djb.ylt.health.dao;

import org.springframework.stereotype.Repository;
import com.djb.ylt.framework.dao.BaseDAOImpl;
import com.djb.ylt.health.entity.InqueryResultEntity;






@Repository("inqueryResultDao")
public class InqueryResultImpl extends BaseDAOImpl<InqueryResultEntity> implements
 IInqueryResultDao {
}
