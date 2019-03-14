package cn.com.dbridge.lifecare.service.impl;

import java.util.List;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dbridge.lifecare.dao.po.CityPO;
import cn.com.dbridge.lifecare.dao.respository.CityPOMapper;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileCityDTO;
import cn.com.dbridge.lifecare.service.CityService;
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityPOMapper cityPOMapper;

    @Override
    public int deleteCityById(Integer cityId) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int add(MobileCityDTO mobileCityDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public CityPO getCityById(Integer cityId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CityPO> queryAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int updateCity(MobileCityDTO mobileCityDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<CityPO> queryCityByProvinceId(String provinceId) {
        List<CityPO> cityPOList = cityPOMapper
                .selectCityByProvinceId(provinceId);
        if (CollectionUtils.isEmpty(cityPOList)) {
            return null;
        }
        return cityPOList;
    }

}