package com.djb.ylt.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.user.dao.IGradePriceDao;

import com.djb.ylt.user.entity.GradePriceEntity;


@Service("iGradePriceService")
public class GradePriceServiceImpl extends BaseService implements IGradePriceService {

    @Autowired
    @Qualifier("gradePriceDao")
    private IGradePriceDao gradePriceDao;
	@Override
	public void addGradePrice(GradePriceEntity gradePrice) {
		
		// TODO Auto-generated method stub
		  try {
			  gradePriceDao.insert(gradePrice);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public void deleteGradePrice(GradePriceEntity gradePrice) {
		
		try {
			gradePriceDao.delete(gradePrice);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deleteGradePriceBatch(List<GradePriceEntity> list) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGradePrice(GradePriceEntity gradePrice) {
		
		 try {
			 gradePriceDao.update(gradePrice);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public GradePriceEntity getObject(GradePriceEntity gradePrice) {
		
		  return gradePriceDao.getObject(gradePrice);
		
	}

	@Override
	public List<GradePriceEntity> getGradePriceList() {
		
        return (List<GradePriceEntity>) gradePriceDao.findList();
	}

	@Override
	public List<GradePriceEntity> getGradePriceList(GradePriceEntity gradePrice) {
		 List<GradePriceEntity> list = (List<GradePriceEntity>) gradePriceDao.findListByCondition(gradePrice);
	        return list;
	}

}
