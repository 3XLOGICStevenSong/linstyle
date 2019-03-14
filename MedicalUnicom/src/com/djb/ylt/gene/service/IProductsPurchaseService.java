package com.djb.ylt.gene.service;

import java.util.List;

import com.djb.ylt.gene.entity.ProductsPurchaseEntity;

public interface IProductsPurchaseService {

	public void addProductsPurchase(ProductsPurchaseEntity productsPurchase);

	public void deleteProductsPurchase(ProductsPurchaseEntity productsPurchase);

	public void deleteProductsPurchaseBatch(List<ProductsPurchaseEntity> list);

	public void updateProductsPurchase(ProductsPurchaseEntity productsPurchase);

	public ProductsPurchaseEntity getObject(ProductsPurchaseEntity productsPurchase);

	public List<ProductsPurchaseEntity> getProductsPurchaseList();

	public List<ProductsPurchaseEntity> getProductsPurchaseList(ProductsPurchaseEntity productsPurchase);
}
