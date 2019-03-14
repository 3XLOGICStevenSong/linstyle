package com.djb.ylt.gene.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.gene.dao.IProductsPurchaseDao;
import com.djb.ylt.gene.entity.ProductsPurchaseEntity;

@Service("iProductsPurchaseService")
public class ProductsPurchaseServiceImpl extends BaseService implements IProductsPurchaseService {

    @Autowired
    @Qualifier("productsPurchaseDao")
    private IProductsPurchaseDao productsPurchaseDao;


	@Override
	public void addProductsPurchase(ProductsPurchaseEntity productsPurchase) {
		
		 try {
			 productsPurchaseDao.insert(productsPurchase);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
		
	}

	@Override
	public void deleteProductsPurchase(ProductsPurchaseEntity productsPurchase) {
		
		try {
			productsPurchaseDao.delete(productsPurchase);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deleteProductsPurchaseBatch(List<ProductsPurchaseEntity> list) {
		
		try {
			productsPurchaseDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void updateProductsPurchase(ProductsPurchaseEntity productsPurchase) {
		
		try {
			productsPurchaseDao.update(productsPurchase);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
		
	}

	@Override
	public ProductsPurchaseEntity getObject(ProductsPurchaseEntity productsPurchase) {
		 return productsPurchaseDao.getObject(productsPurchase);
	}

	@Override
	public List<ProductsPurchaseEntity> getProductsPurchaseList() {
		
		return (List<ProductsPurchaseEntity>) productsPurchaseDao.findList();
	}

	@Override
	public List<ProductsPurchaseEntity> getProductsPurchaseList(ProductsPurchaseEntity productsPurchase) {
		
		 List<ProductsPurchaseEntity> list = (List<ProductsPurchaseEntity>) productsPurchaseDao.findListByCondition(productsPurchase);
	        return list;
	}

}
