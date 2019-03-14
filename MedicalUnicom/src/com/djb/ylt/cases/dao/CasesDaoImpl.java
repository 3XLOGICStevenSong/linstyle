package com.djb.ylt.cases.dao;

import org.springframework.stereotype.Repository;

import com.djb.ylt.cases.entity.CasesEntity;
import com.djb.ylt.framework.dao.BaseDAOImpl;

@Repository("casesDao")
public class CasesDaoImpl  extends BaseDAOImpl<CasesEntity> implements 
ICasesDao {

}
