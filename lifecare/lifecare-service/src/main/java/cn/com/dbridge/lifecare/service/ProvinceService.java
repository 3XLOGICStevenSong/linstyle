package cn.com.dbridge.lifecare.service;

import java.util.List;

import cn.com.dbridge.lifecare.dao.po.ProvincePO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileProvinceDTO;

public interface ProvinceService {
    int deleteProvinceById(Integer provinceId);

    int add(MobileProvinceDTO mobileProvinceDTO);

    ProvincePO getProvinceById(Integer provinceId);

    List<ProvincePO> queryAll();

    int updateProvince(MobileProvinceDTO mobileProvinceDTO);
}