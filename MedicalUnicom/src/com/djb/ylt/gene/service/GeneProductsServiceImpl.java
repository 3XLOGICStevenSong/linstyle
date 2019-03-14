package com.djb.ylt.gene.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.gene.dao.IGeneProductsDao;

import com.djb.ylt.gene.entity.GeneProductsEntity;

@Service("iGeneProductsService")
public class GeneProductsServiceImpl extends BaseService implements IGeneProductsService {

    @Autowired
    @Qualifier("geneProductsDao")
    private IGeneProductsDao geneProductsDao;


	@Override
	public void addGeneProducts(GeneProductsEntity GeneProducts) {
		
		 try {
			 geneProductsDao.insert(GeneProducts);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
		
	}

	@Override
	public void deleteGeneProducts(GeneProductsEntity GeneProducts) {
		
		try {
			geneProductsDao.delete(GeneProducts);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deleteGeneProductsBatch(List<GeneProductsEntity> list) {
		
		try {
			geneProductsDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void updateGeneProducts(GeneProductsEntity geneProducts) {
		
		try {
			geneProductsDao.update(geneProducts);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
		
	}

	@Override
	public GeneProductsEntity getObject(GeneProductsEntity geneProducts) {
		 return geneProductsDao.getObject(geneProducts);
	}

	@Override
	public List<GeneProductsEntity> getGeneProductsList() {
		
		return (List<GeneProductsEntity>) geneProductsDao.findList();
	}

	@Override
	public List<GeneProductsEntity> getGeneProductsList(GeneProductsEntity geneProducts) {
		
		 List<GeneProductsEntity> list = (List<GeneProductsEntity>) geneProductsDao.findListByCondition(geneProducts);
	        return list;
	}

	
}
