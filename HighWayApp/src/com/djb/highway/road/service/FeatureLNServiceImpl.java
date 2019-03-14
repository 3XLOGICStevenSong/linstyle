package com.djb.highway.road.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.road.dao.IFeatureLNDao;
import com.djb.highway.road.entity.FeatureLNEntity;

@Service("iFeatureLNService")
public class FeatureLNServiceImpl extends BaseService implements IFeatureLNService {

    @Autowired
    @Qualifier("featureLNDao")
    private IFeatureLNDao featureLNDao;

    @Override
    public void addFeatureLN(FeatureLNEntity featureLN) {

        try {
        	featureLNDao.insert(featureLN);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteFeatureLN(FeatureLNEntity featureLN) {

        try {
        	featureLNDao.delete(featureLN);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteFeatureLNBatch(List<FeatureLNEntity> list) {

        try {
        	featureLNDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }


    @Override
    public FeatureLNEntity getObject(FeatureLNEntity featureLN) {
        return featureLNDao.getObject(featureLN);
    }

    @Override
    public List<FeatureLNEntity> getFeatureLNList() {

        return (List<FeatureLNEntity>) featureLNDao.findList();
    }

    public List<FeatureLNEntity> getFeatureLNList(FeatureLNEntity featureLN) {
        List<FeatureLNEntity> list = (List<FeatureLNEntity>) featureLNDao.findListByCondition(featureLN);
        return list;
    }

	@Override
	public void updateFeatureLN(FeatureLNEntity featureLN) {
		// TODO Auto-generated method stub
				try {
					featureLNDao.update(featureLN);
				} catch (DataNotFoundException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}


   

	}}
