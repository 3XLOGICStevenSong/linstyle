package com.djb.ylt.main_page.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.service.BaseService;

import com.djb.ylt.main_page.dao.IServiceHotlineDao;

import com.djb.ylt.main_page.entity.ServiceHotlineEntity;

/**
 * @date 2016年12月13日 下午6:08:54
 * @author chengming
 * 内容：android端首页轮播图service实现
 * 修改履历：2016年12月13日 下午6:08:54 chengming 
 */
@Service("serviceHotlineService")
public class ServiceHotlineServiceImpl extends BaseService implements IServiceHotlineService {
	@Autowired
	@Qualifier("serviceHotlineDao")
	private IServiceHotlineDao iServiceHotlineDao;
	/**
	 * 
	* @Title: getCarouselList 
	* @Description:(方法描述) 获得轮播图的list
	* @param carouselEntity 传入获取轮播图的参数（roleId）
	* @return
	* @see com.djb.ylt.main_page.service.ICarouselService#getCarouselList(com.djb.ylt.main_page.entity.CarouselEntity) 
	* @throws
	 */
	
	@Override
	public ServiceHotlineEntity getServiceHotline() {
		
		return iServiceHotlineDao.getObject();
	
	}

}
