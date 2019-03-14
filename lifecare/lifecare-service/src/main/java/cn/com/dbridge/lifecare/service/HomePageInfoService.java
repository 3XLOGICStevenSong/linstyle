package cn.com.dbridge.lifecare.service;

import cn.com.dbridge.lifecare.dao.po.HomePageInfoPO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileHomePageInfoDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileHomePageInfoQueryDTO;
import cn.com.dbridge.lifecare.framework.dto.web.WebHomePageInfoDTO;
/**
 * 
 * @ClassName:  HomePageInfoService
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 陈健飞
 * @date:   2018年12月27日 上午9:12:30
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public interface HomePageInfoService {
    /**
     * 
     * @Title: deleteHomePageInfoById
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param homePageId
     * @return
     */
    int deleteHomePageInfoById(Integer homePageId);
    /**
     * 
     * @Title: add
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param record
     * @return
     */
    int add(MobileHomePageInfoDTO mobileHomePageInfoDTO);
    /**
     * 
     * @Title: getHomePageInfoById
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param homePageId
     * @return
     */
    HomePageInfoPO getHomePageInfoById(Integer homePageId);

    /**
     * 
     * @Title: saveHomePageInfo
     * @author 王林江
     * @Description: 更新主页信息
     * @param webHomePageInfoDTO 主页信息DTO
     * @return
     */
    int saveHomePageInfo(WebHomePageInfoDTO webHomePageInfoDTO);
    
    /**
     * 
     * @Title: getNewestHomePageInfo
     * @Description: 获取最新的主页信息
     * @return
     */
    HomePageInfoPO getNewestHomePageInfo(
            MobileHomePageInfoQueryDTO mobileHomePageInfoQueryDTO);

    /**
     * 
     * @Title: getHomePageInfo
     * @author 郭健
     * @Description: 获取首页信息
     * @return
     */
    HomePageInfoPO getHomePageInfo();
}