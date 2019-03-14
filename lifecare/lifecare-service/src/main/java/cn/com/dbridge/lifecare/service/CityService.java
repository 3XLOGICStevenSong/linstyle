package cn.com.dbridge.lifecare.service;

import java.util.List;

import cn.com.dbridge.lifecare.dao.po.CityPO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileCityDTO;

public interface CityService {
    int deleteCityById(Integer cityId);

    int add(MobileCityDTO mobileCityDTO);

    CityPO getCityById(Integer cityId);

    List<CityPO> queryAll();

    int updateCity(MobileCityDTO mobileCityDTO);

    List<CityPO> queryCityByProvinceId(String provinceId);
}