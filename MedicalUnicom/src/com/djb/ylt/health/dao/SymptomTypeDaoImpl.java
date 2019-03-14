package com.djb.ylt.health.dao;

import org.springframework.stereotype.Repository;
import com.djb.ylt.framework.dao.BaseDAOImpl;

import com.djb.ylt.health.entity.SymptomTypeEntity;





@Repository("symptomTypeDao")
public class SymptomTypeDaoImpl extends BaseDAOImpl<SymptomTypeEntity> implements
 ISymptomTypeDao {
}
