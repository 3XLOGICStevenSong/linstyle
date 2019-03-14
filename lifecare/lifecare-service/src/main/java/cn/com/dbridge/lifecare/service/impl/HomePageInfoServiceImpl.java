package cn.com.dbridge.lifecare.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dbridge.lifecare.dao.po.HomePageInfoPO;
import cn.com.dbridge.lifecare.dao.respository.HomePageInfoPOMapper;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileHomePageInfoDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileHomePageInfoQueryDTO;
import cn.com.dbridge.lifecare.framework.dto.web.WebHomePageInfoDTO;
import cn.com.dbridge.lifecare.service.HomePageInfoService;
@Service
public class HomePageInfoServiceImpl implements HomePageInfoService {
    @Autowired
    private HomePageInfoPOMapper homePageInfoPOMapper;

    @Override
    public int deleteHomePageInfoById(Integer homePageId) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int add(MobileHomePageInfoDTO mobilePageInfoDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public HomePageInfoPO getHomePageInfoById(Integer homePageId) {
        // TODO Auto-generated method stub
        return null;
    }

    /****
    @Title: saveHomePageInfo
    * @author 王林江
    * @Description: 更新主页信息
    * @param webHomePageInfoDTO 主页信息DTO
    * @return
    */
    @Override
    public int saveHomePageInfo(WebHomePageInfoDTO webHomePageInfoDTO) {
        //保存影响记录数
        int affectNum = 0;
        //主页信息PO
        HomePageInfoPO homePageInfoPO = new HomePageInfoPO();
        //主页信息PO赋值
        BeanUtils.copyProperties(webHomePageInfoDTO, homePageInfoPO);
        //获取主页信息表的记录数
        HomePageInfoPO resultHomePageInfoPO = homePageInfoPOMapper.getCountHomePageInfo();
        //主页信息表存在的场合
        if (null != resultHomePageInfoPO) {
            //更新人
            homePageInfoPO.setUpdateBy(webHomePageInfoDTO.getLoginUserId());
            homePageInfoPO.setId(resultHomePageInfoPO.getId());
            //更新主页信息
            affectNum = homePageInfoPOMapper.updateHomePageInfo(homePageInfoPO);
        } else {
            //创建人
            homePageInfoPO.setCreateBy(webHomePageInfoDTO.getLoginUserId());
            homePageInfoPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
            //添加主页信息
            affectNum = homePageInfoPOMapper.insertHomePageInfo(homePageInfoPO);
        }
        //保存影响记录数
        return affectNum;
    }

    /**   
     * Title: getNewestHomePageInfo  
     * Description:  
     * @param mobileHomePageInfoQueryDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.HomePageInfoService#getNewestHomePageInfo(cn.com.dbridge.lifecare.framework.dto.mobile.MobileHomePageInfoQueryDTO)   
     */
    @Override
    public HomePageInfoPO getNewestHomePageInfo(
            MobileHomePageInfoQueryDTO mobileHomePageInfoQueryDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 
     * Title: getHomePageInfo  
     * Description:获取首页信息
     * @author 郭健
     * @param mobileHomePageInfoDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.HomePageInfoService#updateHomePageInfo(cn.com.dbridge.lifecare.framework.dto.mobile.MobileHomePageInfoDTO)
     */
    @Override
    public HomePageInfoPO getHomePageInfo() {
        HomePageInfoPO homePageInfoPO = homePageInfoPOMapper.selectAll();
        if (null == homePageInfoPO) {
            return null;
        }
        return homePageInfoPO;
    }
}