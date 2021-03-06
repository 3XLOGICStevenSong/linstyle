package com.djb.ylt.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.ylt.framework.dao.BaseDAOImpl;
import com.djb.ylt.user.entity.TypicalCaseEntity;

@Repository("typicalCaseDao")
public class TypicalCaseDaoImpl extends BaseDAOImpl<TypicalCaseEntity> implements
		ITypicalCaseDao {
}
