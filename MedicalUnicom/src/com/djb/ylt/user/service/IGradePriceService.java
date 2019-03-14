package com.djb.ylt.user.service;

import java.util.List;

import com.djb.ylt.user.entity.GradePriceEntity;


public interface IGradePriceService {
	
    public void addGradePrice(GradePriceEntity gradePrice);

    public void deleteGradePrice(GradePriceEntity gradePrice);

    public void deleteGradePriceBatch(List<GradePriceEntity> list);

    public void updateGradePrice(GradePriceEntity gradePrice);

    public GradePriceEntity getObject(GradePriceEntity gradePrice);

    public List<GradePriceEntity> getGradePriceList();

    public List<GradePriceEntity> getGradePriceList(GradePriceEntity gradePrice);

   
    
}
