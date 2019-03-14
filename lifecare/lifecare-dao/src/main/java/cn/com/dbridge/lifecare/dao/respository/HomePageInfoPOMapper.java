package cn.com.dbridge.lifecare.dao.respository;

import cn.com.dbridge.lifecare.dao.po.HomePageInfoPO;

public interface HomePageInfoPOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HomePageInfoPO record);

    int insertSelective(HomePageInfoPO record);

    HomePageInfoPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomePageInfoPO record);


    int updateByPrimaryKey(HomePageInfoPO record);

    HomePageInfoPO getCountHomePageInfo();

    int updateHomePageInfo(HomePageInfoPO homePageInfoPO);

    int insertHomePageInfo(HomePageInfoPO homePageInfoPO);

    HomePageInfoPO selectAll();
}