package com.djb.highway.road.service;

import java.util.List;

import com.djb.highway.road.entity.FeatureLNEntity;



public interface IFeatureLNService {
	public void addFeatureLN(FeatureLNEntity featureLN);

	public void deleteFeatureLN(FeatureLNEntity featureLN);

	public void deleteFeatureLNBatch(List<FeatureLNEntity> list);

	public void updateFeatureLN(FeatureLNEntity featureLN);

	public FeatureLNEntity getObject(FeatureLNEntity featureLN);

	public List<FeatureLNEntity> getFeatureLNList();

	public List<FeatureLNEntity> getFeatureLNList(FeatureLNEntity featureLN);
	
}

