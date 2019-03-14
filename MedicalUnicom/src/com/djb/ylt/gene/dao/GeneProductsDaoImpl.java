package com.djb.ylt.gene.dao;

import org.springframework.stereotype.Repository;
import com.djb.ylt.framework.dao.BaseDAOImpl;
import com.djb.ylt.gene.entity.GeneProductsEntity;




@Repository("geneProductsDao")
public class GeneProductsDaoImpl extends BaseDAOImpl<GeneProductsEntity> implements
		IGeneProductsDao {
}
