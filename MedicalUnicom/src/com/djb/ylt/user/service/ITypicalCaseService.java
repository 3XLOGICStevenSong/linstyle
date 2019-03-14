package com.djb.ylt.user.service;

import java.util.List;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.user.entity.TypicalCaseEntity;

/**
 * @date 2017年04月25日 下午11:09:09
 * @author fanzeng
 * 内容：android端首页轮播图service接口
 */
public interface ITypicalCaseService {

    public void addTypicalCase(TypicalCaseEntity typicalCase) throws Exception;

    public void deleteTypicalCase(TypicalCaseEntity typicalCase);

    public void deleteTypicalCaseBatch(List<TypicalCaseEntity> list);

    public void updateTypicalCase(TypicalCaseEntity typicalCase) throws DataNotFoundException;

    public TypicalCaseEntity getObject(TypicalCaseEntity typicalCase);
    
    public TypicalCaseEntity getTypicalCaseDc(TypicalCaseEntity typicalCaseEntity);
	/**
	* 
	* @Title: getCarouselList 
	* @Description:(方法描述) 获得轮播图的list
	* @param carouselEntity 传入获取轮播图的参数（roleId）
	* @return
	* @see com.djb.ylt.user.service.ITypicalCaseService#getTypicalCaseList(com.djb.ylt.user.entity.TypicalCaseEntity) 
	* @throws
	*/
    public List<TypicalCaseEntity> getTypicalCaseList();

    public List<TypicalCaseEntity> getTypicalCaseList(TypicalCaseEntity typicalCase);
}